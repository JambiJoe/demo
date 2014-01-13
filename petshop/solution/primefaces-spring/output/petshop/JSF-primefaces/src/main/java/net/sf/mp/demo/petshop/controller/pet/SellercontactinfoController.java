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
import net.sf.mp.demo.petshop.service.face.pet.SellercontactinfoService;
import net.sf.mp.demo.petshop.utils.JsfUtils;
import net.sf.mp.demo.petshop.utils.JsfSemanticReferenceUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
/**
 *
 * <p>Title: SellercontactinfoController</p>
 *
 * <p>Description: JSF controller SellercontactinfoController </p>
 *
 */
@ManagedBean (name="sellercontactinfoController")
@ViewScoped
public class SellercontactinfoController implements java.io.Serializable{

	@ManagedProperty("#{sellercontactinfoService}")
    SellercontactinfoService sellercontactinfoService;
	
	private LazyDataModel<Sellercontactinfo> lazyModel;

	@ManagedProperty("#{petshopLookupDefaultingService}")
    PetshopLookupDefaultingService petshopLookupDefaultingService;

	@ManagedProperty("#{petshopModelService}")
    PetshopModelService petshopModelService;
	
	
	@PostConstruct
	void initialiseSession() {
	    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
	
	private Sellercontactinfo sellercontactinfo = new Sellercontactinfo();
	private Sellercontactinfo selectedSellercontactinfo = new Sellercontactinfo();
	
	private List<MyGoodItem> myGoodItems;
	private Boolean refreshMyGoodItems=true;
	//used as property listener
	public void setRefreshMyGoodItems(Boolean b) {
		refreshMyGoodItems=true;
    }
	
    public List<MyGoodItem> getMyGoodItems () {
		if (myGoodItems==null || refreshMyGoodItems) {
			populatemyGoodItems ();
		}
        return myGoodItems;
    }

	private void populatemyGoodItems () {
		if (selectedSellercontactinfo.getContactinfoid()!=null) {
		    refreshMyGoodItems = false;
    		MyGoodItem mask = new MyGoodItem();
			mask.setContactinfoContactinfoid_(selectedSellercontactinfo.getContactinfoid());
    		myGoodItems = petshopModelService.getList(mask);
		}
	}
	


	private void populateFirst () {
		populatemyGoodItems ();
	}

    public List<Sellercontactinfo> findAll () {
		return sellercontactinfoService.findAll(sellercontactinfo);
	}

	public Sellercontactinfo findById (java.lang.Integer contactinfoid) {
		return sellercontactinfoService.findById(contactinfoid);
	}

	public void delete (Sellercontactinfo sellercontactinfo) {
		sellercontactinfoService.delete (sellercontactinfo);
	}
	
	public String delete () {
		sellercontactinfoService.delete (selectedSellercontactinfo);
        return "success";
	}
	

    public LazyDataModel<Sellercontactinfo> getLazyModel() {
    	if (lazyModel == null) {
    		lazyModel = new LazyDataModel<Sellercontactinfo>() {

			     @Override
			     public List<Sellercontactinfo> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
                	QuerySortOrder order = QuerySortOrder.ASC;
                	if (sortOrder == SortOrder.DESCENDING) {
            			order = QuerySortOrder.DESC;     
            		}
					Sellercontactinfo esMask = new Sellercontactinfo();
					esMask.mask(sortField);
                	EntitySort<Sellercontactinfo> es = new EntitySort<Sellercontactinfo>(esMask, order);

            		Sellercontactinfo ecMask = new Sellercontactinfo();
            		ecMask.maskString(filters);
            		EntityCriteria<Sellercontactinfo> ec = new EntityCriteria<Sellercontactinfo>(ecMask, EntityMatchType.ALL, OperandType.CONTAINS, false);

            		QueryData<Sellercontactinfo> data = new QueryData<Sellercontactinfo>(first, pageSize, es, ec);
            		sellercontactinfoService.find(data);
            		List<Sellercontactinfo> list = data.getResult();
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
                public Sellercontactinfo getRowData(String rowKey) {
					return serviceLoad(rowKey);
                }

                @Override
                public Object getRowKey(Sellercontactinfo element) {
                    if (element.getContactinfoid()==null)
                        return null;
                    return element.getContactinfoid().toString();
                }
			};
    	}
    	return lazyModel;
    }

    public void reloadSelected() {
    	selectedSellercontactinfo = sellercontactinfoService.load(selectedSellercontactinfo.getContactinfoid());
    }

    public Sellercontactinfo serviceLoad(String rowKey) {
	   if (rowKey == null || "null".equals(rowKey)) return new Sellercontactinfo();
        return sellercontactinfoService.load(Integer.valueOf(rowKey));
    }

    public void recoverItemRowToggle(ToggleEvent toggleEvent) {
        selectedSellercontactinfo = (Sellercontactinfo) toggleEvent.getData();
    } 

    public void createOrUpdateListener(ActionEvent event) {
		createOrUpdateListener();
	}
	
    public void createOrUpdateListener() {
    	try {
	    	if (selectedSellercontactinfo.getContactinfoid() == null) {
	    		sellercontactinfoService.create(selectedSellercontactinfo);
	            JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityCreated"), JsfSemanticReferenceUtils.getSemanticReference(selectedSellercontactinfo));
	    	} else {
	    		sellercontactinfoService.update(selectedSellercontactinfo);
	            JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityUpdated"), JsfSemanticReferenceUtils.getSemanticReference(selectedSellercontactinfo));
	    	}
	    	selectedSellercontactinfo = new Sellercontactinfo();
    	} catch (Exception e) {
			System.out.println(">>>>> selectedSellercontactinfo = "+selectedSellercontactinfo);
			e.printStackTrace();
			JsfUtils.addErrorMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("PersistenceErrorOccured"), JsfSemanticReferenceUtils.getSemanticReference(selectedSellercontactinfo));
        }
    }

    public void clearListener(ActionEvent event) {
    	selectedSellercontactinfo = new Sellercontactinfo();
    	UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
	    UIComponent component = viewRoot.findComponent("form2");  
	    JsfUtils.clearComponentHierarchy(component);
    }
	
    public void deleteListener(ActionEvent event) {
        try {
            if (selectedSellercontactinfo.getContactinfoid() != null) {
                sellercontactinfoService.delete(selectedSellercontactinfo);
                JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityDeleted"), JsfSemanticReferenceUtils.getSemanticReference(selectedSellercontactinfo));
            }
			else {
			   System.out.println("no resources found");
			}
	       	selectedSellercontactinfo = new Sellercontactinfo();
        } catch (Exception e) {
			e.printStackTrace();
			JsfUtils.addErrorMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("PersistenceErrorOccured"));
        }
    }
	


	public String create () {
		sellercontactinfoService.create(sellercontactinfo);
		return "success";
	}
	
	public Sellercontactinfo update () {
		return sellercontactinfoService.update (sellercontactinfo);
	}
	
	public Sellercontactinfo getSellercontactinfo() {
	    return sellercontactinfo;
	}
	
	public void setSellercontactinfo (Sellercontactinfo sellercontactinfo) {
	    this.sellercontactinfo = sellercontactinfo;
	}	
	
	public Sellercontactinfo getSelectedSellercontactinfo() {
        if (selectedSellercontactinfo==null) selectedSellercontactinfo = new Sellercontactinfo();
	    return selectedSellercontactinfo;
	}
	
	public void resetSelectedSellercontactinfo () {
	    selectedSellercontactinfo = new Sellercontactinfo();
	}		
	
	public void setSelectedSellercontactinfo (Sellercontactinfo sellercontactinfo) {
	    this.selectedSellercontactinfo = sellercontactinfo;
	}		

	//TODO put in upperclass
    public String displayInput () {
		return "success";
	}	
    public void onRowSelect(SelectEvent event) {
        selectedSellercontactinfo = (Sellercontactinfo) event.getObject();
		populateFirst ();
    }

    public void onTabChange(TabChangeEvent event) {
	    String tabId = event.getTab().getId();
	    rendermyGoodItems = false; // reset
	    if ("child-myGoodItems".equals(tabId)) {
		    rendermyGoodItems = true;
			populatemyGoodItems ();
		}

    }

	//myGoodItems
	//rendering of tab
    private boolean rendermyGoodItems = false;

	public boolean isRenderMyGoodItems() {
		return rendermyGoodItems;
	}
	// rendering of add button 
    private boolean isRenderAddMyGoodItems = false;

	public boolean isRenderAddMyGoodItems() {
		return isRenderAddMyGoodItems;
	}	
	public void activateRenderAddMyGoodItems() {
		isRenderAddMyGoodItems = true;
	}
	public void desactivateRenderAddMyGoodItems() {
		isRenderAddMyGoodItems = false;
	}
	// rendering of dialog button 
    private boolean isRenderDialogMyGoodItems = false;

	public boolean isRenderDialogMyGoodItems() {
		return isRenderDialogMyGoodItems;
	}	
	public void activateRenderDialogMyGoodItems() {
		isRenderDialogMyGoodItems=true;
	}
	public void desactivateRenderDialogMyGoodItems() {
		isRenderDialogMyGoodItems=false;
	}


    public SellercontactinfoService getSellercontactinfoService () {
        return sellercontactinfoService;
    }

    public void setSellercontactinfoService (SellercontactinfoService sellercontactinfoService) {
        this.sellercontactinfoService = sellercontactinfoService;
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