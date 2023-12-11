package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("M")
@Getter @Setter
public class Movie extends Item{

    private String director;
    private String actor;

    public Movie(String name, int price, int stockQuantity, String director, String actor){
        super(name, price, stockQuantity);
        this.director = director;
        this.actor = actor;
    }

    public Movie(){}


}

