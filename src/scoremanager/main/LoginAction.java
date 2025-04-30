package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD

import tool.Action;

public class LoginAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "login_dummy.jsp";  // 仮のJSPを返す
    }
}
=======
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

>>>>>>> 2adb4da8a49b7cbd977dbd40744f6060258c842c
