package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Time(@JsonProperty("timestamp_gmt") Long timestampGmt,
                   String timezone) {
}
