package soasec.jaxws.beans;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3946175455849334568L;
	private int id;
	private String username;
	private int privilege_level;//0: normal user
								//1: moderator
								//2: admin
	
	public User(int id, String username, int priv_lev) {
		super();
		this.id = id;
		this.username = username;
		this.privilege_level = priv_lev;
	}
	
	public User(int id, String username) {
		super();
		this.id = id;
		this.username = username;
		this.privilege_level = 0;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", privilege_level=" + privilege_level + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPrivilege_level() {
		return privilege_level;
	}

	public void setPrivilege_level(int privilege_level) {
		this.privilege_level = privilege_level;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id && Objects.equals(username, other.username);
	}
	

}
