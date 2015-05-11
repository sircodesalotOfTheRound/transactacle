package com.sircodesalot.transactable.query;

import com.sircodesalot.transactable.schema.Partition;
import com.sircodesalot.transactable.network.NetworkDB;
import com.sircodesalot.transactable.network.ServerNode;
import com.sircodesalot.transactable.schema.Row;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sircodesalot on 15/5/11.
 */
public class TestWhere {
  public class MyTable implements Partition<MyTableRow> {
    List<MyTableRow> rows = new ArrayList<>();

    public MyTable add(MyTableRow row) {
      this.rows.add(row);
      return this;
    }

    @Override
    public Iterable<MyTableRow> rows() {
      return rows;
    }
  }

  public class MyTableRow implements Row {
    private int id;
    private String first;
    private String last;

    public MyTableRow(int id, String first, String last) {
      this.id = id;
      this.first = first;
      this.last = last;
    }

    public int id() { return this.id; }
    public String first() { return this.first; }
    public String last() { return this.last; }

    @Override
    public String toString() {
      return String.format("%s\t%s\t%s", id, first, last);
    }
  }

  @Test
  public void testWhere() {
    MyTable table = new MyTable();
    WhereQuery query = new WhereQuery<MyTableRow>(row -> row.first().length() <= 5);
    NetworkDB networkDB = new NetworkDB();

    ServerNode firstServer = new ServerNode();
    MyTable partition1 = new MyTable()
      .add(new MyTableRow(0, "samuel", "smith"))
      .add(new MyTableRow(3, "paul", "holly"))
      .add(new MyTableRow(4, "tony", "greenman"));

    firstServer.addPartition(partition1);

    ServerNode secondServer = new ServerNode();
    MyTable partition2 = new MyTable()
      .add(new MyTableRow(0, "samuel", "smith"))
      .add(new MyTableRow(1, "james", "jones"))
      .add(new MyTableRow(5, "patricia", "vincent"))
      .add(new MyTableRow(6, "richard", "mcqueen"));

    secondServer.addPartition(partition2);

    ServerNode thirdServer = new ServerNode();
    MyTable partition3 = new MyTable()
      .add(new MyTableRow(2, "harris", "williams"))
      .add(new MyTableRow(5, "patricia", "vincent"))
      .add(new MyTableRow(6, "richard", "mcqueen"));

    thirdServer.addPartition(partition3);

    networkDB.addNode(firstServer);
    networkDB.addNode(secondServer);
    networkDB.addNode(thirdServer);

    Iterable<MyTableRow> results = networkDB.performQuery(query);
    for (MyTableRow row : results) {
      System.out.println(row);
    }
  }
}
