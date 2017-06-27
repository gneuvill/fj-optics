package fj.data.lens.types;

import fj.data.lens.internal.Shop;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;

public interface ALens_<S, A> extends __2<ALens_.µ, S, A> {
  enum µ {}

  Shop<A, A, S, S> f(__<__<__<__<Shop.µ, A>, A>, A>, A> sab);

  default Optic_<__<__<Shop.µ, A>, A>, S, A> optic() {
    return this::f;
  }

  default ALens<S, S, A, A> poly() {
    return this::f;
  }
}
