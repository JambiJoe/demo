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

import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;
import java.util.List;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;

/**
 *
 * <p>Title: SellercontactinfoDao</p>
 *
 * <p>Description: Interface of a Data access object dealing with SellercontactinfoDao
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching sellercontactinfo objects</p>
 *
 */
public interface SellercontactinfoDao {
    /**
     * Inserts a Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     */
    public void insertSellercontactinfo(Sellercontactinfo sellercontactinfo) ;
 
    /**
     * Inserts a list of Sellercontactinfo entity 
     * @param List<Sellercontactinfo> sellercontactinfos
     */
    public void insertSellercontactinfos(List<Sellercontactinfo> sellercontactinfos) ;
        
    /**
     * Updates a Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     */
    public Sellercontactinfo updateSellercontactinfo(Sellercontactinfo sellercontactinfo) ;

	 /**
     * Updates a Sellercontactinfo entity with only the attributes set into Sellercontactinfo.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is to be done
   * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
   * @param Sellercontactinfo sellercontactinfo
   */
    public int updateNotNullOnlySellercontactinfo(Sellercontactinfo sellercontactinfo) ;
	 
	public int updateNotNullOnlyPrototypeSellercontactinfo(Sellercontactinfo sellercontactinfo, Sellercontactinfo prototypeCriteria);
	
     /**
     * Saves a Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     */
    public void saveSellercontactinfo(Sellercontactinfo sellercontactinfo);
    
    /**
     * Deletes a Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     */
    public void deleteSellercontactinfo(Sellercontactinfo sellercontactinfo) ;
 
    /**
     * Loads the Sellercontactinfo entity which is related to an instance of
     * Sellercontactinfo
     * @param Long id
     * @return Sellercontactinfo The Sellercontactinfo entity
     
    public Sellercontactinfo loadSellercontactinfo(Long id);
*/
    /**
     * Loads the Sellercontactinfo entity which is related to an instance of
     * Sellercontactinfo
     * @param java.lang.Integer Contactinfoid
     * @return Sellercontactinfo The Sellercontactinfo entity
     */
    public Sellercontactinfo loadSellercontactinfo(java.lang.Integer contactinfoid);    

    /**
     * Loads a list of Sellercontactinfo entity 
     * @param List<java.lang.Integer> contactinfoids
     * @return List<Sellercontactinfo> The Sellercontactinfo entity
     */
    public List<Sellercontactinfo> loadSellercontactinfoListBySellercontactinfo (List<Sellercontactinfo> sellercontactinfos);
    
    /**
     * Loads a list of Sellercontactinfo entity 
     * @param List<java.lang.Integer> contactinfoids
     * @return List<Sellercontactinfo> The Sellercontactinfo entity
     */
    public List<Sellercontactinfo> loadSellercontactinfoListByContactinfoid(List<java.lang.Integer> contactinfoids);
    
    /**
     * Loads the Sellercontactinfo entity which is related to an instance of
     * Sellercontactinfo and its dependent one to many objects
     * @param Long id
     * @return Sellercontactinfo The Sellercontactinfo entity
     */
    public Sellercontactinfo loadFullFirstLevelSellercontactinfo(java.lang.Integer contactinfoid);
    
    /**
     * Loads the Sellercontactinfo entity which is related to an instance of
     * Sellercontactinfo
     * @param Sellercontactinfo sellercontactinfo
     * @return Sellercontactinfo The Sellercontactinfo entity
     */
    public Sellercontactinfo loadFullFirstLevelSellercontactinfo(Sellercontactinfo sellercontactinfo);    
    
    
    /**
     * Loads the Sellercontactinfo entity which is related to an instance of
     * Sellercontactinfo and its dependent objects one to many
     * @param Long id
     * @return Sellercontactinfo The Sellercontactinfo entity
     */
    public Sellercontactinfo loadFullSellercontactinfo(Long id) ;

    /**
     * Searches a list of Sellercontactinfo entity based on a Sellercontactinfo containing Sellercontactinfo matching criteria on the positive mask
     * @param Sellercontactinfo sellercontactinfo
     * @return List<Sellercontactinfo>
     */
    public List<Sellercontactinfo> searchPrototypeSellercontactinfo(Sellercontactinfo sellercontactinfo) ;
    

    /**
     * Searches a list of Sellercontactinfo entity based on a Sellercontactinfo containing Sellercontactinfo matching criteria on any field
     * @param Sellercontactinfo sellercontactinfo
     * @return List<Sellercontactinfo>
     */
    public List<Sellercontactinfo> searchPrototypeAnySellercontactinfo(Sellercontactinfo sellercontactinfo) ;

    public List<Sellercontactinfo> find (Sellercontactinfo sellercontactinfo, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) ;

    /**
     * Searches a list of Sellercontactinfo entity based on a list of Sellercontactinfo containing Sellercontactinfo matching criteria
     * @param List<Sellercontactinfo> sellercontactinfos
     * @return List<Sellercontactinfo>
     */
    public List<Sellercontactinfo> searchPrototypeSellercontactinfo(List<Sellercontactinfo> sellercontactinfos) ;    
    	
	/**
     * Searches a list of Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     * @return List
     */
    public List<Sellercontactinfo> searchPrototypeSellercontactinfo(Sellercontactinfo sellercontactinfoPositive, Sellercontactinfo sellercontactinfoNegative) ;

}
