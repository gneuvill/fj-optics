package fj.data.lens.types;

import fj.F;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;

public interface Setter_<S, A> extends __2<Setter_.µ, S, A> {
  enum µ {}

  F<S, S> f(__<__<F.µ, A>, A> pab);

  default Optic_<F.µ, S, A> optic() {
    return this::f;
  }

  default Setter<S, S, A, A> poly() {
    return this::f;
  }
}
