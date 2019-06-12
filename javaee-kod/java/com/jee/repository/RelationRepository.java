package com.jee.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jee.model.Relation;

@Repository("relationRepository")
public interface RelationRepository extends CrudRepository<Relation, Long> {
}