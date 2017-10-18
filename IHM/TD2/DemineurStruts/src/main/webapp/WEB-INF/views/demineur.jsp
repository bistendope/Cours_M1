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
        <s:iterator value="%{plateau.getMonPlateau}" var="ligneCase" status="statusX">
            <tr>
            <s:iterator value="#ligneCase" var="case" status="statusY">
                <td>
                    <s:url action="jouer" var="jeu">
                        <s:param name="xplayed" value="#statusX.index"></s:param>
                        <s:param name="yplayed" value="#statusY.index"></s:param>
                    </s:url>
                    <s:if test="#case.cachee">
                        <s:a href="%{jeu}" cssClass="btn btn-primary"></s:a>
                    </s:if>
                    <s:else>
                        <s:if test="#case.valeur == -1">
                            <s:a href="%{jeu}" cssClass="btn-primary btn">B</s:a>
                        </s:if>
                        <s:else>
                            <s:a href="%{jeu}" cssClass="btn-primary btn"><s:property value="#case.valeur"/></s:a>
                        </s:else>
                    </s:else>

                </td>
            </s:iterator>
            <tr/>
        </s:iterator>
    </table>

</div>

</body>
</html>