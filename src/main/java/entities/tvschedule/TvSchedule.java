package entities.tvschedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TvSchedule(List<Program> programs,
                         @JsonProperty("video_id") Long videoId) {
}
