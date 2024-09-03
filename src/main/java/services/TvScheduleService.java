package services;

import dto.TvScheduleDTO;
import entities.tvschedule.TvSchedule;

import java.net.HttpURLConnection;
import java.util.List;

public class TvScheduleService extends BaseService {

    private TvScheduleService() {
        super("channel");
    }

    private static class InstanceHolder {
        private static final TvScheduleService INSTANCE = new TvScheduleService();
    }

    public static TvScheduleService getInstance() {
        return TvScheduleService.InstanceHolder.INSTANCE;
    }

    public TvSchedule getTvSchedule(long id) {
        List<TvSchedule> tvSchedules = getSpecification().queryParam("video_ids", id)
                .get()
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .as(TvScheduleDTO.class)
                .data();
        verifyListIsNotEmpty(id, tvSchedules);
        return tvSchedules.getFirst();
    }

    private static void verifyListIsNotEmpty(long id, List<TvSchedule> tvSchedules) {
        if (tvSchedules.isEmpty()) {
            throw new AssertionError("TV schedule is not available for video_id [%s]".formatted(id));
        }
    }
}
