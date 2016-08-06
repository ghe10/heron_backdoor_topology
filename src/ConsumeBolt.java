import java.util.Map;

import com.twitter.heron.api.bolt.BaseRichBolt;
import com.twitter.heron.api.bolt.OutputCollector;
import com.twitter.heron.api.topology.OutputFieldsDeclarer;
import com.twitter.heron.api.topology.TopologyContext;
import com.twitter.heron.api.tuple.Tuple;

/**
 * Created by TAISHI on 8/6/16.
 */
public class ConsumeBolt extends BaseRichBolt {
  public void prepare(Map<String, Object> map,
                      TopologyContext topologyContext,
                      OutputCollector outputCollector) {

  }

  public void execute(Tuple tuple) {
    // do nothing
  }

  public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

  }
}
