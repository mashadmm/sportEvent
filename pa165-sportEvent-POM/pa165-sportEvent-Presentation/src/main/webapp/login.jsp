<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><f:message key="LoginPage"/></title>
    
</head>
<body onload='document.f.j_username.focus();' style="background-color:silver">
	<h3><f:message key="LoginHeader"/></h3>      

            <c:if test='${not empty sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}'>
		<font color='red'> 
			<f:message key="LoginFail"/>
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}                          
		</font>  
	</c:if> 
 
	<form name='f' action="<c:url value='/j_spring_security_check' />"
		method='POST'>
 
		<table>
			<tr>
				<td><f:message key="User"/>:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td><f:message key="Password"/>:</td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="<f:message key='Submit'/>" />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="reset" type="reset"
                                        value="<f:message key='reset'/>" />
				</td>
			</tr>
		</table>
 
	</form>
</body>
</html>