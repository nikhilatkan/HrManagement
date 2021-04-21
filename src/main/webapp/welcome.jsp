<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
        .inner{
            
            display:flex;
            justify-content:flex-end;
            padding:20px;
        }
        table{
            width:100%;
        }
        th,td,table{
            border: 1px solid black;
            text-align:center;
            border-collapse:collapse;
            padding: 10px;
        }
        header{
        margin-right:20px;
        display:flex;
        flex-direction: row;
        justify-content: flex-end;
        }
        
    </style>
</head>
<body>

<div >
        <header>
        <form action="logout" method="post">
            <h3>Welcome ${userName } <input type="submit" value="Logout"></h3>
            </form>       
        </header>
        <fieldset>
            <legend style="color: blue;">Employee Listings</legend>
            <div>
                <div class="inner">
						<form >
                        <input type="submit" value="Upload Employee Details" formaction="add"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="submit" value="Download Employee Details" formaction="download"/>
                        </form>
                    
                </div>
                <c:if test="${list.size()!=0 }">
                <table>
                    <thead style="background-color: gray;">
                        <th>Employee Code</th>
                        <th>Employee Name</th>
                        <th>Location</th>
                        <th>Email</th>
                        <th>DOB</th>
                        <th>Action</th>
                    </thead>
                    <c:forEach var="item" items="${list }">
                    <tr>
                    	<td>${item.getEmployeeId()}</td>
                    	<td>${item.getEmployeeName()}</td>
                    	<td>${item.getLocation()}</td>
                    	<td>${item.getEmail()}</td>
                    	<td>${item.getEmployeeDOB()}</td>
                    	<td><form action="edit">
                                <input type="submit" value="edit" />
                                <input type="hidden" value="${item.getEmployeeId() }" name="employeeId">
                            </form></td>
                    </tr>
                    	
                    </c:forEach>
  
                </table>
                </c:if>
   				<c:if test="${list.size()==0 }">
   				<script type="text/javascript">alert("server not found");</script>
   					
   					
   				</c:if>
            </div>
        </fieldset>
        <footer>

        </footer>
    </div>
</body>
</html>