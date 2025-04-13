package si.um.feri.jee.sample.jsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.jee.sample.service.ElektricnaPolnilnicaService;
import si.um.feri.jee.sample.service.PonudnikService;
import si.um.feri.jee.sample.service.UporabnikService;
import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Ponudnik;
import si.um.feri.jee.sample.vao.Uporabnik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named("ponudnikBean")
@SessionScoped
public class PonudnikBean implements Serializable {

    // === Services ===
    private final PonudnikService ponudnikService = new PonudnikService();
    private final ElektricnaPolnilnicaService polnilnicaService = new ElektricnaPolnilnicaService();
    private final UporabnikService uporabnikService = new UporabnikService();

    // === Input Fields ===
    private String ime;
    private String naslov;
    private List<ElektricnaPolnilnica> izbranePolnilnice = new ArrayList<>();

    // === Selection & UI ===
    private String selectedIme;
    private String izbranEmail;
    private ElektricnaPolnilnica izbranaPolnilnica;

    // === CRUD ===

    public void dodajPonudnika() {
        Ponudnik nov = new Ponudnik(ime, naslov);
        ponudnikService.createPonudnik(nov.getIme(), nov.getNaslov(), izbranePolnilnice);

        ime = "";
        naslov = "";
        izbranePolnilnice.clear();
    }

    public void izbrisiPonudnika() {
        if (selectedIme != null) {
            ponudnikService.deletePonudnik(selectedIme);
        }
    }

    public List<Ponudnik> getVsiPonudniki() {
        return ponudnikService.getAllPonudniki();
    }

    public List<ElektricnaPolnilnica> getProstePolnilnice() {
        return polnilnicaService.getAllElektricnaPolnilnici().stream()
                .filter(p -> p.getPonudnik() == null)
                .toList();
    }

    // === Charging ===

    public void zacniPolnjenjeZaPolnilnico() {
        Uporabnik uporabnik = uporabnikService
                .pridobiUporabnikaPoEmailu(izbranEmail)
                .orElseThrow(() -> new RuntimeException("Uporabnik z emailom " + izbranEmail + " ne obstaja."));

        Optional<ElektricnaPolnilnica> polnilnica = polnilnicaService.getElektricnaPolnilnicaByLokacija(izbranaPolnilnica.getLokacija());

        if (polnilnica.isPresent()) {
            uporabnikService.zacniPolnjenje(uporabnik, polnilnica.get());
            System.out.println("✅ Polnjenje začeto za: " + izbranEmail + " na polnilnici: " + izbranaPolnilnica.getLokacija());
        } else {
            System.out.println("⚠️ Polnilnica ni bila najdena.");
        }
    }

    public List<Uporabnik> getVsiUporabniki() {
        return uporabnikService.pridobiVseUporabnike();
    }

    // === Polnilnica podrobnosti ===

    public ElektricnaPolnilnica getIzbranaPolnilnica() {
        return izbranaPolnilnica;
    }

    // === Getters & Setters ===

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public List<ElektricnaPolnilnica> getIzbranePolnilnice() {
        return izbranePolnilnice;
    }

    public void setIzbranePolnilnice(List<ElektricnaPolnilnica> izbranePolnilnice) {
        this.izbranePolnilnice = izbranePolnilnice;
    }

    public String getSelectedIme() {
        return selectedIme;
    }

    public void setSelectedIme(String selectedIme) {
        this.selectedIme = selectedIme;
    }

    public String getIzbranEmail() {
        return izbranEmail;
    }

    public void setIzbranEmail(String izbranEmail) {
        this.izbranEmail = izbranEmail;
    }

    public void setIzbranaPolnilnica(ElektricnaPolnilnica izbranaPolnilnica) {
        this.izbranaPolnilnica = izbranaPolnilnica;
    }
}
