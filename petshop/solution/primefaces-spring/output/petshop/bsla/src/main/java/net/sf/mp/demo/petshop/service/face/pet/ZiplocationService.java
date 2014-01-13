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

import net.sf.mp.demo.petshop.domain.pet.Ziplocation;
import net.sf.minuteProject.model.service.GenericService;
import net.sf.minuteProject.model.service.GenericService;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;


//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: ZiplocationService</p>
 *
 * <p>Description: Service layer Interface 
 * It offers coarse grain methods over the fine grain DAO layer:
 * It performs input and business validation.
 * </p>
 *
 */
public interface ZiplocationService extends GenericService<Ziplocation> {

    /**
     * Inserts a Ziplocation entity Ziplocation 
     * @param Ziplocation ziplocation
     */
    public void create (Ziplocation ziplocation) ;

    /**
     * Updates a Ziplocation entity 
     * @param Ziplocation ziplocation
     */
    public Ziplocation update(Ziplocation ziplocation) ;

     /**
     * Saves a Ziplocation entity 
     * @param Ziplocation ziplocation
     */
    public void save(Ziplocation ziplocation);
         
    public List<Ziplocation> findAll (Ziplocation ziplocation) ;
         
    public List<Ziplocation> findAny (Ziplocation ziplocation) ;

    public List<Ziplocation> find (Ziplocation ziplocation, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) ;

    /**
     * Deletes a Ziplocation entity 
     * @param Ziplocation ziplocation
     */
    public void delete(Ziplocation ziplocation) ; 
   
	public Ziplocation findById (java.lang.Integer zipcode);
   
	public Ziplocation load (java.lang.Integer zipcode);
	
    /**
     * return a list of Ziplocation entities 
     */
    public List<Ziplocation> getList ();

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
