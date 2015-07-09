package com.taotao.sso.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.vo.TaotaoResult;
import com.taotao.sso.pojo.User;
import com.taotao.sso.service.UserService;

@RequestMapping("user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 检查数据是否可用
     * 
     * @param param 校验的数据
     * @param type type为类型，可选参数1、2、3分别代表username、phone、email
     * @return
     */
    @RequestMapping(value = "check/{param}/{type}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult check(@PathVariable("param") String param, @PathVariable("type") Integer type) {
        return this.userService.check(param, type);
    }

    /**
     * 用户注册
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(@Valid User user, BindingResult result) {
        // 判断是否存在错误
        if (result.hasErrors()) {
            // 获取所有错误对象
            List<ObjectError> errors = result.getAllErrors();
            // 获取错误信息
            List<String> strs = new ArrayList<String>();
            for (ObjectError objectError : errors) {
                // getDefaultMessage 校验规则中 Message
                strs.add(objectError.getDefaultMessage());
            }
            return TaotaoResult.build(400, StringUtils.join(strs, '|'));
        }
        // 对数据做校验
        return this.userService.saveUser(user);
    }

    /**
     * 登录
     * 
     * @param username
     * @param passwd
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult login(@RequestParam("u") String username, @RequestParam("p") String passwd) {
        return this.userService.login(username, passwd);
    }

    /**
     * 根据ticket查询用户信息
     * 
     * @param ticket
     * @return
     */
    @RequestMapping(value = "query/{ticket}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult query(@PathVariable("ticket") String ticket) {
        return this.userService.queryByTicket(ticket);
    }

}
