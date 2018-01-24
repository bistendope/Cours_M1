<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: YohanBoichut
  Date: 06/10/2016
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="gagne.titre"/></title>
</head>
<sb:head/>

<body>

<div class="col-md-offset-3 col-md-6 text-center">
<s:text name="gagne.accueil"/> <s:property value="pseudo"/>, <s:text name="gagne.dora"/>

<br/>

<s:text name="gagne.classement"/>

    <s:iterator value="classement.classement" status="x">
        <li><s:property value="#x.count"/> - <s:property value="joueur.pseudoJoueur"/>, <s:property value="tempsRealise"/> </li>
    </s:iterator>

    </ul>

    <s:a action="backToChoix"><s:text name="retourChoix"/></s:a>


    </div>
</body>
</html>
