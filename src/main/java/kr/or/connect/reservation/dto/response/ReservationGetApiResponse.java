package kr.or.connect.reservation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.or.connect.reservation.dto.ReservationResponseResult;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationGetApiResponse {
    @JsonProperty("reservations")
    private List<ReservationResponseResult> reservationResponseResults;
    private long size;

    public ReservationGetApiResponse(@NotNull List<ReservationResponseResult> reservationResponseResults, long size) {
        this.reservationResponseResults = reservationResponseResults;
        this.size = size;
    }

    @Nonnull
    public static ReservationGetApiResponse createReservationGetApiResponse(@Nonnull List<ReservationResponseResult> reservationResponseResults) {
        ReservationGetApiResponse reservationGetApiResponse = new ReservationGetApiResponse(reservationResponseResults, reservationResponseResults.size());
        return reservationGetApiResponse;
    }
}