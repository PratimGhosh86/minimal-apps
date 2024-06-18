package org.msi;

import java.awt.AWTException;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;

public class Najardari {

  static Map<String, Integer> noraChorarSimana = new HashMap<>();
  static Map<String, Integer> aagerJaygata = new HashMap<>();

  public static void main(final String[] args) {
    lekho("\rdekhi to kere!!");
    pardarMaapTaDekhaJaak();
    idurTaKothayeAcheDekhaJak();
    try {
      Thread.sleep(5000L);
      kichuEktaKorteiHobe();
    } catch (final AWTException e) {
      e.printStackTrace();
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  static void kichuEktaKorteiHobe() throws AWTException, InterruptedException {
    if (ektaSonkhyaDaoToErBhetore(0, 9) % 3 == 0) janlaPaltao();
    else idurBhagao();
    idurTaKothayeAcheDekhaJak();
    Thread.sleep(5000L);
    kichuEktaKorteiHobe();
  }

  static void idurBhagao() throws AWTException {
    if (idurKiNoraNoriKoreni()) {
      lekho("\rpichle gelam to!!");
      final var mLoc = MouseInfo.getPointerInfo().getLocation();
      tukKoreHurkiyeJao( //
          mLoc.x,
          ektaSonkhyaDaoToErBhetore(
              noraChorarSimana.get("chotoX"), noraChorarSimana.get("boroX")), //
          mLoc.y,
          ektaSonkhyaDaoToErBhetore(
              noraChorarSimana.get("chotoY"), noraChorarSimana.get("boroY")), //
          1000 //
          );
    }
  }

  static void tukKoreHurkiyeJao(
      final int shururX, final int sesherX, final int shururY, final int sesherY, final int dhaap)
      throws AWTException {
    final var robo = new Robot();
    final var dx = (sesherX - shururX) / ((double) dhaap);
    final var dy = (sesherY - shururY) / ((double) dhaap);
    final var dt = 1 / ((double) dhaap);
    for (var step = 1; step <= dhaap; step++) {
      robo.delay((int) dt);
      robo.mouseMove((int) (shururX + dx * step), (int) (shururY + dy * step));
    }
  }

  static void janlaPaltao() throws AWTException {
    if (idurKiNoraNoriKoreni()) {
      lekho("\rpalte dilam kintu!!");
      final var robo = new Robot();
      robo.keyPress(KeyEvent.VK_ALT);
      var kotobar = ektaSonkhyaDaoToErBhetore(3, 9);
      while (--kotobar > 0) {
        robo.keyPress(KeyEvent.VK_TAB);
        robo.delay(100);
      }
      robo.keyRelease(KeyEvent.VK_ALT);
      robo.keyRelease(KeyEvent.VK_TAB);
      robo.delay(1000);
      dekhoToKonJanlaTaKhola(robo);
    }
  }

  static void dekhoToKonJanlaTaKhola(final Robot robo) {
    final var buffer = new char[4096];
    final var hwnd = User32.INSTANCE.GetForegroundWindow();
    User32.INSTANCE.GetWindowText(hwnd, buffer, 2048);
    final var naam = Native.toString(buffer);
    if (naam.contains("Eclipse") || naam.contains("Spring Tool Suite"))
      dhamnaEclipseErPaglamiSamlao(robo);
    else normalJanlaTeNaraNariKoro(robo);
  }

  static void dhamnaEclipseErPaglamiSamlao(final Robot robo) {
    lekho("\rdhur chai abar eclipse!!");
    robo.delay(500);
    robo.keyPress(KeyEvent.VK_CONTROL);
    robo.keyPress(KeyEvent.VK_F6);
    robo.keyRelease(KeyEvent.VK_CONTROL);
    robo.keyRelease(KeyEvent.VK_F6);
    var kotobar = ektaSonkhyaDaoToErBhetore(2, 6);
    while (--kotobar > 0) {
      robo.delay(100);
      robo.keyPress(KeyEvent.VK_DOWN);
    }
    robo.keyRelease(KeyEvent.VK_DOWN);
    robo.keyPress(KeyEvent.VK_ENTER);
    robo.keyRelease(KeyEvent.VK_ENTER);
  }

  static void normalJanlaTeNaraNariKoro(final Robot robo) {
    // bhodro sobhyo application gulor jonno eitai jothesto
    lekho("\rahaa ki bhalo janla!!");
    robo.delay(500);
    robo.keyPress(KeyEvent.VK_CONTROL);
    robo.keyPress(KeyEvent.VK_TAB);
    robo.keyRelease(KeyEvent.VK_CONTROL);
    robo.keyRelease(KeyEvent.VK_TAB);
  }

  static void pardarMaapTaDekhaJaak() {
    final var gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    final var prastha = gd.getDisplayMode().getWidth();
    final var ucchata = gd.getDisplayMode().getHeight();
    noraChorarSimana.put("boroX", prastha / 2 + 100);
    noraChorarSimana.put("chotoX", prastha / 2 - 200);
    noraChorarSimana.put("boroY", ucchata / 2 + 200);
    noraChorarSimana.put("chotoY", ucchata / 2 - 100);
  }

  static void idurTaKothayeAcheDekhaJak() {
    final var mLoc = MouseInfo.getPointerInfo().getLocation();
    aagerJaygata.put("X", mLoc.x);
    aagerJaygata.put("Y", mLoc.y);
  }

  static int ektaSonkhyaDaoToErBhetore(final int shuru, final int sesh) {
    return new SecureRandom().nextInt(shuru, sesh);
  }

  static boolean idurKiNoraNoriKoreni() {
    final var mLoc = MouseInfo.getPointerInfo().getLocation();
    final var noreni = mLoc.x == aagerJaygata.get("X") || mLoc.y == aagerJaygata.get("X");
    if (!noreni) lekho("\ridur norche kintu!!");
    return noreni;
  }

  public static void lekho(final String s) {
    System.out.printf("%-25s", s);
  }
}
