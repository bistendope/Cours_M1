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
    <title>Ajouter un étudiant</title>
</head>
<body>
Felicitations Etudiant rajouté! <br/>

<s:text name="etudiantAjoute.numEtudiant"></s:text><s:property value="etudiant.numeroEtudiant"></s:property><br/>
<s:text name="etudiantAjoute.nomEtu"></s:text><s:property value="etudiant.nom"></s:property><br/>
<s:text name="etudiantAjoute.prenomEtu"></s:text><s:property value="etudiant.prenom"></s:property><br/>
<s:text name="etudiantAjoute.dateNaissanceEtu"></s:text><s:property value="etudiant.dateDeNaissance"></s:property><br/>


<br/><br/>
<s:a action="goToMenu">Retour au menu</s:a>
</body>
</html>
