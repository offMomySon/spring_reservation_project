package kr.or.connect.reservation.model;

import lombok.Data;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "display_info")
public class DisplayInfo {
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    @Nonnull
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
