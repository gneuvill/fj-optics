package fj.data.lens.types;

import fj.data.lens.internal.Forget;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__5;

public interface Fold<R, S, T, A, B> extends __5<Fold.µ, R, S, T, A, B> {
  enum µ {}

  Forget<R, S, T> f(__<__<__<Forget.µ, R>, A>, B> fgt);

  default Optic<__<Forget.µ, R>, S, T, A, B> optic() {
    return this::f;
  }

  static <R, S, A> Fold_<R, S, A> mono(Fold<R, S, S, A, A> fold) {
    return fold::f;
  }
}