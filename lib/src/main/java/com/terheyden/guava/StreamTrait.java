package com.terheyden.guava;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * Adds stream functions to any class with a {@code stream()} method.
 *
 * @param <T> "This" type that is extending this class
 * @param <E> The type of the elements in the stream
 */
public interface StreamTrait<T, E> {

    /**
     * The implementing type must be stremable.
     */
    Stream<E> stream();

    /**
     * Create a new instance from a stream.
     */
    T createFromStream(Stream<? extends E> stream);

    default T filter(Predicate<? super E> predicate) {
        return createFromStream(stream().filter(predicate));
    }

    default T map(Function<? super E, ? extends E> mapper) {
        return createFromStream(stream().map(mapper));
    }

    default T flatMap(Function<? super E, ? extends Stream<? extends E>> mapper) {
        return createFromStream(stream().flatMap(mapper));
    }

    default T distinct() {
        return createFromStream(stream().distinct());
    }

    default T sorted() {
        return createFromStream(stream().sorted());
    }

    default T sorted(Comparator<? super E> comparator) {
        return createFromStream(stream().sorted(comparator));
    }

    default T peek(Consumer<? super E> action) {
        return createFromStream(stream().peek(action));
    }

    default T limit(long maxSize) {
        return createFromStream(stream().limit(maxSize));
    }

    default T skip(long n) {
        return createFromStream(stream().skip(n));
    }

    default void forEachOrdered(Consumer<? super E> action) {
        stream().forEachOrdered(action);
    }

    default E reduce(E identity, BinaryOperator<E> accumulator) {
        return stream().reduce(identity, accumulator);
    }

    default Optional<E> reduce(BinaryOperator<E> accumulator) {
        return stream().reduce(accumulator);
    }

    default <U> U reduce(U identity, BiFunction<U, ? super E, U> accumulator, BinaryOperator<U> combiner) {
        return stream().reduce(identity, accumulator, combiner);
    }

    default <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super E> accumulator, BiConsumer<R, R> combiner) {
        return stream().collect(supplier, accumulator, combiner);
    }

    default <R, A> R collect(Collector<? super E, A, R> collector) {
        return stream().collect(collector);
    }

    default Optional<E> min(Comparator<? super E> comparator) {
        return stream().min(comparator);
    }

    default Optional<E> max(Comparator<? super E> comparator) {
        return stream().max(comparator);
    }

    default boolean anyMatch(Predicate<? super E> predicate) {
        return stream().anyMatch(predicate);
    }

    default boolean allMatch(Predicate<? super E> predicate) {
        return stream().allMatch(predicate);
    }

    default boolean noneMatch(Predicate<? super E> predicate) {
        return stream().noneMatch(predicate);
    }

    default Optional<E> findFirst() {
        return stream().findFirst();
    }

    default Optional<E> findAny() {
        return stream().findAny();
    }
}
