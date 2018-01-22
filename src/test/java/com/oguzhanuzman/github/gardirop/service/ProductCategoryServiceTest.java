package com.oguzhanuzman.github.gardirop.service;

import com.oguzhanuzman.github.gardirop.controller.rest.productcategory.ProductCategoryCreateDto;
import com.oguzhanuzman.github.gardirop.exception.ProductCategoryAlreadyExists;
import com.oguzhanuzman.github.gardirop.repository.ProductCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductCategoryService.class)
public class ProductCategoryServiceTest {
    @Autowired
    private ProductCategoryService productCategoryService;
    @MockBean
    private ProductCategoryRepository productCategoryRepository;

    @Test(expected = ProductCategoryAlreadyExists.class)
    public void throwExceptionOnMemberCreateIfProvidedUsernameAlreadyInUse() throws Exception {
        when(productCategoryRepository.existsByNameAndDeletedFalse(anyString())).thenReturn(true);
        productCategoryService.create(new ProductCategoryCreateDto());
    }

}