package kr.or.connect.reservation.application.service.impl;

import kr.or.connect.reservation.core.presentation.domain.DisplayInfo;
import kr.or.connect.reservation.core.presentation.domain.FileInfo;
import kr.or.connect.reservation.core.presentation.domain.Product;
import kr.or.connect.reservation.core.presentation.domain.ProductImage;
import kr.or.connect.reservation.infrastructure.repository.DisplayInfoRepository;
import kr.or.connect.reservation.infrastructure.repository.ProductImageRepository;
import kr.or.connect.reservation.core.presentation.dto.ProductDisplayInfo;
import kr.or.connect.reservation.core.presentation.dto.ProductDisplayInfoResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static kr.or.connect.reservation.core.presentation.domain.DisplayInfo.makeDummyDisplayInfo;
import static kr.or.connect.reservation.core.presentation.dto.ProductDisplayInfo.makeProductResult;
import static kr.or.connect.reservation.core.presentation.dto.ProductDisplayInfoResult.craeteProductDisplayInfoResult;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ProductServiceImplTest {
    @InjectMocks
    ProductServiceImpl productService;
    @Mock
    DisplayInfoRepository displayInfoRepository;
    @Mock
    ProductImageRepository productImageRepository;

    public static List<ProductDisplayInfo> createExpectedProductDisplayInfos(long size) {
        List<ProductDisplayInfo> productDisplayInfos = new ArrayList();
        for (long th = 1; th <= size; th++) {
            productDisplayInfos.add(makeProductResult(th, th, "testDescription" + th, "testPlaceName" + th, "testcontent" + th, "testSaveFileName" + th));
        }

        return productDisplayInfos;
    }

    public static List<DisplayInfo> createDummyDisplayInfos(long size) {
        List<DisplayInfo> displayInfos = new ArrayList();
        for (int th = 1; th <= size; th++) {
            DisplayInfo displayInfo = makeDummyDisplayInfo();
            displayInfo.setId((long) th);
            displayInfos.add(displayInfo);

            Product product = Product.builder()
                    .id((long) th)
                    .description("testDescription" + th)
                    .content("testcontent" + th)
                    .event("")
                    .reservedCount(0L)
                    .build();

            displayInfo.setProduct(product);

            displayInfo.setPlaceName("testPlaceName" + th);
        }

        return displayInfos;
    }

    public static List<ProductImage> createDummyProductImage(long th) {
        FileInfo fileInfo = new FileInfo((long) th, "testFileName" + th, "testSaveFileName" + th, "testContentType" + th, true);

        return Arrays.asList(new ProductImage((long) th, "th", null, fileInfo));
    }

    @Test
    @DisplayName("4 개의 displayInfo 를 받은 경우")
    void test_get_four_displayInfo() {
        long categoryId = 1;
        long startPageNum = 1;
        long requestCount = 4;
        long totalCount = 100;
        long SELECT_COUNT_LIMIT = 4;

        //given
        ProductDisplayInfoResult expedtedProductDisplayInfoResult = craeteProductDisplayInfoResult(totalCount, createExpectedProductDisplayInfos(SELECT_COUNT_LIMIT));

        //mocking
        PageRequest pageRequest = PageRequest.of((int) (startPageNum / SELECT_COUNT_LIMIT), (int) SELECT_COUNT_LIMIT);
        given(displayInfoRepository.findByImageTypeAndCategoryIdOrderByProductId("th", categoryId, pageRequest))
                .willReturn(new PageImpl(createDummyDisplayInfos(requestCount), pageRequest, totalCount));
        for (int i = 1; i <= requestCount; i++)
            given(productImageRepository.findByTypesAndProductId(i, PageRequest.of(0, 1), "th"))
                    .willReturn(createDummyProductImage(i));

        //when
        ProductDisplayInfoResult productDisplayInfo = productService.getProductDisplayInfo(categoryId, startPageNum);

        //then
        Assertions.assertEquals(expedtedProductDisplayInfoResult, productDisplayInfo);
    }
}