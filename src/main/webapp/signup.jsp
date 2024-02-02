<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin-left:60px;
            padding: 2%;
            display: compact;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .signup-form {
            max-width: 400px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
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

    <div class="signup-form">
        <h2>Sign Up</h2>
        <form action="users" method="post"><input type="hidden" name="task" value="signup">
    
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required>

            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" >

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="contactNumber">Contact Number:</label>
            <input type="text" id="contact" name="contact" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" required>

<!--             <label for="city">Select City:</label> -->
<!--             <select id="city" name="city"> -->
<!--             <option value="" disabled selected>Select a City</option> -->
<!--                 <option value="indore">Indore</option> -->
<!--                 <option value="bhopal">Bhopal</option> -->
<!--                 <option value="delhi">Delhi</option> -->
<!--                 Add more city options as needed -->
<!--             </select> -->

            <input type="submit" value="Sign Up">

        </form>

        <div class="back-to-login">
            <a href="login.jsp">Back to Login</a>
        </div>
    </div>

</body>
</html>
