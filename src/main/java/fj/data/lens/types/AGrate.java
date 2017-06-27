package fj.data.lens.types;

import fj.data.lens.internal.Grating;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__4;

public interface AGrate<S, T, A, B> extends __4<AGrate.µ, S, T, A, B> {
  enum µ {}

  Grating<A, B, S, T> f(__<__<__<__<Grating.µ, A>, B>, A>, B> pab);

  default Optic<__<__<Grating.µ, A>, B>, S, T, A, B> optic() {
    return this::f;
  }

  static <S, A> AGrate_<S, A> mono(AGrate<S, S, A, A> aGrate) {
    return aGrate::f;
  }
}