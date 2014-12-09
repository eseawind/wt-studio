<%@ tag pageEncoding="utf-8" isELIgnored = "false"%>
<%@ taglib uri="/WEB-INF/tld/more-lib.tld" prefix="more" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag body-content="scriptless"%>
<%@ attribute name="action"%>
<%@ attribute name="name"%>
<%@ attribute name="cssClass"%>
<%@ attribute name="style"%>
<%@ attribute name="value"%>
<%@ attribute name="groupId"%>
<%@ attribute name="deptId"%>
<more:ActionTag process="${action}" result="selectData">
  <more:ActionParamTag key="groupId" value="${groupId}"/>
  <more:ActionParamTag key="deptId" value="${deptId}"/>
</more:ActionTag>
<select name="${name}" class="${cssClass}" style="${style}">
<c:forEach var="kv" items="${selectData}">
  <c:if test="${kv.value eq value}"><option value="${kv.value}" selected="selected">${kv.key}</option></c:if>
  <c:if test="${kv.value ne value}"><option value="${kv.value}">${kv.key}</option></c:if>
</c:forEach>
</select>