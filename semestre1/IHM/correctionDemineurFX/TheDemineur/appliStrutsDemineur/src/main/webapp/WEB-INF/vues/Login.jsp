<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: YohanBoichut
  Date: 06/10/2016
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="login.titre"/></title>
    <sb:head/>
</head>
<body>
<div class="col-md-offset-3 col-md-6 text-center">
    <h1><s:text name="login.accueil"/></h1>


<s:form action="login" theme="bootstrap" cssClass="well form-vertical">
    <s:textfield name="username" key="username"/>
    <s:password key="motdepasse" name="password"/>
    <s:submit key="bouton.GO"/>
</s:form>
    <br>
    <s:a action="goToInscription">
    <s:text name="inscription"/>
    </s:a>
</div>
</body>
</html>
