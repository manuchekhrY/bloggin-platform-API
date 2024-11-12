package com.example.demo.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public List<Blog> getBlogs() {
        return blogService.getBlogs();
    }

    @PostMapping
    public void addNewBlog(@RequestBody Blog blog){
        blogService.addNewBlog(blog);
    }

}
