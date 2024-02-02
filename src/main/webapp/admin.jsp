<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.tinkudhaba.backend.DTO.UserDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
	background-color:#333;
}

.admin-panel {
	display: flex;
	flex-direction: column;
	min-height: 100vh;
}

header {
	background-color: #333;
	color: #fff;
	padding: 20px;
	text-align: center;
}

nav {
	background-color: #000;
	padding: 10px;
}

nav ul {
	list-style: none;
	margin: 0;
	padding: 0;
	display: flex;
}

nav li {
	margin-right: 20px;
}

nav a {
	text-decoration: none;
	color: #fff;
	font-weight: bold;
}

.content {
	flex-grow: 1;
	padding: 20px;
}

.container {
	margin: 20px;
}

.search-container {
	background-color: #1f232b;
	padding: 20px;
	border-radius: 8px;
	text-align: center;
}

.search-box {
	width: 20%;
	padding: 10px;
	box-sizing: border-box;
	border: none;
	margin-bottom: 10px;
	border-bottom: 2px solid #4caf50;
	background-color: transparent;
	color: #fff;
	transition: border-bottom 0.3s ease, background-color 0.3s ease;
}

.search-box:focus {
	outline: none;
	border-bottom: 2px solid #45a049;
	background-color: rgba(255, 255, 255, 0.1);
}

.search-buttons {
	margin-bottom: 20px;
}

.search-buttons button {
	width: 150px;
	padding: 10px;
	box-sizing: border-box;
	border: none;
	border-radius: 4px;
	background-color: #4caf50;
	color: #fff;
	cursor: pointer;
	transition: background-color 0.3s ease;
	margin: 0 10px;
	font-size: 16px;
}

.search-buttons button:hover {
	background-color: #45a049;
}

.table-container {
	background-color:#65d492;
	padding: 2.5px;
	border-radius: 8px;
	overflow-x: auto;
	margin: 20px
}

table {
	width: 100%;
	border-collapse: collapse;
	margin: 20px;
}

th, td {
	border: 1px solid #4caf50;
	padding: 12px;
	text-align: left;
	font-size: 16px;
}

th {
	background-color: #4caf50;
	color: #fff;
}

.actions {
	display: flex;
	justify-content: space-around;
}

.delete {
	width: 70px;
	padding: 10px;
	box-sizing: border-box;
	border: none;
	border-radius: 4px;
	background-color: #4caf50;
	color: #fff;
	cursor: pointer;
	transition: background-color 0.3s ease;
	font-size: 14px;
}

.edit {
	width: 70px;
	padding: 10px;
	box-sizing: border-box;
	border: none;
	border-radius: 4px;
	background-color: #fff;
	color: #4caf50;
	cursor: pointer;
	transition: background-color 0.3s ease;
	font-size: 14px;
}

/* .delete:hover { */
/* 	background-color: #45a049; */
/* } */
.edit:hover, .delete:hover {
	background-color: #c5c5c5;
}

.edit-modal {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.7);
	justify-content: center;
	align-items: center;
	z-index: 1;
}

.modal-content {
	background-color: #1f232b;
	padding: 20px;
	border-radius: 8px;
	text-align: center;
}

.modal-content input {
	width: 100%;
	padding: 10px;
	box-sizing: border-box;
	border: none;
	margin-bottom: 10px;
	border-bottom: 2px solid #4caf50;
	background-color: transparent;
	color: #fff;
	transition: border-bottom 0.3s ease, background-color 0.3s ease;
	font-size: 16px;
}

.modal-content input:focus {
	outline: none;
	border-bottom: 2px solid #45a049;
	background-color: rgba(255, 255, 255, 0.1);
}

.modal-buttons {
	display: flex;
	justify-content: space-around;
	margin-top: 20px;
}

.modal-buttons button {
	width: 45%;
	padding: 10px;
	box-sizing: border-box;
	border: none;
	border-radius: 4px;
	background-color: #4caf50;
	color: #fff;
	cursor: pointer;
	transition: background-color 0.3s ease;
	font-size: 16px;
}

.modal-buttons button:hover {
	background-color: #45a049;
}

.close-modal {
	cursor: pointer;
	color: #ccc;
	font-size: 24px;
	position: absolute;
	top: 10px;
	right: 10px;
}

input[class="search-box"]:invalid {
	border: 2px solid red;
}

.update-form {
	max-width: 400px;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

footer {
	background-color: #000;
	color: #fff;
	text-align: center;
	padding: 10px;
}
</style>
<title>Admin Panel</title>
</head>
<body>
	<%
	if (request.getAttribute("loginUserDTO") != null) {
		UserDTO userDTO = (UserDTO) request.getAttribute("loginUserDTO");
		if (request.getAttribute("loginUserDTO") == null) {
			response.sendRedirect("login.jsp");
		}
	%>
	<div class="admin-panel">
		<header>
			<h1>
				Welcome Admin
				<%=userDTO.getFirstName()%></h1>
		</header>
		<%
		}
		%>
		<nav>
			<ul>
				<li><a href="#">Dashboard</a></li>
				<li><a href="#">Users</a></li>
				<li><a href="#">Settings</a></li>
				<li><a href="#">Logs</a></li>
			</ul>
		</nav>
		<div class="container">
			<form action="users">
				<input type="hidden" name="task" value="findByMobile"> <input
					class="search-box" name="contact" placeholder="Enter Mobile Number"
					required
					oninvalid="setCustomValidity('Please enter a valid number.')">
				<button>Find User</button>
			</form>
			<form action="users">
				<input type="hidden" name="task" value="findAllbyAdmin">
				<button>Find All Users</button>
			</form>
		</div>
		<div class="table-container">
			<table>
				<thead>
					<tr>
						<th>User ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>D.O.B</th>
						<th>Mobile No.</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (request.getAttribute("userDTO") != null) {
						UserDTO userDTO = (UserDTO) request.getAttribute("userDTO");
					%>
					<tr>
						<td><%=userDTO.getId()%></td>
						<td><%=userDTO.getFirstName()%></td>
						<td><%=userDTO.getEmail()%></td>
						<td><%=userDTO.getDob()%></td>
						<td><%=userDTO.getContact()%></td>
						<td class="actions">
							<form action="users">
								<input type="hidden" name="task" value="findById"> <input
									type="hidden" name="userId" value="<%=userDTO.getId()%>">
								<button class="edit">Edit</button>
							</form>
							<form action="users">
								<input type="hidden" name="task" value="deleteById"> <input
									type="hidden" name="userId" value="<%=userDTO.getId()%>">
								<button class="delete">Delete</button>
							</form>
						</td>
					</tr>
					<%
					}
					%>
					<%
					if (request.getAttribute("userDTOs") != null) {
						List<UserDTO> userDTOs = (List) request.getAttribute("userDTOs");
						for (UserDTO userDTO : userDTOs) {
					%>
					<tr>
						<td><%=userDTO.getId()%></td>
						<td><%=userDTO.getFirstName()%></td>
						<td><%=userDTO.getEmail()%></td>
						<td><%=userDTO.getDob()%></td>
						<td><%=userDTO.getContact()%></td>
						<td class="actions">
							<form action="users">
								<input type="hidden" name="task" value="findById"> <input
									type="hidden" name="userId" value="<%=userDTO.getId()%>">
								<button class="edit">Edit</button>
							</form>

							<form action="users">
								<input type="hidden" name="task" value="deleteById"> <input
									type="hidden" name="userId" value="<%=userDTO.getId()%>">
								<button class="delete">Delete</button>
							</form>

						</td>
					</tr>
					<%
					}
					}
					%>
				</tbody>
			</table>
		</div>
		<div id="editModal" class="edit-modal">
			<div class="modal-content">
				<span class="close-modal">&times;</span>
				<h2>Edit Profile</h2>
				<div class="modal-content">
					<input type="text" placeholder="New Name"> <input
						type="email" placeholder="New Email"> <input type="tel"
						placeholder="New Mobile">
					<div class="modal-buttons">
						<button>Update</button>
						<button>Close</button>
					</div>
				</div>
			</div>
		</div>
		<%
		if (request.getAttribute("userDTOForEdit") != null) {
			UserDTO userDTOForEdit = (UserDTO) request.getAttribute("userDTOForEdit");
		%>
		<div>
			<form class="update-form" action="users" method="post">
				<input type="hidden" name="userId"
					value="<%=userDTOForEdit.getId()%>"> <input type="hidden"
					name="task" value="updateById" />
				<div class="update-container">
					<h2>Edit Profile</h2>
					<div class="form-group">
						<input type="text" name="firstName"
							value="<%=userDTOForEdit.getFirstName()%>"
							placeholder="First Name" required>
					</div>
					<div class="form-group">
						<input type="text" name="lastName"
							value="<%=userDTOForEdit.getLastName()%>" placeholder="Last Name"
							required>
					</div>
					<div class="form-group">
						<input type="tel" name="contact"
							value="<%=userDTOForEdit.getContact()%>"
							placeholder="Mobile Number" required>
					</div>
					<div class="form-group">
						<input type="email" name="email"
							value="<%=userDTOForEdit.getEmail()%>" placeholder="Email"
							required>
					</div>
					<div class="form-group">
						<input type="date" name="dob" value="<%=userDTOForEdit.getDob()%>"
							placeholder="Date Of Birth" required>
					</div>
					<div class="form-group">
						<button>Update</button>
					</div>
				</div>
			</form>
		</div>
		<%
		}
		%>
		<footer>
			<p>&copy; 2024 Admin Panel</p>
		</footer>
	</div>
</body>
</html>
