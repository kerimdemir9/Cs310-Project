package com.mynba.myNBA.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mynba.myNBA.RapidApiResponse;
import com.mynba.myNBA.model.Standings;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("standings")
@AllArgsConstructor
public class StandingsController {

	@GetMapping("")
	public Object fetchStandings() {
		// REST API Uri
		final String uri = "https://api-nba-v1.p.rapidapi.com/standings?league=standard&season=2022&conference=";
		
		// GET call headers (RAPID API KEYS)
		HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "7ef721d915msh66b3484fb691e42p15cc7ajsnd3065b0db939");
        headers.add("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com");
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        // External REST API call
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<RapidApiResponse<Object>> eastStandingsRes = restTemplate.exchange(uri + "east", HttpMethod.GET, entity, new ParameterizedTypeReference<RapidApiResponse<Object>>(){});
	    RapidApiResponse<Object> eastStandings = eastStandingsRes.getBody();
	    ResponseEntity<RapidApiResponse<Object>> westStandingsRes = restTemplate.exchange(uri + "west", HttpMethod.GET, entity, new ParameterizedTypeReference<RapidApiResponse<Object>>(){});
	    RapidApiResponse<Object> westStandings = westStandingsRes.getBody();

	    return new Standings(eastStandings.response, westStandings.response);
	}
}
