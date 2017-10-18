<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: o2122130
  Date: 16/10/17
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demineur</title>
    <sb:head/>
</head>
<body>
<div class="container">
    <table>
        <s:iterator value="%{plateau.getMonPlateau}" var="ligneCase" status="iterStatus">
            <tr>
            <s:iterator value="#ligneCase" var="case">
                <td><s:property value="%{#case.getValeur}"/></td>
            </s:iterator>
            <tr/>
        </s:iterator>
    </table>

</div>

</body>
</html>