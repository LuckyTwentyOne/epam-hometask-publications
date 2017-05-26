<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="subscription">
	<c:if test="${CURRENT_MESSAGE != null }">
		<div class="alert alert-success hidden-print" role="alert">${CURRENT_MESSAGE }</div>
	</c:if>
	<h4 class="text-center">Subcripton # ${subscription.id }</h4>
	<hr />
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Publication</th>
				<th>Price</th>
				<th>Expiration date</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="text-center">
					<img src="/subpub/media/1358423120_gq.jpg" alt="publication-name" /><br>GO
				</td>
				<td>10грн</td>
				<td>06.06.2017</td>
			</tr>
		</tbody>
	</table>
	<div class="row hidden-print">
		<div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
			<a href="/subpub/my-subscriptions" class="btn btn-warning btn-block">Go to My subcriptons</a>
		</div>
	</div>
</div>