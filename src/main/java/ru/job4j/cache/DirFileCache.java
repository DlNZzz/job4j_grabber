package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        var value = cache.getOrDefault(key, null);
        if (null != value) {
            return String.valueOf(value);
        }
        String text = "";
        try {
            FileReader fileReader = new FileReader(cachingDir);
            BufferedReader reader = new BufferedReader(fileReader);
            while (reader.ready()) {
                text += reader.readLine();
                text += System.lineSeparator();
            }
            cache.put(key, new SoftReference<>(text));
            fileReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

}