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

import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import net.sf.minuteProject.model.service.GenericService;
import net.sf.minuteProject.model.service.GenericService;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;

import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.mp.demo.petshop.domain.pet.Category;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: MyGoodProductService</p>
 *
 * <p>Description: Service layer Interface 
 * It offers coarse grain methods over the fine grain DAO layer:
 * It performs input and business validation.
 * </p>
 *
 */
public interface MyGoodProductService extends GenericService<MyGoodProduct> {

    /**
     * Inserts a MyGoodProduct entity MyGoodProduct 
     * @param MyGoodProduct mygoodproduct
     */
    public void create (MyGoodProduct mygoodproduct) ;

    /**
     * Updates a MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     */
    public MyGoodProduct update(MyGoodProduct mygoodproduct) ;

     /**
     * Saves a MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     */
    public void save(MyGoodProduct mygoodproduct);
         
    public List<MyGoodProduct> findAll (MyGoodProduct mygoodproduct) ;
         
    public List<MyGoodProduct> findAny (MyGoodProduct mygoodproduct) ;

    public List<MyGoodProduct> find (MyGoodProduct mygoodproduct, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) ;

    /**
     * Deletes a MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     */
    public void delete(MyGoodProduct mygoodproduct) ; 
   
	public MyGoodProduct findById (java.lang.String productid);
   
	public MyGoodProduct load (java.lang.String productid);
	
    /**
     * return a list of MyGoodProduct entities 
     */
    public List<MyGoodProduct> getList ();

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
