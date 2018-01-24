<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: o2144945
  Date: 07/12/16
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Erreur</title>
</head>
<body>
    <h1><s:text name="error.title"></s:text></h1>
    <s:property value="erreur"></s:property>
    <s:action name="GoToMenu">Retourner au menu</s:action>
</body>
</html>
