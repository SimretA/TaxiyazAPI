package com.triplethreads.taxiyazapi.Controller;

import com.triplethreads.taxiyazapi.Model.Comment;
import com.triplethreads.taxiyazapi.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/{route_id}")
    public Iterable<Comment> getAll(@PathVariable("route_id") long route_id){
        return commentRepository.findByRouteId(route_id);
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
