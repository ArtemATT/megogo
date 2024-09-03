package entities.tvschedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Program(@JsonProperty("start_timestamp") Long startTimestamp,
                      @JsonProperty("end_timestamp") Long endTimestamp) {
}
