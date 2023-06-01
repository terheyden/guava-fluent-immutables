package com.terheyden.guava;

import java.util.stream.Collector;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

/**
 * Helpful collectors for streaming into Guava immutable collections.
 * Note that some of these aren't needed after Guava 21.0.
 */
public final class ImmutableCollectors {

    private ImmutableCollectors() {
        // Private since this class shouldn't be instantiated.
    }

    public static <T> Collector<T, Builder<T>, ImmutableList<T>> toImmutableList() {
        return new ImmutableListCollector<>();
    }

    public static <T> Collector<T, Builder<T>, FluentImmutableList<T>> toFluentImmutableList() {
        return new FluentImmutableListCollector<>();
    }
}
