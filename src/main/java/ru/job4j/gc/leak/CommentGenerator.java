package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class CommentGenerator implements Generate {

    public static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";

    public static final String SEPARATOR = System.lineSeparator();
    private final List<Comment> comments = new ArrayList<>();
    public static final int COUNT = 50;
    private List<String> phrases;
    private final UserGenerator userGenerator;
    private final Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.random = random;
        this.userGenerator = userGenerator;
        readPhrases();
    }

    @Override
    public void generate() {
        comments.clear();
        StringBuilder commentBuilder = new StringBuilder();
        for (int i = 0; i < COUNT; i++) {
            comments.add(createRandomComment(commentBuilder, userGenerator.randomUser()));
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    private void readPhrases() {
        try {
            phrases = read(PATH_PHRASES);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Comment createRandomComment(StringBuilder stringBuilder, User user) {
        stringBuilder.append(phrases.get(random.nextInt(phrases.size())));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(phrases.get(random.nextInt(phrases.size())));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(phrases.get(random.nextInt(phrases.size())));
        Comment comment = new Comment(stringBuilder.toString(), user);
        stringBuilder.setLength(0);
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommentGenerator that = (CommentGenerator) o;
        if (!comments.equals(that.comments)) {
            return false;
        }
        if (!Objects.equals(phrases, that.phrases)) {
            return false;
        }
        if (!userGenerator.equals(that.userGenerator)) {
            return false;
        }
        return random.equals(that.random);
    }

    @Override
    public int hashCode() {
        int result = comments.hashCode();
        result = 31 * result + (phrases != null ? phrases.hashCode() : 0);
        result = 31 * result + userGenerator.hashCode();
        result = 31 * result + random.hashCode();
        return result;
    }
}