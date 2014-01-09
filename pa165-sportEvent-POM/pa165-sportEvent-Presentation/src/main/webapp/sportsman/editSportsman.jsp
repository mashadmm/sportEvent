<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/menu.jsp" nadpis="Sportsman">
    <s:layout-component name="telo">

        <s:useActionBean beanclass="com.pa165.sportEventpresentation.SportsmanActionBean" var="actionBean"/>

        <s:form beanclass="com.pa165.sportEventpresentation.SportsmanActionBean">
            <s:hidden name="sportsmanDTO.sportsmanId"/>
            <fieldset><legend><f:message key="sportsman.edit"/></legend>
                <%@include file="sportsmanForm.jsp"%>
                <s:submit name="saveEdit" class="btn"><f:message key="Edit"/></s:submit>
                </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>