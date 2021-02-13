package kr.or.connect.reservation.dto;

import kr.or.connect.reservation.exception.list.RelatedEntityAbsentException;
import kr.or.connect.reservation.model.FileInfo;
import kr.or.connect.reservation.model.ReservationUserComment;
import kr.or.connect.reservation.model.ReservationUserCommentImage;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentImageResult {
    private Long imageId;
    private Long reservationInfoId;
    private Long reservationUserCommentId;
    private Long fileId;
    private String fileName;
    private String saveFileName;
    private String contentType;
    private Boolean deleteFlag;
    private Date createDate;
    private Date modifyDate;

    public CommentImageResult(Long imageId, Long reservationInfoId, Long reservationUserCommentId, Long fileId,
                              String fileName, String saveFileName, String contentType, Boolean deleteFlag, Date createDate,
                              Date modifyDate) {
        super();
        this.imageId = imageId;
        this.reservationInfoId = reservationInfoId;
        this.reservationUserCommentId = reservationUserCommentId;
        this.fileId = fileId;
        this.fileName = fileName;
        this.saveFileName = saveFileName;
        this.contentType = contentType;
        this.deleteFlag = deleteFlag;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }


    public static CommentImageResult makeCommentImageResult(@Nonnull ReservationUserComment reservationUserComment) {
        ReservationUserCommentImage reservationUserCommentImage = reservationUserComment.getReservationUserCommentImages().stream().findFirst().orElseThrow(() -> {
            throw new RelatedEntityAbsentException();
        });
        FileInfo fileInfo = reservationUserCommentImage.getFileInfo();

        return new CommentImageResult(
                reservationUserCommentImage.getId(),
                reservationUserComment.getReservationInfo().getId(),
                reservationUserComment.getId(),
                fileInfo.getId(),
                fileInfo.getFileName(),
                fileInfo.getSaveFileName(),
                fileInfo.getContentType(),
                fileInfo.getDeleteFlag(),
                fileInfo.getCreateDate(),
                fileInfo.getModifyDate());
    }
}
