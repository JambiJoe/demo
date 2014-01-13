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
package net.sf.mp.demo.petshop.dao.face.pet;

import net.sf.mp.demo.petshop.domain.pet.Ziplocation;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;

/**
 *
 * <p>Title: ZiplocationDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with ZiplocationDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching ziplocation objects</p>
 *
 */
public interface ZiplocationDao {
    /**
     * Inserts a Ziplocation entity 
     * @param Ziplocation ziplocation
     */
    public void insertZiplocation(Ziplocation ziplocation) ;
 
    /**
     * Inserts a list of Ziplocation entity 
     * @param List<Ziplocation> ziplocations
     */
    public void insertZiplocations(List<Ziplocation> ziplocations) ;
        
    /**
     * Updates a Ziplocation entity 
     * @param Ziplocation ziplocation
     */
    public Ziplocation updateZiplocation(Ziplocation ziplocation) ;

	 /**
     * Updates a Ziplocation entity with only the attributes set into Ziplocation.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param Ziplocation ziplocation
   */
    public int updateNotNullOnlyZiplocation(Ziplocation ziplocation) ;
	 
	public int updateNotNullOnlyPrototypeZiplocation(Ziplocation ziplocation, Ziplocation prototypeCriteria);
	
     /**
     * Saves a Ziplocation entity 
     * @param Ziplocation ziplocation
     */
    public void saveZiplocation(Ziplocation ziplocation);
    
    /**
     * Deletes a Ziplocation entity 
     * @param Ziplocation ziplocation
     */
    public void deleteZiplocation(Ziplocation ziplocation) ;
 
    /**
     * Loads the Ziplocation entity which is related to an instance of
     * Ziplocation
     * @param Long id
     * @return Ziplocation The Ziplocation entity
     
    public Ziplocation loadZiplocation(Long id);
*/
    /**
     * Loads the Ziplocation entity which is related to an instance of
     * Ziplocation
     * @param java.lang.Integer Zipcode
     * @return Ziplocation The Ziplocation entity
     */
    public Ziplocation loadZiplocation(java.lang.Integer zipcode);    

    /**
     * Loads a list of Ziplocation entity 
     * @param List<java.lang.Integer> zipcodes
     * @return List<Ziplocation> The Ziplocation entity
     */
    public List<Ziplocation> loadZiplocationListByZiplocation (List<Ziplocation> ziplocations);
    
    /**
     * Loads a list of Ziplocation entity 
     * @param List<java.lang.Integer> zipcodes
     * @return List<Ziplocation> The Ziplocation entity
     */
    public List<Ziplocation> loadZiplocationListByZipcode(List<java.lang.Integer> zipcodes);
    
    /**
     * Loads the Ziplocation entity which is related to an instance of
     * Ziplocation and its dependent one to many objects
     * @param Long id
     * @return Ziplocation The Ziplocation entity
     */
    public Ziplocation loadFullFirstLevelZiplocation(java.lang.Integer zipcode);
    
    /**
     * Loads the Ziplocation entity which is related to an instance of
     * Ziplocation
     * @param Ziplocation ziplocation
     * @return Ziplocation The Ziplocation entity
     */
    public Ziplocation loadFullFirstLevelZiplocation(Ziplocation ziplocation);    
    
    
    /**
     * Loads the Ziplocation entity which is related to an instance of
     * Ziplocation and its dependent objects one to many
     * @param Long id
     * @return Ziplocation The Ziplocation entity
     */
    public Ziplocation loadFullZiplocation(Long id) ;

    /**
     * Searches a list of Ziplocation entity based on a Ziplocation containing Ziplocation matching criteria on the positive mask
     * @param Ziplocation ziplocation
     * @return List<Ziplocation>
     */
    public List<Ziplocation> searchPrototypeZiplocation(Ziplocation ziplocation) ;
    

    /**
     * Searches a list of Ziplocation entity based on a Ziplocation containing Ziplocation matching criteria on any field
     * @param Ziplocation ziplocation
     * @return List<Ziplocation>
     */
    public List<Ziplocation> searchPrototypeAnyZiplocation(Ziplocation ziplocation) ;

    public List<Ziplocation> find (Ziplocation ziplocation, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) ;

    /**
     * Searches a list of Ziplocation entity based on a list of Ziplocation containing Ziplocation matching criteria
     * @param List<Ziplocation> ziplocations
     * @return List<Ziplocation>
     */
    public List<Ziplocation> searchPrototypeZiplocation(List<Ziplocation> ziplocations) ;    
    	
	/**
     * Searches a list of Ziplocation entity 
     * @param Ziplocation ziplocation
     * @return List
     */
    public List<Ziplocation> searchPrototypeZiplocation(Ziplocation ziplocationPositive, Ziplocation ziplocationNegative) ;

}
