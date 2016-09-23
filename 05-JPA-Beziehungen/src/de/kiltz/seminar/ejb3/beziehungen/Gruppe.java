package de.kiltz.seminar.ejb3.beziehungen;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gruppe")
public class Gruppe  extends SuperEntity{
	private String name;
	
	
	
	public Gruppe() {}
	public Gruppe(String name) {
		setName(name);
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Gruppe [name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
}
