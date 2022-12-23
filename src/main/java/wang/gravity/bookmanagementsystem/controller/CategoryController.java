package wang.gravity.bookmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import wang.gravity.bookmanagementsystem.annotation.AuthCheck;
import wang.gravity.bookmanagementsystem.annotation.PassToken;
import wang.gravity.bookmanagementsystem.annotation.UserLogAnnotation;
import wang.gravity.bookmanagementsystem.constant.ErrorCode;
import wang.gravity.bookmanagementsystem.constant.UserLogConst;
import wang.gravity.bookmanagementsystem.dto.DeleteForm;
import wang.gravity.bookmanagementsystem.exception.MyException;
import wang.gravity.bookmanagementsystem.pojo.Category;
import wang.gravity.bookmanagementsystem.service.CategoryService;
import wang.gravity.bookmanagementsystem.utils.ResultUtil;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PassToken
    @GetMapping("/getCategoryList")
    public ResultUtil<List<Category>> getCategoryList() {
        return ResultUtil.success(categoryService.getCategoryList());
    }

    @AuthCheck
    @PostMapping("/deleteCategories")
    @UserLogAnnotation(operation = UserLogConst.DELETE_CATEGORY)
    public ResultUtil<String> deleteUsers(@RequestBody DeleteForm deleteForm) {
        List<Integer> deleteErr = categoryService.deleteCategories(deleteForm.getIds());
        if (deleteErr.size() != 0) {
            throw new MyException(ErrorCode.CATEGORY_NOT_FOUND_ERROR);
        }
        return ResultUtil.success();
    }

    @AuthCheck
    @PostMapping("/addCategory")
    @UserLogAnnotation(operation = UserLogConst.ADD_CATEGORY)
    public ResultUtil<String> addCategory(@RequestBody Category category) {
        //添加分类
        categoryService.addCategory(category);
        return ResultUtil.success();
    }

    @AuthCheck
    @PostMapping("/editCategory")
    @UserLogAnnotation(operation = UserLogConst.UPDATE_CATEGORY)
    public ResultUtil<String> editCategory(@RequestBody Category category) {
        //添加分类
        categoryService.editCategory(category);
        return ResultUtil.success();
    }

}
