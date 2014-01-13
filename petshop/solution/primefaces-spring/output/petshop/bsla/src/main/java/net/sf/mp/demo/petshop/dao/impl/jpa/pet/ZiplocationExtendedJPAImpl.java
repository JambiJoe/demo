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
import net.sf.mp.demo.petshop.dao.face.pet.ZiplocationExtDao;
import net.sf.mp.demo.petshop.domain.pet.Ziplocation;
import net.sf.mp.demo.petshop.dao.impl.jpa.pet.ZiplocationJPAImpl;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@


/**
 *
 * <p>Title: ZiplocationExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with ZiplocationExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching ZiplocationExtendedJPAImpl objects</p>
 *
 */
@org.springframework.stereotype.Repository(value="ziplocationExtDao")
public class ZiplocationExtendedJPAImpl extends ZiplocationJPAImpl implements ZiplocationExtDao{
    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public ZiplocationExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public ZiplocationExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a Ziplocation entity with cascade of its children
     * @param Ziplocation ziplocation
     */
    public void insertZiplocationWithCascade(Ziplocation ziplocation) {
    	ZiplocationExtendedJPAImpl ziplocationextendedjpaimpl = new ZiplocationExtendedJPAImpl(getEntityManager());
    	ziplocationextendedjpaimpl.insertZiplocationWithCascade(ziplocationextendedjpaimpl.getEntityManagerForRecursiveDao(), ziplocation);
    }
     
    public void insertZiplocationWithCascade(EntityManager emForRecursiveDao, Ziplocation ziplocation) {
       insertZiplocation(emForRecursiveDao, ziplocation);
    }
        
    /**
     * Inserts a list of Ziplocation entity with cascade of its children
     * @param List<Ziplocation> ziplocations
     */
    public void insertZiplocationsWithCascade(List<Ziplocation> ziplocations) {
       for (Ziplocation ziplocation : ziplocations) {
          insertZiplocationWithCascade(ziplocation);
       }
    } 
        
    /**
     * lookup Ziplocation entity Ziplocation, criteria and max result number
     */
    public List<Ziplocation> lookupZiplocation(Ziplocation ziplocation, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT ziplocation FROM Ziplocation ziplocation ");
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
    
    public List<Ziplocation> lookupZiplocation(Ziplocation ziplocation, Criteria criteria, Integer numberOfResult) {
		return lookupZiplocation(ziplocation, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyZiplocation (Ziplocation ziplocation, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyZiplocationQueryChunkPrototype (ziplocation);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
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
	
	public Ziplocation affectZiplocation (Ziplocation ziplocation) {
	    return referZiplocation (ziplocation, null, false);		    
	}
		
	/**
	 * Assign the first ziplocation retrieved corresponding to the ziplocation criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no ziplocation corresponding in the database. Then ziplocation is inserted and returned with its primary key(s). 
	 */
	public Ziplocation assignZiplocation (Ziplocation ziplocation) {
		return referZiplocation (ziplocation, null, true);
	}

	/**
	 * Assign the first ziplocation retrieved corresponding to the mask criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no ziplocation corresponding in the database. 
	 * Then ziplocation is inserted and returned with its primary key(s). 
	 * Mask servers usually to set unique keys or the semantic reference
	 */
    public Ziplocation assignZiplocation (Ziplocation ziplocation, Ziplocation mask) {
		return referZiplocation (ziplocation, mask, true);
	}

	public Ziplocation referZiplocation (Ziplocation ziplocation, Ziplocation mask, boolean isAssign) {
		ziplocation = assignBlankToNull (ziplocation);
		if (isAllNull(ziplocation))
			return null;
		else {
			List<Ziplocation> list;
			if (mask==null)
				list = searchPrototypeZiplocation(ziplocation);
			else
				list = searchPrototypeZiplocation(mask);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertZiplocation(ziplocation);
			    else
				   return null;
			}
			else if (list.size()==1)
				ziplocation.copy(list.get(0));
			else 
				//TODO log error
				ziplocation.copy(list.get(0));
		}
		return ziplocation;		    
	}

   public Ziplocation assignZiplocationUseCache (Ziplocation ziplocation) {
      return referZiplocationUseCache (ziplocation, true);
   }
      		
   public Ziplocation affectZiplocationUseCache (Ziplocation ziplocation) {
      return referZiplocationUseCache (ziplocation, false);
   }
      		
   public Ziplocation referZiplocationUseCache (Ziplocation ziplocation, boolean isAssign) {
	  String key = getCacheKey(null, ziplocation, null, "assignZiplocation");
      Ziplocation ziplocationCache = (Ziplocation)simpleCache.get(key);
      if (ziplocationCache==null) {
         ziplocationCache = referZiplocation (ziplocation, null, isAssign);
         if (key!=null)
         	simpleCache.put(key, ziplocationCache);
      }
      ziplocation.copy(ziplocationCache);
      return ziplocationCache;
   }	

	private String getCacheKey (Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (ziplocationWhat!=null)
	       sb.append(ziplocationWhat.toStringWithParents());
	    if (positiveZiplocation!=null)
	       sb.append(positiveZiplocation.toStringWithParents());
	    if (negativeZiplocation!=null)
	       sb.append(negativeZiplocation.toStringWithParents());
	    return sb.toString();
	}
	
    public Ziplocation partialLoadWithParentFirstZiplocation(Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation){
		List <Ziplocation> list = partialLoadWithParentZiplocation(ziplocationWhat, positiveZiplocation, negativeZiplocation);
		return (!list.isEmpty())?(Ziplocation)list.get(0):null;
    }
    
    public Ziplocation partialLoadWithParentFirstZiplocationUseCache(Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, Boolean useCache){
		List <Ziplocation> list = partialLoadWithParentZiplocationUseCache(ziplocationWhat, positiveZiplocation, negativeZiplocation, useCache);
		return (!list.isEmpty())?(Ziplocation)list.get(0):null;
    }
	
	public Ziplocation partialLoadWithParentFirstZiplocationUseCacheOnResult(Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, Boolean useCache){
		List <Ziplocation> list = partialLoadWithParentZiplocationUseCacheOnResult(ziplocationWhat, positiveZiplocation, negativeZiplocation, useCache);
		return (!list.isEmpty())?(Ziplocation)list.get(0):null;
    }
	//
	protected List<Ziplocation> partialLoadWithParentZiplocation(Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentZiplocation(ziplocationWhat, positiveZiplocation, negativeZiplocation, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentZiplocationQueryResult (Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentZiplocationQueryResult (ziplocationWhat, positiveZiplocation, negativeZiplocation, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<Ziplocation> getDistinctZiplocation(Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation) {
		 return partialLoadWithParentZiplocation(ziplocationWhat, positiveZiplocation, negativeZiplocation, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<Ziplocation> partialLoadWithParentZiplocation(Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation) {
		 return partialLoadWithParentZiplocation(ziplocationWhat, positiveZiplocation, negativeZiplocation, new QuerySelectInit(), null, false);
	}	
  
	public List<Ziplocation> partialLoadWithParentZiplocationUseCacheOnResult(Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, Boolean useCache) {
		String key = getCacheKey(ziplocationWhat, positiveZiplocation, negativeZiplocation, "partialLoadWithParentZiplocation");
		List<Ziplocation> list = (List<Ziplocation>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentZiplocation(ziplocationWhat, positiveZiplocation, negativeZiplocation);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<Ziplocation> partialLoadWithParentZiplocationUseCache(Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, Boolean useCache) {
		String key = getCacheKey(ziplocationWhat, positiveZiplocation, negativeZiplocation, "partialLoadWithParentZiplocation");
		List<Ziplocation> list = (List<Ziplocation>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentZiplocation(ziplocationWhat, positiveZiplocation, negativeZiplocation);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<Ziplocation> handleLoadWithParentZiplocation(Map beanPath, List list, Ziplocation ziplocationWhat) {
	    return handleLoadWithParentZiplocation(beanPath, list, ziplocationWhat, true);  
	}
	
	private List<Ziplocation> handleLoadWithParentZiplocation(Map beanPath, List list, Ziplocation ziplocationWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentZiplocationWithOneElementInRow(list, beanPath, ziplocationWhat, isHql);
		}
		return handlePartialLoadWithParentZiplocation(list, beanPath, ziplocationWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateZiplocation (Ziplocation ziplocation, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(ziplocation, beanPath, value);
	}
	
	protected void populateZiplocationFromSQL (Ziplocation ziplocation, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(ziplocation, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to ziplocation.clone() instead
	private Ziplocation cloneZiplocation (Ziplocation ziplocation) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (Ziplocation) BeanUtils.cloneBeanObject(ziplocation);
	   if (ziplocation==null) return new Ziplocation();
	   return ziplocation.clone();
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
	
    public List<Ziplocation> countDistinct (Ziplocation whatMask, Ziplocation whereEqCriteria) {
       return partialLoadWithParentZiplocation(whatMask, whereEqCriteria, null, new QuerySelectCountInit("ziplocation"), null, false);
    }   
  	
    public Long count(Ziplocation whereEqCriteria) {
	    return count(null, whereEqCriteria, EntityMatchType.ALL, OperandType.EQUALS, true); 
/*        Query query = getEntityManager().createQuery(getSelectCountPrototype(whereEqCriteria));
        List<Long> list = query.getResultList();
    	if (!list.isEmpty()) {
            return list.get(0);
    	}
    	return 0L;
*/
    }


    public Long count(Ziplocation whatMask, Ziplocation whereCriteria, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
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

	protected String countQuery (Ziplocation ziplocation, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        String what = "SELECT count(*) FROM Ziplocation ziplocation ";
		return findQuery (ziplocation, null, what, matchType, operandType, caseSensitivenessType, null);
    }
	
    protected String getSelectCountPrototype (Ziplocation whereEqCriteria) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT count(*) FROM Ziplocation ziplocation ");
        queryWhere.append (getWhereEqualWhereQueryChunk(whereEqCriteria, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }
			
   public Ziplocation getFirstZiplocationWhereConditionsAre (Ziplocation ziplocation) {
      List<Ziplocation> list = partialLoadWithParentZiplocation(getDefaultZiplocationWhat(), ziplocation, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (Ziplocation ziplocation) {
      return partialLoadWithParentZiplocationQueryResult(getDefaultZiplocationWhat(), ziplocation, null, 1, false);	
   }
   
   protected Ziplocation getDefaultZiplocationWhat() {
      Ziplocation ziplocation = new Ziplocation();
      ziplocation.setZipcode(Integer.valueOf(-1));
      return ziplocation;
   }
   
	public Ziplocation getFirstZiplocation (Ziplocation ziplocation) {
		if (isAllNull(ziplocation))
			return null;
		else {
			List<Ziplocation> list = searchPrototype (ziplocation, 1);
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
    * checks if the Ziplocation entity exists
    */           
    public boolean existsZiplocation (Ziplocation ziplocation) {
       if (getFirstZiplocation(ziplocation)!=null)
          return true;
       return false;  
    }
        
    public boolean existsZiplocationWhereConditionsAre (Ziplocation ziplocation) {
       if (getFirstResultWhereConditionsAre (ziplocation).isEmpty())
          return false;
       return true;  
    }

	private int countPartialField (Ziplocation ziplocation) {
	   int cpt = 0;
       if (ziplocation.getZipcode() != null) {
          cpt++;
       }
       if (ziplocation.getCity() != null) {
          cpt++;
       }
       if (ziplocation.getState() != null) {
          cpt++;
       }
       return cpt;
	}   

	public List<Ziplocation> partialLoadWithParentZiplocation(Ziplocation what, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentZiplocationJPAQueryResult (what, positiveZiplocation, negativeZiplocation, queryWhatInit, beanPath, nbOfResult, useCache);
		return handlePartialLoadWithParentResult(what, list, beanPath);
	}
	
	public List<Ziplocation> handlePartialLoadWithParentResult(Ziplocation what, List list, Map beanPath) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentZiplocationWithOneElementInRow(list, beanPath, what, true);
		}
		return handlePartialLoadWithParentZiplocation(list, beanPath, what, true);
	}	

	private List partialLoadWithParentZiplocationQueryResult(Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentZiplocationJPAQueryResult (ziplocationWhat, positiveZiplocation, negativeZiplocation, queryWhatInit, new Hashtable(), nbOfResult, useCache);
    }	
  
	private List partialLoadWithParentZiplocationJPAQueryResult(Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentJPAQuery (ziplocationWhat, positiveZiplocation, negativeZiplocation, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
    }	
   /**
    * @returns an JPA Hsql query based on entity Ziplocation and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentJPAQuery (Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getPartialLoadWithParentJPARawQuery (ziplocationWhat, positiveZiplocation, negativeZiplocation, beanPath, queryWhatInit);
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
    }
  	
   /**
    * @returns an JPA Raw Hsql query based on entity Ziplocation and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentJPARawQuery (Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, Map beanPath, QueryWhatInit queryWhatInit) {
	   return getEntityManager().createQuery(getPartialLoadWithParentRawHsqlQuery (ziplocationWhat, positiveZiplocation, negativeZiplocation, beanPath, queryWhatInit));
    }
	
	private List<Ziplocation> handlePartialLoadWithParentZiplocation(List<Object[]> list, Map<Integer, String> beanPath, Ziplocation ziplocationWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentZiplocation(list, beanPath, ziplocationWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentZiplocation, message:"+ex.getMessage());
			return new ArrayList<Ziplocation>();
		}
    }

	private List<Ziplocation> handlePartialLoadWithParentZiplocationWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Ziplocation ziplocationWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentZiplocationWithOneElementInRow(list, beanPath, ziplocationWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentZiplocationWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<Ziplocation>();
		}
    }
    	
	 private List<Ziplocation> convertPartialLoadWithParentZiplocation(List<Object[]> list, Map<Integer, String> beanPath, Ziplocation ziplocationWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Ziplocation> resultList = new ArrayList<Ziplocation>();
		 for (Object[] row : list) {		
		    Ziplocation ziplocation = cloneZiplocation (ziplocationWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateZiplocation (ziplocation, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(ziplocation);
		 }
		 return resultList;		
	 }	
    
	 private List<Ziplocation> convertPartialLoadWithParentZiplocationWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Ziplocation ziplocationWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Ziplocation> resultList = new ArrayList<Ziplocation>();
		 for (Object row : list) {		
		    Ziplocation ziplocation = cloneZiplocation (ziplocationWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateZiplocation (ziplocation, row, (String)entry.getValue());
		    }
		    resultList.add(ziplocation);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, Ziplocation ziplocationWhat, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentJPAQuery (ziplocationWhat, positiveZiplocation, negativeZiplocation, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentRawHsqlQuery (Ziplocation ziplocation, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentZiplocationQuery (ziplocation, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereQuery (positiveZiplocation, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public String findPartialLoadWithParentRawHsqlQuery (Ziplocation whatMask, Ziplocation criteriaMask, Map beanPath, QueryWhatInit queryWhatInit,  Ziplocation orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentZiplocationQuery (whatMask, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
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
	public String countPartialLoadWithParentRawHsqlQuery (Ziplocation whatMask, Ziplocation criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
		Map beanPath = new Hashtable();
		Hashtable aliasWhatHt = new Hashtable();
		// used to initiate the how part of the what
		getPartialLoadWithParentZiplocationQuery (whatMask, null, aliasWhatHt, null, null, beanPath, "", new QuerySelectInit());
		String what = "select count(ziplocation) ";
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
    	
	public String findPartialQuery (Ziplocation whatMask, Ziplocation criteriaMask, Ziplocation orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Map beanPath) {
        QueryWhatInit queryWhatInit = new QuerySelectInit();
        return findPartialLoadWithParentRawHsqlQuery(whatMask, criteriaMask, beanPath, queryWhatInit, orderMask, matchType, operandType, caseSensitivenessType,  sortOrder);
    }
	
	/**
    * partial on a single entity load enables to specify the fields you want to load explicitly
    */         
	public List<Ziplocation> partialLoadZiplocation(Ziplocation ziplocation, Ziplocation positiveZiplocation, Ziplocation negativeZiplocation) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadZiplocationQuery (ziplocation, positiveZiplocation, negativeZiplocation));
		int countPartialField = countPartialField(ziplocation);
		if (countPartialField==0) 
			return new ArrayList<Ziplocation>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<Ziplocation> returnList = new ArrayList<Ziplocation>();
		while(iter.hasNext()) {
			int index = 0;
			Object[] row;
			if (countPartialField==1) {
				row = new Object[1];
				row[0] = iter.next();
				} 
			else 
				row = (Object[]) iter.next();
			Ziplocation ziplocationResult = new Ziplocation();
			if (ziplocation.getCity() != null) {
                ziplocationResult.setCity((String) row[index]);
				index++;
			}
			if (ziplocation.getState() != null) {
                ziplocationResult.setState((String) row[index]);
				index++;
			}
			returnList.add(ziplocationResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereQuery (
	   Ziplocation criteriaMask, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias,
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
	   Ziplocation ziplocation, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (ziplocation==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(ziplocation)){
	      alias = getNextAlias (aliasWhereHt, ziplocation);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (ziplocation.getZipcode() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".zipcode = "+ ziplocation.getZipcode() + " ");
       }
       if (ziplocation.getCity() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".city = '"+ ziplocation.getCity()+"' ");
       }
       if (ziplocation.getState() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".state = '"+ ziplocation.getState()+"' ");
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
	
    public static String getPartialLoadWithParentZiplocationQuery (
	   Ziplocation ziplocation, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (ziplocation==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(ziplocation)){
	      alias = getNextAlias (aliasHt, ziplocation);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (ziplocation.getZipcode() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"zipcode");
          query.append(" "+alias+".zipcode ");
       }
       if (ziplocation.getCity() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"city");
          query.append(" "+alias+".city ");
       }
       if (ziplocation.getState() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"state");
          query.append(" "+alias+".state ");
       }
//       query.append(getZiplocationSearchEqualQuery (positiveZiplocation, negativeZiplocation));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"zipcode";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "Ziplocation|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, Ziplocation ziplocation) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("ziplocation"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "ziplocation";
		else
			return "ziplocation_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (Ziplocation ziplocation) {
	   if (ziplocation==null)
		  return false;
       if (ziplocation.getZipcode() != null) {
	      return true;
       }
       if (ziplocation.getCity() != null) {
	      return true;
       }
       if (ziplocation.getState() != null) {
	      return true;
       }
       return false;   
	}
	
    public String getPartialLoadZiplocationQuery(
	   Ziplocation ziplocation, 
	   Ziplocation positiveZiplocation, 
	   Ziplocation negativeZiplocation) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (ziplocation.getZipcode() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" zipcode ");
       }
       if (ziplocation.getCity() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" city ");
       }
       if (ziplocation.getState() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" state ");
       }
       query.append(getZiplocationSearchEqualQuery (positiveZiplocation, negativeZiplocation));
	   return query.toString(); 
    }
	
	public List<Ziplocation> searchPrototypeWithCacheZiplocation(Ziplocation ziplocation) {
		SimpleCache simpleCache = new SimpleCache();
		List<Ziplocation> list = (List<Ziplocation>)simpleCache.get(ziplocation.toString());
		if (list==null) {
			list = searchPrototypeZiplocation(ziplocation);
			simpleCache.put(ziplocation.toString(), list);
		}
		return list;
	}

    public List<Ziplocation> loadGraph(Ziplocation graphMaskWhat, List<Ziplocation> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }

	public List<Ziplocation> loadGraphOneLevel(Ziplocation graphMaskWhat, List<Ziplocation> whereMask) {
		//first get roots element from where list mask
		// this brings the level 0 of the graph (root level)
 		graphMaskWhat.setZipcode(graphMaskWhat.integerMask__);
		List<Ziplocation> ziplocations = searchPrototypeZiplocation (whereMask);
		// for each sub level perform the search with a subquery then reassemble
		// 1. get all sublevel queries
		// 2. perform queries on the correct dao
		// 3. reassemble
		return getLoadGraphOneLevel (graphMaskWhat, ziplocations);
	}

	private List<Ziplocation> copy(List<Ziplocation> inputs) {
		List<Ziplocation> l = new ArrayList<Ziplocation>();
		for (Ziplocation input : inputs) {
			Ziplocation copy = new Ziplocation();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<Ziplocation> getLoadGraphOneLevel (Ziplocation graphMaskWhat, List<Ziplocation> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<Ziplocation> loadGraphFromParentKey (Ziplocation graphMaskWhat, List<Ziplocation> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		return parents;
	}
	
	private List<String> getPk(List<Ziplocation> input) {
		List<String> s = new ArrayList<String>();
		for (Ziplocation t : input) {
			s.add(t.getZipcode()+"");
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
	public void find (QueryData<Ziplocation> data) {
		EntityCriteria<Ziplocation> filter = data.getEntityCriteria();
		Ziplocation entityWhat = data.getEntityWhat();
		Ziplocation criteriaMask = filter.getEntity();
		int start = data.getStart();
		int max = data.getMax();
		EntitySort<Ziplocation> entitySort = data.getEntitySort();
		QuerySortOrder sortOrder = entitySort.getOrder();
		Ziplocation sortMask = entitySort.getEntity();	

		List<Ziplocation> results = find(entityWhat, criteriaMask, sortMask, filter.getMatchType(), filter.getOperandType(), filter.getCaseSensitivenessType(), sortOrder, start, max);
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
	

    /**
     * return a list of Ziplocation entities 
     */
    public List<Ziplocation> getList () {
        //first lightweight implementation
        return searchPrototypeZiplocation(new Ziplocation());
    }
    /**
     * return a list of Ziplocation entities and sort
     */
    public List<Ziplocation> getList (Ziplocation orderMask, QuerySortOrder sortOrder) {
        return searchPrototype(new Ziplocation(), orderMask, sortOrder, null);
    }
    /**
     * return a list of Ziplocation entities and sort based on a Ziplocation prototype
     */
    public List<Ziplocation> list (Ziplocation mask, Ziplocation orderMask, QuerySortOrder sortOrder) {
        return searchPrototype(mask, orderMask, sortOrder, null);
    }

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
