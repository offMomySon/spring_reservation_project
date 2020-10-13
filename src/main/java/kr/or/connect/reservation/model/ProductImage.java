package kr.or.connect.reservation.model;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_image")
public class ProductImage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "product_id")
	private long productId;
	
	private String type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Nonnull
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = FileInfo.class)
	@JoinColumn(name = "file_id")
	private FileInfo fileInfo;

	@Nonnull
	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(@Nonnull FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

}
