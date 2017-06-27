package fj.data.lens.types;

import fj.data.lens.internal.Forget;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__3;

public interface Fold_<R, S, A> extends __3<Fold_.µ, R, S, A> {
  enum µ {}

  Forget<R, S, S> f(__<__<__<Forget.µ, R>, A>, A> fgt);

  default Optic_<__<Forget.µ, R>, S, A> optic() {
    return this::f;
  }

  default Fold<R, S, S, A, A> poly() {
    return this::f;
  }
}