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
import net.sf.mp.demo.petshop.service.face.pet.AddressService;
import net.sf.mp.demo.petshop.utils.JsfUtils;
import net.sf.mp.demo.petshop.utils.JsfSemanticReferenceUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import net.sf.mp.demo.petshop.domain.pet.Address;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
/**
 *
 * <p>Title: AddressController</p>
 *
 * <p>Description: JSF controller AddressController </p>
 *
 */
@ManagedBean (name="addressController")
@ViewScoped
public class AddressController implements java.io.Serializable{

	@ManagedProperty("#{addressService}")
    AddressService addressService;
	
	private LazyDataModel<Address> lazyModel;

	@ManagedProperty("#{petshopLookupDefaultingService}")
    PetshopLookupDefaultingService petshopLookupDefaultingService;

	@ManagedProperty("#{petshopModelService}")
    PetshopModelService petshopModelService;
	
	
	@PostConstruct
	void initialiseSession() {
	    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
	
	private Address address = new Address();
	private Address selectedAddress = new Address();
	
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
		if (selectedAddress.getAddressid()!=null) {
		    refreshMyGoodItems = false;
    		MyGoodItem mask = new MyGoodItem();
			mask.setAddressAddressid_(selectedAddress.getAddressid());
    		myGoodItems = petshopModelService.getList(mask);
		}
	}
	


	private void populateFirst () {
		populatemyGoodItems ();
	}

    public List<Address> findAll () {
		return addressService.findAll(address);
	}

	public Address findById (java.lang.Integer addressid) {
		return addressService.findById(addressid);
	}

	public void delete (Address address) {
		addressService.delete (address);
	}
	
	public String delete () {
		addressService.delete (selectedAddress);
        return "success";
	}
	

    public LazyDataModel<Address> getLazyModel() {
    	if (lazyModel == null) {
    		lazyModel = new LazyDataModel<Address>() {

			     @Override
			     public List<Address> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
                	QuerySortOrder order = QuerySortOrder.ASC;
                	if (sortOrder == SortOrder.DESCENDING) {
            			order = QuerySortOrder.DESC;     
            		}
					Address esMask = new Address();
					esMask.mask(sortField);
                	EntitySort<Address> es = new EntitySort<Address>(esMask, order);

            		Address ecMask = new Address();
            		ecMask.maskString(filters);
            		EntityCriteria<Address> ec = new EntityCriteria<Address>(ecMask, EntityMatchType.ALL, OperandType.CONTAINS, false);

            		QueryData<Address> data = new QueryData<Address>(first, pageSize, es, ec);
            		addressService.find(data);
            		List<Address> list = data.getResult();
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
                public Address getRowData(String rowKey) {
					return serviceLoad(rowKey);
                }

                @Override
                public Object getRowKey(Address element) {
                    if (element.getAddressid()==null)
                        return null;
                    return element.getAddressid().toString();
                }
			};
    	}
    	return lazyModel;
    }

    public void reloadSelected() {
    	selectedAddress = addressService.load(selectedAddress.getAddressid());
    }

    public Address serviceLoad(String rowKey) {
	   if (rowKey == null || "null".equals(rowKey)) return new Address();
        return addressService.load(Integer.valueOf(rowKey));
    }

    public void recoverItemRowToggle(ToggleEvent toggleEvent) {
        selectedAddress = (Address) toggleEvent.getData();
    } 

    public void createOrUpdateListener(ActionEvent event) {
		createOrUpdateListener();
	}
	
    public void createOrUpdateListener() {
    	try {
	    	if (selectedAddress.getAddressid() == null) {
	    		addressService.create(selectedAddress);
	            JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityCreated"), JsfSemanticReferenceUtils.getSemanticReference(selectedAddress));
	    	} else {
	    		addressService.update(selectedAddress);
	            JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityUpdated"), JsfSemanticReferenceUtils.getSemanticReference(selectedAddress));
	    	}
	    	selectedAddress = new Address();
    	} catch (Exception e) {
			System.out.println(">>>>> selectedAddress = "+selectedAddress);
			e.printStackTrace();
			JsfUtils.addErrorMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("PersistenceErrorOccured"), JsfSemanticReferenceUtils.getSemanticReference(selectedAddress));
        }
    }

    public void clearListener(ActionEvent event) {
    	selectedAddress = new Address();
    	UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
	    UIComponent component = viewRoot.findComponent("form2");  
	    JsfUtils.clearComponentHierarchy(component);
    }
	
    public void deleteListener(ActionEvent event) {
        try {
            if (selectedAddress.getAddressid() != null) {
                addressService.delete(selectedAddress);
                JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityDeleted"), JsfSemanticReferenceUtils.getSemanticReference(selectedAddress));
            }
			else {
			   System.out.println("no resources found");
			}
	       	selectedAddress = new Address();
        } catch (Exception e) {
			e.printStackTrace();
			JsfUtils.addErrorMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("PersistenceErrorOccured"));
        }
    }
	


	public String create () {
		addressService.create(address);
		return "success";
	}
	
	public Address update () {
		return addressService.update (address);
	}
	
	public Address getAddress() {
	    return address;
	}
	
	public void setAddress (Address address) {
	    this.address = address;
	}	
	
	public Address getSelectedAddress() {
        if (selectedAddress==null) selectedAddress = new Address();
	    return selectedAddress;
	}
	
	public void resetSelectedAddress () {
	    selectedAddress = new Address();
	}		
	
	public void setSelectedAddress (Address address) {
	    this.selectedAddress = address;
	}		

	//TODO put in upperclass
    public String displayInput () {
		return "success";
	}	
    public void onRowSelect(SelectEvent event) {
        selectedAddress = (Address) event.getObject();
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


    public AddressService getAddressService () {
        return addressService;
    }

    public void setAddressService (AddressService addressService) {
        this.addressService = addressService;
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