package si.um.feri.jee.sample.chainOfResponsability;

import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Uporabnik;

public class PreveriRazpolozljivost implements PolnjenjeHandler {

    private PolnjenjeHandler naslednji;

    @Override
    public void nastaviNaslednjega(PolnjenjeHandler naslednji) {
        this.naslednji = naslednji;
    }

    @Override
    public void obdelaj(Uporabnik uporabnik, ElektricnaPolnilnica polnilnica) {
        if (polnilnica.getCurrentUserEmail() != null) {
            System.out.println("Polnilnica je že zasedena.");
            return;
        }
        if (naslednji != null) {
            naslednji.obdelaj(uporabnik, polnilnica);
        }
    }
}
