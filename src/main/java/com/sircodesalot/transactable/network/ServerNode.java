package com.sircodesalot.transactable.network;

import com.sircodesalot.transactable.schema.Partition;
import com.sircodesalot.transactable.query.WhereQuery;
import com.sircodesalot.transactable.schema.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sircodesalot on 15/5/11.
 */
public class ServerNode {
  List<Partition> partitions = new ArrayList<>();

  public void addPartition(Partition partition) {
    partitions.add(partition);
  }

  public <T extends Row> Iterable<T> submitQuery(WhereQuery<T> query) {
    Partition<T> first = partitions.get(0);

    List<T> results = new ArrayList<>();
    for (T row : first.rows()) {
      if (query.test(row)) {
        results.add(row);
      }
    }

    return results;
  }
}
