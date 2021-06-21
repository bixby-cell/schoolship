package com.school.schoolship.service;

import com.github.pagehelper.PageInfo;
import com.school.schoolship.exception.MallException;
import com.school.schoolship.model.pojo.Category;
import com.school.schoolship.model.request.AddCategoryReq;
import com.school.schoolship.model.request.UpdateCategoryReq;
import com.school.schoolship.model.vo.CategoryVO;

import java.util.List;

/**
 * @author Bixby
 * @create 2021-06-20
 */
public interface CategoryService {
    void add(AddCategoryReq addCategoryReq);

    void update(UpdateCategoryReq updateCategoryReq);

    void delete(Integer id);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    List<CategoryVO> listCategoryForCustomer(Integer parentId);
}
