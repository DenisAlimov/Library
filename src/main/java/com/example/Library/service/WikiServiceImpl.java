package com.example.Library.service;

import com.example.Library.web.WikiClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Service;

@Service
public class WikiServiceImpl implements WikiService {

    private final BookService bookService;
    private final WikiClient wikiClient;


    public WikiServiceImpl(BookService bookService, WikiClient wikiClient) {
        this.bookService = bookService;
        this.wikiClient = wikiClient;
    }

    @Override
    public String getWiki(int id) throws JsonProcessingException {

        String bookName = bookService.getBookById(id).getBookName();

        ObjectMapper mapper = new ObjectMapper();
        // Для вывода с отступами
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(wikiClient.getSearchs(bookName));
    }
}
