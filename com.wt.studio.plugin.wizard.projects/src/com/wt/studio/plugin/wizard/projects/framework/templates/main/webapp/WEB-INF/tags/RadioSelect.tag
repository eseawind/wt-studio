<%@ tag pageEncoding="utf-8" isELIgnored = "false"%>
<%@ taglib uri="/WEB-INF/tld/more-lib.tld" prefix="more" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag body-content="scriptless"%>
<%@ attribute name="action"%><%@ attribute name="name"%><%@ attribute name="cssClass"%>
<%@ attribute name="style"%><%@ attribute name="value"%><%@ attribute name="readOnly"%>
<more:ActionTag process="${action}" result="selectData"/>
<c:forEach var="kv" items="${selectData}">
${kv.key}<input name="${name}" type="radio" value="${kv.value}" class="${cssClass}" style="${style}" <c:if test="${kv.value eq value}">checked="checked"</c:if> <c:if test="${readOnly eq 'true'}">disabled="disabled"</c:if>/>
</c:forEach>
<c:if test="${readOnly eq 'true'}"><input type="hidden"  name="${name}" value="${value}"/></c:if>