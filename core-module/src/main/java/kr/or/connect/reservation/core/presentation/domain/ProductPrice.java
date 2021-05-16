package kr.or.connect.reservation.core.presentation.domain;

import kr.or.connect.reservation.core.presentation.domain.audite.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_price")
public class ProductPrice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "price_type_name")
    private String priceTypeName;

    @Column(name = "price")
    private long price;

    @Column(name = "discount_rate")
    private double discountRate;

}
