package com.viksingh.movieservice.repository;

import com.viksingh.movieservice.entity.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,String> {
    @Query("select m from Movie m where m.genre=(:genre) order by m.releaseYear asc")
    List<Movie> findByGenre(@Param("genre") String genre);

    List<Movie> getMovieByGenreOrderByReleaseYear(String genre);
}
