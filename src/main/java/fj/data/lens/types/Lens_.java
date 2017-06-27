package fj.data.lens.types;

import fj.data.profunctor.Strong;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;

public interface Lens_<S, A> extends __2<Lens_.µ, S, A> {
  enum µ {}

  <p> __2<p, S, S> f(Strong<p> P, __<__<p, A>, A> paa);

  default <p> Optic_<p, S, A> optic(Strong<p> P) {
    return paa -> f(P, paa);
  }

  default Lens<S, S, A, A> poly() {
    return this::f;
  }
}
