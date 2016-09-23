package de.kiltz.kv.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rechnung implements Serializable {
	private Long id;
	private Integer nummer;

	private Kunde kunde;

	private List<Position> positionen;

	public Rechnung() {
	}

	public Rechnung(Integer nummer) {
		super();
		this.nummer = nummer;
	}

	public void addPositionen(Position position) {
		if (positionen == null) {
			positionen = new ArrayList<Position>();
		}
		positionen.add(position);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNummer() {
		return nummer;
	}

	public void setNummer(Integer nummer) {
		this.nummer = nummer;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public List<Position> getPositionen() {
		return positionen;
	}

	public void setPositionen(List<Position> positionen) {
		this.positionen = positionen;
	}

	@Override
	public String toString() {
		return "Rechnung [id=" + id + ", nummer=" + nummer + ", kunde=" + kunde
				+ ", positionen=" + positionen + "]";
	}

}
