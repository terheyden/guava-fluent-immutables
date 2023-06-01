package com.terheyden.guava;

import java.util.Collection;

/**
 * Implementing classes create immutable copies with element changes using
 * the given methods.
 *
 * @param <T> The type of the implementing class
 * @param <E> The type of the elements in the collection
 */
public interface FluentImmutableWithers<T extends Collection<E>, E> {

    /**
     * Create a new immutable copy "with" the given element added.
     */
    T with(E element);

    /**
     * Create a new immutable copy "with" the given elements added.
     */
    T withAll(E... elements);

    /**
     * Create a new immutable copy "with" the given elements added.
     */
    T withAll(Collection<? extends E> collection);

    /**
     * Create a new immutable copy "without" the given element (it is removed).
     */
    T without(E element);

    /**
     * Create a new immutable copy "without" the given element (it is removed).
     */
    T without(int index);

    /**
     * Create a new immutable copy "without" the given elements (they are removed).
     * <p>
     * Grammatically this should be {@code withoutAny()}
     * but the terms 'any' and 'all' have mixed meanings in our world.
     * For example, 'any' can mean, "choose one of the following and remove it," lol.
     */
    T withoutAll(E... elements);

    /**
     * Create a new immutable copy "without" the given elements (they are removed).
     * <p>
     * Grammatically this should be {@code withoutAny()}
     * but the terms 'any' and 'all' have mixed meanings in our world.
     * For example, 'any' can mean, "choose one of the following and remove it," lol.
     */
    T withoutAll(Collection<E> collection);
}
