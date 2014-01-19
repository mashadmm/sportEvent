<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/menu.jsp" nadpis="Sportsman">
    <s:layout-component name="telo">
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}" >
                <s:useActionBean beanclass="com.pa165.sportEventpresentation.SportsmanActionBean" var="actionBean"/>

                <s:form beanclass="com.pa165.sportEventpresentation.SportsmanActionBean">          
                    <fieldset><legend><f:message key="sportsman.add"/></legend>
                        <%@include file="sportsmanForm.jsp"%>
                        <br>
                        <s:submit name="saveAdd" class="btn" ><f:message key="Add" /></s:submit>
                        </fieldset>
                </s:form>
        </c:if>  
    </s:layout-component>
</s:layout-render>
