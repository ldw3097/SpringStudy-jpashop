package jpabook.jpashop.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemForm {

    private String itemType;

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    private String director;
    private String actor;

    private String artist;
    private String etc;
}
