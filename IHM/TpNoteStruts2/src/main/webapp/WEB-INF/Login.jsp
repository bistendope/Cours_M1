<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accueil</title>
</head>
<body>
    <h1><s:text name="login.title"></s:text></h1>
    <s:form action="Login" label="Connexion">
        <s:textfield name="username" key="login.username"/>
        <s:password name="password" key="login.password"/>
        <s:submit label="login.submit"/>
    </s:form>
    <s:a action="GoToRegister">Faire une demande d'inscription</s:a>
</body>
</html>
