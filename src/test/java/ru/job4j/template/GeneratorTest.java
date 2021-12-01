package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GeneratorTest {

    @Ignore
    @Test
    public void produce() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "r", "subject", "b");
        Generator generator = new GeneratorTemplate();
        assertThat(generator.produce(template, map), is("I am a r, Who are b?"));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void produceException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("subject", "b");
        Generator generator = new GeneratorTemplate();
        generator.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void produceExceptionTwo() {
        String template = "I am a ${name}";
        Map<String, String> map = Map.of("name", "r", "subject", "b");
        Generator generator = new GeneratorTemplate();
        generator.produce(template, map);
    }
}