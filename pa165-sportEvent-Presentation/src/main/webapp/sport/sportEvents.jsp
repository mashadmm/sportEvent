<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
 
<s:layout-render name="/menu.jsp" nadpis="SportEvents">
    <s:layout-component name="telo">
    <s:useActionBean beanclass="com.pa165.sportEventpresentation.SportActionBean" var="actionBean"/>    
    
    <f:message key="Sport"/>: <b>${sportDTO.name} </b><br/>
   <f:message key="Total"/> = ${actionBean.getEvents().size()}:
        <table class="table">
            <tr>
                <th><f:message key="Number"/></th>
                <th><f:message key="Id"/></th>
                <th><f:message key="event.name"/></th>               
                <th><f:message key="event.dateOfEvent"/></th>
            </tr>
            <c:forEach items="${actionBean.getEvents()}" var="eventDTO" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${eventDTO.eventId}</td>                   
                    <td><c:out value="${eventDTO.name}"/></td> 
                    <td><c:out value="${actionBean.sdfSource.format(eventDTO.dateOfEvent)}"/></td>
                </tr>
            </c:forEach>
            
        </table> 
                
     <s:link beanclass="com.pa165.sportEventpresentation.SportActionBean" event="all"><f:message key="Back"/></s:link> 
    </s:layout-component>
</s:layout-render>