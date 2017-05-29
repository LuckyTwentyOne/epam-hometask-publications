<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h4 class="text-center">My balance</h4>
<hr />
<div class="alert alert-info text-center" role="alert">You balance is ---> <strong>${CURRENT_ACCOUNT.money }</strong>$</div>
<h4 class="text-center">Please put vaucher code to top up your balance</h4>
<form class="form-inline text-center" method="post" action="/subpub/my-balance">
  <div class="form-group">
    <div class="input-group">
      <input type="number" class="form-control" name="code" id="exampleInputAmount" placeholder="Code">
    </div>
  </div>
  <button type="submit" class="btn btn-primary">Transfer</button>
</form>
<hr />
<c:if test="${UNSUCCESS_MESSAGE != null }">
	<div class="alert alert-danger hidden-print text-center" role="alert">${UNSUCCESS_MESSAGE }</div>
</c:if>