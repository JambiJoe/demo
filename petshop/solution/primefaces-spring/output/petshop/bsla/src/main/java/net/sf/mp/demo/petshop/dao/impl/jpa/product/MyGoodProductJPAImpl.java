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
package net.sf.mp.demo.petshop.dao.impl.jpa.product;

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
import net.sf.mp.demo.petshop.dao.face.product.MyGoodProductDao;
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import net.sf.mp.demo.petshop.domain.pet.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * <p>Title: MyGoodProductJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with MyGoodProductJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching MyGoodProductJPAImpl objects</p>
 *
 */


@org.springframework.stereotype.Repository(value="myGoodProductDao")
public class MyGoodProductJPAImpl implements MyGoodProductDao {
	public MyGoodProductJPAImpl () {}
	
    @PersistenceContext
    EntityManager entityManager;
	
    /**
     * Inserts a MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     */
    public void insertMyGoodProduct(MyGoodProduct mygoodproduct) {
       entityManager.persist(mygoodproduct);
    }

    protected void insertMyGoodProduct(EntityManager emForRecursiveDao, MyGoodProduct mygoodproduct) {
       emForRecursiveDao.persist(mygoodproduct);
    } 
    /**
     * Inserts a list of MyGoodProduct entity 
     * @param List<MyGoodProduct> mygoodproducts
     */
    public void insertMyGoodProducts(List<MyGoodProduct> mygoodproducts) {
    	//TODO
    }
    /**
     * Updates a MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     */
    public MyGoodProduct updateMyGoodProduct(MyGoodProduct mygoodproduct) {
       return entityManager.merge(mygoodproduct);
    }

	/**
     * Updates a MyGoodProduct entity with only the attributes set into MyGoodProduct.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param MyGoodProduct mygoodproduct
    */ 
    @Transactional
    public int updateNotNullOnlyMyGoodProduct(MyGoodProduct mygoodproduct) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyMyGoodProductQueryChunk(mygoodproduct));
        if (mygoodproduct.getProductid() != null) {
           jpaQuery.setParameter ("productid", mygoodproduct.getProductid());
        }   
        if (mygoodproduct.getCategoryid_() != null) {
           jpaQuery.setParameter ("categoryid_", mygoodproduct.getCategoryid_());
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

    protected String getUpdateNotNullOnlyMyGoodProductQueryChunkPrototype (MyGoodProduct mygoodproduct) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update MyGoodProduct mygoodproduct ");
        if (mygoodproduct.getCategoryid() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygoodproduct.categoryid = :categoryid");
        }
        if (mygoodproduct.getName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygoodproduct.name = :name");
        }
        if (mygoodproduct.getDescription() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygoodproduct.description = :description");
        }
        if (mygoodproduct.getImageurl() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygoodproduct.imageurl = :imageurl");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyMyGoodProductQueryChunk (MyGoodProduct mygoodproduct) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyMyGoodProductQueryChunkPrototype(mygoodproduct));
        if (mygoodproduct.getProductid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" mygoodproduct.productid = :productid");
        }
        return query.toString();
    }
    
                
	protected MyGoodProduct assignBlankToNull (MyGoodProduct mygoodproduct) {
        if (mygoodproduct==null)
			return null;
        if (mygoodproduct.getName()!=null && mygoodproduct.getName().equals(""))
           mygoodproduct.setName((String)null);
        if (mygoodproduct.getDescription()!=null && mygoodproduct.getDescription().equals(""))
           mygoodproduct.setDescription((String)null);
        if (mygoodproduct.getImageurl()!=null && mygoodproduct.getImageurl().equals(""))
           mygoodproduct.setImageurl((String)null);
        //foreign key categoryid
        if (mygoodproduct.getCategoryid_()!=null && mygoodproduct.getCategoryid_().equals(""))
           mygoodproduct.setCategoryid_((String)null);
		return mygoodproduct;
	}
	
	protected boolean isAllNull (MyGoodProduct mygoodproduct) {
	    if (mygoodproduct==null)
			return true;
        if (mygoodproduct.getProductid()!=null) 
            return false;
        if (mygoodproduct.getCategoryid()!=null) 
            return false;
        if (mygoodproduct.getName()!=null) 
            return false;
        if (mygoodproduct.getDescription()!=null) 
            return false;
        if (mygoodproduct.getImageurl()!=null) 
            return false;
		return true;
	}
		
    @Transactional
    public int updateNotNullOnlyPrototypeMyGoodProduct(MyGoodProduct mygoodproduct, MyGoodProduct prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update MyGoodProduct mygoodproduct ");
        if (mygoodproduct.getProductid() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygoodproduct.productid = '"+ mygoodproduct.getProductid()+"' ");
        }
        if (mygoodproduct.getCategoryid_() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygoodproduct.categoryid_ = '"+ mygoodproduct.getCategoryid_()+"' ");
        }
        if (mygoodproduct.getName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygoodproduct.name = '"+ mygoodproduct.getName()+"' ");
        }
        if (mygoodproduct.getDescription() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygoodproduct.description = '"+ mygoodproduct.getDescription()+"' ");
        }
        if (mygoodproduct.getImageurl() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygoodproduct.imageurl = '"+ mygoodproduct.getImageurl()+"' ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getProductid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygoodproduct.productid = '"+ prototypeCriteria.getProductid()+"' ");
        }
        if (prototypeCriteria.getCategoryid_() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygoodproduct.categoryid_ = '"+ prototypeCriteria.getCategoryid_()+"' ");
        }
        if (prototypeCriteria.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygoodproduct.name = '"+ prototypeCriteria.getName()+"' ");
        }
        if (prototypeCriteria.getDescription() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygoodproduct.description = '"+ prototypeCriteria.getDescription()+"' ");
        }
        if (prototypeCriteria.getImageurl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygoodproduct.imageurl = '"+ prototypeCriteria.getImageurl()+"' ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     */
    public void saveMyGoodProduct(MyGoodProduct mygoodproduct) {
       //entityManager.persist(mygoodproduct);
       if (entityManager.contains(mygoodproduct)) {
          entityManager.merge(mygoodproduct);
       } else {
          entityManager.persist(mygoodproduct);
       }
       entityManager.flush(); 
    }
       
    /**
     * Deletes a MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     */
    public void deleteMyGoodProduct(MyGoodProduct mygoodproduct) {
      entityManager.remove(mygoodproduct);
    }
    
    /**
     * Loads the MyGoodProduct entity which is related to an instance of
     * MyGoodProduct
     * @param Long id
     * @return MyGoodProduct The MyGoodProduct entity
     
    public MyGoodProduct loadMyGoodProduct(Long id) {
    	return (MyGoodProduct)entityManager.get(MyGoodProduct.class, id);
    }
*/
  
    /**
     * Loads the MyGoodProduct entity which is related to an instance of
     * MyGoodProduct
     * @param java.lang.String Productid
     * @return MyGoodProduct The MyGoodProduct entity
     */
    public MyGoodProduct loadMyGoodProduct(java.lang.String productid) {
    	return (MyGoodProduct)entityManager.find(MyGoodProduct.class, productid);
    }
    
    /**
     * Loads a list of MyGoodProduct entity 
     * @param List<java.lang.String> productids
     * @return List<MyGoodProduct> The MyGoodProduct entity
     */
    public List<MyGoodProduct> loadMyGoodProductListByMyGoodProduct (List<MyGoodProduct> mygoodproducts) {
       return null;
    }
    
    /**
     * Loads a list of MyGoodProduct entity 
     * @param List<java.lang.String> productids
     * @return List<MyGoodProduct> The MyGoodProduct entity
     */
    public List<MyGoodProduct> loadMyGoodProductListByProductid(List<java.lang.String> productids){
       return null;
    }
    
    /**
     * Loads the MyGoodProduct entity which is related to an instance of
     * MyGoodProduct and its dependent one to many objects
     * @param Long id
     * @return MyGoodProduct The MyGoodProduct entity
     */
    public MyGoodProduct loadFullFirstLevelMyGoodProduct(java.lang.String productid) {
        List list = getResultList(
                     "SELECT mygoodproduct FROM MyGoodProduct mygoodproduct "
                     + " LEFT JOIN mygoodproduct.myGoodItemThisIsMyProducts "   
                     + " WHERE mygoodproduct.productid = "+productid
               );
         if (list!=null && !list.isEmpty())
            return (MyGoodProduct)list.get(0);
         return null;
    	//return null;//(MyGoodProduct) entityManager.queryForObject("loadFullFirstLevelMyGoodProduct", id);
    }

    /**
     * Loads the MyGoodProduct entity which is related to an instance of
     * MyGoodProduct
     * @param MyGoodProduct mygoodproduct
     * @return MyGoodProduct The MyGoodProduct entity
     */
    public MyGoodProduct loadFullFirstLevelMyGoodProduct(MyGoodProduct mygoodproduct) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT mygoodproduct FROM MyGoodProduct mygoodproduct ");
        query.append (" LEFT JOIN mygoodproduct.myGoodItemThisIsMyProducts ");
        if (mygoodproduct.getProductid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygoodproduct.productid = '"+ mygoodproduct.getProductid()+"' ");
         }
        List list = getResultList(query.toString());
        if (list!=null && !list.isEmpty())
           return (MyGoodProduct)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the MyGoodProduct entity which is related to an instance of
     * MyGoodProduct and its dependent objects one to many
     * @param Long id
     * @return MyGoodProduct The MyGoodProduct entity
     */
    public MyGoodProduct loadFullMyGoodProduct(Long id) {
    	return null;//(MyGoodProduct)entityManager.queryForObject("loadFullMyGoodProduct", id);
    }

    /**
     * Searches a list of MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     * @return List
     */  
    public List<MyGoodProduct> searchPrototypeMyGoodProduct(MyGoodProduct mygoodproduct) {
       return searchPrototype (mygoodproduct, null);
    }  
	
    public List<MyGoodProduct> searchPrototypeAnyMyGoodProduct(MyGoodProduct mygoodproduct) {
       return searchPrototypeAny (mygoodproduct, null);
    }  

	// indirection
    public List<MyGoodProduct> find (MyGoodProduct criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
       return find (criteriaMask, matchType, operandType, caseSensitivenessType, null, null); 
	}
	
	// indirection
	protected List<MyGoodProduct> find (MyGoodProduct criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, Integer startPosition, Integer maxResults) {
       return find (criteriaMask, null, matchType, operandType, caseSensitivenessType, null, startPosition, maxResults); 
    }
	
	// indirection
	protected List<MyGoodProduct> find (MyGoodProduct criteriaMask, MyGoodProduct orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Integer startPosition, Integer maxResults) {
       return find (null, criteriaMask, orderMask, matchType, operandType, caseSensitivenessType, sortOrder, startPosition, maxResults);
    }
	
	// main find implementation
	protected List<MyGoodProduct> find (MyGoodProduct whatMask, MyGoodProduct criteriaMask, MyGoodProduct orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Integer startPosition, Integer maxResults) {
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
    public String findQuery (MyGoodProduct criteriaMask, MyGoodProduct orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
        String what = "SELECT mygoodproduct FROM MyGoodProduct mygoodproduct ";
		return findQuery (criteriaMask, orderMask, what, matchType, operandType, caseSensitivenessType, sortOrder);
    }

	/**
	 *   find partial on entity
	 *
	 */
    public String findPartialQuery (MyGoodProduct whatMask, MyGoodProduct criteriaMask, MyGoodProduct orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Map beanPath) {
        return "to override ";
    }
	
	public List<MyGoodProduct> handlePartialLoadWithParentResult(MyGoodProduct what, List list, Map beanPath) {
		return null; //TO Override
	}
	
    protected String findQuery (MyGoodProduct criteriaMask, MyGoodProduct orderMask, String what, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
        String queryWhere = findWhere (criteriaMask, false, isAll(matchType), operandType, caseSensitivenessType);
		String queryOrder = findOrder (orderMask, sortOrder);
	    return getHQuery(what, queryWhere, queryOrder);
    }
	
    protected List<MyGoodProduct> searchPrototype (MyGoodProduct mygoodproduct, MyGoodProduct orderMask, QuerySortOrder sortOrder, Integer maxResults) {
       return searchPrototype(getMyGoodProductSelectQuery (getWhereEqualWhereQueryChunk(mygoodproduct), orderMask, sortOrder), maxResults);
    }

    protected List<MyGoodProduct> searchPrototype (MyGoodProduct mygoodproduct, Integer maxResults) {
       return searchPrototype(mygoodproduct, null, null, maxResults);
    }
    
    protected List<MyGoodProduct> searchPrototypeAny (MyGoodProduct mygoodproduct, Integer maxResults) { 
       return searchPrototype(getSearchEqualAnyQuery (mygoodproduct), maxResults);
    }
    
    protected List<MyGoodProduct> searchPrototype (String query, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(query);
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();
    }

    public List<MyGoodProduct> searchPrototypeMyGoodProduct (List<MyGoodProduct> mygoodproducts) {
       return searchPrototype (mygoodproducts, null);
    }

    protected List<MyGoodProduct> searchPrototype (List<MyGoodProduct> mygoodproducts, Integer maxResults) {    
	   return getResultList(getMyGoodProductSearchEqualQuery (null, mygoodproducts));
	}    

    protected List<MyGoodProduct> getResultList (String query) {    
	   Query hquery = entityManager.createQuery(query);            
	   return hquery.getResultList();
	}    

    public List<MyGoodProduct> searchDistinctPrototypeMyGoodProduct (MyGoodProduct mygoodproductMask, List<MyGoodProduct> mygoodproducts) {
        return getResultList(getMyGoodProductSearchEqualQuery (mygoodproductMask, mygoodproducts));    
    }
         
	/**
     * Searches a list of MyGoodProduct entity 
     * @param MyGoodProduct mygoodproductPositive
     * @param MyGoodProduct mygoodproductNegative
     * @return List
     */
    public List<MyGoodProduct> searchPrototypeMyGoodProduct(MyGoodProduct mygoodproductPositive, MyGoodProduct mygoodproductNegative) {
	    return getResultList(getMyGoodProductSearchEqualQuery (mygoodproductPositive, mygoodproductNegative));  
    }

    /**
    * return a string query search on a MyGoodProduct prototype
    */
    protected String getMyGoodProductSelectQuery (String where, MyGoodProduct orderMask, QuerySortOrder sortOrder) {
       return getMyGoodProductSelectQuery (where, findOrder (orderMask, sortOrder));
    }
    protected String getMyGoodProductSelectQuery (String where, String order) {
       StringBuffer query = new StringBuffer();
       StringBuffer queryWhere = new StringBuffer();
       query.append ("SELECT mygoodproduct FROM MyGoodProduct mygoodproduct ");
       return getHQuery(query.toString(), where, order);
    }
    /**
    * return a jql query search on a MyGoodProduct prototype
    */
    protected String getSearchEqualQuery (MyGoodProduct mygoodproduct) {
       return getMyGoodProductSelectQuery (getWhereEqualWhereQueryChunk(mygoodproduct),null);
    }
    protected String getWhereEqualWhereQueryChunk (MyGoodProduct mygoodproduct) {
       return getWhereEqualWhereQueryChunk(mygoodproduct, false);
    }
    /**
    * return a jql query search on a MyGoodProduct with any prototype
    */
    protected String getSearchEqualAnyQuery (MyGoodProduct mygoodproduct) {
       return getMyGoodProductSelectQuery (getWhereEqualAnyWhereQueryChunk(mygoodproduct), null);   
    }
    protected String getWhereEqualAnyWhereQueryChunk (MyGoodProduct mygoodproduct) {
       return getWhereEqualAnyWhereQueryChunk(mygoodproduct, false);   
    }

    /**
    * return a jql search for a list of MyGoodProduct prototype
    */
    protected String getMyGoodProductSearchEqualQuery (MyGoodProduct mygoodproductMask, List<MyGoodProduct> mygoodproducts) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (mygoodproductMask !=null)
           query.append (getMyGoodProductMaskWhat (mygoodproductMask));
        query.append (" FROM MyGoodProduct mygoodproduct ");
        StringBuffer queryWhere = new StringBuffer();
        for (MyGoodProduct mygoodproduct : mygoodproducts) {
           if (!isAllNull(mygoodproduct)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;
	           queryWhere.append (" ("+getWhereEqualWhereQueryChunk(mygoodproduct, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    /**
    * return a jql search for a list of MyGoodProduct prototype
    */
    protected String getSearchEqualAnyQuery (MyGoodProduct mygoodproductMask, List<MyGoodProduct> mygoodproducts) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (mygoodproductMask !=null)
           query.append (getMyGoodProductMaskWhat (mygoodproductMask));
        query.append (" FROM MyGoodProduct mygoodproduct ");
        StringBuffer queryWhere = new StringBuffer();
        for (MyGoodProduct mygoodproduct : mygoodproducts) {
           if (!isAllNull(mygoodproduct)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getWhereEqualAnyWhereQueryChunk(mygoodproduct, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getMyGoodProductMaskWhat (MyGoodProduct mygoodproductMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (mygoodproductMask.getProductid() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" productid ");
        }
        if (mygoodproductMask.getCategoryid() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" categoryid ");
        }
        if (mygoodproductMask.getName() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" name ");
        }
        if (mygoodproductMask.getDescription() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" description ");
        }
        if (mygoodproductMask.getImageurl() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" imageurl ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();
    }
    
    protected String getWhereEqualAnyWhereQueryChunk (MyGoodProduct mygoodproduct, boolean isAndSet) {
		return getSearchEqualWhereQueryChunk (mygoodproduct, isAndSet, false);	
	}
	
    protected String getWhereEqualWhereQueryChunk (MyGoodProduct mygoodproduct, boolean isAndSet) {
		return getSearchEqualWhereQueryChunk (mygoodproduct, isAndSet, true);
	}
	
    protected String getSearchEqualWhereQueryChunk (MyGoodProduct mygoodproduct, boolean isAndSet, boolean isAll) {
        StringBuffer query = new StringBuffer();
        if (mygoodproduct.getProductid() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygoodproduct.productid = '"+ mygoodproduct.getProductid()+"' ");
        }
        if (mygoodproduct.getCategoryid_() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygoodproduct.categoryid_ = '"+ mygoodproduct.getCategoryid_()+"' ");
        }
        if (mygoodproduct.getName() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygoodproduct.name = '"+ mygoodproduct.getName()+"' ");
        }
        if (mygoodproduct.getDescription() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygoodproduct.description = '"+ mygoodproduct.getDescription()+"' ");
        }
        if (mygoodproduct.getImageurl() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygoodproduct.imageurl = '"+ mygoodproduct.getImageurl()+"' ");
        }
	    return query.toString();
    }

    protected String findOrder (MyGoodProduct orderMask, QuerySortOrder sortOrder) {
        if (orderMask!=null) {
            String orderColumn = getFirstNotNullColumnOtherWiseNull(orderMask);
            if (orderColumn!=null)
               return orderColumn + " " + sortOrder;
        }
        return "";
    }

	// indirection
    protected String findWhere (MyGoodProduct mygoodproduct, boolean isAndSet, boolean isAll, OperandType operandType, boolean caseSensitive) {
		return findWhere (null, mygoodproduct, isAndSet, isAll, operandType, caseSensitive);
	}
	
	protected static String findWhere (String alias, MyGoodProduct mygoodproduct, boolean isAndSet, boolean isAll, OperandType operandType, boolean caseSensitive) {
        if (alias==null)
			alias = "mygoodproduct";
		StringBuffer query = new StringBuffer();
		String operand = getOperand (operandType);
		String evaluatorPrefix = getEvaluatorPrefix (operandType);		
		String evaluatorSuffix = getEvaluatorSuffix (operandType);		
        if (mygoodproduct.getProductid() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = mygoodproduct.getProductid();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".productid) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".productid "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (mygoodproduct.getCategoryid_() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".categoryid_ = '"+ mygoodproduct.getCategoryid_()+"' ");
        }
        if (mygoodproduct.getName() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = mygoodproduct.getName();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".name) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".name "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (mygoodproduct.getDescription() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = mygoodproduct.getDescription();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".description) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".description "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (mygoodproduct.getImageurl() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = mygoodproduct.getImageurl();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".imageurl) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".imageurl "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        return query.toString();
    }
	
	protected String getFirstNotNullColumnOtherWiseNull (MyGoodProduct mask) {
        if (mask == null) return null;
        if (mask.getProductid() != null) return "productid";
        if (mask.getCategoryid_() != null) return "categoryid";
        if (mask.getName() != null) return "name";
        if (mask.getDescription() != null) return "description";
        if (mask.getImageurl() != null) return "imageurl";
        return null;	
	}
    
    /**
    * return a jql search on a MyGoodProduct prototype with positive and negative beans
    */
    protected String getMyGoodProductSearchEqualQuery (MyGoodProduct mygoodproductPositive, MyGoodProduct mygoodproductNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT mygoodproduct FROM MyGoodProduct mygoodproduct ");
        if (mygoodproductPositive!=null && mygoodproductPositive.getProductid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygoodproduct.productid = '"+ mygoodproductPositive.getProductid()+"' ");
        } else if (mygoodproductNegative.getProductid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygoodproduct.productid is null ");
        }
        if (mygoodproductPositive!=null && mygoodproductPositive.getCategoryid_() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygoodproduct.categoryid_ = '"+ mygoodproductPositive.getCategoryid_()+"' ");
        } else if (mygoodproductNegative.getCategoryid_() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygoodproduct.categoryid_ is null ");
        }
        if (mygoodproductPositive!=null && mygoodproductPositive.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygoodproduct.name = '"+ mygoodproductPositive.getName()+"' ");
        } else if (mygoodproductNegative.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygoodproduct.name is null ");
        }
        if (mygoodproductPositive!=null && mygoodproductPositive.getDescription() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygoodproduct.description = '"+ mygoodproductPositive.getDescription()+"' ");
        } else if (mygoodproductNegative.getDescription() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygoodproduct.description is null ");
        }
        if (mygoodproductPositive!=null && mygoodproductPositive.getImageurl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygoodproduct.imageurl = '"+ mygoodproductPositive.getImageurl()+"' ");
        } else if (mygoodproductNegative.getImageurl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygoodproduct.imageurl is null ");
        }
	    return query.toString();
    }
 
   
    protected EntityManager getEntityManager () {
        return entityManager;    
    }


}
