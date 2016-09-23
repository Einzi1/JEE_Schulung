package de.kiltz.kv.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Kunde implements Serializable {
	private Long id;
	private String name;

	public Kunde() {
	}

	public Kunde(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Kunde [id=" + id + ", name=" + name + "]";
	}

}
