<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@taglib uri="/struts-tags"  prefix="s"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table class="easyui-datagrid" fit="true" nowrap="false">
			<thead>
				<tr>
					<th data-options="field:'id',width:120">任务编号</th>
					<th data-options="field:'name',width:120">任务名称</th>
					<th data-options="field:'data',width:520">业务数据</th>
					<th data-options="field:'pick',width:120">办理任务</th>
				</tr>
			</thead>
			<tbody>
				<!-- Map<Task,Map<String,Object>> -->
				<s:iterator var="entry1">
					<tr>
						<td> <s:property value="#entry1.key.id"/> </td>
						<td> <s:property value="#entry1.key.name"/></td>
						<td>
							<!-- variables 是map 遍历获得每个entry -->
							<s:iterator value="#entry1.value" var="entry2">
								<s:property value="#entry2.key"/> ：<s:property value="#entry2.value" escapeHtml="false"/>
							</s:iterator>
						</td>
						<td> 
							<s:a action="task_completeTask" cssClass="easyui-linkbutton" >
								办理任务
								<s:param name="id" value="#entry1.key.id"></s:param>
							</s:a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</body>
</html>