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

import com.mynba.myNBA.RapidApiResponse;
import com.mynba.myNBA.model.PlayerComment;
import com.mynba.myNBA.model.PlayerCommentPayload;
import com.mynba.myNBA.model.PlayerWithStats;
import com.mynba.myNBA.repo.PlayerCommentRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("players")
@AllArgsConstructor
public class PlayersController {
	
	@Autowired private PlayerCommentRepository playerCommentRepository;

	@GetMapping("/{id}")
	public Object fetchPlayerWhereIdIs(@PathVariable("id") String id) {
		// REST API Uri
		final String uri = "https://api-nba-v1.p.rapidapi.com/players?id=" + id;
		final String statsUri = "https://api-nba-v1.p.rapidapi.com/players/statistics?season=2022&id=" + id;
				
		// GET call headers (RAPID API KEYS)
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-RapidAPI-Key", "7ef721d915msh66b3484fb691e42p15cc7ajsnd3065b0db939");
		headers.add("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com");
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);

		// External REST API call
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<RapidApiResponse<Object>> res = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<RapidApiResponse<Object>>(){});
	    RapidApiResponse<Object> player = res.getBody();
	    ResponseEntity<RapidApiResponse<Object>> statsRes = restTemplate.exchange(statsUri, HttpMethod.GET, entity, new ParameterizedTypeReference<RapidApiResponse<Object>>(){});
	    RapidApiResponse<Object> stats = statsRes.getBody();
	    
	    // PlayerWithStats object
	    PlayerWithStats result = new PlayerWithStats(player.response.get(0), stats.response);
	    
	    return result;
	}
	
	@PostMapping("/{playerId}/comments")
	public PlayerComment savePlayerComment(@RequestBody PlayerCommentPayload playerCommentPayload, @PathVariable("playerId") String playerId) {
		PlayerComment playerComment = new PlayerComment(playerCommentPayload.getDescription(), playerId);
		
		PlayerComment commentSaved = playerCommentRepository.save(playerComment);
		return commentSaved;
	}
	
	@GetMapping("/{playerId}/comments")
	public Object getPlayerCommentsByPlayerId(@RequestParam(defaultValue = "5") int limit, @RequestParam(defaultValue = "0") int page, @PathVariable("playerId") String playerId) {
		try {
			List<PlayerComment> playerComments = new ArrayList<PlayerComment>();
		    Pageable paging = PageRequest.of(page, limit);
		      
		    Page<PlayerComment> pagePlayerComments;
		    pagePlayerComments = playerCommentRepository.findByPlayerIdIs(playerId, paging);

		    playerComments = pagePlayerComments.getContent();

		    Map<String, Object> response = new HashMap<>();
		    response.put("playerComments", playerComments);
		    response.put("currentPage", pagePlayerComments.getNumber());
		    response.put("totalItems", pagePlayerComments.getTotalElements());
		    response.put("totalPages", pagePlayerComments.getTotalPages());

		    return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
