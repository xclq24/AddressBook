package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.Info;
/**
 * ͨѶ¼��Ϣ������ݿ������
 * @author Jasper
 *
 */
public class InfoDAO {
	/**
	 * ���һ��ͨѶ¼��Ϣ
	 * @param info һ����Ϣ����
	 * @return �Ƿ�ɹ����
	 */
	public static boolean add(Info info){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into info(id,classid,name,gender,province,address,birthday,tel) values(?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getId());
			ps.setString(2, info.getClassid());
			ps.setString(3, info.getName());
			ps.setString(4, info.getGender());
			ps.setString(5, info.getProvince());
			ps.setString(6, info.getAddress());
			ps.setString(7,new SimpleDateFormat("yyyy-MM-dd").format(info.getBirthday()));
			ps.setString(8, info.getTel());
			
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
	 * ͨ��ѧ��idɾ��һ��ͨѶ¼��Ϣ
	 * @param id ѧ��
	 * @return �Ƿ�ɹ�ɾ��
	 */
	public static boolean deleteById(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from info where id=?";
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
	 * ͨ��ѧ�Ÿ���һ��ͨѶ¼��Ϣ
	 * @param id ѧ��
	 * @param student ѧ������
	 * @return �Ƿ�ɹ��޸�
	 */
	public static boolean updateById(String id,Info info){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update info set classid=?,name=?,gender=?,province=?,address=?,birthday=?,tel=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getClassid());
			ps.setString(2, info.getName());
			ps.setString(3, info.getGender());
			ps.setString(4, info.getProvince());
			ps.setString(5, info.getAddress());
			ps.setString(6,new SimpleDateFormat("yyyy-MM-dd").format(info.getBirthday()));
			ps.setString(7, info.getTel());
			ps.setString(8, id);
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
	 * @return ͨѶ¼��Ϣ����
	 */
	public static Info findById(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name,gender,province,address,birthday,tel from info where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				Info info = new Info();
				info.setId(rs.getString("id"));
				info.setClassid(rs.getString("classid"));
				info.setName(rs.getString("name"));
				info.setGender(rs.getString("gender"));
				info.setProvince(rs.getString("province"));
				info.setAddress(rs.getString("address"));
				info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				info.setTel(rs.getString("tel"));
				return info;
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
			String sql = "select classid from info where id=?";
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
	 * ͨ���������һ��ͨѶ¼��Ϣ����
	 * @param name ����
	 * @return ͨѶ¼��Ϣ����
	 */
	public static Info findByName(String name){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name,gender,province,address,birthday,tel from info where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()){
				Info info = new Info();
				info.setId(rs.getString("id"));
				info.setClassid(rs.getString("classid"));
				info.setName(rs.getString("name"));
				info.setGender(rs.getString("gender"));
				info.setProvince(rs.getString("province"));
				info.setAddress(rs.getString("address"));
				info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				info.setTel(rs.getString("tel"));
				return info;
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
	 * ��ñ���ģ��ҷ����������Ҫ�������ͨѶ¼��Ϣ
	 * @param classid �༶��
	 * @param classify �����ࣨѧ��/����/��ϵ��ʽ����ѯ
	 * @param keywords �ؼ���
	 * @param isFuzzy �Ƿ�ģ����ѯ
	 * @return	ͨѶ¼��Ϣ�б�
	 */
	public static List<Info> findByInput(String classid,String classify,String keywords,boolean isFuzzy){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Info> infoList = new ArrayList<Info>();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "";
			if("".equals(keywords)){
				sql = "select id,classid,name,gender,province,address,birthday,tel from info where classid=?";
			}else if(isFuzzy){
				sql = "select id,classid,name,gender,province,address,birthday,tel from info where classid=? and "+ classify +" like ? ";
				keywords = "%"+keywords+"%";
			}else{
				sql = "select id,classid,name,gender,province,address,birthday,tel from info where classid=? and "+ classify +" =?";				
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, classid);
			if(!"".equals(keywords)){
				ps.setString(2, keywords);				
			}
			rs = ps.executeQuery();
			while(rs.next()){
				Info info = new Info();
				infoList.add(info);
				info.setId(rs.getString("id"));
				info.setClassid(rs.getString("classid"));
				info.setName(rs.getString("name"));
				info.setGender(rs.getString("gender"));
				info.setProvince(rs.getString("province"));
				info.setAddress(rs.getString("address"));
				info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				info.setTel(rs.getString("tel"));
			}
			return infoList;
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
	 * ��÷����������Ҫ�������ͨѶ¼��Ϣ
	 * @param classify �����ࣨ�༶��/ѧ��/����/��ϵ��ʽ����ѯ
	 * @param keywords �ؼ���
	 * @param isFuzzy �Ƿ�ģ����ѯ
	 * @return	ͨѶ¼��Ϣ�б�
	 */
	public static List<Info> findByInput(String classify,String keywords,boolean isFuzzy){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Info> infoList = new ArrayList<Info>();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "";
			if("".equals(keywords)){
				sql = "select id,classid,name,gender,province,address,birthday,tel from info";
			}else if(isFuzzy){
				sql = "select id,classid,name,gender,province,address,birthday,tel from info where "+ classify +" like ? ";
				keywords = "%"+keywords+"%";
			}else{
				sql = "select id,classid,name,gender,province,address,birthday,tel from info where "+ classify +" =?";				
			}
			
			ps = conn.prepareStatement(sql);
			if(!"".equals(keywords)){
				ps.setString(1, keywords);				
			}
			rs = ps.executeQuery();
			while(rs.next()){
				Info info = new Info();
				infoList.add(info);
				info.setId(rs.getString("id"));
				info.setClassid(rs.getString("classid"));
				info.setName(rs.getString("name"));
				info.setGender(rs.getString("gender"));
				info.setProvince(rs.getString("province"));
				info.setAddress(rs.getString("address"));
				info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				info.setTel(rs.getString("tel"));
			}
			return infoList;
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
	 * ������б����ͨѶ¼��Ϣ
	 * @param classid �༶��
	 * @return ͨѶ¼��Ϣ�б�
	 */
	public static List<Info> findAllByClassid(String classid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Info> infoList = new ArrayList<Info>();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name,gender,province,address,birthday,tel from info where classid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, classid);
			rs = ps.executeQuery();
			while(rs.next()){
				Info info = new Info();
				infoList.add(info);
				info.setId(rs.getString("id"));
				info.setClassid(rs.getString("classid"));
				info.setName(rs.getString("name"));
				info.setGender(rs.getString("gender"));
				info.setProvince(rs.getString("province"));
				info.setAddress(rs.getString("address"));
				info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				info.setTel(rs.getString("tel"));
			}
			return infoList;
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
	 * ������е�ͨѶ¼��Ϣ
	 * @return ͨѶ¼��Ϣ�б�
	 */
	public static List<Info> findAll(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Info> infoList = new ArrayList<Info>();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,classid,name,gender,province,address,birthday,tel from info";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Info info = new Info();
				infoList.add(info);
				info.setId(rs.getString("id"));
				info.setClassid(rs.getString("classid"));
				info.setName(rs.getString("name"));
				info.setGender(rs.getString("gender"));
				info.setProvince(rs.getString("province"));
				info.setAddress(rs.getString("address"));
				info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthday")));
				info.setTel(rs.getString("tel"));
			}
			return infoList;
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
	
	public static int[] getProvinceDistribution(List<Info> infoList){
		String[] provinces = {"����","���","�Ϻ�","����","�ӱ�","����","����","����","������","����","����","ɽ��","�½�","����","�㽭","����","����","����","����","ɽ��","���ɹ�",
				"����","����","����","����","�㶫","�ຣ","����","�Ĵ�","����","����","̨��","���","����"};
		int []p = new int[provinces.length];
		for (int i = 0; i < infoList.size(); i++) {
			Info info = infoList.get(i);
			String province = info.getProvince();
			
			int index = -1;
			for (int j = 0; j < provinces.length; j++) {
				if(provinces[j].equals(province)){
					index = j;
					break;
				}
			}
			p[index]++;
		}
		return  p;
	}
}
