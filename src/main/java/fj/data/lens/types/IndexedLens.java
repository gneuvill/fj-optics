package fj.data.lens.types;

import fj.data.lens.internal.Indexed;
import fj.data.profunctor.Strong;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__5;

/**
 * An indexed lens.
 */
public interface IndexedLens<I, S, T, A, B> extends __5<IndexedLens.µ, I, S, T, A, B> {
  enum µ {}

  <p> __2<p, S, T> f(Strong<p> S, __<__<__<__<Indexed.µ, p>, I>, A>, B> idx);

  default <p> IndexedOptic<p, I, S, T, A, B> optic(Strong<p> S) {
    return idx -> f(S, idx);
  }

  static <I, S, A> IndexedLens_<I, S, A> mono(IndexedLens<I, S, S, A, A> idx) {
    return idx::f;
  }
}
