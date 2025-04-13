package si.um.feri.jee.sample.observers.polnilnica;


import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Ponudnik;

public interface ElektricnaPolnilnicaObserver {
    void update(Ponudnik ponudnik, ElektricnaPolnilnica polnilnica, String action);
}
