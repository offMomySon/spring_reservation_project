package kr.or.connect.reservation.application.service.impl;

import kr.or.connect.reservation.presentation.dto.ProductDisplayInfo;
import kr.or.connect.reservation.presentation.dto.ProductDisplayInfoResult;
import kr.or.connect.reservation.domain.FileInfo;
import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.domain.ProductImage;
import kr.or.connect.reservation.infrastructure.repository.DisplayInfoRepository;
import kr.or.connect.reservation.infrastructure.repository.ProductImageRepository;
import kr.or.connect.reservation.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static kr.or.connect.reservation.presentation.dto.ProductDisplayInfo.makeProductResult;
import static kr.or.connect.reservation.presentation.dto.ProductDisplayInfoResult.craeteProductDisplayInfoResult;

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
        Page<ProductImage> productImagePage;
        PageRequest pageRequest = PageRequest.of((int) (startPageNum / SELECT_COUNT_LIMIT), (int) SELECT_COUNT_LIMIT, Sort.by(Sort.Direction.ASC, "product"));

        if (categoryId == 0) {
            productImagePage = productImageRepository.findByType("th", pageRequest);
        } else {
            productImagePage = productImageRepository.findByTypeAndCategoryId("th", categoryId, pageRequest);
        }

        List<ProductImage> productImages = productImagePage.getContent();
        List<ProductDisplayInfo> productDisplayInfos = getProductDisplayInfos(productImages);

        return craeteProductDisplayInfoResult(productImagePage.getSize(), productDisplayInfos);
    }

    private List<ProductDisplayInfo> getProductDisplayInfos(List<ProductImage> productImages) {
        List<ProductDisplayInfo> productDisplayInfos = new ArrayList();
        for (ProductImage productImage : productImages) {
            Product product = productImage.getProduct();
            displayInfoRepository.findOneByProductId(product.getId()).ifPresent(displayInfo -> {
                FileInfo fileInfo = productImage.getFileInfo();
                productDisplayInfos.add(makeProductResult(product, displayInfo, fileInfo));
            });
        }
        return productDisplayInfos;
    }
}
