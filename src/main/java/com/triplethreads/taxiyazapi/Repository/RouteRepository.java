package com.triplethreads.taxiyazapi.Repository;

import com.triplethreads.taxiyazapi.Model.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {

    Iterable<Route> findRoutesByTitleOrderByPrice(String title);

    Route findById(long id);
}
