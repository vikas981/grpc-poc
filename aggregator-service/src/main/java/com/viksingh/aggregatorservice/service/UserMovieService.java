package com.viksingh.aggregatorservice.service;

import com.viksingh.aggregatorservice.dto.MovieDTO;
import com.viksingh.grpcpoc.movie.MovieSearchRequest;
import com.viksingh.grpcpoc.movie.MovieServiceGrpc;
import com.viksingh.grpcpoc.user.UserSearchRequest;
import com.viksingh.grpcpoc.user.UserSearchResponse;
import com.viksingh.grpcpoc.user.UserServiceGrpc;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class UserMovieService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userStub;

    @GrpcClient("movie-service")
    private MovieServiceGrpc.MovieServiceBlockingStub movieStub;

   public List<MovieDTO> getMovies(String loginId){
       UserSearchRequest request = UserSearchRequest.newBuilder().setLoginId(loginId).build();
       UserSearchResponse userResponse = userStub.getUserGenre(request);
       MovieSearchRequest movieSearchRequest = MovieSearchRequest.newBuilder().setGenre(userResponse.getGenre()).build();
       return movieStub.getMovies(movieSearchRequest)
               .getMovieList().stream().map(movieDto ->
                 MovieDTO.builder().name(movieDto.getName())
                         .rating(movieDto.getRating())
                         .genre(movieDto.getGenre())
                         .releaseYear(movieDto.getReleaseYear())
                         .released(movieDto.getReleased())
                         .score(movieDto.getScore())
                         .votes(BigDecimal.valueOf(movieDto.getVotes()))
                         .director(movieDto.getDirector())
                         .writer(movieDto.getWriter())
                         .star(movieDto.getStar())
                         .country(movieDto.getCountry())
                         .budget(BigDecimal.valueOf(movieDto.getBudget()))
                         .gross(BigDecimal.valueOf(movieDto.getGross()))
                         .company(movieDto.getCompany())
                         .runtime(BigDecimal.valueOf(movieDto.getRuntime()))
                         .build()
               ).collect(Collectors.toList());
   }

}
