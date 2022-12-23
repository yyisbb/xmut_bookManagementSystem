package wang.gravity.bookmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import wang.gravity.bookmanagementsystem.constant.Check;
import wang.gravity.bookmanagementsystem.constant.ErrorCode;
import wang.gravity.bookmanagementsystem.exception.MyException;
import wang.gravity.bookmanagementsystem.mapper.BookMapper;
import wang.gravity.bookmanagementsystem.mapper.CategoryMapper;
import wang.gravity.bookmanagementsystem.pojo.Book;
import wang.gravity.bookmanagementsystem.pojo.Category;
import wang.gravity.bookmanagementsystem.pojo.User;
import wang.gravity.bookmanagementsystem.service.CategoryService;
import wang.gravity.bookmanagementsystem.utils.AesUtil;
import wang.gravity.bookmanagementsystem.utils.PassWordUtil;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getAllCategory();
    }

    @Override
    public List<Integer> deleteCategories(int[] ids) {
        if (ids.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        List<Integer> delete = new ArrayList<>();
        for (int categoryId : ids) {
            Category category = categoryMapper.getCategoryById(categoryId);
            if (category == null) {
                throw new MyException(ErrorCode.CATEGORY_NOT_FOUND_ERROR);
            }
            //查询该分类下是否有图书
            List<Book> books = bookMapper.getBookByCategoryId(categoryId);
            if (books.size() != 0) {
                res.add(categoryId);
            } else {
                delete.add(categoryId);
            }
        }

        if (delete.size() != 0) {
            categoryMapper.deleteCategories(delete);
        }
        return res;
    }

    @Override
    public void addCategory(Category category) {
        //参数为空抛异常
        if (category.getName() == null
        ) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }

        if (category.getName().equals("")) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }

        category.setCreatedTime(new Date());
        //查询分类是否存在
        Category dbCategory = categoryMapper.getCategoryByName(category.getName());
        if (dbCategory != null) {
            throw new MyException(ErrorCode.CATEGORY_EXIST_ERROR);
        }
        boolean nameMatches = category.getName().matches(Check.NICKNAME_REGEX);
        if (!nameMatches) {
            throw new MyException(ErrorCode.NAME_FORMAT_ERROR);
        }
        categoryMapper.addCategory(category);
        if (category.getId() == 0) {
            throw new MyException(ErrorCode.REGISTER_ERROR);
        }
    }

    @Override
    public void editCategory(Category category) {
        //参数为空抛异常
        if (category.getName() == null
        ) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }

        if (category.getName().equals("")) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }

        boolean nameMatches = category.getName().matches(Check.NICKNAME_REGEX);
        if (!nameMatches) {
            throw new MyException(ErrorCode.NAME_FORMAT_ERROR);
        }

        categoryMapper.editCategory(category);
    }


}
