package kr.or.connect.reservation.model;

import lombok.Data;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "event")
    private String event;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modify_date")
    private Date modifyDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductImage> productImages = new ArrayList();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<DisplayInfo> displayInfos = new ArrayList();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Set<ReservationInfo> reservationInfos = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Set<ReservationUserComment> reservationUserComments = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Set<ProductPrice> productPrices = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Promotion> promotions = new ArrayList();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Nonnull
    public Set<ReservationInfo> getReservationInfos() {
        return reservationInfos;
    }

    public void setReservationInfos(@Nonnull Set<ReservationInfo> reservationInfos) {
        this.reservationInfos = reservationInfos;
    }

    @Nonnull
    public Set<ReservationUserComment> getReservationUserComments() {
        return reservationUserComments;
    }

    public void setReservationUserComments(@Nonnull Set<ReservationUserComment> reservationUserComments) {
        this.reservationUserComments = reservationUserComments;
    }

    @Nonnull
    public Set<ProductPrice> getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(@Nonnull Set<ProductPrice> productPrices) {
        this.productPrices = productPrices;
    }


    @Nonnull
    public Category getCategory() {
        return category;
    }

    public void setCategory(@Nonnull Category category) {
        this.category = category;
    }
}
