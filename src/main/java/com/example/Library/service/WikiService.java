package com.example.Library.service;

import com.example.Library.data.Wiki;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public interface WikiService {

    Wiki getWiki(int id) throws JsonProcessingException;
}
