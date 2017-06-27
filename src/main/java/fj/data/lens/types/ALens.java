package fj.data.lens.types;

import fj.data.lens.internal.Shop;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__4;

public interface ALens<S, T, A, B> extends __4<ALens.µ, S, T, A, B> {
  enum µ {}

  Shop<A, B, S, T> f(__<__<__<__<Shop.µ, A>, B>, A>, B> sab);

  default Optic<__<__<Shop.µ, A>, B>, S, T, A, B> optic() {
    return this::f;
  }

  static <S, A> ALens_<S, A> mono(ALens<S, S, A, A> aLens) {
    return aLens::f;
  }
}
