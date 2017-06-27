package fj.data.lens.internal;

import fj.*;
import fj.data.lens.data;
import fj.data.profunctor.Strong;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__4;

import static fj.Function.compose;
import static fj.Function.uncurryF2;
import static fj.data.lens.internal._Shop.Shop;

@data
public interface Shop<A, B, S, T> extends __4<Shop.µ, A, B, S, T> {
  enum µ {}

  interface Cases<R, A, B, S, T> {
    R Shop(F<S, A> x, F2<S, B, T> y);
  }

  <R> R match(Cases<R, A, B, S, T> cases);

  static <A, B> ShopStrong<A, B> strong() { return () -> {}; }

  interface ShopStrong<X, Y> extends Strong<__<__<Shop.µ, X>, Y>> {
    void self ();

    @Override
    default <A, B, C> Shop<X, Y, P2<A, C>, P2<B, C>> first(__<__<__<__<Shop.µ, X>, Y>, A>, B> p) {
      return _Internal.asShop(p).match((x, y) ->
        Shop(pr -> x.f(pr._1())
          , (pr, b) -> P.p(y.f(pr._1(), b), pr._2())));
    }

    @Override
    default <A, B, C> Shop<X, Y, P2<A, B>, P2<A, C>> second(__<__<__<__<µ, X>, Y>, B>, C> p) {
      return _Internal.asShop(p).match((x, y) ->
        Shop(pr -> x.f(pr._2())
          , (pr, b) -> P.p(pr._1(), y.f(pr._2(), b))));
    }

    @Override
    default <A, B, C, D> Shop<X, Y, A, D> dimap(F<A, B> f, F<C, D> g, __<__<__<__<Shop.µ, X>, Y>, B>, C> p) {
      return _Internal.asShop(p).match((x, y) ->
        Shop(compose(x, f)
          , uncurryF2(s -> compose(g, F2W.lift(y).f(f.f(s))))));
    }
  }
}
