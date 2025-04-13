package si.um.feri.jee.sample.observers.polnilnica;


import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Ponudnik;

public class ElektricnaPolnilnicaRemovedNotifier implements ElektricnaPolnilnicaObserver {
    @Override
    public void update(Ponudnik ponudnik, ElektricnaPolnilnica polnilnica, String action) {
        if ("removed".equals(action)) {
            System.out.println("Obvestilo: " + ponudnik.getIme() + " je odstranil polnilnico: " + polnilnica);
        }
    }
}



