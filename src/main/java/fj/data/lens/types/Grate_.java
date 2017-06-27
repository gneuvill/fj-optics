package fj.data.lens.types;

import fj.data.profunctor.Closed;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;

public interface Grate_<S, A> extends __2<Grate_.µ, S, A> {
  enum µ {}

  <p> __2<p, S, S> f(Closed<p> C, __<__<p, A>, A> paa);

  default <p> Optic_<p, S, A> optic(Closed<p> C) {
    return paa -> f(C, paa);
  }

  default <p> Grate<S, S, A, A> poly() {
    return this::f;
  }
}