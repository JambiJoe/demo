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
import net.sf.mp.demo.petshop.dao.face.pet.TagDao;
import net.sf.mp.demo.petshop.domain.pet.Tag;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * <p>Title: TagJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with TagJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching TagJPAImpl objects</p>
 *
 */


@org.springframework.stereotype.Repository(value="tagDao")
public class TagJPAImpl implements TagDao {
	public TagJPAImpl () {}
	
    @PersistenceContext
    EntityManager entityManager;
	
    /**
     * Inserts a Tag entity 
     * @param Tag tag
     */
    public void insertTag(Tag tag) {
       entityManager.persist(tag);
    }

    protected void insertTag(EntityManager emForRecursiveDao, Tag tag) {
       emForRecursiveDao.persist(tag);
    } 
    /**
     * Inserts a list of Tag entity 
     * @param List<Tag> tags
     */
    public void insertTags(List<Tag> tags) {
    	//TODO
    }
    /**
     * Updates a Tag entity 
     * @param Tag tag
     */
    public Tag updateTag(Tag tag) {
       return entityManager.merge(tag);
    }

	/**
     * Updates a Tag entity with only the attributes set into Tag.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param Tag tag
    */ 
    @Transactional
    public int updateNotNullOnlyTag(Tag tag) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyTagQueryChunk(tag));
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

    protected String getUpdateNotNullOnlyTagQueryChunkPrototype (Tag tag) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Tag tag ");
        if (tag.getTag() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" tag.tag = :tag");
        }
        if (tag.getRefcount() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" tag.refcount = :refcount");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyTagQueryChunk (Tag tag) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyTagQueryChunkPrototype(tag));
        if (tag.getTagid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" tag.tagid = :tagid");
        }
        return query.toString();
    }
    
                
	protected Tag assignBlankToNull (Tag tag) {
        if (tag==null)
			return null;
        if (tag.getTag()!=null && tag.getTag().equals(""))
           tag.setTag((String)null);
		return tag;
	}
	
	protected boolean isAllNull (Tag tag) {
	    if (tag==null)
			return true;
        if (tag.getTagid()!=null) 
            return false;
        if (tag.getTag()!=null) 
            return false;
        if (tag.getRefcount()!=null) 
            return false;
		return true;
	}
		
    @Transactional
    public int updateNotNullOnlyPrototypeTag(Tag tag, Tag prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Tag tag ");
        if (tag.getTagid() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" tag.tagid = "+ tag.getTagid() + " ");
        }
        if (tag.getTag() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" tag.tag = '"+ tag.getTag()+"' ");
        }
        if (tag.getRefcount() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" tag.refcount = "+ tag.getRefcount() + " ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getTagid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" tag.tagid = "+ prototypeCriteria.getTagid() + " ");
        }
        if (prototypeCriteria.getTag() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" tag.tag = '"+ prototypeCriteria.getTag()+"' ");
        }
        if (prototypeCriteria.getRefcount() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" tag.refcount = "+ prototypeCriteria.getRefcount() + " ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a Tag entity 
     * @param Tag tag
     */
    public void saveTag(Tag tag) {
       //entityManager.persist(tag);
       if (entityManager.contains(tag)) {
          entityManager.merge(tag);
       } else {
          entityManager.persist(tag);
       }
       entityManager.flush(); 
    }
       
    /**
     * Deletes a Tag entity 
     * @param Tag tag
     */
    public void deleteTag(Tag tag) {
      entityManager.remove(tag);
    }
    
    /**
     * Loads the Tag entity which is related to an instance of
     * Tag
     * @param Long id
     * @return Tag The Tag entity
     
    public Tag loadTag(Long id) {
    	return (Tag)entityManager.get(Tag.class, id);
    }
*/
  
    /**
     * Loads the Tag entity which is related to an instance of
     * Tag
     * @param java.lang.Integer Tagid
     * @return Tag The Tag entity
     */
    public Tag loadTag(java.lang.Integer tagid) {
    	return (Tag)entityManager.find(Tag.class, tagid);
    }
    
    /**
     * Loads a list of Tag entity 
     * @param List<java.lang.Integer> tagids
     * @return List<Tag> The Tag entity
     */
    public List<Tag> loadTagListByTag (List<Tag> tags) {
       return null;
    }
    
    /**
     * Loads a list of Tag entity 
     * @param List<java.lang.Integer> tagids
     * @return List<Tag> The Tag entity
     */
    public List<Tag> loadTagListByTagid(List<java.lang.Integer> tagids){
       return null;
    }
    
    /**
     * Loads the Tag entity which is related to an instance of
     * Tag and its dependent one to many objects
     * @param Long id
     * @return Tag The Tag entity
     */
    public Tag loadFullFirstLevelTag(java.lang.Integer tagid) {
        List list = getResultList(
                     "SELECT tag FROM Tag tag "
                     + " LEFT JOIN tag.tagItemTagids "   
                     + " WHERE tag.tagid = "+tagid
               );
         if (list!=null && !list.isEmpty())
            return (Tag)list.get(0);
         return null;
    	//return null;//(Tag) entityManager.queryForObject("loadFullFirstLevelTag", id);
    }

    /**
     * Loads the Tag entity which is related to an instance of
     * Tag
     * @param Tag tag
     * @return Tag The Tag entity
     */
    public Tag loadFullFirstLevelTag(Tag tag) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT tag FROM Tag tag ");
        if (tag.getTagid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" tag.tagid = "+ tag.getTagid() + " ");
         }
        List list = getResultList(query.toString());
        if (list!=null && !list.isEmpty())
           return (Tag)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the Tag entity which is related to an instance of
     * Tag and its dependent objects one to many
     * @param Long id
     * @return Tag The Tag entity
     */
    public Tag loadFullTag(Long id) {
    	return null;//(Tag)entityManager.queryForObject("loadFullTag", id);
    }

    /**
     * Searches a list of Tag entity 
     * @param Tag tag
     * @return List
     */  
    public List<Tag> searchPrototypeTag(Tag tag) {
       return searchPrototype (tag, null);
    }  
	
    public List<Tag> searchPrototypeAnyTag(Tag tag) {
       return searchPrototypeAny (tag, null);
    }  

	// indirection
    public List<Tag> find (Tag criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
       return find (criteriaMask, matchType, operandType, caseSensitivenessType, null, null); 
	}
	
	// indirection
	protected List<Tag> find (Tag criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, Integer startPosition, Integer maxResults) {
       return find (criteriaMask, null, matchType, operandType, caseSensitivenessType, null, startPosition, maxResults); 
    }
	
	// indirection
	protected List<Tag> find (Tag criteriaMask, Tag orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Integer startPosition, Integer maxResults) {
       return find (null, criteriaMask, orderMask, matchType, operandType, caseSensitivenessType, sortOrder, startPosition, maxResults);
    }
	
	// main find implementation
	protected List<Tag> find (Tag whatMask, Tag criteriaMask, Tag orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Integer startPosition, Integer maxResults) {
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
    public String findQuery (Tag criteriaMask, Tag orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
        String what = "SELECT tag FROM Tag tag ";
		return findQuery (criteriaMask, orderMask, what, matchType, operandType, caseSensitivenessType, sortOrder);
    }

	/**
	 *   find partial on entity
	 *
	 */
    public String findPartialQuery (Tag whatMask, Tag criteriaMask, Tag orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Map beanPath) {
        return "to override ";
    }
	
	public List<Tag> handlePartialLoadWithParentResult(Tag what, List list, Map beanPath) {
		return null; //TO Override
	}
	
    protected String findQuery (Tag criteriaMask, Tag orderMask, String what, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
        String queryWhere = findWhere (criteriaMask, false, isAll(matchType), operandType, caseSensitivenessType);
		String queryOrder = findOrder (orderMask, sortOrder);
	    return getHQuery(what, queryWhere, queryOrder);
    }
	
    protected List<Tag> searchPrototype (Tag tag, Tag orderMask, QuerySortOrder sortOrder, Integer maxResults) {
       return searchPrototype(getTagSelectQuery (getWhereEqualWhereQueryChunk(tag), orderMask, sortOrder), maxResults);
    }

    protected List<Tag> searchPrototype (Tag tag, Integer maxResults) {
       return searchPrototype(tag, null, null, maxResults);
    }
    
    protected List<Tag> searchPrototypeAny (Tag tag, Integer maxResults) { 
       return searchPrototype(getSearchEqualAnyQuery (tag), maxResults);
    }
    
    protected List<Tag> searchPrototype (String query, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(query);
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();
    }

    public List<Tag> searchPrototypeTag (List<Tag> tags) {
       return searchPrototype (tags, null);
    }

    protected List<Tag> searchPrototype (List<Tag> tags, Integer maxResults) {    
	   return getResultList(getTagSearchEqualQuery (null, tags));
	}    

    protected List<Tag> getResultList (String query) {    
	   Query hquery = entityManager.createQuery(query);            
	   return hquery.getResultList();
	}    

    public List<Tag> searchDistinctPrototypeTag (Tag tagMask, List<Tag> tags) {
        return getResultList(getTagSearchEqualQuery (tagMask, tags));    
    }
         
	/**
     * Searches a list of Tag entity 
     * @param Tag tagPositive
     * @param Tag tagNegative
     * @return List
     */
    public List<Tag> searchPrototypeTag(Tag tagPositive, Tag tagNegative) {
	    return getResultList(getTagSearchEqualQuery (tagPositive, tagNegative));  
    }

    /**
    * return a string query search on a Tag prototype
    */
    protected String getTagSelectQuery (String where, Tag orderMask, QuerySortOrder sortOrder) {
       return getTagSelectQuery (where, findOrder (orderMask, sortOrder));
    }
    protected String getTagSelectQuery (String where, String order) {
       StringBuffer query = new StringBuffer();
       StringBuffer queryWhere = new StringBuffer();
       query.append ("SELECT tag FROM Tag tag ");
       return getHQuery(query.toString(), where, order);
    }
    /**
    * return a jql query search on a Tag prototype
    */
    protected String getSearchEqualQuery (Tag tag) {
       return getTagSelectQuery (getWhereEqualWhereQueryChunk(tag),null);
    }
    protected String getWhereEqualWhereQueryChunk (Tag tag) {
       return getWhereEqualWhereQueryChunk(tag, false);
    }
    /**
    * return a jql query search on a Tag with any prototype
    */
    protected String getSearchEqualAnyQuery (Tag tag) {
       return getTagSelectQuery (getWhereEqualAnyWhereQueryChunk(tag), null);   
    }
    protected String getWhereEqualAnyWhereQueryChunk (Tag tag) {
       return getWhereEqualAnyWhereQueryChunk(tag, false);   
    }

    /**
    * return a jql search for a list of Tag prototype
    */
    protected String getTagSearchEqualQuery (Tag tagMask, List<Tag> tags) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (tagMask !=null)
           query.append (getTagMaskWhat (tagMask));
        query.append (" FROM Tag tag ");
        StringBuffer queryWhere = new StringBuffer();
        for (Tag tag : tags) {
           if (!isAllNull(tag)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;
	           queryWhere.append (" ("+getWhereEqualWhereQueryChunk(tag, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    /**
    * return a jql search for a list of Tag prototype
    */
    protected String getSearchEqualAnyQuery (Tag tagMask, List<Tag> tags) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (tagMask !=null)
           query.append (getTagMaskWhat (tagMask));
        query.append (" FROM Tag tag ");
        StringBuffer queryWhere = new StringBuffer();
        for (Tag tag : tags) {
           if (!isAllNull(tag)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getWhereEqualAnyWhereQueryChunk(tag, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getTagMaskWhat (Tag tagMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (tagMask.getTagid() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" tagid ");
        }
        if (tagMask.getTag() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" tag ");
        }
        if (tagMask.getRefcount() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" refcount ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();
    }
    
    protected String getWhereEqualAnyWhereQueryChunk (Tag tag, boolean isAndSet) {
		return getSearchEqualWhereQueryChunk (tag, isAndSet, false);	
	}
	
    protected String getWhereEqualWhereQueryChunk (Tag tag, boolean isAndSet) {
		return getSearchEqualWhereQueryChunk (tag, isAndSet, true);
	}
	
    protected String getSearchEqualWhereQueryChunk (Tag tag, boolean isAndSet, boolean isAll) {
        StringBuffer query = new StringBuffer();
        if (tag.getTagid() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" tag.tagid = "+ tag.getTagid() + " ");
        }
        if (tag.getTag() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" tag.tag = '"+ tag.getTag()+"' ");
        }
        if (tag.getRefcount() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" tag.refcount = "+ tag.getRefcount() + " ");
        }
	    return query.toString();
    }

    protected String findOrder (Tag orderMask, QuerySortOrder sortOrder) {
        if (orderMask!=null) {
            String orderColumn = getFirstNotNullColumnOtherWiseNull(orderMask);
            if (orderColumn!=null)
               return orderColumn + " " + sortOrder;
        }
        return "";
    }

	// indirection
    protected String findWhere (Tag tag, boolean isAndSet, boolean isAll, OperandType operandType, boolean caseSensitive) {
		return findWhere (null, tag, isAndSet, isAll, operandType, caseSensitive);
	}
	
	protected static String findWhere (String alias, Tag tag, boolean isAndSet, boolean isAll, OperandType operandType, boolean caseSensitive) {
        if (alias==null)
			alias = "tag";
		StringBuffer query = new StringBuffer();
		String operand = getOperand (operandType);
		String evaluatorPrefix = getEvaluatorPrefix (operandType);		
		String evaluatorSuffix = getEvaluatorSuffix (operandType);		
        if (tag.getTagid() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".tagid = "+ tag.getTagid() + " ");
        }
        if (tag.getTag() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = tag.getTag();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".tag) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".tag "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (tag.getRefcount() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".refcount = "+ tag.getRefcount() + " ");
        }
        return query.toString();
    }
	
	protected String getFirstNotNullColumnOtherWiseNull (Tag mask) {
        if (mask == null) return null;
        if (mask.getTagid() != null) return "tagid";
        if (mask.getTag() != null) return "tag";
        if (mask.getRefcount() != null) return "refcount";
        return null;	
	}
    
    /**
    * return a jql search on a Tag prototype with positive and negative beans
    */
    protected String getTagSearchEqualQuery (Tag tagPositive, Tag tagNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT tag FROM Tag tag ");
        if (tagPositive!=null && tagPositive.getTagid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" tag.tagid = "+ tagPositive.getTagid() + " ");
        } else if (tagNegative.getTagid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" tag.tagid is null ");
        }
        if (tagPositive!=null && tagPositive.getTag() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" tag.tag = '"+ tagPositive.getTag()+"' ");
        } else if (tagNegative.getTag() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" tag.tag is null ");
        }
        if (tagPositive!=null && tagPositive.getRefcount() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" tag.refcount = "+ tagPositive.getRefcount() + " ");
        } else if (tagNegative.getRefcount() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" tag.refcount is null ");
        }
	    return query.toString();
    }
 
   
    protected EntityManager getEntityManager () {
        return entityManager;    
    }


}
