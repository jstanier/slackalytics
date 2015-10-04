package com.jstanier.slackalytics.streaming;

import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;

public class KafkaSpoutFactory {

    public static KafkaSpout createKafkaSpout() {
        String zookeeperHost = "localhost:2181";
        String kafkaTopic = "slack.messages";

        String zookeeperRoot = "/slack-message-spout";
        String zookeeperSpoutId = "slack-message-spout";
        ZkHosts zookeeperHosts = new ZkHosts(zookeeperHost);

        return new KafkaSpout(new SpoutConfig(zookeeperHosts, kafkaTopic, zookeeperRoot, zookeeperSpoutId));
    }
}
