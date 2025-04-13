package si.um.feri.jee.sample.vao;

public class Uporabnik {
    private String ime;
    private String email;
    private double stanje;
    private String tipVozila;

    public Uporabnik(String ime, String email, double stanje, String tipVozila) {
        this.ime = ime;
        this.email = email;
        this.stanje = stanje;
        this.tipVozila = tipVozila;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getStanje() {
        return stanje;
    }

    public void setStanje(double stanje) {
        this.stanje = stanje;
    }

    public String getTipVozila() {
        return tipVozila;
    }

    public void setTipVozila(String tipVozila) {
        this.tipVozila = tipVozila;
    }

}
