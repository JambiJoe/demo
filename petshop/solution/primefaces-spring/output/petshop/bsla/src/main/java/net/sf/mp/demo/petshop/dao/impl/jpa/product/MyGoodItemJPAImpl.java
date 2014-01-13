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
import net.sf.mp.demo.petshop.dao.face.product.MyGoodItemDao;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.mp.demo.petshop.domain.pet.Address;
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * <p>Title: MyGoodItemJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with MyGoodItemJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching MyGoodItemJPAImpl objects</p>
 *
 */


@org.springframework.stereotype.Repository(value="myGoodItemDao")
public class MyGoodItemJPAImpl implements MyGoodItemDao {
	public MyGoodItemJPAImpl () {}
	
    @PersistenceContext
    EntityManager entityManager;
	
    /**
     * Inserts a MyGoodItem entity 
     * @param MyGoodItem mygooditem
     */
    public void insertMyGoodItem(MyGoodItem mygooditem) {
       entityManager.persist(mygooditem);
    }

    protected void insertMyGoodItem(EntityManager emForRecursiveDao, MyGoodItem mygooditem) {
       emForRecursiveDao.persist(mygooditem);
    } 
    /**
     * Inserts a list of MyGoodItem entity 
     * @param List<MyGoodItem> mygooditems
     */
    public void insertMyGoodItems(List<MyGoodItem> mygooditems) {
    	//TODO
    }
    /**
     * Updates a MyGoodItem entity 
     * @param MyGoodItem mygooditem
     */
    public MyGoodItem updateMyGoodItem(MyGoodItem mygooditem) {
       return entityManager.merge(mygooditem);
    }

	/**
     * Updates a MyGoodItem entity with only the attributes set into MyGoodItem.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param MyGoodItem mygooditem
    */ 
    @Transactional
    public int updateNotNullOnlyMyGoodItem(MyGoodItem mygooditem) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyMyGoodItemQueryChunk(mygooditem));
        if (mygooditem.getItemid() != null) {
           jpaQuery.setParameter ("itemid", mygooditem.getItemid());
        }   
        if (mygooditem.getThisIsMyProduct_() != null) {
           jpaQuery.setParameter ("thisIsMyProduct_", mygooditem.getThisIsMyProduct_());
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
        if (mygooditem.getAddressAddressid_() != null) {
           jpaQuery.setParameter ("addressAddressid_", mygooditem.getAddressAddressid_());
        }   
        if (mygooditem.getContactinfoContactinfoid_() != null) {
           jpaQuery.setParameter ("contactinfoContactinfoid_", mygooditem.getContactinfoContactinfoid_());
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

    protected String getUpdateNotNullOnlyMyGoodItemQueryChunkPrototype (MyGoodItem mygooditem) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update MyGoodItem mygooditem ");
        if (mygooditem.getThisIsMyProduct() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.thisIsMyProduct = :thisIsMyProduct");
        }
        if (mygooditem.getName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.name = :name");
        }
        if (mygooditem.getDescription() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.description = :description");
        }
        if (mygooditem.getImageurl() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.imageurl = :imageurl");
        }
        if (mygooditem.getImagethumburl() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.imagethumburl = :imagethumburl");
        }
        if (mygooditem.getPrice() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.price = :price");
        }
        if (mygooditem.getAddressAddressid() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.addressAddressid = :addressAddressid");
        }
        if (mygooditem.getContactinfoContactinfoid() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.contactinfoContactinfoid = :contactinfoContactinfoid");
        }
        if (mygooditem.getTotalscore() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.totalscore = :totalscore");
        }
        if (mygooditem.getNumberofvotes() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.numberofvotes = :numberofvotes");
        }
        if (mygooditem.getDisabled() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.disabled = :disabled");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyMyGoodItemQueryChunk (MyGoodItem mygooditem) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyMyGoodItemQueryChunkPrototype(mygooditem));
        if (mygooditem.getItemid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" mygooditem.itemid = :itemid");
        }
        return query.toString();
    }
    
                
	protected MyGoodItem assignBlankToNull (MyGoodItem mygooditem) {
        if (mygooditem==null)
			return null;
        if (mygooditem.getName()!=null && mygooditem.getName().equals(""))
           mygooditem.setName((String)null);
        if (mygooditem.getDescription()!=null && mygooditem.getDescription().equals(""))
           mygooditem.setDescription((String)null);
        if (mygooditem.getImageurl()!=null && mygooditem.getImageurl().equals(""))
           mygooditem.setImageurl((String)null);
        if (mygooditem.getImagethumburl()!=null && mygooditem.getImagethumburl().equals(""))
           mygooditem.setImagethumburl((String)null);
        //foreign key productid
        if (mygooditem.getThisIsMyProduct_()!=null && mygooditem.getThisIsMyProduct_().equals(""))
           mygooditem.setThisIsMyProduct_((String)null);
		return mygooditem;
	}
	
	protected boolean isAllNull (MyGoodItem mygooditem) {
	    if (mygooditem==null)
			return true;
        if (mygooditem.getItemid()!=null) 
            return false;
        if (mygooditem.getThisIsMyProduct()!=null) 
            return false;
        if (mygooditem.getName()!=null) 
            return false;
        if (mygooditem.getDescription()!=null) 
            return false;
        if (mygooditem.getImageurl()!=null) 
            return false;
        if (mygooditem.getImagethumburl()!=null) 
            return false;
        if (mygooditem.getPrice()!=null) 
            return false;
        if (mygooditem.getAddressAddressid()!=null) 
            return false;
        if (mygooditem.getContactinfoContactinfoid()!=null) 
            return false;
        if (mygooditem.getTotalscore()!=null) 
            return false;
        if (mygooditem.getNumberofvotes()!=null) 
            return false;
        if (mygooditem.getDisabled()!=null) 
            return false;
		return true;
	}
		
    @Transactional
    public int updateNotNullOnlyPrototypeMyGoodItem(MyGoodItem mygooditem, MyGoodItem prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update MyGoodItem mygooditem ");
        if (mygooditem.getItemid() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.itemid = "+ mygooditem.getItemid() + " ");
        }
        if (mygooditem.getThisIsMyProduct_() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.thisIsMyProduct_ = '"+ mygooditem.getThisIsMyProduct_()+"' ");
        }
        if (mygooditem.getName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.name = '"+ mygooditem.getName()+"' ");
        }
        if (mygooditem.getDescription() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.description = '"+ mygooditem.getDescription()+"' ");
        }
        if (mygooditem.getImageurl() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.imageurl = '"+ mygooditem.getImageurl()+"' ");
        }
        if (mygooditem.getImagethumburl() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.imagethumburl = '"+ mygooditem.getImagethumburl()+"' ");
        }
        if (mygooditem.getPrice() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.price = "+ mygooditem.getPrice() + " ");
        }
        if (mygooditem.getAddressAddressid_() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.addressAddressid_ = "+ mygooditem.getAddressAddressid_() + " ");
        }
        if (mygooditem.getContactinfoContactinfoid_() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.contactinfoContactinfoid_ = "+ mygooditem.getContactinfoContactinfoid_() + " ");
        }
        if (mygooditem.getTotalscore() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.totalscore = "+ mygooditem.getTotalscore() + " ");
        }
        if (mygooditem.getNumberofvotes() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.numberofvotes = "+ mygooditem.getNumberofvotes() + " ");
        }
        if (mygooditem.getDisabled() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" mygooditem.disabled = "+ mygooditem.getDisabled() + " ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getItemid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.itemid = "+ prototypeCriteria.getItemid() + " ");
        }
        if (prototypeCriteria.getThisIsMyProduct_() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.thisIsMyProduct_ = '"+ prototypeCriteria.getThisIsMyProduct_()+"' ");
        }
        if (prototypeCriteria.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.name = '"+ prototypeCriteria.getName()+"' ");
        }
        if (prototypeCriteria.getDescription() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.description = '"+ prototypeCriteria.getDescription()+"' ");
        }
        if (prototypeCriteria.getImageurl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.imageurl = '"+ prototypeCriteria.getImageurl()+"' ");
        }
        if (prototypeCriteria.getImagethumburl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.imagethumburl = '"+ prototypeCriteria.getImagethumburl()+"' ");
        }
        if (prototypeCriteria.getPrice() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.price = "+ prototypeCriteria.getPrice() + " ");
        }
        if (prototypeCriteria.getAddressAddressid_() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.addressAddressid_ = "+ prototypeCriteria.getAddressAddressid_() + " ");
        }
        if (prototypeCriteria.getContactinfoContactinfoid_() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.contactinfoContactinfoid_ = "+ prototypeCriteria.getContactinfoContactinfoid_() + " ");
        }
        if (prototypeCriteria.getTotalscore() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.totalscore = "+ prototypeCriteria.getTotalscore() + " ");
        }
        if (prototypeCriteria.getNumberofvotes() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.numberofvotes = "+ prototypeCriteria.getNumberofvotes() + " ");
        }
        if (prototypeCriteria.getDisabled() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.disabled = "+ prototypeCriteria.getDisabled() + " ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a MyGoodItem entity 
     * @param MyGoodItem mygooditem
     */
    public void saveMyGoodItem(MyGoodItem mygooditem) {
       //entityManager.persist(mygooditem);
       if (entityManager.contains(mygooditem)) {
          entityManager.merge(mygooditem);
       } else {
          entityManager.persist(mygooditem);
       }
       entityManager.flush(); 
    }
       
    /**
     * Deletes a MyGoodItem entity 
     * @param MyGoodItem mygooditem
     */
    public void deleteMyGoodItem(MyGoodItem mygooditem) {
      entityManager.remove(mygooditem);
    }
    
    /**
     * Loads the MyGoodItem entity which is related to an instance of
     * MyGoodItem
     * @param Long id
     * @return MyGoodItem The MyGoodItem entity
     
    public MyGoodItem loadMyGoodItem(Long id) {
    	return (MyGoodItem)entityManager.get(MyGoodItem.class, id);
    }
*/
  
    /**
     * Loads the MyGoodItem entity which is related to an instance of
     * MyGoodItem
     * @param java.lang.Integer Itemid
     * @return MyGoodItem The MyGoodItem entity
     */
    public MyGoodItem loadMyGoodItem(java.lang.Integer itemid) {
    	return (MyGoodItem)entityManager.find(MyGoodItem.class, itemid);
    }
    
    /**
     * Loads a list of MyGoodItem entity 
     * @param List<java.lang.Integer> itemids
     * @return List<MyGoodItem> The MyGoodItem entity
     */
    public List<MyGoodItem> loadMyGoodItemListByMyGoodItem (List<MyGoodItem> mygooditems) {
       return null;
    }
    
    /**
     * Loads a list of MyGoodItem entity 
     * @param List<java.lang.Integer> itemids
     * @return List<MyGoodItem> The MyGoodItem entity
     */
    public List<MyGoodItem> loadMyGoodItemListByItemid(List<java.lang.Integer> itemids){
       return null;
    }
    
    /**
     * Loads the MyGoodItem entity which is related to an instance of
     * MyGoodItem and its dependent one to many objects
     * @param Long id
     * @return MyGoodItem The MyGoodItem entity
     */
    public MyGoodItem loadFullFirstLevelMyGoodItem(java.lang.Integer itemid) {
        List list = getResultList(
                     "SELECT mygooditem FROM MyGoodItem mygooditem "
                     + " LEFT JOIN mygooditem.tagItemItemids "   
                     + " WHERE mygooditem.itemid = "+itemid
               );
         if (list!=null && !list.isEmpty())
            return (MyGoodItem)list.get(0);
         return null;
    	//return null;//(MyGoodItem) entityManager.queryForObject("loadFullFirstLevelMyGoodItem", id);
    }

    /**
     * Loads the MyGoodItem entity which is related to an instance of
     * MyGoodItem
     * @param MyGoodItem mygooditem
     * @return MyGoodItem The MyGoodItem entity
     */
    public MyGoodItem loadFullFirstLevelMyGoodItem(MyGoodItem mygooditem) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT mygooditem FROM MyGoodItem mygooditem ");
        if (mygooditem.getItemid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.itemid = "+ mygooditem.getItemid() + " ");
         }
        List list = getResultList(query.toString());
        if (list!=null && !list.isEmpty())
           return (MyGoodItem)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the MyGoodItem entity which is related to an instance of
     * MyGoodItem and its dependent objects one to many
     * @param Long id
     * @return MyGoodItem The MyGoodItem entity
     */
    public MyGoodItem loadFullMyGoodItem(Long id) {
    	return null;//(MyGoodItem)entityManager.queryForObject("loadFullMyGoodItem", id);
    }

    /**
     * Searches a list of MyGoodItem entity 
     * @param MyGoodItem mygooditem
     * @return List
     */  
    public List<MyGoodItem> searchPrototypeMyGoodItem(MyGoodItem mygooditem) {
       return searchPrototype (mygooditem, null);
    }  
	
    public List<MyGoodItem> searchPrototypeAnyMyGoodItem(MyGoodItem mygooditem) {
       return searchPrototypeAny (mygooditem, null);
    }  

	// indirection
    public List<MyGoodItem> find (MyGoodItem criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
       return find (criteriaMask, matchType, operandType, caseSensitivenessType, null, null); 
	}
	
	// indirection
	protected List<MyGoodItem> find (MyGoodItem criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, Integer startPosition, Integer maxResults) {
       return find (criteriaMask, null, matchType, operandType, caseSensitivenessType, null, startPosition, maxResults); 
    }
	
	// indirection
	protected List<MyGoodItem> find (MyGoodItem criteriaMask, MyGoodItem orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Integer startPosition, Integer maxResults) {
       return find (null, criteriaMask, orderMask, matchType, operandType, caseSensitivenessType, sortOrder, startPosition, maxResults);
    }
	
	// main find implementation
	protected List<MyGoodItem> find (MyGoodItem whatMask, MyGoodItem criteriaMask, MyGoodItem orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Integer startPosition, Integer maxResults) {
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
    public String findQuery (MyGoodItem criteriaMask, MyGoodItem orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
        String what = "SELECT mygooditem FROM MyGoodItem mygooditem ";
		return findQuery (criteriaMask, orderMask, what, matchType, operandType, caseSensitivenessType, sortOrder);
    }

	/**
	 *   find partial on entity
	 *
	 */
    public String findPartialQuery (MyGoodItem whatMask, MyGoodItem criteriaMask, MyGoodItem orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Map beanPath) {
        return "to override ";
    }
	
	public List<MyGoodItem> handlePartialLoadWithParentResult(MyGoodItem what, List list, Map beanPath) {
		return null; //TO Override
	}
	
    protected String findQuery (MyGoodItem criteriaMask, MyGoodItem orderMask, String what, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
        String queryWhere = findWhere (criteriaMask, false, isAll(matchType), operandType, caseSensitivenessType);
		String queryOrder = findOrder (orderMask, sortOrder);
	    return getHQuery(what, queryWhere, queryOrder);
    }
	
    protected List<MyGoodItem> searchPrototype (MyGoodItem mygooditem, MyGoodItem orderMask, QuerySortOrder sortOrder, Integer maxResults) {
       return searchPrototype(getMyGoodItemSelectQuery (getWhereEqualWhereQueryChunk(mygooditem), orderMask, sortOrder), maxResults);
    }

    protected List<MyGoodItem> searchPrototype (MyGoodItem mygooditem, Integer maxResults) {
       return searchPrototype(mygooditem, null, null, maxResults);
    }
    
    protected List<MyGoodItem> searchPrototypeAny (MyGoodItem mygooditem, Integer maxResults) { 
       return searchPrototype(getSearchEqualAnyQuery (mygooditem), maxResults);
    }
    
    protected List<MyGoodItem> searchPrototype (String query, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(query);
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();
    }

    public List<MyGoodItem> searchPrototypeMyGoodItem (List<MyGoodItem> mygooditems) {
       return searchPrototype (mygooditems, null);
    }

    protected List<MyGoodItem> searchPrototype (List<MyGoodItem> mygooditems, Integer maxResults) {    
	   return getResultList(getMyGoodItemSearchEqualQuery (null, mygooditems));
	}    

    protected List<MyGoodItem> getResultList (String query) {    
	   Query hquery = entityManager.createQuery(query);            
	   return hquery.getResultList();
	}    

    public List<MyGoodItem> searchDistinctPrototypeMyGoodItem (MyGoodItem mygooditemMask, List<MyGoodItem> mygooditems) {
        return getResultList(getMyGoodItemSearchEqualQuery (mygooditemMask, mygooditems));    
    }
         
	/**
     * Searches a list of MyGoodItem entity 
     * @param MyGoodItem mygooditemPositive
     * @param MyGoodItem mygooditemNegative
     * @return List
     */
    public List<MyGoodItem> searchPrototypeMyGoodItem(MyGoodItem mygooditemPositive, MyGoodItem mygooditemNegative) {
	    return getResultList(getMyGoodItemSearchEqualQuery (mygooditemPositive, mygooditemNegative));  
    }

    /**
    * return a string query search on a MyGoodItem prototype
    */
    protected String getMyGoodItemSelectQuery (String where, MyGoodItem orderMask, QuerySortOrder sortOrder) {
       return getMyGoodItemSelectQuery (where, findOrder (orderMask, sortOrder));
    }
    protected String getMyGoodItemSelectQuery (String where, String order) {
       StringBuffer query = new StringBuffer();
       StringBuffer queryWhere = new StringBuffer();
       query.append ("SELECT mygooditem FROM MyGoodItem mygooditem ");
       return getHQuery(query.toString(), where, order);
    }
    /**
    * return a jql query search on a MyGoodItem prototype
    */
    protected String getSearchEqualQuery (MyGoodItem mygooditem) {
       return getMyGoodItemSelectQuery (getWhereEqualWhereQueryChunk(mygooditem),null);
    }
    protected String getWhereEqualWhereQueryChunk (MyGoodItem mygooditem) {
       return getWhereEqualWhereQueryChunk(mygooditem, false);
    }
    /**
    * return a jql query search on a MyGoodItem with any prototype
    */
    protected String getSearchEqualAnyQuery (MyGoodItem mygooditem) {
       return getMyGoodItemSelectQuery (getWhereEqualAnyWhereQueryChunk(mygooditem), null);   
    }
    protected String getWhereEqualAnyWhereQueryChunk (MyGoodItem mygooditem) {
       return getWhereEqualAnyWhereQueryChunk(mygooditem, false);   
    }

    /**
    * return a jql search for a list of MyGoodItem prototype
    */
    protected String getMyGoodItemSearchEqualQuery (MyGoodItem mygooditemMask, List<MyGoodItem> mygooditems) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (mygooditemMask !=null)
           query.append (getMyGoodItemMaskWhat (mygooditemMask));
        query.append (" FROM MyGoodItem mygooditem ");
        StringBuffer queryWhere = new StringBuffer();
        for (MyGoodItem mygooditem : mygooditems) {
           if (!isAllNull(mygooditem)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;
	           queryWhere.append (" ("+getWhereEqualWhereQueryChunk(mygooditem, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    /**
    * return a jql search for a list of MyGoodItem prototype
    */
    protected String getSearchEqualAnyQuery (MyGoodItem mygooditemMask, List<MyGoodItem> mygooditems) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (mygooditemMask !=null)
           query.append (getMyGoodItemMaskWhat (mygooditemMask));
        query.append (" FROM MyGoodItem mygooditem ");
        StringBuffer queryWhere = new StringBuffer();
        for (MyGoodItem mygooditem : mygooditems) {
           if (!isAllNull(mygooditem)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getWhereEqualAnyWhereQueryChunk(mygooditem, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getMyGoodItemMaskWhat (MyGoodItem mygooditemMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (mygooditemMask.getItemid() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" itemid ");
        }
        if (mygooditemMask.getThisIsMyProduct() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" thisIsMyProduct ");
        }
        if (mygooditemMask.getName() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" name ");
        }
        if (mygooditemMask.getDescription() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" description ");
        }
        if (mygooditemMask.getImageurl() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" imageurl ");
        }
        if (mygooditemMask.getImagethumburl() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" imagethumburl ");
        }
        if (mygooditemMask.getPrice() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" price ");
        }
        if (mygooditemMask.getAddressAddressid() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" addressAddressid ");
        }
        if (mygooditemMask.getContactinfoContactinfoid() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" contactinfoContactinfoid ");
        }
        if (mygooditemMask.getTotalscore() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" totalscore ");
        }
        if (mygooditemMask.getNumberofvotes() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" numberofvotes ");
        }
        if (mygooditemMask.getDisabled() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" disabled ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();
    }
    
    protected String getWhereEqualAnyWhereQueryChunk (MyGoodItem mygooditem, boolean isAndSet) {
		return getSearchEqualWhereQueryChunk (mygooditem, isAndSet, false);	
	}
	
    protected String getWhereEqualWhereQueryChunk (MyGoodItem mygooditem, boolean isAndSet) {
		return getSearchEqualWhereQueryChunk (mygooditem, isAndSet, true);
	}
	
    protected String getSearchEqualWhereQueryChunk (MyGoodItem mygooditem, boolean isAndSet, boolean isAll) {
        StringBuffer query = new StringBuffer();
        if (mygooditem.getItemid() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygooditem.itemid = "+ mygooditem.getItemid() + " ");
        }
        if (mygooditem.getThisIsMyProduct_() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygooditem.thisIsMyProduct_ = '"+ mygooditem.getThisIsMyProduct_()+"' ");
        }
        if (mygooditem.getName() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygooditem.name = '"+ mygooditem.getName()+"' ");
        }
        if (mygooditem.getDescription() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygooditem.description = '"+ mygooditem.getDescription()+"' ");
        }
        if (mygooditem.getImageurl() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygooditem.imageurl = '"+ mygooditem.getImageurl()+"' ");
        }
        if (mygooditem.getImagethumburl() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygooditem.imagethumburl = '"+ mygooditem.getImagethumburl()+"' ");
        }
        if (mygooditem.getPrice() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygooditem.price = "+ mygooditem.getPrice() + " ");
        }
        if (mygooditem.getAddressAddressid_() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygooditem.addressAddressid_ = "+ mygooditem.getAddressAddressid_() + " ");
        }
        if (mygooditem.getContactinfoContactinfoid_() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygooditem.contactinfoContactinfoid_ = "+ mygooditem.getContactinfoContactinfoid_() + " ");
        }
        if (mygooditem.getTotalscore() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygooditem.totalscore = "+ mygooditem.getTotalscore() + " ");
        }
        if (mygooditem.getNumberofvotes() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygooditem.numberofvotes = "+ mygooditem.getNumberofvotes() + " ");
        }
        if (mygooditem.getDisabled() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" mygooditem.disabled = "+ mygooditem.getDisabled() + " ");
        }
	    return query.toString();
    }

    protected String findOrder (MyGoodItem orderMask, QuerySortOrder sortOrder) {
        if (orderMask!=null) {
            String orderColumn = getFirstNotNullColumnOtherWiseNull(orderMask);
            if (orderColumn!=null)
               return orderColumn + " " + sortOrder;
        }
        return "";
    }

	// indirection
    protected String findWhere (MyGoodItem mygooditem, boolean isAndSet, boolean isAll, OperandType operandType, boolean caseSensitive) {
		return findWhere (null, mygooditem, isAndSet, isAll, operandType, caseSensitive);
	}
	
	protected static String findWhere (String alias, MyGoodItem mygooditem, boolean isAndSet, boolean isAll, OperandType operandType, boolean caseSensitive) {
        if (alias==null)
			alias = "mygooditem";
		StringBuffer query = new StringBuffer();
		String operand = getOperand (operandType);
		String evaluatorPrefix = getEvaluatorPrefix (operandType);		
		String evaluatorSuffix = getEvaluatorSuffix (operandType);		
        if (mygooditem.getItemid() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".itemid = "+ mygooditem.getItemid() + " ");
        }
        if (mygooditem.getThisIsMyProduct_() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".thisIsMyProduct_ = '"+ mygooditem.getThisIsMyProduct_()+"' ");
        }
        if (mygooditem.getName() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = mygooditem.getName();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".name) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".name "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (mygooditem.getDescription() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = mygooditem.getDescription();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".description) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".description "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (mygooditem.getImageurl() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = mygooditem.getImageurl();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".imageurl) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".imageurl "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (mygooditem.getImagethumburl() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = mygooditem.getImagethumburl();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".imagethumburl) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".imagethumburl "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (mygooditem.getPrice() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".price = "+ mygooditem.getPrice() + " ");
        }
        if (mygooditem.getAddressAddressid_() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".addressAddressid_ = "+ mygooditem.getAddressAddressid_() + " ");
        }
        if (mygooditem.getContactinfoContactinfoid_() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".contactinfoContactinfoid_ = "+ mygooditem.getContactinfoContactinfoid_() + " ");
        }
        if (mygooditem.getTotalscore() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".totalscore = "+ mygooditem.getTotalscore() + " ");
        }
        if (mygooditem.getNumberofvotes() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".numberofvotes = "+ mygooditem.getNumberofvotes() + " ");
        }
        if (mygooditem.getDisabled() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".disabled = "+ mygooditem.getDisabled() + " ");
        }
        return query.toString();
    }
	
	protected String getFirstNotNullColumnOtherWiseNull (MyGoodItem mask) {
        if (mask == null) return null;
        if (mask.getItemid() != null) return "itemid";
        if (mask.getThisIsMyProduct_() != null) return "thisIsMyProduct";
        if (mask.getName() != null) return "name";
        if (mask.getDescription() != null) return "description";
        if (mask.getImageurl() != null) return "imageurl";
        if (mask.getImagethumburl() != null) return "imagethumburl";
        if (mask.getPrice() != null) return "price";
        if (mask.getAddressAddressid_() != null) return "addressAddressid";
        if (mask.getContactinfoContactinfoid_() != null) return "contactinfoContactinfoid";
        if (mask.getTotalscore() != null) return "totalscore";
        if (mask.getNumberofvotes() != null) return "numberofvotes";
        if (mask.getDisabled() != null) return "disabled";
        return null;	
	}
    
    /**
    * return a jql search on a MyGoodItem prototype with positive and negative beans
    */
    protected String getMyGoodItemSearchEqualQuery (MyGoodItem mygooditemPositive, MyGoodItem mygooditemNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT mygooditem FROM MyGoodItem mygooditem ");
        if (mygooditemPositive!=null && mygooditemPositive.getItemid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.itemid = "+ mygooditemPositive.getItemid() + " ");
        } else if (mygooditemNegative.getItemid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygooditem.itemid is null ");
        }
        if (mygooditemPositive!=null && mygooditemPositive.getThisIsMyProduct_() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.thisIsMyProduct_ = '"+ mygooditemPositive.getThisIsMyProduct_()+"' ");
        } else if (mygooditemNegative.getThisIsMyProduct_() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygooditem.thisIsMyProduct_ is null ");
        }
        if (mygooditemPositive!=null && mygooditemPositive.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.name = '"+ mygooditemPositive.getName()+"' ");
        } else if (mygooditemNegative.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygooditem.name is null ");
        }
        if (mygooditemPositive!=null && mygooditemPositive.getDescription() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.description = '"+ mygooditemPositive.getDescription()+"' ");
        } else if (mygooditemNegative.getDescription() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygooditem.description is null ");
        }
        if (mygooditemPositive!=null && mygooditemPositive.getImageurl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.imageurl = '"+ mygooditemPositive.getImageurl()+"' ");
        } else if (mygooditemNegative.getImageurl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygooditem.imageurl is null ");
        }
        if (mygooditemPositive!=null && mygooditemPositive.getImagethumburl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.imagethumburl = '"+ mygooditemPositive.getImagethumburl()+"' ");
        } else if (mygooditemNegative.getImagethumburl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygooditem.imagethumburl is null ");
        }
        if (mygooditemPositive!=null && mygooditemPositive.getPrice() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.price = "+ mygooditemPositive.getPrice() + " ");
        } else if (mygooditemNegative.getPrice() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygooditem.price is null ");
        }
        if (mygooditemPositive!=null && mygooditemPositive.getAddressAddressid_() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.addressAddressid_ = "+ mygooditemPositive.getAddressAddressid_() + " ");
        } else if (mygooditemNegative.getAddressAddressid_() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygooditem.addressAddressid_ is null ");
        }
        if (mygooditemPositive!=null && mygooditemPositive.getContactinfoContactinfoid_() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.contactinfoContactinfoid_ = "+ mygooditemPositive.getContactinfoContactinfoid_() + " ");
        } else if (mygooditemNegative.getContactinfoContactinfoid_() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygooditem.contactinfoContactinfoid_ is null ");
        }
        if (mygooditemPositive!=null && mygooditemPositive.getTotalscore() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.totalscore = "+ mygooditemPositive.getTotalscore() + " ");
        } else if (mygooditemNegative.getTotalscore() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygooditem.totalscore is null ");
        }
        if (mygooditemPositive!=null && mygooditemPositive.getNumberofvotes() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.numberofvotes = "+ mygooditemPositive.getNumberofvotes() + " ");
        } else if (mygooditemNegative.getNumberofvotes() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygooditem.numberofvotes is null ");
        }
        if (mygooditemPositive!=null && mygooditemPositive.getDisabled() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" mygooditem.disabled = "+ mygooditemPositive.getDisabled() + " ");
        } else if (mygooditemNegative.getDisabled() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" mygooditem.disabled is null ");
        }
	    return query.toString();
    }
 
   
    protected EntityManager getEntityManager () {
        return entityManager;    
    }


}
