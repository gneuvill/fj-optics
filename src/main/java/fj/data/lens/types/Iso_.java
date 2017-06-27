package fj.data.lens.types;

import fj.data.profunctor.Profunctor;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;

public interface Iso_<S, A> extends __2<Iso_.µ, S, A> {
  enum µ {}

  <p> __2<p, S, S> f(Profunctor<p> P, __<__<p, A>, A> paa);

  default <p> Optic_<p, S, A> optic(Profunctor<p> P) {
    return paa -> f(P, paa);
  }

  default Iso<S, S, A, A> poly() {
    return this::f;
  }
}
