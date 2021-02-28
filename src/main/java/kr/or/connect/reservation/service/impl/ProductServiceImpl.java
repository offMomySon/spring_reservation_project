package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.ProductDisplayInfo;
import kr.or.connect.reservation.dto.ProductDisplayInfoResult;
import kr.or.connect.reservation.model.DisplayInfo;
import kr.or.connect.reservation.model.FileInfo;
import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.model.ProductImage;
import kr.or.connect.reservation.repository.DisplayInfoRepository;
import kr.or.connect.reservation.repository.ProductImageRepository;
import kr.or.connect.reservation.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static kr.or.connect.reservation.dto.ProductDisplayInfo.makeProductResult;
import static kr.or.connect.reservation.dto.ProductDisplayInfoResult.craeteProductDisplayInfoResult;

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
        List<ProductDisplayInfo> productDisplayInfos = productImages.stream()
                .map(productImage -> {
                    Product product = productImage.getProduct();
                    DisplayInfo displayInfo = displayInfoRepository.findOneByProductId(product.getId()).orElseGet(() -> {
                        log.info("Product Id = %d 의 displayInfo 가 존재하지 않음.", product.getId());
                        return null;
                    });
                    FileInfo fileInfo = productImage.getFileInfo();
                    return makeProductResult(product, displayInfo, fileInfo);
                })
                .collect(Collectors.toList());

        return craeteProductDisplayInfoResult(productImagePage.getSize(), productDisplayInfos);
    }
}
