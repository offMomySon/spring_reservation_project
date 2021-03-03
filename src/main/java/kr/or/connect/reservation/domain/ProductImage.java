package kr.or.connect.reservation.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = FileInfo.class)
    @JoinColumn(name = "file_id")
    private FileInfo fileInfo;
}
