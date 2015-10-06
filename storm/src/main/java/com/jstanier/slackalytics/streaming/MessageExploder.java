package com.jstanier.slackalytics.streaming;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.Map;

public class MessageExploder implements IRichBolt {

    private OutputCollector outputCollector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.outputCollector = collector;
    }

    @Override
    public void execute(Tuple input) {
        Object byteInput = input.getValue(0);
        JSONObject slackMessage = null;
        try {
            byte[] bytes = (byte[]) byteInput;
            String rawInput = new String(bytes, "UTF-8");
            slackMessage = (JSONObject) JSONValue.parse(rawInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputCollector.ack(input);

        if (slackMessage == null) return;

        outputCollector.emit(new Values(parseTimestamp(slackMessage),
                parseChannel(slackMessage),
                parseUser(slackMessage),
                parseMessage(slackMessage)));
    }

    private String parseUser(JSONObject slackMessage) {
        JSONObject sender = (JSONObject) slackMessage.get("sender");
        return (String) sender.get("userName");
    }

    private String parseTimestamp(JSONObject slackMessage) {
        return ((String) slackMessage.get("timeStamp")).split("\\.")[0];
    }

    private String parseMessage(JSONObject slackMessage) {
        return (String) slackMessage.get("messageContent");
    }

    private String parseChannel(JSONObject slackMessage) {
        JSONObject channel = (JSONObject) slackMessage.get("channel");
        return (String) channel.get("name");
    }

    @Override
    public void cleanup() {}

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("timestamp", "channel", "user", "message"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
