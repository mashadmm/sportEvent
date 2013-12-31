<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="b4" name="Event"/></th>
        <td><s:select id="b4" name="eventDTO.eventId">
            <s:options-collection collection="${actionBean.getAllEvents()}" value="eventId" label="name" />  
        </s:select></td>
    </tr>
</table>