package com.example.Library.data;

import com.example.Library.dto.SearchDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Query {
    private List<SearchDto> search;

    public Query(@JsonProperty("search")List<SearchDto> search) {
        this.search = search;
    }
}
