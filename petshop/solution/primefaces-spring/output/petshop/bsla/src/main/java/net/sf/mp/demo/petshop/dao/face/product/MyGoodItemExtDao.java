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
package net.sf.mp.demo.petshop.dao.face.product;

import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.model.dao.GenericDao;

import net.sf.minuteProject.model.data.criteria.QueryData;
import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;
//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: MyGoodItemExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with MyGoodItemExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface MyGoodItemExtDao {     
     /**
     * Inserts a MyGoodItem entity with cascade of its children
     * @param MyGoodItem mygooditem
     */
    public void insertMyGoodItemWithCascade(MyGoodItem mygooditem) ;
    
    /**
     * Inserts a list of MyGoodItem entity with cascade of its children
     * @param List<MyGoodItem> mygooditems
     */
    public void insertMyGoodItemsWithCascade(List<MyGoodItem> mygooditems) ;        
   
    /**
     * lookup MyGoodItem entity MyGoodItem, criteria and max result number
     */
    public List<MyGoodItem> lookupMyGoodItem(MyGoodItem mygooditem, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyMyGoodItem (MyGoodItem mygooditem, Criteria criteria);

	/**
	 * Affect the first mygooditem retrieved corresponding to the mygooditem criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public MyGoodItem affectMyGoodItem (MyGoodItem mygooditem);
    
    public MyGoodItem affectMyGoodItemUseCache (MyGoodItem mygooditem);
    	
	/**
	 * Assign the first mygooditem retrieved corresponding to the mygooditem criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no mygooditem corresponding in the database. Then mygooditem is inserted and returned with its primary key(s). 
	 */
    public MyGoodItem assignMyGoodItem (MyGoodItem mygooditem);

	/**
	 * Assign the first mygooditem retrieved corresponding to the mask criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no mygooditem corresponding in the database. 
	 * Then mygooditem is inserted and returned with its primary key(s). 
	 * Mask servers usually to set unique keys or the semantic reference
	 */
    public MyGoodItem assignMyGoodItem (MyGoodItem mygooditem, MyGoodItem mask);
	
    public MyGoodItem assignMyGoodItemUseCache (MyGoodItem mygooditem);
         
    /**
    * return the first MyGoodItem entity found
    */           
    public MyGoodItem getFirstMyGoodItem (MyGoodItem mygooditem);
    
    /**
    * checks if the MyGoodItem entity exists
    */           
    public boolean existsMyGoodItem (MyGoodItem mygooditem);    
    
    public boolean existsMyGoodItemWhereConditionsAre (MyGoodItem mygooditem);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<MyGoodItem> partialLoadMyGoodItem(MyGoodItem mygooditem, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<MyGoodItem> partialLoadWithParentMyGoodItem(MyGoodItem mygooditem, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem);

    public List<MyGoodItem> partialLoadWithParentMyGoodItemUseCache(MyGoodItem mygooditem, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, Boolean useCache);

    public List<MyGoodItem> partialLoadWithParentMyGoodItemUseCacheOnResult(MyGoodItem mygooditem, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, Boolean useCache);

    /**
    * variation first
    */
    public MyGoodItem partialLoadWithParentFirstMyGoodItem(MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem);
    
    public MyGoodItem partialLoadWithParentFirstMyGoodItemUseCache(MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, Boolean useCache);

    public MyGoodItem partialLoadWithParentFirstMyGoodItemUseCacheOnResult(MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, Boolean useCache);

    /**
    * variation distinct
    */
    public List<MyGoodItem> getDistinctMyGoodItem(MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem);

    //
    public List partialLoadWithParentForBean(Object bean, MyGoodItem mygooditem, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem);

    /**
    * search on prototype with cache
    */
    public List<MyGoodItem> searchPrototypeWithCacheMyGoodItem (MyGoodItem mygooditem);
      
   /**
   * uk<->pk
   */
   public MyGoodItem loadMyGoodItemFromUniqueKey (MyGoodItem mygooditem);

   public MyGoodItem loadMyGoodItemFromUniqueKeyWithCacheOnResult (MyGoodItem mygooditem);

   public Integer loadMyGoodItemPkFromUniqueKey (MyGoodItem mygooditem);

   public Integer loadMyGoodItemPkFromUniqueKeyWithCacheOnResult (MyGoodItem mygooditem);
    
    /**
     * Searches a list of distinct MyGoodItem entity based on a MyGoodItem mask and a list of MyGoodItem containing MyGoodItem matching criteria
     * @param MyGoodItem mygooditem
     * @param List<MyGoodItem> mygooditems
     * @return List<MyGoodItem>
     */
    public List<MyGoodItem> searchDistinctPrototypeMyGoodItem(MyGoodItem mygooditemMask, List<MyGoodItem> mygooditems) ;    

	public List<MyGoodItem> countDistinct (MyGoodItem whatMask, MyGoodItem whereEqCriteria);
	
	public Long count (MyGoodItem whereEqCriteria);
	
	public List<MyGoodItem> loadGraph(MyGoodItem graphMaskWhat, List<MyGoodItem> whereMask);  
	
	public List<MyGoodItem> loadGraphFromParentKey (MyGoodItem graphMaskWhat, List<MyGoodItem> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

    /**
     * return a list of MyGoodItem entities 
     */
    public List<MyGoodItem> getList ();
        
    public List<MyGoodItem> getList (MyGoodItem orderMask, QuerySortOrder sortOrder);

    public List<MyGoodItem> list (MyGoodItem mask, MyGoodItem orderMask, QuerySortOrder sortOrder);

	public void find (QueryData<MyGoodItem> data);
//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}

