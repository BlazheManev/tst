package si.um.feri.jee.sample.chainOfResponsability;


import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Uporabnik;

public interface PolnjenjeHandler {
    void nastaviNaslednjega(PolnjenjeHandler naslednji);
    void obdelaj(Uporabnik uporabnik, ElektricnaPolnilnica polnilnica);
}
