package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entities.tvschedule.TvSchedule;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TvScheduleDTO(List<TvSchedule> data) {
}
