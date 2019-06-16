package com.triplethreads.taxiyazapi.Repository;

import com.triplethreads.taxiyazapi.Model.NodeConnection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeConnectionRepository extends CrudRepository<NodeConnection, Long> {

}
