package de.kiltz.kv.ws;

public class KundeXML {
	private Long id;
	private String name;
	public KundeXML() {
		
	}
	
	
	public KundeXML(String name) {
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

	
}
