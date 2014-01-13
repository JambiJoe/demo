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

import net.sf.mp.demo.petshop.domain.pet.Ziplocation;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.model.dao.GenericDao;

import net.sf.minuteProject.model.data.criteria.QueryData;
import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;
//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: ZiplocationExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with ZiplocationExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface ZiplocationExtDao {     
     /**
     * Inserts a Ziplocation entity with cascade of its children
     * @param Ziplocation ziplocation
     */
    public void insertZiplocationWithCascade(Ziplocation ziplocation) ;
    
    /**
     * Inserts a list of Ziplocation entity with cascade of its children
     * @param List<Ziplocation> ziplocations
     */
    public void insertZiplocationsWithCascade(List<Ziplocation> ziplocations) ;        
   
    /**
     * lookup Ziplocation entity Ziplocation, criteria and max result number
     */
    public List<Ziplocation> lookupZiplocation(Ziplocation ziplocation, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyZiplocation (Ziplocation ziplocation, Criteria criteria);

	/**
	 * Affect the first ziplocation retrieved corresponding to the ziplocation criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public Ziplocation affectZiplocation (Ziplocation ziplocation);
    
    public Ziplocation affectZiplocationUseCache (Ziplocation ziplocation);
    	
	/**
	 * Assign the first ziplocation retrieved corresponding to the ziplocation criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no ziplocation corresponding in the database. Then ziplocation is inserted and returned with its primary key(s). 
	 */
    public Ziplocation assignZiplocation (Ziplocation ziplocation);

	/**
	 * Assign the first ziplocation retrieved corresponding to the mask criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no ziplocation corresponding in the database. 
	 * Then ziplocation is inserted and returned with its primary key(s). 
	 * Mask servers usually to set unique keys or the semantic reference
	 */
    public Ziplocation assignZiplocation (Ziplocation ziplocation, Ziplocation mask);
	
    public Ziplocation assignZiplocationUseCache (Ziplocation ziplocation);
         
    /**
    * return the first Ziplocation entity found
    */           
    public Ziplocation getFirstZiplocation (Ziplocation ziplocation);
    
    /**
    * checks if the Ziplocation entity exists
    */           
    public boolean existsZiplocation (Ziplocation ziplocation);    
    
    public boolean existsZiplocationWhereConditionsAre (Ziplocation ziplocation);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<Ziplocation> partialLoadZiplocation(Ziplocation ziplocation, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<Ziplocation> partialLoadWithParentZiplocation(Ziplocation ziplocation, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation);

    public List<Ziplocation> partialLoadWithParentZiplocationUseCache(Ziplocation ziplocation, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, Boolean useCache);

    public List<Ziplocation> partialLoadWithParentZiplocationUseCacheOnResult(Ziplocation ziplocation, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, Boolean useCache);

    /**
    * variation first
    */
    public Ziplocation partialLoadWithParentFirstZiplocation(Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation);
    
    public Ziplocation partialLoadWithParentFirstZiplocationUseCache(Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, Boolean useCache);

    public Ziplocation partialLoadWithParentFirstZiplocationUseCacheOnResult(Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, Boolean useCache);

    /**
    * variation distinct
    */
    public List<Ziplocation> getDistinctZiplocation(Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation);

    //
    public List partialLoadWithParentForBean(Object bean, Ziplocation ziplocation, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation);

    /**
    * search on prototype with cache
    */
    public List<Ziplocation> searchPrototypeWithCacheZiplocation (Ziplocation ziplocation);
      
    
    /**
     * Searches a list of distinct Ziplocation entity based on a Ziplocation mask and a list of Ziplocation containing Ziplocation matching criteria
     * @param Ziplocation ziplocation
     * @param List<Ziplocation> ziplocations
     * @return List<Ziplocation>
     */
    public List<Ziplocation> searchDistinctPrototypeZiplocation(Ziplocation ziplocationMask, List<Ziplocation> ziplocations) ;    

	public List<Ziplocation> countDistinct (Ziplocation whatMask, Ziplocation whereEqCriteria);
	
	public Long count (Ziplocation whereEqCriteria);
	
	public List<Ziplocation> loadGraph(Ziplocation graphMaskWhat, List<Ziplocation> whereMask);  
	
	public List<Ziplocation> loadGraphFromParentKey (Ziplocation graphMaskWhat, List<Ziplocation> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

    /**
     * return a list of Ziplocation entities 
     */
    public List<Ziplocation> getList ();
        
    public List<Ziplocation> getList (Ziplocation orderMask, QuerySortOrder sortOrder);

    public List<Ziplocation> list (Ziplocation mask, Ziplocation orderMask, QuerySortOrder sortOrder);

	public void find (QueryData<Ziplocation> data);
//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}

