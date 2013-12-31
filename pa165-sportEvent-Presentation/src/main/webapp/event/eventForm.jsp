<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:errors/>


<table>
   <tr align="left">
        <th><f:message key="event.name"/></th>
        <td><s:text id="b1" name="eventDTO.name"/></td>
    </tr>
    
     <tr align="left">
        <th><f:message key="event.dateOfEvent"/></th>
        <td><s:text id="b2" name="eventDTO.dateOfEvent" formatPattern="yyyy-MM-dd"/><it>(yyyy-MM-dd)</it></td>
     </tr>
     <tr align="left">
        <th><s:label for="b3" name="event.sport.sportId"/></th>
        <td>
             <s:select id="b3" name="eventDTO.sport.sportId">
                 
                <c:forEach var="item" items="${actionBean.getAllSports()}">
                    <option value="${item.sportId}">${item.name}</option>
                </c:forEach>
            </s:select>
        </td>
    </tr> 
</table>
        

            