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

import net.sf.mp.demo.petshop.domain.pet.Tag;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;

/**
 *
 * <p>Title: TagDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with TagDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching tag objects</p>
 *
 */
public interface TagDao {
    /**
     * Inserts a Tag entity 
     * @param Tag tag
     */
    public void insertTag(Tag tag) ;
 
    /**
     * Inserts a list of Tag entity 
     * @param List<Tag> tags
     */
    public void insertTags(List<Tag> tags) ;
        
    /**
     * Updates a Tag entity 
     * @param Tag tag
     */
    public Tag updateTag(Tag tag) ;

	 /**
     * Updates a Tag entity with only the attributes set into Tag.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param Tag tag
   */
    public int updateNotNullOnlyTag(Tag tag) ;
	 
	public int updateNotNullOnlyPrototypeTag(Tag tag, Tag prototypeCriteria);
	
     /**
     * Saves a Tag entity 
     * @param Tag tag
     */
    public void saveTag(Tag tag);
    
    /**
     * Deletes a Tag entity 
     * @param Tag tag
     */
    public void deleteTag(Tag tag) ;
 
    /**
     * Loads the Tag entity which is related to an instance of
     * Tag
     * @param Long id
     * @return Tag The Tag entity
     
    public Tag loadTag(Long id);
*/
    /**
     * Loads the Tag entity which is related to an instance of
     * Tag
     * @param java.lang.Integer Tagid
     * @return Tag The Tag entity
     */
    public Tag loadTag(java.lang.Integer tagid);    

    /**
     * Loads a list of Tag entity 
     * @param List<java.lang.Integer> tagids
     * @return List<Tag> The Tag entity
     */
    public List<Tag> loadTagListByTag (List<Tag> tags);
    
    /**
     * Loads a list of Tag entity 
     * @param List<java.lang.Integer> tagids
     * @return List<Tag> The Tag entity
     */
    public List<Tag> loadTagListByTagid(List<java.lang.Integer> tagids);
    
    /**
     * Loads the Tag entity which is related to an instance of
     * Tag and its dependent one to many objects
     * @param Long id
     * @return Tag The Tag entity
     */
    public Tag loadFullFirstLevelTag(java.lang.Integer tagid);
    
    /**
     * Loads the Tag entity which is related to an instance of
     * Tag
     * @param Tag tag
     * @return Tag The Tag entity
     */
    public Tag loadFullFirstLevelTag(Tag tag);    
    
    
    /**
     * Loads the Tag entity which is related to an instance of
     * Tag and its dependent objects one to many
     * @param Long id
     * @return Tag The Tag entity
     */
    public Tag loadFullTag(Long id) ;

    /**
     * Searches a list of Tag entity based on a Tag containing Tag matching criteria on the positive mask
     * @param Tag tag
     * @return List<Tag>
     */
    public List<Tag> searchPrototypeTag(Tag tag) ;
    

    /**
     * Searches a list of Tag entity based on a Tag containing Tag matching criteria on any field
     * @param Tag tag
     * @return List<Tag>
     */
    public List<Tag> searchPrototypeAnyTag(Tag tag) ;

    public List<Tag> find (Tag tag, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) ;

    /**
     * Searches a list of Tag entity based on a list of Tag containing Tag matching criteria
     * @param List<Tag> tags
     * @return List<Tag>
     */
    public List<Tag> searchPrototypeTag(List<Tag> tags) ;    
    	
	/**
     * Searches a list of Tag entity 
     * @param Tag tag
     * @return List
     */
    public List<Tag> searchPrototypeTag(Tag tagPositive, Tag tagNegative) ;

}
