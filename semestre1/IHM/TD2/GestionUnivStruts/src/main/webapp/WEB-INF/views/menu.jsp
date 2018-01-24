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
    <title><s:text name="menu.titre"></s:text> </title>
</head>
<body>
<h1><s:text name="menu.header"></s:text></h1>
<ul>
    <li><s:a action="goToAjouterProf"><s:text name="menu.ajouterProf"></s:text></s:a> </li>
    <li><s:a action="goToAjouterFormation"><s:text name="menu.ajouterFormation"></s:text></s:a> </li>
    <li><s:a action="goToAjouterEtudiant"><s:text name="menu.ajouterEtudiant"></s:text></s:a> </li>
</ul>
</body>
</html>
