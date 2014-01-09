<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-definition>
    <html>
        <head>
          <!--title><f:message key="${nadpis}"/></title-->
            <title><f:message key="Nadpis"/></title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap-responsive.css" />
            <s:layout-component name="hlavicka"/>
        </head>
        <body> 
            <div class="navbar navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>
                        <a class="brand" href="#">PA165 Sport Events</a>
                        <div class="nav-collapse">
                            <ul class="nav">
                                <li><s:link href="/index.jsp"><f:message key="Home"/></s:link></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>    
                <div class="container-fluid">
                    <div class="row-fluid">
                        <div class="span3">
                            <div class="well sidebar-nav">
                                <ul class="nav nav-list">
                                    <li><s:link href="/sportsman/sportsman.jsp"><f:message key="Sportsman"/></s:link></li>
                                <li><s:link href="/event/event.jsp"><f:message key="events"/></s:link></li>
                                <li><s:link href="/sport/sport.jsp"><f:message key="Sports"/></s:link></li>
                                </ul>
                            </div>
                        </div>
                        <div class="span9">
                            <h1><f:message key="${nadpis}"/></h1>
                        <div class="hero-unit">                      
                            <s:layout-component name="telo"/>
                        </div>
                    </div>
                </div>
            </div>
            <script type="text/javascript" src="${pageContext.request.contextPath}/jquery.min.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap.js"></script>

        </body>
    </html>
</s:layout-definition>