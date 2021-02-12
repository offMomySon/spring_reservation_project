package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.model.ProductImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ProductImageRepositoryTest {

    @Autowired
    ProductImageRepository productImageRepository;

    @Test
    void selectWithCategoryId() {
        //given
        long categoryId = 1;
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "product_id"));

        //when
        List<ProductImage> productImages = productImageRepository.selectWithCategoryId(categoryId, pageRequest).getContent();

        System.out.println("productImages size = " + productImages.size());
        for (ProductImage productImage : productImages) {

            System.out.println("productImage.getId() = " + productImage.getId());
            System.out.println("productImage.getType() = " + productImage.getType());
            Product product = productImage.getProduct();
            System.out.println("product.getId() = " + product.getId());
            System.out.println("product.getDescription() = " + product.getDescription());
            System.out.println("product.getContent() = " + product.getContent());
        }

        //then
        assertEquals(productImages.size(), 3);
    }
}