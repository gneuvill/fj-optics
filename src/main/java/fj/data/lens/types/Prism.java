package fj.data.lens.types;

import fj.data.profunctor.Choice;
import org.derive4j.hkt.__;
import org.derive4j.hkt.__2;
import org.derive4j.hkt.__4;

/**
 * A prism
 */
public interface Prism<S, T, A, B> extends __4<Prism.µ, S, T, A, B> {
  enum µ {}

  <p> __2<p, S, T> f(Choice<p> C, __<__<p, A>, B> pab);

  default <p> Optic<p, S, T, A, B> optic(Choice<p> C) {
    return pab -> f(C, pab);
  }

  static <S, A> Prism_<S, A> mono(Prism<S, S, A, A> p) {
    return p::f;
  }
}
