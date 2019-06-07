package com.triplethreads.taxiyazapi.Repository;

import com.triplethreads.taxiyazapi.Model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository  extends CrudRepository<Comment, Long> {


    @Query(value ="select * from comment where route_id=:routeID" , nativeQuery = true)
    Iterable<Comment> findCommentsByRouteId(@Param("routeID") long route_Id);


}
