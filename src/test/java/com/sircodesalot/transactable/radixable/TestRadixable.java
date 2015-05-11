package com.sircodesalot.transactable.radixable;

import com.sircodesalot.transactable.RadixString;
import com.sircodesalot.transactable.Radixable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sircodesalot on 15/5/11.
 */
public class TestRadixable {
  @Test
  public void testSimpleRadixString() {
    String text = "the quick brown fox";
    Radixable string = new RadixString(text);
    for (int index = 0; index < string.length(); index++) {
       assertEquals(string.radixAt(index), text.charAt(index));
    }
  }
}
