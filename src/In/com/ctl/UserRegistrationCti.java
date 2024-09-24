package In.com.ctl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import In.com.Bean.Userbean;
import In.com.Model.UserModel;

@WebServlet("/UserRegistrationCti")
public class UserRegistrationCti extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("UserRegistrationView.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
		Userbean bean = new Userbean();
		bean.setFirstName(req.getParameter("firstName"));
		bean.setLastName(req.getParameter("lastName"));
		bean.setLoginId(req.getParameter("loginId"));
		bean.setPassward(req.getParameter("passward"));
		try {
			bean.setDob(sdf.parse(req.getParameter("dob")));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		bean.setAddress(req.getParameter("address"));
		
		UserModel model = new UserModel();
		try {
			model.add(bean);
			req.setAttribute("mesg", "User registered sucessfully");
			RequestDispatcher rd = req.getRequestDispatcher("UserRegistrationView.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	
}
