package kr.or.connect.reservation.model;

import javax.annotation.Nonnull;
import javax.persistence.*;

@Entity
@Table(name = "display_info_image")
public class DisplayInfoImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "display_info_id")
    private long displayInfoId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "file_id")
    private FileInfo fileInfo;

    @Nonnull
    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(@Nonnull FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }
}
