package by.epam.training.helper.bean;

import java.sql.Date;

/**
 * 
 * @author Nikolaev Ilya
 *
 */
public class User extends Entity {
	
	private String name;
	private String surname;
	private String login;
	private String email;
	private String password;
	private Date dateCreated;
	private int countAnswer;
	private int countMark;
	/**
	 * The role is needed. To specify the status of the user
	 */
	private byte role;
	/**
	 *  General rating of the user for the answers
	 */
	private double averageMark;
	/**
	 * Blocked or not user
	 */
	private byte status;
	
	public User() {
		super();
	}

	public User(int userId, String name, String surname, String login, String email, String password, Date createdData,
			int countAnswer, int countMark, byte role, double averageMark, byte status) {
		super(userId);
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.email = email;
		this.password = password;
		this.dateCreated = createdData;
		this.countAnswer = countAnswer;
		this.countMark = countMark;
		this.role = role;
		this.averageMark = averageMark;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getCountAnswer() {
		return countAnswer;
	}

	public void setCountAnswer(int countAnswer) {
		this.countAnswer = countAnswer;
	}

	public int getCountMark() {
		return countMark;
	}

	public void setCountMark(int countMark) {
		this.countMark = countMark;
	}

	public byte getRole() {
		return role;
	}

	public void setRole(byte role) {
		this.role = role;
	}

	public double getAverageMark() {
		return averageMark;
	}

	public void setAverageMark(double averageMark) {
		this.averageMark = averageMark;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(averageMark);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + countAnswer;
		result = prime * result + countMark;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + role;
		result = prime * result + status;
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (Double.doubleToLongBits(averageMark) != Double.doubleToLongBits(other.averageMark))
			return false;
		if (countAnswer != other.countAnswer)
			return false;
		if (countMark != other.countMark)
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		if (status != other.status)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", login=" + login + ", email=" + email + ", password="
				+ password + ", createdData=" + dateCreated + ", countAnswer=" + countAnswer + ", countMark="
				+ countMark + ", role=" + role + ", averageMark=" + averageMark + ", status=" + status + "]";
	}	
}
