package kr.or.connect.reservation.repository.impl;

import kr.or.connect.reservation.model.DisplayInfo;
import kr.or.connect.reservation.repository.queryDsl.DisplayInfoRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Slf4j

public class DisplayInfoRepositoryImpl extends QuerydslRepositorySupport implements DisplayInfoRepositoryCustom {

    public DisplayInfoRepositoryImpl() {
        super(DisplayInfo.class);
    }

}
