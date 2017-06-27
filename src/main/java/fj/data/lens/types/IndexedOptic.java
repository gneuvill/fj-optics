package fj.data.lens.types;

import fj.F;
import fj.data.lens.internal.Indexed;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__6;

/**
 * An indexed optic.
 */
public interface IndexedOptic<p, I, S, T, A, B> extends __6<IndexedOptic.µ, p, I, S, T, A, B> {
  enum µ {}

  __2<p, S, T> f(__<__<__<__<Indexed.µ, p>, I>, A>, B> idx);

  default F<__<__<__<__<Indexed.µ, p>, I>, A>, B>, __2<p, S, T>> f_() {
    return this::f;
  }

  static <p, I, S, A> IndexedOptic_<p, I, S, A> mono(IndexedOptic<p, I, S, S, A, A> idx) {
    return idx::f;
  }
}
