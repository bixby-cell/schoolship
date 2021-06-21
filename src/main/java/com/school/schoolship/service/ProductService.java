package com.school.schoolship.service;

import com.github.pagehelper.PageInfo;
import com.school.schoolship.model.pojo.Product;
import com.school.schoolship.model.request.AddProductReq;
import com.school.schoolship.model.request.ProductListReq;
import com.school.schoolship.model.request.UpdateProductReq;

/**
 * @author Bixby
 * @create 2021-06-20
 */
public interface ProductService {

    Product detail(Integer id);

    void add(AddProductReq addProductReq);

    void update(UpdateProductReq updateProductReq);

    void delete(Integer id);

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    PageInfo list(ProductListReq productListReq);
}
