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

import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;
import net.sf.mp.demo.petshop.service.face.pet.SellercontactinfoService;
import net.sf.mp.demo.petshop.dao.face.pet.SellercontactinfoDao;
import net.sf.mp.demo.petshop.dao.face.pet.SellercontactinfoExtDao;

import org.springframework.transaction.annotation.Transactional;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: SellercontactinfoServiceImpl</p>
 *
 * <p>Description: Service layer Interface 
 * It offers coarse grain methods over the fine grain DAO layer:
 * It performs input and business validation.
 * </p>
 *
 */

@Service ("sellercontactinfoService")
@Transactional
public class SellercontactinfoServiceImpl implements SellercontactinfoService {
    @Autowired
    @Qualifier("sellercontactinfoDao")	
	SellercontactinfoDao sellercontactinfoDao;
    @Autowired
    @Qualifier("sellercontactinfoExtDao")	
	SellercontactinfoExtDao sellercontactinfoExtDao;
	
    /**
     * Inserts a Sellercontactinfo entity Sellercontactinfo 
     * @param Sellercontactinfo sellercontactinfo
     */
    public void create (Sellercontactinfo sellercontactinfo) {
		sellercontactinfoDao.insertSellercontactinfo(sellercontactinfo);
	}

    /**
     * Updates a Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     */
    public Sellercontactinfo update(Sellercontactinfo sellercontactinfo) {
        return sellercontactinfoDao.updateSellercontactinfo(sellercontactinfo);
    }

    public Sellercontactinfo findById (java.lang.Integer contactinfoid) {
        return sellercontactinfoDao.loadSellercontactinfo(contactinfoid);
    }

	public Sellercontactinfo findById (Sellercontactinfo c) {
		return sellercontactinfoDao.loadSellercontactinfo(c.getContactinfoid());
	}

    public Sellercontactinfo load (java.lang.Integer contactinfoid) {
        Sellercontactinfo entity = findById(contactinfoid);
        return entity;
    }
	
	public void delete (Sellercontactinfo sellercontactinfo) {
	    Sellercontactinfo var = findById (sellercontactinfo.getContactinfoid());
		sellercontactinfoDao.deleteSellercontactinfo (var);
	}	

     /**
     * Saves a Sellercontactinfo entity 
     * @param Sellercontactinfo sellercontactinfo
     */
    public void save(Sellercontactinfo sellercontactinfo){
		sellercontactinfoDao.saveSellercontactinfo (sellercontactinfo);
	}
	
	public List<Sellercontactinfo> findAll (Sellercontactinfo sellercontactinfo) {
		List<Sellercontactinfo> list = sellercontactinfoDao.searchPrototypeSellercontactinfo (sellercontactinfo);
        return convert(list);
	}
	
	public List<Sellercontactinfo> findAny (Sellercontactinfo sellercontactinfo) {
		List<Sellercontactinfo> list = sellercontactinfoDao.searchPrototypeAnySellercontactinfo (sellercontactinfo);
        return convert(list);
	}

    public List<Sellercontactinfo> find (Sellercontactinfo sellercontactinfo, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        List<Sellercontactinfo> list = sellercontactinfoDao.find (sellercontactinfo, matchType, operandType, caseSensitivenessType);
        return convert(list);
    }
	
    private List<Sellercontactinfo> convert(List<Sellercontactinfo> list) {
        List<Sellercontactinfo> ret = new ArrayList<Sellercontactinfo>();   
         for (Sellercontactinfo a : list) {
            Sellercontactinfo b = a.clone();
            ret.add(b);
        }
        return ret;
    }
    
//	@Override
	public void find(QueryData<Sellercontactinfo> data) {
		sellercontactinfoExtDao.find (data);
	}
	
    /**
     * return a list of Sellercontactinfo entities 
     */
    //@Cachable
    public List<Sellercontactinfo> getList () {
        Sellercontactinfo sortMask = new Sellercontactinfo();
        sortMask.setContactinfoid(Integer.valueOf(-1));
        return sellercontactinfoExtDao.getList(sortMask, QuerySortOrder.ASC);
    }


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
