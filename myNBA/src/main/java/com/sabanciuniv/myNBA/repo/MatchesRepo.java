package com.sabanciuniv.myNBA.repo;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sabanciuniv.myNBA.model.Matches;


public interface MatchesRepo extends MongoRepository<Matches, String>{

}
