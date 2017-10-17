<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: o2122130
  Date: 16/10/17
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accueil</title>
    <sb:head/>
</head>
<body>
<div class="container">
    <s:actionerror theme="bootstrap"/>
    <s:actionmessage theme="bootstrap"/>
    <s:fielderror theme="bootstrap"/>
    <s:form action="connexion" theme="bootstrap" cssClass="form-horizontal">
        <legend cssClass="center-block">Veuillez vous identifier</legend>
        <s:textfield name="pseudo"  key="formConnexion.pseudo"></s:textfield>
        <s:submit cssClass="center-block"></s:submit>
    </s:form>
</div>

</body>
</html>
