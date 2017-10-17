<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: o2144945
  Date: 07/12/16
  Time: 09:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="register.title"></s:text></title>
</head>
<body>
    <h1><s:text name="register.title"></s:text></h1>
    <s:form action="Register" label="Inscription">
        <s:textfield name="username" key="login.username"></s:textfield>
        <s:password name="password" key="login.password"></s:password>
        <s:password name="confirmationMDP" key="register.confirmPass"></s:password>
        <s:select name="role" list="listeRole"></s:select>
        <s:submit key="register.submit"></s:submit>
    </s:form>
    <s:a action="GoToLogin">Se connecter</s:a>

</body>
</html>
