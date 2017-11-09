<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: YohanBoichut
  Date: 06/10/2016
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="perdu.titre"/></title>
</head>
<sb:head/>
<body>

<div class="col-md-offset-3 col-md-6 text-center">

    <h2><s:text name="perdu.accueuil"/></h2>
<br/>

    <s:property value="pseudo"/>, <s:text name="perdu.menu"/>

    <br/>
    <ul>
        <li><s:a action="deconnexion"><s:text name="abandonner"/></s:a></li>
        <li><s:a action="backToChoix"><s:text name="rejouer"/></s:a> </li>
    </ul>

    </div>
</body>
</html>
