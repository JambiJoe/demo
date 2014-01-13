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
import net.sf.mp.demo.petshop.dao.face.pet.AddressDao;
import net.sf.mp.demo.petshop.domain.pet.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * <p>Title: AddressJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with AddressJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching AddressJPAImpl objects</p>
 *
 */


@org.springframework.stereotype.Repository(value="addressDao")
public class AddressJPAImpl implements AddressDao {
	public AddressJPAImpl () {}
	
    @PersistenceContext
    EntityManager entityManager;
	
    /**
     * Inserts a Address entity 
     * @param Address address
     */
    public void insertAddress(Address address) {
       entityManager.persist(address);
    }

    protected void insertAddress(EntityManager emForRecursiveDao, Address address) {
       emForRecursiveDao.persist(address);
    } 
    /**
     * Inserts a list of Address entity 
     * @param List<Address> addresss
     */
    public void insertAddresss(List<Address> addresss) {
    	//TODO
    }
    /**
     * Updates a Address entity 
     * @param Address address
     */
    public Address updateAddress(Address address) {
       return entityManager.merge(address);
    }

	/**
     * Updates a Address entity with only the attributes set into Address.
	 * The primary keys are to be set for this method to operate.
	 * This is a performance friendly feature, which remove the udibiquous full load and full update when an
	 * update is issued
     * Remark: The primary keys cannot be update by this methods, nor are the attributes that must be set to null.
     * @param Address address
    */ 
    @Transactional
    public int updateNotNullOnlyAddress(Address address) {
        Query jpaQuery = getEntityManager().createQuery(getUpdateNotNullOnlyAddressQueryChunk(address));
        if (address.getAddressid() != null) {
           jpaQuery.setParameter ("addressid", address.getAddressid());
        }   
        if (address.getStreet1() != null) {
           jpaQuery.setParameter ("street1", address.getStreet1());
        }   
        if (address.getStreet2() != null) {
           jpaQuery.setParameter ("street2", address.getStreet2());
        }   
        if (address.getCity() != null) {
           jpaQuery.setParameter ("city", address.getCity());
        }   
        if (address.getState() != null) {
           jpaQuery.setParameter ("state", address.getState());
        }   
        if (address.getZip() != null) {
           jpaQuery.setParameter ("zip", address.getZip());
        }   
        if (address.getLatitude() != null) {
           jpaQuery.setParameter ("latitude", address.getLatitude());
        }   
        if (address.getLongitude() != null) {
           jpaQuery.setParameter ("longitude", address.getLongitude());
        }   
		return jpaQuery.executeUpdate();
    }

    protected String getUpdateNotNullOnlyAddressQueryChunkPrototype (Address address) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Address address ");
        if (address.getStreet1() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.street1 = :street1");
        }
        if (address.getStreet2() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.street2 = :street2");
        }
        if (address.getCity() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.city = :city");
        }
        if (address.getState() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.state = :state");
        }
        if (address.getZip() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.zip = :zip");
        }
        if (address.getLatitude() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.latitude = :latitude");
        }
        if (address.getLongitude() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.longitude = :longitude");
        }
        return query.toString();
    }
    
    protected String getUpdateNotNullOnlyAddressQueryChunk (Address address) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer(getUpdateNotNullOnlyAddressQueryChunkPrototype(address));
        if (address.getAddressid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
			     query.append(" address.addressid = :addressid");
        }
        return query.toString();
    }
    
                
	protected Address assignBlankToNull (Address address) {
        if (address==null)
			return null;
        if (address.getStreet1()!=null && address.getStreet1().equals(""))
           address.setStreet1((String)null);
        if (address.getStreet2()!=null && address.getStreet2().equals(""))
           address.setStreet2((String)null);
        if (address.getCity()!=null && address.getCity().equals(""))
           address.setCity((String)null);
        if (address.getState()!=null && address.getState().equals(""))
           address.setState((String)null);
        if (address.getZip()!=null && address.getZip().equals(""))
           address.setZip((String)null);
		return address;
	}
	
	protected boolean isAllNull (Address address) {
	    if (address==null)
			return true;
        if (address.getAddressid()!=null) 
            return false;
        if (address.getStreet1()!=null) 
            return false;
        if (address.getStreet2()!=null) 
            return false;
        if (address.getCity()!=null) 
            return false;
        if (address.getState()!=null) 
            return false;
        if (address.getZip()!=null) 
            return false;
        if (address.getLatitude()!=null) 
            return false;
        if (address.getLongitude()!=null) 
            return false;
		return true;
	}
		
    @Transactional
    public int updateNotNullOnlyPrototypeAddress(Address address, Address prototypeCriteria) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" update Address address ");
        if (address.getAddressid() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.addressid = "+ address.getAddressid() + " ");
        }
        if (address.getStreet1() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.street1 = '"+ address.getStreet1()+"' ");
        }
        if (address.getStreet2() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.street2 = '"+ address.getStreet2()+"' ");
        }
        if (address.getCity() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.city = '"+ address.getCity()+"' ");
        }
        if (address.getState() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.state = '"+ address.getState()+"' ");
        }
        if (address.getZip() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.zip = '"+ address.getZip()+"' ");
        }
        if (address.getLatitude() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.latitude = "+ address.getLatitude() + " ");
        }
        if (address.getLongitude() != null) {
           query.append (getQueryCommaSet (isWhereSet));
           isWhereSet = true; 
           query.append(" address.longitude = "+ address.getLongitude() + " ");
        }
		isWhereSet = false; 
        if (prototypeCriteria.getAddressid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.addressid = "+ prototypeCriteria.getAddressid() + " ");
        }
        if (prototypeCriteria.getStreet1() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.street1 = '"+ prototypeCriteria.getStreet1()+"' ");
        }
        if (prototypeCriteria.getStreet2() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.street2 = '"+ prototypeCriteria.getStreet2()+"' ");
        }
        if (prototypeCriteria.getCity() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.city = '"+ prototypeCriteria.getCity()+"' ");
        }
        if (prototypeCriteria.getState() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.state = '"+ prototypeCriteria.getState()+"' ");
        }
        if (prototypeCriteria.getZip() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.zip = '"+ prototypeCriteria.getZip()+"' ");
        }
        if (prototypeCriteria.getLatitude() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.latitude = "+ prototypeCriteria.getLatitude() + " ");
        }
        if (prototypeCriteria.getLongitude() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.longitude = "+ prototypeCriteria.getLongitude() + " ");
        }
        Query jpaQuery = getEntityManager().createQuery(query.toString());
		return jpaQuery.executeUpdate();
    }
     
     /**
     * Saves a Address entity 
     * @param Address address
     */
    public void saveAddress(Address address) {
       //entityManager.persist(address);
       if (entityManager.contains(address)) {
          entityManager.merge(address);
       } else {
          entityManager.persist(address);
       }
       entityManager.flush(); 
    }
       
    /**
     * Deletes a Address entity 
     * @param Address address
     */
    public void deleteAddress(Address address) {
      entityManager.remove(address);
    }
    
    /**
     * Loads the Address entity which is related to an instance of
     * Address
     * @param Long id
     * @return Address The Address entity
     
    public Address loadAddress(Long id) {
    	return (Address)entityManager.get(Address.class, id);
    }
*/
  
    /**
     * Loads the Address entity which is related to an instance of
     * Address
     * @param java.lang.Integer Addressid
     * @return Address The Address entity
     */
    public Address loadAddress(java.lang.Integer addressid) {
    	return (Address)entityManager.find(Address.class, addressid);
    }
    
    /**
     * Loads a list of Address entity 
     * @param List<java.lang.Integer> addressids
     * @return List<Address> The Address entity
     */
    public List<Address> loadAddressListByAddress (List<Address> addresss) {
       return null;
    }
    
    /**
     * Loads a list of Address entity 
     * @param List<java.lang.Integer> addressids
     * @return List<Address> The Address entity
     */
    public List<Address> loadAddressListByAddressid(List<java.lang.Integer> addressids){
       return null;
    }
    
    /**
     * Loads the Address entity which is related to an instance of
     * Address and its dependent one to many objects
     * @param Long id
     * @return Address The Address entity
     */
    public Address loadFullFirstLevelAddress(java.lang.Integer addressid) {
        List list = getResultList(
                     "SELECT address FROM Address address "
                     + " LEFT JOIN address.myGoodItemAddressAddressids "   
                     + " WHERE address.addressid = "+addressid
               );
         if (list!=null && !list.isEmpty())
            return (Address)list.get(0);
         return null;
    	//return null;//(Address) entityManager.queryForObject("loadFullFirstLevelAddress", id);
    }

    /**
     * Loads the Address entity which is related to an instance of
     * Address
     * @param Address address
     * @return Address The Address entity
     */
    public Address loadFullFirstLevelAddress(Address address) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT address FROM Address address ");
        query.append (" LEFT JOIN address.myGoodItemAddressAddressids ");
        if (address.getAddressid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.addressid = "+ address.getAddressid() + " ");
         }
        List list = getResultList(query.toString());
        if (list!=null && !list.isEmpty())
           return (Address)list.get(0);    
        return null;
    }  
     
    /**
     * Loads the Address entity which is related to an instance of
     * Address and its dependent objects one to many
     * @param Long id
     * @return Address The Address entity
     */
    public Address loadFullAddress(Long id) {
    	return null;//(Address)entityManager.queryForObject("loadFullAddress", id);
    }

    /**
     * Searches a list of Address entity 
     * @param Address address
     * @return List
     */  
    public List<Address> searchPrototypeAddress(Address address) {
       return searchPrototype (address, null);
    }  
	
    public List<Address> searchPrototypeAnyAddress(Address address) {
       return searchPrototypeAny (address, null);
    }  

	// indirection
    public List<Address> find (Address criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
       return find (criteriaMask, matchType, operandType, caseSensitivenessType, null, null); 
	}
	
	// indirection
	protected List<Address> find (Address criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, Integer startPosition, Integer maxResults) {
       return find (criteriaMask, null, matchType, operandType, caseSensitivenessType, null, startPosition, maxResults); 
    }
	
	// indirection
	protected List<Address> find (Address criteriaMask, Address orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Integer startPosition, Integer maxResults) {
       return find (null, criteriaMask, orderMask, matchType, operandType, caseSensitivenessType, sortOrder, startPosition, maxResults);
    }
	
	// main find implementation
	protected List<Address> find (Address whatMask, Address criteriaMask, Address orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Integer startPosition, Integer maxResults) {
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
    public String findQuery (Address criteriaMask, Address orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
        String what = "SELECT address FROM Address address ";
		return findQuery (criteriaMask, orderMask, what, matchType, operandType, caseSensitivenessType, sortOrder);
    }

	/**
	 *   find partial on entity
	 *
	 */
    public String findPartialQuery (Address whatMask, Address criteriaMask, Address orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Map beanPath) {
        return "to override ";
    }
	
	public List<Address> handlePartialLoadWithParentResult(Address what, List list, Map beanPath) {
		return null; //TO Override
	}
	
    protected String findQuery (Address criteriaMask, Address orderMask, String what, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
        String queryWhere = findWhere (criteriaMask, false, isAll(matchType), operandType, caseSensitivenessType);
		String queryOrder = findOrder (orderMask, sortOrder);
	    return getHQuery(what, queryWhere, queryOrder);
    }
	
    protected List<Address> searchPrototype (Address address, Address orderMask, QuerySortOrder sortOrder, Integer maxResults) {
       return searchPrototype(getAddressSelectQuery (getWhereEqualWhereQueryChunk(address), orderMask, sortOrder), maxResults);
    }

    protected List<Address> searchPrototype (Address address, Integer maxResults) {
       return searchPrototype(address, null, null, maxResults);
    }
    
    protected List<Address> searchPrototypeAny (Address address, Integer maxResults) { 
       return searchPrototype(getSearchEqualAnyQuery (address), maxResults);
    }
    
    protected List<Address> searchPrototype (String query, Integer maxResults) { 
       Query hquery = getEntityManager().createQuery(query);
       if (maxResults!=null)
          hquery.setMaxResults(maxResults);
       return hquery.getResultList();
    }

    public List<Address> searchPrototypeAddress (List<Address> addresss) {
       return searchPrototype (addresss, null);
    }

    protected List<Address> searchPrototype (List<Address> addresss, Integer maxResults) {    
	   return getResultList(getAddressSearchEqualQuery (null, addresss));
	}    

    protected List<Address> getResultList (String query) {    
	   Query hquery = entityManager.createQuery(query);            
	   return hquery.getResultList();
	}    

    public List<Address> searchDistinctPrototypeAddress (Address addressMask, List<Address> addresss) {
        return getResultList(getAddressSearchEqualQuery (addressMask, addresss));    
    }
         
	/**
     * Searches a list of Address entity 
     * @param Address addressPositive
     * @param Address addressNegative
     * @return List
     */
    public List<Address> searchPrototypeAddress(Address addressPositive, Address addressNegative) {
	    return getResultList(getAddressSearchEqualQuery (addressPositive, addressNegative));  
    }

    /**
    * return a string query search on a Address prototype
    */
    protected String getAddressSelectQuery (String where, Address orderMask, QuerySortOrder sortOrder) {
       return getAddressSelectQuery (where, findOrder (orderMask, sortOrder));
    }
    protected String getAddressSelectQuery (String where, String order) {
       StringBuffer query = new StringBuffer();
       StringBuffer queryWhere = new StringBuffer();
       query.append ("SELECT address FROM Address address ");
       return getHQuery(query.toString(), where, order);
    }
    /**
    * return a jql query search on a Address prototype
    */
    protected String getSearchEqualQuery (Address address) {
       return getAddressSelectQuery (getWhereEqualWhereQueryChunk(address),null);
    }
    protected String getWhereEqualWhereQueryChunk (Address address) {
       return getWhereEqualWhereQueryChunk(address, false);
    }
    /**
    * return a jql query search on a Address with any prototype
    */
    protected String getSearchEqualAnyQuery (Address address) {
       return getAddressSelectQuery (getWhereEqualAnyWhereQueryChunk(address), null);   
    }
    protected String getWhereEqualAnyWhereQueryChunk (Address address) {
       return getWhereEqualAnyWhereQueryChunk(address, false);   
    }

    /**
    * return a jql search for a list of Address prototype
    */
    protected String getAddressSearchEqualQuery (Address addressMask, List<Address> addresss) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (addressMask !=null)
           query.append (getAddressMaskWhat (addressMask));
        query.append (" FROM Address address ");
        StringBuffer queryWhere = new StringBuffer();
        for (Address address : addresss) {
           if (!isAllNull(address)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;
	           queryWhere.append (" ("+getWhereEqualWhereQueryChunk(address, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    /**
    * return a jql search for a list of Address prototype
    */
    protected String getSearchEqualAnyQuery (Address addressMask, List<Address> addresss) {
        boolean isOrSet = false;
        StringBuffer query = new StringBuffer();
        if (addressMask !=null)
           query.append (getAddressMaskWhat (addressMask));
        query.append (" FROM Address address ");
        StringBuffer queryWhere = new StringBuffer();
        for (Address address : addresss) {
           if (!isAllNull(address)) {        
	           queryWhere.append (getQueryOR (isOrSet));
	           isOrSet = true;        
	           queryWhere.append (" ("+getWhereEqualAnyWhereQueryChunk(address, false)+") ");
           }
        }
	    return getHQuery(query.toString(), queryWhere.toString());
    }	
    
    protected String getAddressMaskWhat (Address addressMask) {
        boolean isCommaSet = false;
        StringBuffer query = new StringBuffer("SELECT DISTINCT ");
        if (addressMask.getAddressid() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" addressid ");
        }
        if (addressMask.getStreet1() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" street1 ");
        }
        if (addressMask.getStreet2() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" street2 ");
        }
        if (addressMask.getCity() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" city ");
        }
        if (addressMask.getState() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" state ");
        }
        if (addressMask.getZip() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" zip ");
        }
        if (addressMask.getLatitude() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" latitude ");
        }
        if (addressMask.getLongitude() != null) {
           query.append (getQueryComma (isCommaSet));
           isCommaSet = true;
           query.append(" longitude ");
        }
        if (!isCommaSet)
           return "";
	    return query.toString();
    }
    
    protected String getWhereEqualAnyWhereQueryChunk (Address address, boolean isAndSet) {
		return getSearchEqualWhereQueryChunk (address, isAndSet, false);	
	}
	
    protected String getWhereEqualWhereQueryChunk (Address address, boolean isAndSet) {
		return getSearchEqualWhereQueryChunk (address, isAndSet, true);
	}
	
    protected String getSearchEqualWhereQueryChunk (Address address, boolean isAndSet, boolean isAll) {
        StringBuffer query = new StringBuffer();
        if (address.getAddressid() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" address.addressid = "+ address.getAddressid() + " ");
        }
        if (address.getStreet1() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" address.street1 = '"+ address.getStreet1()+"' ");
        }
        if (address.getStreet2() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" address.street2 = '"+ address.getStreet2()+"' ");
        }
        if (address.getCity() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" address.city = '"+ address.getCity()+"' ");
        }
        if (address.getState() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" address.state = '"+ address.getState()+"' ");
        }
        if (address.getZip() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" address.zip = '"+ address.getZip()+"' ");
        }
        if (address.getLatitude() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" address.latitude = "+ address.getLatitude() + " ");
        }
        if (address.getLongitude() != null) {
		   if (isAll)
			  query.append (getQueryAND (isAndSet));
		   else 
		      query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" address.longitude = "+ address.getLongitude() + " ");
        }
	    return query.toString();
    }

    protected String findOrder (Address orderMask, QuerySortOrder sortOrder) {
        if (orderMask!=null) {
            String orderColumn = getFirstNotNullColumnOtherWiseNull(orderMask);
            if (orderColumn!=null)
               return orderColumn + " " + sortOrder;
        }
        return "";
    }

	// indirection
    protected String findWhere (Address address, boolean isAndSet, boolean isAll, OperandType operandType, boolean caseSensitive) {
		return findWhere (null, address, isAndSet, isAll, operandType, caseSensitive);
	}
	
	protected static String findWhere (String alias, Address address, boolean isAndSet, boolean isAll, OperandType operandType, boolean caseSensitive) {
        if (alias==null)
			alias = "address";
		StringBuffer query = new StringBuffer();
		String operand = getOperand (operandType);
		String evaluatorPrefix = getEvaluatorPrefix (operandType);		
		String evaluatorSuffix = getEvaluatorSuffix (operandType);		
        if (address.getAddressid() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".addressid = "+ address.getAddressid() + " ");
        }
        if (address.getStreet1() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = address.getStreet1();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".street1) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".street1 "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (address.getStreet2() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = address.getStreet2();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".street2) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".street2 "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (address.getCity() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = address.getCity();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".city) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".city "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (address.getState() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = address.getState();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".state) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".state "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (address.getZip() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           String value = address.getZip();
           if (!caseSensitive) {
              value = value.toLowerCase();
              query.append(" lower("+alias+".zip) "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
           }
           else
              query.append(" "+alias+".zip "+operand+ "'"+evaluatorPrefix+value+evaluatorSuffix+"' ");
        }
        if (address.getLatitude() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".latitude = "+ address.getLatitude() + " ");
        }
        if (address.getLongitude() != null) {
           if (isAll)
              query.append (getQueryAND (isAndSet));
           else 
              query.append (getQueryOR (isAndSet));
           isAndSet = true;
           query.append(" "+alias+".longitude = "+ address.getLongitude() + " ");
        }
        return query.toString();
    }
	
	protected String getFirstNotNullColumnOtherWiseNull (Address mask) {
        if (mask == null) return null;
        if (mask.getAddressid() != null) return "addressid";
        if (mask.getStreet1() != null) return "street1";
        if (mask.getStreet2() != null) return "street2";
        if (mask.getCity() != null) return "city";
        if (mask.getState() != null) return "state";
        if (mask.getZip() != null) return "zip";
        if (mask.getLatitude() != null) return "latitude";
        if (mask.getLongitude() != null) return "longitude";
        return null;	
	}
    
    /**
    * return a jql search on a Address prototype with positive and negative beans
    */
    protected String getAddressSearchEqualQuery (Address addressPositive, Address addressNegative) {
        boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append (" SELECT address FROM Address address ");
        if (addressPositive!=null && addressPositive.getAddressid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.addressid = "+ addressPositive.getAddressid() + " ");
        } else if (addressNegative.getAddressid() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" address.addressid is null ");
        }
        if (addressPositive!=null && addressPositive.getStreet1() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.street1 = '"+ addressPositive.getStreet1()+"' ");
        } else if (addressNegative.getStreet1() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" address.street1 is null ");
        }
        if (addressPositive!=null && addressPositive.getStreet2() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.street2 = '"+ addressPositive.getStreet2()+"' ");
        } else if (addressNegative.getStreet2() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" address.street2 is null ");
        }
        if (addressPositive!=null && addressPositive.getCity() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.city = '"+ addressPositive.getCity()+"' ");
        } else if (addressNegative.getCity() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" address.city is null ");
        }
        if (addressPositive!=null && addressPositive.getState() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.state = '"+ addressPositive.getState()+"' ");
        } else if (addressNegative.getState() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" address.state is null ");
        }
        if (addressPositive!=null && addressPositive.getZip() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.zip = '"+ addressPositive.getZip()+"' ");
        } else if (addressNegative.getZip() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" address.zip is null ");
        }
        if (addressPositive!=null && addressPositive.getLatitude() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.latitude = "+ addressPositive.getLatitude() + " ");
        } else if (addressNegative.getLatitude() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" address.latitude is null ");
        }
        if (addressPositive!=null && addressPositive.getLongitude() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;
           query.append(" address.longitude = "+ addressPositive.getLongitude() + " ");
        } else if (addressNegative.getLongitude() != null) {
           query.append (getQueryWHERE_AND (isWhereSet));
           isWhereSet = true;   
           query.append(" address.longitude is null ");
        }
	    return query.toString();
    }
 
   
    protected EntityManager getEntityManager () {
        return entityManager;    
    }


}
