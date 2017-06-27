package fj.data.lens.types;

import fj.data.lens.internal.Indexed;
import fj.data.profunctor.Strong;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__3;

public interface IndexedLens_<I, S, A> extends __3<IndexedLens_.µ, I, S, A> {
  enum µ {}

  <p> __2<p, S, S> f(Strong<p> S, __<__<__<__<Indexed.µ, p>, I>, A>, A> idx);

  default <p> IndexedOptic_<p, I, S, A> optic(Strong<p> S) {
    return idx -> f(S, idx);
  }

  default IndexedLens<I, S, S, A, A> poly() {
    return this::f;
  }
}
