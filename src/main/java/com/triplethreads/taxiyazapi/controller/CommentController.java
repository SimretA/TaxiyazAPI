package com.triplethreads.taxiyazapi.controller;

import com.triplethreads.taxiyazapi.model.Comment;
import com.triplethreads.taxiyazapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/{route_id}")
    public Iterable<Comment> getAll(@PathVariable("route_id") long route_id){
        return commentRepository.findCommentsByRouteId(route_id);
    }

    @PostMapping("")
    public void addComment(@RequestBody Comment comment){
        commentRepository.save(comment);
    }

    @PutMapping("/{route_id}")
    public void editComment(@PathVariable("route_id") long route_id, @RequestBody Comment comment){
        commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id")long id){
        commentRepository.deleteById(id);
    }

}
