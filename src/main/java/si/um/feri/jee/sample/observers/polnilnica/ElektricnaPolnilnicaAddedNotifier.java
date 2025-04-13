package si.um.feri.jee.sample.observers.polnilnica;

import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Ponudnik;

public class ElektricnaPolnilnicaAddedNotifier implements ElektricnaPolnilnicaObserver {
        @Override
        public void update(Ponudnik ponudnik, ElektricnaPolnilnica polnilnica, String action) {
            if ("added".equals(action)) {
                System.out.println("Obvestilo: " + ponudnik.getIme() + " je dodal novo polnilnico: " + polnilnica);
            }}
}

