<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
        .form{
            text-align:center;
        }
        header{
            text-align:right;
            margin-right:20px;
        }
    </style>
</head>
<body>
<header>
<form action="logout" method="post">
        <h3> Welcome ${userName }</h3><input type="submit" value="Logout">
        </form>
    </header>
    <fieldset>
        <legend>Edit Employee Details</legend>
        <div class="form">
	<form action="editUser" method="post">
            <div style="padding:10px;">
                <div style="margin-bottom:20px;">
                    <label style="margin-right:20px;padding:10px;">Employee Code</label>
                    <label>${employeeId }</label>
                    <input type="hidden" value="${employeeId }" name="employeeId">
                </div>
                <div style="margin-bottom:20px;">
                    <label style="margin-right:20px;padding:10px">Employee Name:</label>
                    <input type="text" name="employeeName" required="required" maxlength="100" />
                </div>
                <div style="margin-bottom:20px;">
                    <label style="margin-right:73px;padding:10px">Location</label>
                    <input type="text" name="location" maxlength="500"/>
                </div>
                <div style="margin-bottom:20px;">
                    <label style="margin-right:88px;padding:10px">Email:</label>
                    <input type="email" name="email" />
                </div>
                <div style="margin-bottom:20px;">
                    <label style="margin-right:50px;padding:10px">Date of Birth:</label>
                    <input type="date" name="date" />
                </div>
                <br />
                <input type="submit" value="Save" formaction="editUser" />
                <input type="reset" value="Cancel" onclick="window.location='welcome.jsp'" />
</form>
            </div>
        </div>
    </fieldset>
</body>
</html>