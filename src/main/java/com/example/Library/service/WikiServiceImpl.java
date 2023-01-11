package com.example.Library.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WikiServiceImpl implements WikiService {

    private final BookService bookService;

    @Autowired
    private RestTemplate restTemplate;

    public WikiServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public String getWiki(int id) throws JsonProcessingException {

        String bookName = bookService.getBookById(id).getBookName();
        String resourceUrl = "https://ru.wikipedia.org/w/api.php?action=query&list=search&srsearch=" + bookName + "&utf8=&format=json";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(resourceUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseEntity.getBody());
        return root.toString();
    }
}
