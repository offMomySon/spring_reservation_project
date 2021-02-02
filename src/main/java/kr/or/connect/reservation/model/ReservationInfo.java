package kr.or.connect.reservation.model;

import kr.or.connect.reservation.dto.ReservationRequestResult;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "reservation_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "display_info_id")
    private long displayInfoId;

    @Column(name = "reservation_name")
    private String reservationName;

    @Column(name = "reservation_tel")
    private String reservationTel;

    @Column(name = "reservation_email")
    private String reservationEmail;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "cancel_flag")
    private Boolean cancelFlag;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
    @Nonnull
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_info_id")
    private Set<ReservationUserComment> userComments = new HashSet<>();
    @Nonnull
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_info_id")
    private Set<ReservationInfoPrice> rsvInfoPrices = new HashSet<>();

    public ReservationInfo(long productId, long displayInfoId, String reservationName, String reservationTel,
                           String reservationEmail, Date reservationDate, Date createDate, Date modifyDate, Boolean cancelFlag) {
        this.productId = productId;
        this.displayInfoId = displayInfoId;
        this.reservationName = reservationName;
        this.reservationTel = reservationTel;
        this.reservationEmail = reservationEmail;
        this.reservationDate = reservationDate;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.cancelFlag = cancelFlag;
    }

    public static ReservationInfo createReservationInfo(ReservationRequestResult reservationRequestResult) {
        return new ReservationInfo(
                reservationRequestResult.getProductId(),
                reservationRequestResult.getDisplayInfoId(),
                reservationRequestResult.getReservationName(),
                reservationRequestResult.getReservationTel(),
                reservationRequestResult.getReservationEmail(),
                reservationRequestResult.getReservationDate(),
                reservationRequestResult.getCreateDate(),
                reservationRequestResult.getModifyDate(),
                reservationRequestResult.getCancelFlag()
        );
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getDisplayInfoId() {
        return displayInfoId;
    }

    public void setDisplayInfoId(long displayInfoId) {
        this.displayInfoId = displayInfoId;
    }

    @Nonnull
    public Set<ReservationInfoPrice> getRsvInfoPrices() {
        return rsvInfoPrices;
    }

    public void setRsvInfoPrices(@Nonnull Set<ReservationInfoPrice> rsvInfoPrices) {
        this.rsvInfoPrices = rsvInfoPrices;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public String getReservationTel() {
        return reservationTel;
    }

    public void setReservationTel(String reservationTel) {
        this.reservationTel = reservationTel;
    }

    public String getReservationEmail() {
        return reservationEmail;
    }

    public void setReservationEmail(String reservationEmail) {
        this.reservationEmail = reservationEmail;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Boolean getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(Boolean cancelFlag) {
        this.cancelFlag = cancelFlag;
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

    @Nonnull
    public Set<ReservationUserComment> getUserComments() {
        return userComments;
    }

    public void setUserComments(@Nonnull Set<ReservationUserComment> userComments) {
        this.userComments = userComments;
    }

    @Override
    public String toString() {
        return "ReservationInfo [id=" + id + ", productId=" + productId + ", displayInfoId=" + displayInfoId
                + ", reservationName=" + reservationName + ", reservationTel=" + reservationTel + ", reservationEmail="
                + reservationEmail + ", reservationDate=" + reservationDate + ", cancelFlag=" + cancelFlag
                + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", userComments=" + userComments + "]";
    }
}
