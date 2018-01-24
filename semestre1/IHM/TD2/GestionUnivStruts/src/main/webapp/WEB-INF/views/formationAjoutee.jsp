<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: o2122130
  Date: 09/10/17
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formation ajoutée</title>
</head>
<body>
Formation ajoutée ! <br/>

<s:text name="formationAjoutee.libelle"></s:text><s:property value="filiere.libelle"></s:property><br/>
<s:text name="formationAjoutee.responsable"></s:text><s:property value="filiere.responsable.nom"></s:property> <s:property value="filiere.responsable.prenom"></s:property><br/>
<br/><br/>
<s:a action="goToMenu">Retour au menu</s:a>
</body>
</html>
