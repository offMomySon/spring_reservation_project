package kr.or.connect.reservation.infrastructure.repository.queryDsl;

import kr.or.connect.reservation.domain.DisplayInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DisplayInfoRepositoryCustom {

    public Page<DisplayInfo> findByDisplayInfoIdAndImageTypePage(String type, long categoryId, Pageable pageable);
}