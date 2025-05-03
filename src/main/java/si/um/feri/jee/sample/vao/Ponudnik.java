package si.um.feri.jee.sample.vao;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ponudnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ime;

    private String naslov;

    @OneToMany(mappedBy = "ponudnik", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ElektricnaPolnilnica> polnilnice = new ArrayList<>();

    public Ponudnik(String ime, String naslov) {
        this.ime = ime;
        this.naslov = naslov;
        this.polnilnice = new ArrayList<>();
    }

    public Ponudnik() {

    }

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

    public List<ElektricnaPolnilnica> getPolnilnice() {
        return polnilnice;
    }

    public void dodajPolnilnico(ElektricnaPolnilnica polnilnica) {
        polnilnice.add(polnilnica);
        polnilnica.setPonudnik(this);
    }

    public void odstraniPolnilnico(ElektricnaPolnilnica polnilnica) {
        polnilnice.remove(polnilnica);
        polnilnica.setPonudnik(null);
    }

    @Override
    public String toString() {
        return "Ponudnik{" +
                "ime='" + ime + '\'' +
                ", naslov='" + naslov + '\'' +
                '}';
    }
}
