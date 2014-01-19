<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/menu.jsp" nadpis="Event">
    <s:layout-component name="telo">
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}" >
            <s:useActionBean beanclass="com.pa165.sportEventpresentation.EventActionBean" var="actionBean"/>

            <s:form beanclass="com.pa165.sportEventpresentation.EventActionBean">
                <s:hidden name="eventDTO.eventId"/>
                <fieldset><legend><f:message key="event.edit"/></legend>
                    <%@include file="eventForm.jsp"%>
                    <s:submit name="saveEdit" class="btn"><f:message key="Edit"/></s:submit>
                    </fieldset>
            </s:form>
      </c:if>  
    </s:layout-component>
</s:layout-render>