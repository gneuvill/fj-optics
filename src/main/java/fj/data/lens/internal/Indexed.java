package fj.data.lens.internal;

import fj.F;
import fj.P;
import fj.P2;
import fj.data.Either;
import fj.control.Applicative;
import fj.data.profunctor.Choice;
import fj.data.profunctor.Profunctor;
import fj.data.profunctor.Strong;
import org.derive4j.hkt.TypeEq;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__4;

public interface Indexed<p, I, S, T> extends __4<Indexed.µ, p, I, S, T> {
  enum µ {}

  __2<p, P2<I, S>, T> p();

  static <p, I, S, T> Indexed<p, I, S, T> of(__<__<p, P2<I, S>>, T> p) {
    return () -> TypeEq.as__2(p);
  }

  static <p, I> ProfunctorIndexed<p, I> profunctor(Profunctor<p> P) { return () -> P; }

  static <p, I> StrongIndexed<p, I> strong(Strong<p> S) { return () -> S; }

  static <p, I> WanderIndexed<p, I> wander(Wander<p> W) { return () -> W; }

  interface ProfunctorIndexed<f, X> extends Profunctor<__<__<Indexed.µ, f>, X>> {
    Profunctor<f> P();

    @Override
    default <A, B, C, D> Indexed<f, X, A, D> dimap(F<A, B> f, F<C, D> g, __<__<__<__<Indexed.µ, f>, X>, B>, C> i) {
      return () -> P().dimap(Strong.f().second(f), g, _Internal.asIndexed(i).p());
    }
  }

  interface StrongIndexed<f, X> extends Strong<__<__<Indexed.µ, f>, X>>, ProfunctorIndexed<f, X> {
    Strong<f> S();

    @Override
    default Profunctor<f> P() { return S(); }

    @Override
    default <A, B, C> Indexed<f, X, P2<A, C>, P2<B, C>> first(__<__<__<__<Indexed.µ, f>, X>, A>, B> i) {
      final __2<f, P2<X, A>, B> p = _Internal.asIndexed(i).p();

      return () -> S().lmap(t -> P.p(P.p(t._1(), t._2()._1()), t._2()._2()), S().first(p));
    }

    @Override
    default <A, B, C> Indexed<f, X, P2<A, B>, P2<A, C>> second(__<__<__<__<Indexed.µ, f>, X>, B>, C> i) {
      final __2<f, P2<X, B>, C> p = _Internal.asIndexed(i).p();

      return () -> S().lmap(t -> P.p(t._2()._1(), P.p(t._1(), t._2()._2())), S().second(p));
    }
  }

  interface ChoiceIndexed<f, X> extends Choice<__<__<Indexed.µ, f>, X>>, ProfunctorIndexed<f, X> {
    Choice<f> C();

    @Override
    default Profunctor<f> P() { return C(); }

    @Override
    default <A, B, C> Indexed<f, X, Either<A, C>, Either<B, C>> left(__<__<__<__<Indexed.µ, f>, X>, A>, B> i) {
      final __2<f, P2<X, A>, B> p = _Internal.asIndexed(i).p();

      return () -> C().lmap(t -> t._2().either(a -> Either.left(P.p(t._1(), a)), Either::right), C().left(p));
    }

    @Override
    default <A, B, C> Indexed<f, X, Either<A, B>, Either<A, C>> right(__<__<__<__<Indexed.µ, f>, X>, B>, C> i) {
      final __2<f, P2<X, B>, C> p = _Internal.asIndexed(i).p();

      return () -> C().lmap(t -> t._2().either(Either::left, b -> Either.right(P.p(t._1(), b))), C().right(p));
    }
  }

  interface WanderIndexed<f, X> extends Wander<__<__<Indexed.µ, f>, X>>, StrongIndexed<f, X>, ChoiceIndexed<f, X> {
    Wander<f> W();

    @Override
    default Profunctor<f> P() { return W(); }

    @Override
    default Strong<f> S() { return W(); }

    @Override
    default Choice<f> C() { return W(); }

    @Override
    default <S, T, A, B> Indexed<f, X, S, T> wander(Fw<S, T, A, B> trav, __<__<__<__<Indexed.µ, f>, X>, A>, B> pab) {
      final Fw<P2<X, S>, T, P2<X, A>, B> fw = new Fw<P2<X, S>, T, P2<X, A>, B>() {
        @Override
        public <m> __<m, T> f(Applicative<m> A, F<P2<X, A>, __<m, B>> f, P2<X, S> xs) {
          return trav.f(A, a -> f.f(P.p(xs._1(), a)), xs._2());
        }
      };

      return () -> W().wander(fw, _Internal.asIndexed(pab).p());
    }
  }

}
