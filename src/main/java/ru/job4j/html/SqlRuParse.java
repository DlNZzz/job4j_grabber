package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        String url = "https://www.sql.ru/forum/job-offers";
        Document doc = Jsoup.connect(url).get();
        Elements row = doc.select(".postslisttopic");
        for (int i = 0; i < 5; i++) {
            for (Element td : row) {
                Element element = td.child(0);
                System.out.println(element.attr("href"));
                System.out.println(element.text());
                element = td.parent().child(5);
                System.out.println(element.text());
            }
            Element element = doc.select(".sort_options").get(1);
            element = element.child(0).child(0).child(0).child(1 + i);
            url = element.attr("href");
            System.out.println(url);
            doc = Jsoup.connect(url).get();
            row = doc.select(".postslisttopic");
        }
    }
}