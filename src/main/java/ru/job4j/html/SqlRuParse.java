package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        BufferedWriter writer =
                new BufferedWriter(new FileWriter("C:\\projects\\job4j_grabber\\db\\bd.txt"));
        StringBuilder builder = new StringBuilder();
        String line = System.lineSeparator();
        for (int i = 0; i < 720; i++) {
            String url = "https://www.sql.ru/forum/job-offers";
            url += "/" + (i + 1);
            Document doc = Jsoup.connect(url).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element element = td.child(0);
                System.out.println(element.attr("href"));
                builder.append(element.attr("href") + line);
                System.out.println(element.text());
                builder.append(element.text() + line);
                element = td.parent().child(5);
                System.out.println(element.text());
                builder.append(element.text() + line);
            }
            writer.write(builder.toString());
            writer.flush();
            builder.setLength(0);
            /*
            Element element = doc.select(".sort_options").get(1);
            element = element.child(0).child(0).child(0).child(1 + i);
            url = element.attr("href");
            System.out.println(url);
            doc = Jsoup.connect(url).get();
            row = doc.select(".postslisttopic");

             */
            //url =
        }
    }
}