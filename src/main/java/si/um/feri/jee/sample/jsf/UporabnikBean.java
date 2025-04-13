package si.um.feri.jee.sample.jsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.jee.sample.service.UporabnikService;
import si.um.feri.jee.sample.vao.Uporabnik;

import java.io.Serializable;
import java.util.List;

@Named("uporabnikBean")
@SessionScoped
public class UporabnikBean implements Serializable {

    // === Service ===
    private final UporabnikService uporabnikService = new UporabnikService();

    // === Input Fields ===
    private String ime;
    private String email;
    private double stanje;
    private String tipVozila;

    // === Selection ===
    private String selectedEmail;

    // === CRUD ===

    public void dodajUporabnika() {
        uporabnikService.dodajUporabnika(ime, email, stanje, tipVozila);
        resetForm();
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
}
