<%--
  Created by IntelliJ IDEA.
  User: o2122130
  Date: 20/09/17
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter Etudiant</title>
</head>
<body>
<form method="POST" id="form1" action="/control?action=ajouterEtudiant">
    <label>Numéro étudiant:</label> <input name="numEtudiant" type="text"/><br/>
    <label>Nom:</label> <input name="nom" type="text"/><br/>
    <label>Prenom: </label><input name="prenom" type="text"/><br/>
    <label>Date de naissance: </label><input name="dateNaissance" type="date"/><br/>
    <input type="submit" value="envoyer"/><br/>
</form>
</body>
</html>
