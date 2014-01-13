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
	* - name      : SpringModelServiceInterface
	* - file name : SpringModelServiceInterface.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/
package net.sf.mp.demo.petshop.service.face;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

import java.util.List;
import java.util.ArrayList;

import net.sf.mp.demo.petshop.domain.pet.Address;
import net.sf.mp.demo.petshop.domain.pet.Category;
import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;
import net.sf.mp.demo.petshop.domain.pet.Tag;
import net.sf.mp.demo.petshop.domain.pet.Ziplocation;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;
import net.sf.minuteProject.model.data.criteria.QueryData;
import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;

public interface PetshopModelService {

	
    public List<Address> getList (Address address) ;
	
    public List<Category> getList (Category category) ;
	
    public List<Sellercontactinfo> getList (Sellercontactinfo sellercontactinfo) ;
	
    public List<Tag> getList (Tag tag) ;

    public Tag getManyToManyList (Tag tag, String child) ;
	
    public List<Ziplocation> getList (Ziplocation ziplocation) ;
	
    public List<MyGoodItem> getList (MyGoodItem myGoodItem) ;

    public MyGoodItem getManyToManyList (MyGoodItem myGoodItem, String child) ;
	
    public List<MyGoodProduct> getList (MyGoodProduct myGoodProduct) ;

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
