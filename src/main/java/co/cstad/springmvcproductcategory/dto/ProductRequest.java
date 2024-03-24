package co.cstad.springmvcproductcategory.dto;

import lombok.ToString;

public record ProductRequest (String title , String description , float price , String imageUrl) {
}
