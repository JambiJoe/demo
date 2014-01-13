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
	* - name      : BslaJPAExtendedDaoImplUML
	* - file name : BslaJPA2KFDaoImpl.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/
package net.sf.mp.demo.petshop.dao.impl.jpa.pet;

import java.lang.reflect.InvocationTargetException;
import java.sql.Clob;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import net.sf.minuteProject.architecture.query.QueryWhatInit;
import net.sf.minuteProject.architecture.query.impl.QueryCountInit;
import net.sf.minuteProject.architecture.query.impl.QuerySelectCountInit;
import net.sf.minuteProject.architecture.query.impl.QuerySelectInit;
import net.sf.minuteProject.architecture.query.impl.QuerySelectDistinctInit;
import net.sf.minuteProject.architecture.cache.SimpleCache;
import net.sf.minuteProject.architecture.filter.data.ClauseCriterion;
import net.sf.minuteProject.architecture.filter.data.Criteria;
import net.sf.minuteProject.architecture.filter.data.Criterion;
import net.sf.minuteProject.architecture.filter.data.InCriterion;
import net.sf.minuteProject.architecture.filter.data.OrderCriteria;
import net.sf.minuteProject.model.service.GenericService;
import net.sf.minuteProject.model.data.criteria.EntityCriteria;
import net.sf.minuteProject.model.data.criteria.EntitySort;
import net.sf.minuteProject.model.data.criteria.QueryData;
import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;
import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;
import static net.sf.minuteProject.model.utils.BuilderUtils.*;

import net.sf.minuteProject.architecture.utils.BeanUtils;
import net.sf.mp.demo.petshop.dao.face.pet.SellercontactinfoExtDao;
import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;
import net.sf.mp.demo.petshop.dao.impl.jpa.pet.SellercontactinfoJPAImpl;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@


import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.mp.demo.petshop.dao.impl.jpa.product.MyGoodItemExtendedJPAImpl;
/**
 *
 * <p>Title: SellercontactinfoExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with SellercontactinfoExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching SellercontactinfoExtendedJPAImpl objects</p>
 *
 */
@org.springframework.stereotype.Repository(value="sellercontactinfoExtDao")
public class SellercontactinfoExtendedJPAImpl extends SellercontactinfoJPAImpl implements SellercontactinfoExtDao{
    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private MyGoodItemExtendedJPAImpl mygooditemextendedjpaimpl;
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public SellercontactinfoExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public SellercontactinfoExtendedJPAImpl (EntityManager emForRecursiveDao) {
       this.emForRecursiveDao = emForRecursiveDao;
    }
            
    /**
     * generic to move after in superclass
     */
    public List<Object[]> getSQLQueryResult(String query) {
		   Query queryJ = getEntityManager().createNativeQuery(query);
		   return queryJ.getResultList();
    }


    /**
     * Inserts a Sellercontactinfo entity with cascade of its children
     * @param Sellercontactinfo sellercontactinfo
     */
    public void insertSellercontactinfoWithCascade(Sellercontactinfo sellercontactinfo) {
    	SellercontactinfoExtendedJPAImpl sellercontactinfoextendedjpaimpl = new SellercontactinfoExtendedJPAImpl(getEntityManager());
    	sellercontactinfoextendedjpaimpl.insertSellercontactinfoWithCascade(sellercontactinfoextendedjpaimpl.getEntityManagerForRecursiveDao(), sellercontactinfo);
    }
     
    public void insertSellercontactinfoWithCascade(EntityManager emForRecursiveDao, Sellercontactinfo sellercontactinfo) {
       insertSellercontactinfo(emForRecursiveDao, sellercontactinfo);
       if (!sellercontactinfo.getMyGoodItems().isEmpty()) {
          MyGoodItemExtendedJPAImpl mygooditemextendedjpaimpl = new MyGoodItemExtendedJPAImpl (emForRecursiveDao);
          for (MyGoodItem _myGoodItems : sellercontactinfo.getMyGoodItems()) {
             mygooditemextendedjpaimpl.insertMyGoodItemWithCascade(emForRecursiveDao, _myGoodItems);
          }
       } 
    }
        
    /**
     * Inserts a list of Sellercontactinfo entity with cascade of its children
     * @param List<Sellercontactinfo> sellercontactinfos
     */
    public void insertSellercontactinfosWithCascade(List<Sellercontactinfo> sellercontactinfos) {
       for (Sellercontactinfo sellercontactinfo : sellercontactinfos) {
          insertSellercontactinfoWithCascade(sellercontactinfo);
       }
    } 
        
    /**
     * lookup Sellercontactinfo entity Sellercontactinfo, criteria and max result number
     */
    public List<Sellercontactinfo> lookupSellercontactinfo(Sellercontactinfo sellercontactinfo, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT sellercontactinfo FROM Sellercontactinfo sellercontactinfo ");
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());
        }
        OrderCriteria orderCriteria = criteria.getOrderCriteria();
        if (criteria.getOrderCriteria()!=null)
        	query.append(orderCriteria.getExpression());
        Query hquery = em.createQuery(query.toString());
        if (numberOfResult!=null)
            hquery.setMaxResults(numberOfResult);
        return hquery.getResultList();
    }
    
    public List<Sellercontactinfo> lookupSellercontactinfo(Sellercontactinfo sellercontactinfo, Criteria criteria, Integer numberOfResult) {
		return lookupSellercontactinfo(sellercontactinfo, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlySellercontactinfo (Sellercontactinfo sellercontactinfo, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlySellercontactinfoQueryChunkPrototype (sellercontactinfo);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
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
	
	public Sellercontactinfo affectSellercontactinfo (Sellercontactinfo sellercontactinfo) {
	    return referSellercontactinfo (sellercontactinfo, null, false);		    
	}
		
	/**
	 * Assign the first sellercontactinfo retrieved corresponding to the sellercontactinfo criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no sellercontactinfo corresponding in the database. Then sellercontactinfo is inserted and returned with its primary key(s). 
	 */
	public Sellercontactinfo assignSellercontactinfo (Sellercontactinfo sellercontactinfo) {
		return referSellercontactinfo (sellercontactinfo, null, true);
	}

	/**
	 * Assign the first sellercontactinfo retrieved corresponding to the mask criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no sellercontactinfo corresponding in the database. 
	 * Then sellercontactinfo is inserted and returned with its primary key(s). 
	 * Mask servers usually to set unique keys or the semantic reference
	 */
    public Sellercontactinfo assignSellercontactinfo (Sellercontactinfo sellercontactinfo, Sellercontactinfo mask) {
		return referSellercontactinfo (sellercontactinfo, mask, true);
	}

	public Sellercontactinfo referSellercontactinfo (Sellercontactinfo sellercontactinfo, Sellercontactinfo mask, boolean isAssign) {
		sellercontactinfo = assignBlankToNull (sellercontactinfo);
		if (isAllNull(sellercontactinfo))
			return null;
		else {
			List<Sellercontactinfo> list;
			if (mask==null)
				list = searchPrototypeSellercontactinfo(sellercontactinfo);
			else
				list = searchPrototypeSellercontactinfo(mask);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertSellercontactinfo(sellercontactinfo);
			    else
				   return null;
			}
			else if (list.size()==1)
				sellercontactinfo.copy(list.get(0));
			else 
				//TODO log error
				sellercontactinfo.copy(list.get(0));
		}
		return sellercontactinfo;		    
	}

   public Sellercontactinfo assignSellercontactinfoUseCache (Sellercontactinfo sellercontactinfo) {
      return referSellercontactinfoUseCache (sellercontactinfo, true);
   }
      		
   public Sellercontactinfo affectSellercontactinfoUseCache (Sellercontactinfo sellercontactinfo) {
      return referSellercontactinfoUseCache (sellercontactinfo, false);
   }
      		
   public Sellercontactinfo referSellercontactinfoUseCache (Sellercontactinfo sellercontactinfo, boolean isAssign) {
	  String key = getCacheKey(null, sellercontactinfo, null, "assignSellercontactinfo");
      Sellercontactinfo sellercontactinfoCache = (Sellercontactinfo)simpleCache.get(key);
      if (sellercontactinfoCache==null) {
         sellercontactinfoCache = referSellercontactinfo (sellercontactinfo, null, isAssign);
         if (key!=null)
         	simpleCache.put(key, sellercontactinfoCache);
      }
      sellercontactinfo.copy(sellercontactinfoCache);
      return sellercontactinfoCache;
   }	

	private String getCacheKey (Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (sellercontactinfoWhat!=null)
	       sb.append(sellercontactinfoWhat.toStringWithParents());
	    if (positiveSellercontactinfo!=null)
	       sb.append(positiveSellercontactinfo.toStringWithParents());
	    if (negativeSellercontactinfo!=null)
	       sb.append(negativeSellercontactinfo.toStringWithParents());
	    return sb.toString();
	}
	
    public Sellercontactinfo partialLoadWithParentFirstSellercontactinfo(Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo){
		List <Sellercontactinfo> list = partialLoadWithParentSellercontactinfo(sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo);
		return (!list.isEmpty())?(Sellercontactinfo)list.get(0):null;
    }
    
    public Sellercontactinfo partialLoadWithParentFirstSellercontactinfoUseCache(Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, Boolean useCache){
		List <Sellercontactinfo> list = partialLoadWithParentSellercontactinfoUseCache(sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo, useCache);
		return (!list.isEmpty())?(Sellercontactinfo)list.get(0):null;
    }
	
	public Sellercontactinfo partialLoadWithParentFirstSellercontactinfoUseCacheOnResult(Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, Boolean useCache){
		List <Sellercontactinfo> list = partialLoadWithParentSellercontactinfoUseCacheOnResult(sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo, useCache);
		return (!list.isEmpty())?(Sellercontactinfo)list.get(0):null;
    }
	//
	protected List<Sellercontactinfo> partialLoadWithParentSellercontactinfo(Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentSellercontactinfo(sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentSellercontactinfoQueryResult (Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentSellercontactinfoQueryResult (sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<Sellercontactinfo> getDistinctSellercontactinfo(Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo) {
		 return partialLoadWithParentSellercontactinfo(sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<Sellercontactinfo> partialLoadWithParentSellercontactinfo(Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo) {
		 return partialLoadWithParentSellercontactinfo(sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo, new QuerySelectInit(), null, false);
	}	
  
	public List<Sellercontactinfo> partialLoadWithParentSellercontactinfoUseCacheOnResult(Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, Boolean useCache) {
		String key = getCacheKey(sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo, "partialLoadWithParentSellercontactinfo");
		List<Sellercontactinfo> list = (List<Sellercontactinfo>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentSellercontactinfo(sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<Sellercontactinfo> partialLoadWithParentSellercontactinfoUseCache(Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, Boolean useCache) {
		String key = getCacheKey(sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo, "partialLoadWithParentSellercontactinfo");
		List<Sellercontactinfo> list = (List<Sellercontactinfo>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentSellercontactinfo(sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<Sellercontactinfo> handleLoadWithParentSellercontactinfo(Map beanPath, List list, Sellercontactinfo sellercontactinfoWhat) {
	    return handleLoadWithParentSellercontactinfo(beanPath, list, sellercontactinfoWhat, true);  
	}
	
	private List<Sellercontactinfo> handleLoadWithParentSellercontactinfo(Map beanPath, List list, Sellercontactinfo sellercontactinfoWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentSellercontactinfoWithOneElementInRow(list, beanPath, sellercontactinfoWhat, isHql);
		}
		return handlePartialLoadWithParentSellercontactinfo(list, beanPath, sellercontactinfoWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateSellercontactinfo (Sellercontactinfo sellercontactinfo, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(sellercontactinfo, beanPath, value);
	}
	
	protected void populateSellercontactinfoFromSQL (Sellercontactinfo sellercontactinfo, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(sellercontactinfo, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to sellercontactinfo.clone() instead
	private Sellercontactinfo cloneSellercontactinfo (Sellercontactinfo sellercontactinfo) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (Sellercontactinfo) BeanUtils.cloneBeanObject(sellercontactinfo);
	   if (sellercontactinfo==null) return new Sellercontactinfo();
	   return sellercontactinfo.clone();
	}
    
    // to set in super class
	private Object getBeanObjectInstance (Object bean) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
       return BeanUtils.getBeanObjectInstance(bean);	
	}
	// to set in super class
	protected void populateObject (Object bean, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException {
       BeanUtils.populateObject(bean, value, beanPath);
	}
	
	protected void populateObjectFromSQL (Object bean, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException {
       BeanUtils.populateObject(bean, value, beanPath);
	}
	
    public List<Sellercontactinfo> countDistinct (Sellercontactinfo whatMask, Sellercontactinfo whereEqCriteria) {
       return partialLoadWithParentSellercontactinfo(whatMask, whereEqCriteria, null, new QuerySelectCountInit("sellercontactinfo"), null, false);
    }   
  	
    public Long count(Sellercontactinfo whereEqCriteria) {
	    return count(null, whereEqCriteria, EntityMatchType.ALL, OperandType.EQUALS, true); 
/*        Query query = getEntityManager().createQuery(getSelectCountPrototype(whereEqCriteria));
        List<Long> list = query.getResultList();
    	if (!list.isEmpty()) {
            return list.get(0);
    	}
    	return 0L;
*/
    }


    public Long count(Sellercontactinfo whatMask, Sellercontactinfo whereCriteria, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        Query query = null;
		if (isAllNull(whatMask))
			query = getEntityManager().createQuery(countQuery(whereCriteria, matchType, operandType, caseSensitivenessType));
		else {
			query = getEntityManager().createQuery(countPartialLoadWithParentRawHsqlQuery(whatMask, whereCriteria, matchType, operandType, caseSensitivenessType));
		}
        List<Long> list = query.getResultList();
    	if (!list.isEmpty()) {
            return list.get(0);
    	}
    	return 0L;
    }

	protected String countQuery (Sellercontactinfo sellercontactinfo, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        String what = "SELECT count(*) FROM Sellercontactinfo sellercontactinfo ";
		return findQuery (sellercontactinfo, null, what, matchType, operandType, caseSensitivenessType, null);
    }
	
    protected String getSelectCountPrototype (Sellercontactinfo whereEqCriteria) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT count(*) FROM Sellercontactinfo sellercontactinfo ");
        queryWhere.append (getWhereEqualWhereQueryChunk(whereEqCriteria, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }
			
   public Sellercontactinfo getFirstSellercontactinfoWhereConditionsAre (Sellercontactinfo sellercontactinfo) {
      List<Sellercontactinfo> list = partialLoadWithParentSellercontactinfo(getDefaultSellercontactinfoWhat(), sellercontactinfo, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (Sellercontactinfo sellercontactinfo) {
      return partialLoadWithParentSellercontactinfoQueryResult(getDefaultSellercontactinfoWhat(), sellercontactinfo, null, 1, false);	
   }
   
   protected Sellercontactinfo getDefaultSellercontactinfoWhat() {
      Sellercontactinfo sellercontactinfo = new Sellercontactinfo();
      sellercontactinfo.setContactinfoid(Integer.valueOf(-1));
      return sellercontactinfo;
   }
   
	public Sellercontactinfo getFirstSellercontactinfo (Sellercontactinfo sellercontactinfo) {
		if (isAllNull(sellercontactinfo))
			return null;
		else {
			List<Sellercontactinfo> list = searchPrototype (sellercontactinfo, 1);
			if (list.isEmpty()) {
				return null;
			}
			else if (list.size()==1)
				return list.get(0);
			else 
				//TODO log error
				return list.get(0);	
		}
	}
	
    /**
    * checks if the Sellercontactinfo entity exists
    */           
    public boolean existsSellercontactinfo (Sellercontactinfo sellercontactinfo) {
       if (getFirstSellercontactinfo(sellercontactinfo)!=null)
          return true;
       return false;  
    }
        
    public boolean existsSellercontactinfoWhereConditionsAre (Sellercontactinfo sellercontactinfo) {
       if (getFirstResultWhereConditionsAre (sellercontactinfo).isEmpty())
          return false;
       return true;  
    }

	private int countPartialField (Sellercontactinfo sellercontactinfo) {
	   int cpt = 0;
       if (sellercontactinfo.getContactinfoid() != null) {
          cpt++;
       }
       if (sellercontactinfo.getLastname() != null) {
          cpt++;
       }
       if (sellercontactinfo.getFirstname() != null) {
          cpt++;
       }
       if (sellercontactinfo.getEmail() != null) {
          cpt++;
       }
       return cpt;
	}   

	public List<Sellercontactinfo> partialLoadWithParentSellercontactinfo(Sellercontactinfo what, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentSellercontactinfoJPAQueryResult (what, positiveSellercontactinfo, negativeSellercontactinfo, queryWhatInit, beanPath, nbOfResult, useCache);
		return handlePartialLoadWithParentResult(what, list, beanPath);
	}
	
	public List<Sellercontactinfo> handlePartialLoadWithParentResult(Sellercontactinfo what, List list, Map beanPath) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentSellercontactinfoWithOneElementInRow(list, beanPath, what, true);
		}
		return handlePartialLoadWithParentSellercontactinfo(list, beanPath, what, true);
	}	

	private List partialLoadWithParentSellercontactinfoQueryResult(Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentSellercontactinfoJPAQueryResult (sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo, queryWhatInit, new Hashtable(), nbOfResult, useCache);
    }	
  
	private List partialLoadWithParentSellercontactinfoJPAQueryResult(Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentJPAQuery (sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
    }	
   /**
    * @returns an JPA Hsql query based on entity Sellercontactinfo and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentJPAQuery (Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getPartialLoadWithParentJPARawQuery (sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo, beanPath, queryWhatInit);
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
    }
  	
   /**
    * @returns an JPA Raw Hsql query based on entity Sellercontactinfo and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentJPARawQuery (Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, Map beanPath, QueryWhatInit queryWhatInit) {
	   return getEntityManager().createQuery(getPartialLoadWithParentRawHsqlQuery (sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo, beanPath, queryWhatInit));
    }
	
	private List<Sellercontactinfo> handlePartialLoadWithParentSellercontactinfo(List<Object[]> list, Map<Integer, String> beanPath, Sellercontactinfo sellercontactinfoWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentSellercontactinfo(list, beanPath, sellercontactinfoWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentSellercontactinfo, message:"+ex.getMessage());
			return new ArrayList<Sellercontactinfo>();
		}
    }

	private List<Sellercontactinfo> handlePartialLoadWithParentSellercontactinfoWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Sellercontactinfo sellercontactinfoWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentSellercontactinfoWithOneElementInRow(list, beanPath, sellercontactinfoWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentSellercontactinfoWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<Sellercontactinfo>();
		}
    }
    	
	 private List<Sellercontactinfo> convertPartialLoadWithParentSellercontactinfo(List<Object[]> list, Map<Integer, String> beanPath, Sellercontactinfo sellercontactinfoWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Sellercontactinfo> resultList = new ArrayList<Sellercontactinfo>();
		 for (Object[] row : list) {		
		    Sellercontactinfo sellercontactinfo = cloneSellercontactinfo (sellercontactinfoWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateSellercontactinfo (sellercontactinfo, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(sellercontactinfo);
		 }
		 return resultList;		
	 }	
    
	 private List<Sellercontactinfo> convertPartialLoadWithParentSellercontactinfoWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Sellercontactinfo sellercontactinfoWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Sellercontactinfo> resultList = new ArrayList<Sellercontactinfo>();
		 for (Object row : list) {		
		    Sellercontactinfo sellercontactinfo = cloneSellercontactinfo (sellercontactinfoWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateSellercontactinfo (sellercontactinfo, row, (String)entry.getValue());
		    }
		    resultList.add(sellercontactinfo);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, Sellercontactinfo sellercontactinfoWhat, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentJPAQuery (sellercontactinfoWhat, positiveSellercontactinfo, negativeSellercontactinfo, beanPath, new QuerySelectInit(), null);
        List<Object[]> list = hquery.getResultList();
		return handlePartialLoadWithParentForBean(list, beanPath, bean);
    }	
//	 to set in super class
	private List handlePartialLoadWithParentForBean(List<Object[]> list, Map<Integer, String> beanPath, Object bean) {
		try {
			return convertPartialLoadWithParentForBean(list, beanPath, bean);
		} catch (Exception ex) {
			//TODO log ex
			return new ArrayList();
		}
    }
	// to set in super class
	private List convertPartialLoadWithParentForBean(List<Object[]> list, Map<Integer, String> beanPath, Object bean) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		List resultList = new ArrayList();
		for (Object[] row : list) {		
		   Object result = getBeanObjectInstance(bean);
		   Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		   while (iter.hasNext()) {
			  Entry entry = iter.next();
			  populateObject (result, row[(Integer)entry.getKey()], getFieldFromBeanPath((String)entry.getValue()));
			}
			resultList.add(result);
		}
		return resultList;		
    }

	
	// to set in super class
	private String getFieldFromBeanPath(String beanPath) {
	   String result = StringUtils.substringAfterLast(beanPath, ".");
	   if (result.equals(""))
		 return beanPath;
	   return result;
	}

   /**
    * 
    * partial on entity and its parents load enables to specify the fields you want to load explicitly
    */
	public String getPartialLoadWithParentRawHsqlQuery (Sellercontactinfo sellercontactinfo, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentSellercontactinfoQuery (sellercontactinfo, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereQuery (positiveSellercontactinfo, null, aliasWhatHt, aliasWhereHt, null, null);
		String whereHow = reconciliateWherePath(aliasWhatHt, aliasWhereHt);
		String how = reconciliateHowPath(aliasWhatHt, aliasWhereHt);
		String andConcat = "";
		if (where!=null && !where.equals("") && whereHow!=null && !whereHow.equals(""))
			andConcat=" AND ";
		return what+" FROM "+how +" WHERE "+whereHow+ andConcat +where;
	}

   /**
    * 
    * partial on entity and its parents load enables to specify the fields you want to load explicitly
    */
	public String findPartialLoadWithParentRawHsqlQuery (Sellercontactinfo whatMask, Sellercontactinfo criteriaMask, Map beanPath, QueryWhatInit queryWhatInit,  Sellercontactinfo orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentSellercontactinfoQuery (whatMask, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereQuery (criteriaMask, null, aliasWhatHt, aliasWhereHt, null, null, matchType, operandType, caseSensitivenessType);
		String whereHow = reconciliateWherePath(aliasWhatHt, aliasWhereHt);
		String how = reconciliateHowPath(aliasWhatHt, aliasWhereHt);
		String whereConcat = "";
		if (whereHow!=null && !whereHow.equals(""))
			whereConcat=" WHERE ";
		String andConcat = "";
		if (where!=null && !where.equals("") && whereHow!=null && !whereHow.equals(""))
			andConcat=" AND ";
		String order = findOrder (orderMask, sortOrder);
		String orderConcat = "";
		if (order!=null && !order.equals(""))
			orderConcat=" ORDER BY ";
		return what + " FROM " +how + whereConcat + whereHow + andConcat + where + orderConcat + order;
	}
   /**
    * 
    * count number of entity matching criteria on entity and its parents load enables to specify the fields you want to load explicitly
    */
	public String countPartialLoadWithParentRawHsqlQuery (Sellercontactinfo whatMask, Sellercontactinfo criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
		Map beanPath = new Hashtable();
		Hashtable aliasWhatHt = new Hashtable();
		// used to initiate the how part of the what
		getPartialLoadWithParentSellercontactinfoQuery (whatMask, null, aliasWhatHt, null, null, beanPath, "", new QuerySelectInit());
		String what = "select count(sellercontactinfo) ";
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereQuery (criteriaMask, null, aliasWhatHt, aliasWhereHt, null, null, matchType, operandType, caseSensitivenessType);
		String whereHow = reconciliateWherePath(aliasWhatHt, aliasWhereHt);
		String how = reconciliateHowPath(aliasWhatHt, aliasWhereHt);
        String whereConcat = "";
        if (whereHow!=null && !whereHow.equals(""))
            whereConcat=" WHERE ";
		String andConcat = "";
		if (where!=null && !where.equals("") && whereHow!=null && !whereHow.equals(""))
            andConcat=" AND ";
		return what+" FROM "+how +whereConcat+whereHow+ andConcat +where;
	}
    	
	public String findPartialQuery (Sellercontactinfo whatMask, Sellercontactinfo criteriaMask, Sellercontactinfo orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Map beanPath) {
        QueryWhatInit queryWhatInit = new QuerySelectInit();
        return findPartialLoadWithParentRawHsqlQuery(whatMask, criteriaMask, beanPath, queryWhatInit, orderMask, matchType, operandType, caseSensitivenessType,  sortOrder);
    }
	
	/**
    * partial on a single entity load enables to specify the fields you want to load explicitly
    */         
	public List<Sellercontactinfo> partialLoadSellercontactinfo(Sellercontactinfo sellercontactinfo, Sellercontactinfo positiveSellercontactinfo, Sellercontactinfo negativeSellercontactinfo) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadSellercontactinfoQuery (sellercontactinfo, positiveSellercontactinfo, negativeSellercontactinfo));
		int countPartialField = countPartialField(sellercontactinfo);
		if (countPartialField==0) 
			return new ArrayList<Sellercontactinfo>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<Sellercontactinfo> returnList = new ArrayList<Sellercontactinfo>();
		while(iter.hasNext()) {
			int index = 0;
			Object[] row;
			if (countPartialField==1) {
				row = new Object[1];
				row[0] = iter.next();
				} 
			else 
				row = (Object[]) iter.next();
			Sellercontactinfo sellercontactinfoResult = new Sellercontactinfo();
			if (sellercontactinfo.getLastname() != null) {
                sellercontactinfoResult.setLastname((String) row[index]);
				index++;
			}
			if (sellercontactinfo.getFirstname() != null) {
                sellercontactinfoResult.setFirstname((String) row[index]);
				index++;
			}
			if (sellercontactinfo.getEmail() != null) {
                sellercontactinfoResult.setEmail((String) row[index]);
				index++;
			}
			returnList.add(sellercontactinfoResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereQuery (
	   Sellercontactinfo criteriaMask, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias,
	   EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
	   if (criteriaMask==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(criteriaMask)){
	      alias = getNextAlias (aliasWhereHt, criteriaMask);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
	   return findWhere (alias, criteriaMask, false, isAll(matchType), operandType, caseSensitivenessType); 
	}

	public static String getPartialLoadWithParentWhereQuery (
	   Sellercontactinfo sellercontactinfo, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (sellercontactinfo==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(sellercontactinfo)){
	      alias = getNextAlias (aliasWhereHt, sellercontactinfo);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (sellercontactinfo.getContactinfoid() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".contactinfoid = "+ sellercontactinfo.getContactinfoid() + " ");
       }
       if (sellercontactinfo.getLastname() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".lastname = '"+ sellercontactinfo.getLastname()+"' ");
       }
       if (sellercontactinfo.getFirstname() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".firstname = '"+ sellercontactinfo.getFirstname()+"' ");
       }
       if (sellercontactinfo.getEmail() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".email = '"+ sellercontactinfo.getEmail()+"' ");
       }
	   return query.toString(); 
    }
	
	public static String reconciliateWherePath(Hashtable aliasWhatHt, Hashtable aliasWhereHt) {
	   StringBuffer sb = new StringBuffer();
	   boolean isBlankSet = false;
	   aliasWhatHt.putAll(aliasWhereHt);
	   Enumeration<String> elements = aliasWhatHt.elements();
	   while (elements.hasMoreElements()) {
		  String element = elements.nextElement();
		  if (!element.equals("")) {
		     sb.append (getQueryBLANK_AND (isBlankSet));
		     isBlankSet=true;
		     sb.append(element);
		  }
	   }
	   return sb.toString();
	}
	
	public static String reconciliateHowPath(Hashtable aliasWhatHt, Hashtable aliasWhereHt) {
	   StringBuffer sb = new StringBuffer();
	   boolean isBlankSet = false;
	   aliasWhatHt.putAll(aliasWhereHt);
	   Enumeration<String> keys = aliasWhatHt.keys();
	   while (keys.hasMoreElements()) {
		  String key = keys.nextElement();
		  sb.append (getQueryBLANK_COMMA (isBlankSet));
		  isBlankSet = true;
		  sb.append(getAliasKeyDomain(key)+" "+getAliasKeyAlias(key));
	   }
	   return sb.toString();
	}
	
	protected static String getRootDomainName (String domainName) {
		return StringUtils.substringBefore(domainName, "_");
	}
	
    public static String getPartialLoadWithParentSellercontactinfoQuery (
	   Sellercontactinfo sellercontactinfo, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (sellercontactinfo==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(sellercontactinfo)){
	      alias = getNextAlias (aliasHt, sellercontactinfo);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (sellercontactinfo.getContactinfoid() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"contactinfoid");
          query.append(" "+alias+".contactinfoid ");
       }
       if (sellercontactinfo.getLastname() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"lastname");
          query.append(" "+alias+".lastname ");
       }
       if (sellercontactinfo.getFirstname() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"firstname");
          query.append(" "+alias+".firstname ");
       }
       if (sellercontactinfo.getEmail() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"email");
          query.append(" "+alias+".email ");
       }
//       query.append(getSellercontactinfoSearchEqualQuery (positiveSellercontactinfo, negativeSellercontactinfo));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"contactinfoid";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "Sellercontactinfo|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, Sellercontactinfo sellercontactinfo) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("sellercontactinfo"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "sellercontactinfo";
		else
			return "sellercontactinfo_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (Sellercontactinfo sellercontactinfo) {
	   if (sellercontactinfo==null)
		  return false;
       if (sellercontactinfo.getContactinfoid() != null) {
	      return true;
       }
       if (sellercontactinfo.getLastname() != null) {
	      return true;
       }
       if (sellercontactinfo.getFirstname() != null) {
	      return true;
       }
       if (sellercontactinfo.getEmail() != null) {
	      return true;
       }
       return false;   
	}
	
    public String getPartialLoadSellercontactinfoQuery(
	   Sellercontactinfo sellercontactinfo, 
	   Sellercontactinfo positiveSellercontactinfo, 
	   Sellercontactinfo negativeSellercontactinfo) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (sellercontactinfo.getContactinfoid() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" contactinfoid ");
       }
       if (sellercontactinfo.getLastname() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" lastname ");
       }
       if (sellercontactinfo.getFirstname() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" firstname ");
       }
       if (sellercontactinfo.getEmail() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" email ");
       }
       query.append(getSellercontactinfoSearchEqualQuery (positiveSellercontactinfo, negativeSellercontactinfo));
	   return query.toString(); 
    }
	
	public List<Sellercontactinfo> searchPrototypeWithCacheSellercontactinfo(Sellercontactinfo sellercontactinfo) {
		SimpleCache simpleCache = new SimpleCache();
		List<Sellercontactinfo> list = (List<Sellercontactinfo>)simpleCache.get(sellercontactinfo.toString());
		if (list==null) {
			list = searchPrototypeSellercontactinfo(sellercontactinfo);
			simpleCache.put(sellercontactinfo.toString(), list);
		}
		return list;
	}

    public List<Sellercontactinfo> loadGraph(Sellercontactinfo graphMaskWhat, List<Sellercontactinfo> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }

	public List<Sellercontactinfo> loadGraphOneLevel(Sellercontactinfo graphMaskWhat, List<Sellercontactinfo> whereMask) {
		//first get roots element from where list mask
		// this brings the level 0 of the graph (root level)
 		graphMaskWhat.setContactinfoid(graphMaskWhat.integerMask__);
		List<Sellercontactinfo> sellercontactinfos = searchPrototypeSellercontactinfo (whereMask);
		// for each sub level perform the search with a subquery then reassemble
		// 1. get all sublevel queries
		// 2. perform queries on the correct dao
		// 3. reassemble
		return getLoadGraphOneLevel (graphMaskWhat, sellercontactinfos);
	}

	private List<Sellercontactinfo> copy(List<Sellercontactinfo> inputs) {
		List<Sellercontactinfo> l = new ArrayList<Sellercontactinfo>();
		for (Sellercontactinfo input : inputs) {
			Sellercontactinfo copy = new Sellercontactinfo();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<Sellercontactinfo> getLoadGraphOneLevel (Sellercontactinfo graphMaskWhat, List<Sellercontactinfo> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<Sellercontactinfo> loadGraphFromParentKey (Sellercontactinfo graphMaskWhat, List<Sellercontactinfo> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		if (graphMaskWhat.getMyGoodItems()!=null && !graphMaskWhat.getMyGoodItems().isEmpty()) {
			for (MyGoodItem childWhat : graphMaskWhat.getMyGoodItems()) {
				childWhat.setContactinfoContactinfoid_(graphMaskWhat.integerMask__); // add to the what mask, usefull for reconciliation
				MyGoodItemExtendedJPAImpl mygooditemextendedjpaimpl = new MyGoodItemExtendedJPAImpl ();
				List<MyGoodItem> children = mygooditemextendedjpaimpl.lookupMyGoodItem(childWhat, getFkCriteria(" contactinfoid ", ids), null, getEntityManager());
				reassembleMyGoodItem (children, parents);				
				break;
			}
		}
		return parents;
	}
	
	private void reassembleMyGoodItem (List<MyGoodItem> children, List<Sellercontactinfo> parents) {
		for (MyGoodItem child : children) {
			for (Sellercontactinfo parent : parents) {
				if (parent.getContactinfoid()!=null && parent.getContactinfoid().toString().equals(child.getContactinfoContactinfoid()+"")) {
					parent.addMyGoodItems(child); 
					child.setContactinfoContactinfoid(parent);
					break;
				}
			}
		}
	}
	
	private List<String> getPk(List<Sellercontactinfo> input) {
		List<String> s = new ArrayList<String>();
		for (Sellercontactinfo t : input) {
			s.add(t.getContactinfoid()+"");
		}
		return s;
	}

	private Criteria getFkCriteria(String field, List<String> ids) {
		Criteria criteria = new Criteria();
		criteria.addCriterion(getInPk(field, ids));
		return criteria;
	}

	private ClauseCriterion getInPk(String field, List<String> ids) {
		InCriterion inCriterion = new InCriterion(field, ids, true);
		return inCriterion;
	}	    
    
    // generic part
	public void find (QueryData<Sellercontactinfo> data) {
		EntityCriteria<Sellercontactinfo> filter = data.getEntityCriteria();
		Sellercontactinfo entityWhat = data.getEntityWhat();
		Sellercontactinfo criteriaMask = filter.getEntity();
		int start = data.getStart();
		int max = data.getMax();
		EntitySort<Sellercontactinfo> entitySort = data.getEntitySort();
		QuerySortOrder sortOrder = entitySort.getOrder();
		Sellercontactinfo sortMask = entitySort.getEntity();	

		List<Sellercontactinfo> results = find(entityWhat, criteriaMask, sortMask, filter.getMatchType(), filter.getOperandType(), filter.getCaseSensitivenessType(), sortOrder, start, max);
		data.setResult(results);
		int size = results.size();
		if (size<max) 
			data.setTotalResultCount(Long.valueOf(size));
		else
			data.setTotalResultCount(count(entityWhat, criteriaMask, filter.getMatchType(), filter.getOperandType(), filter.getCaseSensitivenessType()));

	}
	


    public EntityManager getEntityManagerForRecursiveDao() {
		return emForRecursiveDao;
	}

	public void setEntityManagerForRecursiveDao(EntityManager emForRecursiveDao) {
		this.emForRecursiveDao = emForRecursiveDao;
	}
	
    public void setMyGoodItemExtendedJPAImpl (MyGoodItemExtendedJPAImpl mygooditemextendedjpaimpl) {
       this.mygooditemextendedjpaimpl = mygooditemextendedjpaimpl;
    }
    
    public MyGoodItemExtendedJPAImpl getMyGoodItemExtendedJPAImpl () {
       return mygooditemextendedjpaimpl;
    }
    

    /**
     * return a list of Sellercontactinfo entities 
     */
    public List<Sellercontactinfo> getList () {
        //first lightweight implementation
        return searchPrototypeSellercontactinfo(new Sellercontactinfo());
    }
    /**
     * return a list of Sellercontactinfo entities and sort
     */
    public List<Sellercontactinfo> getList (Sellercontactinfo orderMask, QuerySortOrder sortOrder) {
        return searchPrototype(new Sellercontactinfo(), orderMask, sortOrder, null);
    }
    /**
     * return a list of Sellercontactinfo entities and sort based on a Sellercontactinfo prototype
     */
    public List<Sellercontactinfo> list (Sellercontactinfo mask, Sellercontactinfo orderMask, QuerySortOrder sortOrder) {
        return searchPrototype(mask, orderMask, sortOrder, null);
    }

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
