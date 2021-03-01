package kr.or.connect.reservation.model;

import kr.or.connect.reservation.model.audite.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "display_info")
public class DisplayInfo extends BaseEntity {
    public static final long DUMMY_ENTITY = -1;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "displayInfo")
    private List<DisplayInfoImage> displayinfoImages = new ArrayList<>();

    public static DisplayInfo makeDummyDisplayInfo() {
        DisplayInfo displayInfo = new DisplayInfo();
        displayInfo.setId((long) -1);
        return displayInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DisplayInfo)) return false;
        DisplayInfo that = (DisplayInfo) o;
        return Objects.equals(id, that.id) && Objects.equals(openingHours, that.openingHours) && Objects.equals(placeName, that.placeName) && Objects.equals(placeLot, that.placeLot) && Objects.equals(placeStreet, that.placeStreet) && Objects.equals(tel, that.tel) && Objects.equals(homepage, that.homepage) && Objects.equals(email, that.email) && Objects.equals(product, that.product) && Objects.equals(reservationInfos, that.reservationInfos) && Objects.equals(displayinfoImages, that.displayinfoImages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, openingHours, placeName, placeLot, placeStreet, tel, homepage, email);
    }
}
