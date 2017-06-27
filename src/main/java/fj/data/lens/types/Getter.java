package fj.data.lens.types;

import fj.data.lens.internal.Forget;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__4;

public interface Getter<S, T, A, B> extends __4<Getter.µ, S, T, A, B> {
  enum µ {}

  Forget<A, S, T> f(__<__<__<Forget.µ, A>, A>, B> fgt);

  default Fold<A, S, T, A, B> fold() {
    return this::f;
  }

  static <S, A> Getter_<S, A> mono(Getter<S, S, A, A> getter) {
    return getter::f;
  }
}