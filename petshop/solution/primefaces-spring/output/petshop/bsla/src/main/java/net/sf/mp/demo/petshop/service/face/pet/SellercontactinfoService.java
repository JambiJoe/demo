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

import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;
import net.sf.minuteProject.model.service.GenericService;
import net.sf.minuteProject.model.service.GenericService;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;

import net.sf.mp.demo.petshop.domain.product.MyGoodItem;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: SellercontactinfoService</p>
 *
 * <p>Description: Service layer Interface 
 * It offers coarse grain methods over the fine grain DAO layer:
 * It performs input and business validation.
 * </p>
 *
 */
public interface SellercontactinfoService extends GenericService<Sellercontactinfo> {

    /**
     * Inserts a Sellercontactinfo entity Sellercontactinfo 
     * @param Sellercontactinfo sellercontactinfo
     */
    public void create (Sellercontactinfo sellercontactinfo) ;

    /**
     * Updates a Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     */
    public Sellercontactinfo update(Sellercontactinfo sellercontactinfo) ;

     /**
     * Saves a Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     */
    public void save(Sellercontactinfo sellercontactinfo);
         
    public List<Sellercontactinfo> findAll (Sellercontactinfo sellercontactinfo) ;
         
    public List<Sellercontactinfo> findAny (Sellercontactinfo sellercontactinfo) ;

    public List<Sellercontactinfo> find (Sellercontactinfo sellercontactinfo, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) ;

    /**
     * Deletes a Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     */
    public void delete(Sellercontactinfo sellercontactinfo) ; 
   
	public Sellercontactinfo findById (java.lang.Integer contactinfoid);
   
	public Sellercontactinfo load (java.lang.Integer contactinfoid);
	
    /**
     * return a list of Sellercontactinfo entities 
     */
    public List<Sellercontactinfo> getList ();

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
