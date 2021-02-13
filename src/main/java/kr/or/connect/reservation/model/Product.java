package kr.or.connect.reservation.model;

import kr.or.connect.reservation.model.audite.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "event")
    private String event;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductImage> productImages = new ArrayList();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<DisplayInfo> displayInfos = new ArrayList();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ReservationInfo> reservationInfos = new ArrayList();

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
