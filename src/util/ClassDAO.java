package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * �༶������ݿ������
 * @author Jasper
 *
 */
public class ClassDAO {
	/**
	 * ͨ���༶�Ż�ð༶����
	 * @param id �༶��
	 * @return �༶����
	 */
	public static String findNameById(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select name from class where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getString("name");
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
