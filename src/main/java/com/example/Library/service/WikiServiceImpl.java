package com.example.Library.service;

import com.example.Library.data.Wiki;
import com.example.Library.web.WikiClient;
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
    public Wiki getWiki(int id) {
        String bookName = bookService.getBookById(id).getBookName();
        return wikiClient.getSearchs(bookName);
    }
}
