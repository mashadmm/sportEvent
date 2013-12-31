<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:errors/>
<table>    
    <tr>
        <th><s:label for="b1" name="Name"/></th>
        <td><s:text id="b1" name="sportDTO.name"/></td>
    </tr>
     <tr>
        <th><s:label for="b2" name="Description"/></th>
        <td><s:text id="b2" name="sportDTO.description"/></td>
    </tr>

</table>
