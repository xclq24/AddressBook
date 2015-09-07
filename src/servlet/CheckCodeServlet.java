package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckCodeServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		//��ͼ
		//step1������һ���ڴ�ӳ�񣨻�����
		BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
		//step2����û���
		Graphics g = image.getGraphics();
		//step3��������������ɫ
		g.setColor(Color.WHITE);
		//step4�����û����ı�����ɫ
		g.fillRect(0, 0, 80, 30);
		//step5������һ�������
		String number = getNumber(2);
		

		HttpSession session = request.getSession();
		session.setAttribute("number_pic", number);
		
		
		//step6������������Ƶ�ͼƬ��
		Random r= new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.setFont(new Font("Microsoft Yahei",Font.ITALIC,24));
		g.drawString(number, 3, 24);
		
		//step7��������
		for (int i = 0; i < 5; i++) {
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
		}
		
		//ѹ��ͼƬ��Ȼ�����
		//step1�����÷��������ص���������ΪͼƬ
		response.setContentType("image/jpeg");
		//step2����������
		OutputStream os = response.getOutputStream();
		//step3��ѹ��ͼƬ�����
		ImageIO.write(image, "jpeg", os);
		os.close();
		
	}
	private String getNumber(int size){
		String number="";
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random r= new Random();
		for (int i = 0; i < size; i++) {
			number += str.charAt(r.nextInt(36));
		}
		return number;
	}
}
