package com.sabanciuniv.myNBA;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sabanciuniv.myNBA.model.Matches;
import com.sabanciuniv.myNBA.repo.MatchesRepo;

@SpringBootApplication
public class MyNbaApplication implements CommandLineRunner{
	
	@Autowired MatchesRepo matchesRepo;

	public static void main(String[] args) {
		SpringApplication.run(MyNbaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		long count = matchesRepo.count();
		// TODO Auto-generated method stub
		System.out.println("App started!");
		
		Matches m1 = new Matches("Kerim", "Zeynep");
		Matches m2 = new Matches("Zeynep", "Kerim");
		List<Matches> ms = new ArrayList<>();
		ms.add(m1);
		ms.add(m2);
		//matchesRepo.saveAll(ms);
		//matchesRepo.save(m1);
		System.out.println("Insert done!");
		System.out.println("Collection size: " + count);
		System.out.println("App finished!");
		
	}	
}
