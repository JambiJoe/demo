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
import net.sf.mp.demo.petshop.dao.face.pet.CategoryDao;
import net.sf.mp.demo.petshop.domain.pet.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * <p>Title: CategoryJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with CategoryJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching CategoryJPAImpl objects</p>
 *
 */


@org.springframework.stereotype.Repository(value="categoryDao")
public class CategoryJPAImpl implements CategoryDao {
	public CategoryJPAImpl () {}
	
    @PersistenceContext
    EntityManager entityManager;
	
    /**
     * Inserts a Category entity 
     * @param Category category
     */
    public void insertCategory(Category category) {
       entityManager.persist(category);
    }

    protected void insertCategory(EntityManager emForRecursiveDao, Category category) {
       emForRecursiveDao.persist(category);
    } 
    /**
     * Inserts a list of Category entity 
     * @param List<Category> categorys
     */
    public void insertCategorys(List<Category> categorys) {
    	//TODO
    }
    /**
     * Updates a Category entity 
     * @param Category category
     */
    public Category updateCategory(Category category) {
       return entityManager.merge(category);
    }

	/**
     * Updates a Category entity with only the attributes set into Category.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param Category category
    */ 
    @Transactional
    public int updateNotNullOnlyCategory(Category category) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyCategoryQueryChunk(category));
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

    protected String getUpdateNotNullOnlyCategoryQueryChunkPrototype (Category category) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Category category ");
        if (category.getName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" category.name = :name");
        }
        if (category.getDescription() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" category.description = :description");
        }
        if (category.getImageurl() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" category.imageurl = :imageurl");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyCategoryQueryChunk (Category category) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyCategoryQueryChunkPrototype(category));
        if (category.getCategoryid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" category.categoryid = :categoryid");
        }
        return query.toString();
    }
    
                
	protected Category assignBlankToNull (Category category) {
        if (category==null)
			return null;
        if (category.getName()!=null && category.getName().equals(""))
           category.setName((String)null);
        if (category.getDescription()!=null && category.getDescription().equals(""))
           category.setDescription((String)null);
        if (category.getImageurl()!=null && category.getImageurl().equals(""))
           category.setImageurl((String)null);
		return category;
	}
	
	protected boolean isAllNull (Category category) {
	    if (category==null)
			return true;
        if (category.getCategoryid()!=null) 
            return false;
        if (category.getName()!=null) 
            return false;
        if (category.getDescription()!=null) 
            return false;
        if (category.getImageurl()!=null) 
            return false;
		return true;
	}
		
    @Transactional
    public int updateNotNullOnlyPrototypeCategory(Category category, Category prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Category category ");
        if (category.getCategoryid() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" category.categoryid = '"+ category.getCategoryid()+"' ");
        }
        if (category.getName() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" category.name = '"+ category.getName()+"' ");
        }
        if (category.getDescription() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" category.description = '"+ category.getDescription()+"' ");
        }
        if (category.getImageurl() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" category.imageurl = '"+ category.getImageurl()+"' ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getCategoryid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" category.categoryid = '"+ prototypeCriteria.getCategoryid()+"' ");
        }
        if (prototypeCriteria.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" category.name = '"+ prototypeCriteria.getName()+"' ");
        }
        if (prototypeCriteria.getDescription() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" category.description = '"+ prototypeCriteria.getDescription()+"' ");
        }
        if (prototypeCriteria.getImageurl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" category.imageurl = '"+ prototypeCriteria.getImageurl()+"' ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a Category entity 
     * @param Category category
     */
    public void saveCategory(Category category) {
       //entityManager.persist(category);
       if (entityManager.contains(category)) {
          entityManager.merge(category);
       } else {
          entityManager.persist(category);
       }
       entityManager.flush(); 
    }
       
    /**
     * Deletes a Category entity 
     * @param Category category
     */
    public void deleteCategory(Category category) {
      entityManager.remove(category);
    }
    
    /**
     * Loads the Category entity which is related to an instance of
     * Category
     * @param Long id
     * @return Category The Category entity
     
    public Category loadCategory(Long id) {
    	return (Category)entityManager.get(Category.class, id);
    }
*/
  
    /**
     * Loads the Category entity which is related to an instance of
     * Category
     * @param java.lang.String Categoryid
     * @return Category The Category entity
     */
    public Category loadCategory(java.lang.String categoryid) {
    	return (Category)entityManager.find(Category.class, categoryid);
    }
    
    /**
     * Loads a list of Category entity 
     * @param List<java.lang.String> categoryids
     * @return List<Category> The Category entity
     */
    public List<Category> loadCategoryListByCategory (List<Category> categorys) {
       return null;
    }
    
    /**
     * Loads a list of Category entity 
     * @param List<java.lang.String> categoryids
     * @return List<Category> The Category entity
     */
    public List<Category> loadCategoryListByCategoryid(List<java.lang.String> categoryids){
       return null;
    }
    
    /**
     * Loads the Category entity which is related to an instance of
     * Category and its dependent one to many objects
     * @param Long id
     * @return Category The Category entity
     */
    public Category loadFullFirstLevelCategory(java.lang.String categoryid) {
        List list = getResultList(
                     "SELECT category FROM Category category "
                     + " LEFT JOIN category.myGoodProductCategoryids "   
                     + " WHERE category.categoryid = "+categoryid
               );
         if (list!=null && !list.isEmpty())
            return (Category)list.get(0);
         return null;
    	//return null;//(Category) entityManager.queryForObject("loadFullFirstLevelCategory", id);
    }

    /**
     * Loads the Category entity which is related to an instance of
     * Category
     * @param Category category
     * @return Category The Category entity
     */
    public Category loadFullFirstLevelCategory(Category category) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT category FROM Category category ");
        query.append (" LEFT JOIN category.myGoodProductCategoryids ");
        if (category.getCategoryid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" category.categoryid = '"+ category.getCategoryid()+"' ");
         }
        List list = getResultList(query.toString());
        if (list!=null && !list.isEmpty())
           return (Category)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the Category entity which is related to an instance of
     * Category and its dependent objects one to many
     * @param Long id
     * @return Category The Category entity
     */
    public Category loadFullCategory(Long id) {
    	return null;//(Category)entityManager.queryForObject("loadFullCategory", id);
    }

    /**
     * Searches a list of Category entity 
     * @param Category category
     * @return List
     */  
    public List<Category> searchPrototypeCategory(Category category) {
       return searchPrototype (category, null);
    }  
	
    public List<Category> searchPrototypeAnyCategory(Category category) {
       return searchPrototypeAny (category, null);
    }  

	// indirection
    public List<Category> find (Category criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
       return find (criteriaMask, matchType, operandType, caseSensitivenessType, null, null); 
	}
	
	// indirection
	protected List<Category> find (Category criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, Integer startPosition, Integer maxResults) {
       return find (criteriaMask, null, matchType, operandType, caseSensitivenessType, null, startPosition, maxResults); 
    }
	
	// indirection
	protected List<Category> find (Category criteriaMask, Category orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Integer startPosition, Integer maxResults) {
       return find (null, criteriaMask, orderMask, matchType, operandType, caseSensitivenessType, sortOrder, startPosition, maxResults);
    }
	
	// main find implementation
	protected List<Category> find (Category whatMask, Category criteriaMask, Category orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Integer startPosition, Integer maxResults) {
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
    public String findQuery (Category criteriaMask, Category orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
        String what = "SELECT category FROM Category category ";
		return findQuery (criteriaMask, orderMask, what, matchType, operandType, caseSensitivenessType, sortOrder);
    }

	/**
	 *   find partial on entity
	 *
	 */
    public String findPartialQuery (Category whatMask, Category criteriaMask, Category orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Map beanPath) {
        return "to override ";
    }
	
	public List<Category> handlePartialLoadWithParentResult(Category what, List list, Map beanPath) {
		return null; //TO Override
	}
	
    protected String findQuery (Category criteriaMask, Category orderMask, String what, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
        String queryWhere = findWhere (criteriaMask, false, isAll(matchType), operandType, caseSensitivenessType);
		String queryOrder = findOrder (orderMask, sortOrder);
	    return getHQuery(what, queryWhere, queryOrder);
    }
	
    protected List<Category> searchPrototype (Category category, Category orderMask, QuerySortOrder sortOrder, Integer maxResults) {
       return searchPrototype(getCategorySelectQuery (getWhereEqualWhereQueryChunk(category), orderMask, sortOrder), maxResults);
    }

    protected List<Category> searchPrototype (Category category, Integer maxResults) {
       return searchPrototype(category, null, null, maxResults);
    }
    
    protected List<Category> searchPrototypeAny (Category category, Integer maxResults) { 
       return searchPrototype(getSearchEqualAnyQuery (category), maxResults);
    }
    
    protected List<Category> searchPrototype (String query, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(query);
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();
    }

    public List<Category> searchPrototypeCategory (List<Category> categorys) {
       return searchPrototype (categorys, null);
    }

    protected List<Category> searchPrototype (List<Category> categorys, Integer maxResults) {    
	   return getResultList(getCategorySearchEqualQuery (null, categorys));
	}    

    protected List<Category> getResultList (String query) {    
	   Query hquery = entityManager.createQuery(query);            
	   return hquery.getResultList();
	}    

    public List<Category> searchDistinctPrototypeCategory (Category categoryMask, List<Category> categorys) {
        return getResultList(getCategorySearchEqualQuery (categoryMask, categorys));    
    }
         
	/**
     * Searches a list of Category entity 
     * @param Category categoryPositive
     * @param Category categoryNegative
     * @return List
     */
    public List<Category> searchPrototypeCategory(Category categoryPositive, Category categoryNegative) {
	    return getResultList(getCategorySearchEqualQuery (categoryPositive, categoryNegative));  
    }

    /**
    * return a string query search on a Category prototype
    */
    protected String getCategorySelectQuery (String where, Category orderMask, QuerySortOrder sortOrder) {
       return getCategorySelectQuery (where, findOrder (orderMask, sortOrder));
    }
    protected String getCategorySelectQuery (String where, String order) {
       StringBuffer query = new StringBuffer();
       StringBuffer queryWhere = new StringBuffer();
       query.append ("SELECT category FROM Category category ");
       return getHQuery(query.toString(), where, order);
    }
    /**
    * return a jql query search on a Category prototype
    */
    protected String getSearchEqualQuery (Category category) {
       return getCategorySelectQuery (getWhereEqualWhereQueryChunk(category),null);
    }
    protected String getWhereEqualWhereQueryChunk (Category category) {
       return getWhereEqualWhereQueryChunk(category, false);
    }
    /**
    * return a jql query search on a Category with any prototype
    */
    protected String getSearchEqualAnyQuery (Category category) {
       return getCategorySelectQuery (getWhereEqualAnyWhereQueryChunk(category), null);   
    }
    protected String getWhereEqualAnyWhereQueryChunk (Category category) {
       return getWhereEqualAnyWhereQueryChunk(category, false);   
    }

    /**
    * return a jql search for a list of Category prototype
    */
    protected String getCategorySearchEqualQuery (Category categoryMask, List<Category> categorys) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (categoryMask !=null)
           query.append (getCategoryMaskWhat (categoryMask));
        query.append (" FROM Category category ");
        StringBuffer queryWhere = new StringBuffer();
        for (Category category : categorys) {
           if (!isAllNull(category)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;
	           queryWhere.append (" ("+getWhereEqualWhereQueryChunk(category, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    /**
    * return a jql search for a list of Category prototype
    */
    protected String getSearchEqualAnyQuery (Category categoryMask, List<Category> categorys) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (categoryMask !=null)
           query.append (getCategoryMaskWhat (categoryMask));
        query.append (" FROM Category category ");
        StringBuffer queryWhere = new StringBuffer();
        for (Category category : categorys) {
           if (!isAllNull(category)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getWhereEqualAnyWhereQueryChunk(category, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getCategoryMaskWhat (Category categoryMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (categoryMask.getCategoryid() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" categoryid ");
        }
        if (categoryMask.getName() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" name ");
        }
        if (categoryMask.getDescription() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" description ");
        }
        if (categoryMask.getImageurl() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" imageurl ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();
    }
    
    protected String getWhereEqualAnyWhereQueryChunk (Category category, boolean isAndSet) {
		return getSearchEqualWhereQueryChunk (category, isAndSet, false);	
	}
	
    protected String getWhereEqualWhereQueryChunk (Category category, boolean isAndSet) {
		return getSearchEqualWhereQueryChunk (category, isAndSet, true);
	}
	
    protected String getSearchEqualWhereQueryChunk (Category category, boolean isAndSet, boolean isAll) {
        StringBuffer query = new StringBuffer();
        if (category.getCategoryid() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" category.categoryid = '"+ category.getCategoryid()+"' ");
        }
        if (category.getName() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" category.name = '"+ category.getName()+"' ");
        }
        if (category.getDescription() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" category.description = '"+ category.getDescription()+"' ");
        }
        if (category.getImageurl() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" category.imageurl = '"+ category.getImageurl()+"' ");
        }
	    return query.toString();
    }

    protected String findOrder (Category orderMask, QuerySortOrder sortOrder) {
        if (orderMask!=null) {
            String orderColumn = getFirstNotNullColumnOtherWiseNull(orderMask);
            if (orderColumn!=null)
               return orderColumn + " " + sortOrder;
        }
        return "";
    }

	// indirection
    protected String findWhere (Category category, boolean isAndSet, boolean isAll, OperandType operandType, boolean caseSensitive) {
		return findWhere (null, category, isAndSet, isAll, operandType, caseSensitive);
	}
	
	protected static String findWhere (String alias, Category category, boolean isAndSet, boolean isAll, OperandType operandType, boolean caseSensitive) {
        if (alias==null)
			alias = "category";
		StringBuffer query = new StringBuffer();
		String operand = getOperand (operandType);
		String evaluatorPrefix = getEvaluatorPrefix (operandType);		
		String evaluatorSuffix = getEvaluatorSuffix (operandType);		
        if (category.getCategoryid() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = category.getCategoryid();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".categoryid) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".categoryid "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (category.getName() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = category.getName();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".name) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".name "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (category.getDescription() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = category.getDescription();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".description) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".description "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (category.getImageurl() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = category.getImageurl();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".imageurl) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".imageurl "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        return query.toString();
    }
	
	protected String getFirstNotNullColumnOtherWiseNull (Category mask) {
        if (mask == null) return null;
        if (mask.getCategoryid() != null) return "categoryid";
        if (mask.getName() != null) return "name";
        if (mask.getDescription() != null) return "description";
        if (mask.getImageurl() != null) return "imageurl";
        return null;	
	}
    
    /**
    * return a jql search on a Category prototype with positive and negative beans
    */
    protected String getCategorySearchEqualQuery (Category categoryPositive, Category categoryNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT category FROM Category category ");
        if (categoryPositive!=null && categoryPositive.getCategoryid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" category.categoryid = '"+ categoryPositive.getCategoryid()+"' ");
        } else if (categoryNegative.getCategoryid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" category.categoryid is null ");
        }
        if (categoryPositive!=null && categoryPositive.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" category.name = '"+ categoryPositive.getName()+"' ");
        } else if (categoryNegative.getName() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" category.name is null ");
        }
        if (categoryPositive!=null && categoryPositive.getDescription() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" category.description = '"+ categoryPositive.getDescription()+"' ");
        } else if (categoryNegative.getDescription() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" category.description is null ");
        }
        if (categoryPositive!=null && categoryPositive.getImageurl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" category.imageurl = '"+ categoryPositive.getImageurl()+"' ");
        } else if (categoryNegative.getImageurl() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" category.imageurl is null ");
        }
	    return query.toString();
    }
 
   
    protected EntityManager getEntityManager () {
        return entityManager;    
    }


}
