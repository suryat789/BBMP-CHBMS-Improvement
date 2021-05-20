package com.chbms.queuemgmt.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailsVO {

    @JsonProperty("field")
    private String field;

    @JsonProperty("value")
    private Object value;

    @JsonProperty("location")
    private String location = "body";
    @JsonProperty("issue")

    @NotNull
    private String issue;

    @JsonProperty("description")
    private String description;

}
