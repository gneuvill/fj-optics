package fj.data.lens.types;

import fj.data.lens.internal.Wander;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__4;

/**
 * A traversal.
 */
public interface Traversal<S, T, A, B> extends __4<Traversal.µ, S, T, A, B> {
  enum µ {}

  <p> __2<p, S, T> f(Wander<p> W, __<__<p, A>, B> pab);

  default <p> Optic<p, S,T, A, B> optic(Wander<p> W) {
    return pab -> f(W, pab);
  }

  static <S, A> Traversal_<S, A> mono(Traversal<S, S, A, A> traversal) {
    return traversal::f;
  }
}
