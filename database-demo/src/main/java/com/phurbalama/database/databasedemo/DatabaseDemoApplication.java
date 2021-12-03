package com.phurbalama.database.databasedemo;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.phurbalama.database.databasedemo.entity.Person;
import com.phurbalama.database.databasedemo.jdbc.PersonJdbcDAO;

//@SpringBootApplication
public class DatabaseDemoApplication implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonJdbcDAO dao;
	public static void main(String[] args) {
		SpringApplication.run(DatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//fire a query
		dao.findAll();
		logger.info("All user -> {}", dao.findAll());
		logger.info("User id 10001 -> {}", dao.findById(10001));
		logger.info("User id Manga -> {}", dao.findByName("Manga"));
		logger.info("Delete 10002 -> {}",dao.deleteById(10002));
		logger.info("Inserting 10004 -> {}",dao.insert(new Person(10004,"tara","nepal", new Date())));
		logger.info("Update 10003 -> {}",dao.update(new Person(10003 ,"tara","Nepal", new Date())));
	}

}
