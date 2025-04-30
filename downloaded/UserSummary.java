package com.example.demo.payload;

/**
 * @author yosuk
 *
 */
public class UserSummary {

	private Long id;

	private String username;

	private String userId;

	/**
	 * Constructs an <code>UserSummary.java</code> object.
	 * @param id
	 * @param username
	 * @param userId
	 */
	public UserSummary(Long id, String username, String userId) {
		this.id = id;
		this.username = username;
		this.userId = userId;
	}

	/**
	 * Gets the id
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the username
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the userId
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the userId
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


}
