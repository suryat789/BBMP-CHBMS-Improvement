package com.chbms.queuemgmt.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("message")
    @NotNull
    private String message;

    @JsonProperty("details")
    @Valid
    private List<DetailsVO> details = Lists.newArrayList();

}
