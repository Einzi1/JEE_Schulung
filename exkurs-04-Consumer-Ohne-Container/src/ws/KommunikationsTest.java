package ws;

/**
 * Dynamischer Proxy mit der Klasse Service.
 * 
 * @author fk
 *
 */
public class KommunikationsTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		KommunikationService service = new KommunikationService();
		Kommunikation k = service.getKommunikationPort();
		System.out.println(k.ping("kleiner Test"));
	}
}
