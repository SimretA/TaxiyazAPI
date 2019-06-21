package com.triplethreads.taxiyazapi.controller;

import com.triplethreads.taxiyazapi.model.AvailableNode;
import com.triplethreads.taxiyazapi.model.Route;
import com.triplethreads.taxiyazapi.repository.AvailableNodesRepository;
import com.triplethreads.taxiyazapi.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/route")
public class RouteController {
    private final RouteRepository routeRepository;
    private final AvailableNodesRepository availableNodesRepository;

    @Autowired
    public RouteController(RouteRepository routeRepository, AvailableNodesRepository availableNodesRepository) {
        this.routeRepository = routeRepository;
        this.availableNodesRepository = availableNodesRepository;
    }

    @GetMapping("/all")
    public Iterable<Route> getAllRoutes(){
        return routeRepository.findAll();
    }

    @GetMapping("")
    public Iterable<Route> getRouteByTitle(@RequestParam(name = "title") String title){
        System.out.println(title);
        return routeRepository.findRoutesByTitle(title);
    }

    @GetMapping("/dest")
    public Iterable<Route> getRouteByStartAndDestination(@RequestParam("dest_id") long dest_id, @RequestParam("start_id") long start_id){
        return routeRepository.findRoutesByDestination(dest_id, start_id);
    }

    @PostMapping("")
    public ResponseEntity<?> addRoute(@RequestBody AvailableNode availableNode){

         availableNodesRepository.save(availableNode);
        return ResponseEntity.ok().build();

    }
    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable("id") long id){
        routeRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateRoute(@PathVariable("id") long id, @RequestBody Route route){
        Route temp = routeRepository.findById(id);
        if(route.getTitle() != null){
            temp.setTitle(route.getTitle());
        }
        temp.setHops(route.getHops());

        temp.setPrice(route.getPrice());


    }





}

