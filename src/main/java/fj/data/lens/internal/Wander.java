package fj.data.lens.internal;

import fj.F;
import fj.F0;
import fj._Fj;
import fj.control.Applicative;
import fj.data.Functor;
import fj.data.profunctor.Choice;
import fj.data.profunctor.Star;
import fj.data.profunctor.Star.ChoiceStar;
import fj.data.profunctor.Star.StrongStar;
import fj.data.profunctor.Strong;
import fj.data.profunctor._FjProF;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;

public interface Wander<p> extends Strong<p>, Choice<p> {
  <S, T, A, B> __2<p, S, T> wander(Fw<S, T, A, B> f, __<__<p, A>, B> pab);

  interface Fw<S, T, A, B> {
    <f> __<f, T> f(Applicative<f> A, F<A, __<f, B>> f, S s);

    default <f> F<F<A, __<f, B>>, F<S, __<f, T>>> curry(Applicative<f> A) {
      return f -> s -> f(A, f, s);
    }
  }

  static WanderFunction f() { return () -> {}; }

  static <f> WanderStar<f> star(Applicative<f> A) { return () -> A; }

  interface WanderFunction extends Wander<F.µ>, FStrong, FChoice {
    void self();

    @Override
    default <S, T, A, B> F<S, T> wander(Fw<S, T, A, B> f, __<__<F.µ, A>, B> pab) {
      final Applicative<F0.µ> A = Applicative.f0();

      return s -> _Fj.asF0(f.f(A, a -> A.pure(_Fj.asF(pab).f(a)), s)).f();
    }
  }

  interface WanderStar<f> extends Wander<__<Star.µ, f>>, StrongStar<f>, ChoiceStar<f> {
    Applicative<f> A();

    @Override
    default Functor<f> F() { return A(); }

    @Override
    default <S, T, A, B> Star<f, S, T> wander(Fw<S, T, A, B> f, __<__<__<Star.µ, f>, A>, B> pab) {
      return s -> f.f(A(), _FjProF.asStar(pab)::f, s);
    }
  }
}