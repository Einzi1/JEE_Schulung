package de.kiltz.seminar.hibernate.basic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");

			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			Benutzer neu = new Benutzer("Testa", "test2@test.de", "geheim");

			em.persist(neu);

			neu.setEmail("neu@test.de");

			System.out.println("Neue ID: " + neu.getId());
			Benutzer b = (Benutzer) em.find(Benutzer.class, neu.getId());
			System.out.println(b);

			// em.remove(neu);
			em.getTransaction().commit();

			em.close();
			emf.close();
		} catch (Throwable e) {
			e.printStackTrace();

		}
	}

}
