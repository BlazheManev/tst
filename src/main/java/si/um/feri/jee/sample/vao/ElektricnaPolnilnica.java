package si.um.feri.jee.sample.vao;


import si.um.feri.jee.sample.observers.polnilnica.ElektricnaPolnilnicaObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElektricnaPolnilnica {
    private String ime;
    private String lokacija;
    private Ponudnik ponudnik; // Referenca na ponudnika
    private double hitrostPolnjenja;
    private boolean active;
    private String currentUserEmail;
    private double cenaPolnjenja;
    private List<ElektricnaPolnilnicaObserver> observers = new ArrayList<>();
    private String[] kompatibilnaVozila;


    // Constructor modified to accept an array of compatible vehicles
    public ElektricnaPolnilnica(String ime, String lokacija, Ponudnik ponudnik, double hitrostPolnjenja, boolean active, String currentUserEmail, double cenaPolnjenja, String[] kompatibilniTipVozila) {
        this.ime = ime;
        this.lokacija = lokacija;
        this.ponudnik = ponudnik;
        this.hitrostPolnjenja = hitrostPolnjenja;
        this.active = active;
        this.currentUserEmail = currentUserEmail;
        this.cenaPolnjenja = cenaPolnjenja;
        this.kompatibilnaVozila = kompatibilniTipVozila;
    }

    public ElektricnaPolnilnica() {

    }

    public String[] getKompatibilnaVozila() {
        return kompatibilnaVozila;
    }

    public boolean isCompatibleWithVehicle(String userVehicleType) {
        return Arrays.asList(this.kompatibilnaVozila).contains(userVehicleType);
    }

    public double getCenaPolnjenja() {
        return cenaPolnjenja;
    }

    public void setCenaPolnjenja(double cenaPolnjenja) {
        this.cenaPolnjenja = cenaPolnjenja;
    }

    public void startCharging(String userEmail, String tipVozila) {
        if (currentUserEmail == null) {
                setCurrentUserEmail(userEmail);
                notifyObservers(this, "occupied");
        } else {
            System.out.println("Polnilnica je že zasedena.");
        }
    }

    public void stopCharging() {
        if (currentUserEmail != null) {
            notifyObservers(this, "free");
            this.currentUserEmail = null;
        } else {
            System.out.println("Polnilnica je že prosta.");
        }
    }

    public void addObserver(ElektricnaPolnilnicaObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ElektricnaPolnilnicaObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(ElektricnaPolnilnica elektricnaPolnilnica, String action) {
        for (ElektricnaPolnilnicaObserver observer : observers) {
            observer.update(this.getPonudnik(), elektricnaPolnilnica, action);
        }
    }

    public String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public void setCurrentUserEmail(String currentUserEmail) {
        this.currentUserEmail = currentUserEmail;
    }

    public double getHitrostPolnjenja() {
        return hitrostPolnjenja;
    }

    public boolean isActive() {
        return active;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Ponudnik getPonudnik() {
        return ponudnik;
    }

    public void setPonudnik(Ponudnik ponudnik) {
        this.ponudnik = ponudnik;
    }

    @Override
    public String toString() {
        return "ElektricnaPolnilnica{" +
                "lokacija='" + lokacija + '\'' +
                ", ponudnik=" + (ponudnik != null ? ponudnik.getIme() : "ni dodeljen") +
                ", hitrostPolnjenja=" + hitrostPolnjenja +
                ", active=" + active +
                '}';
    }


    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setHitrostPolnjenja(double hitrostPolnjenja) {
        this.hitrostPolnjenja = hitrostPolnjenja;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<ElektricnaPolnilnicaObserver> getObservers() {
        return observers;
    }

    public void setObservers(List<ElektricnaPolnilnicaObserver> observers) {
        this.observers = observers;
    }

    public void setKompatibilnaVozila(String[] kompatibilnaVozila) {
        this.kompatibilnaVozila = kompatibilnaVozila;
    }
}
