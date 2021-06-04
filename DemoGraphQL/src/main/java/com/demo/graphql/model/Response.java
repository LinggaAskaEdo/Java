package com.demo.graphql.model;

import com.demo.graphql.model.entity.CarBrand;
import lombok.*;

import java.util.List;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Response
{
    private String status;
    private String message;
    private List<CarBrand> brandList;
}