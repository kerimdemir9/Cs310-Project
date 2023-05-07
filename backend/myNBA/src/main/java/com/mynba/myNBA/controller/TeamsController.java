package com.mynba.myNBA.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mynba.myNBA.RapidApiResponse;
import com.mynba.myNBA.model.Team;
import com.mynba.myNBA.model.TeamWithInfo;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("teams")
@AllArgsConstructor
public class TeamsController {

	@GetMapping("")
	public Object fetchAllTeams() {
		// REST API Uri
		final String uri = "https://api-nba-v1.p.rapidapi.com/teams";
		
		// GET call headers (RAPID API KEYS)
		HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "7ef721d915msh66b3484fb691e42p15cc7ajsnd3065b0db939");
        headers.add("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com");
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        // External REST API call
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<RapidApiResponse<Team>> teamsRes = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<RapidApiResponse<Team>>(){});
	    List<Team> teams = teamsRes.getBody().response;
	    ArrayList<Team> teamsFiltered = new ArrayList<Team>();
	    
	    // filter out non NBA teams
	    for (Team team: teams) {
	    	if (team.isNbaFranchise() == true && team.isAllStar() == false) {
	    		teamsFiltered.add((Team) team);
	    	}
	    }
	    
	    // sort alphabetically
	    Collections.sort(teamsFiltered, new Comparator<Team>() {
	        @Override
	        public int compare(Team t1, Team t2) {
	            return t1.getName().compareToIgnoreCase(t2.getName());
	        }
	    });
	    
	    return teamsFiltered;
	}
	
	@GetMapping("/{id}")
	public Object fetchTeamWhereIdIs(@PathVariable("id") String id) {
		// REST API Uri
		final String uri = "https://api-nba-v1.p.rapidapi.com/teams?id=" + id;
		final String playersUri = "https://api-nba-v1.p.rapidapi.com/players?season=2022&team=" + id;
		final String standingsUri = "https://api-nba-v1.p.rapidapi.com/standings?season=2022&league=standard&team=" + id;
		final String statsUri = "https://api-nba-v1.p.rapidapi.com/teams/statistics?season=2022&id=" + id;
				
		// GET call headers (RAPID API KEYS)
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-RapidAPI-Key", "7ef721d915msh66b3484fb691e42p15cc7ajsnd3065b0db939");
		headers.add("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com");
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);

		// External REST API call
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<RapidApiResponse<Object>> res = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<RapidApiResponse<Object>>(){});
	    RapidApiResponse<Object> team = res.getBody();
	    ResponseEntity<RapidApiResponse<Object>> playersRes = restTemplate.exchange(playersUri, HttpMethod.GET, entity, new ParameterizedTypeReference<RapidApiResponse<Object>>(){});
	    RapidApiResponse<Object> players = playersRes.getBody();
	    ResponseEntity<RapidApiResponse<Object>> standingsRes = restTemplate.exchange(standingsUri, HttpMethod.GET, entity, new ParameterizedTypeReference<RapidApiResponse<Object>>(){});
	    RapidApiResponse<Object> standings = standingsRes.getBody();
	    ResponseEntity<RapidApiResponse<Object>> statsRes = restTemplate.exchange(statsUri, HttpMethod.GET, entity, new ParameterizedTypeReference<RapidApiResponse<Object>>(){});
	    RapidApiResponse<Object> stats = statsRes.getBody();
	    
	    // TeamWithInfo object
	    TeamWithInfo result = new TeamWithInfo(team.response.get(0), players.response, standings.response.get(0), stats.response.get(0));
	    
	    return result;
	}
	
}
