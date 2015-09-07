package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entity.Student;
/**
 * ѧ��������ݿ������
 * @author Jasper
 *
 */
public class StudentDAO {
	/**
	 * ���һ��ѧ����Ϣ
	 * @param student һ��ѧ������
	 * @return �Ƿ�ɹ����
	 */
	public static boolean add(Student student){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into student(id,classid,name,gender,address,birthday,tel) values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, student.getId());
			ps.setString(2, student.getClassid());
			ps.setString(3, student.getName());
			ps.setString(4, student.getGender());
			ps.setString(5, student.getAddress());
			ps.setString(6,new SimpleDateFormat("yyyy-MM-dd").format(student.getBirthday()));
			ps.setString(7, student.getTel());
			
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
	 * ͨ��ѧ��idɾ��һ��ѧ����Ϣ
	 * @param id ѧ��
	 * @return �Ƿ�ɹ�ɾ��
	 */
	public static boolean deleteById(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from student where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
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
	 * ͨ��ѧ�Ÿ���һ��ѧ����Ϣ
	 * @param id ѧ��
	 * @param student ѧ������
	 * @return �Ƿ�ɹ��޸�
	 */
	public static boolean updateById(String id,Student student){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update student set classid=?,name=?,gender=?,address=?,birthday=?,tel=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, student.getClassid());
			ps.setString(2, student.getName());
			ps.setString(3, student.getGender());
			ps.setString(4, student.getAddress());
			ps.setString(5,new SimpleDateFormat("yyyy-MM-dd").format(student.getBirthday()));
			ps.setString(6, student.getTel());
			ps.setString(7, id);
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
	 * ͨ��ѧ�Ż��һ��ѧ������
	 * @param id ѧ��
	 * @return ѧ������
	 */
	public static Student findById(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name,gender,address,birthday,tel from student where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				Student student = new Student();
				student.setId(rs.getString("id"));
				student.setClassid(rs.getString("classid"));
				student.setName(rs.getString("name"));
				student.setGender(rs.getString("gender"));
				student.setAddress(rs.getString("address"));
				student.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				student.setTel(rs.getString("tel"));
				return student;
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
	/**
	 * ͨ��ѧ�Ż�����ڰ༶��
	 * @param id ѧ��
	 * @return �༶��
	 */
	public static String findClassidById(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select classid from student where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getString("classid");
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
	/**
	 * ͨ���������һ��ѧ������
	 * @param name ����
	 * @return ѧ������
	 */
	public static Student findByName(String name){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name,gender,address,birthday,tel from student where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()){
				Student student = new Student();
				student.setId(rs.getString("id"));
				student.setClassid(rs.getString("classid"));
				student.setName(rs.getString("name"));
				student.setGender(rs.getString("gender"));
				student.setAddress(rs.getString("address"));
				student.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				student.setTel(rs.getString("tel"));
				return student;
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
	/**
	 * ��ñ���ģ��ҷ����������Ҫ�������ѧ��
	 * @param classid �༶��
	 * @param classify �����ࣨѧ��/����/��ϵ��ʽ����ѯ
	 * @param keywords �ؼ���
	 * @param isFuzzy �Ƿ�ģ����ѯ
	 * @return	ѧ���б�
	 */
	public static List<Student> findByInput(String classid,String classify,String keywords,boolean isFuzzy){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Student> studentList = new ArrayList<Student>();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "";
			if("".equals(keywords)){
				sql = "select id,classid,name,gender,address,birthday,tel from student where classid=?";
			}else if(isFuzzy){
				sql = "select id,classid,name,gender,address,birthday,tel from student where classid=? and "+ classify +" like ? ";
				keywords = "%"+keywords+"%";
			}else{
				sql = "select id,classid,name,gender,address,birthday,tel from student where classid=? and "+ classify +" =?";				
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, classid);
			if(!"".equals(keywords)){
				ps.setString(2, keywords);				
			}
			rs = ps.executeQuery();
			while(rs.next()){
				Student student = new Student();
				studentList.add(student);
				student.setId(rs.getString("id"));
				student.setClassid(rs.getString("classid"));
				student.setName(rs.getString("name"));
				student.setGender(rs.getString("gender"));
				student.setAddress(rs.getString("address"));
				student.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				student.setTel(rs.getString("tel"));
			}
			return studentList;
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
	}
	
	/**
	 * ������б����ѧ��
	 * @param classid �༶��
	 * @return ѧ���б�
	 */
	public static List<Student> findAll(String classid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Student> studentList = new ArrayList<Student>();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name,gender,address,birthday,tel from student where classid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, classid);
			rs = ps.executeQuery();
			while(rs.next()){
				Student student = new Student();
				studentList.add(student);
				student.setId(rs.getString("id"));
				student.setClassid(rs.getString("classid"));
				student.setName(rs.getString("name"));
				student.setGender(rs.getString("gender"));
				student.setAddress(rs.getString("address"));
				student.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				student.setTel(rs.getString("tel"));
			}
			return studentList;
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
	}
	
}
