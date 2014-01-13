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
package net.sf.mp.demo.petshop.service.impl.product;

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

import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.mp.demo.petshop.service.face.product.MyGoodItemService;
import net.sf.mp.demo.petshop.dao.face.product.MyGoodItemDao;
import net.sf.mp.demo.petshop.dao.face.product.MyGoodItemExtDao;

import org.springframework.transaction.annotation.Transactional;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;
import net.sf.mp.demo.petshop.domain.pet.Address;
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;
import net.sf.mp.demo.petshop.domain.pet.Tag;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: MyGoodItemServiceImpl</p>
 *
 * <p>Description: Service layer Interface 
 * It offers coarse grain methods over the fine grain DAO layer:
 * It performs input and business validation.
 * </p>
 *
 */

@Service ("myGoodItemService")
@Transactional
public class MyGoodItemServiceImpl implements MyGoodItemService {
    @Autowired
    @Qualifier("myGoodItemDao")	
	MyGoodItemDao myGoodItemDao;
    @Autowired
    @Qualifier("myGoodItemExtDao")	
	MyGoodItemExtDao myGoodItemExtDao;
	
    /**
     * Inserts a MyGoodItem entity MyGoodItem 
     * @param MyGoodItem mygooditem
     */
    public void create (MyGoodItem mygooditem) {
		myGoodItemDao.insertMyGoodItem(mygooditem);
	}

    /**
     * Updates a MyGoodItem entity 
     * @param MyGoodItem mygooditem
     */
    public MyGoodItem update(MyGoodItem mygooditem) {
        return myGoodItemDao.updateMyGoodItem(mygooditem);
    }

    public MyGoodItem findById (java.lang.Integer itemid) {
        return myGoodItemDao.loadMyGoodItem(itemid);
    }

	public MyGoodItem findById (MyGoodItem c) {
		return myGoodItemDao.loadMyGoodItem(c.getItemid());
	}

    public MyGoodItem load (java.lang.Integer itemid) {
        MyGoodItem entity = findById(itemid);
        if (entity.getAddressAddressid()!=null)
	       entity.getAddressAddressid().getAddressid();//improve with partialparent loading
        if (entity.getThisIsMyProduct()!=null)
	       entity.getThisIsMyProduct().getProductid();//improve with partialparent loading
        if (entity.getContactinfoContactinfoid()!=null)
	       entity.getContactinfoContactinfoid().getContactinfoid();//improve with partialparent loading
        for (net.sf.mp.demo.petshop.domain.pet.Tag element : entity.getTagTagItemViaTagid()){
           element.toString();//eager load
        }
        return entity;
    }
	
	public void delete (MyGoodItem mygooditem) {
	    MyGoodItem var = findById (mygooditem.getItemid());
		myGoodItemDao.deleteMyGoodItem (var);
	}	

     /**
     * Saves a MyGoodItem entity 
     * @param MyGoodItem mygooditem
     */
    public void save(MyGoodItem mygooditem){
		myGoodItemDao.saveMyGoodItem (mygooditem);
	}
	
	public List<MyGoodItem> findAll (MyGoodItem mygooditem) {
		List<MyGoodItem> list = myGoodItemDao.searchPrototypeMyGoodItem (mygooditem);
        return convert(list);
	}
	
	public List<MyGoodItem> findAny (MyGoodItem mygooditem) {
		List<MyGoodItem> list = myGoodItemDao.searchPrototypeAnyMyGoodItem (mygooditem);
        return convert(list);
	}

    public List<MyGoodItem> find (MyGoodItem mygooditem, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        List<MyGoodItem> list = myGoodItemDao.find (mygooditem, matchType, operandType, caseSensitivenessType);
        return convert(list);
    }
	
    private List<MyGoodItem> convert(List<MyGoodItem> list) {
        List<MyGoodItem> ret = new ArrayList<MyGoodItem>();   
         for (MyGoodItem a : list) {
            MyGoodItem b = a.clone();
            ret.add(b);
        }
        return ret;
    }
    
//	@Override
	public void find(QueryData<MyGoodItem> data) {
		myGoodItemExtDao.find (data);
	}
	
    /**
     * return a list of MyGoodItem entities 
     */
    //@Cachable
    public List<MyGoodItem> getList () {
        MyGoodItem sortMask = new MyGoodItem();
        sortMask.setItemid(Integer.valueOf(-1));
        return myGoodItemExtDao.getList(sortMask, QuerySortOrder.ASC);
    }

	public void addTagTagItemViaTagid(MyGoodItem c, Tag ... element) {
		MyGoodItem cc = findById (c);
        cc.getTagTagItemViaTagid().addAll(Arrays.asList(element));
        save(cc);
	}
	
	public void removeTagTagItemViaTagid(MyGoodItem c, Tag ... element) {
		MyGoodItem cc = findById (c);
        for (Iterator<Tag> iterator = cc.getTagTagItemViaTagid().iterator(); iterator.hasNext();) {
            Tag e = iterator.next();
			for (Tag ele : element) {
                if (e.equals(ele))
                    iterator.remove();
            }
        }
        save(cc);
	}
	

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
