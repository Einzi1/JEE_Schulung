package rest.lieferanten;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Lieferant {

	// Primärschlüssel
	private Integer id;

	private String nr;
	private String name;
	private Adresse rechnungsAdresse;
	private Adresse lieferAdresse;
	private String email;
	private String url;

	public Lieferant() {
	}

	public Lieferant(String nr, String name) {
		super();
		this.nr = nr;
		this.name = name;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Adresse getRechnungsAdresse() {
		return rechnungsAdresse;
	}

	public void setRechnungsAdresse(Adresse rechnungsAdresse) {
		this.rechnungsAdresse = rechnungsAdresse;
	}

	public Adresse getLieferAdresse() {
		return lieferAdresse;
	}

	public void setLieferAdresse(Adresse lieferAdresse) {
		this.lieferAdresse = lieferAdresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {

		return "ID: " + getId() + "\nName: " + getName() + "\nEMail: "
				+ getEmail();
	}
}
