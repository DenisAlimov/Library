package com.example.Library.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchDto {
    private int ns;
    private String title;
    private int pageid;
    private int size;
    private int wordcount;
    private String snippet;
    private String timestamp;

    @Override
    public String toString() {
        return String.format("\n ns: %s \n title: %s \n pageId: %s \n size: %s \n wordCount: %s \n snippet: %s \n timeStamp: %s \n", ns, title, pageid, size, wordcount, snippet, timestamp);
    }
}