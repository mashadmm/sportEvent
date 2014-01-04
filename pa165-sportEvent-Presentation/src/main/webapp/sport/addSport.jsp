<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/menu.jsp" nadpis="Sport">
    <s:layout-component name="telo">
       
            <s:useActionBean beanclass="com.pa165.sportEventpresentation.SportActionBean" var="actionBean"/>

            <s:form beanclass="com.pa165.sportEventpresentation.SportActionBean">          
                <fieldset><legend><f:message key="sport.add"/></legend>
                    <%@include file="sportForm.jsp"%>
                    <br>
                    <s:submit name="saveAdd" class="btn" ><f:message key="Add" /></s:submit>
                    
                    </fieldset>
            </s:form>
        
    </s:layout-component>
</s:layout-render>
