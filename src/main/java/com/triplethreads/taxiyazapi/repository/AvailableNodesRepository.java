package com.triplethreads.taxiyazapi.repository;

import com.triplethreads.taxiyazapi.model.AvailableNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableNodesRepository extends CrudRepository<AvailableNode, Long> {

}
