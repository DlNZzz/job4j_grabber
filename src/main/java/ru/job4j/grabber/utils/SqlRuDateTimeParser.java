package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Map<String, String> MONTHS = Map.ofEntries(
            Map.entry("янв", "января"),
            Map.entry("фев", "февраля"),
            Map.entry("мар", "марта"),
            Map.entry("апр", "апреля"),
            Map.entry("май", "мая"),
            Map.entry("июн", "июня"),
            Map.entry("июл", "июля"),
            Map.entry("авг", "августа"),
            Map.entry("сен", "сентября"),
            Map.entry("окт", "октября"),
            Map.entry("ноя", "ноября"),
            Map.entry("дек", "декабря"),
            Map.entry("сегодня", java.time.LocalDateTime.now().getMonth().getValue() + ""),
            Map.entry("вчера",
                    java.time.LocalDateTime.now().minusDays(1).getMonth().getValue() + "")
            );

    @Override
    public LocalDateTime parse(String parse) {
        String[] parseArray = parse.split(" ");
        parseArray[1] = MONTHS.getOrDefault(parseArray[1], parseArray[1]);
        parse = Arrays.stream(parseArray).reduce((x, y) -> x + " " + y).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yy, HH:mm");
        try {
            if (Integer.parseInt(parseArray[1]) >= 0) {
                formatter = DateTimeFormatter.ofPattern("dd M yy, HH:mm");
            }
        } catch (NumberFormatException ignored) { }
        return LocalDateTime.parse(parse, formatter);
    }

    public static void main(String[] args) {
        SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
        System.out.println(sqlRuDateTimeParser.parse("9 сен 21, 12:14"));
    }
}