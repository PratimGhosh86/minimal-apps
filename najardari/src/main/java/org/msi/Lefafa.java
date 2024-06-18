package org.msi;

import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.IOException;

public class Lefafa {

  public static void main(final String[] args) throws IOException {
    final Console sadaKaloJanla = System.console();
    if (sadaKaloJanla == null && !GraphicsEnvironment.isHeadless()) {
      final String naam =
          Lefafa.class
              .getProtectionDomain()
              .getCodeSource()
              .getLocation()
              .toString()
              .substring(6)
              .replace("%20", " ");
      Runtime.getRuntime()
          .exec(new String[] {"cmd", "/c", "start", "cmd", "/k", "java -jar \"" + naam + "\""});
    } else Najardari.main(new String[0]);
  }
}
