package fj.data.lens.types;

import fj.data.lens.internal.Forget;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;

public interface Getter_<S, A> extends __2<Getter_.µ, S, A> {
  enum µ {}

  Forget<A, S, S> f(__<__<__<Forget.µ, A>, A>, A> fgt);

  default Fold_<A, S, A> fold() {
    return this::f;
  }

  default Getter<S, S, A, A> poly() {
    return this::f;
  }
}