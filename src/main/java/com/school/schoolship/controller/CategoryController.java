package com.school.schoolship.controller;

import com.github.pagehelper.PageInfo;
import com.school.schoolship.common.ApiRestResponse;
import com.school.schoolship.common.Constant;
import com.school.schoolship.exception.MallException;
import com.school.schoolship.exception.MallExceptionEnum;
import com.school.schoolship.model.pojo.Category;
import com.school.schoolship.model.pojo.User;
import com.school.schoolship.model.request.AddCategoryReq;
import com.school.schoolship.model.request.UpdateCategoryReq;
import com.school.schoolship.model.vo.CategoryVO;
import com.school.schoolship.service.CategoryService;
import com.school.schoolship.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Bixby
 * @create 2021-06-20
 * 描述：     目录Controller
 */

@RestController
@SuppressWarnings("all")
public class CategoryController {


    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;
    /**
    * 后台添加目录
    */
    @ApiOperation("后台添加目录")
    @PostMapping("admin/category/add")
    public ApiRestResponse addCategory(HttpSession session,
                                       @Valid @RequestBody AddCategoryReq addCategoryReq) throws MallException {
   //        if (addCategoryReq.getName() == null || addCategoryReq.getType() == null
    //                || addCategoryReq.getParentId() == null || addCategoryReq.getOrderNum() == null) {
    //            return ApiRestResponse.error(ImoocMallExceptionEnum.PARA_NOT_NULL);
    //        }

        User currentUser = (User) session.getAttribute(Constant.MALL_USER);
        if (currentUser == null){
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        boolean adminRole = userService.checkAdminRole(currentUser);
        if (adminRole){
            categoryService.add(addCategoryReq);
            return ApiRestResponse.success();
        }else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }

    }

    @ApiOperation("后台更新目录")
    @PostMapping("admin/category/update")
    public ApiRestResponse updateCategory(HttpSession session,
                                          @Valid @RequestBody UpdateCategoryReq updateCategoryReq){
        User currentUser = (User) session.getAttribute(Constant.MALL_USER);
        if (currentUser == null){
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        //校验是否是管理员
        boolean adminRole = userService.checkAdminRole(currentUser);
        if (adminRole){
            //是管理员，执行操作
            categoryService.update(updateCategoryReq);
            return ApiRestResponse.success();
        }else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }
    }

    @ApiOperation("后台删除目录")
    @PostMapping("admin/category/delete")
    public ApiRestResponse deleteCategory(@RequestParam Integer id){
        categoryService.delete(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台目录列表")
    @GetMapping("admin/category/list")
    public ApiRestResponse lisCategoryAdmin(@RequestParam Integer pageNum,
                                            @RequestParam Integer pageSize){
        PageInfo pageInfo = categoryService.listForAdmin(pageNum,pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation("前台目录列表")
    @GetMapping("category/list")
    public ApiRestResponse listCategoryForCustomer() {
        List<CategoryVO> categoryVOList = categoryService.listCategoryForCustomer(0);
        return ApiRestResponse.success(categoryVOList);
    }
}
