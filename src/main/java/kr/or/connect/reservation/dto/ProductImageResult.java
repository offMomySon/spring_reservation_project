package kr.or.connect.reservation.dto;

import kr.or.connect.reservation.model.FileInfo;
import kr.or.connect.reservation.model.ProductImage;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImageResult {
    private long productId;
    private long productImageId;
    private String type;
    private long fileInfoId;
    private String fileName;
    private String saveFileName;
    private String contentType;
    private Boolean deleteFlag;
    private Date createDate;
    private Date modifyDate;

    public ProductImageResult(long productId, long productImageId, String type, long fileInfoId, String fileName,
                              String saveFileName, String contentType, Boolean deleteFlag, Date createDate, Date modifyDate) {
        super();
        this.productId = productId;
        this.productImageId = productImageId;
        this.type = type;
        this.fileInfoId = fileInfoId;
        this.fileName = fileName;
        this.saveFileName = saveFileName;
        this.contentType = contentType;
        this.deleteFlag = deleteFlag;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public static ProductImageResult makeProductImageResult(@Nonnull ProductImage productImage) {
        FileInfo fileInfo = productImage.getFileInfo();

        return new ProductImageResult(
                productImage.getProduct().getId(),
                productImage.getId(),
                productImage.getType(),
                fileInfo.getId(), fileInfo.getFileName(), fileInfo.getSaveFileName(), fileInfo.getContentType(),
                fileInfo.getDeleteFlag(), fileInfo.getCreateDate(), fileInfo.getModifyDate());
    }
}
