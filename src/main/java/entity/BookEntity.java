package entity;

import lombok.Data;

@Data
public class BookEntity {
    private int id;
    private String bookName;
    private float price;
    private String author;
}