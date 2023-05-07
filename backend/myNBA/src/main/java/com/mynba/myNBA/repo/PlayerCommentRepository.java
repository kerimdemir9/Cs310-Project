package com.mynba.myNBA.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mynba.myNBA.model.PlayerComment;

public interface PlayerCommentRepository extends MongoRepository<PlayerComment, String> {
	Page<PlayerComment> findByPlayerIdIs(String playerId, Pageable pageable);
}
