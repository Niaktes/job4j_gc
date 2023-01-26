package ru.job4j.cache;

import java.io.*;

public class DirFileCache extends AbstractCache<String, String> {

    private static final String SEPARATOR = System.lineSeparator();

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    public String load(String key) {
        File file = new File(cachingDir, key);
        validate(file);
        StringBuilder textFileContent = new StringBuilder();
        try (BufferedReader reader  = new BufferedReader(new FileReader(file))) {
            reader.lines()
                    .forEach(l -> {
                        textFileContent.append(l);
                        textFileContent.append(SEPARATOR);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textFileContent.toString();
    }

    private void validate(File file) {
        if (!file.getName().endsWith(".txt")) {
            throw new IllegalArgumentException("Wrong file format! Only .txt files can be loaded.");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException("File doesn't exist!");
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Entered parameter points to directory!");
        }
    }

}