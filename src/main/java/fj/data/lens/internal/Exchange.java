package fj.data.lens.internal;

import fj.F;
import fj.F1W;
import fj.data.lens.data;
import fj.typeclasses.data.Functor;
import fj.typeclasses.data.Profunctor;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__4;

@data
public abstract class Exchange<A, B, S, T> implements __4<Exchange.µ, A, B, S, T> {
  public enum µ {}
  Exchange() {}

  interface Cases<R, A, B, S, T> {
    R Exchange(F<S, A> l, F<B, T> r);
  }

  public abstract <R> R match(Cases<R, A, B, S, T> cases);

  @SuppressWarnings("unchecked")
  public static <A, B, S> Instances<A, B, S> instances() { return (Instances<A, B, S>) Instances.self; }

  public static final class Instances<X, Y, S> implements
    Functor<__<__<__<Exchange.µ, X>, Y>, S>>
    , Profunctor<__<__<µ, X>, Y>> {

    static Instances<?, ?, ?> self = new Instances<>();

    private Instances() {}

    @Override
    public <A, B> Exchange<X, Y, S, B> map(F<A, B> f, __<__<__<__<Exchange.µ, X>, Y>, S>, A> p) {
      return _Internal.asExchange(p).match((a, b) -> _Exchange.Exchange(a, F1W.lift(f).o(b)));
    }

    @Override
    public <A, B, C, D> Exchange<X, Y, A, D> dimap(F<A, B> f, F<C, D> g, __<__<__<__<Exchange.µ, X>, Y>, B>, C> p) {
      return _Internal.asExchange(p).match((a, b) -> _Exchange.Exchange(F1W.lift(a).o(f), F1W.lift(g).o(b)));
    }
  }
}
