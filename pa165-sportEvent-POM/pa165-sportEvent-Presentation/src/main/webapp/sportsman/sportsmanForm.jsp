<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:errors/>

<table>    
    <tr align="left">
        <th><f:message key="sportsman.name"/></th>
        <td><s:text id="b1" name="sportsmanDTO.name"/></td>
    </tr>
    <tr align="left">
        <th><f:message key="sportsman.lastname"/></th>
        <td><s:text id="b2" name="sportsmanDTO.lastname"/></td>
    </tr> 
     <tr align="left">
        <th><f:message key="sportsman.dateOfBirth"/></th>
        <td><s:text id="b3" name="sportsmanDTO.dateOfBirth" formatPattern="yyyy-MM-dd"/><it>(yyyy-MM-dd)</it></td>
     </tr>
  
 </table>
