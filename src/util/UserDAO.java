package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
/**
 * �û�������ݿ������
 * @author Jasper
 *
 */
public class UserDAO {
	/**
	 * ���һ���û���Ϣ
	 * @param user һ���û�����
	 * @return �Ƿ�ɹ����
	 */
	public static boolean add(User user){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into user(username,password) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * ͨ���û���ɾ��һ���û���Ϣ
	 * @param username �û���
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public static boolean deleteByUsername(String username){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from user where username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * �޸�һ���û���Ϣ������
	 * @param username �û���
	 * @param newPassword ������
	 * @return �Ƿ��޸ĳɹ�
	 */
	public static boolean updatePassword(String username,String newPassword){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update user set password=? where username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setString(2, username);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * ͨ���û�������û�����
	 * @param username �û���
	 * @return	�û�����
	 */
	public static User findByUsername(String username){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select username,password from user where username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()){
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
