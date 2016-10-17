package br.com.kunami.primefaces_study.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.kunami.primefaces_study.model.User;

@ManagedBean
@RequestScoped
public class UserController {
	private User registrationUser;
	private User loginUser;
	private String loginStatus;

	public UserController() {
		this.setRegistrationUser(new User());
		this.setLoginUser(new User());
	}

	public User getRegistrationUser() {
		System.out.println("getRegistrationUser()");
		return registrationUser;
	}

	public void setRegistrationUser(User registrationUser) {
		System.out.println("setRegistrationUser(" + registrationUser + ")");
		this.registrationUser = registrationUser;
	}

	public User getLoginUser() {
		System.out.println("getLoginUser()");
		return loginUser;
	}

	public void setLoginUser(User loginUser) {
		System.out.println("setLoginUser(" + loginUser + ")");
		this.loginUser = loginUser;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String register() {
		System.out.println("Register User :" + this.registrationUser);
		String msg = "User Registered Successfully";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "registration.jsf?faces-redirect=true";
	}

	public String updateUser() {
		System.out.println("Updating User Id: " + this.loginUser.getId());
		String msg = "User updated Successfully";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		return "userDetails";
	}

	public String deleteUser() {
		System.out.println("deleting User Id: " + this.loginUser.getId());
		String msg = "User deleted Successfully";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		return "userDetails";
	}

	// Uso de listener para apresentar mensagem de erro via AJAX
	public void checkUserNamesExists() {
		System.out.println("checking if user name exists: " + this.loginUser.getUserName());
		if (getLoginUser().getUserName().equals("admin")) {
			String msg = "User name already exists";
			FacesContext.getCurrentInstance().addMessage("userDetailsForm:userName",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}
	}

	public String login() {
		boolean validCredentials = "admin".equals(loginUser.getUserName()) && "admin".equals(loginUser.getPassword());
		this.loginStatus = validCredentials ? "Login Successful" : "Login failed";
		return null;
	}

	public void checkEmailExists() {
		String email = this.registrationUser.getEmail();
		if ("admin@gmail.com".equals(email) || "test@gmail.com".equals(email)) {
			String msg = "Email [" + email + "] already in use.";
			FacesContext.getCurrentInstance().addMessage("registrationForm:email",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}
	}

}
