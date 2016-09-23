package beans;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named("benutzer")
@SessionScoped
public class BenutzerBean implements Serializable {

	private String name;
	private String passwort;
	
	@Inject
	@UserList
	private Map<String, String> users;
	
	private String info;
	
	public String login() {
		System.out.println(users);
		for (Entry<String, String> e : users.entrySet()) {
			if (e.getKey().equals(name) && e.getValue().equals(passwort)) {
				return "/start.jsf";
			}
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
