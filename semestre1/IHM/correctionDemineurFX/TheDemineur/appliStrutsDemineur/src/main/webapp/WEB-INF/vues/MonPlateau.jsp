<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: YohanBoichut
  Date: 06/10/2016
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="monplateau.titre"/></title>
    <sb:head/>

    <SCRIPT LANGUAGE = "JavaScript">
        function envoyer(x,y)
        {
            document.getElementById("hiddenX").value = x;
            document.getElementById("hiddenY").value = y;
            document.getElementById("leTerrain").submit();
        }
 function nothing(){}
    </SCRIPT>
</head>
<body>
<div class="col-md-offset-3 col-md-6 text-center">

    <h2><s:text name="monplateau.gogo"/> <s:property value="pseudo"/></h2>

<s:form theme="simple" action="jouer" id="leTerrain">
<table>
<s:hidden name="hiddenX" id="hiddenX" /> <s:hidden name="hiddenY" id="hiddenY" />


    <s:iterator value="plateauJoueurCourant" var="ligne" status="posX">
        <tr>
        <s:iterator value="ligne" var="lacase"  status="posY">
            <s:if test="%{#lacase.cachee}">
            <td><s:submit value="U" style="width: 100px" onclick="envoyer(%{#posX.index},%{#posY.index})"/></td>
            </s:if>
            <s:else>
                <td>
                    <s:submit value="%{valeur}"  style="width: 100px" disabled="true"/>
                </td>
            </s:else>
        </s:iterator>
        </tr>
    </s:iterator>

    <s:iterator value="plateauJoueurCourant" var="ligne" status="posX">
        <tr>
            <s:iterator value="ligne" var="lacase"  status="posY">
                    <td>
                        <s:submit value="%{valeur}"  style="width: 100px" disabled="true"/>
                    </td>

            </s:iterator>
        </tr>
    </s:iterator>
</table>

    <h2> <s:text name="monplateau.encouragement"/></h2>
</div>


</s:form>




</body>
</html>
