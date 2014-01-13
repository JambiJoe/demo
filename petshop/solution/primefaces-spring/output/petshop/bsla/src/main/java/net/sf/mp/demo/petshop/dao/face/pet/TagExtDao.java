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

import net.sf.mp.demo.petshop.domain.pet.Tag;
import java.util.List;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.model.dao.GenericDao;

import net.sf.minuteProject.model.data.criteria.QueryData;
import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;
//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: TagExtDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with TagExtDao
 * persistence. It offers extended DAO functionalities</p>
 *
 */
public interface TagExtDao {     
     /**
     * Inserts a Tag entity with cascade of its children
     * @param Tag tag
     */
    public void insertTagWithCascade(Tag tag) ;
    
    /**
     * Inserts a list of Tag entity with cascade of its children
     * @param List<Tag> tags
     */
    public void insertTagsWithCascade(List<Tag> tags) ;        
   
    /**
     * lookup Tag entity Tag, criteria and max result number
     */
    public List<Tag> lookupTag(Tag tag, Criteria criteria, Integer numberOfResult);
	
	public Integer updateNotNullOnlyTag (Tag tag, Criteria criteria);

	/**
	 * Affect the first tag retrieved corresponding to the tag criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 */
    public Tag affectTag (Tag tag);
    
    public Tag affectTagUseCache (Tag tag);
    	
	/**
	 * Assign the first tag retrieved corresponding to the tag criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no tag corresponding in the database. Then tag is inserted and returned with its primary key(s). 
	 */
    public Tag assignTag (Tag tag);

	/**
	 * Assign the first tag retrieved corresponding to the mask criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no tag corresponding in the database. 
	 * Then tag is inserted and returned with its primary key(s). 
	 * Mask servers usually to set unique keys or the semantic reference
	 */
    public Tag assignTag (Tag tag, Tag mask);
	
    public Tag assignTagUseCache (Tag tag);
         
    /**
    * return the first Tag entity found
    */           
    public Tag getFirstTag (Tag tag);
    
    /**
    * checks if the Tag entity exists
    */           
    public boolean existsTag (Tag tag);    
    
    public boolean existsTagWhereConditionsAre (Tag tag);

    /**
    * partial load enables to specify the fields you want to load explicitly
    */            
    public List<Tag> partialLoadTag(Tag tag, Tag positiveTag, Tag negativeTag);

    /**
    * partial load with parent entities
    * variation (list, first, distinct decorator)
    * variation2 (with cache)
    */
    public List<Tag> partialLoadWithParentTag(Tag tag, Tag positiveTag, Tag negativeTag);

    public List<Tag> partialLoadWithParentTagUseCache(Tag tag, Tag positiveTag, Tag negativeTag, Boolean useCache);

    public List<Tag> partialLoadWithParentTagUseCacheOnResult(Tag tag, Tag positiveTag, Tag negativeTag, Boolean useCache);

    /**
    * variation first
    */
    public Tag partialLoadWithParentFirstTag(Tag tagWhat, Tag positiveTag, Tag negativeTag);
    
    public Tag partialLoadWithParentFirstTagUseCache(Tag tagWhat, Tag positiveTag, Tag negativeTag, Boolean useCache);

    public Tag partialLoadWithParentFirstTagUseCacheOnResult(Tag tagWhat, Tag positiveTag, Tag negativeTag, Boolean useCache);

    /**
    * variation distinct
    */
    public List<Tag> getDistinctTag(Tag tagWhat, Tag positiveTag, Tag negativeTag);

    //
    public List partialLoadWithParentForBean(Object bean, Tag tag, Tag positiveTag, Tag negativeTag);

    /**
    * search on prototype with cache
    */
    public List<Tag> searchPrototypeWithCacheTag (Tag tag);
      
    
    /**
     * Searches a list of distinct Tag entity based on a Tag mask and a list of Tag containing Tag matching criteria
     * @param Tag tag
     * @param List<Tag> tags
     * @return List<Tag>
     */
    public List<Tag> searchDistinctPrototypeTag(Tag tagMask, List<Tag> tags) ;    

	public List<Tag> countDistinct (Tag whatMask, Tag whereEqCriteria);
	
	public Long count (Tag whereEqCriteria);
	
	public List<Tag> loadGraph(Tag graphMaskWhat, List<Tag> whereMask);  
	
	public List<Tag> loadGraphFromParentKey (Tag graphMaskWhat, List<Tag> parents); 
	
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query);     

    /**
     * return a list of Tag entities 
     */
    public List<Tag> getList ();
        
    public List<Tag> getList (Tag orderMask, QuerySortOrder sortOrder);

    public List<Tag> list (Tag mask, Tag orderMask, QuerySortOrder sortOrder);

	public void find (QueryData<Tag> data);
//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}

