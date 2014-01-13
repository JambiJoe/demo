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
package net.sf.mp.demo.petshop.dao.impl.jpa.product;

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
import net.sf.mp.demo.petshop.dao.face.product.MyGoodItemExtDao;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.mp.demo.petshop.dao.impl.jpa.product.MyGoodItemJPAImpl;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@


import net.sf.mp.demo.petshop.dao.impl.jpa.pet.AddressExtendedJPAImpl;
import net.sf.mp.demo.petshop.dao.impl.jpa.product.MyGoodProductExtendedJPAImpl;
import net.sf.mp.demo.petshop.dao.impl.jpa.pet.SellercontactinfoExtendedJPAImpl;
/**
 *
 * <p>Title: MyGoodItemExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with MyGoodItemExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching MyGoodItemExtendedJPAImpl objects</p>
 *
 */
@org.springframework.stereotype.Repository(value="myGoodItemExtDao")
public class MyGoodItemExtendedJPAImpl extends MyGoodItemJPAImpl implements MyGoodItemExtDao{
    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public MyGoodItemExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public MyGoodItemExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a MyGoodItem entity with cascade of its children
     * @param MyGoodItem mygooditem
     */
    public void insertMyGoodItemWithCascade(MyGoodItem mygooditem) {
    	MyGoodItemExtendedJPAImpl mygooditemextendedjpaimpl = new MyGoodItemExtendedJPAImpl(getEntityManager());
    	mygooditemextendedjpaimpl.insertMyGoodItemWithCascade(mygooditemextendedjpaimpl.getEntityManagerForRecursiveDao(), mygooditem);
    }
     
    public void insertMyGoodItemWithCascade(EntityManager emForRecursiveDao, MyGoodItem mygooditem) {
       insertMyGoodItem(emForRecursiveDao, mygooditem);
    }
        
    /**
     * Inserts a list of MyGoodItem entity with cascade of its children
     * @param List<MyGoodItem> mygooditems
     */
    public void insertMyGoodItemsWithCascade(List<MyGoodItem> mygooditems) {
       for (MyGoodItem mygooditem : mygooditems) {
          insertMyGoodItemWithCascade(mygooditem);
       }
    } 
        
    /**
     * lookup MyGoodItem entity MyGoodItem, criteria and max result number
     */
    public List<MyGoodItem> lookupMyGoodItem(MyGoodItem mygooditem, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT mygooditem FROM MyGoodItem mygooditem ");
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
    
    public List<MyGoodItem> lookupMyGoodItem(MyGoodItem mygooditem, Criteria criteria, Integer numberOfResult) {
		return lookupMyGoodItem(mygooditem, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyMyGoodItem (MyGoodItem mygooditem, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyMyGoodItemQueryChunkPrototype (mygooditem);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
        if (mygooditem.getItemid() != null) {
           jpaQuery.setParameter ("itemid", mygooditem.getItemid());
        }   
        if (mygooditem.getThisIsMyProduct() != null) {
           jpaQuery.setParameter ("thisIsMyProduct", mygooditem.getThisIsMyProduct());
        }   
        if (mygooditem.getName() != null) {
           jpaQuery.setParameter ("name", mygooditem.getName());
        }   
        if (mygooditem.getDescription() != null) {
           jpaQuery.setParameter ("description", mygooditem.getDescription());
        }   
        if (mygooditem.getImageurl() != null) {
           jpaQuery.setParameter ("imageurl", mygooditem.getImageurl());
        }   
        if (mygooditem.getImagethumburl() != null) {
           jpaQuery.setParameter ("imagethumburl", mygooditem.getImagethumburl());
        }   
        if (mygooditem.getPrice() != null) {
           jpaQuery.setParameter ("price", mygooditem.getPrice());
        }   
        if (mygooditem.getAddressAddressid() != null) {
           jpaQuery.setParameter ("addressAddressid", mygooditem.getAddressAddressid());
        }   
        if (mygooditem.getContactinfoContactinfoid() != null) {
           jpaQuery.setParameter ("contactinfoContactinfoid", mygooditem.getContactinfoContactinfoid());
        }   
        if (mygooditem.getTotalscore() != null) {
           jpaQuery.setParameter ("totalscore", mygooditem.getTotalscore());
        }   
        if (mygooditem.getNumberofvotes() != null) {
           jpaQuery.setParameter ("numberofvotes", mygooditem.getNumberofvotes());
        }   
        if (mygooditem.getDisabled() != null) {
           jpaQuery.setParameter ("disabled", mygooditem.getDisabled());
        }   
		return jpaQuery.executeUpdate();
    }
	
	public MyGoodItem affectMyGoodItem (MyGoodItem mygooditem) {
	    return referMyGoodItem (mygooditem, null, false);		    
	}
		
	/**
	 * Assign the first mygooditem retrieved corresponding to the mygooditem criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no mygooditem corresponding in the database. Then mygooditem is inserted and returned with its primary key(s). 
	 */
	public MyGoodItem assignMyGoodItem (MyGoodItem mygooditem) {
		return referMyGoodItem (mygooditem, null, true);
	}

	/**
	 * Assign the first mygooditem retrieved corresponding to the mask criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no mygooditem corresponding in the database. 
	 * Then mygooditem is inserted and returned with its primary key(s). 
	 * Mask servers usually to set unique keys or the semantic reference
	 */
    public MyGoodItem assignMyGoodItem (MyGoodItem mygooditem, MyGoodItem mask) {
		return referMyGoodItem (mygooditem, mask, true);
	}

	public MyGoodItem referMyGoodItem (MyGoodItem mygooditem, MyGoodItem mask, boolean isAssign) {
		mygooditem = assignBlankToNull (mygooditem);
		if (isAllNull(mygooditem))
			return null;
		else {
			List<MyGoodItem> list;
			if (mask==null)
				list = searchPrototypeMyGoodItem(mygooditem);
			else
				list = searchPrototypeMyGoodItem(mask);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertMyGoodItem(mygooditem);
			    else
				   return null;
			}
			else if (list.size()==1)
				mygooditem.copy(list.get(0));
			else 
				//TODO log error
				mygooditem.copy(list.get(0));
		}
		return mygooditem;		    
	}

   public MyGoodItem assignMyGoodItemUseCache (MyGoodItem mygooditem) {
      return referMyGoodItemUseCache (mygooditem, true);
   }
      		
   public MyGoodItem affectMyGoodItemUseCache (MyGoodItem mygooditem) {
      return referMyGoodItemUseCache (mygooditem, false);
   }
      		
   public MyGoodItem referMyGoodItemUseCache (MyGoodItem mygooditem, boolean isAssign) {
	  String key = getCacheKey(null, mygooditem, null, "assignMyGoodItem");
      MyGoodItem mygooditemCache = (MyGoodItem)simpleCache.get(key);
      if (mygooditemCache==null) {
         mygooditemCache = referMyGoodItem (mygooditem, null, isAssign);
         if (key!=null)
         	simpleCache.put(key, mygooditemCache);
      }
      mygooditem.copy(mygooditemCache);
      return mygooditemCache;
   }	

	private String getCacheKey (MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (mygooditemWhat!=null)
	       sb.append(mygooditemWhat.toStringWithParents());
	    if (positiveMyGoodItem!=null)
	       sb.append(positiveMyGoodItem.toStringWithParents());
	    if (negativeMyGoodItem!=null)
	       sb.append(negativeMyGoodItem.toStringWithParents());
	    return sb.toString();
	}
	
    public MyGoodItem partialLoadWithParentFirstMyGoodItem(MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem){
		List <MyGoodItem> list = partialLoadWithParentMyGoodItem(mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem);
		return (!list.isEmpty())?(MyGoodItem)list.get(0):null;
    }
    
    public MyGoodItem partialLoadWithParentFirstMyGoodItemUseCache(MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, Boolean useCache){
		List <MyGoodItem> list = partialLoadWithParentMyGoodItemUseCache(mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem, useCache);
		return (!list.isEmpty())?(MyGoodItem)list.get(0):null;
    }
	
	public MyGoodItem partialLoadWithParentFirstMyGoodItemUseCacheOnResult(MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, Boolean useCache){
		List <MyGoodItem> list = partialLoadWithParentMyGoodItemUseCacheOnResult(mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem, useCache);
		return (!list.isEmpty())?(MyGoodItem)list.get(0):null;
    }
	//
	protected List<MyGoodItem> partialLoadWithParentMyGoodItem(MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentMyGoodItem(mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentMyGoodItemQueryResult (MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentMyGoodItemQueryResult (mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<MyGoodItem> getDistinctMyGoodItem(MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem) {
		 return partialLoadWithParentMyGoodItem(mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<MyGoodItem> partialLoadWithParentMyGoodItem(MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem) {
		 return partialLoadWithParentMyGoodItem(mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem, new QuerySelectInit(), null, false);
	}	
  
	public List<MyGoodItem> partialLoadWithParentMyGoodItemUseCacheOnResult(MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, Boolean useCache) {
		String key = getCacheKey(mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem, "partialLoadWithParentMyGoodItem");
		List<MyGoodItem> list = (List<MyGoodItem>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentMyGoodItem(mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<MyGoodItem> partialLoadWithParentMyGoodItemUseCache(MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, Boolean useCache) {
		String key = getCacheKey(mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem, "partialLoadWithParentMyGoodItem");
		List<MyGoodItem> list = (List<MyGoodItem>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentMyGoodItem(mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<MyGoodItem> handleLoadWithParentMyGoodItem(Map beanPath, List list, MyGoodItem mygooditemWhat) {
	    return handleLoadWithParentMyGoodItem(beanPath, list, mygooditemWhat, true);  
	}
	
	private List<MyGoodItem> handleLoadWithParentMyGoodItem(Map beanPath, List list, MyGoodItem mygooditemWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentMyGoodItemWithOneElementInRow(list, beanPath, mygooditemWhat, isHql);
		}
		return handlePartialLoadWithParentMyGoodItem(list, beanPath, mygooditemWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateMyGoodItem (MyGoodItem mygooditem, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(mygooditem, beanPath, value);
	}
	
	protected void populateMyGoodItemFromSQL (MyGoodItem mygooditem, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(mygooditem, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to mygooditem.clone() instead
	private MyGoodItem cloneMyGoodItem (MyGoodItem mygooditem) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (MyGoodItem) BeanUtils.cloneBeanObject(mygooditem);
	   if (mygooditem==null) return new MyGoodItem();
	   return mygooditem.clone();
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
	
    public List<MyGoodItem> countDistinct (MyGoodItem whatMask, MyGoodItem whereEqCriteria) {
       return partialLoadWithParentMyGoodItem(whatMask, whereEqCriteria, null, new QuerySelectCountInit("mygooditem"), null, false);
    }   
  	
    public Long count(MyGoodItem whereEqCriteria) {
	    return count(null, whereEqCriteria, EntityMatchType.ALL, OperandType.EQUALS, true); 
/*        Query query = getEntityManager().createQuery(getSelectCountPrototype(whereEqCriteria));
        List<Long> list = query.getResultList();
    	if (!list.isEmpty()) {
            return list.get(0);
    	}
    	return 0L;
*/
    }


    public Long count(MyGoodItem whatMask, MyGoodItem whereCriteria, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
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

	protected String countQuery (MyGoodItem mygooditem, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        String what = "SELECT count(*) FROM MyGoodItem mygooditem ";
		return findQuery (mygooditem, null, what, matchType, operandType, caseSensitivenessType, null);
    }
	
    protected String getSelectCountPrototype (MyGoodItem whereEqCriteria) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT count(*) FROM MyGoodItem mygooditem ");
        queryWhere.append (getWhereEqualWhereQueryChunk(whereEqCriteria, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }
			
   public MyGoodItem getFirstMyGoodItemWhereConditionsAre (MyGoodItem mygooditem) {
      List<MyGoodItem> list = partialLoadWithParentMyGoodItem(getDefaultMyGoodItemWhat(), mygooditem, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (MyGoodItem mygooditem) {
      return partialLoadWithParentMyGoodItemQueryResult(getDefaultMyGoodItemWhat(), mygooditem, null, 1, false);	
   }
   
   protected MyGoodItem getDefaultMyGoodItemWhat() {
      MyGoodItem mygooditem = new MyGoodItem();
      mygooditem.setItemid(Integer.valueOf(-1));
      return mygooditem;
   }
   
	public MyGoodItem getFirstMyGoodItem (MyGoodItem mygooditem) {
		if (isAllNull(mygooditem))
			return null;
		else {
			List<MyGoodItem> list = searchPrototype (mygooditem, 1);
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
    * checks if the MyGoodItem entity exists
    */           
    public boolean existsMyGoodItem (MyGoodItem mygooditem) {
       if (getFirstMyGoodItem(mygooditem)!=null)
          return true;
       return false;  
    }
        
    public boolean existsMyGoodItemWhereConditionsAre (MyGoodItem mygooditem) {
       if (getFirstResultWhereConditionsAre (mygooditem).isEmpty())
          return false;
       return true;  
    }

	private int countPartialField (MyGoodItem mygooditem) {
	   int cpt = 0;
       if (mygooditem.getItemid() != null) {
          cpt++;
       }
       if (mygooditem.getThisIsMyProduct() != null) {
          cpt++;
       }
       if (mygooditem.getName() != null) {
          cpt++;
       }
       if (mygooditem.getDescription() != null) {
          cpt++;
       }
       if (mygooditem.getImageurl() != null) {
          cpt++;
       }
       if (mygooditem.getImagethumburl() != null) {
          cpt++;
       }
       if (mygooditem.getPrice() != null) {
          cpt++;
       }
       if (mygooditem.getAddressAddressid() != null) {
          cpt++;
       }
       if (mygooditem.getContactinfoContactinfoid() != null) {
          cpt++;
       }
       if (mygooditem.getTotalscore() != null) {
          cpt++;
       }
       if (mygooditem.getNumberofvotes() != null) {
          cpt++;
       }
       if (mygooditem.getDisabled() != null) {
          cpt++;
       }
       return cpt;
	}   

	public List<MyGoodItem> partialLoadWithParentMyGoodItem(MyGoodItem what, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentMyGoodItemJPAQueryResult (what, positiveMyGoodItem, negativeMyGoodItem, queryWhatInit, beanPath, nbOfResult, useCache);
		return handlePartialLoadWithParentResult(what, list, beanPath);
	}
	
	public List<MyGoodItem> handlePartialLoadWithParentResult(MyGoodItem what, List list, Map beanPath) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentMyGoodItemWithOneElementInRow(list, beanPath, what, true);
		}
		return handlePartialLoadWithParentMyGoodItem(list, beanPath, what, true);
	}	

	private List partialLoadWithParentMyGoodItemQueryResult(MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentMyGoodItemJPAQueryResult (mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem, queryWhatInit, new Hashtable(), nbOfResult, useCache);
    }	
  
	private List partialLoadWithParentMyGoodItemJPAQueryResult(MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentJPAQuery (mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
    }	
   /**
    * @returns an JPA Hsql query based on entity MyGoodItem and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentJPAQuery (MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getPartialLoadWithParentJPARawQuery (mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem, beanPath, queryWhatInit);
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
    }
  	
   /**
    * @returns an JPA Raw Hsql query based on entity MyGoodItem and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentJPARawQuery (MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, Map beanPath, QueryWhatInit queryWhatInit) {
	   return getEntityManager().createQuery(getPartialLoadWithParentRawHsqlQuery (mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem, beanPath, queryWhatInit));
    }
	
	private List<MyGoodItem> handlePartialLoadWithParentMyGoodItem(List<Object[]> list, Map<Integer, String> beanPath, MyGoodItem mygooditemWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentMyGoodItem(list, beanPath, mygooditemWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentMyGoodItem, message:"+ex.getMessage());
			return new ArrayList<MyGoodItem>();
		}
    }

	private List<MyGoodItem> handlePartialLoadWithParentMyGoodItemWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, MyGoodItem mygooditemWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentMyGoodItemWithOneElementInRow(list, beanPath, mygooditemWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentMyGoodItemWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<MyGoodItem>();
		}
    }
    	
	 private List<MyGoodItem> convertPartialLoadWithParentMyGoodItem(List<Object[]> list, Map<Integer, String> beanPath, MyGoodItem mygooditemWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<MyGoodItem> resultList = new ArrayList<MyGoodItem>();
		 for (Object[] row : list) {		
		    MyGoodItem mygooditem = cloneMyGoodItem (mygooditemWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateMyGoodItem (mygooditem, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(mygooditem);
		 }
		 return resultList;		
	 }	
    
	 private List<MyGoodItem> convertPartialLoadWithParentMyGoodItemWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, MyGoodItem mygooditemWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<MyGoodItem> resultList = new ArrayList<MyGoodItem>();
		 for (Object row : list) {		
		    MyGoodItem mygooditem = cloneMyGoodItem (mygooditemWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateMyGoodItem (mygooditem, row, (String)entry.getValue());
		    }
		    resultList.add(mygooditem);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, MyGoodItem mygooditemWhat, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentJPAQuery (mygooditemWhat, positiveMyGoodItem, negativeMyGoodItem, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentRawHsqlQuery (MyGoodItem mygooditem, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentMyGoodItemQuery (mygooditem, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereQuery (positiveMyGoodItem, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public String findPartialLoadWithParentRawHsqlQuery (MyGoodItem whatMask, MyGoodItem criteriaMask, Map beanPath, QueryWhatInit queryWhatInit,  MyGoodItem orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentMyGoodItemQuery (whatMask, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
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
	public String countPartialLoadWithParentRawHsqlQuery (MyGoodItem whatMask, MyGoodItem criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
		Map beanPath = new Hashtable();
		Hashtable aliasWhatHt = new Hashtable();
		// used to initiate the how part of the what
		getPartialLoadWithParentMyGoodItemQuery (whatMask, null, aliasWhatHt, null, null, beanPath, "", new QuerySelectInit());
		String what = "select count(mygooditem) ";
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
    	
	public String findPartialQuery (MyGoodItem whatMask, MyGoodItem criteriaMask, MyGoodItem orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Map beanPath) {
        QueryWhatInit queryWhatInit = new QuerySelectInit();
        return findPartialLoadWithParentRawHsqlQuery(whatMask, criteriaMask, beanPath, queryWhatInit, orderMask, matchType, operandType, caseSensitivenessType,  sortOrder);
    }
	
	/**
    * partial on a single entity load enables to specify the fields you want to load explicitly
    */         
	public List<MyGoodItem> partialLoadMyGoodItem(MyGoodItem mygooditem, MyGoodItem positiveMyGoodItem, MyGoodItem negativeMyGoodItem) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadMyGoodItemQuery (mygooditem, positiveMyGoodItem, negativeMyGoodItem));
		int countPartialField = countPartialField(mygooditem);
		if (countPartialField==0) 
			return new ArrayList<MyGoodItem>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<MyGoodItem> returnList = new ArrayList<MyGoodItem>();
		while(iter.hasNext()) {
			int index = 0;
			Object[] row;
			if (countPartialField==1) {
				row = new Object[1];
				row[0] = iter.next();
				} 
			else 
				row = (Object[]) iter.next();
			MyGoodItem mygooditemResult = new MyGoodItem();
			if (mygooditem.getThisIsMyProduct_() != null) {
                mygooditemResult.setThisIsMyProduct_((String) row[index]);
				index++;
			}
			if (mygooditem.getName() != null) {
                mygooditemResult.setName((String) row[index]);
				index++;
			}
			if (mygooditem.getDescription() != null) {
                mygooditemResult.setDescription((String) row[index]);
				index++;
			}
			if (mygooditem.getImageurl() != null) {
                mygooditemResult.setImageurl((String) row[index]);
				index++;
			}
			if (mygooditem.getImagethumburl() != null) {
                mygooditemResult.setImagethumburl((String) row[index]);
				index++;
			}
			if (mygooditem.getPrice() != null) {
                mygooditemResult.setPrice((java.math.BigDecimal) row[index]);
				index++;
			}
			if (mygooditem.getAddressAddressid_() != null) {
                mygooditemResult.setAddressAddressid_((Integer) row[index]);
				index++;
			}
			if (mygooditem.getContactinfoContactinfoid_() != null) {
                mygooditemResult.setContactinfoContactinfoid_((Integer) row[index]);
				index++;
			}
			if (mygooditem.getTotalscore() != null) {
                mygooditemResult.setTotalscore((Integer) row[index]);
				index++;
			}
			if (mygooditem.getNumberofvotes() != null) {
                mygooditemResult.setNumberofvotes((Integer) row[index]);
				index++;
			}
			if (mygooditem.getDisabled() != null) {
                mygooditemResult.setDisabled((Integer) row[index]);
				index++;
			}
			returnList.add(mygooditemResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereQuery (
	   MyGoodItem criteriaMask, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias,
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
	   MyGoodItem mygooditem, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (mygooditem==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(mygooditem)){
	      alias = getNextAlias (aliasWhereHt, mygooditem);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (mygooditem.getItemid() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".itemid = "+ mygooditem.getItemid() + " ");
       }
       if (mygooditem.getThisIsMyProduct() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".thisIsMyProduct_ = '"+ mygooditem.getThisIsMyProduct_()+"' ");
       }
       if (mygooditem.getName() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".name = '"+ mygooditem.getName()+"' ");
       }
       if (mygooditem.getDescription() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".description = '"+ mygooditem.getDescription()+"' ");
       }
       if (mygooditem.getImageurl() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".imageurl = '"+ mygooditem.getImageurl()+"' ");
       }
       if (mygooditem.getImagethumburl() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".imagethumburl = '"+ mygooditem.getImagethumburl()+"' ");
       }
       if (mygooditem.getPrice() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".price = "+ mygooditem.getPrice() + " ");
       }
       if (mygooditem.getAddressAddressid() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".addressAddressid = "+ mygooditem.getAddressAddressid() + " ");
       }
       if (mygooditem.getContactinfoContactinfoid() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".contactinfoContactinfoid = "+ mygooditem.getContactinfoContactinfoid() + " ");
       }
       if (mygooditem.getTotalscore() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".totalscore = "+ mygooditem.getTotalscore() + " ");
       }
       if (mygooditem.getNumberofvotes() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".numberofvotes = "+ mygooditem.getNumberofvotes() + " ");
       }
       if (mygooditem.getDisabled() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".disabled = "+ mygooditem.getDisabled() + " ");
       }
       if (mygooditem.getAddressAddressid()!=null) {
	      String chunck = net.sf.mp.demo.petshop.dao.impl.jpa.pet.AddressExtendedJPAImpl.getPartialLoadWithParentWhereQuery(
		      mygooditem.getAddressAddressid(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "addressAddressid");
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  }  	  
	   }
       if (mygooditem.getThisIsMyProduct()!=null) {
	      String chunck = net.sf.mp.demo.petshop.dao.impl.jpa.product.MyGoodProductExtendedJPAImpl.getPartialLoadWithParentWhereQuery(
		      mygooditem.getThisIsMyProduct(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "productid");
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  }  	  
	   }
       if (mygooditem.getContactinfoContactinfoid()!=null) {
	      String chunck = net.sf.mp.demo.petshop.dao.impl.jpa.pet.SellercontactinfoExtendedJPAImpl.getPartialLoadWithParentWhereQuery(
		      mygooditem.getContactinfoContactinfoid(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "contactinfoContactinfoid");
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  }  	  
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
	
    public static String getPartialLoadWithParentMyGoodItemQuery (
	   MyGoodItem mygooditem, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (mygooditem==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(mygooditem)){
	      alias = getNextAlias (aliasHt, mygooditem);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (mygooditem.getItemid() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"itemid");
          query.append(" "+alias+".itemid ");
       }
       if (mygooditem.getThisIsMyProduct() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"thisIsMyProduct");
          query.append(" "+alias+".thisIsMyProduct ");
       }
       if (mygooditem.getName() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"name");
          query.append(" "+alias+".name ");
       }
       if (mygooditem.getDescription() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"description");
          query.append(" "+alias+".description ");
       }
       if (mygooditem.getImageurl() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"imageurl");
          query.append(" "+alias+".imageurl ");
       }
       if (mygooditem.getImagethumburl() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"imagethumburl");
          query.append(" "+alias+".imagethumburl ");
       }
       if (mygooditem.getPrice() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"price");
          query.append(" "+alias+".price ");
       }
       if (mygooditem.getAddressAddressid() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"addressAddressid");
          query.append(" "+alias+".addressAddressid ");
       }
       if (mygooditem.getContactinfoContactinfoid() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"contactinfoContactinfoid");
          query.append(" "+alias+".contactinfoContactinfoid ");
       }
       if (mygooditem.getTotalscore() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"totalscore");
          query.append(" "+alias+".totalscore ");
       }
       if (mygooditem.getNumberofvotes() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"numberofvotes");
          query.append(" "+alias+".numberofvotes ");
       }
       if (mygooditem.getDisabled() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"disabled");
          query.append(" "+alias+".disabled ");
       }
       if (mygooditem.getAddressAddressid()!=null) {
	      String chunck = net.sf.mp.demo.petshop.dao.impl.jpa.pet.AddressExtendedJPAImpl.getPartialLoadWithParentAddressQuery(
		      mygooditem.getAddressAddressid(), 
			  isWhereSet, aliasHt, alias, "addressAddressid", beanPath, rootPath+"addressAddressid.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
       if (mygooditem.getThisIsMyProduct()!=null) {
	      String chunck = net.sf.mp.demo.petshop.dao.impl.jpa.product.MyGoodProductExtendedJPAImpl.getPartialLoadWithParentMyGoodProductQuery(
		      mygooditem.getThisIsMyProduct(), 
			  isWhereSet, aliasHt, alias, "thisIsMyProduct", beanPath, rootPath+"productid.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
       if (mygooditem.getContactinfoContactinfoid()!=null) {
	      String chunck = net.sf.mp.demo.petshop.dao.impl.jpa.pet.SellercontactinfoExtendedJPAImpl.getPartialLoadWithParentSellercontactinfoQuery(
		      mygooditem.getContactinfoContactinfoid(), 
			  isWhereSet, aliasHt, alias, "contactinfoContactinfoid", beanPath, rootPath+"contactinfoContactinfoid.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
//       query.append(getMyGoodItemSearchEqualQuery (positiveMyGoodItem, negativeMyGoodItem));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"itemid";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "MyGoodItem|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, MyGoodItem mygooditem) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("mygooditem"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "mygooditem";
		else
			return "mygooditem_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (MyGoodItem mygooditem) {
	   if (mygooditem==null)
		  return false;
       if (mygooditem.getItemid() != null) {
	      return true;
       }
       if (mygooditem.getThisIsMyProduct() != null) {
	      return true;
       }
       if (mygooditem.getName() != null) {
	      return true;
       }
       if (mygooditem.getDescription() != null) {
	      return true;
       }
       if (mygooditem.getImageurl() != null) {
	      return true;
       }
       if (mygooditem.getImagethumburl() != null) {
	      return true;
       }
       if (mygooditem.getPrice() != null) {
	      return true;
       }
       if (mygooditem.getAddressAddressid() != null) {
	      return true;
       }
       if (mygooditem.getContactinfoContactinfoid() != null) {
	      return true;
       }
       if (mygooditem.getTotalscore() != null) {
	      return true;
       }
       if (mygooditem.getNumberofvotes() != null) {
	      return true;
       }
       if (mygooditem.getDisabled() != null) {
	      return true;
       }
       if (mygooditem.getAddressAddressid()!=null) {
	      return true;
	   }  
       if (mygooditem.getThisIsMyProduct()!=null) {
	      return true;
	   }  
       if (mygooditem.getContactinfoContactinfoid()!=null) {
	      return true;
	   }  
       return false;   
	}
	
    public String getPartialLoadMyGoodItemQuery(
	   MyGoodItem mygooditem, 
	   MyGoodItem positiveMyGoodItem, 
	   MyGoodItem negativeMyGoodItem) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (mygooditem.getItemid() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" itemid ");
       }
       if (mygooditem.getThisIsMyProduct() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" thisIsMyProduct ");
       }
       if (mygooditem.getName() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" name ");
       }
       if (mygooditem.getDescription() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" description ");
       }
       if (mygooditem.getImageurl() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" imageurl ");
       }
       if (mygooditem.getImagethumburl() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" imagethumburl ");
       }
       if (mygooditem.getPrice() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" price ");
       }
       if (mygooditem.getAddressAddressid() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" addressAddressid ");
       }
       if (mygooditem.getContactinfoContactinfoid() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" contactinfoContactinfoid ");
       }
       if (mygooditem.getTotalscore() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" totalscore ");
       }
       if (mygooditem.getNumberofvotes() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" numberofvotes ");
       }
       if (mygooditem.getDisabled() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" disabled ");
       }
       query.append(getMyGoodItemSearchEqualQuery (positiveMyGoodItem, negativeMyGoodItem));
	   return query.toString(); 
    }
	
	public List<MyGoodItem> searchPrototypeWithCacheMyGoodItem(MyGoodItem mygooditem) {
		SimpleCache simpleCache = new SimpleCache();
		List<MyGoodItem> list = (List<MyGoodItem>)simpleCache.get(mygooditem.toString());
		if (list==null) {
			list = searchPrototypeMyGoodItem(mygooditem);
			simpleCache.put(mygooditem.toString(), list);
		}
		return list;
	}

    public List<MyGoodItem> loadGraph(MyGoodItem graphMaskWhat, List<MyGoodItem> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }

	public List<MyGoodItem> loadGraphOneLevel(MyGoodItem graphMaskWhat, List<MyGoodItem> whereMask) {
		//first get roots element from where list mask
		// this brings the level 0 of the graph (root level)
 		graphMaskWhat.setItemid(graphMaskWhat.integerMask__);
		List<MyGoodItem> mygooditems = searchPrototypeMyGoodItem (whereMask);
		// for each sub level perform the search with a subquery then reassemble
		// 1. get all sublevel queries
		// 2. perform queries on the correct dao
		// 3. reassemble
		return getLoadGraphOneLevel (graphMaskWhat, mygooditems);
	}

	private List<MyGoodItem> copy(List<MyGoodItem> inputs) {
		List<MyGoodItem> l = new ArrayList<MyGoodItem>();
		for (MyGoodItem input : inputs) {
			MyGoodItem copy = new MyGoodItem();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<MyGoodItem> getLoadGraphOneLevel (MyGoodItem graphMaskWhat, List<MyGoodItem> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<MyGoodItem> loadGraphFromParentKey (MyGoodItem graphMaskWhat, List<MyGoodItem> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		return parents;
	}
	
	private List<String> getPk(List<MyGoodItem> input) {
		List<String> s = new ArrayList<String>();
		for (MyGoodItem t : input) {
			s.add(t.getItemid()+"");
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
    
   /**
   * uk<->pk
   */
   public MyGoodItem loadMyGoodItemFromUniqueKey (MyGoodItem mygooditem) {
      return null;
   }

   public MyGoodItem loadMyGoodItemFromUniqueKeyWithCacheOnResult (MyGoodItem mygooditem) {
      return null;
   }

   public Integer loadMyGoodItemPkFromUniqueKey (MyGoodItem mygooditem) {
      //TODO
      return null;
   }

   public Integer loadMyGoodItemPkFromUniqueKeyWithCacheOnResult (MyGoodItem mygooditem) {
      //TODO
      return null;
   }
   
    // generic part
	public void find (QueryData<MyGoodItem> data) {
		EntityCriteria<MyGoodItem> filter = data.getEntityCriteria();
		MyGoodItem entityWhat = data.getEntityWhat();
		MyGoodItem criteriaMask = filter.getEntity();
		int start = data.getStart();
		int max = data.getMax();
		EntitySort<MyGoodItem> entitySort = data.getEntitySort();
		QuerySortOrder sortOrder = entitySort.getOrder();
		MyGoodItem sortMask = entitySort.getEntity();	

		List<MyGoodItem> results = find(entityWhat, criteriaMask, sortMask, filter.getMatchType(), filter.getOperandType(), filter.getCaseSensitivenessType(), sortOrder, start, max);
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
     * return a list of MyGoodItem entities 
     */
    public List<MyGoodItem> getList () {
        //first lightweight implementation
        return searchPrototypeMyGoodItem(new MyGoodItem());
    }
    /**
     * return a list of MyGoodItem entities and sort
     */
    public List<MyGoodItem> getList (MyGoodItem orderMask, QuerySortOrder sortOrder) {
        return searchPrototype(new MyGoodItem(), orderMask, sortOrder, null);
    }
    /**
     * return a list of MyGoodItem entities and sort based on a MyGoodItem prototype
     */
    public List<MyGoodItem> list (MyGoodItem mask, MyGoodItem orderMask, QuerySortOrder sortOrder) {
        return searchPrototype(mask, orderMask, sortOrder, null);
    }

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
