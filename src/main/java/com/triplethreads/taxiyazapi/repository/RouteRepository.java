package com.triplethreads.taxiyazapi.repository;

import com.triplethreads.taxiyazapi.model.Route;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {

    Iterable<Route> findRoutesByTitleOrderByPrice(String title);

    @Query(value ="select * from route where title=:routeTitle" , nativeQuery = true)
    Iterable<Route> findRoutesByTitle(@Param("routeTitle") String title);

    Route findById(long id);


    @Query(value = "SELECT * FROM route where  dest_node_id=:dest_id and start_node_id=:start_id order by hops", nativeQuery =true)
    Iterable<Route> findRoutesByDestination(@Param("dest_id") long destination, @Param("start_id") long start);
}
