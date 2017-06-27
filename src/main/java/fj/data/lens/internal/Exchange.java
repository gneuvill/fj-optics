package fj.data.lens.internal;

import fj.F;
import fj.F1W;
import fj.data.lens.data;
import fj.data.Functor;
import fj.data.profunctor.Profunctor;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__4;

import static fj.data.lens.internal._Exchange.Exchange;

@data
public interface Exchange<A, B, S, T> extends  __4<Exchange.µ, A, B, S, T> {
  enum µ {}

  interface Cases<R, A, B, S, T> {
    R Exchange(F<S, A> l, F<B, T> r);
  }

  <R> R match(Cases<R, A, B, S, T> cases);

  static <A, B, S> ExchangeFunctor<A, B, S> functor() { return () -> {}; }

  static <A, B, S> ExchangeProfunctor<A, B, S> profunctor() { return () -> {}; }

  interface ExchangeFunctor<X, Y, S> extends Functor<__<__<__<µ, X>, Y>, S>> {
    void self();

    @Override
    default <A, B> Exchange<X, Y, S, B> map(F<A, B> f, __<__<__<__<Exchange.µ, X>, Y>, S>, A> p) {
      return _Internal.asExchange(p).match((a, b) -> Exchange(a, F1W.lift(f).o(b)));
    }
  }

  interface ExchangeProfunctor<X, Y, S> extends Profunctor<__<__<µ, X>, Y>>, ExchangeFunctor<X, Y, S> {
    @Override
    default <A, B, C, D> Exchange<X, Y, A, D> dimap(F<A, B> f, F<C, D> g, __<__<__<__<Exchange.µ, X>, Y>, B>, C> p) {
      return _Internal.asExchange(p).match((a, b) -> Exchange(F1W.lift(a).o(f), F1W.lift(g).o(b)));
    }
  }

}
