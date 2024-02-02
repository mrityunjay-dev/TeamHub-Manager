package com.tinkudhaba.backend.SERVICE;

import java.sql.SQLException;
import java.util.List;

import com.tinkudhaba.backend.DAO.UserDAO;
import com.tinkudhaba.backend.DTO.UserDTO;

public class UserService {
	private UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public int save(UserDTO userDTO) throws Exception {
		System.out.println("UserService.Save()");
		int count = userDAO.save(userDTO);
		if (count > 0) {
			System.out.println("Sending welcome email");
		}
		return count;

	}

	public int update(UserDTO userDTO) throws Exception {
		return userDAO.update(userDTO);
	}

	public int deleteById(int id) throws Exception {
		return userDAO.deleteById(id);
	}

	public UserDTO findById(int id) throws Exception {
		return userDAO.findById(id);
	}

	public UserDTO findByMobile(String contact) throws Exception {
		return userDAO.findByMobile(contact);
	}

	public UserDTO findByLogin(String input, String password) throws Exception {

		return userDAO.findByLogin(input, password);
	}

	public List<UserDTO> findAll() throws ClassNotFoundException, SQLException {
		return userDAO.findAll();
	}
	public List<UserDTO> findAllbyAdmin() throws ClassNotFoundException, SQLException {
		return userDAO.findAll();
	}

}
