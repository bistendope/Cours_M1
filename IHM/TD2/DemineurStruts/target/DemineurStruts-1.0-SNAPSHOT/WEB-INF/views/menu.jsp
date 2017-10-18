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
    <title>Menu</title>
    <sb:head/>
</head>
<body>
<div class="container">
    <s:actionerror theme="bootstrap"/>
    <s:actionmessage theme="bootstrap"/>
    <s:fielderror theme="bootstrap"/>
    <s:form action="menu" theme="bootstrap" cssClass="form-horizontal">
        <legend cssClass="center-block">Paramétrez votre partie ou cliquez directement sur Lancer</legend>
        <s:textfield name="percentBombes"  key="formMenu.percentBombes"></s:textfield>
        <s:textfield name="taille"  key="formMenu.taille"></s:textfield>

        <s:submit value="Lancer" cssClass="center-block"></s:submit>
    </s:form>
</div>

</body>
</html>
