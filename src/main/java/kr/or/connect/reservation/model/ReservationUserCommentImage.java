package kr.or.connect.reservation.model;

import javax.annotation.Nonnull;
import javax.persistence.*;

@Entity
@Table(name = "reservation_user_comment_image")
public class ReservationUserCommentImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reservation_info_id")
    private String reservationInfoId;

    @Column(name = "reservation_user_comment_id")
    private String reservationUserCommentId;

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "file_id")
    private FileInfo fileInfo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReservationInfoId() {
        return reservationInfoId;
    }

    public void setReservationInfoId(String reservationInfoId) {
        this.reservationInfoId = reservationInfoId;
    }

    public String getReservationUserCommentId() {
        return reservationUserCommentId;
    }

    public void setReservationUserCommentId(String reservationUserCommentId) {
        this.reservationUserCommentId = reservationUserCommentId;
    }

    @Nonnull
    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(@Nonnull FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

}
