package com.example.transformer;

/**
 * This represents the transformer contract to transform domain objects into
 * data transfer objects.
 *
 * @param <S>
 *            source to be transformed.
 * @param <T>
 *            result after the transformation.
 */
public interface Transformer<S, T> {
	T transform(S source);

}
