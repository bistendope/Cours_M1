<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="a" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: o2144945
  Date: 07/12/16
  Time: 09:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="menu.title"></s:text></title>
</head>
<body>
    <h1><s:text name="menu.title"></s:text></h1>

    <s:if test="estAdmin" >
        <h1><s:text name="menu.admin"></s:text></h1>

        <h2><s:text name="menu.admin2"></s:text></h2>
        <s:iterator value="listeDesDemandesNonTraitees" status="x">
            <p>
            Nom : <s:property value="nom"></s:property> <br>
            Mdp : <s:property value="mdp"></s:property> <br>
            Role : <s:property value="roleDemande"></s:property>
            <s:a action="ValidateRegister">
                <s:param name="id"><s:property value="identifiant"></s:property></s:param>
                <s:text name="menu.valider"></s:text>
            </s:a><br>
            <s:a action="InvalidateRegister">
                <s:param name="id"><s:property value="identifiant"></s:property></s:param>
                <s:text name="menu.invalider"></s:text>
            </s:a><br>
            </p>
            <br><br><br>

        </s:iterator>
    </s:if>

    <s:if test="estVendeur">
        <hr>
        <h1><s:text name="menu.vendeur"></s:text></h1>
    </s:if>
    <s:if test="estRespon">
        <hr>
        <h1><s:text name="menu.respon"></s:text></h1>
    </s:if>

    <s:a action="Logout">se déconnecter</s:a>





</body>
</html>
