package kr.or.connect.reservation.application.service.impl;

import kr.or.connect.reservation.core.presentation.domain.Category;
import kr.or.connect.reservation.infrastructure.repository.CategoryRepository;
import kr.or.connect.reservation.core.presentation.dto.CategoryResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static kr.or.connect.reservation.core.presentation.dto.CategoryResult.createCategoryResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class CategoryServiceImplTest {
    @InjectMocks
    CategoryServiceImpl categoryService;
    @Mock
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("모든 category list 받아온 경우")
    void test_get_all_category_list() {
        //given
        List<CategoryResult> expectedResultList = new ArrayList();
        expectedResultList.add(createCategoryResult(1, "test1", 10L));
        expectedResultList.add(createCategoryResult(2, "test2", 20L));
        expectedResultList.add(createCategoryResult(3, "test3", 30L));
        expectedResultList.add(createCategoryResult(4, "test4", 40L));

        //mocking
        given(categoryRepository.findAll())
                .willReturn(Arrays.asList(
                        createCategroy(1, "test1"),
                        createCategroy(2, "test2"),
                        createCategroy(3, "test3"),
                        createCategroy(4, "test4")));
        given(categoryRepository.countProductDisplayInfo(1)).willReturn(10L);
        given(categoryRepository.countProductDisplayInfo(2)).willReturn(20L);
        given(categoryRepository.countProductDisplayInfo(3)).willReturn(30L);
        given(categoryRepository.countProductDisplayInfo(4)).willReturn(40L);

        //when
        List<CategoryResult> categoryList = categoryService.getCategoryList();

        //then
        assertEquals(expectedResultList, categoryList);
    }

    @Test
    @DisplayName("repository 에서 category 일부분만 가져온 경우")
    void test_missing_some_category_list() {
        //given
        List<CategoryResult> expectedResultList = new ArrayList();
        expectedResultList.add(createCategoryResult(1, "test1", 10L));
        expectedResultList.add(createCategoryResult(2, "test2", 20L));
        expectedResultList.add(createCategoryResult(3, "test3", 30L));
        expectedResultList.add(createCategoryResult(4, "test4", 40L));

        //mocking
        given(categoryRepository.findAll())
                .willReturn(Arrays.asList(
                        createCategroy(1, "test1"),
                        createCategroy(2, "test2"),
                        createCategroy(3, "test3")));
        given(categoryRepository.countProductDisplayInfo(1)).willReturn(10L);
        given(categoryRepository.countProductDisplayInfo(2)).willReturn(20L);
        given(categoryRepository.countProductDisplayInfo(3)).willReturn(30L);
        //when
        List<CategoryResult> categoryList = categoryService.getCategoryList();

        //then
        assertNotEquals(expectedResultList.size(), categoryList.size());
    }

    @Test
    @DisplayName("category 의 count 를 잘못 가져온경우")
    void test_get_wrong_count() {
        //given
        List<CategoryResult> expectedResultList = new ArrayList();
        expectedResultList.add(createCategoryResult(1, "test1", 10L));
        expectedResultList.add(createCategoryResult(2, "test2", 20L));
        expectedResultList.add(createCategoryResult(3, "test3", 30L));

        //mocking
        given(categoryRepository.findAll())
                .willReturn(Arrays.asList(
                        createCategroy(1, "test1"),
                        createCategroy(2, "test2"),
                        createCategroy(3, "test3")));
        given(categoryRepository.countProductDisplayInfo(1)).willReturn(10L);
        given(categoryRepository.countProductDisplayInfo(2)).willReturn(20L);
        given(categoryRepository.countProductDisplayInfo(3)).willReturn(35L);
        //when
        List<CategoryResult> categoryList = categoryService.getCategoryList();

        //then
        assertNotEquals(expectedResultList, categoryList);
    }

    private Category createCategroy(long id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);

        return category;
    }
}