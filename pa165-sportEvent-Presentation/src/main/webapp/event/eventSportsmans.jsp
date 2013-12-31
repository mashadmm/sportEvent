<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
 
<s:layout-render name="/menu.jsp" nadpis="EventSportsmans">
    <s:layout-component name="telo">
    <s:useActionBean beanclass="com.pa165.sportEventpresentation.EventActionBean" var="actionBean"/>
    <f:message key="Event"/>: <b>${actionBean.eventDTO.name} </b> <b>${actionBean.eventDTO.dateOfEvent} </b><br/>
   <f:message key="Total"/> = ${actionBean.getSportsmans().size()}:
        <table class="table">
            <tr>
                <th><f:message key="Number"/></th>
                <th><f:message key="Id"/></th>
                <th><f:message key="sportsman.name"/></th>
                <th><f:message key="sportsman.lastname"/></th>
                <th><f:message key="grade.grade"/></th>
                <th></th>
                <th></th>
              
                
            </tr>
            
            
            <c:forEach items="${actionBean.getSportsmans()}" var="entry" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td><c:out value="${entry.key.sportsmanId}"/></td>                   
                    <td><c:out value="${entry.key.name}"/></td> 
                    <td><c:out value="${entry.key.lastname}"/></td> 
                    <td><c:out value="${entry.value.grade}"/></td> 
                   
                    <s:form beanclass="com.pa165.sportEventpresentation.EventActionBean" action="editGrade">
                        <s:hidden name="eventDTO.eventId" value="${eventDTO.eventId}"/>
                        <s:hidden name="sportsmanDTO.sportsmanId" value="${entry.key.sportsmanId}"/>
                        <td><s:text id="b1" name="gradeDTO.grade"/></td>
                        <td><s:submit name="editGrade"><f:message key="grade.changeGrade"/></s:submit></td>
                    </s:form>
                  
                 </tr>
            </c:forEach>
         
        </table>
        <s:link beanclass="com.pa165.sportEventpresentation.EventActionBean" event="all"><f:message key="Back"/></s:link> 
    </s:layout-component>
</s:layout-render>
