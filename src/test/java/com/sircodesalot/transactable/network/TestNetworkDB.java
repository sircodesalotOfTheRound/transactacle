package com.sircodesalot.transactable.network;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by sircodesalot on 15/5/11.
 */
public class TestNetworkDB {
  @Test
  public void testNetworkDB() {
    NetworkDB network = new NetworkDB();

    network.addNode(new ServerNode());
    network.addNode(new ServerNode());
    network.addNode(new ServerNode());

    assertEquals(network.size(), 3);
  }
}
