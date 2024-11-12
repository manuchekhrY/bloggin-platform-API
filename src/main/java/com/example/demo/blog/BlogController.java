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

    @PutMapping(path="{id}")
    public void updateBlog(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String category){
        blogService.updateBlog(id, title, content, category);
    }

    @DeleteMapping(path = "{id}")
    public void deleteBlog(@PathVariable("id") Long id) {
        blogService.deleteBlog(id);
    }
}
