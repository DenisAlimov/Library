package com.example.Library.mapper;

import com.example.Library.dto.AuthorDto;
import com.example.Library.request.AuthorRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto authorRequestToAuthorDto(AuthorRequest AuthorRequest);

    AuthorRequest AuthorDtoToAuthorRequest(AuthorDto AuthorDto);

}
