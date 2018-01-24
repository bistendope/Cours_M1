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
    <title><s:text name="formAjouterEtudiant.titre"></s:text></title>
</head>
<body>
<s:form action="ajouterEtudiant">
    <s:textfield name="numEtudiant" key="formAjouterEtu.numEtudiant"></s:textfield>
    <s:textfield name="nomEtu" key="formAjouterEtu.nom"></s:textfield>
    <s:textfield name="prenomEtu" key="formAjouterEtu.prenom"></s:textfield>
    <s:textfield name="dateDeNaissanceEtu" key="formAjouterEtu.dateNaissance"></s:textfield>
    <s:submit></s:submit>
</s:form>

<s:a action="goToMenu"><s:text name="formAjouterProf.retourMenu"></s:text></s:a>
</body>
</html>
