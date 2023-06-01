package com.terheyden.guava;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableSet;

/**
 * For collecting a stream into a {@link FluentImmutableList}.
 */
public class FluentImmutableListCollector<T> implements Collector<T, Builder<T>, FluentImmutableList<T>> {

    @Override
    public Supplier<Builder<T>> supplier() {
        return Builder::new;
    }

    @Override
    public BiConsumer<Builder<T>, T> accumulator() {
        return Builder::add;
    }

    @Override
    public BinaryOperator<Builder<T>> combiner() {
        return (b1, b2) -> b1.addAll(b2.build());
    }

    @Override
    public Function<Builder<T>, FluentImmutableList<T>> finisher() {
        return builder -> new FluentImmutableList<>(builder.build());
    }

    @Override
    public Set<Characteristics> characteristics() {
        return ImmutableSet.of();
    }
}
