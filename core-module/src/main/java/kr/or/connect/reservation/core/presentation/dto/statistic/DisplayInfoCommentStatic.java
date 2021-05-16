package kr.or.connect.reservation.core.presentation.dto.statistic;

import lombok.Data;

@Data
public class DisplayInfoCommentStatic {
    private long commentCount;
    private double commentScoreSum;

    public DisplayInfoCommentStatic(long commentCount, double commentScoreSum) {
        this.commentCount = commentCount;
        this.commentScoreSum = commentScoreSum;
    }
}
