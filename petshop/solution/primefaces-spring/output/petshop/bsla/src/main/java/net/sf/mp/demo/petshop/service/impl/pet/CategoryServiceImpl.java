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

import net.sf.mp.demo.petshop.domain.pet.Category;
import net.sf.mp.demo.petshop.service.face.pet.CategoryService;
import net.sf.mp.demo.petshop.dao.face.pet.CategoryDao;
import net.sf.mp.demo.petshop.dao.face.pet.CategoryExtDao;

import org.springframework.transaction.annotation.Transactional;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: CategoryServiceImpl</p>
 *
 * <p>Description: Service layer Interface 
 * It offers coarse grain methods over the fine grain DAO layer:
 * It performs input and business validation.
 * </p>
 *
 */

@Service ("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    @Qualifier("categoryDao")	
	CategoryDao categoryDao;
    @Autowired
    @Qualifier("categoryExtDao")	
	CategoryExtDao categoryExtDao;
	
    /**
     * Inserts a Category entity Category 
     * @param Category category
     */
    public void create (Category category) {
		categoryDao.insertCategory(category);
	}

    /**
     * Updates a Category entity 
     * @param Category category
     */
    public Category update(Category category) {
        return categoryDao.updateCategory(category);
    }

    public Category findById (java.lang.String categoryid) {
        return categoryDao.loadCategory(categoryid);
    }

	public Category findById (Category c) {
		return categoryDao.loadCategory(c.getCategoryid());
	}

    public Category load (java.lang.String categoryid) {
        Category entity = findById(categoryid);
        return entity;
    }
	
	public void delete (Category category) {
	    Category var = findById (category.getCategoryid());
		categoryDao.deleteCategory (var);
	}	

     /**
     * Saves a Category entity 
     * @param Category category
     */
    public void save(Category category){
		categoryDao.saveCategory (category);
	}
	
	public List<Category> findAll (Category category) {
		List<Category> list = categoryDao.searchPrototypeCategory (category);
        return convert(list);
	}
	
	public List<Category> findAny (Category category) {
		List<Category> list = categoryDao.searchPrototypeAnyCategory (category);
        return convert(list);
	}

    public List<Category> find (Category category, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        List<Category> list = categoryDao.find (category, matchType, operandType, caseSensitivenessType);
        return convert(list);
    }
	
    private List<Category> convert(List<Category> list) {
        List<Category> ret = new ArrayList<Category>();   
         for (Category a : list) {
            Category b = a.clone();
            ret.add(b);
        }
        return ret;
    }
    
//	@Override
	public void find(QueryData<Category> data) {
		categoryExtDao.find (data);
	}
	
    /**
     * return a list of Category entities 
     */
    //@Cachable
    public List<Category> getList () {
        Category sortMask = new Category();
        sortMask.setDescription(new String());
        return categoryExtDao.getList(sortMask, QuerySortOrder.ASC);
    }


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
