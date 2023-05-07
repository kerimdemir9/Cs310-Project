package com.mynba.myNBA.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("news")
@AllArgsConstructor
public class NewsController {
	
	@GetMapping("")
	public Object fetchLatestNews() {
		// REST API Uri
		final String uri = "https://nba-latest-news.p.rapidapi.com/articles";
		
		// GET call headers (RAPID API KEYS)
		HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "7ef721d915msh66b3484fb691e42p15cc7ajsnd3065b0db939");
        headers.add("X-RapidAPI-Host", "nba-latest-news.p.rapidapi.com");
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        // External REST API call
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<Object> result = restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class);

	    return result;
	}

}
