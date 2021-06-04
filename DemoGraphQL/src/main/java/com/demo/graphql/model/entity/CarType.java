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
public class CarType
{
    public CarType()
    {
        //Prevents access default paramater-less constructor
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_brand_id", nullable = false)
    private CarBrand carBrand;

    @Column(name = "type_name", nullable = false)
    private String typeName;
}