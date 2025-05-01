package bean;

public class Customer implements java.io.Serializable{

	private String id;
	private String login;
	private String password;

	public String getId(){
		return id;
	}public String getLogin(){
		return login;
	}public String getPassword(){
		return password;
	}

	public void setId(String id){
		this.id=id;
	}public void setLogin(String login){
		this.login=login;
	}public void setPassword(String password){
		this.password=password;
	}

}
