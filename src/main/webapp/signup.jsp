<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
.outer {
	width: 50%;
	background-color: aliceblue;
	margin-left: 25%;
	margin-top: 15%;
}

.form {
	padding: 10px;
}

header {
	color: darkslateblue;
	padding-left: 10px;
	font-weight: bold;
	background-color: lightblue;
}

footer {
	text-align: right;
	padding-right: 10px;
	margin-bottom: 10px;
	background-color: lightblue;
}
</style>
<title>Signup Page</title>
</head>
<body>
	<div class="outer">

		<form action="signup" method="post">
			<header> Signup </header>
			<div class="form">
				<div class="form-group row">
					<label class="col-sm-2 col-form-label">Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="name"
							placeholder="Name">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 col-form-label">Username</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="username" minlength="5" maxlength="50"
							placeholder="Username" required="required">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="password" minlength="5" maxlength="50"
							id="inputPassword" placeholder="Password" required="required">
					</div>
				</div>
			</div>
			<footer>
				<input type="submit" value="Signup" />
			</footer>
		</form>

	</div>


</body>
</html>