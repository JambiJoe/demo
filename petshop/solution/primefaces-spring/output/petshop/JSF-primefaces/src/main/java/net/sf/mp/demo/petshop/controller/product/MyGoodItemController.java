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

package net.sf.mp.demo.petshop.controller.product;


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
import net.sf.mp.demo.petshop.service.face.product.MyGoodItemService;
import net.sf.mp.demo.petshop.utils.JsfUtils;
import net.sf.mp.demo.petshop.utils.JsfSemanticReferenceUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.mp.demo.petshop.domain.pet.Address;
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;
import net.sf.mp.demo.petshop.domain.pet.Tag;
/**
 *
 * <p>Title: MyGoodItemController</p>
 *
 * <p>Description: JSF controller MyGoodItemController </p>
 *
 */
@ManagedBean (name="myGoodItemController")
@ViewScoped
public class MyGoodItemController implements java.io.Serializable{

	@ManagedProperty("#{myGoodItemService}")
    MyGoodItemService myGoodItemService;
	
	private LazyDataModel<MyGoodItem> lazyModel;

	@ManagedProperty("#{petshopLookupDefaultingService}")
    PetshopLookupDefaultingService petshopLookupDefaultingService;

	@ManagedProperty("#{petshopModelService}")
    PetshopModelService petshopModelService;
	
	
	@PostConstruct
	void initialiseSession() {
	    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
	
	private MyGoodItem myGoodItem = new MyGoodItem();
	private MyGoodItem selectedMyGoodItem = new MyGoodItem();
	

	private List<Tag> tagTagItemViaTagid;
	private Boolean refreshTagTagItemViaTagid=true;
	//used as property listener
	public void setRefreshTagTagItemViaTagid(Boolean b) {
		refreshTagTagItemViaTagid=true;
    }
	private Tag tagTagItemViaTagid_;
    public void setTagTagItemViaTagidToAdd (Tag element) {
		this.tagTagItemViaTagid_ = element;
	}
    public List<Tag> getTagTagItemViaTagid () {
		if (tagTagItemViaTagid==null || refreshTagTagItemViaTagid) {
			populatetagTagItemViaTagid ();
		}
        return tagTagItemViaTagid;
    }

	private List<Tag> getAllTagTagItemViaTagid () {
	    return petshopModelService.getList(new Tag());
	}
	
	private void populatetagTagItemViaTagid () {
		if (selectedMyGoodItem.getItemid()!=null) {
		    refreshTagTagItemViaTagid = false;
    		MyGoodItem mask = new MyGoodItem();
	        mask.setItemid(selectedMyGoodItem.getItemid());
    		mask = petshopModelService.getManyToManyList(mask, "tagTagItemViaTagid");
    		tagTagItemViaTagid = new ArrayList(mask.getTagTagItemViaTagid());
		}
	}

    private DualListModel<Tag> tagTagItemViaTagidDualListModel;

	public void setTagTagItemViaTagidDualListModel(DualListModel<Tag> list){
		this.tagTagItemViaTagidDualListModel = list;
	}
			
    public DualListModel<Tag> getTagTagItemViaTagidDualListModel(){
		populatetagTagItemViaTagid();
		List<Tag> affected = (tagTagItemViaTagid==null)?new ArrayList<Tag>():tagTagItemViaTagid; 
        List<Tag> available = getAllTagTagItemViaTagid();
		available.removeAll(affected);
		tagTagItemViaTagidDualListModel = new DualListModel<Tag>(available, affected);
        return tagTagItemViaTagidDualListModel;
    }
	
	public void onTagTagItemViaTagidTransfer(TransferEvent event){
		//do nothing
	}
	
	public void addTagTagItemViaTagid() {
		//TODO to improve in next release (transactional+algo)
	    for (Tag item : tagTagItemViaTagidDualListModel.getSource()) {
		   myGoodItemService.removeTagTagItemViaTagid(selectedMyGoodItem, item);
		}
		for (Tag item : tagTagItemViaTagidDualListModel.getTarget()) {
		   myGoodItemService.addTagTagItemViaTagid(selectedMyGoodItem, item);
		}
		populatetagTagItemViaTagid ();		
	}

	private void populateFirst () {
		populatetagTagItemViaTagid ();
	}

    public List<MyGoodItem> findAll () {
		return myGoodItemService.findAll(myGoodItem);
	}

	public MyGoodItem findById (java.lang.Integer itemid) {
		return myGoodItemService.findById(itemid);
	}

	public void delete (MyGoodItem myGoodItem) {
		myGoodItemService.delete (myGoodItem);
	}
	
	public String delete () {
		myGoodItemService.delete (selectedMyGoodItem);
        return "success";
	}
	

    public LazyDataModel<MyGoodItem> getLazyModel() {
    	if (lazyModel == null) {
    		lazyModel = new LazyDataModel<MyGoodItem>() {

			     @Override
			     public List<MyGoodItem> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
                	QuerySortOrder order = QuerySortOrder.ASC;
                	if (sortOrder == SortOrder.DESCENDING) {
            			order = QuerySortOrder.DESC;     
            		}
					MyGoodItem esMask = new MyGoodItem();
					esMask.mask(sortField);
                	EntitySort<MyGoodItem> es = new EntitySort<MyGoodItem>(esMask, order);

            		MyGoodItem ecMask = new MyGoodItem();
            		ecMask.maskString(filters);
            		EntityCriteria<MyGoodItem> ec = new EntityCriteria<MyGoodItem>(ecMask, EntityMatchType.ALL, OperandType.CONTAINS, false);

            		QueryData<MyGoodItem> data = new QueryData<MyGoodItem>(first, pageSize, es, ec);
            		myGoodItemService.find(data);
            		List<MyGoodItem> list = data.getResult();
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
                public MyGoodItem getRowData(String rowKey) {
					return serviceLoad(rowKey);
                }

                @Override
                public Object getRowKey(MyGoodItem element) {
                    if (element.getItemid()==null)
                        return null;
                    return element.getItemid().toString();
                }
			};
    	}
    	return lazyModel;
    }

    public void reloadSelected() {
    	selectedMyGoodItem = myGoodItemService.load(selectedMyGoodItem.getItemid());
    }

    public MyGoodItem serviceLoad(String rowKey) {
	   if (rowKey == null || "null".equals(rowKey)) return new MyGoodItem();
        return myGoodItemService.load(Integer.valueOf(rowKey));
    }

    public void recoverItemRowToggle(ToggleEvent toggleEvent) {
        selectedMyGoodItem = (MyGoodItem) toggleEvent.getData();
    } 

    public void createOrUpdateListener(ActionEvent event) {
		createOrUpdateListener();
	}
	
    public void createOrUpdateListener() {
    	try {
	    	if (selectedMyGoodItem.getItemid() == null) {
	    		myGoodItemService.create(selectedMyGoodItem);
	            JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityCreated"), JsfSemanticReferenceUtils.getSemanticReference(selectedMyGoodItem));
	    	} else {
	    		myGoodItemService.update(selectedMyGoodItem);
	            JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityUpdated"), JsfSemanticReferenceUtils.getSemanticReference(selectedMyGoodItem));
	    	}
	    	selectedMyGoodItem = new MyGoodItem();
    	} catch (Exception e) {
			System.out.println(">>>>> selectedMyGoodItem = "+selectedMyGoodItem);
			e.printStackTrace();
			JsfUtils.addErrorMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("PersistenceErrorOccured"), JsfSemanticReferenceUtils.getSemanticReference(selectedMyGoodItem));
        }
    }

    public void clearListener(ActionEvent event) {
    	selectedMyGoodItem = new MyGoodItem();
    	UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
	    UIComponent component = viewRoot.findComponent("form2");  
	    JsfUtils.clearComponentHierarchy(component);
    }
	
    public void deleteListener(ActionEvent event) {
        try {
            if (selectedMyGoodItem.getItemid() != null) {
                myGoodItemService.delete(selectedMyGoodItem);
                JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityDeleted"), JsfSemanticReferenceUtils.getSemanticReference(selectedMyGoodItem));
            }
			else {
			   System.out.println("no resources found");
			}
	       	selectedMyGoodItem = new MyGoodItem();
        } catch (Exception e) {
			e.printStackTrace();
			JsfUtils.addErrorMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("PersistenceErrorOccured"));
        }
    }
	
    public List<Address> getAddressAddressids() {
        return petshopLookupDefaultingService.getAddresss();
    }

    private boolean renderAddressAddressid = false;
    
    public void desactivateRenderAddressAddressid () {
     	renderAddressAddressid = false;
    }
    
    public void activateRenderAddressAddressid () {
    	renderAddressAddressid = true;
    }

	public boolean isRenderAddressAddressid() {
		return renderAddressAddressid;
	}

    public List<MyGoodProduct> getThisIsMyProducts() {
        return petshopLookupDefaultingService.getMyGoodProducts();
    }

    private boolean renderThisIsMyProduct = false;
    
    public void desactivateRenderThisIsMyProduct () {
     	renderThisIsMyProduct = false;
    }
    
    public void activateRenderThisIsMyProduct () {
    	renderThisIsMyProduct = true;
    }

	public boolean isRenderThisIsMyProduct() {
		return renderThisIsMyProduct;
	}

    public List<Sellercontactinfo> getContactinfoContactinfoids() {
        return petshopLookupDefaultingService.getSellercontactinfos();
    }

    private boolean renderContactinfoContactinfoid = false;
    
    public void desactivateRenderContactinfoContactinfoid () {
     	renderContactinfoContactinfoid = false;
    }
    
    public void activateRenderContactinfoContactinfoid () {
    	renderContactinfoContactinfoid = true;
    }

	public boolean isRenderContactinfoContactinfoid() {
		return renderContactinfoContactinfoid;
	}



	public String create () {
		myGoodItemService.create(myGoodItem);
		return "success";
	}
	
	public MyGoodItem update () {
		return myGoodItemService.update (myGoodItem);
	}
	
	public MyGoodItem getMyGoodItem() {
	    return myGoodItem;
	}
	
	public void setMyGoodItem (MyGoodItem myGoodItem) {
	    this.myGoodItem = myGoodItem;
	}	
	
	public MyGoodItem getSelectedMyGoodItem() {
        if (selectedMyGoodItem==null) selectedMyGoodItem = new MyGoodItem();
	    return selectedMyGoodItem;
	}
	
	public void resetSelectedMyGoodItem () {
	    selectedMyGoodItem = new MyGoodItem();
	}		
	
	public void setSelectedMyGoodItem (MyGoodItem myGoodItem) {
	    this.selectedMyGoodItem = myGoodItem;
	}		

	//TODO put in upperclass
    public String displayInput () {
		return "success";
	}	
    public void onRowSelect(SelectEvent event) {
        selectedMyGoodItem = (MyGoodItem) event.getObject();
		populateFirst ();
    }

    public void onTabChange(TabChangeEvent event) {
	    String tabId = event.getTab().getId();

	    rendertagTagItemViaTagid = false; // reset
	    if ("child-tagTagItemViaTagid".equals(tabId)) {
		    rendertagTagItemViaTagid = true;
			populatetagTagItemViaTagid ();
		}
    }


	//rendering of tab

    private boolean rendertagTagItemViaTagid = false;

	public boolean isRenderTagTagItemViaTagid() {
		return rendertagTagItemViaTagid;
	}
	// rendering of add button 
    private boolean isRenderAddTagTagItemViaTagid = false;

	public boolean isRenderAddTagTagItemViaTagid() {
		return isRenderAddTagTagItemViaTagid;
	}	
	
	public void affectAndDesactivateRenderTagTagItemViaTagid() {
		myGoodItemService.addTagTagItemViaTagid(selectedMyGoodItem, tagTagItemViaTagid_);
		populatetagTagItemViaTagid ();
	}
	
	public void disaffectAndDesactivateRenderTagTagItemViaTagid() {
		myGoodItemService.removeTagTagItemViaTagid(selectedMyGoodItem, tagTagItemViaTagid_);
		populatetagTagItemViaTagid ();
	}
	
	public void activateRenderAddTagTagItemViaTagid() {
		isRenderAddTagTagItemViaTagid = true;
	}
	public void desactivateRenderAddTagTagItemViaTagid() {
		isRenderAddTagTagItemViaTagid = false;
	}
	// rendering of dialog button 
    private boolean isRenderDialogTagTagItemViaTagid = false;

	public boolean isRenderDialogTagTagItemViaTagid() {
		return isRenderDialogTagTagItemViaTagid;
	}	
	public void activateRenderDialogTagTagItemViaTagid() {
		isRenderDialogTagTagItemViaTagid=true;
	}
	public void desactivateRenderDialogTagTagItemViaTagid() {
		isRenderDialogTagTagItemViaTagid=false;
	}

    public MyGoodItemService getMyGoodItemService () {
        return myGoodItemService;
    }

    public void setMyGoodItemService (MyGoodItemService myGoodItemService) {
        this.myGoodItemService = myGoodItemService;
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