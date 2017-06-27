package fj.data.lens.types;

import fj.data.lens.internal.Exchange;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;

public interface AnIso_<S, A> extends __2<AnIso_.µ, S, A> {
  enum µ {}

  Exchange<A, A, S, S> f(__<__<__<__<Exchange.µ, A>, A>, A>, A> ea);

  default Optic_<__<__<Exchange.µ, A>, A>, S, A> optic() {
    return this::f;
  }

  default AnIso<S, S, A, A> poly() {
    return this::f;
  }
}
