<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="visible-xs-block xs-option-container">
	<a class="pull-right" data-toggle="collapse" href="#subjects">Publication's subject <span class="caret"></span></a>
	<a data-toggle="collapse" href="#searchForm">Search publication <span class="caret"></span></a>
</div>
<!-- Search form -->
<form action="">
	<div id="searchForm" class="panel panel-default collapse">
	  <div class="panel-heading">Search publication</div>
	    <div class="panel-body">
		  <div class="input-group">
		      <input type="text" name="query" class="form-control" placeholder="Search query">
		      <span class="input-group-btn">
		        <button type="submit" class="btn btn-default">Go!</button>
		      </span>
		   </div>
		   <div class="sort-by">
			<a data-toggle="collapse" href="#sortOptions">Sort by <span class="caret"></span></a>
		   </div>
	  </div>
	  <div id="sortOptions" class="collapse">
	    	<div class="panel-body sortPanel">
	    		<div class="radio">
	    			<label><input type="radio" name="sortBy" value="date"> Date</label>
	    		</div>
	    		<div class="radio">
	    			<label><input type="radio" name="sortBy" value="name"> Name</label>
	    		</div>
	    		<div class="radio">
	    			<label><input type="radio" name="sortBy" value="price"> Price</label>
	    		</div>
	    	</div>
	  </div>
	</div>
</form>
<!-- /Search form -->
<!-- Subjects -->
<div id="subjects" class="panel panel-default collapse">
  <div class="panel-heading">Publication's subject</div>
    <div class="list-group">
	  <a href="#" class="list-group-item"><span class="badge">3</span>Cras justo odio</a>
	  <a href="#" class="list-group-item"><span class="badge">3</span>Dapibus ac facilisis in</a>
	  <a href="#" class="list-group-item"><span class="badge">7</span>Morbi leo risus</a>
	  <a href="#" class="list-group-item"><span class="badge">8</span>Porta ac consectetur ac</a>
	  <a href="#" class="list-group-item"><span class="badge">14</span>Vestibulum at eros</a>
  </div>
</div>
<!-- /Subjects -->