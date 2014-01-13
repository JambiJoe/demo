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
package net.sf.mp.demo.petshop.dao.face.product;

import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;

/**
 *
 * <p>Title: MyGoodProductDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with MyGoodProductDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching mygoodproduct objects</p>
 *
 */
public interface MyGoodProductDao {
    /**
     * Inserts a MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     */
    public void insertMyGoodProduct(MyGoodProduct mygoodproduct) ;
 
    /**
     * Inserts a list of MyGoodProduct entity 
     * @param List<MyGoodProduct> mygoodproducts
     */
    public void insertMyGoodProducts(List<MyGoodProduct> mygoodproducts) ;
        
    /**
     * Updates a MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     */
    public MyGoodProduct updateMyGoodProduct(MyGoodProduct mygoodproduct) ;

	 /**
     * Updates a MyGoodProduct entity with only the attributes set into MyGoodProduct.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param MyGoodProduct mygoodproduct
   */
    public int updateNotNullOnlyMyGoodProduct(MyGoodProduct mygoodproduct) ;
	 
	public int updateNotNullOnlyPrototypeMyGoodProduct(MyGoodProduct mygoodproduct, MyGoodProduct prototypeCriteria);
	
     /**
     * Saves a MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     */
    public void saveMyGoodProduct(MyGoodProduct mygoodproduct);
    
    /**
     * Deletes a MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     */
    public void deleteMyGoodProduct(MyGoodProduct mygoodproduct) ;
 
    /**
     * Loads the MyGoodProduct entity which is related to an instance of
     * MyGoodProduct
     * @param Long id
     * @return MyGoodProduct The MyGoodProduct entity
     
    public MyGoodProduct loadMyGoodProduct(Long id);
*/
    /**
     * Loads the MyGoodProduct entity which is related to an instance of
     * MyGoodProduct
     * @param java.lang.String Productid
     * @return MyGoodProduct The MyGoodProduct entity
     */
    public MyGoodProduct loadMyGoodProduct(java.lang.String productid);    

    /**
     * Loads a list of MyGoodProduct entity 
     * @param List<java.lang.String> productids
     * @return List<MyGoodProduct> The MyGoodProduct entity
     */
    public List<MyGoodProduct> loadMyGoodProductListByMyGoodProduct (List<MyGoodProduct> mygoodproducts);
    
    /**
     * Loads a list of MyGoodProduct entity 
     * @param List<java.lang.String> productids
     * @return List<MyGoodProduct> The MyGoodProduct entity
     */
    public List<MyGoodProduct> loadMyGoodProductListByProductid(List<java.lang.String> productids);
    
    /**
     * Loads the MyGoodProduct entity which is related to an instance of
     * MyGoodProduct and its dependent one to many objects
     * @param Long id
     * @return MyGoodProduct The MyGoodProduct entity
     */
    public MyGoodProduct loadFullFirstLevelMyGoodProduct(java.lang.String productid);
    
    /**
     * Loads the MyGoodProduct entity which is related to an instance of
     * MyGoodProduct
     * @param MyGoodProduct mygoodproduct
     * @return MyGoodProduct The MyGoodProduct entity
     */
    public MyGoodProduct loadFullFirstLevelMyGoodProduct(MyGoodProduct mygoodproduct);    
    
    
    /**
     * Loads the MyGoodProduct entity which is related to an instance of
     * MyGoodProduct and its dependent objects one to many
     * @param Long id
     * @return MyGoodProduct The MyGoodProduct entity
     */
    public MyGoodProduct loadFullMyGoodProduct(Long id) ;

    /**
     * Searches a list of MyGoodProduct entity based on a MyGoodProduct containing MyGoodProduct matching criteria on the positive mask
     * @param MyGoodProduct mygoodproduct
     * @return List<MyGoodProduct>
     */
    public List<MyGoodProduct> searchPrototypeMyGoodProduct(MyGoodProduct mygoodproduct) ;
    

    /**
     * Searches a list of MyGoodProduct entity based on a MyGoodProduct containing MyGoodProduct matching criteria on any field
     * @param MyGoodProduct mygoodproduct
     * @return List<MyGoodProduct>
     */
    public List<MyGoodProduct> searchPrototypeAnyMyGoodProduct(MyGoodProduct mygoodproduct) ;

    public List<MyGoodProduct> find (MyGoodProduct mygoodproduct, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) ;

    /**
     * Searches a list of MyGoodProduct entity based on a list of MyGoodProduct containing MyGoodProduct matching criteria
     * @param List<MyGoodProduct> mygoodproducts
     * @return List<MyGoodProduct>
     */
    public List<MyGoodProduct> searchPrototypeMyGoodProduct(List<MyGoodProduct> mygoodproducts) ;    
    	
	/**
     * Searches a list of MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     * @return List
     */
    public List<MyGoodProduct> searchPrototypeMyGoodProduct(MyGoodProduct mygoodproductPositive, MyGoodProduct mygoodproductNegative) ;

}
