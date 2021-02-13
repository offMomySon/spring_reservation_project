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
@Table(name = "display_info")
public class DisplayInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "opening_hours")
    private String openingHours;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "place_lot")
    private String placeLot;

    @Column(name = "place_street")
    private String placeStreet;

    private String tel;

    private String homepage;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "displayInfo")
    private List<ReservationInfo> reservationInfos = new ArrayList();

    @OneToMany(fetch = FetchType.LAZY)
    private Set<DisplayInfoImage> displayinfoImages = new HashSet<>();

    @Nonnull
    public Set<DisplayInfoImage> getDisplayinfoImages() {
        return displayinfoImages;
    }

    public void setDisplayinfoImages(@Nonnull Set<DisplayInfoImage> displayinfoImages) {
        this.displayinfoImages = displayinfoImages;
    }
}
