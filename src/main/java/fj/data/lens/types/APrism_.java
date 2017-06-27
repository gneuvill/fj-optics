package fj.data.lens.types;

import fj.data.lens.internal.Market;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;

public interface APrism_<S, A> extends __2<APrism_.µ, S, A> {
  enum µ {}

  Market<A, A, S, S> f(__<__<__<__<Market.µ, A>, A>, A>, A> pab);

  default Optic_<__<__<Market.µ, A>, A>, S, A> optic() {
    return this::f;
  }

  default APrism<S, S, A, A> poly() {
    return this::f;
  }
}
