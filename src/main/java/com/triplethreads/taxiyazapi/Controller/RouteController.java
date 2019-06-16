package com.triplethreads.taxiyazapi.Controller;

import com.triplethreads.taxiyazapi.Model.NodeConnection;
import com.triplethreads.taxiyazapi.Model.Route;
import com.triplethreads.taxiyazapi.Repository.NodeConnectionRepository;
import com.triplethreads.taxiyazapi.Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/route")
public class RouteController {
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private NodeConnectionRepository nodeConnectionRepository;

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
    public ResponseEntity<?> addRoute(@RequestBody NodeConnection nodeConnection){

         nodeConnectionRepository.save(nodeConnection);
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

