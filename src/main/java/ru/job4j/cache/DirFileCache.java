package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String value = String.valueOf(cache.get(key));
        if (value != null) {
            return value;
        }
        try {
            FileReader fileReader = new FileReader(key);
            BufferedReader reader = new BufferedReader(fileReader);
            value = "";
            while (reader.ready()) {
                value += reader.readLine();
            }
            fileReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

}