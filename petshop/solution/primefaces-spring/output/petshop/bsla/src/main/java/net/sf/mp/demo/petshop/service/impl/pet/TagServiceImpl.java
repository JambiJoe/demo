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

import net.sf.mp.demo.petshop.domain.pet.Tag;
import net.sf.mp.demo.petshop.service.face.pet.TagService;
import net.sf.mp.demo.petshop.dao.face.pet.TagDao;
import net.sf.mp.demo.petshop.dao.face.pet.TagExtDao;

import org.springframework.transaction.annotation.Transactional;

import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

/**
 *
 * <p>Title: TagServiceImpl</p>
 *
 * <p>Description: Service layer Interface 
 * It offers coarse grain methods over the fine grain DAO layer:
 * It performs input and business validation.
 * </p>
 *
 */

@Service ("tagService")
@Transactional
public class TagServiceImpl implements TagService {
    @Autowired
    @Qualifier("tagDao")	
	TagDao tagDao;
    @Autowired
    @Qualifier("tagExtDao")	
	TagExtDao tagExtDao;
	
    /**
     * Inserts a Tag entity Tag 
     * @param Tag tag
     */
    public void create (Tag tag) {
		tagDao.insertTag(tag);
	}

    /**
     * Updates a Tag entity 
     * @param Tag tag
     */
    public Tag update(Tag tag) {
        return tagDao.updateTag(tag);
    }

    public Tag findById (java.lang.Integer tagid) {
        return tagDao.loadTag(tagid);
    }

	public Tag findById (Tag c) {
		return tagDao.loadTag(c.getTagid());
	}

    public Tag load (java.lang.Integer tagid) {
        Tag entity = findById(tagid);
        for (net.sf.mp.demo.petshop.domain.product.MyGoodItem element : entity.getMyGoodItemTagItemViaItemid()){
           element.toString();//eager load
        }
        return entity;
    }
	
	public void delete (Tag tag) {
	    Tag var = findById (tag.getTagid());
		tagDao.deleteTag (var);
	}	

     /**
     * Saves a Tag entity 
     * @param Tag tag
     */
    public void save(Tag tag){
		tagDao.saveTag (tag);
	}
	
	public List<Tag> findAll (Tag tag) {
		List<Tag> list = tagDao.searchPrototypeTag (tag);
        return convert(list);
	}
	
	public List<Tag> findAny (Tag tag) {
		List<Tag> list = tagDao.searchPrototypeAnyTag (tag);
        return convert(list);
	}

    public List<Tag> find (Tag tag, EntityMatchType matchType, OperandType operandType, Boolean caseSensitivenessType) {
        List<Tag> list = tagDao.find (tag, matchType, operandType, caseSensitivenessType);
        return convert(list);
    }
	
    private List<Tag> convert(List<Tag> list) {
        List<Tag> ret = new ArrayList<Tag>();   
         for (Tag a : list) {
            Tag b = a.clone();
            ret.add(b);
        }
        return ret;
    }
    
//	@Override
	public void find(QueryData<Tag> data) {
		tagExtDao.find (data);
	}
	
    /**
     * return a list of Tag entities 
     */
    //@Cachable
    public List<Tag> getList () {
        Tag sortMask = new Tag();
        sortMask.setTag(new String());
        return tagExtDao.getList(sortMask, QuerySortOrder.ASC);
    }

	public void addMyGoodItemTagItemViaItemid(Tag c, MyGoodItem ... element) {
		Tag cc = findById (c);
        cc.getMyGoodItemTagItemViaItemid().addAll(Arrays.asList(element));
        save(cc);
	}
	
	public void removeMyGoodItemTagItemViaItemid(Tag c, MyGoodItem ... element) {
		Tag cc = findById (c);
        for (Iterator<MyGoodItem> iterator = cc.getMyGoodItemTagItemViaItemid().iterator(); iterator.hasNext();) {
            MyGoodItem e = iterator.next();
			for (MyGoodItem ele : element) {
                if (e.equals(ele))
                    iterator.remove();
            }
        }
        save(cc);
	}
	

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
