package com.viksingh.movieservice.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "movie_tbl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie {

    @Id
    private String name;
    private String rating;
    private String genre;
    @Column(name = "release_year")
    private int releaseYear;
    private String released;
    private Double score;
    private BigDecimal votes;
    private String director;
    private String writer;
    private String star;
    private String country;
    private BigDecimal budget;
    private BigDecimal gross;
    private String company;
    private BigDecimal runtime;
}
