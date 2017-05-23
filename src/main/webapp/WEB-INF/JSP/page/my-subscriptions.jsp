<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h4 class="text-center">My subscriptions</h4>
<hr />
<table id="subscriptionList" data-page-count="${pageCount}" data-page-number="1" class="table table-bordered">
	<thead>
		<tr>
			<th>Subscription id</th>
			<th>Subscription name</th>
			<th>Date</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty subscriptions }">
			<tr>
				<td colspan="3" class="text-center">Subscriptions not found</td>
			</tr>
		</c:if>
		<jsp:include page="../fragment/subscription-list.jsp" />
	</tbody>
</table>
<c:if test="${pageCount > 1 }">
	<div class="text-center hidden-print">
		<a class="btn btn-success"	id="loadMoreSubscriptions" href="#">Load more subscriptions</a>
	</div>
</c:if>