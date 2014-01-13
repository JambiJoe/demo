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
package net.sf.mp.demo.petshop.service.face.pet;

import java.util.List;

import net.sf.mp.demo.petshop.domain.pet.Category;
import net.sf.minuteProject.model.service.GenericService;
import net.sf.minuteProject.model.service.GenericService;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;

import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: CategoryService</p>
 *
 * <p>Description: Service layer Interface 
 * It offers coarse grain methods over the fine grain DAO layer:
 * It performs input and business validation.
 * </p>
 *
 */
public interface CategoryService extends GenericService<Category> {

    /**
     * Inserts a Category entity Category 
     * @param Category category
     */
    public void create (Category category) ;

    /**
     * Updates a Category entity 
     * @param Category category
     */
    public Category update(Category category) ;

     /**
     * Saves a Category entity 
     * @param Category category
     */
    public void save(Category category);
         
    public List<Category> findAll (Category category) ;
         
    public List<Category> findAny (Category category) ;

    public List<Category> find (Category category, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) ;

    /**
     * Deletes a Category entity 
     * @param Category category
     */
    public void delete(Category category) ; 
   
	public Category findById (java.lang.String categoryid);
   
	public Category load (java.lang.String categoryid);
	
    /**
     * return a list of Category entities 
     */
    public List<Category> getList ();

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
