package net.zomis.chess.core;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class UniqueCollector<E> implements Collector<E, UniqueCollector<E>, Optional<E>> {

    private E onlyResult;
    private int found;

    public UniqueCollector() {
        this(null, 0);
    }

    public UniqueCollector(E onlyResult, int found) {
        this.onlyResult = onlyResult;
        this.found = found;
    }

    @Override
    public Supplier<UniqueCollector<E>> supplier() {
        return UniqueCollector::new;
    }

    @Override
    public BiConsumer<UniqueCollector<E>, E> accumulator() {
        return UniqueCollector::element;
    }

    @Override
    public BinaryOperator<UniqueCollector<E>> combiner() {
        return UniqueCollector::and;
    }

    @Override
    public Function<UniqueCollector<E>, Optional<E>> finisher() {
        return UniqueCollector::finish;
    }

    private Optional<E> finish() {
        return found == 1 ? Optional.of(onlyResult) : Optional.empty();
    }

    private void element(E e) {
        this.found++;
        if (this.found == 1) {
            this.onlyResult = e;
        }
    }

    private UniqueCollector<E> and(UniqueCollector<E> other) {
        int found = this.found + other.found;
        E onlyResult = this.onlyResult;
        if (other.found > 0) {
            onlyResult = other.onlyResult;
        }
        return new UniqueCollector<>(onlyResult, found);
    }

    @Override
    public Set<Collector.Characteristics> characteristics() {
        return EnumSet.noneOf(Characteristics.class);
    }
}
