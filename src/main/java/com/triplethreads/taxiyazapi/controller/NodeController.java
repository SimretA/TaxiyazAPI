package com.triplethreads.taxiyazapi.controller;


import com.triplethreads.taxiyazapi.exception.ResourceNotFoundException;
import com.triplethreads.taxiyazapi.graphs.GraphOnAddNode;
import com.triplethreads.taxiyazapi.model.AvailableNode;
import com.triplethreads.taxiyazapi.model.Node;
import com.triplethreads.taxiyazapi.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/node")
public class NodeController {

    private final NodeRepository nodeRepository;
    private final GraphOnAddNode graphOnAddNode;

    @Autowired
    public NodeController(NodeRepository nodeRepository, GraphOnAddNode graphOnAddNode) {
        this.nodeRepository = nodeRepository;
        this.graphOnAddNode = graphOnAddNode;
    }

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
        return getResponseEntity(node, optionalTemp);


    }

    @PutMapping()
    public ResponseEntity<?> putNode(@RequestBody Node node){
        Optional<Node> optionalTemp = nodeRepository.findById(node.getId());
        return getResponseEntity(node, optionalTemp);

    }

    private ResponseEntity<?> getResponseEntity(@RequestBody Node node, Optional<Node> optionalTemp) {
        if(optionalTemp.isPresent()) {
            Node temp = optionalTemp.get();
            if(node.getName() != null)
                temp.setName(node.getName());
            temp.setLatitude(node.getLatitude());
            temp.setLongitude(node.getLongitude());

            for (AvailableNode _node: node.getAvailableNodes()) {
                if (!temp.getAvailableNodes().contains(_node))
                    temp.getAvailableNodes().add(_node);
            }
            try {
                graphOnAddNode.onAddNode(temp);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            nodeRepository.save(temp);
            return ResponseEntity.ok(temp);
        }
        else{
            return ResponseEntity.badRequest().body(new ResourceNotFoundException("Node Does not exist"));
        }
    }
}
