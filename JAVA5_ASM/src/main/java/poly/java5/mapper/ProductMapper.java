package poly.java5.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import poly.java5.dto.ProductDTO;
import poly.java5.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDTO productToDTO(Product product);
}