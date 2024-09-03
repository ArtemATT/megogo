package services;

import dto.TimeDTO;
import entities.Time;

import java.net.HttpURLConnection;

public class TimeService extends BaseService {

    private TimeService() {
        super("time");
    }

    private static class InstanceHolder {
        private static final TimeService INSTANCE = new TimeService();
    }

    public static TimeService getInstance() {
        return TimeService.InstanceHolder.INSTANCE;
    }

    public Time getTime() {
        return getSpecification().get()
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .as(TimeDTO.class)
                .data();
    }
}
