<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
 
<s:layout-render name="/menu.jsp" nadpis="SportmenEvents">
    <s:layout-component name="telo">
    <s:useActionBean beanclass="com.pa165.sportEventpresentation.SportsmanActionBean" var="actionBean"/>
    
     <f:message key="Sportsman"/>: <b>${actionBean.sportsmanDTO.name} </b><b>${actionBean.sportsmanDTO.lastname} </b><br/>
   <f:message key="Total"/> = ${actionBean.getEvents().size()}:
        <table class="table">
            <tr>
                <th><f:message key="Number"/></th>
                <th><f:message key="Id"/></th>
                <th><f:message key="event.name"/></th>               
                <th><f:message key="event.dateOfEvent"/></th>
                <th><f:message key="event.sport"/></th>
                <th><f:message key="grade.grade"/></th>
            </tr>
            <c:forEach items="${actionBean.getEvents()}" var="entry" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${entry.key.eventId}</td>                   
                    <td><c:out value="${entry.key.name}"/></td> 
                    <td><c:out value="${actionBean.sdfSource.format(entry.key.dateOfEvent)}"/></td>
                    <td><c:out value="${entry.key.sport.name}"/></td>
                    <td><c:out value="${entry.value.grade}"/></td> 
                </tr>
            </c:forEach>
            
        </table>
        <s:form beanclass="com.pa165.sportEventpresentation.SportsmanActionBean">
           <s:hidden name="sportsmanDTO.sportsmanId"/>
            <fieldset><legend><f:message key="sportsman.registerToEvent"/></legend>
                <%@include file="registerSportsmanToEventForm.jsp"%>
            <s:submit name="registerToEvent"><f:message key="sportsman.register"/></s:submit>
            </fieldset>
        </s:form>
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}" >
            <s:link beanclass="com.pa165.sportEventpresentation.SportsmanActionBean" event="all"><f:message key="Back"/></s:link> 
        </c:if> 
        
    </s:layout-component>
</s:layout-render>