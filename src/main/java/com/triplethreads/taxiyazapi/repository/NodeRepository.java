package com.triplethreads.taxiyazapi.repository;

import com.triplethreads.taxiyazapi.model.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends CrudRepository<Node, Long> {
}
