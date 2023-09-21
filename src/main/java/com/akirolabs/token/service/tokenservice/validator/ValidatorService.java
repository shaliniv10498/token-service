package com.akirolabs.token.service.tokenservice.validator;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ValidatorService {
	
	@GetMapping("/validator")
	public ResponseEntity<Boolean> validateToken(@RequestParam("token") String token){
		return new ResponseEntity<>(validate(token.toString()), HttpStatus.OK);
	}
    
	private boolean validate(String token) {
		int[] tokenArray=new int[token.length()];
		 
        for(int i=0;i<token.length();i++)
        {
            char c= token.charAt(i);
            tokenArray[i]=  Integer.parseInt(""+c);
        }
 
        for(int i=tokenArray.length-2;i>=0;i=i-2)
        {
            int num = tokenArray[i];
            num = num * 2;  // step 1 > multipliying by 2 to get get double of alternate numbers hence i-2 above
            if(num>9)
            {
                num = num%10 + num/10;  // summing the digits if > 9
            }
            tokenArray[i]=num;
        }
 
        int sum = sumDigits(tokenArray);  // step 3 sum of all array numbers
 
        System.out.println(sum);
 
        if(sum%10==0)  // step 4 if mod 10 results 0 it is luhn valid else not invalid token
        {
            return true;
        }
 
        return false;
 
    }
 
    public static int sumDigits(int[] arr)
    {
        return Arrays.stream(arr).sum();
    }

}
