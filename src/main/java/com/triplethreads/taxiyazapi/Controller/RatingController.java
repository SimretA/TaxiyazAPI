package com.triplethreads.taxiyazapi.Controller;

import com.triplethreads.taxiyazapi.Model.Rate;
import com.triplethreads.taxiyazapi.Model.Route;
import com.triplethreads.taxiyazapi.Repository.RateRepository;
import com.triplethreads.taxiyazapi.Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
public class RatingController {
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private RouteRepository routeRepository;

    @GetMapping("/{route_id}/{user_id}")
    public Rate getAll(@PathVariable("route_id")long route_id, @PathVariable("user_id")long user_id){
        return rateRepository.findByRouteAndUser(route_id, user_id);


    }

    @PostMapping("/{rating}")
    public void addRating(@PathVariable("rating")int rating, @RequestBody Rate rate){
        if(rateRepository.findByRouteAndUser(rate.getRoute().getId(), rate.getUser().getPhone_no()) == null){
            rateRepository.save(rate);
            Route temp = routeRepository.findById(rate.getRoute().getId());
            temp.setRating((temp.getRating()+rating)/2);
            routeRepository.save(temp);
        }

    }
}
