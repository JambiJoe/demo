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
	* - name      : SpringModelServiceImpl
	* - file name : SpringModelServiceImpl.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/
package net.sf.mp.demo.petshop.service.impl;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;
import net.sf.minuteProject.model.data.criteria.QueryData;
import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;

import net.sf.mp.demo.petshop.domain.pet.Address;
import net.sf.mp.demo.petshop.dao.face.pet.AddressDao;
import net.sf.mp.demo.petshop.dao.face.pet.AddressExtDao;
import net.sf.mp.demo.petshop.domain.pet.Category;
import net.sf.mp.demo.petshop.dao.face.pet.CategoryDao;
import net.sf.mp.demo.petshop.dao.face.pet.CategoryExtDao;
import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;
import net.sf.mp.demo.petshop.dao.face.pet.SellercontactinfoDao;
import net.sf.mp.demo.petshop.dao.face.pet.SellercontactinfoExtDao;
import net.sf.mp.demo.petshop.domain.pet.Tag;
import net.sf.mp.demo.petshop.dao.face.pet.TagDao;
import net.sf.mp.demo.petshop.dao.face.pet.TagExtDao;
import net.sf.mp.demo.petshop.domain.pet.Ziplocation;
import net.sf.mp.demo.petshop.dao.face.pet.ZiplocationDao;
import net.sf.mp.demo.petshop.dao.face.pet.ZiplocationExtDao;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.mp.demo.petshop.dao.face.product.MyGoodItemDao;
import net.sf.mp.demo.petshop.dao.face.product.MyGoodItemExtDao;
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import net.sf.mp.demo.petshop.dao.face.product.MyGoodProductDao;
import net.sf.mp.demo.petshop.dao.face.product.MyGoodProductExtDao;

import net.sf.mp.demo.petshop.service.face.PetshopModelService;

/**
 *
 * <p>Title: PetshopModelServiceImpl</p>
 *
 * <p>Description: Service layer Interface 
 * It offers coarse grain methods over the fine grain DAO layer:
 * It performs input and business validation.
 * </p>
 *
 */
@Service ("petshopModelService")
@Transactional
public class PetshopModelServiceImpl implements PetshopModelService {


    @Autowired
    @Qualifier("addressDao")	
	AddressDao addressDao;
    @Autowired
    @Qualifier("addressExtDao")	
	AddressExtDao addressExtDao;
	
    public List<Address> getList (Address address) {
        Address sortMask = new Address();
        sortMask.setAddressid(Integer.valueOf(-1));
        return addressExtDao.list(address, sortMask, QuerySortOrder.ASC);
    }



    @Autowired
    @Qualifier("categoryDao")	
	CategoryDao categoryDao;
    @Autowired
    @Qualifier("categoryExtDao")	
	CategoryExtDao categoryExtDao;
	
    public List<Category> getList (Category category) {
        Category sortMask = new Category();
        sortMask.setDescription(new String());
        return categoryExtDao.list(category, sortMask, QuerySortOrder.ASC);
    }



    @Autowired
    @Qualifier("sellercontactinfoDao")	
	SellercontactinfoDao sellercontactinfoDao;
    @Autowired
    @Qualifier("sellercontactinfoExtDao")	
	SellercontactinfoExtDao sellercontactinfoExtDao;
	
    public List<Sellercontactinfo> getList (Sellercontactinfo sellercontactinfo) {
        Sellercontactinfo sortMask = new Sellercontactinfo();
        sortMask.setContactinfoid(Integer.valueOf(-1));
        return sellercontactinfoExtDao.list(sellercontactinfo, sortMask, QuerySortOrder.ASC);
    }



    @Autowired
    @Qualifier("tagDao")	
	TagDao tagDao;
    @Autowired
    @Qualifier("tagExtDao")	
	TagExtDao tagExtDao;
	
    public List<Tag> getList (Tag tag) {
        Tag sortMask = new Tag();
        sortMask.setTag(new String());
        return tagExtDao.list(tag, sortMask, QuerySortOrder.ASC);
    }


    public Tag getManyToManyList (Tag tag, String child) {
       Tag e = tagDao.loadTag(tag.getTagid());
       if ("myGoodItemTagItemViaItemid".equals(child))
	      Hibernate.initialize(e.getMyGoodItemTagItemViaItemid());
       return e;
	}

    @Autowired
    @Qualifier("ziplocationDao")	
	ZiplocationDao ziplocationDao;
    @Autowired
    @Qualifier("ziplocationExtDao")	
	ZiplocationExtDao ziplocationExtDao;
	
    public List<Ziplocation> getList (Ziplocation ziplocation) {
        Ziplocation sortMask = new Ziplocation();
        sortMask.setZipcode(Integer.valueOf(-1));
        return ziplocationExtDao.list(ziplocation, sortMask, QuerySortOrder.ASC);
    }



    @Autowired
    @Qualifier("myGoodItemDao")	
	MyGoodItemDao myGoodItemDao;
    @Autowired
    @Qualifier("myGoodItemExtDao")	
	MyGoodItemExtDao myGoodItemExtDao;
	
    public List<MyGoodItem> getList (MyGoodItem myGoodItem) {
        MyGoodItem sortMask = new MyGoodItem();
        sortMask.setItemid(Integer.valueOf(-1));
        return myGoodItemExtDao.list(myGoodItem, sortMask, QuerySortOrder.ASC);
    }


    public MyGoodItem getManyToManyList (MyGoodItem myGoodItem, String child) {
       MyGoodItem e = myGoodItemDao.loadMyGoodItem(myGoodItem.getItemid());
       if ("tagTagItemViaTagid".equals(child))
	      Hibernate.initialize(e.getTagTagItemViaTagid());
       return e;
	}

    @Autowired
    @Qualifier("myGoodProductDao")	
	MyGoodProductDao myGoodProductDao;
    @Autowired
    @Qualifier("myGoodProductExtDao")	
	MyGoodProductExtDao myGoodProductExtDao;
	
    public List<MyGoodProduct> getList (MyGoodProduct myGoodProduct) {
        MyGoodProduct sortMask = new MyGoodProduct();
        sortMask.setProductid(new String());
        return myGoodProductExtDao.list(myGoodProduct, sortMask, QuerySortOrder.ASC);
    }



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
