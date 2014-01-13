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
import net.sf.mp.demo.petshop.dao.face.pet.TagExtDao;
import net.sf.mp.demo.petshop.domain.pet.Tag;
import net.sf.mp.demo.petshop.dao.impl.jpa.pet.TagJPAImpl;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@


/**
 *
 * <p>Title: TagExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with TagExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching TagExtendedJPAImpl objects</p>
 *
 */
@org.springframework.stereotype.Repository(value="tagExtDao")
public class TagExtendedJPAImpl extends TagJPAImpl implements TagExtDao{
    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public TagExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public TagExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a Tag entity with cascade of its children
     * @param Tag tag
     */
    public void insertTagWithCascade(Tag tag) {
    	TagExtendedJPAImpl tagextendedjpaimpl = new TagExtendedJPAImpl(getEntityManager());
    	tagextendedjpaimpl.insertTagWithCascade(tagextendedjpaimpl.getEntityManagerForRecursiveDao(), tag);
    }
     
    public void insertTagWithCascade(EntityManager emForRecursiveDao, Tag tag) {
       insertTag(emForRecursiveDao, tag);
    }
        
    /**
     * Inserts a list of Tag entity with cascade of its children
     * @param List<Tag> tags
     */
    public void insertTagsWithCascade(List<Tag> tags) {
       for (Tag tag : tags) {
          insertTagWithCascade(tag);
       }
    } 
        
    /**
     * lookup Tag entity Tag, criteria and max result number
     */
    public List<Tag> lookupTag(Tag tag, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT tag FROM Tag tag ");
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
    
    public List<Tag> lookupTag(Tag tag, Criteria criteria, Integer numberOfResult) {
		return lookupTag(tag, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyTag (Tag tag, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyTagQueryChunkPrototype (tag);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
        if (tag.getTagid() != null) {
           jpaQuery.setParameter ("tagid", tag.getTagid());
        }   
        if (tag.getTag() != null) {
           jpaQuery.setParameter ("tag", tag.getTag());
        }   
        if (tag.getRefcount() != null) {
           jpaQuery.setParameter ("refcount", tag.getRefcount());
        }   
		return jpaQuery.executeUpdate();
    }
	
	public Tag affectTag (Tag tag) {
	    return referTag (tag, null, false);		    
	}
		
	/**
	 * Assign the first tag retrieved corresponding to the tag criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no tag corresponding in the database. Then tag is inserted and returned with its primary key(s). 
	 */
	public Tag assignTag (Tag tag) {
		return referTag (tag, null, true);
	}

	/**
	 * Assign the first tag retrieved corresponding to the mask criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no tag corresponding in the database. 
	 * Then tag is inserted and returned with its primary key(s). 
	 * Mask servers usually to set unique keys or the semantic reference
	 */
    public Tag assignTag (Tag tag, Tag mask) {
		return referTag (tag, mask, true);
	}

	public Tag referTag (Tag tag, Tag mask, boolean isAssign) {
		tag = assignBlankToNull (tag);
		if (isAllNull(tag))
			return null;
		else {
			List<Tag> list;
			if (mask==null)
				list = searchPrototypeTag(tag);
			else
				list = searchPrototypeTag(mask);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertTag(tag);
			    else
				   return null;
			}
			else if (list.size()==1)
				tag.copy(list.get(0));
			else 
				//TODO log error
				tag.copy(list.get(0));
		}
		return tag;		    
	}

   public Tag assignTagUseCache (Tag tag) {
      return referTagUseCache (tag, true);
   }
      		
   public Tag affectTagUseCache (Tag tag) {
      return referTagUseCache (tag, false);
   }
      		
   public Tag referTagUseCache (Tag tag, boolean isAssign) {
	  String key = getCacheKey(null, tag, null, "assignTag");
      Tag tagCache = (Tag)simpleCache.get(key);
      if (tagCache==null) {
         tagCache = referTag (tag, null, isAssign);
         if (key!=null)
         	simpleCache.put(key, tagCache);
      }
      tag.copy(tagCache);
      return tagCache;
   }	

	private String getCacheKey (Tag tagWhat, Tag positiveTag, Tag negativeTag, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (tagWhat!=null)
	       sb.append(tagWhat.toStringWithParents());
	    if (positiveTag!=null)
	       sb.append(positiveTag.toStringWithParents());
	    if (negativeTag!=null)
	       sb.append(negativeTag.toStringWithParents());
	    return sb.toString();
	}
	
    public Tag partialLoadWithParentFirstTag(Tag tagWhat, Tag positiveTag, Tag negativeTag){
		List <Tag> list = partialLoadWithParentTag(tagWhat, positiveTag, negativeTag);
		return (!list.isEmpty())?(Tag)list.get(0):null;
    }
    
    public Tag partialLoadWithParentFirstTagUseCache(Tag tagWhat, Tag positiveTag, Tag negativeTag, Boolean useCache){
		List <Tag> list = partialLoadWithParentTagUseCache(tagWhat, positiveTag, negativeTag, useCache);
		return (!list.isEmpty())?(Tag)list.get(0):null;
    }
	
	public Tag partialLoadWithParentFirstTagUseCacheOnResult(Tag tagWhat, Tag positiveTag, Tag negativeTag, Boolean useCache){
		List <Tag> list = partialLoadWithParentTagUseCacheOnResult(tagWhat, positiveTag, negativeTag, useCache);
		return (!list.isEmpty())?(Tag)list.get(0):null;
    }
	//
	protected List<Tag> partialLoadWithParentTag(Tag tagWhat, Tag positiveTag, Tag negativeTag, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentTag(tagWhat, positiveTag, negativeTag, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentTagQueryResult (Tag tagWhat, Tag positiveTag, Tag negativeTag, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentTagQueryResult (tagWhat, positiveTag, negativeTag, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<Tag> getDistinctTag(Tag tagWhat, Tag positiveTag, Tag negativeTag) {
		 return partialLoadWithParentTag(tagWhat, positiveTag, negativeTag, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<Tag> partialLoadWithParentTag(Tag tagWhat, Tag positiveTag, Tag negativeTag) {
		 return partialLoadWithParentTag(tagWhat, positiveTag, negativeTag, new QuerySelectInit(), null, false);
	}	
  
	public List<Tag> partialLoadWithParentTagUseCacheOnResult(Tag tagWhat, Tag positiveTag, Tag negativeTag, Boolean useCache) {
		String key = getCacheKey(tagWhat, positiveTag, negativeTag, "partialLoadWithParentTag");
		List<Tag> list = (List<Tag>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentTag(tagWhat, positiveTag, negativeTag);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<Tag> partialLoadWithParentTagUseCache(Tag tagWhat, Tag positiveTag, Tag negativeTag, Boolean useCache) {
		String key = getCacheKey(tagWhat, positiveTag, negativeTag, "partialLoadWithParentTag");
		List<Tag> list = (List<Tag>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentTag(tagWhat, positiveTag, negativeTag);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<Tag> handleLoadWithParentTag(Map beanPath, List list, Tag tagWhat) {
	    return handleLoadWithParentTag(beanPath, list, tagWhat, true);  
	}
	
	private List<Tag> handleLoadWithParentTag(Map beanPath, List list, Tag tagWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentTagWithOneElementInRow(list, beanPath, tagWhat, isHql);
		}
		return handlePartialLoadWithParentTag(list, beanPath, tagWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateTag (Tag tag, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(tag, beanPath, value);
	}
	
	protected void populateTagFromSQL (Tag tag, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(tag, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to tag.clone() instead
	private Tag cloneTag (Tag tag) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (Tag) BeanUtils.cloneBeanObject(tag);
	   if (tag==null) return new Tag();
	   return tag.clone();
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
	
    public List<Tag> countDistinct (Tag whatMask, Tag whereEqCriteria) {
       return partialLoadWithParentTag(whatMask, whereEqCriteria, null, new QuerySelectCountInit("tag"), null, false);
    }   
  	
    public Long count(Tag whereEqCriteria) {
	    return count(null, whereEqCriteria, EntityMatchType.ALL, OperandType.EQUALS, true); 
/*        Query query = getEntityManager().createQuery(getSelectCountPrototype(whereEqCriteria));
        List<Long> list = query.getResultList();
    	if (!list.isEmpty()) {
            return list.get(0);
    	}
    	return 0L;
*/
    }


    public Long count(Tag whatMask, Tag whereCriteria, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
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

	protected String countQuery (Tag tag, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        String what = "SELECT count(*) FROM Tag tag ";
		return findQuery (tag, null, what, matchType, operandType, caseSensitivenessType, null);
    }
	
    protected String getSelectCountPrototype (Tag whereEqCriteria) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT count(*) FROM Tag tag ");
        queryWhere.append (getWhereEqualWhereQueryChunk(whereEqCriteria, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }
			
   public Tag getFirstTagWhereConditionsAre (Tag tag) {
      List<Tag> list = partialLoadWithParentTag(getDefaultTagWhat(), tag, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (Tag tag) {
      return partialLoadWithParentTagQueryResult(getDefaultTagWhat(), tag, null, 1, false);	
   }
   
   protected Tag getDefaultTagWhat() {
      Tag tag = new Tag();
      tag.setTagid(Integer.valueOf(-1));
      return tag;
   }
   
	public Tag getFirstTag (Tag tag) {
		if (isAllNull(tag))
			return null;
		else {
			List<Tag> list = searchPrototype (tag, 1);
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
    * checks if the Tag entity exists
    */           
    public boolean existsTag (Tag tag) {
       if (getFirstTag(tag)!=null)
          return true;
       return false;  
    }
        
    public boolean existsTagWhereConditionsAre (Tag tag) {
       if (getFirstResultWhereConditionsAre (tag).isEmpty())
          return false;
       return true;  
    }

	private int countPartialField (Tag tag) {
	   int cpt = 0;
       if (tag.getTagid() != null) {
          cpt++;
       }
       if (tag.getTag() != null) {
          cpt++;
       }
       if (tag.getRefcount() != null) {
          cpt++;
       }
       return cpt;
	}   

	public List<Tag> partialLoadWithParentTag(Tag what, Tag positiveTag, Tag negativeTag, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentTagJPAQueryResult (what, positiveTag, negativeTag, queryWhatInit, beanPath, nbOfResult, useCache);
		return handlePartialLoadWithParentResult(what, list, beanPath);
	}
	
	public List<Tag> handlePartialLoadWithParentResult(Tag what, List list, Map beanPath) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentTagWithOneElementInRow(list, beanPath, what, true);
		}
		return handlePartialLoadWithParentTag(list, beanPath, what, true);
	}	

	private List partialLoadWithParentTagQueryResult(Tag tagWhat, Tag positiveTag, Tag negativeTag, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentTagJPAQueryResult (tagWhat, positiveTag, negativeTag, queryWhatInit, new Hashtable(), nbOfResult, useCache);
    }	
  
	private List partialLoadWithParentTagJPAQueryResult(Tag tagWhat, Tag positiveTag, Tag negativeTag, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentJPAQuery (tagWhat, positiveTag, negativeTag, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
    }	
   /**
    * @returns an JPA Hsql query based on entity Tag and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentJPAQuery (Tag tagWhat, Tag positiveTag, Tag negativeTag, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getPartialLoadWithParentJPARawQuery (tagWhat, positiveTag, negativeTag, beanPath, queryWhatInit);
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
    }
  	
   /**
    * @returns an JPA Raw Hsql query based on entity Tag and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentJPARawQuery (Tag tagWhat, Tag positiveTag, Tag negativeTag, Map beanPath, QueryWhatInit queryWhatInit) {
	   return getEntityManager().createQuery(getPartialLoadWithParentRawHsqlQuery (tagWhat, positiveTag, negativeTag, beanPath, queryWhatInit));
    }
	
	private List<Tag> handlePartialLoadWithParentTag(List<Object[]> list, Map<Integer, String> beanPath, Tag tagWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentTag(list, beanPath, tagWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentTag, message:"+ex.getMessage());
			return new ArrayList<Tag>();
		}
    }

	private List<Tag> handlePartialLoadWithParentTagWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Tag tagWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentTagWithOneElementInRow(list, beanPath, tagWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentTagWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<Tag>();
		}
    }
    	
	 private List<Tag> convertPartialLoadWithParentTag(List<Object[]> list, Map<Integer, String> beanPath, Tag tagWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Tag> resultList = new ArrayList<Tag>();
		 for (Object[] row : list) {		
		    Tag tag = cloneTag (tagWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateTag (tag, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(tag);
		 }
		 return resultList;		
	 }	
    
	 private List<Tag> convertPartialLoadWithParentTagWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Tag tagWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Tag> resultList = new ArrayList<Tag>();
		 for (Object row : list) {		
		    Tag tag = cloneTag (tagWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateTag (tag, row, (String)entry.getValue());
		    }
		    resultList.add(tag);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, Tag tagWhat, Tag positiveTag, Tag negativeTag) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentJPAQuery (tagWhat, positiveTag, negativeTag, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentRawHsqlQuery (Tag tag, Tag positiveTag, Tag negativeTag, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentTagQuery (tag, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereQuery (positiveTag, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public String findPartialLoadWithParentRawHsqlQuery (Tag whatMask, Tag criteriaMask, Map beanPath, QueryWhatInit queryWhatInit,  Tag orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentTagQuery (whatMask, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
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
	public String countPartialLoadWithParentRawHsqlQuery (Tag whatMask, Tag criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
		Map beanPath = new Hashtable();
		Hashtable aliasWhatHt = new Hashtable();
		// used to initiate the how part of the what
		getPartialLoadWithParentTagQuery (whatMask, null, aliasWhatHt, null, null, beanPath, "", new QuerySelectInit());
		String what = "select count(tag) ";
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
    	
	public String findPartialQuery (Tag whatMask, Tag criteriaMask, Tag orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Map beanPath) {
        QueryWhatInit queryWhatInit = new QuerySelectInit();
        return findPartialLoadWithParentRawHsqlQuery(whatMask, criteriaMask, beanPath, queryWhatInit, orderMask, matchType, operandType, caseSensitivenessType,  sortOrder);
    }
	
	/**
    * partial on a single entity load enables to specify the fields you want to load explicitly
    */         
	public List<Tag> partialLoadTag(Tag tag, Tag positiveTag, Tag negativeTag) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadTagQuery (tag, positiveTag, negativeTag));
		int countPartialField = countPartialField(tag);
		if (countPartialField==0) 
			return new ArrayList<Tag>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<Tag> returnList = new ArrayList<Tag>();
		while(iter.hasNext()) {
			int index = 0;
			Object[] row;
			if (countPartialField==1) {
				row = new Object[1];
				row[0] = iter.next();
				} 
			else 
				row = (Object[]) iter.next();
			Tag tagResult = new Tag();
			if (tag.getTag() != null) {
                tagResult.setTag((String) row[index]);
				index++;
			}
			if (tag.getRefcount() != null) {
                tagResult.setRefcount((Integer) row[index]);
				index++;
			}
			returnList.add(tagResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereQuery (
	   Tag criteriaMask, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias,
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
	   Tag tag, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (tag==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(tag)){
	      alias = getNextAlias (aliasWhereHt, tag);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (tag.getTagid() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".tagid = "+ tag.getTagid() + " ");
       }
       if (tag.getTag() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".tag = '"+ tag.getTag()+"' ");
       }
       if (tag.getRefcount() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".refcount = "+ tag.getRefcount() + " ");
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
	
    public static String getPartialLoadWithParentTagQuery (
	   Tag tag, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (tag==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(tag)){
	      alias = getNextAlias (aliasHt, tag);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (tag.getTagid() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"tagid");
          query.append(" "+alias+".tagid ");
       }
       if (tag.getTag() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"tag");
          query.append(" "+alias+".tag ");
       }
       if (tag.getRefcount() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"refcount");
          query.append(" "+alias+".refcount ");
       }
//       query.append(getTagSearchEqualQuery (positiveTag, negativeTag));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"tagid";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "Tag|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, Tag tag) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("tag"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "tag";
		else
			return "tag_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (Tag tag) {
	   if (tag==null)
		  return false;
       if (tag.getTagid() != null) {
	      return true;
       }
       if (tag.getTag() != null) {
	      return true;
       }
       if (tag.getRefcount() != null) {
	      return true;
       }
       return false;   
	}
	
    public String getPartialLoadTagQuery(
	   Tag tag, 
	   Tag positiveTag, 
	   Tag negativeTag) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (tag.getTagid() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" tagid ");
       }
       if (tag.getTag() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" tag ");
       }
       if (tag.getRefcount() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" refcount ");
       }
       query.append(getTagSearchEqualQuery (positiveTag, negativeTag));
	   return query.toString(); 
    }
	
	public List<Tag> searchPrototypeWithCacheTag(Tag tag) {
		SimpleCache simpleCache = new SimpleCache();
		List<Tag> list = (List<Tag>)simpleCache.get(tag.toString());
		if (list==null) {
			list = searchPrototypeTag(tag);
			simpleCache.put(tag.toString(), list);
		}
		return list;
	}

    public List<Tag> loadGraph(Tag graphMaskWhat, List<Tag> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }

	public List<Tag> loadGraphOneLevel(Tag graphMaskWhat, List<Tag> whereMask) {
		//first get roots element from where list mask
		// this brings the level 0 of the graph (root level)
 		graphMaskWhat.setTagid(graphMaskWhat.integerMask__);
		List<Tag> tags = searchPrototypeTag (whereMask);
		// for each sub level perform the search with a subquery then reassemble
		// 1. get all sublevel queries
		// 2. perform queries on the correct dao
		// 3. reassemble
		return getLoadGraphOneLevel (graphMaskWhat, tags);
	}

	private List<Tag> copy(List<Tag> inputs) {
		List<Tag> l = new ArrayList<Tag>();
		for (Tag input : inputs) {
			Tag copy = new Tag();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<Tag> getLoadGraphOneLevel (Tag graphMaskWhat, List<Tag> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<Tag> loadGraphFromParentKey (Tag graphMaskWhat, List<Tag> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		return parents;
	}
	
	private List<String> getPk(List<Tag> input) {
		List<String> s = new ArrayList<String>();
		for (Tag t : input) {
			s.add(t.getTagid()+"");
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
	public void find (QueryData<Tag> data) {
		EntityCriteria<Tag> filter = data.getEntityCriteria();
		Tag entityWhat = data.getEntityWhat();
		Tag criteriaMask = filter.getEntity();
		int start = data.getStart();
		int max = data.getMax();
		EntitySort<Tag> entitySort = data.getEntitySort();
		QuerySortOrder sortOrder = entitySort.getOrder();
		Tag sortMask = entitySort.getEntity();	

		List<Tag> results = find(entityWhat, criteriaMask, sortMask, filter.getMatchType(), filter.getOperandType(), filter.getCaseSensitivenessType(), sortOrder, start, max);
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
     * return a list of Tag entities 
     */
    public List<Tag> getList () {
        //first lightweight implementation
        return searchPrototypeTag(new Tag());
    }
    /**
     * return a list of Tag entities and sort
     */
    public List<Tag> getList (Tag orderMask, QuerySortOrder sortOrder) {
        return searchPrototype(new Tag(), orderMask, sortOrder, null);
    }
    /**
     * return a list of Tag entities and sort based on a Tag prototype
     */
    public List<Tag> list (Tag mask, Tag orderMask, QuerySortOrder sortOrder) {
        return searchPrototype(mask, orderMask, sortOrder, null);
    }

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
