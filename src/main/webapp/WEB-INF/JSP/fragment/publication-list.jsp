<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<c:forEach var="p" items="${publications }">
<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 col-xlg-2">
		<div id="publication${p.id }" class="panel panel-default publication">
		  <div class="panel-body">
		    <div class="thumbnail">
		    	<img src="${p.imageLink }" alt="${p.name }" />
		    	<div class="desc">
		    		<div class="cell">
		    			<p>
							<span class="title">Details</span> <span class="description">${p.description }</span>
						</p>
		    		</div>
		    	</div>
		    </div>
		    <h4 class="name">${p.name }</h4>
		    <div class="conditions hidden">${p.conditions }</div>
		    <div class="price hidden">${p.price }</div>		
			<div class="category"><mytags:message key="publications.category"/> <span class="category-name">${p.category }</span></div>	
			<c:choose>
				<c:when test="${CURRENT_ACCOUNT.role=='reader' or CURRENT_ACCOUNT==null }">
					<a class="btn btn-primary btn-block buy-btn" data-id-publication="${p.id }"><mytags:message key="publications.subscribe"/></a>
				</c:when>
				<c:when test="${CURRENT_ACCOUNT.role=='admin' }">
					<div class="text-center">
						<div class="btn-group" role="group" aria-label="...">
						  <a href="/subpub/admin/editPublication?id=${p.id }" type="button" class="btn btn-warning">Edit</a>
						  <a href="/subpub/admin/deletePublication?id=${p.id }" onclick="return confirm('<mytags:message key="confirmOnDeleteMessage"/>')" type="button" class="btn btn-danger">Delete</a>
						</div>
					</div>
				</c:when>
			</c:choose>	
		  </div>
		</div>
	</div>
</c:forEach>