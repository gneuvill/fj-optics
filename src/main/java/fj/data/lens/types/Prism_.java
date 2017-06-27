package fj.data.lens.types;

import fj.data.profunctor.Choice;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;

public interface Prism_<S, A> extends __2<Prism_.µ, S, A> {
  enum µ {}

  <p> __2<p, S, S> f(Choice<p> C, __<__<p, A>, A> pab);

  default <p> Optic_<p, S, A> optic(Choice<p> C) {
    return paa -> f(C, paa);
  }

  default Prism<S, S, A, A> poly() {
    return this::f;
  }
}
