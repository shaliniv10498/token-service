package com.akirolabs.token.service.tokenservice.generator;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GeneratorService {
	
	@PostMapping("/generator")
	public ResponseEntity<String> generateToken(@RequestBody List<Integer> numbers){
		return new ResponseEntity<>(getToken(numbers),HttpStatus.OK);
	}
	
	private String getToken(List<Integer> numbers){
		StringBuilder token = new StringBuilder();
		int tokenlength = 16;
		while(tokenlength !=0) {
			if(tokenlength%4==0 && tokenlength!=16) token.append("-");
			int character = (int) (Math.random()* numbers.size());
			token.append(numbers.get(character));
			tokenlength--;
		}
		return token.toString();
	} 
	
	
			
}
