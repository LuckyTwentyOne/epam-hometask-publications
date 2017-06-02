<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h4 class="text-center">Accounts</h4>
<hr />
<table id="accountList" data-page-count="${pageCount}" data-page-number="1" class="table table-bordered">
	<thead>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Email</th>
			<th>Balance</th>
			<th>Role</th>
			<th>Total sum</th>
			<th>Is active?</th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="../fragment/account-list.jsp" />
	</tbody>
</table>
<c:if test="${pageCount > 1 }">
	<div class="text-center hidden-print">
		<a class="btn btn-success"	id="loadMoreAccounts" href="#">Load more subscriptions</a>
	</div>
</c:if>