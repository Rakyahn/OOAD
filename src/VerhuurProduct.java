import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VerhuurProduct {
    private static List<VerhuurProduct> alleVerhuurProducten = new ArrayList<>();
    private double verhuurprijs;
    private double borg;
    private List<Exemplaar> exemplaren;
    public VerhuurProduct(double verhuurprijs, double borg) {
        this.verhuurprijs = verhuurprijs;
        this.borg = borg;
        this.exemplaren = new ArrayList<>();
        alleVerhuurProducten.add(this);
    }
    public static List<VerhuurProduct> getAlleVerhuurProducten() {
        return alleVerhuurProducten;
    }

    public String geefOmschrijving() {
        StringBuilder omschrijving = new StringBuilder();
        omschrijving.append("Verhuurproduct: ").append(this.verhuurprijs).append(" euro, borg: ").append(this.borg).append(" euro\n");
        omschrijving.append("Exemplaren:\n");
        for (Exemplaar exemplaar : this.exemplaren) {
            omschrijving.append(exemplaar.geefOmschrijving()).append("\n");
        }
        return omschrijving.toString();
    }
    public List<Exemplaar> geefBeschikbareExemplaren(){
        List<Exemplaar> beschikbareExemplaren = new ArrayList<>();
        for(Exemplaar exemplaar : this.exemplaren){
            if (!exemplaar.isVerhuurd()){
                beschikbareExemplaren.add(exemplaar);
            }
        }
        return beschikbareExemplaren;
    }
    public void geefDetails() {
        for (Exemplaar exemplaar : this.exemplaren) {
            System.out.println(exemplaar.geefOmschrijving());
            if (exemplaar.isVerhuurd()) {
                VerhuurRegel verhuurRegel = exemplaar.getVerhuurRegel();
                System.out.println("Verhuurd tot: " + verhuurRegel.getEindDatum());
                System.out.println("Periode: " + verhuurRegel.geefPeriode() + " dagen");
            }
        }
    }
    public boolean isVerhuurd() {
        for (Exemplaar exemplaar : this.exemplaren) {
            if (exemplaar.isVerhuurd()) {
                return true;
            }
        }
        return false;
    }
}

class Exemplaar {
    private LocalDate aanschafDatum;
    private String serieNr;
    private boolean isVerhuurd;
    private VerhuurRegel verhuurRegel;

    public Exemplaar(String serieNr, LocalDate aanschafDatum) {
        this.serieNr = serieNr;
        this.aanschafDatum = aanschafDatum;
        this.isVerhuurd = false;
    }

    public void setVerhuurRegel(VerhuurRegel verhuurRegel) {
        this.verhuurRegel = verhuurRegel;
        this.isVerhuurd = true;
    }

    public String geefOmschrijving() {
        StringBuilder omschrijving = new StringBuilder();
        omschrijving.append("Serie nummer: ").append(this.serieNr).append(", aanschaf datum: ").append(this.aanschafDatum);
        if (this.isVerhuurd) {
            omschrijving.append(", verhuurd tot: ").append(this.verhuurRegel.getEindDatum());
        } else {
            omschrijving.append(", beschikbaar");
        }
        return omschrijving.toString();
    }

    public VerhuurRegel getVerhuurRegel() {
        return verhuurRegel;
    }

    public boolean isVerhuurd() {
        return this.verhuurRegel != null;
    }
}

class VerhuurRegel {
    private LocalDate eindDatum;
    private int aantalDagen;
    private Exemplaar exemplaar;


    public VerhuurRegel(LocalDate eindDatum, int aantalDagen, Exemplaar exemplaar) {
        this.eindDatum = eindDatum;
        this.aantalDagen = aantalDagen;
        this.exemplaar = exemplaar;
        this.exemplaar.setVerhuurRegel(this);
    }
    public LocalDate getEindDatum() {
        return eindDatum;
    }

    public String geefPeriode() {
        List<VerhuurRegel> verhuurRegels = new ArrayList<>();
        for (VerhuurRegel regel : verhuurRegels) {
            System.out.println("Huurperiode: " + regel.geefPeriode());

        }

    return geefPeriode();
    }

}