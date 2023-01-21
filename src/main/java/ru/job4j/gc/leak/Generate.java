package ru.job4j.gc.leak;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Generate {

    void generate();

    default List<String> read(String path) throws IOException {
        List<String> text = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().forEach(text::add);
        }
        return text;
    }

}