package com.example.demo.blog;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Blog not found with id: " + id));
    }

    public void addNewBlog(Blog blog) {
        blog.setCreatedAt(LocalDateTime.now());
        blogRepository.save(blog);
    }

    @Transactional
    public void updateBlog(Long id, String title, String content, String category) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(()->{
                    throw new IllegalStateException("blog with id " + id + " not found");
                });
        if(title != null && title.length() > 0) {
            blog.setTitle(title);
        }
        if(content != null && content.length() > 0) {
            blog.setContent(content);
        }
        if(category != null && category.length() > 0) {
            blog.setCategory(category);
        }
        blog.setUpdatedAt(LocalDateTime.now());
    }

    public void deleteBlog(Long id) {
        boolean exists = blogRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("blog with id " + id + " not found");
        }
        blogRepository.deleteById(id);
    }
}
