package com.example.Library.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ApiModel(description = "data model of author entity")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
public class Author {
    @Id
    @JsonProperty("authorId")
    @ApiModelProperty(value = "author id, added from book json", position = 1)
    private int id;

    @JsonProperty("authorFullName")
    @ApiModelProperty(value = "full name of author", example = "Сергей Васильевич Лукьяненко", position = 2)
    private String authorFullName;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<BookAuthor> bookAuthor = new ArrayList<>();

}
