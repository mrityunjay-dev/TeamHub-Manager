package com.tinkudhaba.backend.MAIN;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.tinkudhaba.backend.DAO.UserDAO;
import com.tinkudhaba.backend.DTO.UserDTO;
import com.tinkudhaba.backend.SERVICE.UserService;
import com.tinkudhaba.backend.UTIL.DBUtil;

public class MainApp {
	private UserService userService;

	public MainApp() {
		DBUtil dbUtil = new DBUtil();
		UserDAO userDAO = new UserDAO(dbUtil);
		userService = new UserService(userDAO);
	}

	public void init() {
	}

	public static void main(String[] args) {

		MainApp mainApp = new MainApp();
		mainApp.init();
		// mainApp.save();
		// mainApp.update();
		// mainApp.findById();
		// mainApp.findByMobile();
		mainApp.findByLogin();
		// mainApp.deleteById();
	}

	public void save() {
		System.out.println("Main.Save()");
		Scanner scanner = new Scanner(System.in);
		try {
			UserDTO userDTO = new UserDTO();
			// userDTO.setFirstName(request.getParam("firstNmae"));
			System.out.println("Enter your First name:");
			userDTO.setFirstName(scanner.next());
			System.out.println("Enter your Last name:");
			userDTO.setLastName(scanner.next());
			System.out.println("Enter your Email Id:");
			userDTO.setEmail(scanner.next());
			System.out.println("Enter your Mobile number:");
			userDTO.setContact(scanner.next());
			System.out.println("Enter your Password:");
			userDTO.setPassword(scanner.next());
			System.out.println("User Status (Active/Inactive) *Admin Only:");
			userDTO.setIsactive(scanner.nextInt());
//			System.out.println("Enter your address:");
//			userDTO.setAddress(scanner.next());
			scanner.close();
			int count = userService.save(userDTO);
			if (count > 0) {
				System.out.println("User account created successfully.");
//				response.set
			} else {
				System.out.println("Failed to create user account.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update() {
		System.out.println("Main.update()");
		Scanner scanner = new Scanner(System.in);
		try {
			UserDTO userDTO = new UserDTO();
//		userDTO.setFirstName(request.getParam("firstNmae"));
			System.out.println("Enter your user Id number:");
			userDTO.setId(scanner.nextInt());

			System.out.println("Enter your first name:");
			userDTO.setFirstName(scanner.next());

			System.out.println("Enter your last name:");
			userDTO.setLastName(scanner.next());
			scanner.close();
			int count = userService.update(userDTO);
			if (count > 0) {
				System.out.println("User account updated successfully.");
//				response.set
			} else {
				System.out.println("Failed to update user account.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteById() {
		System.out.println("Main.delectById()");
		Scanner scanner = new Scanner(System.in);
		try {
//		userDTO.setFirstName(request.getParam("userId"));
			System.out.println("Enter your user Id number:");
			int userId = scanner.nextInt();
			scanner.close();
			int count = userService.deleteById(userId);
			if (count > 0) {
				System.out.println("User account deleted successfully.");
//				response.set
			} else {
				System.out.println("Failed to delete user account.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void findById() {
		System.out.println("Main.findById()");
		Scanner scanner = new Scanner(System.in);
		try {
//		userDTO.setFirstName(request.getParam("userId"));
			System.out.println("Enter your user Id number:");
			int userId = scanner.nextInt();
			scanner.close();
			UserDTO userDTO = userService.findById(userId);
			if (userDTO != null) {
				System.out.println("User account found successfully.");
				System.out.println(userDTO.getFirstName());
//				response.set
			} else {
				System.out.println("Failed to find user account.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findByMobile() {
		System.out.println("Main.findByMobile()");

		Scanner scanner = new Scanner(System.in);
		try {
//		userDTO.setFirstName(request.getParam("userId"));
			System.out.println("Enter your phone number:");
			String contact = scanner.nextLine();
			scanner.close();
			UserDTO userDTO = userService.findByMobile(contact);
			if (userDTO != null) {

				System.out.println("User exists : Name -> " + userDTO.getFirstName() + " " + userDTO.getLastName());
				// System.out.println("Contact : " + userDTO.getMobileNumber());

//				response.set
			} else {
				System.out.println("Failed to find user account.");
				System.out.println("Please recheck " + contact + " your the number.");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	private void findByLogin() {
		System.out.println("Main.findByLogin()");

		Scanner scanner = new Scanner(System.in);
		try {
			// userDTO.setFirstName(request.getParam("userId"));
			System.out.println("Enter your email or phone number:");
			String input = scanner.nextLine();

			System.out.println("Enter your password:");
			String password = scanner.nextLine();
			scanner.close();

			UserDTO userDTO = userService.findByLogin(input, password);
			if (userDTO != null) {
				System.out.println("Good Evening...");

				System.out.println("Welcome " + userDTO.getFirstName() + ", You have successfully logged-in.");

//				response.set
			} else {
				System.out.println("Login failed. Please check your credentials.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void findAll() {
		System.out.println("MainApp.findAll()");

		try {
			List<UserDTO> userDTOs = userService.findAll();
			if (!userDTOs.isEmpty()) {
				System.out.println("User account found successfully.");
				System.out.println(userDTOs.size());

//				List<String> driverNames = new ArrayList<String>();
//				for (UserDTO userDTO : userDTOs) {
//					System.out.println(userDTO.getFirstName());
//					if (userDTO.getRole_id() == 2)
//						driverNames.add(userDTO.getFirstName());
//				}
//				System.out.println(driverNames);
//
//				

				userDTOs.stream().forEach(u -> {
					System.out.println(u.getFirstName());
				});

				List<String> firstNames = userDTOs.stream().map(u -> u.getFirstName()).collect(Collectors.toList());
				System.out.println(firstNames);

//				List<String> driverList = userDTOs.stream().filter(u -> u.getRole_id() == 1).map(u -> u.getFirstName())
//						.collect(Collectors.toList());
//				System.out.println(driverList);

				// response.set
			} else {
				System.out.println("Failed to find user account.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
