package com.jstanier.slackalytics.api.repository;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.jstanier.slackalytics.api.domain.AuthorCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorCountsRepository {

    @Autowired
    private Session session;

    private PreparedStatement ps;

    @PostConstruct
    public void setup() {
        ps = session.prepare("select * from author_counts where channel = ? ");
    }

    public List<AuthorCounts> getAuthorCountsByChannel(String channel) {

        ResultSet results = session.execute(ps.bind(channel));

        List<AuthorCounts> authorCounts = new ArrayList<>();

        for (Row row : results) {
            AuthorCounts counts = new AuthorCounts(row.getString("channel"),
                    row.getString("author"),
                    row.getDate("date"),
                    row.getLong("messages"));
            authorCounts.add(counts);
        }
        return authorCounts;

    }
}
