package kr.or.connect.reservation.application.service.impl;

import kr.or.connect.reservation.application.service.ProductService;
import kr.or.connect.reservation.core.presentation.domain.DisplayInfo;
import kr.or.connect.reservation.core.presentation.domain.FileInfo;
import kr.or.connect.reservation.core.presentation.domain.Product;
import kr.or.connect.reservation.core.presentation.domain.ProductImage;
import kr.or.connect.reservation.infrastructure.exception.list.RelatedEntityAbsentException;
import kr.or.connect.reservation.infrastructure.repository.DisplayInfoRepository;
import kr.or.connect.reservation.infrastructure.repository.ProductImageRepository;
import kr.or.connect.reservation.core.presentation.dto.ProductDisplayInfo;
import kr.or.connect.reservation.core.presentation.dto.ProductDisplayInfoResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static kr.or.connect.reservation.core.presentation.dto.ProductDisplayInfo.makeProductResult;
import static kr.or.connect.reservation.core.presentation.dto.ProductDisplayInfoResult.craeteProductDisplayInfoResult;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    final private DisplayInfoRepository displayInfoRepository;
    final private ProductImageRepository productImageRepository;

    @Nonnull
    @Override
    public ProductDisplayInfoResult getProductDisplayInfo(long categoryId, long startPageNum) {
        Page<DisplayInfo> displayInfoPage;

        PageRequest pageRequest = PageRequest.of((int) (startPageNum / SELECT_COUNT_LIMIT), (int) SELECT_COUNT_LIMIT);
        if (categoryId == 0) {
            displayInfoPage = displayInfoRepository.findByImageTypeOrderByProductId("th", pageRequest);
        } else {
            displayInfoPage = displayInfoRepository.findByImageTypeAndCategoryIdOrderByProductId("th", categoryId, pageRequest);
        }

        List<ProductDisplayInfo> productDisplayInfos = getProductDisplayInfos(displayInfoPage);

        return craeteProductDisplayInfoResult(displayInfoPage.getTotalElements(), productDisplayInfos);
    }

    private List<ProductDisplayInfo> getProductDisplayInfos(Page<DisplayInfo> displayInfoPage) {
        List<ProductDisplayInfo> productDisplayInfos = new ArrayList();
        displayInfoPage.getContent().stream()
                .forEach(displayInfo -> {
                    Product product = displayInfo.getProduct();
                    List<ProductImage> productImages = productImageRepository.findByTypesAndProductId(product.getId(), PageRequest.of(0, 1), "th");

                    if (productImages.isEmpty())
                        throw new RelatedEntityAbsentException();

                    ProductImage productImage = productImages.get(0);
                    FileInfo fileInfo = productImage.getFileInfo();
                    productDisplayInfos.add(makeProductResult(displayInfo.getId(), product.getId(), product.getDescription(), displayInfo.getPlaceName(), product.getContent(), fileInfo.getSaveFileName()));
                });
        return productDisplayInfos;
    }
}
