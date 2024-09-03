import entities.tvschedule.Program;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import services.TimeService;
import services.TvScheduleService;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TvScheduleTests {

    TvScheduleService tvScheduleService;

    TimeService timeService;

    public TvScheduleTests() {
        tvScheduleService = TvScheduleService.getInstance();
        timeService = TimeService.getInstance();
    }

    @ParameterizedTest(name = "Verify programs are sorted for [{0}] video id")
    @ValueSource(longs = {1639111, 1585681, 1639231})
    public void verifyProgramsSortedByStartTimestamp(long videoId) {
        List<Program> actualPrograms = tvScheduleService.getTvSchedule(videoId).programs();
        List<Program> sortedPrograms = new ArrayList<>(actualPrograms);
        sortedPrograms.sort(Comparator.comparing(Program::startTimestamp));

        Assertions.assertThat(actualPrograms).as("Verify TV programs are sorted by start timestamp").isEqualTo(sortedPrograms);
    }

    @ParameterizedTest(name = "Verify program exists for current timestamp for [{0}] video id")
    @ValueSource(longs = {1639111, 1585681, 1639231})
    public void verifyProgramExistsForCurrentTimestamp(long videoId) {
        long now = Instant.now().atZone(ZoneId.of(timeService.getTime().timezone().strip())).toEpochSecond();
        long actualProgramCount = tvScheduleService.getTvSchedule(videoId).programs().stream()
                .filter(program -> program.startTimestamp() < now && program.endTimestamp() > now)
                .count();

        Assertions.assertThat(1).as("Verify that only one program exists for current timestamp").isEqualTo(actualProgramCount);
    }

    @ParameterizedTest(name = "Verify programs one day ahead are available only for [{0}] video id")
    @ValueSource(longs = {1639111, 1585681, 1639231})
    public void verifyProgramsOneDayAheadAreAvailableOnly(long videoId) {
        Instant instant = Instant.now().atZone(ZoneId.of(timeService.getTime().timezone().strip())).toInstant();
        long now = instant.getEpochSecond();
        long dayAhead = instant.plus(Duration.ofHours(24)).getEpochSecond();
        List<Program> actualPrograms = tvScheduleService.getTvSchedule(videoId).programs();
        actualPrograms.sort(Comparator.comparing(Program::startTimestamp));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPrograms).as("Verify programs list is not empty").isNotEmpty();
        softAssertions.assertThat(actualPrograms.getFirst().endTimestamp() < now).as("Verify there are no outdated TV program").isFalse();
        softAssertions.assertThat(actualPrograms.getLast().startTimestamp() > dayAhead).as("Verify there are no outdated TV program").isFalse();
        softAssertions.assertAll();
    }
}
