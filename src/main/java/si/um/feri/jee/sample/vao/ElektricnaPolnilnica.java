package si.um.feri.jee.sample.vao;

import jakarta.persistence.*;
import si.um.feri.jee.sample.observers.polnilnica.ElektricnaPolnilnicaObserver;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ElektricnaPolnilnica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ime;
    private String lokacija;
    private double hitrostPolnjenja;
    private boolean active;
    private String currentUserEmail;
    private double cenaPolnjenja;

    @ManyToOne
    @JoinColumn(name = "ponudnik_id")
    private Ponudnik ponudnik;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "elektricnapolnilnica_kompatibilnavozila",
            joinColumns = @JoinColumn(name = "elektricna_polnilnica_id")
    )
    @Column(name = "kompatibilnaVozila")
    private List<String> kompatibilnaVozila = new ArrayList<>();

    @Transient
    private List<ElektricnaPolnilnicaObserver> observers = new ArrayList<>();

    public ElektricnaPolnilnica() {}

    public ElektricnaPolnilnica(String ime, String lokacija, Ponudnik ponudnik,
                                double hitrostPolnjenja, boolean active,
                                String currentUserEmail, double cenaPolnjenja,
                                List<String> kompatibilnaVozila) {
        this.ime = ime;
        this.lokacija = lokacija;
        this.ponudnik = ponudnik;
        this.hitrostPolnjenja = hitrostPolnjenja;
        this.active = active;
        this.currentUserEmail = currentUserEmail;
        this.cenaPolnjenja = cenaPolnjenja;
        this.kompatibilnaVozila = kompatibilnaVozila;
    }

    public boolean isCompatibleWithVehicle(String userVehicleType) {
        return kompatibilnaVozila != null && kompatibilnaVozila.contains(userVehicleType);
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

    // Getters and Setters

    public Long getId() { return id; }

    public String getIme() { return ime; }

    public void setIme(String ime) { this.ime = ime; }

    public String getLokacija() { return lokacija; }

    public void setLokacija(String lokacija) { this.lokacija = lokacija; }

    public double getHitrostPolnjenja() { return hitrostPolnjenja; }

    public void setHitrostPolnjenja(double hitrostPolnjenja) { this.hitrostPolnjenja = hitrostPolnjenja; }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public String getCurrentUserEmail() { return currentUserEmail; }

    public void setCurrentUserEmail(String currentUserEmail) { this.currentUserEmail = currentUserEmail; }

    public double getCenaPolnjenja() { return cenaPolnjenja; }

    public void setCenaPolnjenja(double cenaPolnjenja) { this.cenaPolnjenja = cenaPolnjenja; }

    public Ponudnik getPonudnik() { return ponudnik; }

    public void setPonudnik(Ponudnik ponudnik) { this.ponudnik = ponudnik; }

    public List<String> getKompatibilnaVozila() { return kompatibilnaVozila; }

    public void setKompatibilnaVozila(List<String> kompatibilnaVozila) {
        this.kompatibilnaVozila = kompatibilnaVozila;
    }

    public List<ElektricnaPolnilnicaObserver> getObservers() { return observers; }

    public void setObservers(List<ElektricnaPolnilnicaObserver> observers) {
        this.observers = observers;
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
}
