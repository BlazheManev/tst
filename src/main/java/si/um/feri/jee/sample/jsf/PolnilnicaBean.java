package si.um.feri.jee.sample.jsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.jee.sample.service.ElektricnaPolnilnicaService;
import si.um.feri.jee.sample.service.PonudnikService;
import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Ponudnik;

import java.io.Serializable;
import java.util.List;

@Named("polnilnica")
@SessionScoped
public class PolnilnicaBean implements Serializable {

    private final ElektricnaPolnilnicaService polnilnicaService = new ElektricnaPolnilnicaService();
    private final PonudnikService ponudnikService = new PonudnikService();

    // === Input Fields ===
    private String ime;
    private String lokacija;
    private double hitrostPolnjenja;
    private boolean active;
    private double cenaPolnjenja;
    private String[] kompatibilnaVozila;

    // === Selection ===
    private String selectedLokacija;
    private String lokacijaZaPotrditevBrisanja;
    private ElektricnaPolnilnica izbranaPolnilnica;

    // === CRUD ===
    public void dodajPolnilnico() {
        if (izbranaPolnilnica == null) {
            // Add new
            polnilnicaService.addElektricnaPolnilnica(ime, lokacija, hitrostPolnjenja, active, cenaPolnjenja, kompatibilnaVozila);
        } else {
            izbranaPolnilnica.setIme(ime);
            izbranaPolnilnica.setLokacija(lokacija);
            izbranaPolnilnica.setHitrostPolnjenja(hitrostPolnjenja);
            izbranaPolnilnica.setCenaPolnjenja(cenaPolnjenja);
            izbranaPolnilnica.setActive(active);
            izbranaPolnilnica.setKompatibilnaVozila(kompatibilnaVozila);

            polnilnicaService.updateElektricnaPolnilnica(izbranaPolnilnica);
        }

        resetForm();
    }

    public void pripraviZaUrejanje(ElektricnaPolnilnica p) {
        this.izbranaPolnilnica = p;
        this.ime = p.getIme();
        this.lokacija = p.getLokacija();
        this.hitrostPolnjenja = p.getHitrostPolnjenja();
        this.cenaPolnjenja = p.getCenaPolnjenja();
        this.active = p.isActive();
        this.kompatibilnaVozila = p.getKompatibilnaVozila();
    }

    public void potrdiBrisanjePolnilnice(String lokacija) {
        this.lokacijaZaPotrditevBrisanja = lokacija;
    }

    public void prekliciBrisanje() {
        this.lokacijaZaPotrditevBrisanja = null;
    }

    public void potrdiBrisanje() {
        if (lokacijaZaPotrditevBrisanja != null) {
            polnilnicaService.deleteElektricnaPolnilnica(lokacijaZaPotrditevBrisanja);
            lokacijaZaPotrditevBrisanja = null;
        }
    }

    public List<ElektricnaPolnilnica> getVsePolnilnice() {
        return polnilnicaService.getAllElektricnaPolnilnici();
    }

    public void izpisiVsePodatkeVKonzolo() {
        System.out.println("=== POLNILNICE ===");
        getVsePolnilnice().forEach(System.out::println);

        System.out.println("=== PONUDNIKI ===");
        ponudnikService.getAllPonudniki().forEach(System.out::println);
    }

    private void resetForm() {
        ime = "";
        lokacija = "";
        hitrostPolnjenja = 0.0;
        cenaPolnjenja = 0.0;
        active = false;
        kompatibilnaVozila = null;
        izbranaPolnilnica = null;
    }

    // === Getters & Setters ===

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    public String getLokacija() { return lokacija; }
    public void setLokacija(String lokacija) { this.lokacija = lokacija; }

    public double getHitrostPolnjenja() { return hitrostPolnjenja; }
    public void setHitrostPolnjenja(double hitrostPolnjenja) { this.hitrostPolnjenja = hitrostPolnjenja; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public double getCenaPolnjenja() { return cenaPolnjenja; }
    public void setCenaPolnjenja(double cenaPolnjenja) { this.cenaPolnjenja = cenaPolnjenja; }

    public String[] getKompatibilnaVozila() { return kompatibilnaVozila; }
    public void setKompatibilnaVozila(String[] kompatibilnaVozila) { this.kompatibilnaVozila = kompatibilnaVozila; }

    public String getSelectedLokacija() { return selectedLokacija; }
    public void setSelectedLokacija(String selectedLokacija) { this.selectedLokacija = selectedLokacija; }

    public ElektricnaPolnilnica getIzbranaPolnilnica() { return izbranaPolnilnica; }
    public void setIzbranaPolnilnica(ElektricnaPolnilnica izbranaPolnilnica) { this.izbranaPolnilnica = izbranaPolnilnica; }

    public String getLokacijaZaPotrditevBrisanja() { return lokacijaZaPotrditevBrisanja; }
    public void setLokacijaZaPotrditevBrisanja(String lokacijaZaPotrditevBrisanja) { this.lokacijaZaPotrditevBrisanja = lokacijaZaPotrditevBrisanja; }
}
