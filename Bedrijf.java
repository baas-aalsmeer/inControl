import java.util.HashSet;
/**
 * @author Geoffrey Pathuis 
 * @version 1.0
 */
public class Bedrijf
{
    private HashSet<Klant> klanten;
    private static final int VOORGERECHT = 3;
    private static final int HOOFDGERECHT = 5;
    private static final int NAGERECHT = 2;
    
    public Bedrijf()
    {
        klanten = new HashSet<Klant>();
    }

    /**
     * @param klant Gemaakte objecten klant toevoegen aan het bedrijf
     */
    public void addKlant(Klant klant)
    {
        klanten.add(klant);
    }
    
    public int getTotaleOmzet(int beginDDMMYYYY, int eindDDMMYYYY)
    {
        int voorgerechten = 0;
        int hoofdgerechten = 0;
        int nagerechten = 0;
        int beginDag = beginDDMMYYYY/1000000;
        int eindDag = eindDDMMYYYY/1000000;
        int beginMaand = beginDDMMYYYY/10000 % 100;
        int eindMaand = eindDDMMYYYY/10000 % 100;
        int beginJaar = beginDDMMYYYY % 10000;
        int eindJaar = eindDDMMYYYY % 10000;
        for (Klant klant:klanten)
        {
            if (klant.getYear() >= beginJaar && klant.getYear() <= eindJaar)
            {
                if (klant.getMonth() >= beginMaand && klant.getMonth() <= eindMaand)
                {
                    if (klant.getDay() >= beginDag && klant.getDay() <= eindDag)
                    {
                        voorgerechten += klant.getVoorgerechten();
                        hoofdgerechten += klant.getHoofdgerechten();
                        nagerechten += klant.getNagerechten();
                    }
                }
            }
        }
        int totaleOmzet = voorgerechten * VOORGERECHT + hoofdgerechten * HOOFDGERECHT + nagerechten * NAGERECHT;
        return totaleOmzet;
    }
    
    /**
     * @param ddmmyyyy Hier moet de datum ingevoerd worden van de dag waarvan de gemiddelde omzet uitgerekend moet worden
     */
    public int getGemiddeldeOmzet(int ddmmyyyy)
    {
        int voorgerechten = 0;
        int hoofdgerechten = 0;
        int nagerechten = 0;
        int aantal = 0;
        for (Klant klant:klanten)
        {
            if (klant.getDDMMYYYY() == ddmmyyyy)
            {
                aantal ++;
                voorgerechten += klant.getVoorgerechten();
                hoofdgerechten += klant.getHoofdgerechten();
                nagerechten += klant.getNagerechten();
            }
        }
        int totaleOmzet = voorgerechten * VOORGERECHT + hoofdgerechten * HOOFDGERECHT + nagerechten * NAGERECHT;
        if(aantal != 0)
        {
            int gemiddeldeOmzet = totaleOmzet / aantal;
            return gemiddeldeOmzet;
        }
        else
        {
            return 0;
        }
    }

    /**
     * @param beginDDMMYYYY Hier moet de begindatum ingevoerd worden voor de periode waarvan de naam van de
     * klant met het hoogste bedrag weergegeven moet worden, in het formaat ddmmyyyy
     * @param eindDDMMYYYY Hier moet de einddatum ingevoerd worden voor de periode waarvan de naam van de
     * klant met het hoogste bedrag weergegeven moet worden, in het formaat ddmmyyyy
     */
    public void naamKlantHoogsteBedrag(int beginDDMMYYYY, int eindDDMMYYYY)
    {
        int totaalBedrag = 0;
        int hoogsteBedrag = 0;
        String klantHoogsteBedrag = "";
        int beginDag = beginDDMMYYYY/1000000;
        int eindDag = eindDDMMYYYY/1000000;
        int beginMaand = beginDDMMYYYY/10000 % 100;
        int eindMaand = eindDDMMYYYY/10000 % 100;
        int beginJaar = beginDDMMYYYY % 10000;
        int eindJaar = eindDDMMYYYY % 10000;
        for (Klant klant:klanten)
        {
            if (klant.getYear() >= beginJaar && klant.getYear() <= eindJaar)
            {
                if (klant.getMonth() >= beginMaand && klant.getMonth() <= eindMaand)
                {
                    if (klant.getDay() >= beginDag && klant.getDay() <= eindDag)
                    {
                        totaalBedrag = klant.getVoorgerechten() * VOORGERECHT + klant.getHoofdgerechten() * HOOFDGERECHT + klant.getNagerechten() * NAGERECHT;
                        if (totaalBedrag > hoogsteBedrag)
                        {
                            hoogsteBedrag = totaalBedrag;
                            klantHoogsteBedrag = klant.getName();
                        }
                    }
                }
            }
        }
        System.out.println("De klant die het meest besteed heeft is " + klantHoogsteBedrag + " met een bedrag van " + hoogsteBedrag + " euro\n");
    }
    
    /**
     * @param beginDDMMYYYY Hier moet de begindatum ingevoerd worden voor de periode waarvan de naam van de
     * klant die wel een voorgerecht heeft gehad maar geen nagerecht weergegeven moet worden, in het formaat ddmmyyyy
     * @param eindDDMMYYYY Hier moet de einddatum ingevoerd worden voor de periode waarvan de naam van de
     * klant die wel een voorgerecht heeft gehad maar geen nagerecht weergegeven moet worden, in het formaat ddmmyyyy
     */
    public void klantWelVoorGeenNa(int beginDDMMYYYY, int eindDDMMYYYY)
    {
        boolean voorgerecht = false;
        boolean nagerecht = false;
        HashSet<String> personen = new HashSet<String>();
        int beginDag = beginDDMMYYYY/1000000;
        int eindDag = eindDDMMYYYY/1000000;
        int beginMaand = beginDDMMYYYY/10000 % 100;
        int eindMaand = eindDDMMYYYY/10000 % 100;
        int beginJaar = beginDDMMYYYY % 10000;
        int eindJaar = eindDDMMYYYY % 10000;
        for (Klant klant:klanten)
        {
            if (klant.getYear() >= beginJaar && klant.getYear() <= eindJaar)
            {
                if (klant.getMonth() >= beginMaand && klant.getMonth() <= eindMaand)
                {
                    if (klant.getDay() >= beginDag && klant.getDay() <= eindDag)
                    {
                        if (klant.getVoorgerechten() >= 1 && klant.getNagerechten() <= 0)
                        {
                            personen.add(klant.getName());
                        }
                    }
                }
            }
        }
        System.out.println("De volgende klanten hebben wel een voorgerecht gehad, maar geen nagerecht:");
        for (String persoon:personen)
        {
            System.out.println(persoon);
        }
    }
    
    /**
     * @param beginDDMMYYYY Hier moet de begindatum ingevoerd worden voor de periode waarvan de naam van de
     * klant die hoger dan gemiddeld besteed heeft weergegeven moet worden, in het formaat ddmmyyyy
     * @param eindDDMMYYYY Hier moet de einddatum ingevoerd worden voor de periode waarvan de naam van de
     * klant die hoger dan gemiddeld besteed heeft weergegeven moet worden, in het formaat ddmmyyyy
     */
    public void klantenHogerDanGemiddeld(int beginDDMMYYYY, int eindDDMMYYYY)
    {
        int totaalBedrag = 0;
        HashSet<String> personen = new HashSet<String>();
        int beginDag = beginDDMMYYYY/1000000;
        int eindDag = eindDDMMYYYY/1000000;
        int beginMaand = beginDDMMYYYY/10000 % 100;
        int eindMaand = eindDDMMYYYY/10000 % 100;
        int beginJaar = beginDDMMYYYY % 10000;
        int eindJaar = eindDDMMYYYY % 10000;
        for (Klant klant:klanten)
        {
            if (klant.getYear() >= beginJaar && klant.getYear() <= eindJaar)
            {
                if (klant.getMonth() >= beginMaand && klant.getMonth() <= eindMaand)
                {
                    if (klant.getDay() >= beginDag && klant.getDay() <= eindDag)
                    {
                        totaalBedrag = klant.getVoorgerechten() * VOORGERECHT + klant.getHoofdgerechten() * HOOFDGERECHT + klant.getNagerechten() * NAGERECHT;
                        if (totaalBedrag > getGemiddeldeOmzet(klant.getDDMMYYYY()))
                        {
                            personen.add(klant.getName());
                        }
                    }
                }
            }
        }
        System.out.println("De volgende klanten hebben meer dan het gemiddelde besteed:");
        for (String persoon:personen)
        {
            System.out.println(persoon);
        }
    }
}
