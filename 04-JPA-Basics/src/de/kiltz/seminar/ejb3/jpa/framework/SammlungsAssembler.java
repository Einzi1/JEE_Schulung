package de.kiltz.seminar.ejb3.jpa.framework;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import de.kiltz.seminar.ejb3.jpa.domain.Sammlung;
import de.kiltz.seminar.ejb3.jpa.entity.SammlungEntity;

public class SammlungsAssembler {

	public static SammlungEntity toEntity(Sammlung von) {
		SammlungEntity nach = new SammlungEntity();
		kopiere(von, nach);
		
		return nach;
	}
	public static Sammlung toDomain(SammlungEntity von) {
		Sammlung nach = new Sammlung();
		kopiere(von, nach);
		
		return nach;
	}
	// Vorsciht bei zirkularen Bezügen
	private static void kopiere(Object von, Object nach) {
		Field[] felder = von.getClass().getDeclaredFields();
		for (Field f : felder) {
			String name = f.getName();
			try {
				Field feldNach = nach.getClass().getDeclaredField(name);
				if (feldNach != null) {
					feldNach.setAccessible(true);
					f.setAccessible(true);
					feldNach.set(nach, f.get(von));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	public static List<Sammlung> toDomain(List<SammlungEntity> daos) {
		List<Sammlung> ret = new ArrayList<Sammlung>();
		for (SammlungEntity e : daos) {
			ret.add(toDomain(e));
		}
		return ret;
	}


}
