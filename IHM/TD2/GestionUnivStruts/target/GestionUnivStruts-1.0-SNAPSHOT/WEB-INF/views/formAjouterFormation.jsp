<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: o2122130
  Date: 04/10/17
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<s:form action="ajouterFormation">
    <s:textfield name="libelle" key="formAjouterFormation.nomProf"></s:textfield>
    <s:select label="Quel professeur devra s'occuper de cette matière"
              headerKey="0" headerValue="Professeur"
              list="#application.facade.profsBase"
              listKey="id"
              listValue="nom +' ' + prenom"
              name="responsable"/>
    <s:submit></s:submit>
    </s:form>
<br/>
<s:a action="goToMenu">Retour au menu</s:a>
</body>
</html>
