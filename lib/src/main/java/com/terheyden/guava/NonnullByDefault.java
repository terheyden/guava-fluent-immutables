package com.terheyden.guava;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.meta.TypeQualifierDefault;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Declares the following defaults for the entire package:
 * <ul>
 *     <li>Methods don't return null</li>
 *     <li>Method params are not null</li>
 *     <li>Fields are never set to null</li>
 * </ul>
 * <p>
 * Local vars can be null, sure, that's internal biz.
 * <p>
 * This code was copied and modified from {@link ParametersAreNonnullByDefault}.
 * <p>
 * See also:
 * package-info.java
 * <a href="https://stackoverflow.com/questions/13310994/set-findbugs-notnull-as-default-for-all-classes-under-a-package">Stack Overflow 1</a>
 * <a href="https://stackoverflow.com/questions/3550292/what-do-java-annotation-elementtype-constants-mean">Stack Overflow 2</a>
 */
@Documented
@Nonnull
@TypeQualifierDefault({ ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NonnullByDefault {
    // No code goes here.
    /*
    Gradle dependencies:

    compileOnly("com.github.spotbugs:spotbugs-annotations:4.+") {
        // https://mvnrepository.com/artifact/com.github.spotbugs/spotbugs-annotations
        because("Many useful annotations")
    }
    */
}
