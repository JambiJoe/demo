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
	* - name      : SpringServiceInterface
	* - file name : SpringServiceInterface.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/
package net.sf.mp.demo.petshop.service.face.product;

import java.util.List;

import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.minuteProject.model.service.GenericService;
import net.sf.minuteProject.model.service.GenericService;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;

import net.sf.mp.demo.petshop.domain.pet.Address;
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;
import net.sf.mp.demo.petshop.domain.pet.Tag;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: MyGoodItemService</p>
 *
 * <p>Description: Service layer Interface 
 * It offers coarse grain methods over the fine grain DAO layer:
 * It performs input and business validation.
 * </p>
 *
 */
public interface MyGoodItemService extends GenericService<MyGoodItem> {

    /**
     * Inserts a MyGoodItem entity MyGoodItem 
     * @param MyGoodItem mygooditem
     */
    public void create (MyGoodItem mygooditem) ;

    /**
     * Updates a MyGoodItem entity 
     * @param MyGoodItem mygooditem
     */
    public MyGoodItem update(MyGoodItem mygooditem) ;

     /**
     * Saves a MyGoodItem entity 
     * @param MyGoodItem mygooditem
     */
    public void save(MyGoodItem mygooditem);
         
    public List<MyGoodItem> findAll (MyGoodItem mygooditem) ;
         
    public List<MyGoodItem> findAny (MyGoodItem mygooditem) ;

    public List<MyGoodItem> find (MyGoodItem mygooditem, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) ;

    /**
     * Deletes a MyGoodItem entity 
     * @param MyGoodItem mygooditem
     */
    public void delete(MyGoodItem mygooditem) ; 
   
	public MyGoodItem findById (java.lang.Integer itemid);
   
	public MyGoodItem load (java.lang.Integer itemid);
	
    /**
     * return a list of MyGoodItem entities 
     */
    public List<MyGoodItem> getList ();

	public void addTagTagItemViaTagid(MyGoodItem c, Tag ... element) ;
	
	public void removeTagTagItemViaTagid(MyGoodItem c, Tag ... element) ;
	
//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
