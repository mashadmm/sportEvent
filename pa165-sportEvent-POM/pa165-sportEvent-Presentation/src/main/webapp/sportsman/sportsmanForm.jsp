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
     <tr align="left">
        <th><f:message key="sportsman.login"/></th>
        <td><s:text id="b3" name="sportsmanDTO.userName"/></td>
     </tr>
     <tr align="left">
        <th><f:message key="sportsman.password"/></th>
        <td><s:password id="b4" name="sportsmanDTO.pwd"/></td>
     </tr>
     <tr>
         <th><f:message key="sportsman.userrole"/></th>
         <td><s:select id="b5" name="sportsmanDTO.userRole">
                 <s:option value="ROLE_USER"><f:message key="User"/></s:option>
                 <s:option value="ROLE_ADMIN"><f:message key="Admin"/></s:option>
             </s:select>
         </td>
     </tr>
  
 </table>
