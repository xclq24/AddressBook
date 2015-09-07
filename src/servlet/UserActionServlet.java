package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StudentDAO;
import util.UserDAO;
import entity.User;
/**
 * �û�����ΪServlet��
 * 
 * @author Jasper
 *
 */
public class UserActionServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		if("/login".equals(action)){
			/*
			 * �û���¼
			 */
			
			//����û��������֤��
			String number_input = request.getParameter("number_input");
			//���ϵͳ���ɵģ������ڵ�ǰ�Ự�е���֤��
			HttpSession session = request.getSession();
			String number_pic = (String)session.getAttribute("number_pic");
			
			//���������֤�벻һ�£��򷵻ش�����Ϣ
			if(!number_input.toLowerCase().equals(number_pic.toLowerCase())){
				request.setAttribute("number_error", "��֤�����");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			
			//����û���д���û���������
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			//��÷����������û�
			User user = UserDAO.findByUsername(username);
			
			//����û������ڻ�������󣬷��ش�����Ϣ
			if(user==null || !user.getPassword().equals(password)){
				request.setAttribute("user_or_password_wrong_error", "�û������������");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			
			//��ѯ�û����ڰ༶��
			String classid = StudentDAO.findClassidById(username);
			//���ñ��λỰ�ĵ�ǰ�û��͵�ǰ�༶
			request.getSession().setAttribute("currentUser", user);
			request.getSession().setAttribute("currentClass", classid);
			
			//���˵�¼�ɹ���ת������ͨѶ¼�б��Servlet
			response.sendRedirect("list.studentaction");

		}else if("/regist".equals(action)){
			/**
			 * �û�ע��
			 */
			//����û��������֤��
			String number_input = request.getParameter("number_input");
			//���ϵͳ���ɵģ������ڵ�ǰ�Ự�е���֤��
			HttpSession session = request.getSession();
			String number_pic = (String)session.getAttribute("number_pic");
			
			//���������֤�벻һ�£��򷵻ش�����Ϣ
			if(!number_input.toLowerCase().equals(number_pic.toLowerCase())){
				request.setAttribute("number_error", "��֤�����");
				request.getRequestDispatcher("regist.jsp").forward(request, response);
				return;
			}
			
			//����û�������û���
			String username = request.getParameter("username");
			//����û����Ѿ����ڣ��򷵻ش�����Ϣ
			if(UserDAO.findByUsername(username)!=null){
				request.setAttribute("user_exist_error", "�û����Ѵ���");
				request.getRequestDispatcher("regist.jsp").forward(request, response);
				return;
			}
			//����û�����������ȷ������
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");
			//����������벻һ�£��򷵻ش�����Ϣ
			if(!password.equals(repassword)){
				request.setAttribute("password_wrong_error", "�������벻һ��");
				request.getRequestDispatcher("regist.jsp").forward(request, response);
				return;
			}
			
			//�½�һ���û�����
			User user = new User();
			//�����û���������
			user.setUsername(username);
			user.setPassword(password);
			//��ӵ����ݿ�
			UserDAO.add(user);
			//����ע��ɹ���ת����½����
			response.sendRedirect("index.jsp");
			
		}else if("/modifyPassword".equals(action)){
			/**
			 * �޸�����
			 */
			
			//�������ľ����룬�����룬ȷ������
			String oldPassword = request.getParameter("old_password");
			String newPassword = request.getParameter("new_password");
			String affirmPassword = request.getParameter("affirm_password");
			
			//��õ�ǰ��½���û�����
			User user = (User)request.getSession().getAttribute("currentUser");
			//����������뵱ǰ�û������벻һ�£��򷵻ش�����Ϣ
			if(!oldPassword.equals(user.getPassword())){
				request.setAttribute("old_password_wrong_error", "ԭ�������");
				request.getRequestDispatcher("pwd.jsp").forward(request, response);
				return;
			}
			//�����������ȷ�����벻һ�£��򷵻ش�����Ϣ
			if(!newPassword.equals(affirmPassword)){
				request.setAttribute("password_wrong_error", "�������벻һ��");
				request.getRequestDispatcher("pwd.jsp").forward(request, response);
				return;
			}
			//�������ݿ��е�����
			UserDAO.updatePassword(user.getUsername(), newPassword);
			//�����ǰ�Ự�б���ĵ�½�û���Ϣ���༶��Ϣ
			request.getSession().removeAttribute("currentUser");
			request.getSession().removeAttribute("currentClass");
			//�����޸�������ɣ������µ�½��ת����½����
			response.sendRedirect("index.jsp");
			
		}else if("/logout".equals(action)){
			/**
			 * �û��ǳ�
			 */
			//�˳�ϵͳ��ʱ�������ǰ�Ự�������Ϣ
			request.getSession().removeAttribute("currentUser");
			request.getSession().removeAttribute("currentClass");
			//ת����½����
			response.sendRedirect("index.jsp");
		}
	}

}
