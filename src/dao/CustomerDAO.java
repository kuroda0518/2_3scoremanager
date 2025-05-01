package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Customer;

public class CustomerDAO extends Dao{
	public Customer search(String id, String password)
		throws Exception{
		Customer customer=null;

		Connection con=getConnection();

		PreparedStatement st;
		st=con.prepareStatement(
				"select * from teacher where id=? and password=?");
		st.setString(1, id);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();

		while(rs.next()){
			customer=new Customer();
			customer.setId(rs.getString("id"));
			customer.setLogin(rs.getString("name"));
			customer.setPassword(rs.getString("password"));
		}

		st.close();
		con.close();

		return customer;
	}

}
