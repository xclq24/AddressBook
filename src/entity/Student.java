package entity;
/**
 * ѧ����
 * 
 * id		ѧ��
 * classid	�༶��
 * name		����
 * 
 * @author Jasper
 *
 */
public class Student {
	private String id;
	private String classid;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", classid=" + classid + ", name=" + name
				+ "]";
	}
	
}
