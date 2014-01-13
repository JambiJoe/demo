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

import net.sf.mp.demo.petshop.domain.pet.Ziplocation;
import net.sf.mp.demo.petshop.service.face.pet.ZiplocationService;
import net.sf.mp.demo.petshop.dao.face.pet.ZiplocationDao;
import net.sf.mp.demo.petshop.dao.face.pet.ZiplocationExtDao;

import org.springframework.transaction.annotation.Transactional;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: ZiplocationServiceImpl</p>
 *
 * <p>Description: Service layer Interface 
 * It offers coarse grain methods over the fine grain DAO layer:
 * It performs input and business validation.
 * </p>
 *
 */

@Service ("ziplocationService")
@Transactional
public class ZiplocationServiceImpl implements ZiplocationService {
    @Autowired
    @Qualifier("ziplocationDao")	
	ZiplocationDao ziplocationDao;
    @Autowired
    @Qualifier("ziplocationExtDao")	
	ZiplocationExtDao ziplocationExtDao;
	
    /**
     * Inserts a Ziplocation entity Ziplocation 
     * @param Ziplocation ziplocation
     */
    public void create (Ziplocation ziplocation) {
		ziplocationDao.insertZiplocation(ziplocation);
	}

    /**
     * Updates a Ziplocation entity 
     * @param Ziplocation ziplocation
     */
    public Ziplocation update(Ziplocation ziplocation) {
        return ziplocationDao.updateZiplocation(ziplocation);
    }

    public Ziplocation findById (java.lang.Integer zipcode) {
        return ziplocationDao.loadZiplocation(zipcode);
    }

	public Ziplocation findById (Ziplocation c) {
		return ziplocationDao.loadZiplocation(c.getZipcode());
	}

    public Ziplocation load (java.lang.Integer zipcode) {
        Ziplocation entity = findById(zipcode);
        return entity;
    }
	
	public void delete (Ziplocation ziplocation) {
	    Ziplocation var = findById (ziplocation.getZipcode());
		ziplocationDao.deleteZiplocation (var);
	}	

     /**
     * Saves a Ziplocation entity 
     * @param Ziplocation ziplocation
     */
    public void save(Ziplocation ziplocation){
		ziplocationDao.saveZiplocation (ziplocation);
	}
	
	public List<Ziplocation> findAll (Ziplocation ziplocation) {
		List<Ziplocation> list = ziplocationDao.searchPrototypeZiplocation (ziplocation);
        return convert(list);
	}
	
	public List<Ziplocation> findAny (Ziplocation ziplocation) {
		List<Ziplocation> list = ziplocationDao.searchPrototypeAnyZiplocation (ziplocation);
        return convert(list);
	}

    public List<Ziplocation> find (Ziplocation ziplocation, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        List<Ziplocation> list = ziplocationDao.find (ziplocation, matchType, operandType, caseSensitivenessType);
        return convert(list);
    }
	
    private List<Ziplocation> convert(List<Ziplocation> list) {
        List<Ziplocation> ret = new ArrayList<Ziplocation>();   
         for (Ziplocation a : list) {
            Ziplocation b = a.clone();
            ret.add(b);
        }
        return ret;
    }
    
//	@Override
	public void find(QueryData<Ziplocation> data) {
		ziplocationExtDao.find (data);
	}
	
    /**
     * return a list of Ziplocation entities 
     */
    //@Cachable
    public List<Ziplocation> getList () {
        Ziplocation sortMask = new Ziplocation();
        sortMask.setZipcode(Integer.valueOf(-1));
        return ziplocationExtDao.getList(sortMask, QuerySortOrder.ASC);
    }


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
