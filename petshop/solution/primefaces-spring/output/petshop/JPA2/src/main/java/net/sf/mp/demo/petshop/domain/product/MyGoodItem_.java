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
	* - name      : DomainEntityJPA2Metamodel
	* - file name : DomainEntityJPA2Metamodel.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:20 CET
*/
package net.sf.mp.demo.petshop.domain.product;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import net.sf.mp.demo.petshop.domain.pet.Address;
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;
import net.sf.mp.demo.petshop.domain.pet.Tag;

@StaticMetamodel(MyGoodItem.class)
public class MyGoodItem_ {

    public static volatile SingularAttribute<MyGoodItem, Integer> itemid;

    public static volatile SingularAttribute<MyGoodItem, String> name;
    public static volatile SingularAttribute<MyGoodItem, String> description;
    public static volatile SingularAttribute<MyGoodItem, String> imageurl;
    public static volatile SingularAttribute<MyGoodItem, String> imagethumburl;
    public static volatile SingularAttribute<MyGoodItem, java.math.BigDecimal> price;
    public static volatile SingularAttribute<MyGoodItem, Integer> totalscore;
    public static volatile SingularAttribute<MyGoodItem, Integer> numberofvotes;
    public static volatile SingularAttribute<MyGoodItem, Integer> disabled;


    public static volatile SingularAttribute<MyGoodItem, Address> addressAddressid;
    public static volatile SingularAttribute<MyGoodItem, Integer> addressAddressid_;

    public static volatile SingularAttribute<MyGoodItem, MyGoodProduct> thisIsMyProduct;
    public static volatile SingularAttribute<MyGoodItem, String> thisIsMyProduct_;

    public static volatile SingularAttribute<MyGoodItem, Sellercontactinfo> contactinfoContactinfoid;
    public static volatile SingularAttribute<MyGoodItem, Integer> contactinfoContactinfoid_;


    public static volatile SetAttribute<MyGoodItem, Tag> tagTagItemViaTagid;

}
