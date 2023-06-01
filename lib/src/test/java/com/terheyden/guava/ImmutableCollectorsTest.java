package com.terheyden.guava;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ImmutableCollectorsTest unit tests.
 */
class ImmutableCollectorsTest {

    @Test
    void testToImmutableList() {

        ImmutableList<String> list1 = Stream
            .of("a", "b", "c")
            .collect(ImmutableCollectors.toImmutableList());

        assertThat(list1).containsExactly("a", "b", "c");
        assertThat(list1).isInstanceOf(ImmutableList.class);
    }

    @Test
    void testToFluentImmutableList() {

        FluentImmutableList<String> list1 = Stream
            .of("a", "b", "c")
            .collect(ImmutableCollectors.toFluentImmutableList());

        assertThat(list1).containsExactly("a", "b", "c");
        assertThat(list1).isInstanceOf(FluentImmutableList.class);
    }
}
