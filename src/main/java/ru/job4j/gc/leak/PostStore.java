package ru.job4j.gc.leak;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private final Map<Integer, Post> posts = new HashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    public Post add(Post post) {
        post.setId(atomicInteger.getAndIncrement());
        posts.put(post.getId(), post);
        return post;
    }

    public void removeAll() {
        posts.clear();
    }

    public Collection<Post> getPosts() {
        return posts.values();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PostStore postStore = (PostStore) o;
        if (!posts.equals(postStore.posts)) {
            return false;
        }
        return atomicInteger.equals(postStore.atomicInteger);
    }

    @Override
    public int hashCode() {
        int result = posts.hashCode();
        result = 31 * result + atomicInteger.hashCode();
        return result;
    }

}