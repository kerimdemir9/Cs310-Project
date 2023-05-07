package com.mynba.myNBA.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mynba.myNBA.model.MatchComment;

public interface MatchCommentRepository extends MongoRepository<MatchComment, String> {
//	public List<MatchComment> findByMatchIdIs(String matchId);
	Page<MatchComment> findByMatchIdIs(String matchId, Pageable pageable);
}
