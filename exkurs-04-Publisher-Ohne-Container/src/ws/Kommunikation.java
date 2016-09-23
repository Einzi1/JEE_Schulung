package ws;

import javax.jws.WebService;

/**
 * 
 * Das Service Endpoint Interface (SEI)
 * 
 * @author fk
 *
 */
@WebService
public interface Kommunikation {

	public String ping(String txt);
}
