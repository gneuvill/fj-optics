package fj.data.lens.types;

import fj.P2;
import fj.data.lens.internal.Indexed;
import fj.data.lens.internal.Shop;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__5;

public interface AnIndexedLens<I, S ,T, A, B> extends __5<AnIndexedLens.µ, I, S, T, A, B> {
  enum µ {}

  __2<__2<Shop.µ, P2<I, A>, B>, S, T> f(__<__<__<__<Indexed.µ, __2<Shop.µ, P2<I, A>, B>>, I>, A>, B> idx);

  default IndexedOptic<__2<Shop.µ, P2<I, A>, B>, I, S, T, A, B> optic() {
    return this::f;
  }

  static <I, S, A> AnIndexedLens_<I, S, A> mono(AnIndexedLens<I, S, S, A, A> idx) {
    return idx::f;
  }
}
