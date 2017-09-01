package by.epam.training.helper.bean;

import java.sql.Date;
/**
 * 
 * @author Nikolaev Ilya
 *
 */
public class Answer extends BaseEntity {
	
	private int questionId;
	/**
	 * Who create the answer
	 */
	private int userId;
	private String userLogin;
	private String content;
	private Date dateCreated;
	private int averageMark;
	private int markCount;
	
	public Answer() {
		super();
	}

	public Answer(int answerId,int questionId, int userId, String userLogin, String content, Date created, int averageMark,
			int markCont) {
		super(answerId);
		this.questionId = questionId;
		this.userId = userId;
		this.userLogin = userLogin;
		this.content = content;
		this.dateCreated = created;
		this.averageMark = averageMark;
		this.markCount = markCont;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getAverageMark() {
		return averageMark;
	}

	public void setAverageMark(int averageMark) {
		this.averageMark = averageMark;
	}

	public int getMarkCount() {
		return markCount;
	}

	public void setMarkCount(int markCount) {
		this.markCount = markCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + averageMark;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + markCount;
		result = prime * result + questionId;
		result = prime * result + userId;
		result = prime * result + ((userLogin == null) ? 0 : userLogin.hashCode());
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
		Answer other = (Answer) obj;
		if (averageMark != other.averageMark)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (markCount != other.markCount)
			return false;
		if (questionId != other.questionId)
			return false;
		if (userId != other.userId)
			return false;
		if (userLogin == null) {
			if (other.userLogin != null)
				return false;
		} else if (!userLogin.equals(other.userLogin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Answer [questionId=" + questionId + ", userId=" + userId + ", userLogin=" + userLogin + ", content="
				+ content + ", dateCreated=" + dateCreated + ", averageMark=" + averageMark + ", markCont=" + markCount + "]";
	}
}
