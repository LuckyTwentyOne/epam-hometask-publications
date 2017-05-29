<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:forEach var="subscription" items="${subscriptions }">
	<tr>
		<td><a href="/subpub/subscription?id=${subscription.id }">Subscription # ${subscription.id }</a></td>
		<td><fmt:formatDate value="${subscription.created }" pattern="dd-MM-yyyy HH:mm"/></td>
		<td><fmt:formatDate value="${subscription.expirationDate }" pattern="dd-MM-yyyy HH:mm"/></td>
	</tr>
</c:forEach>