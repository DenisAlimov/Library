package com.example.Library.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@ApiModel(description = "data model of author entity")
@Table(name = "authors")
public class Author {
    @Id
    @JsonProperty("authorId")
    @ApiModelProperty(value = "author id, added from book json", position = 1)
    private int id;

    @JsonProperty("authorFullName")
    @ApiModelProperty(value = "full name of author", example = "Dan Brown", position = 2)
    private String authorFullName;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<BookAuthor> bookAuthor = new HashSet<>();

}
