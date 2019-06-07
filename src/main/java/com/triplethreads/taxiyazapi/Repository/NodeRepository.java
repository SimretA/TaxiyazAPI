package com.triplethreads.taxiyazapi.Repository;

import com.triplethreads.taxiyazapi.Model.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends CrudRepository<Node, Long> {
    Node findNodeById(long id);
}
