<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-definition>
	<html>
		<head>
			<title><f:message key="${nadpis}"/></title>
			<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
                        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/templatemo_style.css" />

			<s:layout-component name="hlavicka"/>
		</head>
		<body> 
			<header>
				<div class="container logo">
					<div class="row">
						<div class="row">
							<div class="col-md-12"><a rel="nofollow" rel="nofollow" target="_parent"></a></div>
							<br>
						</div>
					</div>
				</div>
					<nav class="navbar navbar-default" role="navigation"> 

						<div class="collapse navbar-collapse navbar-ex1-collapse">
							<ul class="nav navbar-nav">
								
                                                                <li><s:link href="/index.jsp"><f:message key="Home"/></s:link></li>
                                                                <c:choose>
                                                        	       <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}" >
                                                                            <li><s:link href="/sportsman/sportsman.jsp"><f:message key="Sportsman"/></s:link></li>
                                                                       </c:when>
                                                                     <c:otherwise>
                                                                            <li><s:link beanclass="com.pa165.sportEventpresentation.SportsmanActionBean" event="showEventsByLogin"><f:message key="MyEvents"/></s:link></li>
                                                                    </c:otherwise>
                                                                </c:choose>
								<li><s:link href="/event/event.jsp"><f:message key="events"/></s:link></li>
								<li style="padding-right: 350"><s:link href="/sport/sport.jsp"><f:message key="Sports"/></s:link></li>
                                                                <li><f:message key="LoggedIn"/><br/>
                                                                    <sec:authentication property="principal.username"/>
                                                                </li>
                                                                <li><a href="<c:url value="/j_spring_security_logout" />" ><f:message key="Logout"/></a></li>
							</ul>

						</div><!-- /.navbar-collapse -->
					</nav>
				</header>
				<div class="container" id="home">
					<br>
					<div id="outer">
						<div id="inner">
						<s:layout-component name="telo"/>
					</div>
				</div>
				<br> 
			</div> <!-- container -->
			<footer class="container">
				<div class="credit">
					<p id="templatemo_cr_bar">
						<br>
					</p>
				</div>
			</footer>

		</body>

	</html>
</s:layout-definition>