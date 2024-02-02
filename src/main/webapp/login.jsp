<%@page import="com.tinkudhaba.backend.DTO.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<style>
        body {
            font-family: Arial, sans-serif;
            background-color:#433d3d;
            margin:260px;
            padding: 2%;
            display: compact;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .login-form {        
      
            max-width: inherit;
            padding: 40px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #e0d757;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input,
        select {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .back-to-login {
            text-align: center;
            margin-top: 20px;
        }

        .back-to-login a {
            text-decoration: none;
            color: #333;
            font-weight: bold;
        }
    </style>
</head>
<body>
  <div class="login-container">
    <form class="login-form" action="users" method="post">
    <input type="hidden" name="task" value="login">
      <h2>Login</h2>
      <div class="input-group">
        <input type="text" id="username" name="username" placeholder="Username" required>
      </div>
      <div class="input-group">
        <input type="password" id="password" name="password" placeholder="Password" required>
      </div>
      <button type="submit">Login</button>
      
      <P>Don't have account? Click here to <a href="signup.jsp">Signup</a></P>
    </form>
  </div>
</body>
</html>