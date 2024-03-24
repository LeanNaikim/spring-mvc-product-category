package co.cstad.springmvcproductcategory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

// this will be use with database
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String title;
    private String description;
    private float price;
    private String imageUrl;
//    private int categoryId;
}
