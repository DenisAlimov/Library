package com.example.Library.web;

import com.example.Library.data.Wiki;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "wiki-client", url = "${wiki.path}")
public interface WikiClient {

    @RequestMapping(method = RequestMethod.GET,
            value = "api.php?action=query&list=search&srsearch={bookName}&utf8=&format=json",
            produces = "application/json")
    Wiki getSearchs(@RequestParam("bookName") String bookName);
}