package rest.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import rest.lieferanten.Lieferant;

@Provider
@Produces("application/de.traudel.lieferanten+xml")
public class LieferantWriter implements MessageBodyWriter<Lieferant> {

    /**
     * Called before writeTo to ascertain the length in bytes of 
     * the serialized form of t. A non-negative return value is used 
     * in a HTTP Content-Length header.
     */
    public long getSize(Lieferant arg0, Class<?> arg1, Type arg2,
            Annotation[] arg3, MediaType arg4) {
        return -1;
    }

    /**
     * Ascertain if the MessageBodyWriter supports a particular type.
     */
    public boolean isWriteable(Class<?> lieferant, Type type,
            Annotation[] annotations, MediaType mediaType) {
        return Lieferant.class == type;
    }

    /**
     * Die Ausgabe eines Lieferanten mit StAX.
     */
    public void writeTo(Lieferant lieferant, Class<?> type,
            Type genericType,
            Annotation[] annotations, 
            MediaType mediaType, 
            MultivaluedMap<String, Object> httpHeaders,
            OutputStream out) throws IOException,
            WebApplicationException {

        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer;
        try {
            writer = factory.createXMLStreamWriter(out);

            writer.writeStartDocument();

            writer.writeStartElement("lieferant");
            writer.writeAttribute("nr", lieferant.getNr());
            writer.writeAttribute("id", ""+lieferant.getId());

            writer.writeStartElement("name");
            writer.writeCharacters(lieferant.getName());
            writer.writeEndElement();

            writer.writeStartElement("email");
            writer.writeCharacters(lieferant.getEmail());
            writer.writeEndElement();

            writer.writeEndDocument();
            writer.close();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
