package fj.data.lens.types;

import fj.F;
import fj.F2;
import fj.P;
import fj.P2;
import fj.data.profunctor.Strong;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__4;

/**
 * A lens.
 */
public interface Lens<S, T, A, B> extends __4<Lens.µ, S, T, A, B> {
  enum µ {}

  <p> __2<p, S, T> f(Strong<p> S, __<__<p, A>, B> pab);

  default <p> Optic<p, S, T, A, B> optic(Strong<p> S) {
    return pab -> f(S, pab);
  }

  static <S, A> Lens_<S, A> mono(Lens<S, S, A, A> lens) {
    return lens::f;
  }

  // #### Static combinators

  static <S, T, A, B> Lens<S, T, A, B> _lens(F<S, P2<A, F<B, T>>> fp) {
    return new Lens<S, T, A, B>() {
      @Override
      public <p> __2<p, S, T> f(Strong<p> S, __<__<p, A>, B> pab) {
        return S.dimap(fp, p -> p._2().f(p._1()), S.first(pab));
      }
    };
  }

  static <S, T, A, B> Lens<S, T, A, B> lens(F<S, A> get, F2<S, B, T> set) {
    return _lens(s -> P.p(get.f(s), b -> set.f(s, b)));
  }

  static <S, A> Lens_<S, A> lens_(F<S, A> get, F2<S, A, S> set) {
    return mono(lens(get, set));
  }

}
