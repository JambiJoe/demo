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

import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;

/**
 *
 * <p>Title: MyGoodItemDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with MyGoodItemDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching mygooditem objects</p>
 *
 */
public interface MyGoodItemDao {
    /**
     * Inserts a MyGoodItem entity 
     * @param MyGoodItem mygooditem
     */
    public void insertMyGoodItem(MyGoodItem mygooditem) ;
 
    /**
     * Inserts a list of MyGoodItem entity 
     * @param List<MyGoodItem> mygooditems
     */
    public void insertMyGoodItems(List<MyGoodItem> mygooditems) ;
        
    /**
     * Updates a MyGoodItem entity 
     * @param MyGoodItem mygooditem
     */
    public MyGoodItem updateMyGoodItem(MyGoodItem mygooditem) ;

	 /**
     * Updates a MyGoodItem entity with only the attributes set into MyGoodItem.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param MyGoodItem mygooditem
   */
    public int updateNotNullOnlyMyGoodItem(MyGoodItem mygooditem) ;
	 
	public int updateNotNullOnlyPrototypeMyGoodItem(MyGoodItem mygooditem, MyGoodItem prototypeCriteria);
	
     /**
     * Saves a MyGoodItem entity 
     * @param MyGoodItem mygooditem
     */
    public void saveMyGoodItem(MyGoodItem mygooditem);
    
    /**
     * Deletes a MyGoodItem entity 
     * @param MyGoodItem mygooditem
     */
    public void deleteMyGoodItem(MyGoodItem mygooditem) ;
 
    /**
     * Loads the MyGoodItem entity which is related to an instance of
     * MyGoodItem
     * @param Long id
     * @return MyGoodItem The MyGoodItem entity
     
    public MyGoodItem loadMyGoodItem(Long id);
*/
    /**
     * Loads the MyGoodItem entity which is related to an instance of
     * MyGoodItem
     * @param java.lang.Integer Itemid
     * @return MyGoodItem The MyGoodItem entity
     */
    public MyGoodItem loadMyGoodItem(java.lang.Integer itemid);    

    /**
     * Loads a list of MyGoodItem entity 
     * @param List<java.lang.Integer> itemids
     * @return List<MyGoodItem> The MyGoodItem entity
     */
    public List<MyGoodItem> loadMyGoodItemListByMyGoodItem (List<MyGoodItem> mygooditems);
    
    /**
     * Loads a list of MyGoodItem entity 
     * @param List<java.lang.Integer> itemids
     * @return List<MyGoodItem> The MyGoodItem entity
     */
    public List<MyGoodItem> loadMyGoodItemListByItemid(List<java.lang.Integer> itemids);
    
    /**
     * Loads the MyGoodItem entity which is related to an instance of
     * MyGoodItem and its dependent one to many objects
     * @param Long id
     * @return MyGoodItem The MyGoodItem entity
     */
    public MyGoodItem loadFullFirstLevelMyGoodItem(java.lang.Integer itemid);
    
    /**
     * Loads the MyGoodItem entity which is related to an instance of
     * MyGoodItem
     * @param MyGoodItem mygooditem
     * @return MyGoodItem The MyGoodItem entity
     */
    public MyGoodItem loadFullFirstLevelMyGoodItem(MyGoodItem mygooditem);    
    
    
    /**
     * Loads the MyGoodItem entity which is related to an instance of
     * MyGoodItem and its dependent objects one to many
     * @param Long id
     * @return MyGoodItem The MyGoodItem entity
     */
    public MyGoodItem loadFullMyGoodItem(Long id) ;

    /**
     * Searches a list of MyGoodItem entity based on a MyGoodItem containing MyGoodItem matching criteria on the positive mask
     * @param MyGoodItem mygooditem
     * @return List<MyGoodItem>
     */
    public List<MyGoodItem> searchPrototypeMyGoodItem(MyGoodItem mygooditem) ;
    

    /**
     * Searches a list of MyGoodItem entity based on a MyGoodItem containing MyGoodItem matching criteria on any field
     * @param MyGoodItem mygooditem
     * @return List<MyGoodItem>
     */
    public List<MyGoodItem> searchPrototypeAnyMyGoodItem(MyGoodItem mygooditem) ;

    public List<MyGoodItem> find (MyGoodItem mygooditem, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) ;

    /**
     * Searches a list of MyGoodItem entity based on a list of MyGoodItem containing MyGoodItem matching criteria
     * @param List<MyGoodItem> mygooditems
     * @return List<MyGoodItem>
     */
    public List<MyGoodItem> searchPrototypeMyGoodItem(List<MyGoodItem> mygooditems) ;    
    	
	/**
     * Searches a list of MyGoodItem entity 
     * @param MyGoodItem mygooditem
     * @return List
     */
    public List<MyGoodItem> searchPrototypeMyGoodItem(MyGoodItem mygooditemPositive, MyGoodItem mygooditemNegative) ;

}
