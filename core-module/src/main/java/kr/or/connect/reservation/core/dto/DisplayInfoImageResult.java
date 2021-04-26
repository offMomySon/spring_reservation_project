package kr.or.connect.reservation.core.dto;

import kr.or.connect.reservation.core.domain.DisplayInfo;
import kr.or.connect.reservation.core.domain.DisplayInfoImage;
import kr.or.connect.reservation.core.domain.FileInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DisplayInfoImageResult {
    private long displayInfoImageId;
    private long displayInfoId;
    private long fileId;
    private String fileName;
    private String saveFileName;
    private String contentType;
    private Boolean deleteFlag;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public DisplayInfoImageResult(long displayInfoImageId, long displayInfoId, long fileId, String fileName,
                                  String saveFileName, String contentType, Boolean deleteFlag, LocalDateTime createDate, LocalDateTime modifyDate) {
        this.displayInfoImageId = displayInfoImageId;
        this.displayInfoId = displayInfoId;
        this.fileId = fileId;
        this.fileName = fileName;
        this.saveFileName = saveFileName;
        this.contentType = contentType;
        this.deleteFlag = deleteFlag;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public static DisplayInfoImageResult makeDisplayInfoImageResult(DisplayInfo displayInfo, DisplayInfoImage displayInfoImage, FileInfo fileInfo) {
        return new DisplayInfoImageResult(displayInfoImage.getId(), displayInfo.getId(),
                fileInfo.getId(), fileInfo.getFileName(), fileInfo.getSaveFileName(), fileInfo.getContentType(),
                fileInfo.getDeleteFlag(), fileInfo.getCreateDate(), fileInfo.getModifyDate());
    }
}
