import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.TimeService;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeTests {

    TimeService timeService;

    public TimeTests() {
        timeService = TimeService.getInstance();
    }

    @Test
    @DisplayName("Verify server returns current timestamp")
    public void verifyServerReturnsCurrentTime() {
        int allowedTimeDifferenceInSeconds = 1;
        long nowInGmt = ZonedDateTime.now(ZoneId.of("GMT")).toEpochSecond();
        long serviceTimeGmt = timeService.getTime().timestampGmt();

        Assertions.assertThat(nowInGmt - serviceTimeGmt < allowedTimeDifferenceInSeconds)
                .as("Server time is not within allowed time limits of [%s] seconds".formatted(allowedTimeDifferenceInSeconds))
                .isTrue();
    }
}
