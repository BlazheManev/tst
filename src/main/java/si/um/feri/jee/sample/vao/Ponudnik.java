package si.um.feri.jee.sample.vao;

import java.util.ArrayList;
import java.util.List;

public class Ponudnik {
    private String ime;
    private String naslov;
    private List<ElektricnaPolnilnica> polnilnice;

    public Ponudnik(String ime, String naslov) {
        this.ime = ime;
        this.naslov = naslov;
        this.polnilnice = new ArrayList<>();
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
    }

    public void odstraniPolnilnico(ElektricnaPolnilnica polnilnica) {
        polnilnice.remove(polnilnica);
    }

    @Override
    public String toString() {
        return "Ponudnik{" + "ime='" + ime + '\'' + ", naslov='" + naslov + '\'' + ", polnilnice=" + polnilnice + '}';
    }

}
