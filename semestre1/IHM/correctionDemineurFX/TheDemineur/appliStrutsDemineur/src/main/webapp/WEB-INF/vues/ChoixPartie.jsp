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
    <title><s:text name="choixpartie.titre"/></title>
</head>
<sb:head/>
<body>

<div class="col-md-offset-3 col-md-6 text-center">


    <s:property value="pseudo"/>, <s:text name="choixpartie"/>
    <br>

    <ul>
    <s:iterator value="identifiantsParties">
            <li> <s:a action="choixPartie"><s:text name="partie"/> <s:property/><s:param name="idPartie"><s:property/></s:param> </s:a> (<s:a action="voirClassement"><s:text name="classement"/><s:param name="idPartie"><s:property/></s:param> </s:a> ) </li>
    </s:iterator>

        <li><s:a action="deconnexion"><s:text name="abandonner"/></s:a></li>
    </ul>

</div>
</body>
</html>
