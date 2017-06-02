package fj.data.lens;

import fj.F;
import fj.F2;
import fj.P;
import fj.P2;
import fj.data.lens.Types.Lens;
import fj.data.lens.Types.Lens_;
import fj.typeclasses.data.Profunctor.Strong;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;

public final class Lenses {
  private Lenses() {}

  public static <S, T, A, B> Lens<S, T, A, B> _lens(F<S, P2<A, F<B, T>>> fp) {
    return new Lens<S, T, A, B>() {
      public <p> __2<p, S, T> apply(Strong<p> S, __<__<p, A>, B> pab) {
        return S.dimap(fp, p -> p._2().f(p._1()), S.first(pab));
      }
    };
  }

  public static <S, T, A, B> Lens<S, T, A, B> lens(F<S, A> get, F2<S, B, T> set) {
    return _lens(s -> P.p(get.f(s), b -> set.f(s, b)));
  }

  public static <S, A> Lens_<S, A> lens_(F<S, A> get, F2<S, A, S> set) {
    return Lens.mono(lens(get, set));
  }
}

