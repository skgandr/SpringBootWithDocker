package user.model;

import java.io.Serializable;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
@SuppressWarnings("serial")

/*
 * (non-Javadoc)
 * 
 * @see java.lang.Object#toString()
 */

/*
 * (non-Javadoc)
 * 
 * @see java.lang.Object#toString()
 */
@Data
public class User implements Serializable {

	/** The id. */
	private long id;

	/** The name. */
	private String name;

	/** The age. */
	private int age;

	/** The salary. */
	private double salary;

	/**
	 * Instantiates a new user.
	 */
	public User() {
		id = 0;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param age
	 *            the age
	 * @param salary
	 *            the salary
	 */
	public User(long id, String name, int age, double salary) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

}