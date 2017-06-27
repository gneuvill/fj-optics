package fj.data.lens.internal;

import fj.F;
import fj.data.profunctor.Closed;
import fj.data.profunctor.Profunctor;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__4;

import static fj.Function.compose;

public interface Grating<A, B, S, T> extends __4<Grating.µ, A, B, S, T> {
  enum µ {}

  T f(F<F<S, A>, B> f);

  static <A, B> ProfunctorGrating<A, B> profunctor() { return () -> {}; }

  static <A, B> ClosedGrating<A, B> closed() { return () -> {}; }

  interface ProfunctorGrating<X, Y> extends Profunctor<__<__<Grating.µ, X>, Y>> {
    void self();

    @Override
    default <A, B, C, D> Grating<X, Y, A, D> dimap(F<A, B> f, F<C, D> g, __<__<__<__<Grating.µ, X>, Y>, B>, C> p) {
      final Grating<X, Y, B, C> z = _Internal.asGrating(p);

      return d -> g.f(z.f(k -> d.f(compose(k, f))));
    }
  }

  interface ClosedGrating<Y, Z> extends Closed<__<__<Grating.µ, Y>, Z>>, ProfunctorGrating<Y, Z> {
    @Override
    default <A, B, X> Grating<Y, Z, F<X, A>, F<X, B>> closed(__<__<__<__<Grating.µ, Y>, Z>, A>, B> p) {
      final Grating<Y, Z, A, B> z = _Internal.asGrating(p);

      return f -> x -> z.f(k -> f.f(g -> k.f(g.f(x))));
    }
  }
}
