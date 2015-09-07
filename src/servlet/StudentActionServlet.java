package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.StudentDAO;
import entity.Student;

public class StudentActionServlet extends HttpServlet {

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
			//��õ�ǰ�Ự�б���İ༶�ţ�ͨ���༶�Ż�ñ��������ѧ��
			List<Student> studentList =StudentDAO.findAll((String)request.getSession().getAttribute("currentClass"));
			//��ѧ���б����������
			request.setAttribute("studentList", studentList);
			//������ת������ʾͨѶ¼��ҳ��
			request.getRequestDispatcher("student_list.jsp").forward(request, response);
		}else if("/delete".equals(action)){
			/**
			 * ɾ��һ��ͨѶ¼��Ϣ
			 */
			//��������б����ѧ��
			String id = request.getParameter("id");
			//ͨ��ѧ�������ݿ���ɾ��һ��ѧ����Ϣ
			StudentDAO.deleteById(id);
			//ɾ����ϣ������ض��򵽴���ͨѶ¼�б��Servlet
			response.sendRedirect("list.studentaction");
		}else if("/modify".equals(action)){
			/**
			 * �޸�һ��ͨѶ¼��Ϣ
			 */
			//��������д��ݹ�������Ϣ����װ��һ��ѧ������ͨ��getStudent������
			Student student = getStudent(request);
			//����ѧ�ţ������ݿ��и���һ��ͨѶ¼��¼
			StudentDAO.updateById(student.getId(), student);
			//�޸���ϣ������ض��򵽴���ͨѶ¼�б��Servlet
			response.sendRedirect("list.studentaction");
		}else if("/add".equals(action)){
			/**
			 * ���һ��ͨѶ¼��Ϣ
			 */
			//������κ�һ��û����д�����������ӣ����ش�����Ϣ
			if(request.getParameter("id").equals("") || 
					request.getParameter("classid").equals("") || 
					request.getParameter("name").equals("") || 
					request.getParameter("gender")==null || 
					request.getParameter("address").equals("") || 
					request.getParameter("birthday").equals("") || 
					request.getParameter("tel").equals("") ){
				request.setAttribute("info_null_wrong_error", "��Ϣ������");
				request.getRequestDispatcher("student_add.jsp").forward(request, response);
				return;
			}
			//�����ѧ�������ѧ���Ѿ��������ݿ��У������ʧ�ܣ����ش�����Ϣ
			String id = request.getParameter("id");
			if(StudentDAO.findById(id)!=null){
				request.setAttribute("id_exist_wrong_error", "��ѧ����Ϣ�Ѵ���");
				request.getRequestDispatcher("student_add.jsp").forward(request, response);
				return;
			}
			//��������д��ݹ�������Ϣ����װ��һ��ѧ������ͨ��getStudent������
			Student student = getStudent(request);
			//�����ݿ������һ��ͨѶ¼��¼
			StudentDAO.add(student);
			//�����ϣ������ض��򵽴���ͨѶ¼�б��Servlet
			response.sendRedirect("list.studentaction");
		}else if("/search".equals(action)){
			/**
			 * ��ѯͨѶ¼��Ϣ
			 */
			//��÷��࣬�ؼ��֣��Ƿ�ģ����ѯ
			String classify = request.getParameter("classify");
			String keywords = request.getParameter("keywords");
			String fuzzyquery = request.getParameter("fuzzyquery");
			
			//��ӵ�������
			request.setAttribute("classify", classify);
			request.setAttribute("keywords", keywords);
			request.setAttribute("fuzzyquery", fuzzyquery);
			
			//��÷���Ҫ������б����ѧ��������ӵ�������
			List<Student> studentList = StudentDAO.findByInput((String)request.getSession().getAttribute("currentClass"),classify, keywords, "fuzzyquery".equals(fuzzyquery));
			request.setAttribute("studentList", studentList);
			//������ת������ʾͨѶ¼��ҳ��
			request.getRequestDispatcher("student_list.jsp").forward(request, response);
		}
	}
	/**
	 * ��ȡ�����еĸ�����Ϣ����װ��һ��ѧ������
	 * @param request ����
	 * @return ѧ������
	 */
	public static Student getStudent(HttpServletRequest request){
		//����һ���µ�ѧ������
		Student student = new Student();
		//���ø�������
		student.setId(request.getParameter("id"));
		student.setClassid(request.getParameter("classid"));
		student.setName(request.getParameter("name"));
		student.setGender(request.getParameter("gender").equals("m")?"��":"Ů");
		student.setAddress(request.getParameter("address"));
		try {
			student.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		student.setTel(request.getParameter("tel"));
		
		return student;
	}
}
