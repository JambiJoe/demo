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

import net.sf.mp.demo.petshop.domain.pet.Tag;
import net.sf.minuteProject.model.service.GenericService;
import net.sf.minuteProject.model.service.GenericService;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;

import net.sf.mp.demo.petshop.domain.product.MyGoodItem;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: TagService</p>
 *
 * <p>Description: Service layer Interface 
 * It offers coarse grain methods over the fine grain DAO layer:
 * It performs input and business validation.
 * </p>
 *
 */
public interface TagService extends GenericService<Tag> {

    /**
     * Inserts a Tag entity Tag 
     * @param Tag tag
     */
    public void create (Tag tag) ;

    /**
     * Updates a Tag entity 
     * @param Tag tag
     */
    public Tag update(Tag tag) ;

     /**
     * Saves a Tag entity 
     * @param Tag tag
     */
    public void save(Tag tag);
         
    public List<Tag> findAll (Tag tag) ;
         
    public List<Tag> findAny (Tag tag) ;

    public List<Tag> find (Tag tag, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) ;

    /**
     * Deletes a Tag entity 
     * @param Tag tag
     */
    public void delete(Tag tag) ; 
   
	public Tag findById (java.lang.Integer tagid);
   
	public Tag load (java.lang.Integer tagid);
	
    /**
     * return a list of Tag entities 
     */
    public List<Tag> getList ();

	public void addMyGoodItemTagItemViaItemid(Tag c, MyGoodItem ... element) ;
	
	public void removeMyGoodItemTagItemViaItemid(Tag c, MyGoodItem ... element) ;
	
//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
