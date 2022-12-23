package wang.gravity.bookmanagementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import wang.gravity.bookmanagementsystem.pojo.Category;
import wang.gravity.bookmanagementsystem.pojo.User;

@Mapper
public interface CategoryMapper {
    /**
     * 获取分类列表
     *
     * @return 分类列表
     */
    List<Category> getAllCategory();


    /**
     * 根据ID删除分类(批量)
     *
     * @param ids 多个用户的id
     */
    void deleteCategories(List<Integer> ids);

    Category getCategoryById(int id);

    Category getCategoryByName(String name);


    /**
     * 分类创建接口
     *
     * @param category 传入一个分类对象
     * @return 创建成功返回ID
     */
    void addCategory(Category category);


    void editCategory(Category category);
}
