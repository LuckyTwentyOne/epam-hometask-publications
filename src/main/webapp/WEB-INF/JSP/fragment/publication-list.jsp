<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:forEach var="p" items="${publications }">
<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 col-xlg-2">
		<!-- PUBLICATION DATA -->
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
			<div class="category">Category: <span class="category-name">${p.category }</span></div>		
			<a class="btn btn-primary btn-block buy-btn" data-id-publication="${p.id }">Subscribe</a>
		  </div>
		</div>
		<!-- /PUBLICATION DATA -->
	</div>
</c:forEach>