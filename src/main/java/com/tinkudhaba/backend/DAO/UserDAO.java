package com.tinkudhaba.backend.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.tinkudhaba.backend.DTO.UserDTO;
import com.tinkudhaba.backend.UTIL.UserStatus;
import com.tinkudhaba.backend.UTIL.DBUtil;
import com.mysql.cj.protocol.Resultset;

public class UserDAO {

	private final String USER_SELECT_ALL = "select * from user";
	private final String USER_SAVE = "insert into user (role_id,first_name, last_name,email,contact, password,is_active,dob,city_id) value (?,?, ?, ?, ?, ?, ?, ?, ?)";
	private final String USER_UPDATE = "update user set first_name =?, last_name =? where id =?";
	private final String USER_SELECT_BY_ID = "select * from user where id = ?";
	private final String USER_SELECT_BY_MOBILE = "select * from user where contact = ?";
	private final String USER_SELECT_FOR_LOGIN = "select * from user where (email = ? or contact = ? ) and password = ?";
	private final String USER_DELET_BY_ID = "delete from user where id = ?";

	private DBUtil dbUtil;

	public UserDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	public int save(UserDTO dto) throws Exception {
		System.out.println("UserDAO.Save()");
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dbUtil.getConnection();
			// stmt or pstmtt
			pstmt = connection.prepareStatement(USER_SAVE);
			 pstmt.setInt(1, dto.getRoleId());
			pstmt.setString(2, dto.getFirstName());
			pstmt.setString(3, dto.getLastName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getContact());
			pstmt.setString(6, dto.getPassword());
			pstmt.setInt(7, UserStatus.ACTIVE);
			//Date --> UtilDate --> dobSqlDate
			//MM = months, mm = minutes
			Date dobUitlDate = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDob());
			java.sql.Date dobSqlDate = new java.sql.Date(dobUitlDate.getTime());
			System.out.println("dobUitlDate: " + dobUitlDate);
			System.out.println("dobSqlDate: " + dobSqlDate);
			pstmt.setDate(8, dobSqlDate);

			pstmt.setInt(9, dto.getCityId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			// closed
			dbUtil.close(connection, pstmt, null);
		}

	}

	public int update(UserDTO dto) throws Exception {
		System.out.println("UserDAO.Update()");
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(USER_UPDATE);
			pstmt.setString(1, dto.getFirstName());
			pstmt.setString(2, dto.getLastName());
			pstmt.setInt(3, dto.getId());

			return pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}
	}

	public UserDTO findById(int id) throws Exception {
		System.out.println("UserDAO.FindById()");
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		UserDTO userDTO = null;

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(USER_SELECT_BY_ID);
			pstmt.setInt(1, id);

//			return pstmt.executeUpdate();

			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				userDTO = new UserDTO();
				System.out.println("In if Condition");
				userDTO.setId(resultSet.getInt("id"));
				userDTO.setFirstName(resultSet.getString("first_name"));
				userDTO.setLastName(resultSet.getString("last_name"));
				userDTO.setEmail(resultSet.getString("email"));
				userDTO.setContact(resultSet.getString("contact"));
				userDTO.setCityId(resultSet.getInt("city_id"));

			}

			return userDTO;

		} catch (Exception e) {
			throw e;
		} finally {
			dbUtil.close(connection, pstmt, resultSet);
		}
	}

	public UserDTO findByMobile(String contact) throws Exception {
		System.out.println("UserDAO.FindByMobile()");
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		UserDTO userDTO = null;

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(USER_SELECT_BY_MOBILE);
			pstmt.setString(1, contact);

//			return pstmt.executeUpdate();

			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				userDTO = new UserDTO();
				System.out.println("In if Condition");
				userDTO.setId(resultSet.getInt("id"));
				userDTO.setFirstName(resultSet.getString("first_name"));
				userDTO.setLastName(resultSet.getString("last_name"));
//				userDTO.setEmail(resultSet.getString("email"));
				userDTO.setContact(resultSet.getString("contact"));

			}

			return userDTO;

		} catch (Exception e) {
			throw e;
		} finally {
			dbUtil.close(connection, pstmt, resultSet);
		}

	}

	public UserDTO findByLogin(String username, String password) throws Exception {
		System.out.println("UserDAO.FindByLogin()");
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		UserDTO userDTO = null;

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(USER_SELECT_FOR_LOGIN);
			pstmt.setString(1, username);
			pstmt.setString(2, username);
			pstmt.setString(3, password);

//			return pstmt.executeUpdate();

			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				userDTO = new UserDTO();
				System.out.println("In if Condition");
				userDTO.setId(resultSet.getInt("id"));
				userDTO.setFirstName(resultSet.getString("first_name"));
				userDTO.setLastName(resultSet.getString("last_name"));
				userDTO.setEmail(resultSet.getString("email"));
				userDTO.setContact(resultSet.getString("contact"));
				// userDTO.setCityId(resultSet.getInt("city_id"));
			}
			return userDTO;
		} catch (Exception e) {
			throw e;
		} finally {
			dbUtil.close(connection, pstmt, resultSet);
		}
	}

	public int deleteById(int id) throws Exception {
		System.out.println("UserDAO.DeleteById()");
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(USER_DELET_BY_ID);
			pstmt.setInt(1, id);

			return pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}
	}

	public List<UserDTO> findAll() throws ClassNotFoundException, SQLException {

		System.out.println("UserDAO.findAll");
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<UserDTO> userDTOs = new ArrayList<>();

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(USER_SELECT_ALL);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				//System.out.println("in loop");
				userDTO.setId(resultSet.getInt("id"));
				userDTO.setRoleId(resultSet.getInt("role_id"));
				userDTO.setFirstName(resultSet.getString("first_name"));
				userDTO.setLastName(resultSet.getString("last_name"));
				userDTO.setEmail(resultSet.getString("email"));
				userDTO.setContact(resultSet.getString("contact"));
				userDTO.setDob(resultSet.getString("dob"));
				userDTOs.add(userDTO);
			}
			return userDTOs;
		} catch (Exception e) {
			throw e;
		} finally {
			dbUtil.close(connection, pstmt, resultSet);
		}

	}
}
