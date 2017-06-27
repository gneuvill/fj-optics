package fj.data.lens.types;

import fj.data.lens.internal.Wander;
import fj.data.profunctor.Choice;
import fj.data.profunctor.Closed;
import fj.data.profunctor.Profunctor;
import fj.data.profunctor.Strong;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__4;

/**
 * A generalized isomorphism.
 */
public interface Iso<S, T, A, B> extends __4<Iso.µ, S, T, A, B> {
  enum µ {}

  <p> __2<p, S, T> f(Profunctor<p> P, __<__<p, A>, B> pab);

  default <p> Optic<p, S, T, A, B> optic(Profunctor<p> P) {
    return pab -> f(P, pab);
  }

  default <X, Y> Iso<S, T, X, Y> o(Iso<A, B, X, Y> iso) {
    return new Iso<S, T, X, Y>() {
      public <p> __2<p, S, T> f(Profunctor<p> P, __<__<p, X>, Y> pab) {
        return Iso.this.f(P, iso.f(P, pab));
      }
    };
  }

  default <X, Y> Lens<S, T, X, Y> o(Lens<A, B, X, Y> lens) {
    return new Lens<S, T, X, Y>() {
      public <p> __2<p, S, T> f(Strong<p> S, __<__<p, X>, Y> pab) {
        return Iso.this.f(S, lens.f(S, pab));
      }
    };
  }

  default <X, Y> Prism<S, T, X, Y> o(Prism<A, B, X, Y> prism) {
    return new Prism<S, T, X, Y>() {
      public <p> __2<p, S, T> f(Choice<p> C, __<__<p, X>, Y> pab) {
        return Iso.this.f(C, prism.f(C, pab));
      }
    };
  }

  default <X, Y> Traversal<S, T, X, Y> o(Traversal<A, B, X, Y> traversal) {
    return new Traversal<S, T, X, Y>() {
      public <p> __2<p, S, T> f(Wander<p> W, __<__<p, X>, Y> pab) {
        return Iso.this.f(W, traversal.f(W, pab));
      }
    };
  }

  default <X, Y> Grate<S, T, X, Y> o(Grate<A, B, X, Y> grate) {
    return new Grate<S, T, X, Y>() {
      public <p> __2<p, S, T> f(Closed<p> C, __<__<p, X>, Y> pab) {
        return Iso.this.f(C, grate.f(C, pab));
      }
    };
  }

  static <S, A> Iso_<S, A> mono(Iso<S, S, A, A> iso) {
    return iso::f;
  }
}
