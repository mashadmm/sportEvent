<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/menu.jsp" nadpis="Event">
    <s:layout-component name="telo">

        <s:useActionBean beanclass="com.pa165.sportEventpresentation.EventActionBean" var="actionBean"/>
        <f:message key="Total"/> = ${actionBean.getEvents().size()}:
        <table class="table">
            <tr>
                <th><f:message key="Number"/></th>
                <th><f:message key="Id"/></th>
                <th><f:message key="event.name"/></th>               
                <th><f:message key="event.dateOfEvent"/></th>
                <th><f:message key="Sport"/></th>   
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.getEvents()}" var="eventDTO" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${eventDTO.eventId}</td>                   
                    <td><c:out value="${eventDTO.name}"/></td> 
                    <td><c:out value="${actionBean.sdfSource.format(eventDTO.dateOfEvent)}"/></td>
                    <td><c:out value="${eventDTO.sport.name}"/></td>
                    
                    <td><s:link beanclass="com.pa165.sportEventpresentation.EventActionBean" event="edit"><s:param name="eventDTO.eventId" value="${eventDTO.eventId}"/><f:message key="Edit"/></s:link></td>
                    <td><s:link beanclass="com.pa165.sportEventpresentation.EventActionBean" event="delete"><s:param name="eventDTO.eventId" value="${eventDTO.eventId}"/><f:message key="Delete"/></s:link> </td>
                    
                    <td><s:link beanclass="com.pa165.sportEventpresentation.EventActionBean" event="showSportsmans"><s:param name="eventDTO.eventId" value="${eventDTO.eventId}"/><f:message key="ShowSportsmans"/></s:link> </td>
                </tr>
            </c:forEach>
        </table>
       
         <s:link beanclass="com.pa165.sportEventpresentation.EventActionBean" event="add"><f:message key="Add"/></s:link>
       </s:layout-component>
</s:layout-render>
