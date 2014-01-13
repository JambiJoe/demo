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
	* - name      : BslaJPADaoImplUML
	* - file name : BslaJPA2DaoImpl.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:20 CET
*/
package net.sf.mp.demo.petshop.dao.impl.jpa.pet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Hashtable;
import java.sql.Timestamp;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.lang.StringUtils;

import static net.sf.minuteProject.model.utils.BuilderUtils.*;
import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;
import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;
import net.sf.minuteProject.architecture.bsla.bean.criteria.PaginationCriteria;
import net.sf.minuteProject.model.service.GenericService;
import net.sf.mp.demo.petshop.dao.face.pet.SellercontactinfoDao;
import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * <p>Title: SellercontactinfoJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with SellercontactinfoJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching SellercontactinfoJPAImpl objects</p>
 *
 */


@org.springframework.stereotype.Repository(value="sellercontactinfoDao")
public class SellercontactinfoJPAImpl implements SellercontactinfoDao {
	public SellercontactinfoJPAImpl () {}
	
    @PersistenceContext
    EntityManager entityManager;
	
    /**
     * Inserts a Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     */
    public void insertSellercontactinfo(Sellercontactinfo sellercontactinfo) {
       entityManager.persist(sellercontactinfo);
    }

    protected void insertSellercontactinfo(EntityManager emForRecursiveDao, Sellercontactinfo sellercontactinfo) {
       emForRecursiveDao.persist(sellercontactinfo);
    } 
    /**
     * Inserts a list of Sellercontactinfo entity 
     * @param List<Sellercontactinfo> sellercontactinfos
     */
    public void insertSellercontactinfos(List<Sellercontactinfo> sellercontactinfos) {
    	//TODO
    }
    /**
     * Updates a Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     */
    public Sellercontactinfo updateSellercontactinfo(Sellercontactinfo sellercontactinfo) {
       return entityManager.merge(sellercontactinfo);
    }

	/**
     * Updates a Sellercontactinfo entity with only the attributes set into Sellercontactinfo.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param Sellercontactinfo sellercontactinfo
    */ 
    @Transactional
    public int updateNotNullOnlySellercontactinfo(Sellercontactinfo sellercontactinfo) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlySellercontactinfoQueryChunk(sellercontactinfo));
        if (sellercontactinfo.getContactinfoid() != null) {
           jpaQuery.setParameter ("contactinfoid", sellercontactinfo.getContactinfoid());
        }   
        if (sellercontactinfo.getLastname() != null) {
           jpaQuery.setParameter ("lastname", sellercontactinfo.getLastname());
        }   
        if (sellercontactinfo.getFirstname() != null) {
           jpaQuery.setParameter ("firstname", sellercontactinfo.getFirstname());
        }   
        if (sellercontactinfo.getEmail() != null) {
           jpaQuery.setParameter ("email", sellercontactinfo.getEmail());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlySellercontactinfoQueryChunkPrototype (Sellercontactinfo sellercontactinfo) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Sellercontactinfo sellercontactinfo ");
        if (sellercontactinfo.getLastname() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sellercontactinfo.lastname = :lastname");
        }
        if (sellercontactinfo.getFirstname() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sellercontactinfo.firstname = :firstname");
        }
        if (sellercontactinfo.getEmail() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sellercontactinfo.email = :email");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlySellercontactinfoQueryChunk (Sellercontactinfo sellercontactinfo) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlySellercontactinfoQueryChunkPrototype(sellercontactinfo));
        if (sellercontactinfo.getContactinfoid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" sellercontactinfo.contactinfoid = :contactinfoid");
        }
        return query.toString();
    }
    
                
	protected Sellercontactinfo assignBlankToNull (Sellercontactinfo sellercontactinfo) {
        if (sellercontactinfo==null)
			return null;
        if (sellercontactinfo.getLastname()!=null && sellercontactinfo.getLastname().equals(""))
           sellercontactinfo.setLastname((String)null);
        if (sellercontactinfo.getFirstname()!=null && sellercontactinfo.getFirstname().equals(""))
           sellercontactinfo.setFirstname((String)null);
        if (sellercontactinfo.getEmail()!=null && sellercontactinfo.getEmail().equals(""))
           sellercontactinfo.setEmail((String)null);
		return sellercontactinfo;
	}
	
	protected boolean isAllNull (Sellercontactinfo sellercontactinfo) {
	    if (sellercontactinfo==null)
			return true;
        if (sellercontactinfo.getContactinfoid()!=null) 
            return false;
        if (sellercontactinfo.getLastname()!=null) 
            return false;
        if (sellercontactinfo.getFirstname()!=null) 
            return false;
        if (sellercontactinfo.getEmail()!=null) 
            return false;
		return true;
	}
		
    @Transactional
    public int updateNotNullOnlyPrototypeSellercontactinfo(Sellercontactinfo sellercontactinfo, Sellercontactinfo prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Sellercontactinfo sellercontactinfo ");
        if (sellercontactinfo.getContactinfoid() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sellercontactinfo.contactinfoid = "+ sellercontactinfo.getContactinfoid() + " ");
        }
        if (sellercontactinfo.getLastname() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sellercontactinfo.lastname = '"+ sellercontactinfo.getLastname()+"' ");
        }
        if (sellercontactinfo.getFirstname() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sellercontactinfo.firstname = '"+ sellercontactinfo.getFirstname()+"' ");
        }
        if (sellercontactinfo.getEmail() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" sellercontactinfo.email = '"+ sellercontactinfo.getEmail()+"' ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getContactinfoid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sellercontactinfo.contactinfoid = "+ prototypeCriteria.getContactinfoid() + " ");
        }
        if (prototypeCriteria.getLastname() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sellercontactinfo.lastname = '"+ prototypeCriteria.getLastname()+"' ");
        }
        if (prototypeCriteria.getFirstname() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sellercontactinfo.firstname = '"+ prototypeCriteria.getFirstname()+"' ");
        }
        if (prototypeCriteria.getEmail() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sellercontactinfo.email = '"+ prototypeCriteria.getEmail()+"' ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     */
    public void saveSellercontactinfo(Sellercontactinfo sellercontactinfo) {
       //entityManager.persist(sellercontactinfo);
       if (entityManager.contains(sellercontactinfo)) {
          entityManager.merge(sellercontactinfo);
       } else {
          entityManager.persist(sellercontactinfo);
       }
       entityManager.flush(); 
    }
       
    /**
     * Deletes a Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     */
    public void deleteSellercontactinfo(Sellercontactinfo sellercontactinfo) {
      entityManager.remove(sellercontactinfo);
    }
    
    /**
     * Loads the Sellercontactinfo entity which is related to an instance of
     * Sellercontactinfo
     * @param Long id
     * @return Sellercontactinfo The Sellercontactinfo entity
     
    public Sellercontactinfo loadSellercontactinfo(Long id) {
    	return (Sellercontactinfo)entityManager.get(Sellercontactinfo.class, id);
    }
*/
  
    /**
     * Loads the Sellercontactinfo entity which is related to an instance of
     * Sellercontactinfo
     * @param java.lang.Integer Contactinfoid
     * @return Sellercontactinfo The Sellercontactinfo entity
     */
    public Sellercontactinfo loadSellercontactinfo(java.lang.Integer contactinfoid) {
    	return (Sellercontactinfo)entityManager.find(Sellercontactinfo.class, contactinfoid);
    }
    
    /**
     * Loads a list of Sellercontactinfo entity 
     * @param List<java.lang.Integer> contactinfoids
     * @return List<Sellercontactinfo> The Sellercontactinfo entity
     */
    public List<Sellercontactinfo> loadSellercontactinfoListBySellercontactinfo (List<Sellercontactinfo> sellercontactinfos) {
       return null;
    }
    
    /**
     * Loads a list of Sellercontactinfo entity 
     * @param List<java.lang.Integer> contactinfoids
     * @return List<Sellercontactinfo> The Sellercontactinfo entity
     */
    public List<Sellercontactinfo> loadSellercontactinfoListByContactinfoid(List<java.lang.Integer> contactinfoids){
       return null;
    }
    
    /**
     * Loads the Sellercontactinfo entity which is related to an instance of
     * Sellercontactinfo and its dependent one to many objects
     * @param Long id
     * @return Sellercontactinfo The Sellercontactinfo entity
     */
    public Sellercontactinfo loadFullFirstLevelSellercontactinfo(java.lang.Integer contactinfoid) {
        List list = getResultList(
                     "SELECT sellercontactinfo FROM Sellercontactinfo sellercontactinfo "
                     + " LEFT JOIN sellercontactinfo.myGoodItemContactinfoContactinfoids "   
                     + " WHERE sellercontactinfo.contactinfoid = "+contactinfoid
               );
         if (list!=null && !list.isEmpty())
            return (Sellercontactinfo)list.get(0);
         return null;
    	//return null;//(Sellercontactinfo) entityManager.queryForObject("loadFullFirstLevelSellercontactinfo", id);
    }

    /**
     * Loads the Sellercontactinfo entity which is related to an instance of
     * Sellercontactinfo
     * @param Sellercontactinfo sellercontactinfo
     * @return Sellercontactinfo The Sellercontactinfo entity
     */
    public Sellercontactinfo loadFullFirstLevelSellercontactinfo(Sellercontactinfo sellercontactinfo) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT sellercontactinfo FROM Sellercontactinfo sellercontactinfo ");
        query.append (" LEFT JOIN sellercontactinfo.myGoodItemContactinfoContactinfoids ");
        if (sellercontactinfo.getContactinfoid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sellercontactinfo.contactinfoid = "+ sellercontactinfo.getContactinfoid() + " ");
         }
        List list = getResultList(query.toString());
        if (list!=null && !list.isEmpty())
           return (Sellercontactinfo)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the Sellercontactinfo entity which is related to an instance of
     * Sellercontactinfo and its dependent objects one to many
     * @param Long id
     * @return Sellercontactinfo The Sellercontactinfo entity
     */
    public Sellercontactinfo loadFullSellercontactinfo(Long id) {
    	return null;//(Sellercontactinfo)entityManager.queryForObject("loadFullSellercontactinfo", id);
    }

    /**
     * Searches a list of Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     * @return List
     */  
    public List<Sellercontactinfo> searchPrototypeSellercontactinfo(Sellercontactinfo sellercontactinfo) {
       return searchPrototype (sellercontactinfo, null);
    }  
	
    public List<Sellercontactinfo> searchPrototypeAnySellercontactinfo(Sellercontactinfo sellercontactinfo) {
       return searchPrototypeAny (sellercontactinfo, null);
    }  

	// indirection
    public List<Sellercontactinfo> find (Sellercontactinfo criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
       return find (criteriaMask, matchType, operandType, caseSensitivenessType, null, null); 
	}
	
	// indirection
	protected List<Sellercontactinfo> find (Sellercontactinfo criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, Integer startPosition, Integer maxResults) {
       return find (criteriaMask, null, matchType, operandType, caseSensitivenessType, null, startPosition, maxResults); 
    }
	
	// indirection
	protected List<Sellercontactinfo> find (Sellercontactinfo criteriaMask, Sellercontactinfo orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Integer startPosition, Integer maxResults) {
       return find (null, criteriaMask, orderMask, matchType, operandType, caseSensitivenessType, sortOrder, startPosition, maxResults);
    }
	
	// main find implementation
	protected List<Sellercontactinfo> find (Sellercontactinfo whatMask, Sellercontactinfo criteriaMask, Sellercontactinfo orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Integer startPosition, Integer maxResults) {
       Query hquery = null;
	   Map beanPath = new Hashtable();
	   if (isAllNull(whatMask))
		  hquery = getEntityManager().createQuery(findQuery (criteriaMask, orderMask, matchType, operandType, caseSensitivenessType, sortOrder));
	   else
		  hquery = getEntityManager().createQuery(findPartialQuery (whatMask, criteriaMask, orderMask, matchType, operandType, caseSensitivenessType, sortOrder, beanPath));

       if (startPosition!=null)
          hquery.setFirstResult(startPosition);
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
	   List result = hquery.getResultList();
	   if (isAllNull(whatMask))
	      return result; 
	   else
	      return handlePartialLoadWithParentResult (whatMask, result, beanPath);
    }

	/**
	 *   find * on entity
	 *
	 */
    public String findQuery (Sellercontactinfo criteriaMask, Sellercontactinfo orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
        String what = "SELECT sellercontactinfo FROM Sellercontactinfo sellercontactinfo ";
		return findQuery (criteriaMask, orderMask, what, matchType, operandType, caseSensitivenessType, sortOrder);
    }

	/**
	 *   find partial on entity
	 *
	 */
    public String findPartialQuery (Sellercontactinfo whatMask, Sellercontactinfo criteriaMask, Sellercontactinfo orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Map beanPath) {
        return "to override ";
    }
	
	public List<Sellercontactinfo> handlePartialLoadWithParentResult(Sellercontactinfo what, List list, Map beanPath) {
		return null; //TO Override
	}
	
    protected String findQuery (Sellercontactinfo criteriaMask, Sellercontactinfo orderMask, String what, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
        String queryWhere = findWhere (criteriaMask, false, isAll(matchType), operandType, caseSensitivenessType);
		String queryOrder = findOrder (orderMask, sortOrder);
	    return getHQuery(what, queryWhere, queryOrder);
    }
	
    protected List<Sellercontactinfo> searchPrototype (Sellercontactinfo sellercontactinfo, Sellercontactinfo orderMask, QuerySortOrder sortOrder, Integer maxResults) {
       return searchPrototype(getSellercontactinfoSelectQuery (getWhereEqualWhereQueryChunk(sellercontactinfo), orderMask, sortOrder), maxResults);
    }

    protected List<Sellercontactinfo> searchPrototype (Sellercontactinfo sellercontactinfo, Integer maxResults) {
       return searchPrototype(sellercontactinfo, null, null, maxResults);
    }
    
    protected List<Sellercontactinfo> searchPrototypeAny (Sellercontactinfo sellercontactinfo, Integer maxResults) { 
       return searchPrototype(getSearchEqualAnyQuery (sellercontactinfo), maxResults);
    }
    
    protected List<Sellercontactinfo> searchPrototype (String query, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(query);
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();
    }

    public List<Sellercontactinfo> searchPrototypeSellercontactinfo (List<Sellercontactinfo> sellercontactinfos) {
       return searchPrototype (sellercontactinfos, null);
    }

    protected List<Sellercontactinfo> searchPrototype (List<Sellercontactinfo> sellercontactinfos, Integer maxResults) {    
	   return getResultList(getSellercontactinfoSearchEqualQuery (null, sellercontactinfos));
	}    

    protected List<Sellercontactinfo> getResultList (String query) {    
	   Query hquery = entityManager.createQuery(query);            
	   return hquery.getResultList();
	}    

    public List<Sellercontactinfo> searchDistinctPrototypeSellercontactinfo (Sellercontactinfo sellercontactinfoMask, List<Sellercontactinfo> sellercontactinfos) {
        return getResultList(getSellercontactinfoSearchEqualQuery (sellercontactinfoMask, sellercontactinfos));    
    }
         
	/**
     * Searches a list of Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfoPositive
     * @param Sellercontactinfo sellercontactinfoNegative
     * @return List
     */
    public List<Sellercontactinfo> searchPrototypeSellercontactinfo(Sellercontactinfo sellercontactinfoPositive, Sellercontactinfo sellercontactinfoNegative) {
	    return getResultList(getSellercontactinfoSearchEqualQuery (sellercontactinfoPositive, sellercontactinfoNegative));  
    }

    /**
    * return a string query search on a Sellercontactinfo prototype
    */
    protected String getSellercontactinfoSelectQuery (String where, Sellercontactinfo orderMask, QuerySortOrder sortOrder) {
       return getSellercontactinfoSelectQuery (where, findOrder (orderMask, sortOrder));
    }
    protected String getSellercontactinfoSelectQuery (String where, String order) {
       StringBuffer query = new StringBuffer();
       StringBuffer queryWhere = new StringBuffer();
       query.append ("SELECT sellercontactinfo FROM Sellercontactinfo sellercontactinfo ");
       return getHQuery(query.toString(), where, order);
    }
    /**
    * return a jql query search on a Sellercontactinfo prototype
    */
    protected String getSearchEqualQuery (Sellercontactinfo sellercontactinfo) {
       return getSellercontactinfoSelectQuery (getWhereEqualWhereQueryChunk(sellercontactinfo),null);
    }
    protected String getWhereEqualWhereQueryChunk (Sellercontactinfo sellercontactinfo) {
       return getWhereEqualWhereQueryChunk(sellercontactinfo, false);
    }
    /**
    * return a jql query search on a Sellercontactinfo with any prototype
    */
    protected String getSearchEqualAnyQuery (Sellercontactinfo sellercontactinfo) {
       return getSellercontactinfoSelectQuery (getWhereEqualAnyWhereQueryChunk(sellercontactinfo), null);   
    }
    protected String getWhereEqualAnyWhereQueryChunk (Sellercontactinfo sellercontactinfo) {
       return getWhereEqualAnyWhereQueryChunk(sellercontactinfo, false);   
    }

    /**
    * return a jql search for a list of Sellercontactinfo prototype
    */
    protected String getSellercontactinfoSearchEqualQuery (Sellercontactinfo sellercontactinfoMask, List<Sellercontactinfo> sellercontactinfos) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (sellercontactinfoMask !=null)
           query.append (getSellercontactinfoMaskWhat (sellercontactinfoMask));
        query.append (" FROM Sellercontactinfo sellercontactinfo ");
        StringBuffer queryWhere = new StringBuffer();
        for (Sellercontactinfo sellercontactinfo : sellercontactinfos) {
           if (!isAllNull(sellercontactinfo)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;
	           queryWhere.append (" ("+getWhereEqualWhereQueryChunk(sellercontactinfo, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    /**
    * return a jql search for a list of Sellercontactinfo prototype
    */
    protected String getSearchEqualAnyQuery (Sellercontactinfo sellercontactinfoMask, List<Sellercontactinfo> sellercontactinfos) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (sellercontactinfoMask !=null)
           query.append (getSellercontactinfoMaskWhat (sellercontactinfoMask));
        query.append (" FROM Sellercontactinfo sellercontactinfo ");
        StringBuffer queryWhere = new StringBuffer();
        for (Sellercontactinfo sellercontactinfo : sellercontactinfos) {
           if (!isAllNull(sellercontactinfo)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getWhereEqualAnyWhereQueryChunk(sellercontactinfo, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getSellercontactinfoMaskWhat (Sellercontactinfo sellercontactinfoMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (sellercontactinfoMask.getContactinfoid() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" contactinfoid ");
        }
        if (sellercontactinfoMask.getLastname() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" lastname ");
        }
        if (sellercontactinfoMask.getFirstname() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" firstname ");
        }
        if (sellercontactinfoMask.getEmail() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" email ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();
    }
    
    protected String getWhereEqualAnyWhereQueryChunk (Sellercontactinfo sellercontactinfo, boolean isAndSet) {
		return getSearchEqualWhereQueryChunk (sellercontactinfo, isAndSet, false);	
	}
	
    protected String getWhereEqualWhereQueryChunk (Sellercontactinfo sellercontactinfo, boolean isAndSet) {
		return getSearchEqualWhereQueryChunk (sellercontactinfo, isAndSet, true);
	}
	
    protected String getSearchEqualWhereQueryChunk (Sellercontactinfo sellercontactinfo, boolean isAndSet, boolean isAll) {
        StringBuffer query = new StringBuffer();
        if (sellercontactinfo.getContactinfoid() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" sellercontactinfo.contactinfoid = "+ sellercontactinfo.getContactinfoid() + " ");
        }
        if (sellercontactinfo.getLastname() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" sellercontactinfo.lastname = '"+ sellercontactinfo.getLastname()+"' ");
        }
        if (sellercontactinfo.getFirstname() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" sellercontactinfo.firstname = '"+ sellercontactinfo.getFirstname()+"' ");
        }
        if (sellercontactinfo.getEmail() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" sellercontactinfo.email = '"+ sellercontactinfo.getEmail()+"' ");
        }
	    return query.toString();
    }

    protected String findOrder (Sellercontactinfo orderMask, QuerySortOrder sortOrder) {
        if (orderMask!=null) {
            String orderColumn = getFirstNotNullColumnOtherWiseNull(orderMask);
            if (orderColumn!=null)
               return orderColumn + " " + sortOrder;
        }
        return "";
    }

	// indirection
    protected String findWhere (Sellercontactinfo sellercontactinfo, boolean isAndSet, boolean isAll, OperandType operandType, boolean caseSensitive) {
		return findWhere (null, sellercontactinfo, isAndSet, isAll, operandType, caseSensitive);
	}
	
	protected static String findWhere (String alias, Sellercontactinfo sellercontactinfo, boolean isAndSet, boolean isAll, OperandType operandType, boolean caseSensitive) {
        if (alias==null)
			alias = "sellercontactinfo";
		StringBuffer query = new StringBuffer();
		String operand = getOperand (operandType);
		String evaluatorPrefix = getEvaluatorPrefix (operandType);		
		String evaluatorSuffix = getEvaluatorSuffix (operandType);		
        if (sellercontactinfo.getContactinfoid() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".contactinfoid = "+ sellercontactinfo.getContactinfoid() + " ");
        }
        if (sellercontactinfo.getLastname() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = sellercontactinfo.getLastname();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".lastname) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".lastname "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (sellercontactinfo.getFirstname() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = sellercontactinfo.getFirstname();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".firstname) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".firstname "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (sellercontactinfo.getEmail() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = sellercontactinfo.getEmail();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".email) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".email "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        return query.toString();
    }
	
	protected String getFirstNotNullColumnOtherWiseNull (Sellercontactinfo mask) {
        if (mask == null) return null;
        if (mask.getContactinfoid() != null) return "contactinfoid";
        if (mask.getLastname() != null) return "lastname";
        if (mask.getFirstname() != null) return "firstname";
        if (mask.getEmail() != null) return "email";
        return null;	
	}
    
    /**
    * return a jql search on a Sellercontactinfo prototype with positive and negative beans
    */
    protected String getSellercontactinfoSearchEqualQuery (Sellercontactinfo sellercontactinfoPositive, Sellercontactinfo sellercontactinfoNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT sellercontactinfo FROM Sellercontactinfo sellercontactinfo ");
        if (sellercontactinfoPositive!=null && sellercontactinfoPositive.getContactinfoid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sellercontactinfo.contactinfoid = "+ sellercontactinfoPositive.getContactinfoid() + " ");
        } else if (sellercontactinfoNegative.getContactinfoid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" sellercontactinfo.contactinfoid is null ");
        }
        if (sellercontactinfoPositive!=null && sellercontactinfoPositive.getLastname() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sellercontactinfo.lastname = '"+ sellercontactinfoPositive.getLastname()+"' ");
        } else if (sellercontactinfoNegative.getLastname() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" sellercontactinfo.lastname is null ");
        }
        if (sellercontactinfoPositive!=null && sellercontactinfoPositive.getFirstname() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sellercontactinfo.firstname = '"+ sellercontactinfoPositive.getFirstname()+"' ");
        } else if (sellercontactinfoNegative.getFirstname() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" sellercontactinfo.firstname is null ");
        }
        if (sellercontactinfoPositive!=null && sellercontactinfoPositive.getEmail() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" sellercontactinfo.email = '"+ sellercontactinfoPositive.getEmail()+"' ");
        } else if (sellercontactinfoNegative.getEmail() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" sellercontactinfo.email is null ");
        }
	    return query.toString();
    }
 
   
    protected EntityManager getEntityManager () {
        return entityManager;    
    }


}
