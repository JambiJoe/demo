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
import net.sf.mp.demo.petshop.dao.face.product.MyGoodProductExtDao;
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import net.sf.mp.demo.petshop.dao.impl.jpa.product.MyGoodProductJPAImpl;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@


import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.mp.demo.petshop.dao.impl.jpa.product.MyGoodItemExtendedJPAImpl;
import net.sf.mp.demo.petshop.dao.impl.jpa.pet.CategoryExtendedJPAImpl;
/**
 *
 * <p>Title: MyGoodProductExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with MyGoodProductExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching MyGoodProductExtendedJPAImpl objects</p>
 *
 */
@org.springframework.stereotype.Repository(value="myGoodProductExtDao")
public class MyGoodProductExtendedJPAImpl extends MyGoodProductJPAImpl implements MyGoodProductExtDao{
    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private MyGoodItemExtendedJPAImpl mygooditemextendedjpaimpl;
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public MyGoodProductExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public MyGoodProductExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a MyGoodProduct entity with cascade of its children
     * @param MyGoodProduct mygoodproduct
     */
    public void insertMyGoodProductWithCascade(MyGoodProduct mygoodproduct) {
    	MyGoodProductExtendedJPAImpl mygoodproductextendedjpaimpl = new MyGoodProductExtendedJPAImpl(getEntityManager());
    	mygoodproductextendedjpaimpl.insertMyGoodProductWithCascade(mygoodproductextendedjpaimpl.getEntityManagerForRecursiveDao(), mygoodproduct);
    }
     
    public void insertMyGoodProductWithCascade(EntityManager emForRecursiveDao, MyGoodProduct mygoodproduct) {
       insertMyGoodProduct(emForRecursiveDao, mygoodproduct);
       if (!mygoodproduct.getMyGoodItems().isEmpty()) {
          MyGoodItemExtendedJPAImpl mygooditemextendedjpaimpl = new MyGoodItemExtendedJPAImpl (emForRecursiveDao);
          for (MyGoodItem _myGoodItems : mygoodproduct.getMyGoodItems()) {
             mygooditemextendedjpaimpl.insertMyGoodItemWithCascade(emForRecursiveDao, _myGoodItems);
          }
       } 
    }
        
    /**
     * Inserts a list of MyGoodProduct entity with cascade of its children
     * @param List<MyGoodProduct> mygoodproducts
     */
    public void insertMyGoodProductsWithCascade(List<MyGoodProduct> mygoodproducts) {
       for (MyGoodProduct mygoodproduct : mygoodproducts) {
          insertMyGoodProductWithCascade(mygoodproduct);
       }
    } 
        
    /**
     * lookup MyGoodProduct entity MyGoodProduct, criteria and max result number
     */
    public List<MyGoodProduct> lookupMyGoodProduct(MyGoodProduct mygoodproduct, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT mygoodproduct FROM MyGoodProduct mygoodproduct ");
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
    
    public List<MyGoodProduct> lookupMyGoodProduct(MyGoodProduct mygoodproduct, Criteria criteria, Integer numberOfResult) {
		return lookupMyGoodProduct(mygoodproduct, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyMyGoodProduct (MyGoodProduct mygoodproduct, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyMyGoodProductQueryChunkPrototype (mygoodproduct);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
        if (mygoodproduct.getProductid() != null) {
           jpaQuery.setParameter ("productid", mygoodproduct.getProductid());
        }   
        if (mygoodproduct.getCategoryid() != null) {
           jpaQuery.setParameter ("categoryid", mygoodproduct.getCategoryid());
        }   
        if (mygoodproduct.getName() != null) {
           jpaQuery.setParameter ("name", mygoodproduct.getName());
        }   
        if (mygoodproduct.getDescription() != null) {
           jpaQuery.setParameter ("description", mygoodproduct.getDescription());
        }   
        if (mygoodproduct.getImageurl() != null) {
           jpaQuery.setParameter ("imageurl", mygoodproduct.getImageurl());
        }   
		return jpaQuery.executeUpdate();
    }
	
	public MyGoodProduct affectMyGoodProduct (MyGoodProduct mygoodproduct) {
	    return referMyGoodProduct (mygoodproduct, null, false);		    
	}
		
	/**
	 * Assign the first mygoodproduct retrieved corresponding to the mygoodproduct criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no mygoodproduct corresponding in the database. Then mygoodproduct is inserted and returned with its primary key(s). 
	 */
	public MyGoodProduct assignMyGoodProduct (MyGoodProduct mygoodproduct) {
		return referMyGoodProduct (mygoodproduct, null, true);
	}

	/**
	 * Assign the first mygoodproduct retrieved corresponding to the mask criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no mygoodproduct corresponding in the database. 
	 * Then mygoodproduct is inserted and returned with its primary key(s). 
	 * Mask servers usually to set unique keys or the semantic reference
	 */
    public MyGoodProduct assignMyGoodProduct (MyGoodProduct mygoodproduct, MyGoodProduct mask) {
		return referMyGoodProduct (mygoodproduct, mask, true);
	}

	public MyGoodProduct referMyGoodProduct (MyGoodProduct mygoodproduct, MyGoodProduct mask, boolean isAssign) {
		mygoodproduct = assignBlankToNull (mygoodproduct);
		if (isAllNull(mygoodproduct))
			return null;
		else {
			List<MyGoodProduct> list;
			if (mask==null)
				list = searchPrototypeMyGoodProduct(mygoodproduct);
			else
				list = searchPrototypeMyGoodProduct(mask);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertMyGoodProduct(mygoodproduct);
			    else
				   return null;
			}
			else if (list.size()==1)
				mygoodproduct.copy(list.get(0));
			else 
				//TODO log error
				mygoodproduct.copy(list.get(0));
		}
		return mygoodproduct;		    
	}

   public MyGoodProduct assignMyGoodProductUseCache (MyGoodProduct mygoodproduct) {
      return referMyGoodProductUseCache (mygoodproduct, true);
   }
      		
   public MyGoodProduct affectMyGoodProductUseCache (MyGoodProduct mygoodproduct) {
      return referMyGoodProductUseCache (mygoodproduct, false);
   }
      		
   public MyGoodProduct referMyGoodProductUseCache (MyGoodProduct mygoodproduct, boolean isAssign) {
	  String key = getCacheKey(null, mygoodproduct, null, "assignMyGoodProduct");
      MyGoodProduct mygoodproductCache = (MyGoodProduct)simpleCache.get(key);
      if (mygoodproductCache==null) {
         mygoodproductCache = referMyGoodProduct (mygoodproduct, null, isAssign);
         if (key!=null)
         	simpleCache.put(key, mygoodproductCache);
      }
      mygoodproduct.copy(mygoodproductCache);
      return mygoodproductCache;
   }	

	private String getCacheKey (MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (mygoodproductWhat!=null)
	       sb.append(mygoodproductWhat.toStringWithParents());
	    if (positiveMyGoodProduct!=null)
	       sb.append(positiveMyGoodProduct.toStringWithParents());
	    if (negativeMyGoodProduct!=null)
	       sb.append(negativeMyGoodProduct.toStringWithParents());
	    return sb.toString();
	}
	
    public MyGoodProduct partialLoadWithParentFirstMyGoodProduct(MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct){
		List <MyGoodProduct> list = partialLoadWithParentMyGoodProduct(mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct);
		return (!list.isEmpty())?(MyGoodProduct)list.get(0):null;
    }
    
    public MyGoodProduct partialLoadWithParentFirstMyGoodProductUseCache(MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, Boolean useCache){
		List <MyGoodProduct> list = partialLoadWithParentMyGoodProductUseCache(mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct, useCache);
		return (!list.isEmpty())?(MyGoodProduct)list.get(0):null;
    }
	
	public MyGoodProduct partialLoadWithParentFirstMyGoodProductUseCacheOnResult(MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, Boolean useCache){
		List <MyGoodProduct> list = partialLoadWithParentMyGoodProductUseCacheOnResult(mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct, useCache);
		return (!list.isEmpty())?(MyGoodProduct)list.get(0):null;
    }
	//
	protected List<MyGoodProduct> partialLoadWithParentMyGoodProduct(MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentMyGoodProduct(mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentMyGoodProductQueryResult (MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentMyGoodProductQueryResult (mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<MyGoodProduct> getDistinctMyGoodProduct(MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct) {
		 return partialLoadWithParentMyGoodProduct(mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<MyGoodProduct> partialLoadWithParentMyGoodProduct(MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct) {
		 return partialLoadWithParentMyGoodProduct(mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct, new QuerySelectInit(), null, false);
	}	
  
	public List<MyGoodProduct> partialLoadWithParentMyGoodProductUseCacheOnResult(MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, Boolean useCache) {
		String key = getCacheKey(mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct, "partialLoadWithParentMyGoodProduct");
		List<MyGoodProduct> list = (List<MyGoodProduct>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentMyGoodProduct(mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<MyGoodProduct> partialLoadWithParentMyGoodProductUseCache(MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, Boolean useCache) {
		String key = getCacheKey(mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct, "partialLoadWithParentMyGoodProduct");
		List<MyGoodProduct> list = (List<MyGoodProduct>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentMyGoodProduct(mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<MyGoodProduct> handleLoadWithParentMyGoodProduct(Map beanPath, List list, MyGoodProduct mygoodproductWhat) {
	    return handleLoadWithParentMyGoodProduct(beanPath, list, mygoodproductWhat, true);  
	}
	
	private List<MyGoodProduct> handleLoadWithParentMyGoodProduct(Map beanPath, List list, MyGoodProduct mygoodproductWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentMyGoodProductWithOneElementInRow(list, beanPath, mygoodproductWhat, isHql);
		}
		return handlePartialLoadWithParentMyGoodProduct(list, beanPath, mygoodproductWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateMyGoodProduct (MyGoodProduct mygoodproduct, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(mygoodproduct, beanPath, value);
	}
	
	protected void populateMyGoodProductFromSQL (MyGoodProduct mygoodproduct, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(mygoodproduct, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to mygoodproduct.clone() instead
	private MyGoodProduct cloneMyGoodProduct (MyGoodProduct mygoodproduct) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (MyGoodProduct) BeanUtils.cloneBeanObject(mygoodproduct);
	   if (mygoodproduct==null) return new MyGoodProduct();
	   return mygoodproduct.clone();
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
	
    public List<MyGoodProduct> countDistinct (MyGoodProduct whatMask, MyGoodProduct whereEqCriteria) {
       return partialLoadWithParentMyGoodProduct(whatMask, whereEqCriteria, null, new QuerySelectCountInit("mygoodproduct"), null, false);
    }   
  	
    public Long count(MyGoodProduct whereEqCriteria) {
	    return count(null, whereEqCriteria, EntityMatchType.ALL, OperandType.EQUALS, true); 
/*        Query query = getEntityManager().createQuery(getSelectCountPrototype(whereEqCriteria));
        List<Long> list = query.getResultList();
    	if (!list.isEmpty()) {
            return list.get(0);
    	}
    	return 0L;
*/
    }


    public Long count(MyGoodProduct whatMask, MyGoodProduct whereCriteria, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
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

	protected String countQuery (MyGoodProduct mygoodproduct, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        String what = "SELECT count(*) FROM MyGoodProduct mygoodproduct ";
		return findQuery (mygoodproduct, null, what, matchType, operandType, caseSensitivenessType, null);
    }
	
    protected String getSelectCountPrototype (MyGoodProduct whereEqCriteria) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT count(*) FROM MyGoodProduct mygoodproduct ");
        queryWhere.append (getWhereEqualWhereQueryChunk(whereEqCriteria, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }
			
   public MyGoodProduct getFirstMyGoodProductWhereConditionsAre (MyGoodProduct mygoodproduct) {
      List<MyGoodProduct> list = partialLoadWithParentMyGoodProduct(getDefaultMyGoodProductWhat(), mygoodproduct, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (MyGoodProduct mygoodproduct) {
      return partialLoadWithParentMyGoodProductQueryResult(getDefaultMyGoodProductWhat(), mygoodproduct, null, 1, false);	
   }
   
   protected MyGoodProduct getDefaultMyGoodProductWhat() {
      MyGoodProduct mygoodproduct = new MyGoodProduct();
      mygoodproduct.setProductid(new String());
      return mygoodproduct;
   }
   
	public MyGoodProduct getFirstMyGoodProduct (MyGoodProduct mygoodproduct) {
		if (isAllNull(mygoodproduct))
			return null;
		else {
			List<MyGoodProduct> list = searchPrototype (mygoodproduct, 1);
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
    * checks if the MyGoodProduct entity exists
    */           
    public boolean existsMyGoodProduct (MyGoodProduct mygoodproduct) {
       if (getFirstMyGoodProduct(mygoodproduct)!=null)
          return true;
       return false;  
    }
        
    public boolean existsMyGoodProductWhereConditionsAre (MyGoodProduct mygoodproduct) {
       if (getFirstResultWhereConditionsAre (mygoodproduct).isEmpty())
          return false;
       return true;  
    }

	private int countPartialField (MyGoodProduct mygoodproduct) {
	   int cpt = 0;
       if (mygoodproduct.getProductid() != null) {
          cpt++;
       }
       if (mygoodproduct.getCategoryid() != null) {
          cpt++;
       }
       if (mygoodproduct.getName() != null) {
          cpt++;
       }
       if (mygoodproduct.getDescription() != null) {
          cpt++;
       }
       if (mygoodproduct.getImageurl() != null) {
          cpt++;
       }
       return cpt;
	}   

	public List<MyGoodProduct> partialLoadWithParentMyGoodProduct(MyGoodProduct what, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentMyGoodProductJPAQueryResult (what, positiveMyGoodProduct, negativeMyGoodProduct, queryWhatInit, beanPath, nbOfResult, useCache);
		return handlePartialLoadWithParentResult(what, list, beanPath);
	}
	
	public List<MyGoodProduct> handlePartialLoadWithParentResult(MyGoodProduct what, List list, Map beanPath) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentMyGoodProductWithOneElementInRow(list, beanPath, what, true);
		}
		return handlePartialLoadWithParentMyGoodProduct(list, beanPath, what, true);
	}	

	private List partialLoadWithParentMyGoodProductQueryResult(MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentMyGoodProductJPAQueryResult (mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct, queryWhatInit, new Hashtable(), nbOfResult, useCache);
    }	
  
	private List partialLoadWithParentMyGoodProductJPAQueryResult(MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentJPAQuery (mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
    }	
   /**
    * @returns an JPA Hsql query based on entity MyGoodProduct and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentJPAQuery (MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getPartialLoadWithParentJPARawQuery (mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct, beanPath, queryWhatInit);
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
    }
  	
   /**
    * @returns an JPA Raw Hsql query based on entity MyGoodProduct and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentJPARawQuery (MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, Map beanPath, QueryWhatInit queryWhatInit) {
	   return getEntityManager().createQuery(getPartialLoadWithParentRawHsqlQuery (mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct, beanPath, queryWhatInit));
    }
	
	private List<MyGoodProduct> handlePartialLoadWithParentMyGoodProduct(List<Object[]> list, Map<Integer, String> beanPath, MyGoodProduct mygoodproductWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentMyGoodProduct(list, beanPath, mygoodproductWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentMyGoodProduct, message:"+ex.getMessage());
			return new ArrayList<MyGoodProduct>();
		}
    }

	private List<MyGoodProduct> handlePartialLoadWithParentMyGoodProductWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, MyGoodProduct mygoodproductWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentMyGoodProductWithOneElementInRow(list, beanPath, mygoodproductWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentMyGoodProductWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<MyGoodProduct>();
		}
    }
    	
	 private List<MyGoodProduct> convertPartialLoadWithParentMyGoodProduct(List<Object[]> list, Map<Integer, String> beanPath, MyGoodProduct mygoodproductWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<MyGoodProduct> resultList = new ArrayList<MyGoodProduct>();
		 for (Object[] row : list) {		
		    MyGoodProduct mygoodproduct = cloneMyGoodProduct (mygoodproductWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateMyGoodProduct (mygoodproduct, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(mygoodproduct);
		 }
		 return resultList;		
	 }	
    
	 private List<MyGoodProduct> convertPartialLoadWithParentMyGoodProductWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, MyGoodProduct mygoodproductWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<MyGoodProduct> resultList = new ArrayList<MyGoodProduct>();
		 for (Object row : list) {		
		    MyGoodProduct mygoodproduct = cloneMyGoodProduct (mygoodproductWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateMyGoodProduct (mygoodproduct, row, (String)entry.getValue());
		    }
		    resultList.add(mygoodproduct);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, MyGoodProduct mygoodproductWhat, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentJPAQuery (mygoodproductWhat, positiveMyGoodProduct, negativeMyGoodProduct, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentRawHsqlQuery (MyGoodProduct mygoodproduct, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentMyGoodProductQuery (mygoodproduct, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereQuery (positiveMyGoodProduct, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public String findPartialLoadWithParentRawHsqlQuery (MyGoodProduct whatMask, MyGoodProduct criteriaMask, Map beanPath, QueryWhatInit queryWhatInit,  MyGoodProduct orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentMyGoodProductQuery (whatMask, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
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
	public String countPartialLoadWithParentRawHsqlQuery (MyGoodProduct whatMask, MyGoodProduct criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
		Map beanPath = new Hashtable();
		Hashtable aliasWhatHt = new Hashtable();
		// used to initiate the how part of the what
		getPartialLoadWithParentMyGoodProductQuery (whatMask, null, aliasWhatHt, null, null, beanPath, "", new QuerySelectInit());
		String what = "select count(mygoodproduct) ";
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
    	
	public String findPartialQuery (MyGoodProduct whatMask, MyGoodProduct criteriaMask, MyGoodProduct orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Map beanPath) {
        QueryWhatInit queryWhatInit = new QuerySelectInit();
        return findPartialLoadWithParentRawHsqlQuery(whatMask, criteriaMask, beanPath, queryWhatInit, orderMask, matchType, operandType, caseSensitivenessType,  sortOrder);
    }
	
	/**
    * partial on a single entity load enables to specify the fields you want to load explicitly
    */         
	public List<MyGoodProduct> partialLoadMyGoodProduct(MyGoodProduct mygoodproduct, MyGoodProduct positiveMyGoodProduct, MyGoodProduct negativeMyGoodProduct) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadMyGoodProductQuery (mygoodproduct, positiveMyGoodProduct, negativeMyGoodProduct));
		int countPartialField = countPartialField(mygoodproduct);
		if (countPartialField==0) 
			return new ArrayList<MyGoodProduct>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<MyGoodProduct> returnList = new ArrayList<MyGoodProduct>();
		while(iter.hasNext()) {
			int index = 0;
			Object[] row;
			if (countPartialField==1) {
				row = new Object[1];
				row[0] = iter.next();
				} 
			else 
				row = (Object[]) iter.next();
			MyGoodProduct mygoodproductResult = new MyGoodProduct();
			if (mygoodproduct.getCategoryid_() != null) {
                mygoodproductResult.setCategoryid_((String) row[index]);
				index++;
			}
			if (mygoodproduct.getName() != null) {
                mygoodproductResult.setName((String) row[index]);
				index++;
			}
			if (mygoodproduct.getDescription() != null) {
                mygoodproductResult.setDescription((String) row[index]);
				index++;
			}
			if (mygoodproduct.getImageurl() != null) {
                mygoodproductResult.setImageurl((String) row[index]);
				index++;
			}
			returnList.add(mygoodproductResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereQuery (
	   MyGoodProduct criteriaMask, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias,
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
	   MyGoodProduct mygoodproduct, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (mygoodproduct==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(mygoodproduct)){
	      alias = getNextAlias (aliasWhereHt, mygoodproduct);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (mygoodproduct.getProductid() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".productid = '"+ mygoodproduct.getProductid()+"' ");
       }
       if (mygoodproduct.getCategoryid() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".categoryid_ = '"+ mygoodproduct.getCategoryid_()+"' ");
       }
       if (mygoodproduct.getName() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".name = '"+ mygoodproduct.getName()+"' ");
       }
       if (mygoodproduct.getDescription() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".description = '"+ mygoodproduct.getDescription()+"' ");
       }
       if (mygoodproduct.getImageurl() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".imageurl = '"+ mygoodproduct.getImageurl()+"' ");
       }
       if (mygoodproduct.getCategoryid()!=null) {
	      String chunck = net.sf.mp.demo.petshop.dao.impl.jpa.pet.CategoryExtendedJPAImpl.getPartialLoadWithParentWhereQuery(
		      mygoodproduct.getCategoryid(), 
			  isWhereSet, aliasHt, aliasWhereHt, alias, "categoryid");
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
	
    public static String getPartialLoadWithParentMyGoodProductQuery (
	   MyGoodProduct mygoodproduct, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (mygoodproduct==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(mygoodproduct)){
	      alias = getNextAlias (aliasHt, mygoodproduct);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (mygoodproduct.getProductid() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"productid");
          query.append(" "+alias+".productid ");
       }
       if (mygoodproduct.getCategoryid() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"categoryid");
          query.append(" "+alias+".categoryid ");
       }
       if (mygoodproduct.getName() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"name");
          query.append(" "+alias+".name ");
       }
       if (mygoodproduct.getDescription() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"description");
          query.append(" "+alias+".description ");
       }
       if (mygoodproduct.getImageurl() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"imageurl");
          query.append(" "+alias+".imageurl ");
       }
       if (mygoodproduct.getCategoryid()!=null) {
	      String chunck = net.sf.mp.demo.petshop.dao.impl.jpa.pet.CategoryExtendedJPAImpl.getPartialLoadWithParentCategoryQuery(
		      mygoodproduct.getCategoryid(), 
			  isWhereSet, aliasHt, alias, "categoryid", beanPath, rootPath+"categoryid.", queryWhatInit);
		  if (chunck!=null && !chunck.equals("")) {
		     query.append(chunck);
		     isWhereSet=true;
		  } 
	   }  
//       query.append(getMyGoodProductSearchEqualQuery (positiveMyGoodProduct, negativeMyGoodProduct));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"productid";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "MyGoodProduct|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, MyGoodProduct mygoodproduct) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("mygoodproduct"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "mygoodproduct";
		else
			return "mygoodproduct_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (MyGoodProduct mygoodproduct) {
	   if (mygoodproduct==null)
		  return false;
       if (mygoodproduct.getProductid() != null) {
	      return true;
       }
       if (mygoodproduct.getCategoryid() != null) {
	      return true;
       }
       if (mygoodproduct.getName() != null) {
	      return true;
       }
       if (mygoodproduct.getDescription() != null) {
	      return true;
       }
       if (mygoodproduct.getImageurl() != null) {
	      return true;
       }
       if (mygoodproduct.getCategoryid()!=null) {
	      return true;
	   }  
       return false;   
	}
	
    public String getPartialLoadMyGoodProductQuery(
	   MyGoodProduct mygoodproduct, 
	   MyGoodProduct positiveMyGoodProduct, 
	   MyGoodProduct negativeMyGoodProduct) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (mygoodproduct.getProductid() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" productid ");
       }
       if (mygoodproduct.getCategoryid() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" categoryid ");
       }
       if (mygoodproduct.getName() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" name ");
       }
       if (mygoodproduct.getDescription() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" description ");
       }
       if (mygoodproduct.getImageurl() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" imageurl ");
       }
       query.append(getMyGoodProductSearchEqualQuery (positiveMyGoodProduct, negativeMyGoodProduct));
	   return query.toString(); 
    }
	
	public List<MyGoodProduct> searchPrototypeWithCacheMyGoodProduct(MyGoodProduct mygoodproduct) {
		SimpleCache simpleCache = new SimpleCache();
		List<MyGoodProduct> list = (List<MyGoodProduct>)simpleCache.get(mygoodproduct.toString());
		if (list==null) {
			list = searchPrototypeMyGoodProduct(mygoodproduct);
			simpleCache.put(mygoodproduct.toString(), list);
		}
		return list;
	}

    public List<MyGoodProduct> loadGraph(MyGoodProduct graphMaskWhat, List<MyGoodProduct> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }

	public List<MyGoodProduct> loadGraphOneLevel(MyGoodProduct graphMaskWhat, List<MyGoodProduct> whereMask) {
		//first get roots element from where list mask
		// this brings the level 0 of the graph (root level)
 		graphMaskWhat.setProductid(graphMaskWhat.stringMask__);
		List<MyGoodProduct> mygoodproducts = searchPrototypeMyGoodProduct (whereMask);
		// for each sub level perform the search with a subquery then reassemble
		// 1. get all sublevel queries
		// 2. perform queries on the correct dao
		// 3. reassemble
		return getLoadGraphOneLevel (graphMaskWhat, mygoodproducts);
	}

	private List<MyGoodProduct> copy(List<MyGoodProduct> inputs) {
		List<MyGoodProduct> l = new ArrayList<MyGoodProduct>();
		for (MyGoodProduct input : inputs) {
			MyGoodProduct copy = new MyGoodProduct();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<MyGoodProduct> getLoadGraphOneLevel (MyGoodProduct graphMaskWhat, List<MyGoodProduct> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<MyGoodProduct> loadGraphFromParentKey (MyGoodProduct graphMaskWhat, List<MyGoodProduct> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		if (graphMaskWhat.getMyGoodItems()!=null && !graphMaskWhat.getMyGoodItems().isEmpty()) {
			for (MyGoodItem childWhat : graphMaskWhat.getMyGoodItems()) {
				childWhat.setThisIsMyProduct_(graphMaskWhat.stringMask__); // add to the what mask, usefull for reconciliation
				MyGoodItemExtendedJPAImpl mygooditemextendedjpaimpl = new MyGoodItemExtendedJPAImpl ();
				List<MyGoodItem> children = mygooditemextendedjpaimpl.lookupMyGoodItem(childWhat, getFkCriteria(" productid ", ids), null, getEntityManager());
				reassembleMyGoodItem (children, parents);				
				break;
			}
		}
		return parents;
	}
	
	private void reassembleMyGoodItem (List<MyGoodItem> children, List<MyGoodProduct> parents) {
		for (MyGoodItem child : children) {
			for (MyGoodProduct parent : parents) {
				if (parent.getProductid()!=null && parent.getProductid().toString().equals(child.getThisIsMyProduct()+"")) {
					parent.addMyGoodItems(child); 
					child.setThisIsMyProduct(parent);
					break;
				}
			}
		}
	}
	
	private List<String> getPk(List<MyGoodProduct> input) {
		List<String> s = new ArrayList<String>();
		for (MyGoodProduct t : input) {
			s.add(t.getProductid()+"");
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
   public MyGoodProduct loadMyGoodProductFromUniqueKey (MyGoodProduct mygoodproduct) {
      return null;
   }

   public MyGoodProduct loadMyGoodProductFromUniqueKeyWithCacheOnResult (MyGoodProduct mygoodproduct) {
      return null;
   }

   public String loadMyGoodProductPkFromUniqueKey (MyGoodProduct mygoodproduct) {
      //TODO
      return null;
   }

   public String loadMyGoodProductPkFromUniqueKeyWithCacheOnResult (MyGoodProduct mygoodproduct) {
      //TODO
      return null;
   }
   
    // generic part
	public void find (QueryData<MyGoodProduct> data) {
		EntityCriteria<MyGoodProduct> filter = data.getEntityCriteria();
		MyGoodProduct entityWhat = data.getEntityWhat();
		MyGoodProduct criteriaMask = filter.getEntity();
		int start = data.getStart();
		int max = data.getMax();
		EntitySort<MyGoodProduct> entitySort = data.getEntitySort();
		QuerySortOrder sortOrder = entitySort.getOrder();
		MyGoodProduct sortMask = entitySort.getEntity();	

		List<MyGoodProduct> results = find(entityWhat, criteriaMask, sortMask, filter.getMatchType(), filter.getOperandType(), filter.getCaseSensitivenessType(), sortOrder, start, max);
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
     * return a list of MyGoodProduct entities 
     */
    public List<MyGoodProduct> getList () {
        //first lightweight implementation
        return searchPrototypeMyGoodProduct(new MyGoodProduct());
    }
    /**
     * return a list of MyGoodProduct entities and sort
     */
    public List<MyGoodProduct> getList (MyGoodProduct orderMask, QuerySortOrder sortOrder) {
        return searchPrototype(new MyGoodProduct(), orderMask, sortOrder, null);
    }
    /**
     * return a list of MyGoodProduct entities and sort based on a MyGoodProduct prototype
     */
    public List<MyGoodProduct> list (MyGoodProduct mask, MyGoodProduct orderMask, QuerySortOrder sortOrder) {
        return searchPrototype(mask, orderMask, sortOrder, null);
    }

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
