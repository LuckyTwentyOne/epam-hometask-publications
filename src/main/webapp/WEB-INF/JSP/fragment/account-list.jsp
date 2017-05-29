<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:forEach var="account" items="${accounts }">
	<tr>
		<td>${account.id }</td>
		<td>${account.lastName }&nbsp;${account.firstName }</td>
		<td>${account.email }</td>
		<td>${account.money }$</td>
		<td>${account.role }</td>	
		<td><a href="/subpub/admin/active-status?idAccount=${account.id }">${account.active }</a></td>
	</tr>
</c:forEach>