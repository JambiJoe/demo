/**
	* Copyright (c) minuteproject, minuteproject@gmail.com
	* All rights reserved.
	* 
	* Licensed under the Apache License, Version 2.0 (the "License")
	* you may not use this file except in compliance with the License.
	* You may obtain a copy of the License at
	* 
	* http://www.apache.org/licenses/LICENSE-2.0
	* 
	* Unless required by applicable law or agreed to in writing, software
	* distributed under the License is distributed on an "AS IS" BASIS,
	* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	* See the License for the specific language governing permissions and
	* limitations under the License.
	* 
	* More information on minuteproject:
	* twitter @minuteproject
	* wiki http://minuteproject.wikispaces.com 
	* blog http://minuteproject.blogspot.net
	* 
*/
/**
	* template reference : 
	* - name      : BslaDaoInterfaceUML
	* - file name : BslaDaoInterfaceUML.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:20 CET
*/
package net.sf.mp.demo.petshop.dao.face.pet;

import net.sf.mp.demo.petshop.domain.pet.Category;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;

/**
 *
 * <p>Title: CategoryDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with CategoryDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching category objects</p>
 *
 */
public interface CategoryDao {
    /**
     * Inserts a Category entity 
     * @param Category category
     */
    public void insertCategory(Category category) ;
 
    /**
     * Inserts a list of Category entity 
     * @param List<Category> categorys
     */
    public void insertCategorys(List<Category> categorys) ;
        
    /**
     * Updates a Category entity 
     * @param Category category
     */
    public Category updateCategory(Category category) ;

	 /**
     * Updates a Category entity with only the attributes set into Category.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param Category category
   */
    public int updateNotNullOnlyCategory(Category category) ;
	 
	public int updateNotNullOnlyPrototypeCategory(Category category, Category prototypeCriteria);
	
     /**
     * Saves a Category entity 
     * @param Category category
     */
    public void saveCategory(Category category);
    
    /**
     * Deletes a Category entity 
     * @param Category category
     */
    public void deleteCategory(Category category) ;
 
    /**
     * Loads the Category entity which is related to an instance of
     * Category
     * @param Long id
     * @return Category The Category entity
     
    public Category loadCategory(Long id);
*/
    /**
     * Loads the Category entity which is related to an instance of
     * Category
     * @param java.lang.String Categoryid
     * @return Category The Category entity
     */
    public Category loadCategory(java.lang.String categoryid);    

    /**
     * Loads a list of Category entity 
     * @param List<java.lang.String> categoryids
     * @return List<Category> The Category entity
     */
    public List<Category> loadCategoryListByCategory (List<Category> categorys);
    
    /**
     * Loads a list of Category entity 
     * @param List<java.lang.String> categoryids
     * @return List<Category> The Category entity
     */
    public List<Category> loadCategoryListByCategoryid(List<java.lang.String> categoryids);
    
    /**
     * Loads the Category entity which is related to an instance of
     * Category and its dependent one to many objects
     * @param Long id
     * @return Category The Category entity
     */
    public Category loadFullFirstLevelCategory(java.lang.String categoryid);
    
    /**
     * Loads the Category entity which is related to an instance of
     * Category
     * @param Category category
     * @return Category The Category entity
     */
    public Category loadFullFirstLevelCategory(Category category);    
    
    
    /**
     * Loads the Category entity which is related to an instance of
     * Category and its dependent objects one to many
     * @param Long id
     * @return Category The Category entity
     */
    public Category loadFullCategory(Long id) ;

    /**
     * Searches a list of Category entity based on a Category containing Category matching criteria on the positive mask
     * @param Category category
     * @return List<Category>
     */
    public List<Category> searchPrototypeCategory(Category category) ;
    

    /**
     * Searches a list of Category entity based on a Category containing Category matching criteria on any field
     * @param Category category
     * @return List<Category>
     */
    public List<Category> searchPrototypeAnyCategory(Category category) ;

    public List<Category> find (Category category, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) ;

    /**
     * Searches a list of Category entity based on a list of Category containing Category matching criteria
     * @param List<Category> categorys
     * @return List<Category>
     */
    public List<Category> searchPrototypeCategory(List<Category> categorys) ;    
    	
	/**
     * Searches a list of Category entity 
     * @param Category category
     * @return List
     */
    public List<Category> searchPrototypeCategory(Category categoryPositive, Category categoryNegative) ;

}
