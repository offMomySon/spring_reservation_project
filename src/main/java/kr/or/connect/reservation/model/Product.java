package kr.or.connect.reservation.model;

import kr.or.connect.reservation.model.audite.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ReservationUserComment> reservationUserComments = new ArrayList();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductPrice> productPrices = new ArrayList();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Promotion> promotions = new ArrayList();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}
