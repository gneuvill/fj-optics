package fj.data.lens.types;

import fj.P2;
import fj.data.lens.internal.Indexed;
import fj.data.lens.internal.Shop;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__3;

public interface AnIndexedLens_<I, S, A> extends __3<AnIndexedLens_.µ, I, S, A> {
  enum µ {}

  __2<__2<Shop.µ, P2<I, A>, A>, S, S> f(__<__<__<__<Indexed.µ, __2<Shop.µ, P2<I, A>, A>>, I>, A>, A> idx);

  default IndexedOptic_<__2<Shop.µ, P2<I, A>, A>, I, S, A> optic() {
    return this::f;
  }

  default AnIndexedLens<I, S, S, A, A> poly() {
    return this::f;
  }
}
