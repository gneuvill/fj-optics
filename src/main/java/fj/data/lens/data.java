package fj.data.lens;

import org.derive4j.Data;
import org.derive4j.Derive;
import org.derive4j.Flavour;
import org.derive4j.Visibility;

@Data(flavour = Flavour.FJ
  , value = @Derive(inClass = "_{ClassName}", withVisibility = Visibility.Same))
public @interface data {}
