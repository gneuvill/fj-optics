package fj.data.lens.types;

import fj.F;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__4;

public interface Setter<S, T, A, B> extends __4<Setter.µ, S, T, A, B> {
  enum µ {}

  F<S, T> f(__<__<F.µ, A>, B> pab);

  default Optic<F.µ, S, T, A, B> optic() {
    return this::f;
  }

  static <S, A> Setter_<S, A> mono(Setter<S, S, A, A> setter) {
    return setter::f;
  }
}
