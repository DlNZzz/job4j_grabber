package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Parse;
import ru.job4j.grabber.Post;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {

    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) throws Exception {
        SqlRuParse sqlRuParse = new SqlRuParse(new SqlRuDateTimeParser());
        List<Post> list = sqlRuParse.list("https://www.sql.ru/forum/job-offers/3");
        for (Post post : list) {
            System.out.println(post);
        }
    }

    @Override
    public List<Post> list(String link) {
        List<Post> list = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = doc.select(".postslisttopic");
        for (Element td : elements) {
            list.add(detail(td.child(0).attr("href")));
        }
        return list;
    }

    @Override
    public Post detail(String link) {
        Post post = new Post();
        post.setLink(link);
        Document doc = null;
        try {
            doc = Jsoup.connect(link).get();
        } catch (IOException e) {
            System.out.println(link);
            e.printStackTrace();
        }
        Element messageHeader = doc.select(".messageHeader").get(0);
        post.setTitle(messageHeader.text());
        Element msgBody = doc.select(".msgBody").get(1);
        post.setDescription(msgBody.text());
        Element msgFooter = doc.select(".msgFooter").get(0);
        String created = msgFooter.text().split("\\[")[0];
        created = created.substring(0, created.length() - 1);
        try {
            post.setCreated(dateTimeParser.parse(created));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return post;
    }
}