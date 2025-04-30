package com.rnb.searsapi.product.pojo.out;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Verticals (department) related data wrapper.
 *
 * @author tarung
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Verticals {

	/** The vertical name. */
	@JsonProperty("VerticalName")
	private String verticalName;

	/** The cat group id - defines the search group details. */
	@JsonProperty("CatGroupId")
	private String catGroupId;

	/** The vertical url name. */
	@JsonProperty("VerticalUrlName")
	private String verticalUrlName;

	/** The Vertical url. */
	@JsonProperty("VerticalUrl")
	private String VerticalUrl;

	/**
	 * Gets the vertical name.
	 *
	 * @return the vertical name
	 */
	public String getVerticalName() {
		return verticalName;
	}

	/**
	 * Sets the vertical name.
	 *
	 * @param verticalName the new vertical name
	 */
	public void setVerticalName(final String verticalName) {
		this.verticalName = verticalName;
	}

	/**
	 * Gets the cat group id - defines the search group details.
	 *
	 * @return the cat group id - defines the search group details
	 */
	public String getCatGroupId() {
		return catGroupId;
	}

	/**
	 * Sets the cat group id - defines the search group details.
	 *
	 * @param catGroupId the new cat group id - defines the search group details
	 */
	public void setCatGroupId(final String catGroupId) {
		this.catGroupId = catGroupId;
	}

	/**
	 * Gets the vertical url name.
	 *
	 * @return the vertical url name
	 */
	public String getVerticalUrlName() {
		return verticalUrlName;
	}

	/**
	 * Sets the vertical url name.
	 *
	 * @param verticalUrlName the new vertical url name
	 */
	public void setVerticalUrlName(final String verticalUrlName) {
		this.verticalUrlName = verticalUrlName;
	}

	/**
	 * Gets the vertical url.
	 *
	 * @return the vertical url
	 */
	public String getVerticalUrl() {
		return VerticalUrl;
	}

	/**
	 * Sets the vertical url.
	 *
	 * @param verticalUrl the new vertical url
	 */
	public void setVerticalUrl(final String verticalUrl) {
		VerticalUrl = verticalUrl;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Verticals [verticalName=").append(verticalName).append(", catGroupId=").append(catGroupId)
				.append(", verticalUrlName=").append(verticalUrlName).append(", VerticalUrl=").append(VerticalUrl)
				.append("]");
		return builder.toString();
	}

}
