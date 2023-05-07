package com.mynba.myNBA.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mynba.myNBA.model.GameWithStats;
import com.mynba.myNBA.RapidApiResponse;
import com.mynba.myNBA.model.MatchComment;
import com.mynba.myNBA.model.MatchCommentPayload;
import com.mynba.myNBA.repo.MatchCommentRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("matches")
@AllArgsConstructor
public class MatchesController {
	
	@Autowired private MatchCommentRepository matchCommentRepository;
	
	@GetMapping("/all/{date}")
	public Object fetchGamesWhereDateIs(@PathVariable("date") String date) {
		// REST API Uri
		final String uri = "https://api-nba-v1.p.rapidapi.com/games?date=" + date;
		
		// GET call headers (RAPID API KEYS)
		HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "7ef721d915msh66b3484fb691e42p15cc7ajsnd3065b0db939");
        headers.add("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com");
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        // External REST API call
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<Object> result = restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class);

	    return result;
	}
	
	@GetMapping("/{id}")
	public Object fetchGameWhereIdIs(@PathVariable("id") String id) {
		// REST API Uri
		final String uri = "https://api-nba-v1.p.rapidapi.com/games?id=" + id;
		final String teamsStatsUri = "https://api-nba-v1.p.rapidapi.com/games/statistics?id=" + id;
		final String playersStatsUri = "https://api-nba-v1.p.rapidapi.com/players/statistics?game=" + id;
				
		// GET call headers (RAPID API KEYS)
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-RapidAPI-Key", "7ef721d915msh66b3484fb691e42p15cc7ajsnd3065b0db939");
		headers.add("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com");
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);

		// External REST API call
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<RapidApiResponse<Object>> res = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<RapidApiResponse<Object>>(){});
	    RapidApiResponse<Object> game = res.getBody();
	    ResponseEntity<RapidApiResponse<Object>> teamsStatsRes = restTemplate.exchange(teamsStatsUri, HttpMethod.GET, entity, new ParameterizedTypeReference<RapidApiResponse<Object>>(){});
	    RapidApiResponse<Object> teamsStats = teamsStatsRes.getBody();
	    ResponseEntity<RapidApiResponse<Object>> playersStatsRes = restTemplate.exchange(playersStatsUri, HttpMethod.GET, entity, new ParameterizedTypeReference<RapidApiResponse<Object>>(){});
	    RapidApiResponse<Object> playersStats = playersStatsRes.getBody();
	    
	    // GameWithStats object
	    GameWithStats result = new GameWithStats(game.response.get(0), teamsStats.response, playersStats.response);
	    
	    return result;
	}
	
	@PostMapping("/{matchId}/comments")
	public MatchComment saveMatchComment(@RequestBody MatchCommentPayload matchCommentPayload, @PathVariable("matchId") String matchId) {
		MatchComment matchComment = new MatchComment(matchCommentPayload.getDescription(), matchId);
		
		MatchComment commentSaved = matchCommentRepository.save(matchComment);
		return commentSaved;
	}
	
	@GetMapping("/{matchId}/comments")
	public Object getMatchCommentsByMatchId(@RequestParam(defaultValue = "5") int limit, @RequestParam(defaultValue = "0") int page, @PathVariable("matchId") String matchId) {
//		List<MatchComment> matchComments = matchCommentRepository.findByMatchIdIs(matchId);
//		return matchComments;
		try {
			List<MatchComment> matchComments = new ArrayList<MatchComment>();
		    Pageable paging = PageRequest.of(page, limit);
		      
		    Page<MatchComment> pageMatchComments;
		    pageMatchComments = matchCommentRepository.findByMatchIdIs(matchId, paging);

		    matchComments = pageMatchComments.getContent();

		    Map<String, Object> response = new HashMap<>();
		    response.put("matchComments", matchComments);
		    response.put("currentPage", pageMatchComments.getNumber());
		    response.put("totalItems", pageMatchComments.getTotalElements());
		    response.put("totalPages", pageMatchComments.getTotalPages());

		    return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
