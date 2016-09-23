package rest.lieferanten;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dummy-Implementation
 * 
 * @author Friedrich Kiltz
 *
 */
public class LieferantenServiceImpl implements LieferantenService{
    
    private static Map<String, Lieferant> lieferanten;
    
    static {
        lieferanten = new HashMap<String, Lieferant>();
        Lieferant l = new Lieferant("l321", "COWBOY FARMS LTD");
        l.setRechnungsAdresse(new Adresse("Kariou 10", "", "Larnaca- Cyprus" ));
        l.setLieferAdresse(new Adresse("Kariou 10", "", "Larnaca- Cyprus" ));
        l.setEmail("info@challoumi.com");
        l.setUrl("http://www.challoumi.com/");
        l.setId(1);
        lieferanten.put(l.getNr(), l);
    }

    public void delete(Integer id) throws Exception {
        Lieferant l = getById(id);
        lieferanten.remove(l.getNr());
    }

    public Lieferant getById(Integer id) throws Exception {
        Lieferant ret = null;
        for (Lieferant l : lieferanten.values())
        {
            if (l.getId().equals(id))
            {
                ret = l;
            }
        }
        
        return ret;
    }

    public List<Lieferant> getByName(String name) throws Exception {
        List<Lieferant> ret = new ArrayList<Lieferant>();
        for (Lieferant l : lieferanten.values())
        {
            if (l.getName().toLowerCase().indexOf(name.toLowerCase()) != -1)
            {
                ret.add(l);
            }
        }
        return ret;
    }

    public void create(Lieferant l) throws Exception {
        if (lieferanten.get(l.getNr()) != null)
        {
            throw new Exception("Lieferant mit Nr "+l.getNr()+" ist schon vorhanden!");
        }
        l.setId(lieferanten.size()+1);
        lieferanten.put(l.getNr(), l);
        
    }

    public void update(Integer id, Lieferant l) throws Exception {
        Lieferant alt = getById(id);
        if (alt == null)
        {
            throw new Exception("unbekannte ID "+id);
        }
        if (!alt.getNr().equals(l.getNr()))
        {
            lieferanten.remove(alt.getNr());
        }
        lieferanten.put(l.getNr(), l);
        
    }

    public Lieferant getByNr(String nr) throws Exception {
        if (nr.equals("ex"))
        {
            throw new Exception("Fehler beim lesen!");
        }
        return lieferanten.get(nr);
    }

    
}
