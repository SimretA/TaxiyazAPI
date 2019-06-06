package com.triplethreads.taxiyazapi.Controller;

import com.triplethreads.taxiyazapi.Model.Route;
import com.triplethreads.taxiyazapi.Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/route")
public class RouteController {
    @Autowired
    private RouteRepository routeRepository;

    @GetMapping("/all")
    public Iterable<Route> getAllRoutes(){
        return routeRepository.findAll();
    }

    @GetMapping("")
    public Iterable<Route> getRouteByTitle(@RequestParam(name = "title") String title){
        return routeRepository.findRoutesByTitleOrderByPrice(title);
    }

    @PostMapping("")
    public void addRoute(@RequestBody Route route){
        routeRepository.save(route);

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

