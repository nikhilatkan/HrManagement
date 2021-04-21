<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style >
.outer {
    width: 50%;
    margin-left: 25%;
    margin-top: 15%;
}

.button{
    text-align:right;
    padding:10px;
}
</style>
</head>
<body>
<form action="login" method="post">
        <header>

        </header>
        <div class="outer">
            <fieldset>
                <legend>Login</legend>
                <div>
                    <div class="form-group row" style="padding:20px">
                        <label class="col-sm-2 col-form-label">Username</label>
                        
                            <input type="text" class="form-control" placeholder="Username" minlength="5" maxlength="50" name="username" required="required">
                       
                    </div>
                    <div class="form-group row" style="padding:20px">
                        <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
                        
                            <input type="password" class="form-control" id="inputPassword" minlength="5" maxlength="50" placeholder="Password" name="pass" required="required">
                       
                    </div>
                    <div class="button">
                        <input type="submit" value="Login" />
                    </div>
                </div>
            </fieldset>
        </div>
        <footer>

        </footer>
    </form>

</body>
</html>