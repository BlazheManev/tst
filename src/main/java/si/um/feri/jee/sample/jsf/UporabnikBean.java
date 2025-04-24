package si.um.feri.jee.sample.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.jee.sample.service.uporabnik.UporabnikService;
import si.um.feri.jee.sample.service.uporabnik.UporabnikServiceLocal;
import si.um.feri.jee.sample.vao.Uporabnik;

import java.io.Serializable;
import java.util.List;

@Named("uporabnikBean")
@SessionScoped
public class UporabnikBean implements Serializable {

    @EJB
    private UporabnikServiceLocal uporabnikService;
    // === Input Fields ===
    private String ime;
    private String email;
    private double stanje;
    private String tipVozila;
    private String emailZaPotrditevBrisanja;

    // === Selection ===
    private String selectedEmail;
    private Uporabnik izbranUporabnik;

    // === CRUD ===

    public void dodajUporabnika() {
        if (izbranUporabnik == null) {
            uporabnikService.dodajUporabnika(ime, email, stanje, tipVozila);
        } else {
            izbranUporabnik.setIme(ime);
            izbranUporabnik.setStanje(stanje);
            izbranUporabnik.setTipVozila(tipVozila);
            uporabnikService.posodobiUporabnika(izbranUporabnik);
        }
        resetForm();
    }

    public void pripraviZaUrejanje(Uporabnik u) {
        this.izbranUporabnik = u;
        this.ime = u.getIme();
        this.email = u.getEmail();
        this.stanje = u.getStanje();
        this.tipVozila = u.getTipVozila();
    }

    public void izbrisiUporabnika() {
        if (selectedEmail != null) {
            uporabnikService.izbrisiUporabnika(selectedEmail);
        }
    }

    public List<Uporabnik> getVsiUporabniki() {
        return uporabnikService.pridobiVseUporabnike();
    }

    // === Helpers ===

    private void resetForm() {
        ime = "";
        email = "";
        stanje = 0.0;
        tipVozila = "";
        izbranUporabnik = null;
    }

    public void potrdiBrisanjeUporabnika(String email) {
        this.emailZaPotrditevBrisanja = email;
    }

    public void prekliciBrisanje() {
        this.emailZaPotrditevBrisanja = null;
    }

    public void potrdiBrisanje() {
        if (emailZaPotrditevBrisanja != null) {
            uporabnikService.izbrisiUporabnika(emailZaPotrditevBrisanja);
            emailZaPotrditevBrisanja = null;
        }
    }

    public String getEmailZaPotrditevBrisanja() {
        return emailZaPotrditevBrisanja;
    }
    // === Getters & Setters ===

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getStanje() { return stanje; }
    public void setStanje(double stanje) { this.stanje = stanje; }

    public String getTipVozila() { return tipVozila; }
    public void setTipVozila(String tipVozila) { this.tipVozila = tipVozila; }

    public String getSelectedEmail() { return selectedEmail; }
    public void setSelectedEmail(String selectedEmail) { this.selectedEmail = selectedEmail; }

    public Uporabnik getIzbranUporabnik() { return izbranUporabnik; }
    public void setIzbranUporabnik(Uporabnik izbranUporabnik) { this.izbranUporabnik = izbranUporabnik; }
}
