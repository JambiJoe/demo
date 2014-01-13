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
import net.sf.mp.demo.petshop.dao.face.pet.AddressExtDao;
import net.sf.mp.demo.petshop.domain.pet.Address;
import net.sf.mp.demo.petshop.dao.impl.jpa.pet.AddressJPAImpl;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@


import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.mp.demo.petshop.dao.impl.jpa.product.MyGoodItemExtendedJPAImpl;
/**
 *
 * <p>Title: AddressExtendedJPAImpl</p>
 *
 * <p>Description: Interface of a Data access object dealing with AddressExtendedJPAImpl
 * persistence. It offers a set of methods which allow for saving,
 * deleting and searching AddressExtendedJPAImpl objects</p>
 *
 */
@org.springframework.stereotype.Repository(value="addressExtDao")
public class AddressExtendedJPAImpl extends AddressJPAImpl implements AddressExtDao{
    private Logger log = Logger.getLogger(this.getClass());
    
    private SimpleCache simpleCache = new SimpleCache();
    private MyGoodItemExtendedJPAImpl mygooditemextendedjpaimpl;
    private EntityManager emForRecursiveDao; // dao that needs other dao in a recursive manner not support by spring configuration

    public AddressExtendedJPAImpl () {}

    /**
     * generic to move after in superclass
     */
    public AddressExtendedJPAImpl (EntityManager emForRecursiveDao) {
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
     * Inserts a Address entity with cascade of its children
     * @param Address address
     */
    public void insertAddressWithCascade(Address address) {
    	AddressExtendedJPAImpl addressextendedjpaimpl = new AddressExtendedJPAImpl(getEntityManager());
    	addressextendedjpaimpl.insertAddressWithCascade(addressextendedjpaimpl.getEntityManagerForRecursiveDao(), address);
    }
     
    public void insertAddressWithCascade(EntityManager emForRecursiveDao, Address address) {
       insertAddress(emForRecursiveDao, address);
       if (!address.getMyGoodItems().isEmpty()) {
          MyGoodItemExtendedJPAImpl mygooditemextendedjpaimpl = new MyGoodItemExtendedJPAImpl (emForRecursiveDao);
          for (MyGoodItem _myGoodItems : address.getMyGoodItems()) {
             mygooditemextendedjpaimpl.insertMyGoodItemWithCascade(emForRecursiveDao, _myGoodItems);
          }
       } 
    }
        
    /**
     * Inserts a list of Address entity with cascade of its children
     * @param List<Address> addresss
     */
    public void insertAddresssWithCascade(List<Address> addresss) {
       for (Address address : addresss) {
          insertAddressWithCascade(address);
       }
    } 
        
    /**
     * lookup Address entity Address, criteria and max result number
     */
    public List<Address> lookupAddress(Address address, Criteria criteria, Integer numberOfResult, EntityManager em) {
		boolean isWhereSet = false;
        StringBuffer query = new StringBuffer();
        query.append ("SELECT address FROM Address address ");
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
    
    public List<Address> lookupAddress(Address address, Criteria criteria, Integer numberOfResult) {
		return lookupAddress(address, criteria, numberOfResult, getEntityManager());
    }

    public Integer updateNotNullOnlyAddress (Address address, Criteria criteria) {
        String queryWhat = getUpdateNotNullOnlyAddressQueryChunkPrototype (address);
        StringBuffer query = new StringBuffer (queryWhat);
        boolean isWhereSet = false;
        for (Criterion criterion : criteria.getClauseCriterions()) {
            query.append (getQueryWHERE_AND (isWhereSet));
            isWhereSet = true;   
            query.append(criterion.getExpression());			
        }  
        Query jpaQuery = getEntityManager().createQuery(query.toString());
        isWhereSet = false;
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
	
	public Address affectAddress (Address address) {
	    return referAddress (address, null, false);		    
	}
		
	/**
	 * Assign the first address retrieved corresponding to the address criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no address corresponding in the database. Then address is inserted and returned with its primary key(s). 
	 */
	public Address assignAddress (Address address) {
		return referAddress (address, null, true);
	}

	/**
	 * Assign the first address retrieved corresponding to the mask criteria.
	 * Blank criteria are mapped to null.
	 * If no criteria is found, null is returned.
	 * If there is no address corresponding in the database. 
	 * Then address is inserted and returned with its primary key(s). 
	 * Mask servers usually to set unique keys or the semantic reference
	 */
    public Address assignAddress (Address address, Address mask) {
		return referAddress (address, mask, true);
	}

	public Address referAddress (Address address, Address mask, boolean isAssign) {
		address = assignBlankToNull (address);
		if (isAllNull(address))
			return null;
		else {
			List<Address> list;
			if (mask==null)
				list = searchPrototypeAddress(address);
			else
				list = searchPrototypeAddress(mask);
			if (list.isEmpty()) {
			    if (isAssign)
			       insertAddress(address);
			    else
				   return null;
			}
			else if (list.size()==1)
				address.copy(list.get(0));
			else 
				//TODO log error
				address.copy(list.get(0));
		}
		return address;		    
	}

   public Address assignAddressUseCache (Address address) {
      return referAddressUseCache (address, true);
   }
      		
   public Address affectAddressUseCache (Address address) {
      return referAddressUseCache (address, false);
   }
      		
   public Address referAddressUseCache (Address address, boolean isAssign) {
	  String key = getCacheKey(null, address, null, "assignAddress");
      Address addressCache = (Address)simpleCache.get(key);
      if (addressCache==null) {
         addressCache = referAddress (address, null, isAssign);
         if (key!=null)
         	simpleCache.put(key, addressCache);
      }
      address.copy(addressCache);
      return addressCache;
   }	

	private String getCacheKey (Address addressWhat, Address positiveAddress, Address negativeAddress, String queryKey) {
	    StringBuffer sb = new StringBuffer();
	    sb.append(queryKey);
	    if (addressWhat!=null)
	       sb.append(addressWhat.toStringWithParents());
	    if (positiveAddress!=null)
	       sb.append(positiveAddress.toStringWithParents());
	    if (negativeAddress!=null)
	       sb.append(negativeAddress.toStringWithParents());
	    return sb.toString();
	}
	
    public Address partialLoadWithParentFirstAddress(Address addressWhat, Address positiveAddress, Address negativeAddress){
		List <Address> list = partialLoadWithParentAddress(addressWhat, positiveAddress, negativeAddress);
		return (!list.isEmpty())?(Address)list.get(0):null;
    }
    
    public Address partialLoadWithParentFirstAddressUseCache(Address addressWhat, Address positiveAddress, Address negativeAddress, Boolean useCache){
		List <Address> list = partialLoadWithParentAddressUseCache(addressWhat, positiveAddress, negativeAddress, useCache);
		return (!list.isEmpty())?(Address)list.get(0):null;
    }
	
	public Address partialLoadWithParentFirstAddressUseCacheOnResult(Address addressWhat, Address positiveAddress, Address negativeAddress, Boolean useCache){
		List <Address> list = partialLoadWithParentAddressUseCacheOnResult(addressWhat, positiveAddress, negativeAddress, useCache);
		return (!list.isEmpty())?(Address)list.get(0):null;
    }
	//
	protected List<Address> partialLoadWithParentAddress(Address addressWhat, Address positiveAddress, Address negativeAddress, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentAddress(addressWhat, positiveAddress, negativeAddress, new QuerySelectInit(), nbOfResult, useCache);
	}	  

	protected List partialLoadWithParentAddressQueryResult (Address addressWhat, Address positiveAddress, Address negativeAddress, Integer nbOfResult, Boolean useCache) {
		 return partialLoadWithParentAddressQueryResult (addressWhat, positiveAddress, negativeAddress, new QuerySelectInit(), nbOfResult, useCache);
	}	
    
    public List<Address> getDistinctAddress(Address addressWhat, Address positiveAddress, Address negativeAddress) {
		 return partialLoadWithParentAddress(addressWhat, positiveAddress, negativeAddress, new QuerySelectDistinctInit(), null, false);
	}
	
	public List<Address> partialLoadWithParentAddress(Address addressWhat, Address positiveAddress, Address negativeAddress) {
		 return partialLoadWithParentAddress(addressWhat, positiveAddress, negativeAddress, new QuerySelectInit(), null, false);
	}	
  
	public List<Address> partialLoadWithParentAddressUseCacheOnResult(Address addressWhat, Address positiveAddress, Address negativeAddress, Boolean useCache) {
		String key = getCacheKey(addressWhat, positiveAddress, negativeAddress, "partialLoadWithParentAddress");
		List<Address> list = (List<Address>)simpleCache.get(key);
		if (list==null || list.isEmpty()) {
			list = partialLoadWithParentAddress(addressWhat, positiveAddress, negativeAddress);
			if (!list.isEmpty())
			   simpleCache.put(key, list);
		}
		return list;	
	}	

	public List<Address> partialLoadWithParentAddressUseCache(Address addressWhat, Address positiveAddress, Address negativeAddress, Boolean useCache) {
		String key = getCacheKey(addressWhat, positiveAddress, negativeAddress, "partialLoadWithParentAddress");
		List<Address> list = (List<Address>)simpleCache.get(key);
		if (list==null) {
			list = partialLoadWithParentAddress(addressWhat, positiveAddress, negativeAddress);
			simpleCache.put(key, list);
		}
		return list;	
	}	
	
	private List<Address> handleLoadWithParentAddress(Map beanPath, List list, Address addressWhat) {
	    return handleLoadWithParentAddress(beanPath, list, addressWhat, true);  
	}
	
	private List<Address> handleLoadWithParentAddress(Map beanPath, List list, Address addressWhat, boolean isHql) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentAddressWithOneElementInRow(list, beanPath, addressWhat, isHql);
		}
		return handlePartialLoadWithParentAddress(list, beanPath, addressWhat, isHql);	
	}
	
    	// to set in super class	
	protected void populateAddress (Address address, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(address, beanPath, value);
	}
	
	protected void populateAddressFromSQL (Address address, Object value, String beanPath) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	   BeanUtils.populateBeanObject(address, beanPath, value);
	}
    	// to set in super class BEWARE: genericity is only one level!!!!! first level is a copy second level is a reference!!! change to address.clone() instead
	private Address cloneAddress (Address address) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		//return (Address) BeanUtils.cloneBeanObject(address);
	   if (address==null) return new Address();
	   return address.clone();
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
	
    public List<Address> countDistinct (Address whatMask, Address whereEqCriteria) {
       return partialLoadWithParentAddress(whatMask, whereEqCriteria, null, new QuerySelectCountInit("address"), null, false);
    }   
  	
    public Long count(Address whereEqCriteria) {
	    return count(null, whereEqCriteria, EntityMatchType.ALL, OperandType.EQUALS, true); 
/*        Query query = getEntityManager().createQuery(getSelectCountPrototype(whereEqCriteria));
        List<Long> list = query.getResultList();
    	if (!list.isEmpty()) {
            return list.get(0);
    	}
    	return 0L;
*/
    }


    public Long count(Address whatMask, Address whereCriteria, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
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

	protected String countQuery (Address address, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        String what = "SELECT count(*) FROM Address address ";
		return findQuery (address, null, what, matchType, operandType, caseSensitivenessType, null);
    }
	
    protected String getSelectCountPrototype (Address whereEqCriteria) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryWhere = new StringBuffer();
        query.append ("SELECT count(*) FROM Address address ");
        queryWhere.append (getWhereEqualWhereQueryChunk(whereEqCriteria, false));   
	    return getHQuery(query.toString(), queryWhere.toString());
    }
			
   public Address getFirstAddressWhereConditionsAre (Address address) {
      List<Address> list = partialLoadWithParentAddress(getDefaultAddressWhat(), address, null, 1, false);
      if (list.isEmpty()) {
         return null;
      }
      else if (list.size()==1)
         return list.get(0);
      else 
      //TODO log error
         return list.get(0);	
	}

   private List getFirstResultWhereConditionsAre (Address address) {
      return partialLoadWithParentAddressQueryResult(getDefaultAddressWhat(), address, null, 1, false);	
   }
   
   protected Address getDefaultAddressWhat() {
      Address address = new Address();
      address.setAddressid(Integer.valueOf(-1));
      return address;
   }
   
	public Address getFirstAddress (Address address) {
		if (isAllNull(address))
			return null;
		else {
			List<Address> list = searchPrototype (address, 1);
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
    * checks if the Address entity exists
    */           
    public boolean existsAddress (Address address) {
       if (getFirstAddress(address)!=null)
          return true;
       return false;  
    }
        
    public boolean existsAddressWhereConditionsAre (Address address) {
       if (getFirstResultWhereConditionsAre (address).isEmpty())
          return false;
       return true;  
    }

	private int countPartialField (Address address) {
	   int cpt = 0;
       if (address.getAddressid() != null) {
          cpt++;
       }
       if (address.getStreet1() != null) {
          cpt++;
       }
       if (address.getStreet2() != null) {
          cpt++;
       }
       if (address.getCity() != null) {
          cpt++;
       }
       if (address.getState() != null) {
          cpt++;
       }
       if (address.getZip() != null) {
          cpt++;
       }
       if (address.getLatitude() != null) {
          cpt++;
       }
       if (address.getLongitude() != null) {
          cpt++;
       }
       return cpt;
	}   

	public List<Address> partialLoadWithParentAddress(Address what, Address positiveAddress, Address negativeAddress, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		Map beanPath = new Hashtable();
		List list = partialLoadWithParentAddressJPAQueryResult (what, positiveAddress, negativeAddress, queryWhatInit, beanPath, nbOfResult, useCache);
		return handlePartialLoadWithParentResult(what, list, beanPath);
	}
	
	public List<Address> handlePartialLoadWithParentResult(Address what, List list, Map beanPath) {
		if (beanPath.size()==1) {
			return handlePartialLoadWithParentAddressWithOneElementInRow(list, beanPath, what, true);
		}
		return handlePartialLoadWithParentAddress(list, beanPath, what, true);
	}	

	private List partialLoadWithParentAddressQueryResult(Address addressWhat, Address positiveAddress, Address negativeAddress, QueryWhatInit queryWhatInit, Integer nbOfResult, Boolean useCache) {
		return partialLoadWithParentAddressJPAQueryResult (addressWhat, positiveAddress, negativeAddress, queryWhatInit, new Hashtable(), nbOfResult, useCache);
    }	
  
	private List partialLoadWithParentAddressJPAQueryResult(Address addressWhat, Address positiveAddress, Address negativeAddress, QueryWhatInit queryWhatInit, Map beanPath, Integer nbOfResult, Boolean useCache) {
		Query hquery = getPartialLoadWithParentJPAQuery (addressWhat, positiveAddress, negativeAddress, beanPath, queryWhatInit, nbOfResult);
		return hquery.getResultList();
    }	
   /**
    * @returns an JPA Hsql query based on entity Address and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentJPAQuery (Address addressWhat, Address positiveAddress, Address negativeAddress, Map beanPath, QueryWhatInit queryWhatInit, Integer nbOfResult) {
	   Query query = getPartialLoadWithParentJPARawQuery (addressWhat, positiveAddress, negativeAddress, beanPath, queryWhatInit);
	   if (nbOfResult!=null)
	      query.setMaxResults(nbOfResult);
	   return query;
    }
  	
   /**
    * @returns an JPA Raw Hsql query based on entity Address and its parents and the maximum number of result
    */
	protected Query getPartialLoadWithParentJPARawQuery (Address addressWhat, Address positiveAddress, Address negativeAddress, Map beanPath, QueryWhatInit queryWhatInit) {
	   return getEntityManager().createQuery(getPartialLoadWithParentRawHsqlQuery (addressWhat, positiveAddress, negativeAddress, beanPath, queryWhatInit));
    }
	
	private List<Address> handlePartialLoadWithParentAddress(List<Object[]> list, Map<Integer, String> beanPath, Address addressWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentAddress(list, beanPath, addressWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentAddress, message:"+ex.getMessage());
			return new ArrayList<Address>();
		}
    }

	private List<Address> handlePartialLoadWithParentAddressWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Address addressWhat, boolean isJql) {
		try {
			return convertPartialLoadWithParentAddressWithOneElementInRow(list, beanPath, addressWhat);
		} catch (Exception ex) {
			log.error("Error conversion list from handlePartialLoadWithParentAddressWithOneElementInRow, message:"+ex.getMessage());
			return new ArrayList<Address>();
		}
    }
    	
	 private List<Address> convertPartialLoadWithParentAddress(List<Object[]> list, Map<Integer, String> beanPath, Address addressWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Address> resultList = new ArrayList<Address>();
		 for (Object[] row : list) {		
		    Address address = cloneAddress (addressWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateAddress (address, row[(Integer)entry.getKey()], (String)entry.getValue());
		    }
		    resultList.add(address);
		 }
		 return resultList;		
	 }	
    
	 private List<Address> convertPartialLoadWithParentAddressWithOneElementInRow(List<Object> list, Map<Integer, String> beanPath, Address addressWhat) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		 List<Address> resultList = new ArrayList<Address>();
		 for (Object row : list) {		
		    Address address = cloneAddress (addressWhat);
		    Iterator<Entry<Integer, String>> iter = beanPath.entrySet().iterator();	
		    while (iter.hasNext()) {
		       Entry entry = iter.next();
		       populateAddress (address, row, (String)entry.getValue());
		    }
		    resultList.add(address);
		 }		 
		 return resultList;		
	 }
   
	public List partialLoadWithParentForBean(Object bean, Address addressWhat, Address positiveAddress, Address negativeAddress) {
		Map beanPath = new Hashtable();
		Query hquery = getPartialLoadWithParentJPAQuery (addressWhat, positiveAddress, negativeAddress, beanPath, new QuerySelectInit(), null);
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
	public String getPartialLoadWithParentRawHsqlQuery (Address address, Address positiveAddress, Address negativeAddress, Map beanPath, QueryWhatInit queryWhatInit) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentAddressQuery (address, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
		Hashtable aliasWhereHt = new Hashtable();
		String where = getPartialLoadWithParentWhereQuery (positiveAddress, null, aliasWhatHt, aliasWhereHt, null, null);
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
	public String findPartialLoadWithParentRawHsqlQuery (Address whatMask, Address criteriaMask, Map beanPath, QueryWhatInit queryWhatInit,  Address orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder) {
		Hashtable aliasWhatHt = new Hashtable();
		String what = getPartialLoadWithParentAddressQuery (whatMask, null, aliasWhatHt, null, null, beanPath, "", queryWhatInit);
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
	public String countPartialLoadWithParentRawHsqlQuery (Address whatMask, Address criteriaMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
		Map beanPath = new Hashtable();
		Hashtable aliasWhatHt = new Hashtable();
		// used to initiate the how part of the what
		getPartialLoadWithParentAddressQuery (whatMask, null, aliasWhatHt, null, null, beanPath, "", new QuerySelectInit());
		String what = "select count(address) ";
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
    	
	public String findPartialQuery (Address whatMask, Address criteriaMask, Address orderMask, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType, QuerySortOrder sortOrder, Map beanPath) {
        QueryWhatInit queryWhatInit = new QuerySelectInit();
        return findPartialLoadWithParentRawHsqlQuery(whatMask, criteriaMask, beanPath, queryWhatInit, orderMask, matchType, operandType, caseSensitivenessType,  sortOrder);
    }
	
	/**
    * partial on a single entity load enables to specify the fields you want to load explicitly
    */         
	public List<Address> partialLoadAddress(Address address, Address positiveAddress, Address negativeAddress) {
	    Query hquery = getEntityManager().createQuery(getPartialLoadAddressQuery (address, positiveAddress, negativeAddress));
		int countPartialField = countPartialField(address);
		if (countPartialField==0) 
			return new ArrayList<Address>();
		List list = hquery.getResultList();
		Iterator iter = list.iterator();
		List<Address> returnList = new ArrayList<Address>();
		while(iter.hasNext()) {
			int index = 0;
			Object[] row;
			if (countPartialField==1) {
				row = new Object[1];
				row[0] = iter.next();
				} 
			else 
				row = (Object[]) iter.next();
			Address addressResult = new Address();
			if (address.getStreet1() != null) {
                addressResult.setStreet1((String) row[index]);
				index++;
			}
			if (address.getStreet2() != null) {
                addressResult.setStreet2((String) row[index]);
				index++;
			}
			if (address.getCity() != null) {
                addressResult.setCity((String) row[index]);
				index++;
			}
			if (address.getState() != null) {
                addressResult.setState((String) row[index]);
				index++;
			}
			if (address.getZip() != null) {
                addressResult.setZip((String) row[index]);
				index++;
			}
			if (address.getLatitude() != null) {
                addressResult.setLatitude((java.math.BigDecimal) row[index]);
				index++;
			}
			if (address.getLongitude() != null) {
                addressResult.setLongitude((java.math.BigDecimal) row[index]);
				index++;
			}
			returnList.add(addressResult);
        }
	    return returnList;
	}

	public static String getPartialLoadWithParentWhereQuery (
	   Address criteriaMask, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias,
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
	   Address address, Boolean isWhereSet, Hashtable aliasHt, Hashtable aliasWhereHt, String childAlias, String childFKAlias) {
	   if (address==null)
	      return "";
	   String alias = null;
	   if (aliasWhereHt == null) {
	      aliasWhereHt = new Hashtable();
	   } 
	   if (isLookedUp(address)){
	      alias = getNextAlias (aliasWhereHt, address);
		  aliasWhereHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (address.getAddressid() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".addressid = "+ address.getAddressid() + " ");
       }
       if (address.getStreet1() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".street1 = '"+ address.getStreet1()+"' ");
       }
       if (address.getStreet2() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".street2 = '"+ address.getStreet2()+"' ");
       }
       if (address.getCity() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".city = '"+ address.getCity()+"' ");
       }
       if (address.getState() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".state = '"+ address.getState()+"' ");
       }
       if (address.getZip() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".zip = '"+ address.getZip()+"' ");
       }
       if (address.getLatitude() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".latitude = "+ address.getLatitude() + " ");
       }
       if (address.getLongitude() != null) {
           query.append (getQueryBLANK_AND (isWhereSet));
		   isWhereSet = true;
           query.append(" "+alias+".longitude = "+ address.getLongitude() + " ");
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
	
    public static String getPartialLoadWithParentAddressQuery (
	   Address address, Boolean isWhereSet, Hashtable aliasHt, String childAlias, String childFKAlias, Map beanPath, String rootPath, QueryWhatInit queryWhatInit) {
	   if (address==null)
	      return "";
	   String alias = null;
	   if (aliasHt == null) {
	      aliasHt = new Hashtable();
	   } 
	   if (isLookedUp(address)){
	      alias = getNextAlias (aliasHt, address);
		  aliasHt.put (getAliasKey(alias), getAliasConnection(alias, childAlias, childFKAlias));
	   }
	   if (isWhereSet == null)
          isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (address.getAddressid() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"addressid");
          query.append(" "+alias+".addressid ");
       }
       if (address.getStreet1() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"street1");
          query.append(" "+alias+".street1 ");
       }
       if (address.getStreet2() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"street2");
          query.append(" "+alias+".street2 ");
       }
       if (address.getCity() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"city");
          query.append(" "+alias+".city ");
       }
       if (address.getState() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"state");
          query.append(" "+alias+".state ");
       }
       if (address.getZip() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"zip");
          query.append(" "+alias+".zip ");
       }
       if (address.getLatitude() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"latitude");
          query.append(" "+alias+".latitude ");
       }
       if (address.getLongitude() != null) {
          query.append (queryWhatInit.getWhatInit (isWhereSet));
          isWhereSet = true; 
          beanPath.put(beanPath.size(), rootPath+"longitude");
          query.append(" "+alias+".longitude ");
       }
//       query.append(getAddressSearchEqualQuery (positiveAddress, negativeAddress));
	   return query.toString(); 
    }
	
	protected static String getAliasConnection(String existingAlias, String childAlias, String childFKAlias) {
		if (childAlias==null)
		   return "";
		return childAlias+"."+childFKAlias+" = "+existingAlias+"."+"addressid";
	}
	
	protected static String getAliasKey (String alias) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return "Address|"+alias;
	}
	
	protected static String getAliasKeyAlias (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
		return StringUtils.substringAfter(aliasKey, "|");
	}
	
	protected static String getAliasKeyDomain (String aliasKey) {
	  //TODO this is a temporary solution use a dedicated object in BslaHiberateDaoSupport
	  return StringUtils.substringBefore(aliasKey, "|");
	}
	
	protected static String getNextAlias (Hashtable aliasHt, Address address) {
		int cptSameAlias = 0;
		Enumeration<String> keys = aliasHt.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("address"))
				cptSameAlias++;
		}
		if (cptSameAlias==0)
			return "address";
		else
			return "address_"+cptSameAlias;
	}
	
	
	protected static boolean isLookedUp (Address address) {
	   if (address==null)
		  return false;
       if (address.getAddressid() != null) {
	      return true;
       }
       if (address.getStreet1() != null) {
	      return true;
       }
       if (address.getStreet2() != null) {
	      return true;
       }
       if (address.getCity() != null) {
	      return true;
       }
       if (address.getState() != null) {
	      return true;
       }
       if (address.getZip() != null) {
	      return true;
       }
       if (address.getLatitude() != null) {
	      return true;
       }
       if (address.getLongitude() != null) {
	      return true;
       }
       return false;   
	}
	
    public String getPartialLoadAddressQuery(
	   Address address, 
	   Address positiveAddress, 
	   Address negativeAddress) {
       boolean isWhereSet = false;
       StringBuffer query = new StringBuffer();
       if (address.getAddressid() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" addressid ");
       }
       if (address.getStreet1() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" street1 ");
       }
       if (address.getStreet2() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" street2 ");
       }
       if (address.getCity() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" city ");
       }
       if (address.getState() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" state ");
       }
       if (address.getZip() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" zip ");
       }
       if (address.getLatitude() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" latitude ");
       }
       if (address.getLongitude() != null) {
          query.append (getQuerySelectComma (isWhereSet));
          isWhereSet = true; 
          query.append(" longitude ");
       }
       query.append(getAddressSearchEqualQuery (positiveAddress, negativeAddress));
	   return query.toString(); 
    }
	
	public List<Address> searchPrototypeWithCacheAddress(Address address) {
		SimpleCache simpleCache = new SimpleCache();
		List<Address> list = (List<Address>)simpleCache.get(address.toString());
		if (list==null) {
			list = searchPrototypeAddress(address);
			simpleCache.put(address.toString(), list);
		}
		return list;
	}

    public List<Address> loadGraph(Address graphMaskWhat, List<Address> whereMask) {
        return loadGraphOneLevel(graphMaskWhat, whereMask);
    }

	public List<Address> loadGraphOneLevel(Address graphMaskWhat, List<Address> whereMask) {
		//first get roots element from where list mask
		// this brings the level 0 of the graph (root level)
 		graphMaskWhat.setAddressid(graphMaskWhat.integerMask__);
		List<Address> addresss = searchPrototypeAddress (whereMask);
		// for each sub level perform the search with a subquery then reassemble
		// 1. get all sublevel queries
		// 2. perform queries on the correct dao
		// 3. reassemble
		return getLoadGraphOneLevel (graphMaskWhat, addresss);
	}

	private List<Address> copy(List<Address> inputs) {
		List<Address> l = new ArrayList<Address>();
		for (Address input : inputs) {
			Address copy = new Address();
			copy.copy(input);
			l.add(copy);
		}
		return l;
	}
	   
	private List<Address> getLoadGraphOneLevel (Address graphMaskWhat, List<Address> parents) {
	    return loadGraphFromParentKey (graphMaskWhat, parents);
	} 
	
	public List<Address> loadGraphFromParentKey (Address graphMaskWhat, List<Address> parents) {
		//foreach children:
		//check if not empty take first
		parents = copy (parents); //working with detached entities to avoid unnecessary sql calls
		if (parents==null || parents.isEmpty())
		   return parents;
		List<String> ids = getPk (parents);
		if (graphMaskWhat.getMyGoodItems()!=null && !graphMaskWhat.getMyGoodItems().isEmpty()) {
			for (MyGoodItem childWhat : graphMaskWhat.getMyGoodItems()) {
				childWhat.setAddressAddressid_(graphMaskWhat.integerMask__); // add to the what mask, usefull for reconciliation
				MyGoodItemExtendedJPAImpl mygooditemextendedjpaimpl = new MyGoodItemExtendedJPAImpl ();
				List<MyGoodItem> children = mygooditemextendedjpaimpl.lookupMyGoodItem(childWhat, getFkCriteria(" addressid ", ids), null, getEntityManager());
				reassembleMyGoodItem (children, parents);				
				break;
			}
		}
		return parents;
	}
	
	private void reassembleMyGoodItem (List<MyGoodItem> children, List<Address> parents) {
		for (MyGoodItem child : children) {
			for (Address parent : parents) {
				if (parent.getAddressid()!=null && parent.getAddressid().toString().equals(child.getAddressAddressid()+"")) {
					parent.addMyGoodItems(child); 
					child.setAddressAddressid(parent);
					break;
				}
			}
		}
	}
	
	private List<String> getPk(List<Address> input) {
		List<String> s = new ArrayList<String>();
		for (Address t : input) {
			s.add(t.getAddressid()+"");
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
	public void find (QueryData<Address> data) {
		EntityCriteria<Address> filter = data.getEntityCriteria();
		Address entityWhat = data.getEntityWhat();
		Address criteriaMask = filter.getEntity();
		int start = data.getStart();
		int max = data.getMax();
		EntitySort<Address> entitySort = data.getEntitySort();
		QuerySortOrder sortOrder = entitySort.getOrder();
		Address sortMask = entitySort.getEntity();	

		List<Address> results = find(entityWhat, criteriaMask, sortMask, filter.getMatchType(), filter.getOperandType(), filter.getCaseSensitivenessType(), sortOrder, start, max);
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
     * return a list of Address entities 
     */
    public List<Address> getList () {
        //first lightweight implementation
        return searchPrototypeAddress(new Address());
    }
    /**
     * return a list of Address entities and sort
     */
    public List<Address> getList (Address orderMask, QuerySortOrder sortOrder) {
        return searchPrototype(new Address(), orderMask, sortOrder, null);
    }
    /**
     * return a list of Address entities and sort based on a Address prototype
     */
    public List<Address> list (Address mask, Address orderMask, QuerySortOrder sortOrder) {
        return searchPrototype(mask, orderMask, sortOrder, null);
    }

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
