package com.terheyden.guava;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.ImmutableList;

/**
 * The {@code FluentImmutableList} class:
 * <ul>
 *     <li>Extends Guava's {@link ImmutableList}</li>
 *     <li>Adds wither methods ({@code with(newItem)}, {@code without(oldItem)})</li>
 *     <li>Adds {@code empty()}</li>
 *     <li>Mixes in stream functions (e.g. {@code list2 = list1.filter(MyObj::isValid)}</li>
 * </ul>
 *
 * @param <E> The type of the elements in the list
 */
public class FluentImmutableList<E>
    extends ForwardingList<E>
    implements StreamTrait<FluentImmutableList<E>, E>, FluentImmutableWithers<FluentImmutableList<E>, E> {

    private static final ImmutableList<?> EMPTY_IMMUTABLE_LIST = ImmutableList.of();
    private static final FluentImmutableList<?> EMPTY = new FluentImmutableList<>(EMPTY_IMMUTABLE_LIST);

    private final ImmutableList<E> delegate;

    @SuppressWarnings("unchecked")
    private FluentImmutableList() {
        // Just use empty().
        this.delegate = (ImmutableList<E>) EMPTY_IMMUTABLE_LIST;
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public FluentImmutableList(E... elements) {
        this.delegate = elements.length == 0
            ? (ImmutableList<E>) EMPTY_IMMUTABLE_LIST
            : ImmutableList.copyOf(elements);
    }

    public FluentImmutableList(Collection<E> collection) {
        this.delegate = ImmutableList.copyOf(collection);
    }

    public FluentImmutableList(Stream<E> stream) {
        this.delegate = ImmutableList.copyOf(stream.iterator());
    }

    @SafeVarargs
    public static <E> FluentImmutableList<E> of(E... elements) {
        return elements.length == 0
            ? empty()
            : new FluentImmutableList<>(ImmutableList.copyOf(elements));
    }

    public static <E> FluentImmutableList<E> copyOf(Collection<E> collection) {
        return new FluentImmutableList<>(collection);
    }

    public static <E> FluentImmutableList<E> of(Stream<E> stream) {
        return new FluentImmutableList<>(stream);
    }

    @SuppressWarnings("unchecked")
    public static <E> FluentImmutableList<E> empty() {
        return (FluentImmutableList<E>) EMPTY;
    }

    /**
     * Used by the {@link ForwardingList} superclass to delegate all the list methods to.
     */
    @Override
    protected List<E> delegate() {
        return delegate;
    }

    @Override
    public FluentImmutableList<E> with(E element) {
        return new FluentImmutableList<>(ImmutableList.<E>builder()
            .addAll(delegate)
            .add(element)
            .build());
    }

    @Override
    @SafeVarargs
    public final FluentImmutableList<E> withAll(E... elements) {
        return new FluentImmutableList<>(ImmutableList.<E>builder()
            .addAll(delegate)
            .add(elements)
            .build());
    }

    @Override
    public FluentImmutableList<E> withAll(Collection<? extends E> collection) {
        return new FluentImmutableList<>(ImmutableList.<E>builder()
            .addAll(delegate)
            .addAll(collection)
            .build());
    }

    @Override
    public FluentImmutableList<E> without(E element) {

        Stream<E> updatedStream = delegate
            .stream()
            .filter(e -> !Objects.equals(e, element));

        // https://stackoverflow.com/a/29013959
        ImmutableList<E> updatedImmutableList = ImmutableList.copyOf(updatedStream.iterator());
        return new FluentImmutableList<>(updatedImmutableList);
    }

    @Override
    public FluentImmutableList<E> without(int index) {

        Stream<E> updatedStream = delegate
            .stream()
            .filter(e -> !Objects.equals(e, delegate.get(index)));

        // https://stackoverflow.com/a/29013959
        ImmutableList<E> updatedImmutableList = ImmutableList.copyOf(updatedStream.iterator());
        return new FluentImmutableList<>(updatedImmutableList);
    }

    @Override
    @SafeVarargs
    public final FluentImmutableList<E> withoutAll(E... elements) {
        return withoutAll(ImmutableList.copyOf(elements));
    }

    @Override
    public FluentImmutableList<E> withoutAll(Collection<E> collection) {

        Stream<E> updatedStream = delegate
            .stream()
            .filter(e -> !collection.contains(e));

        // https://stackoverflow.com/a/29013959
        ImmutableList<E> updatedImmutableList = ImmutableList.copyOf(updatedStream.iterator());
        return new FluentImmutableList<>(updatedImmutableList);
    }

    @Override
    public FluentImmutableList<E> createFromStream(Stream<? extends E> stream) {
        // https://stackoverflow.com/a/29013959
        return new FluentImmutableList<>(ImmutableList.copyOf(stream.iterator()));
    }

    @Override
    public Stream<E> stream() {
        return super.stream();
    }
}
