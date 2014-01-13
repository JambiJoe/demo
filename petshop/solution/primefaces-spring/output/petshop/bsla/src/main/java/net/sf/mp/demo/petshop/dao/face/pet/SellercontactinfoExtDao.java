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

import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.model.dao.GenericDao;

import net.sf.minuteProject.model.data.criteria.QueryData;
import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;
//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: SellercontactinfoExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with SellercontactinfoExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface SellercontactinfoExtDao {     
     /**
     * Inserts a Sellercontactinfo entity with cascade of its children
     * @param Sellercontactinfo sellercontactinfo
     */
    public void insertSellercontactinfoWithCascade(Sellercontactinfo sellercontactinfo) ;
    
    /**
     * Inserts a list of Sellercontactinfo entity with cascade of its children
     * @param List<Sellercontactinfo> sellercontactinfos
     */
    public void insertSellercontactinfosWithCascade(List<Sellercontactinfo> sellercontactinfos) ;        
   
    /**
     * lookup Sellercontactinfo entity Sellercontactinfo, criteria and max result number
     */
    public List<Sellercontactinfo> lookupSellercontactinfo(Sellercontactinfo sellercontactinfo, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlySellercontactinfo (Sellercontactinfo sellercontactinfo, Criteria criteria);

	/**
	 * Affect the first sellercontactinfo retrieved corresponding to the sellercontactinfo criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public Sellercontactinfo affectSellercontactinfo (Sellercontactinfo sellercontactinfo);
    
    public Sellercontactinfo affectSellercontactinfoUseCache (Sellercontactinfo sellercontactinfo);
    	
	/**
	 * Assign the first sellercontactinfo retrieved corresponding to the sellercontactinfo criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no sellercontactinfo corresponding in the database. Then sellercontactinfo is inserted and returned with its primary key(s). 
	 */
    public Sellercontactinfo assignSellercontactinfo (Sellercontactinfo sellercontactinfo);

	/**
	 * Assign the first sellercontactinfo retrieved corresponding to the mask criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no sellercontactinfo corresponding in the database. 
	 * Then sellercontactinfo is inserted and returned with its primary key(s). 
	 * Mask servers usually to set unique keys or the semantic reference
	 */
    public Sellercontactinfo assignSellercontactinfo (Sellercontactinfo sellercontactinfo, Sellercontactinfo mask);
	
    public Sellercontactinfo assignSellercontactinfoUseCache (Sellercontactinfo sellercontactinfo);
         
    /**
    * return the first Sellercontactinfo entity found
    */           
    public Sellercontactinfo getFirstSellercontactinfo (Sellercontactinfo sellercontactinfo);
    
    /**
    * checks if the Sellercontactinfo entity exists
    */           
    public boolean existsSellercontactinfo (Sellercontactinfo sellercontactinfo);    
    
    public boolean existsSellercontactinfoWhereConditionsAre (Sellercontactinfo sellercontactinfo);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<Sellercontactinfo> partialLoadSellercontactinfo(Sellercontactinfo sellercontactinfo, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<Sellercontactinfo> partialLoadWithParentSellercontactinfo(Sellercontactinfo sellercontactinfo, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo);

    public List<Sellercontactinfo> partialLoadWithParentSellercontactinfoUseCache(Sellercontactinfo sellercontactinfo, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, Boolean useCache);

    public List<Sellercontactinfo> partialLoadWithParentSellercontactinfoUseCacheOnResult(Sellercontactinfo sellercontactinfo, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, Boolean useCache);

    /**
    * variation first
    */
    public Sellercontactinfo partialLoadWithParentFirstSellercontactinfo(Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo);
    
    public Sellercontactinfo partialLoadWithParentFirstSellercontactinfoUseCache(Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, Boolean useCache);

    public Sellercontactinfo partialLoadWithParentFirstSellercontactinfoUseCacheOnResult(Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, Boolean useCache);

    /**
    * variation distinct
    */
    public List<Sellercontactinfo> getDistinctSellercontactinfo(Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo);

    //
    public List partialLoadWithParentForBean(Object bean, Sellercontactinfo sellercontactinfo, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo);

    /**
    * search on prototype with cache
    */
    public List<Sellercontactinfo> searchPrototypeWithCacheSellercontactinfo (Sellercontactinfo sellercontactinfo);
      
    
    /**
     * Searches a list of distinct Sellercontactinfo entity based on a Sellercontactinfo mask and a list of Sellercontactinfo containing Sellercontactinfo matching criteria
     * @param Sellercontactinfo sellercontactinfo
     * @param List<Sellercontactinfo> sellercontactinfos
     * @return List<Sellercontactinfo>
     */
    public List<Sellercontactinfo> searchDistinctPrototypeSellercontactinfo(Sellercontactinfo sellercontactinfoMask, List<Sellercontactinfo> sellercontactinfos) ;    

	public List<Sellercontactinfo> countDistinct (Sellercontactinfo whatMask, Sellercontactinfo whereEqCriteria);
	
	public Long count (Sellercontactinfo whereEqCriteria);
	
	public List<Sellercontactinfo> loadGraph(Sellercontactinfo graphMaskWhat, List<Sellercontactinfo> whereMask);  
	
	public List<Sellercontactinfo> loadGraphFromParentKey (Sellercontactinfo graphMaskWhat, List<Sellercontactinfo> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

    /**
     * return a list of Sellercontactinfo entities 
     */
    public List<Sellercontactinfo> getList ();
        
    public List<Sellercontactinfo> getList (Sellercontactinfo orderMask, QuerySortOrder sortOrder);

    public List<Sellercontactinfo> list (Sellercontactinfo mask, Sellercontactinfo orderMask, QuerySortOrder sortOrder);

	public void find (QueryData<Sellercontactinfo> data);
//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}

