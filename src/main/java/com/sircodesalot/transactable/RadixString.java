package com.sircodesalot.transactable;

import com.sircodesalot.transactable.Radixable;

/**
 * Created by sircodesalot on 15/5/11.
 */
public class RadixString implements Radixable {
  private final String text;
  public RadixString(String text) {
    this.text = text;
  }

  @Override
  public int radixAt(int index) {
    return text.charAt(index);
  }

  @Override
  public int length() {
    return text.length();
  }
}
