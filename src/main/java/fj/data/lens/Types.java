package fj.data.lens;

import fj.data.lens.internal.Exchange;
import fj.data.lens.internal.Shop;
import fj.typeclasses.data.Profunctor;
import fj.typeclasses.data.Profunctor.Strong;
import org.derive4j.hkt.*;

import static org.derive4j.hkt.HktConfig.Visibility.Same;

@HktConfig(generateIn = "_Optics", withVisibility = Same)
public final class Types {
  private Types() {}

  /**
   *
   */
  public interface Optic<p, S, T, A, B> extends __5<Optic.µ, p, S, T, A, B> {
    enum µ {}

    __2<p, S, T> apply(__<__<p, A>, B> pab);

    static <p, S, A> Optic_<p, S, A> mono(Optic<p, S, S, A, A> o) {
      return o::apply;
    }
  }

  /**
   *
   */
  public interface Optic_<p, S, A> extends __3<Optic_.µ, p, S, A> {
    enum µ {}

    __2<p, S, S> apply_(__<__<p, A>, A> paa);

    default Optic<p, S, S, A, A> poly() {
      return this::apply_;
    }
  }

  /**
   *
   */
  public interface Iso<S, T, A, B> extends __4<Iso.µ, S, T, A, B> {
    enum µ {}

    <p> __2<p, S, T> apply(Profunctor<p> P, __<__<p, A>, B> pab);

    default <p> Optic<p, S, T, A, B> toOptic(Profunctor<p> P) {
      return pab -> apply(P, pab);
    }

    static <S, A> Iso_<S, A> mono(Iso<S, S, A, A> iso) {
      return iso::apply;
    }
  }

  /**
   *
   */
  public interface Iso_<S, A> extends __2<Iso_.µ, S, A> {
    enum µ {}

    <p> __2<p, S, S> apply_(Profunctor<p> P, __<__<p, A>, A> paa);

    default <p> Optic_<p, S, A> toOptic_(Profunctor<p> P) {
      return paa -> apply_(P, paa);
    }

    default Iso<S, S, A, A> poly() {
      return this::apply_;
    }
  }

  /**
   *
   */
  public interface AnIso<S, T, A, B> extends __4<AnIso.µ, S, T, A, B> {
    enum µ {}

    Exchange<A, B, S, T> apply(__<__<__<__<Exchange.µ, A>, B>, A>, B> eab);

    default Optic<__<__<Exchange.µ, A>, B>, S, T, A, B> toOptic() {
      return this::apply;
    }

    static <S, A> AnIso_<S, A> mono(AnIso<S, S, A, A> iso) {
      return iso::apply;
    }
  }

  /**
   *
   */
  public interface AnIso_<S, A> extends __2<AnIso_.µ, S, A> {
    enum µ {}

    Exchange<A, A, S, S> apply_(__<__<__<__<Exchange.µ, A>, A>, A>, A> ea);

    default Optic_<__<__<Exchange.µ, A>, A>, S, A> toOptic_() {
      return this::apply_;
    }

    default AnIso<S, S, A, A> poly() {
      return this::apply_;
    }
  }

  /**
   *
   */
  public interface Lens<S, T, A, B> extends __4<Lens.µ, S, T, A, B> {
    enum µ {}

    <p> __2<p, S, T> apply(Strong<p> S, __<__<p, A>, B> pab);

    default <p> Optic<p, S, T, A, B> toOptic(Strong<p> S) {
      return pab -> apply(S, pab);
    }

    static <S, A> Lens_<S, A> mono(Lens<S, S, A, A> lens) {
      return lens::apply;
    }
  }

  /**
   *
   */
  public interface Lens_<S, A> extends __2<Lens_.µ, S, A> {
    enum µ {}

    <p> __2<p, S, S> apply_(Strong<p> P, __<__<p, A>, A> paa);

    default <p> Optic_<p, S, A> toOptic_(Strong<p> P) {
      return paa -> apply_(P, paa);
    }

    default Lens<S, S, A, A> poly() {
      return this::apply_;
    }
  }

  /**
   *
   */
  interface ALens<S, T, A, B> extends __4<ALens.µ, S, T, A, B> {
    enum µ {}

    Shop<A, B, S, T> apply(__<__<__<__<Shop.µ, A>, B>, A>, B> sab);

    default Optic<__<__<Shop.µ, A>, B>, S, T, A, B> toOptic() {
      return this::apply;
    }

    static <S, A> ALens_<S, A> mono(ALens<S, S, A, A> aLens) {
      return aLens::apply;
    }
  }

  /**
   *
   */
  interface ALens_<S, A> extends __2<ALens_.µ, S, A> {
    enum µ {}

    Shop<A, A, S, S> apply(__<__<__<__<Shop.µ, A>, A>, A>, A> sab);

    default Optic_<__<__<Shop.µ, A>, A>, S, A> toOptic_() {
      return this::apply;
    }

    default ALens<S, S, A, A> poly() {
      return this::apply;
    }
  }


}
