package com.triplethreads.taxiyazapi.controller;

import com.triplethreads.taxiyazapi.model.Rate;
import com.triplethreads.taxiyazapi.repository.RateRepository;
import com.triplethreads.taxiyazapi.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
public class RatingController {
    private final RateRepository rateRepository;
    private final RouteRepository routeRepository;

    @Autowired
    public RatingController(RateRepository rateRepository, RouteRepository routeRepository) {
        this.rateRepository = rateRepository;
        this.routeRepository = routeRepository;
    }

    @GetMapping("/{route_id}/{user_id}")
    public Rate getAll(@PathVariable("route_id")long route_id, @PathVariable("user_id")long user_id){
        return rateRepository.findByRouteAndUser(route_id, user_id);


    }

    @PostMapping("/{rating}")
    public void addRating(@PathVariable("rating")int rating, @RequestBody Rate rate){
//        if(rateRepository.findByRouteAndUser(rate.getRoute().getId(), rate.getUser().getPhone_no()) == null){
//            rateRepository.save(rate);
//            Route temp = routeRepository.findById(rate.getRoute().getId());
//            temp.setRating((temp.getRating()+rating)/2);
//            routeRepository.save(temp);
//        }

    }
}
