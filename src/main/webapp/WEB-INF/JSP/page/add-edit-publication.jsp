<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h4 class="text-center">
	<c:choose>
		<c:when test="${publication==null }">
			<mytags:message key="add.edit.publication.add" />
		</c:when>
		<c:otherwise>
			<mytags:message key="add.edit.publication.edit" />
		</c:otherwise>
	</c:choose>
</h4>
<hr />
<c:if test="${UNSUCCESS_MESSAGE != null }">
	<div class="alert alert-danger hidden-print" role="alert">${UNSUCCESS_MESSAGE }</div>
</c:if>
<form action="${publication==null? '/subpub/admin/addPublication' : '/subpub/admin/editPublication' }" class="form-horizontal col-md-10 col-md-offset-1" method="post">
	<input type="hidden" name="id" value="${publication.id }" >
	<div class="form-group">
		<label class="col-sm-2 control-label">Name</label>
		<div class="col-sm-10">
			<input class="form-control" name="name" value="${publication.name }" placeholder="Name">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Price</label>
		<div class="col-sm-10">
			<input class="form-control" name="price" placeholder="Price" value="${publication.price }">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Description</label>
		<div class="col-sm-10">
			<textarea class="form-control" rows="4" name="description">${publication.description }</textarea>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Conditions</label>
		<div class="col-sm-10">
			<textarea class="form-control" rows="4" name="conditions">${publication.conditions }</textarea>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Category</label>
		<div class="col-sm-10">
			<select class="form-control" name="category">
				<c:forEach items="${CATEGORY_LIST }" var="category">
					<option ${publication.category==category.name? 'selected' : '' } value="${category.id }">${category.name }</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<c:choose>
				<c:when test="${publication==null }">
					<button type="submit" class="btn btn-success">Add publication</button>
				</c:when>
				<c:otherwise>
					<button type="submit" class="btn btn-warning">Edit publication</button>
				</c:otherwise>
			</c:choose>		
			<a href="/subpub/publications" class="btn btn-default">Back to publications</a>
		</div>
	</div>
</form>