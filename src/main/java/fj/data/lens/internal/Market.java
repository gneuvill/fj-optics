package fj.data.lens.internal;

import fj.F;
import fj.data.Either;
import fj.data._FjData;
import fj.data.lens.data;
import fj.data.lens.types.Prism;
import fj.data.Functor;
import fj.data.bifunctor.Bifunctor;
import fj.data.profunctor.Choice;
import fj.data.profunctor.Profunctor;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__4;

import static fj.Function.compose;
import static fj.data.Either.*;
import static fj.data.lens.internal._Market.Market;

/**
 * The {@link Market} profunctor characterizes a {@link Prism}
 */
@data
public interface Market<A, B, S, T> extends  __4<Market.µ, A, B, S, T> {
  enum µ {}

  interface Cases<R, A, B, S, T> {
    R Market(F<B, T> f, F<S, Either<T, A>> g);
  }

  <R> R match(Cases<R, A, B, S, T> cases);

  static <A, B, S> MarketFunctor<A, B, S> functor() { return () -> {}; }

  static <A, B> MarketProfunctor<A, B> profunctor() { return () -> {}; }

  interface MarketFunctor<X, Y, Z> extends Functor<__<__<__<Market.µ, X>, Y>, Z>> {
    void self();

    @Override
    default <A, B> Market<X, Y, Z, B> map(F<A, B> f, __<__<__<__<Market.µ, X>, Y>, Z>, A> p) {
      return _Internal.asMarket(p).match((a, b) ->
        Market(compose(f, a)
          , z -> _FjData.asEither(Bifunctor.either().lmap(f, b.f(z)))));
    }
  }

  interface MarketProfunctor<X, Y> extends Profunctor<__<__<Market.µ, X>, Y>> {
    void self();

    @Override
    default <A, B, C, D> Market<X, Y, A, D> dimap(F<A, B> f, F<C, D> g, __<__<__<__<Market.µ, X>, Y>, B>, C> p) {
      return _Internal.asMarket(p).match((a, b) ->
        Market(compose(g, a)
          , z -> _FjData.asEither(Bifunctor.either().lmap(g, b.f(f.f(z))))));
    }
  }

  interface MarketChoice<X, Y> extends Choice<__<__<Market.µ, X>, Y>>, MarketProfunctor<X, Y> {
    @Override
    default <A, B, C> Market<X, Y, Either<A, C>, Either<B, C>> left(__<__<__<__<Market.µ, X>, Y>, A>, B> p) {
      return _Internal.asMarket(p).match((x, y) ->
        Market(compose(left_(), x)
          , either_(a -> _FjData.asEither(Bifunctor.either().lmap(left_(), y.f(a)))
            , compose(left_(), right_()))));
    }

    @Override
    default <A, B, C> Market<X, Y, Either<A, B>, Either<A, C>> right(__<__<__<__<Market.µ, X>, Y>, B>, C> p) {
      return _Internal.asMarket(p).match((x, y) ->
        Market(compose(right_(), x)
          , either_(compose(left_(), left_())
            , b -> _FjData.asEither(Bifunctor.either().lmap(right_(), y.f(b))))));
    }
  }
}
