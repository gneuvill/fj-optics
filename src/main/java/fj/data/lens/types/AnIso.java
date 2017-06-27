package fj.data.lens.types;

import fj.data.lens.internal.Exchange;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__4;

public interface AnIso<S, T, A, B> extends __4<AnIso.µ, S, T, A, B> {
  enum µ {}

  Exchange<A, B, S, T> f(__<__<__<__<Exchange.µ, A>, B>, A>, B> eab);

  default Optic<__<__<Exchange.µ, A>, B>, S, T, A, B> optic() {
    return this::f;
  }

  static <S, A> AnIso_<S, A> mono(AnIso<S, S, A, A> iso) {
    return iso::f;
  }
}
