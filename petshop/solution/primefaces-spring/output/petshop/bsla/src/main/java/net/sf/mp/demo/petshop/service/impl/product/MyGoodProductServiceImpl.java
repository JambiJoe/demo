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

import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import net.sf.mp.demo.petshop.service.face.product.MyGoodProductService;
import net.sf.mp.demo.petshop.dao.face.product.MyGoodProductDao;
import net.sf.mp.demo.petshop.dao.face.product.MyGoodProductExtDao;

import org.springframework.transaction.annotation.Transactional;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.mp.demo.petshop.domain.pet.Category;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: MyGoodProductServiceImpl</p>
 *
 * <p>Description: Service layer Interface 
 * It offers coarse grain methods over the fine grain DAO layer:
 * It performs input and business validation.
 * </p>
 *
 */

@Service ("myGoodProductService")
@Transactional
public class MyGoodProductServiceImpl implements MyGoodProductService {
    @Autowired
    @Qualifier("myGoodProductDao")	
	MyGoodProductDao myGoodProductDao;
    @Autowired
    @Qualifier("myGoodProductExtDao")	
	MyGoodProductExtDao myGoodProductExtDao;
	
    /**
     * Inserts a MyGoodProduct entity MyGoodProduct 
     * @param MyGoodProduct mygoodproduct
     */
    public void create (MyGoodProduct mygoodproduct) {
		myGoodProductDao.insertMyGoodProduct(mygoodproduct);
	}

    /**
     * Updates a MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     */
    public MyGoodProduct update(MyGoodProduct mygoodproduct) {
        return myGoodProductDao.updateMyGoodProduct(mygoodproduct);
    }

    public MyGoodProduct findById (java.lang.String productid) {
        return myGoodProductDao.loadMyGoodProduct(productid);
    }

	public MyGoodProduct findById (MyGoodProduct c) {
		return myGoodProductDao.loadMyGoodProduct(c.getProductid());
	}

    public MyGoodProduct load (java.lang.String productid) {
        MyGoodProduct entity = findById(productid);
        if (entity.getCategoryid()!=null)
	       entity.getCategoryid().getCategoryid();//improve with partialparent loading
        return entity;
    }
	
	public void delete (MyGoodProduct mygoodproduct) {
	    MyGoodProduct var = findById (mygoodproduct.getProductid());
		myGoodProductDao.deleteMyGoodProduct (var);
	}	

     /**
     * Saves a MyGoodProduct entity 
     * @param MyGoodProduct mygoodproduct
     */
    public void save(MyGoodProduct mygoodproduct){
		myGoodProductDao.saveMyGoodProduct (mygoodproduct);
	}
	
	public List<MyGoodProduct> findAll (MyGoodProduct mygoodproduct) {
		List<MyGoodProduct> list = myGoodProductDao.searchPrototypeMyGoodProduct (mygoodproduct);
        return convert(list);
	}
	
	public List<MyGoodProduct> findAny (MyGoodProduct mygoodproduct) {
		List<MyGoodProduct> list = myGoodProductDao.searchPrototypeAnyMyGoodProduct (mygoodproduct);
        return convert(list);
	}

    public List<MyGoodProduct> find (MyGoodProduct mygoodproduct, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        List<MyGoodProduct> list = myGoodProductDao.find (mygoodproduct, matchType, operandType, caseSensitivenessType);
        return convert(list);
    }
	
    private List<MyGoodProduct> convert(List<MyGoodProduct> list) {
        List<MyGoodProduct> ret = new ArrayList<MyGoodProduct>();   
         for (MyGoodProduct a : list) {
            MyGoodProduct b = a.clone();
            ret.add(b);
        }
        return ret;
    }
    
//	@Override
	public void find(QueryData<MyGoodProduct> data) {
		myGoodProductExtDao.find (data);
	}
	
    /**
     * return a list of MyGoodProduct entities 
     */
    //@Cachable
    public List<MyGoodProduct> getList () {
        MyGoodProduct sortMask = new MyGoodProduct();
        sortMask.setProductid(new String());
        return myGoodProductExtDao.getList(sortMask, QuerySortOrder.ASC);
    }


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
