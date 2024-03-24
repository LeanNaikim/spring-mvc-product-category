package co.cstad.springmvcproductcategory.dto;

import lombok.Builder;
import org.apache.tomcat.util.descriptor.web.SecurityRoleRef;

@Builder
public record ProductResponse(int id, String title , String description , float price , String imageUrl) {

}
