package fj.data.lens.types;

import fj.data.lens.internal.Market;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__4;

public interface APrism<S, T, A, B> extends __4<APrism.µ, S, T, A, B> {
  enum µ {}

  Market<A, B, S, T> f(__<__<__<__<Market.µ, A>, B>, A>, B> pab);

  default Optic<__<__<Market.µ, A>, B>, S, T, A, B> optic() {
    return this::f;
  }

  static <S, A> APrism_<S, A> mono(APrism<S, S, A, A> aPrism) {
    return aPrism::f;
  }
}
