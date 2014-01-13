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
	* - time      : 2014/01/11 ap. J.-C. at 23:51:20 CET
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
import net.sf.mp.demo.petshop.dao.face.pet.CategoryExtDao;
import net.sf.mp.demo.petshop.domain.pet.Category;
import net.sf.mp.demo.petshop.dao.impl.jpa.pet.CategoryJPAImpl;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@


import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import net.sf.mp.demo.petshop.dao.impl.jpa.product.MyGoodProductExtendedJPAImpl;
/**
 *
 * <p>Title: CategoryExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with CategoryExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching CategoryExtendedJPAImpl objects</p>
 *
 */
@org.springframework.stereotype.Repository(value="categoryExtDao")
public class CategoryExtendedJPAImpl extends CategoryJPAImpl implements CategoryExtDao{
    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private MyGoodProductExtendedJPAImpl mygoodproductextendedjpaimpl;
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public CategoryExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public CategoryExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a Category entity with cascade of its children
     * @param Category category
     */
    public void insertCategoryWithCascade(Category category) {
    	CategoryExtendedJPAImpl categoryextendedjpaimpl = new CategoryExtendedJPAImpl(getEntityManager());
    	categoryextendedjpaimpl.insertCategoryWithCascade(categoryextendedjpaimpl.getEntityManagerForRecursiveDao(), category);
    }
     
    public void insertCategoryWithCascade(EntityManager emForRecursiveDao, Category category) {
       insertCategory(emForRecursiveDao, category);
       if (!category.getMyGoodProducts().isEmpty()) {
          MyGoodProductExtendedJPAImpl mygoodproductextendedjpaimpl = new MyGoodProductExtendedJPAImpl (emForRecursiveDao);
          for (MyGoodProduct _myGoodProducts : category.getMyGoodProducts()) {
             mygoodproductextendedjpaimpl.insertMyGoodProductWithCascade(emForRecursiveDao, _myGoodProducts);
          }
       } 
    }
        
    /**
     * Inserts a list of Category entity with cascade of its children
     * @param List<Category> categorys
     */
    public void insertCategorysWithCascade(List<Category> categorys) {
       for (Category category : categorys) {
          insertCategoryWithCascade(category);
       }
    } 
        
    /**
     * lookup Category entity Category, criteria and max result number
     */
    public List<Category> lookupCategory(Category category, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT category FROM Category category ");
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
    
    public List<Category> lookupCategory(Category category, Criteria criteria, Integer numberOfResult) {
		return lookupCategory(category, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyCategory (Category category, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyCategoryQueryChunkPrototype (category);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
        if (category.getCategoryid() != null) {
           jpaQuery.setParameter ("categoryid", category.getCategoryid());
        }   
        if (category.getName() != null) {
           jpaQuery.setParameter ("name", category.getName());
        }   
        if (category.getDescription() != null) {
           jpaQuery.setParameter ("description", category.getDescription());
        }   
        if (category.getImageurl() != null) {
           jpaQuery.setParameter ("imageurl", category.getImageurl());
        }   
		return jpaQuery.executeUpdate();
    }
	
	public Category affectCategory (Category category) {
	    return referCategory (category, null, false);		    
	}
		
	/**
	 * Assign the first category retrieved corresponding to the category criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no category corresponding in the database. Then category is inserted and returned with its primary key(s). 
	 */
	public Category assignCategory (Category category) {
		return referCategory (category, null, true);
	}

	/**
	 * Assign the first category retrieved corresponding to the mask criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no category corresponding in the database. 
	 * Then category is inserted and returned with its primary key(s). 
	 * Mask servers usually to set unique keys or the semantic reference
	 */
    public Category assignCategory (Category category, Category mask) {
		return referCategory (category, mask, true);
	}

	public Category referCategory (Category category, Category mask, boolean isAssign) {
		category = assignBlankToNull (category);
		if (isAllNull(category))
			return null;
		else {
			List<Category> list;
			if (mask==null)
				list = searchPrototypeCategory(category);
			else
				list = searchPrototypeCategory(mask);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertCategory(category);
			    else
				   return null;
			}
			else if (list.size()==1)
				category.copy(list.get(0));
			else 
				//TODO log error
				category.copy(list.get(0));
		}
		return category;		    
	}

   public Category assignCategoryUseCache (Category category) {
      return referCategoryUseCache (category, true);
   }
      		
   public Category affectCategoryUseCache (Category category) {
      return referCategoryUseCache (category, false);
   }
      		
   public Category referCategoryUseCache (Category category, boolean isAssign) {
	  String key = getCacheKey(null, category, null, "assignCategory");
      Category categoryCache = (Category)simpleCache.get(key);
      if (categoryCache==null) {
         categoryCache = referCategory (category, null, isAssign);
         if (key!=null)
         	simpleCache.put(key, categoryCache);
      }
      category.copy(categoryCache);
      return categoryCache;
   }	

	private String getCacheKey (Category categoryWhat, Category positiveCategory, Category negativeCategory, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (categoryWhat!=null)
	       sb.append(categoryWhat.toStringWithParents());
	    if (positiveCategory!=null)
	       sb.append(positiveCategory.toStringWithParents());
	    if (negativeCategory!=null)
	       sb.append(negativeCategory.toStringWithParents());
	    return sb.toString();
	}
	
    public Category partialLoadWithParentFirstCategory(Category categoryWhat, Category positiveCategory, Category negativeCategory){
		List <Category> list = partialLoadWithParentCategory(categoryWhat, positiveCategory, negativeCategory);
		return (!list.isEmpty())?(Category)list.get(0):null;
    }
    
    public Category partialLoadWithParentFirstCategoryUseCache(Category categoryWhat, Category positiveCategory, Category negativeCategory, Boolean useCache){
		List <Category> list = partialLoadWithParentCategoryUseCache(categoryWhat, positiveCategory, negativeCategory, useCache);
		return (!list.isEmpty())?(Category)list.get(0):null;
    }
	
	public Category partialLoadWithParentFirstCategoryUseCacheOnResult(Category categoryWhat, Category positiveCategory, Category negativeCategory, Boolean useCache){
		List <Category> list = partialLoadWithParentCategoryUseCacheOnResult(categoryWhat, positiveCategory, negativeCategory, useCache);
		return (!list.isEmpty())?(Category)list.get(0):null;
    }
	//
	protected List<Category> partialLoadWithParentCategory(Category categoryWhat, Category positiveCategory, Category negativeCategory, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentCategory(categoryWhat, positiveCategory, negativeCategory, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentCategoryQueryResult (Category categoryWhat, Category positiveCategory, Category negativeCategory, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentCategoryQueryResult (categoryWhat, positiveCategory, negativeCategory, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<Category> getDistinctCategory(Category categoryWhat, Category positiveCategory, Category negativeCategory) {
		 return partialLoadWithParentCategory(categoryWhat, positiveCategory, negativeCategory, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<Category> partialLoadWithParentCategory(Category categoryWhat, Category positiveCategory, Category negativeCategory) {
		 return partialLoadWithParentCategory(categoryWhat, positiveCategory, negativeCategory, new QuerySelectInit(), null, false);
	}	
  
	public List<Category> partialLoadWithParentCategoryUseCacheOnResult(Category categoryWhat, Category positiveCategory, Category negativeCategory, Boolean useCache) {
		String key = getCacheKey(categoryWhat, positiveCategory, negativeCategory, "partialLoadWithParentCategory");
		List<Category> list = (List<Category>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentCategory(categoryWhat, positiveCategory, negativeCategory);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<Category> partialLoadWithParentCategoryUseCache(Category categoryWhat, Category positiveCategory, Category negativeCategory, Boolean useCache) {
		String key = getCacheKey(categoryWhat, positiveCategory, negativeCategory, "partialLoadWithParentCategory");
		List<Category> list = (List<Category>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentCategory(categoryWhat, positiveCategory, negativeCategory);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<Category> handleLoadWithParentCategory(Map beanPath, List list, Category categoryWhat) {
	    return handleLoadWithParentCategory(beanPath, list, categoryWhat, true);  
	}
	
	private List<Category> handleLoadWithParentCategory(Map beanPath, List list, Category categoryWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentCategoryWithOneElementInRow(list, beanPath, categoryWhat, isHql);
		}
		return handlePartialLoadWithParentCategory(list, beanPath, categoryWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateCategory (Category category, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(category, beanPath, value);
	}
	
	protected void populateCategoryFromSQL (Category category, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(category, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to category.clone() instead
	private Category cloneCategory (Category category) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (Category) BeanUtils.cloneBeanObject(category);
	   if (category==null) return new Category();
	   return category.clone();
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
	
    public List<Category> countDistinct (Category whatMask, Category whereEqCriteria) {
       return partialLoadWithParentCategory(whatMask, whereEqCriteria, null, new QuerySelectCountInit("category"), null, false);
    }   
  	
    public Long count(Category whereEqCriteria) {
	    return count(null, whereEqCriteria, EntityMatchType.ALL, OperandType.EQUALS, true); 
/*        Query query = getEntityManager().createQuery(getSelectCountPrototype(whereEqCriteria));
        List<Long> list = query.getResultList();
    	if (!list.isEmpty()) {
            return list.get(0);
    	}
    	return 0L;
*/
    }


    public Long count(Category whatMask, Category whereCriteria, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
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

	protected String countQuery (Category category, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        String what = "SELECT count(*) FROM Category category ";
		return findQuery (category, null, what, matchType, operandType, caseSensitivenessType, null);
    }
	
    protected String getSelectCountPrototype (Category whereEqCriteria) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT count(*) FROM Category category ");
        queryWhere.append (getWhereEqualWhereQueryChunk(whereEqCriteria, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }
			
   public Category getFirstCategoryWhereConditionsAre (Category category) {
      List<Category> list = partialLoadWithParentCategory(getDefaultCategoryWhat(), category, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (Category category) {
      return partialLoadWithParentCategoryQueryResult(getDefaultCategoryWhat(), category, null, 1, false);	
   }
   
   protected Category getDefaultCategoryWhat() {
      Category category = new Category();
      category.setCategoryid(new String());
      return category;
   }
   
	public Category getFirstCategory (Category category) {
		if (isAllNull(category))
			return null;
		else {
			List<Category> list = searchPrototype (category, 1);
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
    * checks if the Category entity exists
    */           
    public boolean existsCategory (Category category) {
       if (getFirstCategory(category)!=null)
          return true;
       return false;  
    }
        
    public boolean existsCategoryWhereConditionsAre (Category category) {
       if (getFirstResultWhereConditionsAre (category).isEmpty())
          return false;
       return true;  
    }

	private int countPartialField (Category category) {
	   int cpt = 0;
       if (category.getCategoryid() != null) {
          cpt++;
       }
       if (category.getName() != null) {
          cpt++;
       }
       if (category.getDescription() != null) {
          cpt++;
       }
       if (category.getImageurl() != null) {
          cpt++;
       }
       return cpt;
	}   

	public List<Category> partialLoadWithParentCategory(Category what, Category positiveCategory, Category negativeCategory, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentCategoryJPAQueryResult (what, positiveCategory, negativeCategory, queryWhatInit, beanPath, nbOfResult, useCache);
		return handlePartialLoadWithParentResult(what, list, beanPath);
	}
	
	public List<Category> handlePartialLoadWithParentResult(Category what, List list, Map beanPath) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentCategoryWithOneElementInRow(list, beanPath, what, true);
		}
		return handlePartialLoadWithParentCategory(list, beanPath, what, true);
	}	

	private List partialLoadWithParentCategoryQueryResult(Category categoryWhat, Category positiveCategory, Category negativeCategory, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentCategoryJPAQueryResult (categoryWhat, positiveCategory, negativeCategory, queryWhatInit, new Hashtable(), nbOfResult, useCache);
    }	
  
	private List partialLoadWithParentCategoryJPAQueryResult(Category categoryWhat, Category positiveCategory, Category negativeCategory, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentJPAQuery (categoryWhat, positiveCategory, negativeCategory, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
    }	
   /**
    * @returns an JPA Hsql query based on entity Category and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentJPAQuery (Category categoryWhat, Category positiveCategory, Category negativeCategory, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getPartialLoadWithParentJPARawQuery (categoryWhat, positiveCategory, negativeCategory, beanPath, queryWhatInit);
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
    }
  	
   /**
    * @returns an JPA Raw Hsql query based on entity Category and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentJPARawQuery (Category categoryWhat, Category positiveCategory, Category negativeCategory, Map beanPath, QueryWhatInit queryWhatInit) {
	   return getEntityManager().createQuery(getPartialLoadWithParentRawHsqlQuery (categoryWhat, positiveCategory, negativeCategory, beanPath, queryWhatInit));
    }
	
	private List<Category> handlePartialLoadWithParentCategory(List<Object[]> list, Map<Integer, String> beanPath, Category categoryWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentCategory(list, beanPath, categoryWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentCategory, message:"+ex.getMessage());
			return new ArrayList<Category>();
		}
    }

	private List<Category> handlePartialLoadWithParentCategoryWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Category categoryWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentCategoryWithOneElementInRow(list, beanPath, categoryWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentCategoryWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<Category>();
		}
    }
    	
	 private List<Category> convertPartialLoadWithParentCategory(List<Object[]> list, Map<Integer, String> beanPath, Category categoryWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Category> resultList = new ArrayList<Category>();
		 for (Object[] row : list) {		
		    Category category = cloneCategory (categoryWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateCategory (category, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(category);
		 }
		 return resultList;		
	 }	
    
	 private List<Category> convertPartialLoadWithParentCategoryWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Category categoryWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Category> resultList = new ArrayList<Category>();
		 for (Object row : list) {		
		    Category category = cloneCategory (categoryWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateCategory (category, row, (String)entry.getValue());
		    }
		    resultList.add(category);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, Category categoryWhat, Category positiveCategory, Category negativeCategory) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentJPAQuery (categoryWhat, positiveCategory, negativeCategory, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentRawHsqlQuery (Category category, Category positiveCategory, Category negativeCategory, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentCategoryQuery (category, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereQuery (positiveCategory, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public String findPartialLoadWithParentRawHsqlQuery (Category whatMask, Category criteriaMask, Map beanPath, QueryWhatInit queryWhatInit,  Category orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentCategoryQuery (whatMask, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
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
	public String countPartialLoadWithParentRawHsqlQuery (Category whatMask, Category criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
		Map beanPath = new Hashtable();
		Hashtable aliasWhatHt = new Hashtable();
		// used to initiate the how part of the what
		getPartialLoadWithParentCategoryQuery (whatMask, null, aliasWhatHt, null, null, beanPath, "", new QuerySelectInit());
		String what = "select count(category) ";
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
    	
	public String findPartialQuery (Category whatMask, Category criteriaMask, Category orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Map beanPath) {
        QueryWhatInit queryWhatInit = new QuerySelectInit();
        return findPartialLoadWithParentRawHsqlQuery(whatMask, criteriaMask, beanPath, queryWhatInit, orderMask, matchType, operandType, caseSensitivenessType,  sortOrder);
    }
	
	/**
    * partial on a single entity load enables to specify the fields you want to load explicitly
    */         
	public List<Category> partialLoadCategory(Category category, Category positiveCategory, Category negativeCategory) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadCategoryQuery (category, positiveCategory, negativeCategory));
		int countPartialField = countPartialField(category);
		if (countPartialField==0) 
			return new ArrayList<Category>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<Category> returnList = new ArrayList<Category>();
		while(iter.hasNext()) {
			int index = 0;
			Object[] row;
			if (countPartialField==1) {
				row = new Object[1];
				row[0] = iter.next();
				} 
			else 
				row = (Object[]) iter.next();
			Category categoryResult = new Category();
			if (category.getName() != null) {
                categoryResult.setName((String) row[index]);
				index++;
			}
			if (category.getDescription() != null) {
                categoryResult.setDescription((String) row[index]);
				index++;
			}
			if (category.getImageurl() != null) {
                categoryResult.setImageurl((String) row[index]);
				index++;
			}
			returnList.add(categoryResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereQuery (
	   Category criteriaMask, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias,
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
	   Category category, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (category==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(category)){
	      alias = getNextAlias (aliasWhereHt, category);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (category.getCategoryid() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".categoryid = '"+ category.getCategoryid()+"' ");
       }
       if (category.getName() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".name = '"+ category.getName()+"' ");
       }
       if (category.getDescription() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".description = '"+ category.getDescription()+"' ");
       }
       if (category.getImageurl() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".imageurl = '"+ category.getImageurl()+"' ");
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
	
    public static String getPartialLoadWithParentCategoryQuery (
	   Category category, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (category==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(category)){
	      alias = getNextAlias (aliasHt, category);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (category.getCategoryid() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"categoryid");
          query.append(" "+alias+".categoryid ");
       }
       if (category.getName() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"name");
          query.append(" "+alias+".name ");
       }
       if (category.getDescription() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"description");
          query.append(" "+alias+".description ");
       }
       if (category.getImageurl() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"imageurl");
          query.append(" "+alias+".imageurl ");
       }
//       query.append(getCategorySearchEqualQuery (positiveCategory, negativeCategory));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"categoryid";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "Category|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, Category category) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("category"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "category";
		else
			return "category_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (Category category) {
	   if (category==null)
		  return false;
       if (category.getCategoryid() != null) {
	      return true;
       }
       if (category.getName() != null) {
	      return true;
       }
       if (category.getDescription() != null) {
	      return true;
       }
       if (category.getImageurl() != null) {
	      return true;
       }
       return false;   
	}
	
    public String getPartialLoadCategoryQuery(
	   Category category, 
	   Category positiveCategory, 
	   Category negativeCategory) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (category.getCategoryid() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" categoryid ");
       }
       if (category.getName() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" name ");
       }
       if (category.getDescription() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" description ");
       }
       if (category.getImageurl() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" imageurl ");
       }
       query.append(getCategorySearchEqualQuery (positiveCategory, negativeCategory));
	   return query.toString(); 
    }
	
	public List<Category> searchPrototypeWithCacheCategory(Category category) {
		SimpleCache simpleCache = new SimpleCache();
		List<Category> list = (List<Category>)simpleCache.get(category.toString());
		if (list==null) {
			list = searchPrototypeCategory(category);
			simpleCache.put(category.toString(), list);
		}
		return list;
	}

    public List<Category> loadGraph(Category graphMaskWhat, List<Category> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }

	public List<Category> loadGraphOneLevel(Category graphMaskWhat, List<Category> whereMask) {
		//first get roots element from where list mask
		// this brings the level 0 of the graph (root level)
 		graphMaskWhat.setCategoryid(graphMaskWhat.stringMask__);
		List<Category> categorys = searchPrototypeCategory (whereMask);
		// for each sub level perform the search with a subquery then reassemble
		// 1. get all sublevel queries
		// 2. perform queries on the correct dao
		// 3. reassemble
		return getLoadGraphOneLevel (graphMaskWhat, categorys);
	}

	private List<Category> copy(List<Category> inputs) {
		List<Category> l = new ArrayList<Category>();
		for (Category input : inputs) {
			Category copy = new Category();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<Category> getLoadGraphOneLevel (Category graphMaskWhat, List<Category> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<Category> loadGraphFromParentKey (Category graphMaskWhat, List<Category> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		if (graphMaskWhat.getMyGoodProducts()!=null && !graphMaskWhat.getMyGoodProducts().isEmpty()) {
			for (MyGoodProduct childWhat : graphMaskWhat.getMyGoodProducts()) {
				childWhat.setCategoryid_(graphMaskWhat.stringMask__); // add to the what mask, usefull for reconciliation
				MyGoodProductExtendedJPAImpl mygoodproductextendedjpaimpl = new MyGoodProductExtendedJPAImpl ();
				List<MyGoodProduct> children = mygoodproductextendedjpaimpl.lookupMyGoodProduct(childWhat, getFkCriteria(" categoryid ", ids), null, getEntityManager());
				reassembleMyGoodProduct (children, parents);				
				break;
			}
		}
		return parents;
	}
	
	private void reassembleMyGoodProduct (List<MyGoodProduct> children, List<Category> parents) {
		for (MyGoodProduct child : children) {
			for (Category parent : parents) {
				if (parent.getCategoryid()!=null && parent.getCategoryid().toString().equals(child.getCategoryid()+"")) {
					parent.addMyGoodProducts(child); 
					child.setCategoryid(parent);
					break;
				}
			}
		}
	}
	
	private List<String> getPk(List<Category> input) {
		List<String> s = new ArrayList<String>();
		for (Category t : input) {
			s.add(t.getCategoryid()+"");
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
	public void find (QueryData<Category> data) {
		EntityCriteria<Category> filter = data.getEntityCriteria();
		Category entityWhat = data.getEntityWhat();
		Category criteriaMask = filter.getEntity();
		int start = data.getStart();
		int max = data.getMax();
		EntitySort<Category> entitySort = data.getEntitySort();
		QuerySortOrder sortOrder = entitySort.getOrder();
		Category sortMask = entitySort.getEntity();	

		List<Category> results = find(entityWhat, criteriaMask, sortMask, filter.getMatchType(), filter.getOperandType(), filter.getCaseSensitivenessType(), sortOrder, start, max);
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
	
    public void setMyGoodProductExtendedJPAImpl (MyGoodProductExtendedJPAImpl mygoodproductextendedjpaimpl) {
       this.mygoodproductextendedjpaimpl = mygoodproductextendedjpaimpl;
    }
    
    public MyGoodProductExtendedJPAImpl getMyGoodProductExtendedJPAImpl () {
       return mygoodproductextendedjpaimpl;
    }
    

    /**
     * return a list of Category entities 
     */
    public List<Category> getList () {
        //first lightweight implementation
        return searchPrototypeCategory(new Category());
    }
    /**
     * return a list of Category entities and sort
     */
    public List<Category> getList (Category orderMask, QuerySortOrder sortOrder) {
        return searchPrototype(new Category(), orderMask, sortOrder, null);
    }
    /**
     * return a list of Category entities and sort based on a Category prototype
     */
    public List<Category> list (Category mask, Category orderMask, QuerySortOrder sortOrder) {
        return searchPrototype(mask, orderMask, sortOrder, null);
    }

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
