package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Customer;
import dao.CustomerDAO;
import tool.Action;

public class LoginAction extends Action{
	public String execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception{
		HttpSession session=request.getSession();

		String id=request.getParameter("id");
		String password=request.getParameter("password");
		CustomerDAO dao=new CustomerDAO();
		Customer customer=dao.search(id,password);

		if (customer!=null){
			session.setAttribute("customer",customer);
			return "main.jsp";
		}
		return "login-error.jsp";
	}

}

