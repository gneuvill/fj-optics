package fj.data.lens.types;

import fj.F;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__3;

public interface Optic_<p, S, A> extends __3<Optic_.µ, p, S, A> {
  enum µ {}

  __2<p, S, S> f(__<__<p, A>, A> paa);

  default F<__<__<p, A>, A>, __2<p, S, S>> f_() {
    return this::f;
  }

  default Optic<p, S, S, A, A> poly() {
    return this::f;
  }
}
