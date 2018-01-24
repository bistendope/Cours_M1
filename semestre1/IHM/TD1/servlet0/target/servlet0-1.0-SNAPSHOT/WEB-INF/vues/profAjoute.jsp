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
    <title>genial!</title>
</head>
<body>
prof ajouté youhou !

<jsp:useBean id="prof" class="modele.Prof" scope="request"></jsp:useBean>

Le prof <c:out value="${prof.nom} ${prof.prenom}"></c:out> est ajouté à la base de données!

<p>
    <table>
    Liste des profs:
    <c:forEach items="${listeProfs}" var="unProf">
        <tr>
            <td>${unProf.nom}</td>
            <td>${unProf.prenom}</td>
        </tr>
    </c:forEach>
    </table>
</p>
</body>
</html>
