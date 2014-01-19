<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/menu.jsp" nadpis="Sport">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="com.pa165.sportEventpresentation.SportActionBean" var="actionBean"/>
        <f:message key="Total"/> = ${actionBean.getSports().size()}:
        <table class="table">
            <tr>
                <th><f:message key="Number"/></th>
                <th><f:message key="Id"/></th>
                <th><f:message key="Name"/></th>               
                <th><f:message key="Description"/></th>   
                <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}" >
                    <th></th>
                    <th></th>
                </c:if>
                <th></th>

            </tr>
            <c:forEach items="${actionBean.getSports()}" var="sportDTO" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${sportDTO.sportId}</td>                   
                    <td><c:out value="${sportDTO.name}"/></td> 
                    <td><c:out value="${sportDTO.description}"/></td> 
                    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}" >
                        <td><s:link beanclass="com.pa165.sportEventpresentation.SportActionBean" event="delete"><s:param name="sportDTO.sportId" value="${sportDTO.sportId}"/><f:message key="Delete"/></s:link> </td>
                       <td><s:link beanclass="com.pa165.sportEventpresentation.SportActionBean" event="edit"><s:param name="sportDTO.sportId" value="${sportDTO.sportId}"/><f:message key="Edit"/></s:link> </td>
                    </c:if>
                    <td><s:link beanclass="com.pa165.sportEventpresentation.SportActionBean" event="showEvents"><s:param name="sportDTO.sportId" value="${sportDTO.sportId}"/><f:message key="ShowEvents"/></s:link> </td>

                </tr>
            </c:forEach>
        </table>
                
                <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}" >
                    <s:link beanclass="com.pa165.sportEventpresentation.SportActionBean" event="add"><f:message key="Add"/></s:link> 
                </c:if>
     </s:layout-component>
</s:layout-render>
