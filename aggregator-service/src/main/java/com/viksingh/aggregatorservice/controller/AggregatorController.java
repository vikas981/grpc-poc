package com.viksingh.aggregatorservice.controller;

import com.viksingh.aggregatorservice.dto.MovieDTO;
import com.viksingh.aggregatorservice.dto.UserGenreDTO;
import com.viksingh.aggregatorservice.service.UserMovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AggregatorController {

    @Autowired
    private UserMovieService userMovieService;

    @GetMapping("/user/{loginId}")
    public ResponseEntity<List<MovieDTO>> getMovies(@PathVariable String loginId){
        return new ResponseEntity<>(userMovieService.getMovies(loginId), HttpStatus.OK);
    }

    @PutMapping("/user")
    public void getMovies(@RequestBody UserGenreDTO userGenreDTO){

    }
}
