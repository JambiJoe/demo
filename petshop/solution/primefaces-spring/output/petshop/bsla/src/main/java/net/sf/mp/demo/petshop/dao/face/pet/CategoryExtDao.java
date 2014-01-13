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
	* - name      : BslaDaoInterfaceExtendedUML
	* - file name : BslaDaoInterfaceKFUML.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:20 CET
*/
package net.sf.mp.demo.petshop.dao.face.pet;

import net.sf.mp.demo.petshop.domain.pet.Category;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.model.dao.GenericDao;

import net.sf.minuteProject.model.data.criteria.QueryData;
import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;
//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: CategoryExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with CategoryExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface CategoryExtDao {     
     /**
     * Inserts a Category entity with cascade of its children
     * @param Category category
     */
    public void insertCategoryWithCascade(Category category) ;
    
    /**
     * Inserts a list of Category entity with cascade of its children
     * @param List<Category> categorys
     */
    public void insertCategorysWithCascade(List<Category> categorys) ;        
   
    /**
     * lookup Category entity Category, criteria and max result number
     */
    public List<Category> lookupCategory(Category category, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyCategory (Category category, Criteria criteria);

	/**
	 * Affect the first category retrieved corresponding to the category criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public Category affectCategory (Category category);
    
    public Category affectCategoryUseCache (Category category);
    	
	/**
	 * Assign the first category retrieved corresponding to the category criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no category corresponding in the database. Then category is inserted and returned with its primary key(s). 
	 */
    public Category assignCategory (Category category);

	/**
	 * Assign the first category retrieved corresponding to the mask criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no category corresponding in the database. 
	 * Then category is inserted and returned with its primary key(s). 
	 * Mask servers usually to set unique keys or the semantic reference
	 */
    public Category assignCategory (Category category, Category mask);
	
    public Category assignCategoryUseCache (Category category);
         
    /**
    * return the first Category entity found
    */           
    public Category getFirstCategory (Category category);
    
    /**
    * checks if the Category entity exists
    */           
    public boolean existsCategory (Category category);    
    
    public boolean existsCategoryWhereConditionsAre (Category category);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<Category> partialLoadCategory(Category category, Category positiveCategory, Category negativeCategory);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<Category> partialLoadWithParentCategory(Category category, Category positiveCategory, Category negativeCategory);

    public List<Category> partialLoadWithParentCategoryUseCache(Category category, Category positiveCategory, Category negativeCategory, Boolean useCache);

    public List<Category> partialLoadWithParentCategoryUseCacheOnResult(Category category, Category positiveCategory, Category negativeCategory, Boolean useCache);

    /**
    * variation first
    */
    public Category partialLoadWithParentFirstCategory(Category categoryWhat, Category positiveCategory, Category negativeCategory);
    
    public Category partialLoadWithParentFirstCategoryUseCache(Category categoryWhat, Category positiveCategory, Category negativeCategory, Boolean useCache);

    public Category partialLoadWithParentFirstCategoryUseCacheOnResult(Category categoryWhat, Category positiveCategory, Category negativeCategory, Boolean useCache);

    /**
    * variation distinct
    */
    public List<Category> getDistinctCategory(Category categoryWhat, Category positiveCategory, Category negativeCategory);

    //
    public List partialLoadWithParentForBean(Object bean, Category category, Category positiveCategory, Category negativeCategory);

    /**
    * search on prototype with cache
    */
    public List<Category> searchPrototypeWithCacheCategory (Category category);
      
    
    /**
     * Searches a list of distinct Category entity based on a Category mask and a list of Category containing Category matching criteria
     * @param Category category
     * @param List<Category> categorys
     * @return List<Category>
     */
    public List<Category> searchDistinctPrototypeCategory(Category categoryMask, List<Category> categorys) ;    

	public List<Category> countDistinct (Category whatMask, Category whereEqCriteria);
	
	public Long count (Category whereEqCriteria);
	
	public List<Category> loadGraph(Category graphMaskWhat, List<Category> whereMask);  
	
	public List<Category> loadGraphFromParentKey (Category graphMaskWhat, List<Category> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

    /**
     * return a list of Category entities 
     */
    public List<Category> getList ();
        
    public List<Category> getList (Category orderMask, QuerySortOrder sortOrder);

    public List<Category> list (Category mask, Category orderMask, QuerySortOrder sortOrder);

	public void find (QueryData<Category> data);
//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}

