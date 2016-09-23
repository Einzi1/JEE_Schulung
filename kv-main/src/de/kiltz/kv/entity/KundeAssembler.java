package de.kiltz.kv.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import de.kiltz.kv.domain.Kunde;

public class KundeAssembler {

	public static KundeEntity toEntity(Kunde von) {
		KundeEntity nach = new KundeEntity();
		kopiere(von, nach);
		
		return nach;
	}
	public static Kunde toDomain(KundeEntity von) {
		Kunde nach = new Kunde();
		kopiere(von, nach);
		return nach;
	}
	public static List<Kunde> toDomainList(List<KundeEntity> von) {
		List<Kunde> nach = new ArrayList<Kunde>();
		
		for (KundeEntity e : von) {
			Kunde k = new Kunde();
			kopiere(e, k);
			nach.add(k);
		}
		return nach;
	}
	public static List<KundeEntity> toEntityList(List<Kunde> von) {
		List<KundeEntity> nach = new ArrayList<KundeEntity>();
		
		for (Kunde k : von) {
			KundeEntity e = new KundeEntity();
			kopiere(k, e);
			nach.add(e);
		}
		return nach;
	}

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


}
