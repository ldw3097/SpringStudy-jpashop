package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("A")
@Getter @Setter
public class Album extends Item{
    private String artist;
    private String etc;

    public Album(String name, int price, int stockQuantity, String artist, String etc){
        super(name, price, stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }

    public Album(){}



}
