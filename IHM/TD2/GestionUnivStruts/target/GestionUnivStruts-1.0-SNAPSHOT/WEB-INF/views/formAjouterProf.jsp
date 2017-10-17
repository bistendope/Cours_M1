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
    <title><s:text name="formAjouterProf.titre"></s:text></title>
</head>
<body>
<s:form action="ajouterProf">
    <s:textfield name="nomProf" key="formAjouterProf.nom"></s:textfield>
    <s:textfield name="prenomProf" key="formAjouterProf.prenom"></s:textfield>
    <s:textfield name="dateDeNaissanceProf" key="formAjouterProf.dateNaissance"></s:textfield>
    <s:submit></s:submit>
</s:form>

<s:a action="goToMenu"><s:text name="formAjouterProf.retourMenu"></s:text></s:a>
</body>
</html>
