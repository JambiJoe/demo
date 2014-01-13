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
import net.sf.mp.demo.petshop.dao.face.pet.ZiplocationDao;
import net.sf.mp.demo.petshop.domain.pet.Ziplocation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * <p>Title: ZiplocationJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with ZiplocationJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching ZiplocationJPAImpl objects</p>
 *
 */


@org.springframework.stereotype.Repository(value="ziplocationDao")
public class ZiplocationJPAImpl implements ZiplocationDao {
	public ZiplocationJPAImpl () {}
	
    @PersistenceContext
    EntityManager entityManager;
	
    /**
     * Inserts a Ziplocation entity 
     * @param Ziplocation ziplocation
     */
    public void insertZiplocation(Ziplocation ziplocation) {
       entityManager.persist(ziplocation);
    }

    protected void insertZiplocation(EntityManager emForRecursiveDao, Ziplocation ziplocation) {
       emForRecursiveDao.persist(ziplocation);
    } 
    /**
     * Inserts a list of Ziplocation entity 
     * @param List<Ziplocation> ziplocations
     */
    public void insertZiplocations(List<Ziplocation> ziplocations) {
    	//TODO
    }
    /**
     * Updates a Ziplocation entity 
     * @param Ziplocation ziplocation
     */
    public Ziplocation updateZiplocation(Ziplocation ziplocation) {
       return entityManager.merge(ziplocation);
    }

	/**
     * Updates a Ziplocation entity with only the attributes set into Ziplocation.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param Ziplocation ziplocation
    */ 
    @Transactional
    public int updateNotNullOnlyZiplocation(Ziplocation ziplocation) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyZiplocationQueryChunk(ziplocation));
        if (ziplocation.getZipcode() != null) {
           jpaQuery.setParameter ("zipcode", ziplocation.getZipcode());
        }   
        if (ziplocation.getCity() != null) {
           jpaQuery.setParameter ("city", ziplocation.getCity());
        }   
        if (ziplocation.getState() != null) {
           jpaQuery.setParameter ("state", ziplocation.getState());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlyZiplocationQueryChunkPrototype (Ziplocation ziplocation) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Ziplocation ziplocation ");
        if (ziplocation.getCity() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" ziplocation.city = :city");
        }
        if (ziplocation.getState() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" ziplocation.state = :state");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyZiplocationQueryChunk (Ziplocation ziplocation) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyZiplocationQueryChunkPrototype(ziplocation));
        if (ziplocation.getZipcode() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" ziplocation.zipcode = :zipcode");
        }
        return query.toString();
    }
    
                
	protected Ziplocation assignBlankToNull (Ziplocation ziplocation) {
        if (ziplocation==null)
			return null;
        if (ziplocation.getCity()!=null && ziplocation.getCity().equals(""))
           ziplocation.setCity((String)null);
        if (ziplocation.getState()!=null && ziplocation.getState().equals(""))
           ziplocation.setState((String)null);
		return ziplocation;
	}
	
	protected boolean isAllNull (Ziplocation ziplocation) {
	    if (ziplocation==null)
			return true;
        if (ziplocation.getZipcode()!=null) 
            return false;
        if (ziplocation.getCity()!=null) 
            return false;
        if (ziplocation.getState()!=null) 
            return false;
		return true;
	}
		
    @Transactional
    public int updateNotNullOnlyPrototypeZiplocation(Ziplocation ziplocation, Ziplocation prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Ziplocation ziplocation ");
        if (ziplocation.getZipcode() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" ziplocation.zipcode = "+ ziplocation.getZipcode() + " ");
        }
        if (ziplocation.getCity() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" ziplocation.city = '"+ ziplocation.getCity()+"' ");
        }
        if (ziplocation.getState() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" ziplocation.state = '"+ ziplocation.getState()+"' ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getZipcode() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" ziplocation.zipcode = "+ prototypeCriteria.getZipcode() + " ");
        }
        if (prototypeCriteria.getCity() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" ziplocation.city = '"+ prototypeCriteria.getCity()+"' ");
        }
        if (prototypeCriteria.getState() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" ziplocation.state = '"+ prototypeCriteria.getState()+"' ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a Ziplocation entity 
     * @param Ziplocation ziplocation
     */
    public void saveZiplocation(Ziplocation ziplocation) {
       //entityManager.persist(ziplocation);
       if (entityManager.contains(ziplocation)) {
          entityManager.merge(ziplocation);
       } else {
          entityManager.persist(ziplocation);
       }
       entityManager.flush(); 
    }
       
    /**
     * Deletes a Ziplocation entity 
     * @param Ziplocation ziplocation
     */
    public void deleteZiplocation(Ziplocation ziplocation) {
      entityManager.remove(ziplocation);
    }
    
    /**
     * Loads the Ziplocation entity which is related to an instance of
     * Ziplocation
     * @param Long id
     * @return Ziplocation The Ziplocation entity
     
    public Ziplocation loadZiplocation(Long id) {
    	return (Ziplocation)entityManager.get(Ziplocation.class, id);
    }
*/
  
    /**
     * Loads the Ziplocation entity which is related to an instance of
     * Ziplocation
     * @param java.lang.Integer Zipcode
     * @return Ziplocation The Ziplocation entity
     */
    public Ziplocation loadZiplocation(java.lang.Integer zipcode) {
    	return (Ziplocation)entityManager.find(Ziplocation.class, zipcode);
    }
    
    /**
     * Loads a list of Ziplocation entity 
     * @param List<java.lang.Integer> zipcodes
     * @return List<Ziplocation> The Ziplocation entity
     */
    public List<Ziplocation> loadZiplocationListByZiplocation (List<Ziplocation> ziplocations) {
       return null;
    }
    
    /**
     * Loads a list of Ziplocation entity 
     * @param List<java.lang.Integer> zipcodes
     * @return List<Ziplocation> The Ziplocation entity
     */
    public List<Ziplocation> loadZiplocationListByZipcode(List<java.lang.Integer> zipcodes){
       return null;
    }
    
    /**
     * Loads the Ziplocation entity which is related to an instance of
     * Ziplocation and its dependent one to many objects
     * @param Long id
     * @return Ziplocation The Ziplocation entity
     */
    public Ziplocation loadFullFirstLevelZiplocation(java.lang.Integer zipcode) {
        List list = getResultList(
                     "SELECT ziplocation FROM Ziplocation ziplocation "
                     + " WHERE ziplocation.zipcode = "+zipcode
               );
         if (list!=null && !list.isEmpty())
            return (Ziplocation)list.get(0);
         return null;
    	//return null;//(Ziplocation) entityManager.queryForObject("loadFullFirstLevelZiplocation", id);
    }

    /**
     * Loads the Ziplocation entity which is related to an instance of
     * Ziplocation
     * @param Ziplocation ziplocation
     * @return Ziplocation The Ziplocation entity
     */
    public Ziplocation loadFullFirstLevelZiplocation(Ziplocation ziplocation) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT ziplocation FROM Ziplocation ziplocation ");
        if (ziplocation.getZipcode() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" ziplocation.zipcode = "+ ziplocation.getZipcode() + " ");
         }
        List list = getResultList(query.toString());
        if (list!=null && !list.isEmpty())
           return (Ziplocation)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the Ziplocation entity which is related to an instance of
     * Ziplocation and its dependent objects one to many
     * @param Long id
     * @return Ziplocation The Ziplocation entity
     */
    public Ziplocation loadFullZiplocation(Long id) {
    	return null;//(Ziplocation)entityManager.queryForObject("loadFullZiplocation", id);
    }

    /**
     * Searches a list of Ziplocation entity 
     * @param Ziplocation ziplocation
     * @return List
     */  
    public List<Ziplocation> searchPrototypeZiplocation(Ziplocation ziplocation) {
       return searchPrototype (ziplocation, null);
    }  
	
    public List<Ziplocation> searchPrototypeAnyZiplocation(Ziplocation ziplocation) {
       return searchPrototypeAny (ziplocation, null);
    }  

	// indirection
    public List<Ziplocation> find (Ziplocation criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
       return find (criteriaMask, matchType, operandType, caseSensitivenessType, null, null); 
	}
	
	// indirection
	protected List<Ziplocation> find (Ziplocation criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, Integer startPosition, Integer maxResults) {
       return find (criteriaMask, null, matchType, operandType, caseSensitivenessType, null, startPosition, maxResults); 
    }
	
	// indirection
	protected List<Ziplocation> find (Ziplocation criteriaMask, Ziplocation orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Integer startPosition, Integer maxResults) {
       return find (null, criteriaMask, orderMask, matchType, operandType, caseSensitivenessType, sortOrder, startPosition, maxResults);
    }
	
	// main find implementation
	protected List<Ziplocation> find (Ziplocation whatMask, Ziplocation criteriaMask, Ziplocation orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Integer startPosition, Integer maxResults) {
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
    public String findQuery (Ziplocation criteriaMask, Ziplocation orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
        String what = "SELECT ziplocation FROM Ziplocation ziplocation ";
		return findQuery (criteriaMask, orderMask, what, matchType, operandType, caseSensitivenessType, sortOrder);
    }

	/**
	 *   find partial on entity
	 *
	 */
    public String findPartialQuery (Ziplocation whatMask, Ziplocation criteriaMask, Ziplocation orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Map beanPath) {
        return "to override ";
    }
	
	public List<Ziplocation> handlePartialLoadWithParentResult(Ziplocation what, List list, Map beanPath) {
		return null; //TO Override
	}
	
    protected String findQuery (Ziplocation criteriaMask, Ziplocation orderMask, String what, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
        String queryWhere = findWhere (criteriaMask, false, isAll(matchType), operandType, caseSensitivenessType);
		String queryOrder = findOrder (orderMask, sortOrder);
	    return getHQuery(what, queryWhere, queryOrder);
    }
	
    protected List<Ziplocation> searchPrototype (Ziplocation ziplocation, Ziplocation orderMask, QuerySortOrder sortOrder, Integer maxResults) {
       return searchPrototype(getZiplocationSelectQuery (getWhereEqualWhereQueryChunk(ziplocation), orderMask, sortOrder), maxResults);
    }

    protected List<Ziplocation> searchPrototype (Ziplocation ziplocation, Integer maxResults) {
       return searchPrototype(ziplocation, null, null, maxResults);
    }
    
    protected List<Ziplocation> searchPrototypeAny (Ziplocation ziplocation, Integer maxResults) { 
       return searchPrototype(getSearchEqualAnyQuery (ziplocation), maxResults);
    }
    
    protected List<Ziplocation> searchPrototype (String query, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(query);
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();
    }

    public List<Ziplocation> searchPrototypeZiplocation (List<Ziplocation> ziplocations) {
       return searchPrototype (ziplocations, null);
    }

    protected List<Ziplocation> searchPrototype (List<Ziplocation> ziplocations, Integer maxResults) {    
	   return getResultList(getZiplocationSearchEqualQuery (null, ziplocations));
	}    

    protected List<Ziplocation> getResultList (String query) {    
	   Query hquery = entityManager.createQuery(query);            
	   return hquery.getResultList();
	}    

    public List<Ziplocation> searchDistinctPrototypeZiplocation (Ziplocation ziplocationMask, List<Ziplocation> ziplocations) {
        return getResultList(getZiplocationSearchEqualQuery (ziplocationMask, ziplocations));    
    }
         
	/**
     * Searches a list of Ziplocation entity 
     * @param Ziplocation ziplocationPositive
     * @param Ziplocation ziplocationNegative
     * @return List
     */
    public List<Ziplocation> searchPrototypeZiplocation(Ziplocation ziplocationPositive, Ziplocation ziplocationNegative) {
	    return getResultList(getZiplocationSearchEqualQuery (ziplocationPositive, ziplocationNegative));  
    }

    /**
    * return a string query search on a Ziplocation prototype
    */
    protected String getZiplocationSelectQuery (String where, Ziplocation orderMask, QuerySortOrder sortOrder) {
       return getZiplocationSelectQuery (where, findOrder (orderMask, sortOrder));
    }
    protected String getZiplocationSelectQuery (String where, String order) {
       StringBuffer query = new StringBuffer();
       StringBuffer queryWhere = new StringBuffer();
       query.append ("SELECT ziplocation FROM Ziplocation ziplocation ");
       return getHQuery(query.toString(), where, order);
    }
    /**
    * return a jql query search on a Ziplocation prototype
    */
    protected String getSearchEqualQuery (Ziplocation ziplocation) {
       return getZiplocationSelectQuery (getWhereEqualWhereQueryChunk(ziplocation),null);
    }
    protected String getWhereEqualWhereQueryChunk (Ziplocation ziplocation) {
       return getWhereEqualWhereQueryChunk(ziplocation, false);
    }
    /**
    * return a jql query search on a Ziplocation with any prototype
    */
    protected String getSearchEqualAnyQuery (Ziplocation ziplocation) {
       return getZiplocationSelectQuery (getWhereEqualAnyWhereQueryChunk(ziplocation), null);   
    }
    protected String getWhereEqualAnyWhereQueryChunk (Ziplocation ziplocation) {
       return getWhereEqualAnyWhereQueryChunk(ziplocation, false);   
    }

    /**
    * return a jql search for a list of Ziplocation prototype
    */
    protected String getZiplocationSearchEqualQuery (Ziplocation ziplocationMask, List<Ziplocation> ziplocations) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (ziplocationMask !=null)
           query.append (getZiplocationMaskWhat (ziplocationMask));
        query.append (" FROM Ziplocation ziplocation ");
        StringBuffer queryWhere = new StringBuffer();
        for (Ziplocation ziplocation : ziplocations) {
           if (!isAllNull(ziplocation)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;
	           queryWhere.append (" ("+getWhereEqualWhereQueryChunk(ziplocation, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    /**
    * return a jql search for a list of Ziplocation prototype
    */
    protected String getSearchEqualAnyQuery (Ziplocation ziplocationMask, List<Ziplocation> ziplocations) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (ziplocationMask !=null)
           query.append (getZiplocationMaskWhat (ziplocationMask));
        query.append (" FROM Ziplocation ziplocation ");
        StringBuffer queryWhere = new StringBuffer();
        for (Ziplocation ziplocation : ziplocations) {
           if (!isAllNull(ziplocation)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getWhereEqualAnyWhereQueryChunk(ziplocation, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getZiplocationMaskWhat (Ziplocation ziplocationMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (ziplocationMask.getZipcode() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" zipcode ");
        }
        if (ziplocationMask.getCity() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" city ");
        }
        if (ziplocationMask.getState() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" state ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();
    }
    
    protected String getWhereEqualAnyWhereQueryChunk (Ziplocation ziplocation, boolean isAndSet) {
		return getSearchEqualWhereQueryChunk (ziplocation, isAndSet, false);	
	}
	
    protected String getWhereEqualWhereQueryChunk (Ziplocation ziplocation, boolean isAndSet) {
		return getSearchEqualWhereQueryChunk (ziplocation, isAndSet, true);
	}
	
    protected String getSearchEqualWhereQueryChunk (Ziplocation ziplocation, boolean isAndSet, boolean isAll) {
        StringBuffer query = new StringBuffer();
        if (ziplocation.getZipcode() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" ziplocation.zipcode = "+ ziplocation.getZipcode() + " ");
        }
        if (ziplocation.getCity() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" ziplocation.city = '"+ ziplocation.getCity()+"' ");
        }
        if (ziplocation.getState() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" ziplocation.state = '"+ ziplocation.getState()+"' ");
        }
	    return query.toString();
    }

    protected String findOrder (Ziplocation orderMask, QuerySortOrder sortOrder) {
        if (orderMask!=null) {
            String orderColumn = getFirstNotNullColumnOtherWiseNull(orderMask);
            if (orderColumn!=null)
               return orderColumn + " " + sortOrder;
        }
        return "";
    }

	// indirection
    protected String findWhere (Ziplocation ziplocation, boolean isAndSet, boolean isAll, OperandType operandType, boolean caseSensitive) {
		return findWhere (null, ziplocation, isAndSet, isAll, operandType, caseSensitive);
	}
	
	protected static String findWhere (String alias, Ziplocation ziplocation, boolean isAndSet, boolean isAll, OperandType operandType, boolean caseSensitive) {
        if (alias==null)
			alias = "ziplocation";
		StringBuffer query = new StringBuffer();
		String operand = getOperand (operandType);
		String evaluatorPrefix = getEvaluatorPrefix (operandType);		
		String evaluatorSuffix = getEvaluatorSuffix (operandType);		
        if (ziplocation.getZipcode() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".zipcode = "+ ziplocation.getZipcode() + " ");
        }
        if (ziplocation.getCity() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = ziplocation.getCity();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".city) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".city "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (ziplocation.getState() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = ziplocation.getState();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".state) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".state "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        return query.toString();
    }
	
	protected String getFirstNotNullColumnOtherWiseNull (Ziplocation mask) {
        if (mask == null) return null;
        if (mask.getZipcode() != null) return "zipcode";
        if (mask.getCity() != null) return "city";
        if (mask.getState() != null) return "state";
        return null;	
	}
    
    /**
    * return a jql search on a Ziplocation prototype with positive and negative beans
    */
    protected String getZiplocationSearchEqualQuery (Ziplocation ziplocationPositive, Ziplocation ziplocationNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT ziplocation FROM Ziplocation ziplocation ");
        if (ziplocationPositive!=null && ziplocationPositive.getZipcode() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" ziplocation.zipcode = "+ ziplocationPositive.getZipcode() + " ");
        } else if (ziplocationNegative.getZipcode() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" ziplocation.zipcode is null ");
        }
        if (ziplocationPositive!=null && ziplocationPositive.getCity() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" ziplocation.city = '"+ ziplocationPositive.getCity()+"' ");
        } else if (ziplocationNegative.getCity() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" ziplocation.city is null ");
        }
        if (ziplocationPositive!=null && ziplocationPositive.getState() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" ziplocation.state = '"+ ziplocationPositive.getState()+"' ");
        } else if (ziplocationNegative.getState() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" ziplocation.state is null ");
        }
	    return query.toString();
    }
 
   
    protected EntityManager getEntityManager () {
        return entityManager;    
    }


}
