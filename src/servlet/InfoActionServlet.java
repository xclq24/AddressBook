package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.InfoDAO;
import entity.Info;
import entity.User;

public class InfoActionServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		if("/list".equals(action)){
			/**
			 * ��ʾͨѶ¼�б�
			 */
			//��õ�ǰ�Ự�б�����û���Ϣ��ͨ���༶�Ż�ñ��������ͨѶ¼��Ϣ
			User currentUser = (User)request.getSession().getAttribute("currentUser");
			List<Info> infoList = null;
			if(currentUser.getRole().equals("admin")){
				infoList =InfoDAO.findAll();
			}else{
				infoList =InfoDAO.findAllByClassid(currentUser.getClassid());
			}
			//��ͨѶ¼��Ϣ�б����������
			request.setAttribute("infoList", infoList);
			request.getSession().setAttribute("infoList", infoList);
			//������ת������ʾͨѶ¼��ҳ��
			request.getRequestDispatcher("info_list.jsp").forward(request, response);
		}else if("/delete".equals(action)){
			/**
			 * ɾ��һ��ͨѶ¼��Ϣ
			 */
			//��������б����ѧ��
			String id = request.getParameter("id");
			//ͨ��ѧ�������ݿ���ɾ��һ��ѧ����Ϣ
			InfoDAO.deleteById(id);
			//ɾ����ϣ������ض��򵽴���ͨѶ¼�б��Servlet
			response.sendRedirect("list.infoaction");
		}else if("/modify".equals(action)){
			/**
			 * �޸�һ��ͨѶ¼��Ϣ
			 */
			//��������д��ݹ�������Ϣ����װ��һ��ѧ������ͨ��getInfo������
			Info info = getInfo(request);
			//����ѧ�ţ������ݿ��и���һ��ͨѶ¼��¼
			InfoDAO.updateById(info.getId(), info);
			//�޸���ϣ������ض��򵽴���ͨѶ¼�б��Servlet
			response.sendRedirect("list.infoaction");
		}else if("/add".equals(action)){
			/**
			 * ���һ��ͨѶ¼��Ϣ
			 */
			//�����ѧ�������ѧ���Ѿ��������ݿ��У������ʧ�ܣ����ش�����Ϣ
			String id = request.getParameter("id");
			if(InfoDAO.findById(id)!=null){
				request.setAttribute("id_exist_wrong_error", "��ѧ����Ϣ�Ѵ���");
				request.getRequestDispatcher("info_add.jsp").forward(request, response);
				return;
			}
			//������κ�һ��û����д�����������ӣ����ش�����Ϣ
			if(request.getParameter("id").equals("") || 
					request.getParameter("classid").equals("") || 
					request.getParameter("name").equals("") || 
					request.getParameter("gender")==null || 
					request.getParameter("province")==null || 
					request.getParameter("address").equals("") || 
					request.getParameter("birthday").equals("") || 
					request.getParameter("tel").equals("") ){
				request.setAttribute("info_null_wrong_error", "��Ϣ������");
				request.getRequestDispatcher("info_add.jsp").forward(request, response);
				return;
			}
			//��������д��ݹ�������Ϣ����װ��һ��ѧ������ͨ��getInfo������
			Info info = getInfo(request);
			//�����ݿ������һ��ͨѶ¼��¼
			InfoDAO.add(info);
			//�����ϣ������ض��򵽴���ͨѶ¼�б��Servlet
			response.sendRedirect("list.infoaction");
		}else if("/search".equals(action)){
			/**
			 * ��ѯͨѶ¼��Ϣ
			 */
			//��÷��࣬�ؼ��֣��Ƿ�ģ����ѯ
			String classify = request.getParameter("classify");
			String keywords = request.getParameter("keywords");
			String fuzzyquery = request.getParameter("fuzzyquery");
			
			//��÷���Ҫ������б����ѧ��������ӵ�������
			User currentUser = (User)request.getSession().getAttribute("currentUser");
			boolean isAdmin = currentUser.getRole().equals("admin");
			boolean isFuzzy = "fuzzyquery".equals(fuzzyquery);
			List<Info> infoList = null;
			if(isAdmin){
				infoList = InfoDAO.findByInput(classify, keywords, isFuzzy);
			}else{
				infoList = InfoDAO.findByInput(currentUser.getClassid(),classify, keywords, isFuzzy);				
			}
			request.setAttribute("infoList", infoList);
			request.getSession().setAttribute("infoList", infoList);
			//������ת������ʾͨѶ¼��ҳ��
			request.getRequestDispatcher("info_list.jsp").forward(request, response);
		}
	}
	/**
	 * ��ȡ�����еĸ�����Ϣ����װ��һ��ͨѶ¼��Ϣ����
	 * @param request ����
	 * @return ͨѶ¼��Ϣ����
	 */
	public static Info getInfo(HttpServletRequest request){
		//����һ���µ�ѧ������
		Info info = new Info();
		//���ø�������
		info.setId(request.getParameter("id"));
		info.setClassid(request.getParameter("classid"));
		info.setName(request.getParameter("name"));
		info.setGender(request.getParameter("gender").equals("m")?"��":"Ů");
		info.setProvince(request.getParameter("province"));
		info.setAddress(request.getParameter("address"));
		try {
			info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		info.setTel(request.getParameter("tel"));
		
		return info;
	}
}
