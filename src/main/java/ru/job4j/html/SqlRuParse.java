package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".forumTable").get(0).child(0).children();
        int count = 0;
        for (Element tr : row) {
            if (count > 0) {
                Element element = tr.child(1).child(0);
                System.out.println(element.attr("href"));
                System.out.println(element.text());
                element = tr.child(5);
                System.out.println(element.text());
            } else {
                count++;
            }
        }
    }
}
