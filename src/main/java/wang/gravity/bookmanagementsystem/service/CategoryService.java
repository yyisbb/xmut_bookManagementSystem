package wang.gravity.bookmanagementsystem.service;

import org.springframework.stereotype.Repository;

import java.util.List;

import wang.gravity.bookmanagementsystem.pojo.Category;
import wang.gravity.bookmanagementsystem.pojo.User;

@Repository
public interface CategoryService {
    List<Category> getCategoryList();

    List<Integer> deleteCategories(int[] ids);

    void addCategory(Category category);

    void editCategory(Category category);
}
