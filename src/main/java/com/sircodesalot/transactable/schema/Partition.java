package com.sircodesalot.transactable.schema;

/**
 * Created by sircodesalot on 15/5/11.
 */
public interface Partition<T extends Row> {
  Iterable<T> rows();
}
