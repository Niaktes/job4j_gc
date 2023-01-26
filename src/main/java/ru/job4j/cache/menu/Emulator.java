package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {

    private static final int PUT_TO_CACHE = 1;
    private static final int GET_FROM_CACHE = 2;

    private static final String ENTER_FILE_NAME = "Please, enter file name:";

    private static final String MENU = """
                Enter 1 if you want to cache file in specified directory;
                Enter 2 if you want to read file content from cache;
                Enter any other number if you want to quit.
            """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter caching directory:");
        String cachingDirectory = scanner.nextLine();
        DirFileCache cache = new DirFileCache(cachingDirectory);
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (PUT_TO_CACHE == userChoice) {
                System.out.println(ENTER_FILE_NAME);
                String fileName = scanner.nextLine();
                String fileContent = cache.load(fileName);
                cache.put(fileName, fileContent);
            } else if (GET_FROM_CACHE == userChoice) {
                System.out.println(ENTER_FILE_NAME);
                String fileName = scanner.nextLine();
                System.out.println(cache.get(fileName));
            } else {
                run = false;
                System.out.println("Good luck!");
            }
        }
    }

}
