<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div id="subscribePublicationPopup" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Subscribe publication</h4>
      </div>
      <div class="modal-body row">
		<div class="col-xs-12 col-sm-6">
			<div class="thumbnail">
				<img class="publication-image" alt="publication-img" src="data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs=">
			</div>
		</div>
		<div class="col-xs-12 col-sm-6">
			<h4 class="name text-center">Name</h4>
			<div class="list-group">
				<span class="list-group-item hidden-xs"> <small>Category:</small> <span class="category">?</span></span> 
                <span class="list-group-item"> <small>Price per month:</small> <span class="price">0</span></span> 
                <span class="list-group-item"> <small>Number of monthes:</small> <input type="number" class="count" value="1" min="1" max="12"></span> 
                <span class="list-group-item"> <small>Cost:</small> <span class="cost">0</span></span>
            </div>
		</div>
		<div class="col-xs-12 col-sm-12">
			<h4>Description</h4>
			<p class="description"></p>
			<h4>Subscribe conditions</h4>
			<p class="conditions">
				Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quis cumque non pariatur rem quisquam soluta ipsam excepturi ducimus deleniti at eos obcaecati error recusandae molestias expedita atque ea. Fugiat obcaecati nulla ad veniam aliquid omnis dolor! Dicta alias saepe perferendis optio numquam placeat id dolorem a in blanditiis quis eius mollitia beatae itaque quam officiis magni nobis molestias dolore illum. Corporis eum magni eligendi doloremque earum beatae dignissimos voluptatibus. Consequatur culpa hic labore voluptatibus unde accusantium officiis. Accusamus quos fuga modi facilis sed ab asperiores quidem tempora atque explicabo esse impedit voluptas error odio dolor provident autem consequatur beatae nemo.
			</p>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button id="subscribe-btn" type="button" class="btn btn-primary">Subscribe</button>
      </div>
    </div>
  </div>
</div>