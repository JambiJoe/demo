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
	* - name      : JSFBeanManagerController
	* - file name : JSFBeanManagerController.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/

package net.sf.mp.demo.petshop.controller.pet;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.primefaces.event.ToggleEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import net.sf.minuteProject.model.data.criteria.EntityCriteria;
import net.sf.minuteProject.model.data.criteria.EntitySort;
import net.sf.minuteProject.model.data.criteria.QueryData;
import net.sf.minuteProject.model.data.criteria.constant.EntityMatchType;
import net.sf.minuteProject.model.data.criteria.constant.OperandType;
import net.sf.minuteProject.model.data.criteria.constant.QuerySortOrder;

import net.sf.mp.demo.petshop.bean.PetshopLookupDefaultingService;
import net.sf.mp.demo.petshop.service.face.PetshopModelService;
import net.sf.mp.demo.petshop.service.face.pet.TagService;
import net.sf.mp.demo.petshop.utils.JsfUtils;
import net.sf.mp.demo.petshop.utils.JsfSemanticReferenceUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import net.sf.mp.demo.petshop.domain.pet.Tag;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
/**
 *
 * <p>Title: TagController</p>
 *
 * <p>Description: JSF controller TagController </p>
 *
 */
@ManagedBean (name="tagController")
@ViewScoped
public class TagController implements java.io.Serializable{

	@ManagedProperty("#{tagService}")
    TagService tagService;
	
	private LazyDataModel<Tag> lazyModel;

	@ManagedProperty("#{petshopLookupDefaultingService}")
    PetshopLookupDefaultingService petshopLookupDefaultingService;

	@ManagedProperty("#{petshopModelService}")
    PetshopModelService petshopModelService;
	
	
	@PostConstruct
	void initialiseSession() {
	    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
	
	private Tag tag = new Tag();
	private Tag selectedTag = new Tag();
	

	private List<MyGoodItem> myGoodItemTagItemViaItemid;
	private Boolean refreshMyGoodItemTagItemViaItemid=true;
	//used as property listener
	public void setRefreshMyGoodItemTagItemViaItemid(Boolean b) {
		refreshMyGoodItemTagItemViaItemid=true;
    }
	private MyGoodItem myGoodItemTagItemViaItemid_;
    public void setMyGoodItemTagItemViaItemidToAdd (MyGoodItem element) {
		this.myGoodItemTagItemViaItemid_ = element;
	}
    public List<MyGoodItem> getMyGoodItemTagItemViaItemid () {
		if (myGoodItemTagItemViaItemid==null || refreshMyGoodItemTagItemViaItemid) {
			populatemyGoodItemTagItemViaItemid ();
		}
        return myGoodItemTagItemViaItemid;
    }

	private List<MyGoodItem> getAllMyGoodItemTagItemViaItemid () {
	    return petshopModelService.getList(new MyGoodItem());
	}
	
	private void populatemyGoodItemTagItemViaItemid () {
		if (selectedTag.getTagid()!=null) {
		    refreshMyGoodItemTagItemViaItemid = false;
    		Tag mask = new Tag();
	        mask.setTagid(selectedTag.getTagid());
    		mask = petshopModelService.getManyToManyList(mask, "myGoodItemTagItemViaItemid");
    		myGoodItemTagItemViaItemid = new ArrayList(mask.getMyGoodItemTagItemViaItemid());
		}
	}

    private DualListModel<MyGoodItem> myGoodItemTagItemViaItemidDualListModel;

	public void setMyGoodItemTagItemViaItemidDualListModel(DualListModel<MyGoodItem> list){
		this.myGoodItemTagItemViaItemidDualListModel = list;
	}
			
    public DualListModel<MyGoodItem> getMyGoodItemTagItemViaItemidDualListModel(){
		populatemyGoodItemTagItemViaItemid();
		List<MyGoodItem> affected = (myGoodItemTagItemViaItemid==null)?new ArrayList<MyGoodItem>():myGoodItemTagItemViaItemid; 
        List<MyGoodItem> available = getAllMyGoodItemTagItemViaItemid();
		available.removeAll(affected);
		myGoodItemTagItemViaItemidDualListModel = new DualListModel<MyGoodItem>(available, affected);
        return myGoodItemTagItemViaItemidDualListModel;
    }
	
	public void onMyGoodItemTagItemViaItemidTransfer(TransferEvent event){
		//do nothing
	}
	
	public void addMyGoodItemTagItemViaItemid() {
		//TODO to improve in next release (transactional+algo)
	    for (MyGoodItem item : myGoodItemTagItemViaItemidDualListModel.getSource()) {
		   tagService.removeMyGoodItemTagItemViaItemid(selectedTag, item);
		}
		for (MyGoodItem item : myGoodItemTagItemViaItemidDualListModel.getTarget()) {
		   tagService.addMyGoodItemTagItemViaItemid(selectedTag, item);
		}
		populatemyGoodItemTagItemViaItemid ();		
	}

	private void populateFirst () {
		populatemyGoodItemTagItemViaItemid ();
	}

    public List<Tag> findAll () {
		return tagService.findAll(tag);
	}

	public Tag findById (java.lang.Integer tagid) {
		return tagService.findById(tagid);
	}

	public void delete (Tag tag) {
		tagService.delete (tag);
	}
	
	public String delete () {
		tagService.delete (selectedTag);
        return "success";
	}
	

    public LazyDataModel<Tag> getLazyModel() {
    	if (lazyModel == null) {
    		lazyModel = new LazyDataModel<Tag>() {

			     @Override
			     public List<Tag> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
                	QuerySortOrder order = QuerySortOrder.ASC;
                	if (sortOrder == SortOrder.DESCENDING) {
            			order = QuerySortOrder.DESC;     
            		}
					Tag esMask = new Tag();
					esMask.mask(sortField);
                	EntitySort<Tag> es = new EntitySort<Tag>(esMask, order);

            		Tag ecMask = new Tag();
            		ecMask.maskString(filters);
            		EntityCriteria<Tag> ec = new EntityCriteria<Tag>(ecMask, EntityMatchType.ALL, OperandType.CONTAINS, false);

            		QueryData<Tag> data = new QueryData<Tag>(first, pageSize, es, ec);
            		tagService.find(data);
            		List<Tag> list = data.getResult();
            		int count = data.getTotalResultCount().intValue();
            		this.setRowCount(count);
                    return list;
				}
				
				@Override
				public void setRowIndex(final int rowIndex)
				{
		            if (rowIndex == -1 || getPageSize() == 0)
		            {
		               super.setRowIndex(-1);
		            }
		            else
		            {
		               super.setRowIndex(rowIndex % getPageSize());
		            }
				 }

                @Override
				//Strange why is the getRowData input a string and not an object
                public Tag getRowData(String rowKey) {
					return serviceLoad(rowKey);
                }

                @Override
                public Object getRowKey(Tag element) {
                    if (element.getTagid()==null)
                        return null;
                    return element.getTagid().toString();
                }
			};
    	}
    	return lazyModel;
    }

    public void reloadSelected() {
    	selectedTag = tagService.load(selectedTag.getTagid());
    }

    public Tag serviceLoad(String rowKey) {
	   if (rowKey == null || "null".equals(rowKey)) return new Tag();
        return tagService.load(Integer.valueOf(rowKey));
    }

    public void recoverItemRowToggle(ToggleEvent toggleEvent) {
        selectedTag = (Tag) toggleEvent.getData();
    } 

    public void createOrUpdateListener(ActionEvent event) {
		createOrUpdateListener();
	}
	
    public void createOrUpdateListener() {
    	try {
	    	if (selectedTag.getTagid() == null) {
	    		tagService.create(selectedTag);
	            JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityCreated"), JsfSemanticReferenceUtils.getSemanticReference(selectedTag));
	    	} else {
	    		tagService.update(selectedTag);
	            JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityUpdated"), JsfSemanticReferenceUtils.getSemanticReference(selectedTag));
	    	}
	    	selectedTag = new Tag();
    	} catch (Exception e) {
			System.out.println(">>>>> selectedTag = "+selectedTag);
			e.printStackTrace();
			JsfUtils.addErrorMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("PersistenceErrorOccured"), JsfSemanticReferenceUtils.getSemanticReference(selectedTag));
        }
    }

    public void clearListener(ActionEvent event) {
    	selectedTag = new Tag();
    	UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
	    UIComponent component = viewRoot.findComponent("form2");  
	    JsfUtils.clearComponentHierarchy(component);
    }
	
    public void deleteListener(ActionEvent event) {
        try {
            if (selectedTag.getTagid() != null) {
                tagService.delete(selectedTag);
                JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityDeleted"), JsfSemanticReferenceUtils.getSemanticReference(selectedTag));
            }
			else {
			   System.out.println("no resources found");
			}
	       	selectedTag = new Tag();
        } catch (Exception e) {
			e.printStackTrace();
			JsfUtils.addErrorMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("PersistenceErrorOccured"));
        }
    }
	


	public String create () {
		tagService.create(tag);
		return "success";
	}
	
	public Tag update () {
		return tagService.update (tag);
	}
	
	public Tag getTag() {
	    return tag;
	}
	
	public void setTag (Tag tag) {
	    this.tag = tag;
	}	
	
	public Tag getSelectedTag() {
        if (selectedTag==null) selectedTag = new Tag();
	    return selectedTag;
	}
	
	public void resetSelectedTag () {
	    selectedTag = new Tag();
	}		
	
	public void setSelectedTag (Tag tag) {
	    this.selectedTag = tag;
	}		

	//TODO put in upperclass
    public String displayInput () {
		return "success";
	}	
    public void onRowSelect(SelectEvent event) {
        selectedTag = (Tag) event.getObject();
		populateFirst ();
    }

    public void onTabChange(TabChangeEvent event) {
	    String tabId = event.getTab().getId();

	    rendermyGoodItemTagItemViaItemid = false; // reset
	    if ("child-myGoodItemTagItemViaItemid".equals(tabId)) {
		    rendermyGoodItemTagItemViaItemid = true;
			populatemyGoodItemTagItemViaItemid ();
		}
    }


	//rendering of tab

    private boolean rendermyGoodItemTagItemViaItemid = false;

	public boolean isRenderMyGoodItemTagItemViaItemid() {
		return rendermyGoodItemTagItemViaItemid;
	}
	// rendering of add button 
    private boolean isRenderAddMyGoodItemTagItemViaItemid = false;

	public boolean isRenderAddMyGoodItemTagItemViaItemid() {
		return isRenderAddMyGoodItemTagItemViaItemid;
	}	
	
	public void affectAndDesactivateRenderMyGoodItemTagItemViaItemid() {
		tagService.addMyGoodItemTagItemViaItemid(selectedTag, myGoodItemTagItemViaItemid_);
		populatemyGoodItemTagItemViaItemid ();
	}
	
	public void disaffectAndDesactivateRenderMyGoodItemTagItemViaItemid() {
		tagService.removeMyGoodItemTagItemViaItemid(selectedTag, myGoodItemTagItemViaItemid_);
		populatemyGoodItemTagItemViaItemid ();
	}
	
	public void activateRenderAddMyGoodItemTagItemViaItemid() {
		isRenderAddMyGoodItemTagItemViaItemid = true;
	}
	public void desactivateRenderAddMyGoodItemTagItemViaItemid() {
		isRenderAddMyGoodItemTagItemViaItemid = false;
	}
	// rendering of dialog button 
    private boolean isRenderDialogMyGoodItemTagItemViaItemid = false;

	public boolean isRenderDialogMyGoodItemTagItemViaItemid() {
		return isRenderDialogMyGoodItemTagItemViaItemid;
	}	
	public void activateRenderDialogMyGoodItemTagItemViaItemid() {
		isRenderDialogMyGoodItemTagItemViaItemid=true;
	}
	public void desactivateRenderDialogMyGoodItemTagItemViaItemid() {
		isRenderDialogMyGoodItemTagItemViaItemid=false;
	}

    public TagService getTagService () {
        return tagService;
    }

    public void setTagService (TagService tagService) {
        this.tagService = tagService;
    }

    public PetshopLookupDefaultingService getPetshopLookupDefaultingService () {
        return petshopLookupDefaultingService;
    }

    public void setPetshopLookupDefaultingService (PetshopLookupDefaultingService petshopLookupDefaultingService) {
        this.petshopLookupDefaultingService = petshopLookupDefaultingService;
    }

    public PetshopModelService getPetshopModelService () {
        return petshopModelService;
    }

    public void setPetshopModelService (PetshopModelService petshopModelService) {
        this.petshopModelService = petshopModelService;
    }



}