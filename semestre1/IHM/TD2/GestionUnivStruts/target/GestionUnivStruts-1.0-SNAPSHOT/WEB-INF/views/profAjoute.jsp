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
    <title>Ajout d'un prof</title>
</head>
<body>
Felicitations prof rajouté! <br/>

<s:text name="profAjoute.nomProf"></s:text><s:property value="prof.nom"></s:property><br/>
<s:text name="profAjoute.prenomProf"></s:text><s:property value="prof.prenom"></s:property><br/>
<s:text name="profAjoute.dateNaissanceProf"></s:text><s:property value="prof.dateDeNaissance"></s:property><br/>
<s:text name="profAjoute.id"></s:text><s:property value="prof.id"></s:property><br/>

<br/><br/>
<s:a action="goToMenu">Retour au menu</s:a>
</body>
</html>
