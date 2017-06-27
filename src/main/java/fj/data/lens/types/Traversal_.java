package fj.data.lens.types;

import fj.data.lens.internal.Wander;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;

public interface Traversal_<S, A> extends __2<Traversal_.µ, S, A> {
  enum µ {}

  <p> __2<p, S, S> f(Wander<p> W, __<__<p, A>, A> pab);

  default <p> Optic_<p, S, A> optic(Wander<p> W) {
    return paa -> f(W, paa);
  }

  default Traversal<S, S, A, A> poly() {
    return this::f;
  }
}
