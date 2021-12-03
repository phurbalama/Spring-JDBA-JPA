package com.phurbalama.database.databasedemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.phurbalama.database.databasedemo.entity.Person;

@Repository
public class PersonJdbcDAO {

	//talk to database to execute Query
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//custom rowmapper
	class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			return person;
		}
		
	}
	//select * from person
	public List<Person> findAll() {
		
		return jdbcTemplate.query("select * from person", new PersonRowMapper());
	}
	public Person findById(int id) {
		
		return jdbcTemplate.queryForObject("select * from person where id=?", 
				new BeanPropertyRowMapper<Person>(Person.class),new Object[]{id});
	}
	public int deleteById(int id) {
		
		return jdbcTemplate.update("delete from person where id=?", new Object[] {id});
	}
	
	public int deleteByIdAndName(int id, String name) {
		
		return jdbcTemplate.update("delete from person where id=?", new Object[] {id,name});
	}
	public Person findByName(String name) {
		//returns Person Object with name type and return the person object using bean property rowmapper
		return jdbcTemplate.queryForObject("select * from person where name=?", 
				new BeanPropertyRowMapper<Person>(Person.class),new Object[]{name});
	}
	public int insert(Person person) {
		
		return jdbcTemplate.update("insert into person(id, name, location, birth_date) values(?, ?, ?, ?)",
				new Object[] {person.getId(),person.getName(),person.getLocation(),new Timestamp(person.getBirthDate().getTime())});
	}
	
	public int update(Person person) {
			
			return jdbcTemplate.update("update person set name = ?, location = ?, birth_date = ? where id = ?",
					new Object[] {person.getName(),person.getLocation(),new Timestamp(person.getBirthDate().getTime()),person.getId()});
		}
}
