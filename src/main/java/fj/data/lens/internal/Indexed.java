package fj.data.lens.internal;

import fj.F;
import fj.P2;
import fj.P;
import fj.data.Either;
import fj.instances.F_;
import fj.typeclasses.data.Profunctor;
import fj.typeclasses.data.Profunctor.Choice;
import fj.typeclasses.data.Profunctor.Strong;
import org.derive4j.hkt.TypeEq;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__4;

interface Indexed<p, I, S, T> extends __4<Indexed.µ, p, I, S, T> {
  enum µ {}

  __2<p, P2<I, S>, T> p();

  static <p, I, S, T> Indexed<p, I, S, T> of(__<__<p, P2<I, S>>, T> p) {
    return () -> TypeEq.as__2(p);
  }

  static <p, I> ProfunctorIndexed<p, I> profunctor(Profunctor<p> P) {
    return new ProfunctorIndexed<>(P);
  }

  static <p, I> StrongIndexed<p, I> strong(Strong<p> S) {
    return new StrongIndexed<>(S);
  }

  class ProfunctorIndexed<f, X> implements Profunctor<__<__<Indexed.µ, f>, X>> {
    private final Profunctor<f> P;
    private ProfunctorIndexed(Profunctor<f> p) {P = p;}

    @Override
    public final <A, B, C, D> Indexed<f, X, A, D> dimap(F<A, B> f, F<C, D> g, __<__<__<__<Indexed.µ, f>, X>, B>, C> i) {
      return () -> P.dimap(F_.instances().second(f), g, _Internal.asIndexed(i).p());
    }
  }

  class StrongIndexed<f, X> extends ProfunctorIndexed<f, X> implements Strong<__<__<Indexed.µ, f>, X>> {
    private final Strong<f> S;
    private StrongIndexed(Strong<f> S) { super(S); this.S = S; }

    @Override
    public final <A, B, C> Indexed<f, X, P2<A, C>, P2<B, C>> first(__<__<__<__<Indexed.µ, f>, X>, A>, B> i) {
      final __2<f, P2<X, A>, B> p = _Internal.asIndexed(i).p();

      return () -> S.lmap(t -> P.p(P.p(t._1(), t._2()._1()), t._2()._2()), S.first(p));
    }

    @Override
    public final <A, B, C> Indexed<f, X, P2<A, B>, P2<A, C>> second(__<__<__<__<Indexed.µ, f>, X>, B>, C> i) {
      final __2<f, P2<X, B>, C> p = _Internal.asIndexed(i).p();

      return () -> S.lmap(t -> P.p(t._2()._1(), P.p(t._1(), t._2()._2())), S.second(p));
    }
  }

  class ChoiceIndexed<f, X>  extends ProfunctorIndexed<f, X> implements Choice<__<__<Indexed.µ, f>, X>> {
    private final Choice<f> C;
    private ChoiceIndexed(Choice<f> C) { super(C); this.C = C;}

    @Override
    public final <A, B, C> Indexed<f, X, Either<A, C>, Either<B, C>> left(__<__<__<__<Indexed.µ, f>, X>, A>, B> p) {
      return null;
    }

    @Override
    public final <A, B, C> Indexed<f, X, Either<A, B>, Either<A, C>> right(__<__<__<__<Indexed.µ, f>, X>, B>, C> p) {
      return null;
    }
  }

}