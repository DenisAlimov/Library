package com.example.Library.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface WikiService {

    String getWiki(int id) throws JsonProcessingException;

}
