package fj.data.lens.types;

import fj.F;
import fj.data.lens.internal.Indexed;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__4;

public interface IndexedOptic_<p, I, S, A> extends __4<IndexedOptic_.µ, p, I, S, A> {
  enum µ {}

  __2<p, S, S> f(__<__<__<__<Indexed.µ, p>, I>, A>, A> idx);

  default F<__<__<__<__<Indexed.µ, p>, I>, A>, A>, __2<p, S, S>> toF() {
    return this::f;
  }

  default IndexedOptic<p, I, S, S, A, A> poly() {
    return this::f;
  }
}
