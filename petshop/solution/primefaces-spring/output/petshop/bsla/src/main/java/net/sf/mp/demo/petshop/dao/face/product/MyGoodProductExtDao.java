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

import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.model.dao.GenericDao;

import net.sf.minuteProject.model.data.criteria.QueryData;
import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;
//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: MyGoodProductExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with MyGoodProductExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface MyGoodProductExtDao {     
     /**
     * Inserts a MyGoodProduct entity with cascade of its children
     * @param MyGoodProduct mygoodproduct
     */
    public void insertMyGoodProductWithCascade(MyGoodProduct mygoodproduct) ;
    
    /**
     * Inserts a list of MyGoodProduct entity with cascade of its children
     * @param List<MyGoodProduct> mygoodproducts
     */
    public void insertMyGoodProductsWithCascade(List<MyGoodProduct> mygoodproducts) ;        
   
    /**
     * lookup MyGoodProduct entity MyGoodProduct, criteria and max result number
     */
    public List<MyGoodProduct> lookupMyGoodProduct(MyGoodProduct mygoodproduct, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyMyGoodProduct (MyGoodProduct mygoodproduct, Criteria criteria);

	/**
	 * Affect the first mygoodproduct retrieved corresponding to the mygoodproduct criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public MyGoodProduct affectMyGoodProduct (MyGoodProduct mygoodproduct);
    
    public MyGoodProduct affectMyGoodProductUseCache (MyGoodProduct mygoodproduct);
    	
	/**
	 * Assign the first mygoodproduct retrieved corresponding to the mygoodproduct criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no mygoodproduct corresponding in the database. Then mygoodproduct is inserted and returned with its primary key(s). 
	 */
    public MyGoodProduct assignMyGoodProduct (MyGoodProduct mygoodproduct);

	/**
	 * Assign the first mygoodproduct retrieved corresponding to the mask criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no mygoodproduct corresponding in the database. 
	 * Then mygoodproduct is inserted and returned with its primary key(s). 
	 * Mask servers usually to set unique keys or the semantic reference
	 */
    public MyGoodProduct assignMyGoodProduct (MyGoodProduct mygoodproduct, MyGoodProduct mask);
	
    public MyGoodProduct assignMyGoodProductUseCache (MyGoodProduct mygoodproduct);
         
    /**
    * return the first MyGoodProduct entity found
    */           
    public MyGoodProduct getFirstMyGoodProduct (MyGoodProduct mygoodproduct);
    
    /**
    * checks if the MyGoodProduct entity exists
    */           
    public boolean existsMyGoodProduct (MyGoodProduct mygoodproduct);    
    
    public boolean existsMyGoodProductWhereConditionsAre (MyGoodProduct mygoodproduct);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<MyGoodProduct> partialLoadMyGoodProduct(MyGoodProduct mygoodproduct, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<MyGoodProduct> partialLoadWithParentMyGoodProduct(MyGoodProduct mygoodproduct, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct);

    public List<MyGoodProduct> partialLoadWithParentMyGoodProductUseCache(MyGoodProduct mygoodproduct, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, Boolean useCache);

    public List<MyGoodProduct> partialLoadWithParentMyGoodProductUseCacheOnResult(MyGoodProduct mygoodproduct, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, Boolean useCache);

    /**
    * variation first
    */
    public MyGoodProduct partialLoadWithParentFirstMyGoodProduct(MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct);
    
    public MyGoodProduct partialLoadWithParentFirstMyGoodProductUseCache(MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, Boolean useCache);

    public MyGoodProduct partialLoadWithParentFirstMyGoodProductUseCacheOnResult(MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, Boolean useCache);

    /**
    * variation distinct
    */
    public List<MyGoodProduct> getDistinctMyGoodProduct(MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct);

    //
    public List partialLoadWithParentForBean(Object bean, MyGoodProduct mygoodproduct, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct);

    /**
    * search on prototype with cache
    */
    public List<MyGoodProduct> searchPrototypeWithCacheMyGoodProduct (MyGoodProduct mygoodproduct);
      
   /**
   * uk<->pk
   */
   public MyGoodProduct loadMyGoodProductFromUniqueKey (MyGoodProduct mygoodproduct);

   public MyGoodProduct loadMyGoodProductFromUniqueKeyWithCacheOnResult (MyGoodProduct mygoodproduct);

   public String loadMyGoodProductPkFromUniqueKey (MyGoodProduct mygoodproduct);

   public String loadMyGoodProductPkFromUniqueKeyWithCacheOnResult (MyGoodProduct mygoodproduct);
    
    /**
     * Searches a list of distinct MyGoodProduct entity based on a MyGoodProduct mask and a list of MyGoodProduct containing MyGoodProduct matching criteria
     * @param MyGoodProduct mygoodproduct
     * @param List<MyGoodProduct> mygoodproducts
     * @return List<MyGoodProduct>
     */
    public List<MyGoodProduct> searchDistinctPrototypeMyGoodProduct(MyGoodProduct mygoodproductMask, List<MyGoodProduct> mygoodproducts) ;    

	public List<MyGoodProduct> countDistinct (MyGoodProduct whatMask, MyGoodProduct whereEqCriteria);
	
	public Long count (MyGoodProduct whereEqCriteria);
	
	public List<MyGoodProduct> loadGraph(MyGoodProduct graphMaskWhat, List<MyGoodProduct> whereMask);  
	
	public List<MyGoodProduct> loadGraphFromParentKey (MyGoodProduct graphMaskWhat, List<MyGoodProduct> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

    /**
     * return a list of MyGoodProduct entities 
     */
    public List<MyGoodProduct> getList ();
        
    public List<MyGoodProduct> getList (MyGoodProduct orderMask, QuerySortOrder sortOrder);

    public List<MyGoodProduct> list (MyGoodProduct mask, MyGoodProduct orderMask, QuerySortOrder sortOrder);

	public void find (QueryData<MyGoodProduct> data);
//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}

