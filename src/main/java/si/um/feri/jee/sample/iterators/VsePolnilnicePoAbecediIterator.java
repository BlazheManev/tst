package si.um.feri.jee.sample.iterators;

import si.um.feri.jee.sample.vao.ElektricnaPolnilnica;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class VsePolnilnicePoAbecediIterator implements Iterator<ElektricnaPolnilnica> {
    private final Iterator<ElektricnaPolnilnica> iterator;

    public VsePolnilnicePoAbecediIterator(List<ElektricnaPolnilnica> polnilnice) {
        polnilnice.sort(Comparator.comparing(ElektricnaPolnilnica::getLokacija));
        this.iterator = polnilnice.iterator();
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
