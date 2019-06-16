package com.triplethreads.taxiyazapi.Controller;


import com.triplethreads.taxiyazapi.Exception.ResourceNotFoundException;
import com.triplethreads.taxiyazapi.Model.Node;
import com.triplethreads.taxiyazapi.Repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/node")
public class NodeController {
    @Autowired
    private NodeRepository nodeRepository;

    @GetMapping("/all")
    public Iterable<Node> getAllNodes(){
        return nodeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id){
        Optional<Node> optionalTemp = nodeRepository.findById(id);
        if(optionalTemp.isPresent()) {
            Node temp = optionalTemp.get();
            return ResponseEntity.ok(temp);
        }
        else{
            return ResponseEntity.badRequest().body(new ResourceNotFoundException("Node Does not exist"));
        }
    }

    @PostMapping()
    public Node addNode(@RequestBody Node node){

        return nodeRepository.save(node);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteNode(@PathVariable("id") long id){
        nodeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editNode(@PathVariable("id") long id, @RequestBody Node node){
        Optional<Node> optionalTemp = nodeRepository.findById(id);
        if(optionalTemp.isPresent()) {
            Node temp = optionalTemp.get();
            if(node.getName() != null)
                temp.setName(node.getName());
            temp.setLatitude(node.getLatitude());
            temp.setLongitude(node.getLongitude());
            nodeRepository.save(temp);
            return ResponseEntity.ok(temp);
        }
        else{
            return ResponseEntity.badRequest().body(new ResourceNotFoundException("Node Does not exist"));
        }


    }
}
