package com.viksingh.movieservice.service;

import com.viksingh.grpcpoc.movie.MovieDto;
import com.viksingh.grpcpoc.movie.MovieSearchRequest;
import com.viksingh.grpcpoc.movie.MovieSearchResponse;
import com.viksingh.grpcpoc.movie.MovieServiceGrpc;
import com.viksingh.movieservice.entity.Movie;
import com.viksingh.movieservice.repository.MovieRepository;
import io.grpc.stub.StreamObserver;
import java.util.List;
import java.util.stream.Collectors;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class MovieService extends MovieServiceGrpc.MovieServiceImplBase {

    @Autowired
    private MovieRepository movieRepository;
    @Override
    public void getMovies(MovieSearchRequest request, StreamObserver<MovieSearchResponse> responseObserver) {
        MovieSearchResponse.Builder response = MovieSearchResponse.newBuilder();
        StringBuilder genre = new StringBuilder();
        genre.append(request.getGenre().name().substring(0,1).toUpperCase())
                .append(request.getGenre().name().substring(1).toLowerCase());
        System.out.println(genre.toString());
        List<Movie> movies = movieRepository.findByGenre(genre.toString());
        List<MovieDto> movieDtos = movies.stream().map(movie -> MovieDto.newBuilder()
                .setName(movie.getName())
                        .setRating(movie.getRating() !=null ? movie.getRating() : "NA")
                        .setGenre(movie.getGenre() !=null ? movie.getGenre() : "NA")
                        .setReleaseYear(movie.getReleaseYear() == 0 ? movie.getReleaseYear() : 0000)
                        .setReleased(movie.getReleased() !=null ? movie.getReleased() : "NA")
                        .setScore(movie.getScore() !=null ? movie.getScore() : 0.0)
                        .setVotes(movie.getVotes() !=null ? movie.getVotes().longValue() : 0L)
                        .setDirector(movie.getDirector() !=null ? movie.getDirector() : "NA")
                        .setWriter(movie.getWriter() !=null ? movie.getWriter() : "NA")
                        .setStar(movie.getStar() !=null ? movie.getStar() : "NA")
                        .setCountry(movie.getCompany() !=null ? movie.getCompany() : "NA")
                        .setBudget(movie.getBudget() !=null ? movie.getBudget().longValue() : 0L)
                        .setGross(movie.getGross() !=null ? movie.getGross().longValue() : 0L)
                        .setCompany(movie.getCompany() !=null ? movie.getCompany() : "NA")
                        .setRuntime(movie.getRuntime() !=null ? movie.getRuntime().longValue() : 0L)
                .build())
                .collect(Collectors.toList());


        responseObserver.onNext(response.addAllMovie(movieDtos).build());
        responseObserver.onCompleted();


    }
}
