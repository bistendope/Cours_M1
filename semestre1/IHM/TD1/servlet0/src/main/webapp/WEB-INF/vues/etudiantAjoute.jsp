<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: o2122130
  Date: 27/09/17
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Genial</title>
</head>
<body>
Bravo etudiant ajouté!

<p>
<table>
    Liste des étudiants:
    <c:forEach items="${listeEtu}" var="unEtu">
        <tr>
            <td>${unEtu.nom}</td>
            <td>${unEtu.prenom}</td>
        </tr>
    </c:forEach>
</table>
</p>
</body>
</html>
