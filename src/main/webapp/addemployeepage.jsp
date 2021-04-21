<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
        .form {
            text-align: center;
        }

        header {
            text-align: right;
            margin-right: 20px;
        }
    </style>
</head>
<body>
<header>
<form action="logout" method="post">
        <h3> Welcome ${userName } </h3><input type="submit" value="Logout">
        </form>
    </header>
    <fieldset>
        <legend>Add Employee Details</legend>
        <div class="form">
		<form action="save" method="post">
            <div style="padding:10px;">
                <div style="margin-bottom:20px;">
                    <label style="margin-right:20px;padding:10px;">Employee Code</label>
                    <input type="text" name="employeeId" />
                </div>
                <div style="margin-bottom:20px;">
                    <label style="margin-right:20px;padding:10px">Employee Name:</label>
                    <input type="text" name="employeeName" />
                </div>
                <div style="margin-bottom:20px;">
                    <label style="margin-right:73px;padding:10px">Location</label>
                    <input type="text" name="location" />
                </div>
                <div style="margin-bottom:20px;">
                    <label style="margin-right:88px;padding:10px">Email:</label>
                    <input type="email" name="email" />
                </div>
                <div style="margin-bottom:20px;">
                    <label style="margin-right:50px;padding:10px">Date of Birth:</label>
                    <input type="date" name="dateofbirth" />
                </div>
                <br />
                <input type="submit" value="Save" formaction="save" />
                <input type="reset" value="Cancel" onclick="window.location='welcome.jsp'" />
                

            </div>
            </form>
        </div>
    </fieldset>
</body>
</html>