# Guava: Fluent Immutables

```java
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
```

## Overview

This library extends Guava Immutable Collections with a few extra features:

- Adds "wither" methods to all immutable collections, e.g.:
  - `with(newItem)`
  - `without(oldItem)`
- Adds a static `empty()` method to all immutable types
- Adds in stream functions
  - e.g. `list2 = list1.filter(MyObj::isValid)`

Additionally, for versions of Guava older than version `21.0`, this library also adds
`Collectors` for streaming into immutable collections.
