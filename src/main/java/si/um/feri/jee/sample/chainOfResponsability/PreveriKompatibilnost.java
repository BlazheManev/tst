package si.um.feri.jee.sample.chainOfResponsability;


import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import si.um.feri.jee.sample.vao.Uporabnik;

public class PreveriKompatibilnost implements PolnjenjeHandler {

        private PolnjenjeHandler naslednji;

        @Override
        public void nastaviNaslednjega(PolnjenjeHandler naslednji) {
            this.naslednji = naslednji;
        }

        @Override
        public void obdelaj(Uporabnik uporabnik, ElektricnaPolnilnica polnilnica) {
            if (!polnilnica.isCompatibleWithVehicle(uporabnik.getTipVozila())) {
                System.out.println("Vozilo uporabnika ni kompatibilno s polnilnico.");
                return;
            }
            if (naslednji != null) {
                naslednji.obdelaj(uporabnik, polnilnica);
            } else {
                polnilnica.startCharging(uporabnik.getEmail(), uporabnik.getTipVozila());
                System.out.println("Polnjenje uspešno začeto.");

            }
        }
    }
