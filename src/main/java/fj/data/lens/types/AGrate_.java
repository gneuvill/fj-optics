package fj.data.lens.types;

import fj.data.lens.internal.Grating;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;

public interface AGrate_<S, A> extends __2<AGrate_.µ, S, A> {
  enum µ {}

  Grating<A, A, S, S> f(__<__<__<__<Grating.µ, A>, A>, A>, A> pab);

  default Optic_<__<__<Grating.µ, A>, A>, S, A> optic() {
    return this::f;
  }

  default AGrate<S, S, A, A> poly() {
    return this::f;
  }
}