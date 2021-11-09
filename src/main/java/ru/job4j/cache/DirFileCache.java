package ru.job4j.cache;

import java.io.*;
import java.nio.file.Files;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String text = "";
        try {
            text = Files.readString(new File(cachingDir + "\\" + key).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}