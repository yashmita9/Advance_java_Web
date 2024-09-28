package In.com.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import In.com.Bean.Userbean;
import In.com.Model.UserModel;

@WebServlet("/UserListCtl.do")
public class UserListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Userbean bean = null;
		int pageNo = 1;
		int pageSize = 5;
		UserModel model = new UserModel();
		try {
			List list = model.search(bean, pageNo, pageSize);
			req.setAttribute("list", list);
			req.setAttribute("pageNo", pageNo);
			RequestDispatcher rd = req.getRequestDispatcher("UserListView.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Userbean bean = null;
		int pageNo = 1;
		int pageSize = 5;
		UserModel model = new UserModel();
		String op = req.getParameter("operation");

		if (op.equals("search")) {
			bean = new Userbean();
			bean.setFirstName(req.getParameter("firstName"));
		}
		if (op.equals("next")) {
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
			pageNo++;
		}

		if (op.equals("previous")) {
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
			pageNo--;
		}
		if (op.equals("Add")) {
			resp.sendRedirect("UserCtl");
		}
		if (op.equals("Delete")) {
			String[] ids = req.getParameterValues("ids");
			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					try {
						model.delete(Integer.parseInt(id));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		try {
			List list = model.search(bean, pageNo, pageSize);
			req.setAttribute("list", list);
			req.setAttribute("pageNo", pageNo);
			RequestDispatcher rd = req.getRequestDispatcher("UserListView.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
