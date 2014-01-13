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
	* - name      : JSF.Spring.Lookup.Service
	* - file name : JSF.Spring.Lookup.Service.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/
package net.sf.mp.demo.petshop.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;
import  java.io.Serializable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.faces.model.SelectItem;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import net.sf.mp.demo.petshop.service.face.pet.AddressService;
import net.sf.mp.demo.petshop.service.face.pet.CategoryService;
import net.sf.mp.demo.petshop.service.face.pet.SellercontactinfoService;
import net.sf.mp.demo.petshop.service.face.pet.TagService;
import net.sf.mp.demo.petshop.service.face.pet.ZiplocationService;
import net.sf.mp.demo.petshop.service.face.product.MyGoodItemService;
import net.sf.mp.demo.petshop.service.face.product.MyGoodProductService;
@Service ("petshopLookupDefaultingService")
public class PetshopLookupDefaultingService implements Serializable{

    @Autowired 
	@Qualifier("addressService")
    private AddressService addressService;
    @Autowired 
	@Qualifier("categoryService")
    private CategoryService categoryService;
    @Autowired 
	@Qualifier("sellercontactinfoService")
    private SellercontactinfoService sellercontactinfoService;
    @Autowired 
	@Qualifier("tagService")
    private TagService tagService;
    @Autowired 
	@Qualifier("ziplocationService")
    private ZiplocationService ziplocationService;
    @Autowired 
	@Qualifier("myGoodItemService")
    private MyGoodItemService myGoodItemService;
    @Autowired 
	@Qualifier("myGoodProductService")
    private MyGoodProductService myGoodProductService;

    public List<SelectItem> getAddressList() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (net.sf.mp.demo.petshop.domain.pet.Address element : getAddresss()) {
            list.add(new SelectItem(element.getAddressid(),element.display()));
        }
        return list;
    }
	
    public List<net.sf.mp.demo.petshop.domain.pet.Address> getAddresss() {
        return addressService.getList();
    }
	
    public List<SelectItem> getCategoryList() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (net.sf.mp.demo.petshop.domain.pet.Category element : getCategorys()) {
            list.add(new SelectItem(element.getCategoryid(),element.display()));
        }
        return list;
    }
	
    public List<net.sf.mp.demo.petshop.domain.pet.Category> getCategorys() {
        return categoryService.getList();
    }
	
    public List<SelectItem> getSellercontactinfoList() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo element : getSellercontactinfos()) {
            list.add(new SelectItem(element.getContactinfoid(),element.display()));
        }
        return list;
    }
	
    public List<net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo> getSellercontactinfos() {
        return sellercontactinfoService.getList();
    }
	
    public List<SelectItem> getTagList() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (net.sf.mp.demo.petshop.domain.pet.Tag element : getTags()) {
            list.add(new SelectItem(element.getTagid(),element.display()));
        }
        return list;
    }
	
    public List<net.sf.mp.demo.petshop.domain.pet.Tag> getTags() {
        return tagService.getList();
    }
	
    public List<SelectItem> getZiplocationList() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (net.sf.mp.demo.petshop.domain.pet.Ziplocation element : getZiplocations()) {
            list.add(new SelectItem(element.getZipcode(),element.display()));
        }
        return list;
    }
	
    public List<net.sf.mp.demo.petshop.domain.pet.Ziplocation> getZiplocations() {
        return ziplocationService.getList();
    }
	
    public List<SelectItem> getMyGoodItemList() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (net.sf.mp.demo.petshop.domain.product.MyGoodItem element : getMyGoodItems()) {
            list.add(new SelectItem(element.getItemid(),element.display()));
        }
        return list;
    }
	
    public List<net.sf.mp.demo.petshop.domain.product.MyGoodItem> getMyGoodItems() {
        return myGoodItemService.getList();
    }
	
    public List<SelectItem> getMyGoodProductList() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (net.sf.mp.demo.petshop.domain.product.MyGoodProduct element : getMyGoodProducts()) {
            list.add(new SelectItem(element.getProductid(),element.display()));
        }
        return list;
    }
	
    public List<net.sf.mp.demo.petshop.domain.product.MyGoodProduct> getMyGoodProducts() {
        return myGoodProductService.getList();
    }
	

}
