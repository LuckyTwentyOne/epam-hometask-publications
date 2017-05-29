<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div id="sighinPopup" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Sigh in</h4>
			</div>
			<div class="modal-body row">
				<form method="post" action="/subpub/sign-in" class="form-horizontal col-md-10 col-md-offset-1">
					<div id="sign-in-email-input" class="form-group">
						<label class="col-sm-2 control-label">Email</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" name="email" id="sign-in-email" placeholder="Email">
						</div>
					</div>
					<div id="sign-in-password-input" class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" name="password" id="sign-in-password" placeholder="Password">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">Sign in</button>
							<a href="/subpub/registration" class="btn btn-default">Registration</a>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<form id ="facebook-sign-in-form" class="pull-left" action="/subpub/sign-in/facebook" method="post">
					<button type="submit" class="btn btn-primary"><i class="fa fa-facebook-official" aria-hidden="true"></i> Sign in</button>
				</form>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>