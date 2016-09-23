package de.kiltz.kv.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;

import de.kiltz.kv.domain.Position;
import de.kiltz.kv.domain.Rechnung;

public class RechnungsAssembler {

	public static RechnungEntity toEntity(Rechnung von) {
		RechnungEntity nach = new RechnungEntity();
		kopiere(von, nach);
		if (von.getKunde() != null) {
			nach.setKunde(KundeAssembler.toEntity(von.getKunde()));
		}
		if (von.getPositionen() != null) {
			nach.setPosition(new ArrayList<PositionEntity>());
			for (Position p : von.getPositionen()) {
				nach.addPositionen(toEntity(p));
			}
		}
		return nach;
	}

	public static Rechnung toDomain(RechnungEntity von) {
		Rechnung nach = new Rechnung();
		kopiere(von, nach);
		if (von.getKunde() != null) {
			nach.setKunde(KundeAssembler.toDomain(von.getKunde()));
		}
		if (von.getPosition() != null) {
			nach.setPositionen(new ArrayList<Position>());
			for (PositionEntity p : von.getPosition()) {
				nach.addPositionen(toDomain(p));
			}
		}
		return nach;
	}

	public static PositionEntity toEntity(Position von) {
		PositionEntity nach = new PositionEntity();
		kopiere(von, nach);

		return nach;
	}

	public static Position toDomain(PositionEntity von) {
		Position nach = new Position();
		kopiere(von, nach);
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
