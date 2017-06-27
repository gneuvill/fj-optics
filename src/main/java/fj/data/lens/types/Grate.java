package fj.data.lens.types;

import fj.data.profunctor.Closed;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__4;

public interface Grate<S, T, A, B> extends __4<Grate.µ, S, T, A, B> {
  enum µ {}

  <p> __2<p, S, T> f(Closed<p> C, __<__<p, A>, B> pab);

  default <p> Optic<p, S, T, A, B> optic(Closed<p> C) {
    return pab -> f(C, pab);
  }

  static <S, A> Grate_<S, A> mono(Grate<S, S, A, A> grate) {
    return grate::f;
  }
}