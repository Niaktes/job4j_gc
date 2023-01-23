package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class UserGenerator implements Generate {

    public static final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    public static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    public static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    public static final String SEPARATOR = " ";
    public static final int NEW_USERS = 1000;

    private final List<User> users = new ArrayList<>();
    private final Random random;

    private List<String> names;
    private List<String> surnames;
    private List<String> patrons;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }


    @Override
    public void generate() {
        users.clear();
        StringBuilder fullNameBuilder  = new StringBuilder();
        for (int i = 0; i < NEW_USERS; i++) {
            users.add(createRandomUser(fullNameBuilder));
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }

    public List<User> getUsers() {
        return users;
    }

    private void readAll() {
        try {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private User createRandomUser(StringBuilder stringBuilder) {
        stringBuilder.append(surnames.get(random.nextInt(surnames.size())));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(names.get(random.nextInt(names.size())));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(patrons.get(random.nextInt(patrons.size())));
        User user = new User(stringBuilder.toString());
        stringBuilder.setLength(0);
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserGenerator that = (UserGenerator) o;
        if (!Objects.equals(names, that.names)) {
            return false;
        }
        if (!Objects.equals(surnames, that.surnames)) {
            return false;
        }
        if (!Objects.equals(patrons, that.patrons)) {
            return false;
        }
        if (!users.equals(that.users)) {
            return false;
        }
        return random.equals(that.random);
    }

    @Override
    public int hashCode() {
        int result = names != null ? names.hashCode() : 0;
        result = 31 * result + (surnames != null ? surnames.hashCode() : 0);
        result = 31 * result + (patrons != null ? patrons.hashCode() : 0);
        result = 31 * result + users.hashCode();
        result = 31 * result + random.hashCode();
        return result;
    }

}