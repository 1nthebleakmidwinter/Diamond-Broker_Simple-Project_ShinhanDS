package com.personal;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

class KeyHooker implements NativeKeyListener {
  private String val="";
  @Override
  public void nativeKeyPressed(NativeKeyEvent e) {val = NativeKeyEvent.getKeyText(e.getKeyCode());}
  public void nativeKeyReleased(NativeKeyEvent e) {val = "";}
  public void nativeKeyTyped(NativeKeyEvent e) {}
  String getKey() {return val;}
}