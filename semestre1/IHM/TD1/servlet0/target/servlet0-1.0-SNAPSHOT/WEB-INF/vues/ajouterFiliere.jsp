<%--
  Created by IntelliJ IDEA.
  User: o2122130
  Date: 20/09/17
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajouter filière</title>
</head>
<body>
<form method="POST" id="form1" action="/control?action=ajouterProf">
    <label>Nom:</label> <input name="nom" type="text"/><br/>
    <label>Prenom: </label><input name="prenom" type="text"/><br/>
    <label>Date de naissance: </label><input name="dateNaissance" type="date"/><br/>
    <input type="submit" value="envoyer"/><br/>
</form>
</body>
</html>
