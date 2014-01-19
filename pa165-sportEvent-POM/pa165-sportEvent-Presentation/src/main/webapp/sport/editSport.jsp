<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/menu.jsp" nadpis="Sport">
    <s:layout-component name="telo">
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}" >
            <s:useActionBean beanclass="com.pa165.sportEventpresentation.SportActionBean" var="actionBean"/>

                  <s:form beanclass="com.pa165.sportEventpresentation.SportActionBean">
                      <s:hidden name="sportDTO.sportId"/>
                      <fieldset><legend><f:message key="sport.edit"/></legend>
                          <%@include file="/sport/sportForm.jsp"%>
                          <s:submit name="saveEdit" class="btn"><f:message key="Edit"/></s:submit>
                          </fieldset>
                  </s:form>
      </c:if>
      
       </s:layout-component>
</s:layout-render>