package rest.lieferanten;

public class EdelFactory {
    private static LieferantenService lieferantenService;
    
    public static LieferantenService getLieferantenService() {
        if (lieferantenService == null)
        {
            lieferantenService = new LieferantenServiceImpl();
        }
        return lieferantenService;
    }

}
