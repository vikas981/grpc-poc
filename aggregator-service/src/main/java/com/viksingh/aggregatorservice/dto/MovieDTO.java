package com.viksingh.aggregatorservice.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDTO {
    private String name;
    private String rating;
    private String genre;
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
