package Resource;

public class User {
	String Name;
	String Job;
	String id;
	String CreatedAt;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(String createdAt) {
		CreatedAt = createdAt;
	}

	public User()
	{
		
	}
	
	public User(String Name, String Job)
	{
		this.Name=Name;
		this.Job=Job;				
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getJob() {
		return Job;
	}

	public void setJob(String job) {
		Job = job;
	}
	
	
}
