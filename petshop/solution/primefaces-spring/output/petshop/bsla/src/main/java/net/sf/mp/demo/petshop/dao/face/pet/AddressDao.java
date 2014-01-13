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

import net.sf.mp.demo.petshop.domain.pet.Address;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;

/**
 *
 * <p>Title: AddressDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with AddressDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching address objects</p>
 *
 */
public interface AddressDao {
    /**
     * Inserts a Address entity 
     * @param Address address
     */
    public void insertAddress(Address address) ;
 
    /**
     * Inserts a list of Address entity 
     * @param List<Address> addresss
     */
    public void insertAddresss(List<Address> addresss) ;
        
    /**
     * Updates a Address entity 
     * @param Address address
     */
    public Address updateAddress(Address address) ;

	 /**
     * Updates a Address entity with only the attributes set into Address.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param Address address
   */
    public int updateNotNullOnlyAddress(Address address) ;
	 
	public int updateNotNullOnlyPrototypeAddress(Address address, Address prototypeCriteria);
	
     /**
     * Saves a Address entity 
     * @param Address address
     */
    public void saveAddress(Address address);
    
    /**
     * Deletes a Address entity 
     * @param Address address
     */
    public void deleteAddress(Address address) ;
 
    /**
     * Loads the Address entity which is related to an instance of
     * Address
     * @param Long id
     * @return Address The Address entity
     
    public Address loadAddress(Long id);
*/
    /**
     * Loads the Address entity which is related to an instance of
     * Address
     * @param java.lang.Integer Addressid
     * @return Address The Address entity
     */
    public Address loadAddress(java.lang.Integer addressid);    

    /**
     * Loads a list of Address entity 
     * @param List<java.lang.Integer> addressids
     * @return List<Address> The Address entity
     */
    public List<Address> loadAddressListByAddress (List<Address> addresss);
    
    /**
     * Loads a list of Address entity 
     * @param List<java.lang.Integer> addressids
     * @return List<Address> The Address entity
     */
    public List<Address> loadAddressListByAddressid(List<java.lang.Integer> addressids);
    
    /**
     * Loads the Address entity which is related to an instance of
     * Address and its dependent one to many objects
     * @param Long id
     * @return Address The Address entity
     */
    public Address loadFullFirstLevelAddress(java.lang.Integer addressid);
    
    /**
     * Loads the Address entity which is related to an instance of
     * Address
     * @param Address address
     * @return Address The Address entity
     */
    public Address loadFullFirstLevelAddress(Address address);    
    
    
    /**
     * Loads the Address entity which is related to an instance of
     * Address and its dependent objects one to many
     * @param Long id
     * @return Address The Address entity
     */
    public Address loadFullAddress(Long id) ;

    /**
     * Searches a list of Address entity based on a Address containing Address matching criteria on the positive mask
     * @param Address address
     * @return List<Address>
     */
    public List<Address> searchPrototypeAddress(Address address) ;
    

    /**
     * Searches a list of Address entity based on a Address containing Address matching criteria on any field
     * @param Address address
     * @return List<Address>
     */
    public List<Address> searchPrototypeAnyAddress(Address address) ;

    public List<Address> find (Address address, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) ;

    /**
     * Searches a list of Address entity based on a list of Address containing Address matching criteria
     * @param List<Address> addresss
     * @return List<Address>
     */
    public List<Address> searchPrototypeAddress(List<Address> addresss) ;    
    	
	/**
     * Searches a list of Address entity 
     * @param Address address
     * @return List
     */
    public List<Address> searchPrototypeAddress(Address addressPositive, Address addressNegative) ;

}
