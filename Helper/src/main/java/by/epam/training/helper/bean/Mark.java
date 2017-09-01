package by.epam.training.helper.bean;
/**
 * 
 * @author Nikolaev Ilya
 *  Mark of the user for the answer

 *
 */
public class Mark extends BaseEntity {

	int userId;
	int answerId;
	int markVolue;
	
	public Mark() {
		super();
	}

	public Mark(int markId,int userId, int answerId, int markVolue) {
		super(markId);
		this.userId = userId;
		this.answerId = answerId;
		this.markVolue = markVolue;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public int getMarkVolue() {
		return markVolue;
	}

	public void setMarkVolue(int markVolue) {
		this.markVolue = markVolue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + answerId;
		result = prime * result + markVolue;
		result = prime * result + userId;
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
		Mark other = (Mark) obj;
		if (answerId != other.answerId)
			return false;
		if (markVolue != other.markVolue)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mark [userId=" + userId + ", answerId=" + answerId + ", markVolue=" + markVolue + "]";
	}
}
