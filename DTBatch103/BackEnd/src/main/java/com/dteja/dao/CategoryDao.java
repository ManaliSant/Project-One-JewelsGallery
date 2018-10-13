package com.dteja.dao;

import java.util.List;

import com.dteja.models.Category;

public interface CategoryDao {
	public boolean addCategory(Category category);
	public boolean deleteCategory(Category category);
	public boolean updateCategory(Category category);
	public List<Category> listCategories();
	public Category getCategory(int categoryId);
}
