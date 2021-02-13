package kr.or.connect.reservation.dto;

public class CategoryResult {
	private long id;
	private String name;
	private long count;

	public CategoryResult() {
		super();
	}

	public CategoryResult(long id, String name, long count) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CategoryResult [categoryID=" + id + ", categoryName=" + name + ", categoryCount=" + count + "]";
	}

}