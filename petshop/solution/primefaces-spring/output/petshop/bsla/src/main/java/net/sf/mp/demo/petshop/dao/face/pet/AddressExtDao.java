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
	* - name      : BslaDaoInterfaceExtendedUML
	* - file name : BslaDaoInterfaceKFUML.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:20 CET
*/
package net.sf.mp.demo.petshop.dao.face.pet;

import net.sf.mp.demo.petshop.domain.pet.Address;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.model.dao.GenericDao;

import net.sf.minuteProject.model.data.criteria.QueryData;
import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;
//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: AddressExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with AddressExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface AddressExtDao {     
     /**
     * Inserts a Address entity with cascade of its children
     * @param Address address
     */
    public void insertAddressWithCascade(Address address) ;
    
    /**
     * Inserts a list of Address entity with cascade of its children
     * @param List<Address> addresss
     */
    public void insertAddresssWithCascade(List<Address> addresss) ;        
   
    /**
     * lookup Address entity Address, criteria and max result number
     */
    public List<Address> lookupAddress(Address address, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyAddress (Address address, Criteria criteria);

	/**
	 * Affect the first address retrieved corresponding to the address criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public Address affectAddress (Address address);
    
    public Address affectAddressUseCache (Address address);
    	
	/**
	 * Assign the first address retrieved corresponding to the address criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no address corresponding in the database. Then address is inserted and returned with its primary key(s). 
	 */
    public Address assignAddress (Address address);

	/**
	 * Assign the first address retrieved corresponding to the mask criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no address corresponding in the database. 
	 * Then address is inserted and returned with its primary key(s). 
	 * Mask servers usually to set unique keys or the semantic reference
	 */
    public Address assignAddress (Address address, Address mask);
	
    public Address assignAddressUseCache (Address address);
         
    /**
    * return the first Address entity found
    */           
    public Address getFirstAddress (Address address);
    
    /**
    * checks if the Address entity exists
    */           
    public boolean existsAddress (Address address);    
    
    public boolean existsAddressWhereConditionsAre (Address address);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<Address> partialLoadAddress(Address address, Address positiveAddress, Address negativeAddress);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<Address> partialLoadWithParentAddress(Address address, Address positiveAddress, Address negativeAddress);

    public List<Address> partialLoadWithParentAddressUseCache(Address address, Address positiveAddress, Address negativeAddress, Boolean useCache);

    public List<Address> partialLoadWithParentAddressUseCacheOnResult(Address address, Address positiveAddress, Address negativeAddress, Boolean useCache);

    /**
    * variation first
    */
    public Address partialLoadWithParentFirstAddress(Address addressWhat, Address positiveAddress, Address negativeAddress);
    
    public Address partialLoadWithParentFirstAddressUseCache(Address addressWhat, Address positiveAddress, Address negativeAddress, Boolean useCache);

    public Address partialLoadWithParentFirstAddressUseCacheOnResult(Address addressWhat, Address positiveAddress, Address negativeAddress, Boolean useCache);

    /**
    * variation distinct
    */
    public List<Address> getDistinctAddress(Address addressWhat, Address positiveAddress, Address negativeAddress);

    //
    public List partialLoadWithParentForBean(Object bean, Address address, Address positiveAddress, Address negativeAddress);

    /**
    * search on prototype with cache
    */
    public List<Address> searchPrototypeWithCacheAddress (Address address);
      
    
    /**
     * Searches a list of distinct Address entity based on a Address mask and a list of Address containing Address matching criteria
     * @param Address address
     * @param List<Address> addresss
     * @return List<Address>
     */
    public List<Address> searchDistinctPrototypeAddress(Address addressMask, List<Address> addresss) ;    

	public List<Address> countDistinct (Address whatMask, Address whereEqCriteria);
	
	public Long count (Address whereEqCriteria);
	
	public List<Address> loadGraph(Address graphMaskWhat, List<Address> whereMask);  
	
	public List<Address> loadGraphFromParentKey (Address graphMaskWhat, List<Address> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

    /**
     * return a list of Address entities 
     */
    public List<Address> getList ();
        
    public List<Address> getList (Address orderMask, QuerySortOrder sortOrder);

    public List<Address> list (Address mask, Address orderMask, QuerySortOrder sortOrder);

	public void find (QueryData<Address> data);
//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}

