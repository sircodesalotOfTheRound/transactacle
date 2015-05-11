package com.sircodesalot.transactable.network;

import com.sircodesalot.transactable.query.WhereQuery;
import com.sircodesalot.transactable.schema.Row;
import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sircodesalot on 15/5/11.
 */
public class NetworkDB {
  List<ServerNode> nodes = new ArrayList<>();

  public void addNode(ServerNode serverNode) {
    nodes.add(serverNode);
  }

  public int size() {
    return nodes.size();
  }

  public <T extends Row> Iterable<T> performQuery(WhereQuery<T> query) {
    List<T> results = new ArrayList<>();
    for (ServerNode node : nodes) {
      for (T result : node.submitQuery(query)) {
        results.add(result);
      }
    }

    return results;
  }
}
