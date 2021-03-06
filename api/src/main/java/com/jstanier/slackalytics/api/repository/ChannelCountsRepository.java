package com.jstanier.slackalytics.api.repository;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.jstanier.slackalytics.api.domain.ChannelCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChannelCountsRepository {

    @Autowired
    private Session session;

    private PreparedStatement ps;

    @PostConstruct
    public void setup() {
        ps = session.prepare("select * from channel_counts where channel = ?");
    }

    public List<ChannelCounts> getChannelCountsByChannel(String channel) {
        ResultSet results = session.execute(ps.bind(channel));

        List<ChannelCounts> channelCounts = new ArrayList<>();
        for (Row row : results) {
            channelCounts.add(new ChannelCounts(row.getString("channel"), row.getDate("date"), row.getLong("messages")));
        }
        return channelCounts;
    }
}
