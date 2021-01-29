package kr.or.connect.reservation.dto;

import java.util.Date;

public class ProductPriceResult {
	private long productPriceId;
	private long productId;
	private String priceTypeName;
	private long price;
	private double discountRate;
	private Date createDate;
	private Date modifyDate;

	public ProductPriceResult() {
	}


	public ProductPriceResult(long productPriceId, long productId, String priceTypeName, long price, double discountRate,
							  Date createDate, Date modifyDate) {
		super();
		this.productPriceId = productPriceId;
		this.productId = productId;
		this.priceTypeName = priceTypeName;
		this.price = price;
		this.discountRate = discountRate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}


	public long getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(long productPriceId) {
		this.productPriceId = productPriceId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getDiscountRate() {
		return (long)discountRate;
	}

	public void setDiscountRate(long discountRate) {
		this.discountRate = (double)discountRate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ProductPriceResult [productPriceId=" + productPriceId + ", productId=" + productId + ", priceTypeName="
				+ priceTypeName + ", price=" + price + ", discountRate=" + discountRate + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + "]";
	}
}
