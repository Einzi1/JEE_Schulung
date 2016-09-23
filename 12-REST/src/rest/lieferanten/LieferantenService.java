package rest.lieferanten;

import java.util.List;

public interface LieferantenService {

    public void create(Lieferant l) throws Exception;
    public Lieferant getById(Integer id) throws Exception;
    public Lieferant getByNr(String nr) throws Exception;
    public void update(Integer id, Lieferant l) throws Exception;
    public void delete(Integer id) throws Exception;
    public List<Lieferant> getByName(String name) throws Exception;
    
}
