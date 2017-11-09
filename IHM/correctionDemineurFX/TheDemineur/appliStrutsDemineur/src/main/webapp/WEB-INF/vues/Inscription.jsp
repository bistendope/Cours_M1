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
    <title><s:text name="inscription.titre"/></title>
    <sb:head/>
</head>
<body>
<div class="col-md-offset-3 col-md-6 text-center">
    <h1><s:text name="inscription.accueil"/></h1>


<s:form action="inscription" theme="bootstrap" cssClass="well form-vertical">
    <s:textfield name="username" key="username"/>
    <s:password key="motdepasse" name="password"/>
    <s:password key="confirmationMDP" name="confirmationMDP"/>
    <s:submit key="bouton.GO"/>
</s:form>
</div>
</body>
</html>
