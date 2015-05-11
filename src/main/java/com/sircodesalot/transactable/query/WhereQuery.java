package com.sircodesalot.transactable.query;

import com.sircodesalot.transactable.schema.Row;

import java.util.function.Predicate;

/**
 * Created by sircodesalot on 15/5/11.
 */
public class WhereQuery<T extends Row> {
  private final Predicate<T> predicate;
  WhereQuery(Predicate<T> predicate) {
    this.predicate = predicate;
  }

  public boolean test(T row) {
    return predicate.test(row);
  }
}
