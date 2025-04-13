package si.um.feri.jee.sample.chainOfResponsability;

import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Uporabnik;

public class PreveriStanje implements PolnjenjeHandler {

    private PolnjenjeHandler naslednji;

    @Override
    public void nastaviNaslednjega(PolnjenjeHandler naslednji) {
        this.naslednji = naslednji;
    }

    @Override
    public void obdelaj(Uporabnik uporabnik, ElektricnaPolnilnica polnilnica) {
        if (uporabnik.getStanje() < polnilnica.getCenaPolnjenja()) {
            System.out.println(" Uporabnik nima dovolj sredstev na računu.");
            return;
        }
        if (naslednji != null) {
            naslednji.obdelaj(uporabnik, polnilnica);
        }
    }
}
