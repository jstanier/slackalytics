# slackalytics
Analyse and query your Slack conversation.

*Disclaimer*: this is in no way ready yet. It's very early days. 

## Pre-requisites
This project requires the following applications to be running:

- [Apache Zookeeper](http://zookeeper.apache.org)
- [Apache Kafka](http://kafka.apache.org)
- [slack-to-kafka](https://github.com/jstanier/slack-to-kafka): This pulls messages from the channels that your Slack bot is in and pushes them into Apache Kafka. This project should probably move inside this repository, and I plan to do that soon.
- [Apache Storm](http://storm.apache.org): To process messages from the Kafka queue.
- [Apache Cassandra](http://cassandra.apache.org): To store the aggregated data for querying.

## What does it do?

Slackalytics consists of two applications that allow you to store and query conversation that happens in your Slack channels. It requires you to create a Slack bot and to invite it into the channels you're interested in. The two applications are:

1. A storm topology for processing messages and storing counts in Cassandra
2. An API for querying the data.

## Building and running

### Schema
Run Cassandra and load the schema:
```
cqlsh < schema/cassandra/schema.cdl
```

### Storm topology

Build the fat jar:
``` 
cd storm
gradle shadowJar
```
Then submit it to Storm. You can also run the program with the argument `dev` to run a local in-memory instance.

### API
```
cd api
gradle run
```

The API should then be available at `localhost:8080`.
