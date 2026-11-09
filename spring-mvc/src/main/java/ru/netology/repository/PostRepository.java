package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Repository
public class PostRepository {
  private static final long NEW_POST_ID = 0;

  private final ConcurrentMap<Long, Post> posts = new ConcurrentHashMap<>();
  private final AtomicLong idCounter = new AtomicLong(0);

  public List<Post> all() {
   
    return posts.values().stream()
        .sorted(Comparator.comparingLong(Post::getId))
        .collect(Collectors.toList());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

 
  public Post save(Post post) {
    if (post.getId() == NEW_POST_ID) {
      final var newId = idCounter.incrementAndGet();
      post.setId(newId);
      posts.put(newId, post);
      return post;
    }

   
    final var updated = posts.computeIfPresent(post.getId(), (id, old) -> post);
    if (updated == null) {
      throw new NotFoundException("Post with id " + post.getId() + " not found");
    }
    return updated;
  }

  public boolean removeById(long id) {
    return posts.remove(id) != null;
  }
}
