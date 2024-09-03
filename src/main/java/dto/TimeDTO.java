package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entities.Time;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TimeDTO(Time data) {
}
