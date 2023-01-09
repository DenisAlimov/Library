package com.example.Library.mapper;

import com.example.Library.data.Book;
import com.example.Library.dto.BookDto;
import com.example.Library.request.BookRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto bookRequestToBookDto(BookRequest bookRequest);

    BookRequest bookDtoToBookRequest(Book book);

}
