package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "benutzer") // Default: benutzerbean
@SessionScoped
public class BenutzerBean {

	private String name;
	private String passwort;

	private String info;

	public String login() {
		if ("Testa".equals(name) && "geheim".equals(passwort)) {
			return "/start.jsf";
		}
		setInfo("Login nicht erfolgreich!");
		return "/login.jsf";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
