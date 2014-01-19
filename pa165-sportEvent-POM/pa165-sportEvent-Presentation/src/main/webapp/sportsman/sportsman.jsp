<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
 
<s:layout-render name="/menu.jsp" nadpis="Sportsman">
    <s:layout-component name="telo">
    <s:useActionBean beanclass="com.pa165.sportEventpresentation.SportsmanActionBean" var="actionBean"/>
   <f:message key="Total"/> = ${actionBean.getSportsmans().size()}:
        <table class="table">
            <tr>
                <th><f:message key="Number"/></th>
                <th><f:message key="Id"/></th>
                <th><f:message key="sportsman.name"/></th>
                <th><f:message key="sportsman.lastname"/></th>
                <th><f:message key="sportsman.dateOfBirth"/></th>
                <th><f:message key="sportsman.login"/></th>
	        <th><f:message key="sportsman.password"/></th>
                <th><f:message key="sportsman.userrole"/></th>
                <th></th>
                <th></th>
                <th></th>
                
            </tr>
            <c:forEach items="${actionBean.getSportsmans()}" var="sportsmanDTO" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${sportsmanDTO.sportsmanId}</td>                   
                    <td><c:out value="${sportsmanDTO.name}"/></td> 
                    <td><c:out value="${sportsmanDTO.lastname}"/></td>
                    <td><c:out value="${actionBean.sdfSource.format(sportsmanDTO.dateOfBirth)}"/></td> 
                    <td><c:out value="${sportsmanDTO.userName}"/></td>
                    <td><c:out value="${sportsmanDTO.pwd}"/></td>
                    <td><c:if test="${sportsmanDTO.userRole=='ROLE_USER'}" >
                            <f:message key="User"/>
                        </c:if>
                        <c:if test="${sportsmanDTO.userRole=='ROLE_ADMIN'}" >
                             <f:message key="Admin"/>
                        </c:if>
                    </td>
              
                    <td>
                        <s:link beanclass="com.pa165.sportEventpresentation.SportsmanActionBean" event="delete"><s:param name="sportsmanDTO.sportsmanId" value="${sportsmanDTO.sportsmanId}"/><f:message key="Delete"/></s:link> 
                    </td>
                    <td>
                        <s:link beanclass="com.pa165.sportEventpresentation.SportsmanActionBean" event="edit"><s:param name="sportsmanDTO.sportsmanId" value="${sportsmanDTO.sportsmanId}"/><f:message key="Edit"/></s:link> 
                    </td>
                    <c:if test="${studentDTO.userRole=='ROLE_USER'}" >
                        <td>
                            <s:link beanclass="com.pa165.sportEventpresentation.SportsmanActionBean" event="showEvents"><s:param name="sportsmanDTO.sportsmanId" value="${sportsmanDTO.sportsmanId}"/><f:message key="ManageEvents"/></s:link>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
       <s:link beanclass="com.pa165.sportEventpresentation.SportsmanActionBean" event="add"><f:message key="Add"/></s:link> 
    </s:layout-component>
</s:layout-render>
