package com.jstanier.slackalytics.streaming;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import storm.kafka.KafkaSpout;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();

        KafkaSpout kafkaSpout = KafkaSpoutFactory.createKafkaSpout();

        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("slack-message-spout", kafkaSpout);
        builder.setBolt("message-exploder", new MessageExploder()).shuffleGrouping("slack-message-spout");
        builder.setBolt("channel-message-counter", new ChannelMessageCounter()).shuffleGrouping("message-exploder");

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("slackalytics", config, builder.createTopology());

//        try {
//            StormSubmitter.submitTopology("slackalytics", config, builder.createTopology());
//        } catch (AlreadyAliveException e) {
//            System.err.println("Could not submit topology." + e);
//        } catch (InvalidTopologyException e) {
//            System.err.println("Could not submit topology." + e);
//        }
    }
}
