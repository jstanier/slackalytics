package com.jstanier.slackalytics.streaming;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import org.joda.time.DateTime;

import java.util.Map;

public class ChannelMessageCounter implements IRichBolt {

    private OutputCollector outputCollector;
    private Cluster cassandra;
    private Session session;
    private PreparedStatement ps;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.outputCollector = collector;
        cassandra = Cluster.builder().addContactPoint("localhost").build();
        session = cassandra.connect("slackalytics");
        ps = session.prepare("UPDATE channel_counts SET messages = messages + ? " +
                "WHERE channel = ? AND date = ?");
    }

    @Override
    public void execute(Tuple input) {
        String channel = input.getString(1);
        DateTime date = new DateTime().hourOfDay().roundFloorCopy();
        session.execute(ps.bind(1L, channel, date.toDate()));
        outputCollector.ack(input);
    }

    @Override
    public void cleanup() {
        cassandra.close();
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {}

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
