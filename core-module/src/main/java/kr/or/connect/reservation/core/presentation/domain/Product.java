package kr.or.connect.reservation.core.presentation.domain;

import kr.or.connect.reservation.core.presentation.domain.audite.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
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

    @Column(name = "reserved_count")
    private Long reservedCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
