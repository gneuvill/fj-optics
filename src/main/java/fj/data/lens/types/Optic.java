package fj.data.lens.types;

import fj.F;
import fj.data.lens.internal.Wander;
import fj.data.profunctor.Choice;
import fj.data.profunctor.Closed;
import fj.data.profunctor.Profunctor;
import fj.data.profunctor.Strong;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__5;

/**
 * A general-purpose {@link Lens}
 */
public interface Optic<p, S, T, A, B> extends __5<Optic.µ, p, S, T, A, B> {
  enum µ {}

  __2<p, S, T> f(__<__<p, A>, B> pab);

  default F<__<__<p, A>, B>, __2<p, S, T>> f_() {
    return this::f;
  }

  default <X, Y> Optic<p, S, T, X, Y> o(Optic<p, A, B, X, Y> optic) {
    return pxy -> f(optic.f(pxy));
  }

  default <X, Y> Optic<p, S, T, X, Y> o(Profunctor<p> P, Iso<A, B, X, Y> iso) {
    return o(iso.optic(P));
  }

  default <X, Y> Optic<p, S, T, X, Y> o(Strong<p> S, Lens<A, B, X, Y> lens) {
    return o(lens.optic(S));
  }

  default <X, Y> Optic<p, S, T, X, Y> o(Choice<p> C, Prism<A, B, X, Y> prism) {
    return o(prism.optic(C));
  }

  default <X, Y> Optic<p, S, T, X, Y> o(Wander<p> W, Traversal<A, B, X, Y> traversal) {
    return o(traversal.optic(W));
  }

  default <X, Y> Optic<p, S, T, X, Y> o(Closed<p> C, Grate<A, B, X, Y> grate) {
    return o(grate.optic(C));
  }

  static <p, S, A> Optic_<p, S, A> mono(Optic<p, S, S, A, A> o) {
    return o::f;
  }
}
