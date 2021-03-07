package kr.or.connect.reservation.application.service.impl;

import kr.or.connect.reservation.infrastructure.repository.ProductImageRepository;
import kr.or.connect.reservation.presentation.dto.ProductDisplayInfo;
import kr.or.connect.reservation.presentation.dto.ProductDisplayInfoResult;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;
    @Mock
    ProductImageRepository productImageRepository;

    @Test
    void getProductDisplayInfo() {
        //given
        List<ProductDisplayInfo> productDisplayInfos = new ArrayList();
        productDisplayInfos.add(ProductDisplayInfo.makeProductResult(1L, 1L, "testDescription1", "testPlaceName", "testcontent1", "testImageUrl1"));
        productDisplayInfos.add(ProductDisplayInfo.makeProductResult(2L, 2L, "testDescription2", "testPlaceName", "testcontent2", "testImageUrl2"));
        ProductDisplayInfoResult expectedProductDisplayInfoResult;


        //mocking
        //when
        //then

    }


}