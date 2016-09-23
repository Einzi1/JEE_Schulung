package de.kiltz.valid;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DieBean {

	@Size(min=5)
	@NotNull
	private String email; 
	
	@NotNull
	private String nichtNull;

	@KundenNummerConstraint
	private String kdNr;

	@RegExConstraint("[\\d]{5}")
	private String plz;
	
	public String getNichtNull() {
		return nichtNull;
	}

	public void setNichtNull(String nichtNull) {
		this.nichtNull = nichtNull;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKdNr() {
		return kdNr;
	}

	public void setKdNr(String kdNr) {
		this.kdNr = kdNr;
	}

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }
}
