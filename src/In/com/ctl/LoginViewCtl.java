package In.com.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import In.com.Bean.Userbean;
import In.com.Model.UserModel;

@WebServlet("/LoginViewCtl")
public class LoginViewCtl extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op = req.getParameter("opreation");
		if(op != null && op.equals("logout")) {
			HttpSession session = req.getSession();
			session.invalidate();
		}
		
		resp.sendRedirect("LoginView.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String loginId = req.getParameter("loginId");
		String password = req.getParameter("passward");
		String op = req.getParameter("opreation");

		if(op.equals("signin")) {
			UserModel model = new UserModel();
			HttpSession session = req.getSession();

			try {
				
				Userbean bean = model.authenticate(loginId, password);
				
				if (bean != null) {
					session.setAttribute("user", bean);
					RequestDispatcher rd = req.getRequestDispatcher("Welcome.jsp");
					rd.forward(req, resp);
				} else {
					req.setAttribute("mesg", "login id & password invalid");
					RequestDispatcher rd = req.getRequestDispatcher("LoginView.jsp");
					rd.forward(req, resp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(op.equals("SignUp")) {
			resp.sendRedirect("UserRegistrationView.jsp");
		}
	}

}
