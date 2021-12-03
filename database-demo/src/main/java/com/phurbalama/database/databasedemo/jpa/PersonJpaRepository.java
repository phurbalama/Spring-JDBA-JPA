package com.phurbalama.database.databasedemo.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.phurbalama.database.databasedemo.entity.Person;

@Repository
@Transactional
public class PersonJpaRepository {

	//all operation are stored
	@PersistenceContext
	EntityManager entityManager;
	//connect to the database
	
	public Person findById(int id) {
		return entityManager.find(Person.class, id);
	}
}
