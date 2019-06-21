package com.triplethreads.taxiyazapi.repository;

import com.triplethreads.taxiyazapi.model.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends CrudRepository<Rate, Long> {
    Rate findByRouteAndUser(long route_id, long user_id);
}
