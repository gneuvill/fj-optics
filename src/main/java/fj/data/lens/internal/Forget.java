package fj.data.lens.internal;

import fj.F;
import fj.P2;
import fj.data.*;
import fj.data.profunctor.Choice;
import fj.data.profunctor.Cochoice;
import fj.data.profunctor.Profunctor;
import fj.data.profunctor.Strong;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__3;

import static fj.data.Const.Const;

/**
 * <p>Profunctor that forgets the <code>B</code> value and returns (and accumulates) a value of type <code>R</code>.</p>
 *
 * <p><code>__&lt;Forget.µ, R&gt;</code> is isomorphic to <code>__&lt;Star.µ, Const&lt;R&gt;&gt;</code>,
 * but can be given a <code>Cochoice</code> instance. </p>
 */
public interface Forget<R, A, B> extends __3<Forget.µ, R, A, B> {
  enum µ {}

  R f(A a);

  static <R, A, B> NewtypeForget<R, A, B> newtype() { return () -> {}; }

  static <R> ProfunctorForget<R> profunctor() { return () -> {}; }

  static <R> ChoiceForget<R> choice(Monoid<R> M) { return () -> M; }

  static <R> StrongForget<R> strong() { return () -> {}; }

  static <R> CochoiceForget<R> cochoice() { return () -> {}; }

  static <R> WanderForget<R> wander(Monoid<R> M) { return () -> M; }

  interface NewtypeForget<R, A, B> extends Newtype<Forget<R, A, B>, F<A, R>> {
    void self();

    @Override
    default F<A, R> unwrap(Forget<R, A, B> forget) {
      return forget::f;
    }

    @Override
    default Forget<R, A, B> wrap(F<A, R> f) {
      return f::f;
    }
  }

  interface ProfunctorForget<R> extends Profunctor<__<Forget.µ, R>> {
    void self();

    @Override
    default <A, B, C, D> Forget<R, A, D> dimap(F<A, B> f, F<C, D> g, __<__<__<Forget.µ, R>, B>, C> p) {
      return a -> _Internal.asForget(p).f(f.f(a));
    }
  }

  interface ChoiceForget<R> extends Choice<__<Forget.µ, R>>, ProfunctorForget<R> {
    Monoid<R> M();

    @Override
    default void self() {}

    @Override
    default <A, B, C> Forget<R, Either<A, C>, Either<B, C>> left(__<__<__<Forget.µ, R>, A>, B> p) {
      return ei -> ei.either(_Internal.asForget(p)::f, __ -> M().mempty());
    }

    @Override
    default <A, B, C> Forget<R, Either<A, B>, Either<A, C>> right(__<__<__<Forget.µ, R>, B>, C> p) {
      return ei -> ei.either(__ -> M().mempty(), _Internal.asForget(p)::f);
    }
  }

  interface StrongForget<R> extends Strong<__<Forget.µ, R>>, ProfunctorForget<R> {
    @Override
    default <A, B, C> Forget<R, P2<A, C>, P2<B, C>> first(__<__<__<Forget.µ, R>, A>, B> p) {
      return t -> _Internal.asForget(p).f(t._1());
    }

    @Override
    default <A, B, C> Forget<R, P2<A, B>, P2<A, C>> second(__<__<__<Forget.µ, R>, B>, C> p) {
      return t -> _Internal.asForget(p).f(t._2());
    }
  }

  interface CochoiceForget<R> extends Cochoice<__<Forget.µ, R>>, ProfunctorForget<R> {
    @Override
    default <A, B, C> Forget<R, A, B> unleft(__<__<__<Forget.µ, R>, Either<A, C>>, Either<B, C>> p) {
      return a -> _Internal.asForget(p).f(Either.left(a));
    }

    @Override
    default <A, B, C> Forget<R, B, C> unright(__<__<__<Forget.µ, R>, Either<A, B>>, Either<A, C>> p) {
      return b -> _Internal.asForget(p).f(Either.right(b));
    }
  }

  interface WanderForget<R> extends Wander<__<Forget.µ, R>>, ChoiceForget<R>, StrongForget<R> {
    Monoid<R> M();

    @Override
    default <S, T, A, B> Forget<R, S, T> wander(Fw<S, T, A, B> f, __<__<__<Forget.µ, R>, A>, B> pab) {
      final F<A, R> r = _Internal.asForget(pab)::f;

      return s -> _FjData.asConst(f.f(Const.applicative(M()), a -> Const(r.f(a)), s)).get();
//      F<S, R> res =
//      _Fj.asF(Const.newtype().alaF(Functor.f(), Functor.f(), Const.newtype()
//        , a -> Const.Const(a)
//        , f.curry(Const.applicative(M()))
//        , r));
//
//      return res::f;
    }
  }
}