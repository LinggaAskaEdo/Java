package com.demo.graphql.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CarBrand
{
    public CarBrand()
    {
        //Prevents access default paramater-less constructor
    }

    public CarBrand(Long id)
    {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name", nullable = false)
    private String brandName;
}