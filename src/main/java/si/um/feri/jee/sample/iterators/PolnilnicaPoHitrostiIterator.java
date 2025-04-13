package si.um.feri.jee.sample.iterators;

import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import java.util.Iterator;
import java.util.List;

public class PolnilnicaPoHitrostiIterator implements Iterator<ElektricnaPolnilnica> {
    private final Iterator<ElektricnaPolnilnica> iterator;

    public PolnilnicaPoHitrostiIterator(List<ElektricnaPolnilnica> polnilnice, double minHitrost) {
        this.iterator = polnilnice.stream()
                .filter(p -> p.getHitrostPolnjenja() >= minHitrost)
                .iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public ElektricnaPolnilnica next() {
        return iterator.next();
    }
}
