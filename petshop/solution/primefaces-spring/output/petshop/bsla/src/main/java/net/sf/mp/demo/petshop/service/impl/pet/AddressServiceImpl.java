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
	* - name      : SpringServiceImpl
	* - file name : SpringServiceImpl.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/
package net.sf.mp.demo.petshop.service.impl.pet;

import java.util.List;
import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayList;

import net.sf.minuteProject.model.data.criteria.QueryData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;

import net.sf.mp.demo.petshop.domain.pet.Address;
import net.sf.mp.demo.petshop.service.face.pet.AddressService;
import net.sf.mp.demo.petshop.dao.face.pet.AddressDao;
import net.sf.mp.demo.petshop.dao.face.pet.AddressExtDao;

import org.springframework.transaction.annotation.Transactional;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: AddressServiceImpl</p>
 *
 * <p>Description: Service layer Interface 
 * It offers coarse grain methods over the fine grain DAO layer:
 * It performs input and business validation.
 * </p>
 *
 */

@Service ("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    @Qualifier("addressDao")	
	AddressDao addressDao;
    @Autowired
    @Qualifier("addressExtDao")	
	AddressExtDao addressExtDao;
	
    /**
     * Inserts a Address entity Address 
     * @param Address address
     */
    public void create (Address address) {
		addressDao.insertAddress(address);
	}

    /**
     * Updates a Address entity 
     * @param Address address
     */
    public Address update(Address address) {
        return addressDao.updateAddress(address);
    }

    public Address findById (java.lang.Integer addressid) {
        return addressDao.loadAddress(addressid);
    }

	public Address findById (Address c) {
		return addressDao.loadAddress(c.getAddressid());
	}

    public Address load (java.lang.Integer addressid) {
        Address entity = findById(addressid);
        return entity;
    }
	
	public void delete (Address address) {
	    Address var = findById (address.getAddressid());
		addressDao.deleteAddress (var);
	}	

     /**
     * Saves a Address entity 
     * @param Address address
     */
    public void save(Address address){
		addressDao.saveAddress (address);
	}
	
	public List<Address> findAll (Address address) {
		List<Address> list = addressDao.searchPrototypeAddress (address);
        return convert(list);
	}
	
	public List<Address> findAny (Address address) {
		List<Address> list = addressDao.searchPrototypeAnyAddress (address);
        return convert(list);
	}

    public List<Address> find (Address address, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        List<Address> list = addressDao.find (address, matchType, operandType, caseSensitivenessType);
        return convert(list);
    }
	
    private List<Address> convert(List<Address> list) {
        List<Address> ret = new ArrayList<Address>();   
         for (Address a : list) {
            Address b = a.clone();
            ret.add(b);
        }
        return ret;
    }
    
//	@Override
	public void find(QueryData<Address> data) {
		addressExtDao.find (data);
	}
	
    /**
     * return a list of Address entities 
     */
    //@Cachable
    public List<Address> getList () {
        Address sortMask = new Address();
        sortMask.setAddressid(Integer.valueOf(-1));
        return addressExtDao.getList(sortMask, QuerySortOrder.ASC);
    }


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
