package uk.co.lnssolutions.fortune.entities;

public class Competition {

	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "{" + "" + "id=" + getId() + "," + "name=" + getName() + "}";
	}

}
