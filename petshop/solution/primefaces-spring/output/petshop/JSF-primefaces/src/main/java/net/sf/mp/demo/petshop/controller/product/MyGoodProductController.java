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
import net.sf.mp.demo.petshop.service.face.product.MyGoodProductService;
import net.sf.mp.demo.petshop.utils.JsfUtils;
import net.sf.mp.demo.petshop.utils.JsfSemanticReferenceUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.mp.demo.petshop.domain.pet.Category;
/**
 *
 * <p>Title: MyGoodProductController</p>
 *
 * <p>Description: JSF controller MyGoodProductController </p>
 *
 */
@ManagedBean (name="myGoodProductController")
@ViewScoped
public class MyGoodProductController implements java.io.Serializable{

	@ManagedProperty("#{myGoodProductService}")
    MyGoodProductService myGoodProductService;
	
	private LazyDataModel<MyGoodProduct> lazyModel;

	@ManagedProperty("#{petshopLookupDefaultingService}")
    PetshopLookupDefaultingService petshopLookupDefaultingService;

	@ManagedProperty("#{petshopModelService}")
    PetshopModelService petshopModelService;
	
	
	@PostConstruct
	void initialiseSession() {
	    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
	
	private MyGoodProduct myGoodProduct = new MyGoodProduct();
	private MyGoodProduct selectedMyGoodProduct = new MyGoodProduct();
	
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
		if (selectedMyGoodProduct.getProductid()!=null) {
		    refreshMyGoodItems = false;
    		MyGoodItem mask = new MyGoodItem();
			mask.setThisIsMyProduct_(selectedMyGoodProduct.getProductid());
    		myGoodItems = petshopModelService.getList(mask);
		}
	}
	


	private void populateFirst () {
		populatemyGoodItems ();
	}

    public List<MyGoodProduct> findAll () {
		return myGoodProductService.findAll(myGoodProduct);
	}

	public MyGoodProduct findById (java.lang.String productid) {
		return myGoodProductService.findById(productid);
	}

	public void delete (MyGoodProduct myGoodProduct) {
		myGoodProductService.delete (myGoodProduct);
	}
	
	public String delete () {
		myGoodProductService.delete (selectedMyGoodProduct);
        return "success";
	}
	

    public LazyDataModel<MyGoodProduct> getLazyModel() {
    	if (lazyModel == null) {
    		lazyModel = new LazyDataModel<MyGoodProduct>() {

			     @Override
			     public List<MyGoodProduct> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
                	QuerySortOrder order = QuerySortOrder.ASC;
                	if (sortOrder == SortOrder.DESCENDING) {
            			order = QuerySortOrder.DESC;     
            		}
					MyGoodProduct esMask = new MyGoodProduct();
					esMask.mask(sortField);
                	EntitySort<MyGoodProduct> es = new EntitySort<MyGoodProduct>(esMask, order);

            		MyGoodProduct ecMask = new MyGoodProduct();
            		ecMask.maskString(filters);
            		EntityCriteria<MyGoodProduct> ec = new EntityCriteria<MyGoodProduct>(ecMask, EntityMatchType.ALL, OperandType.CONTAINS, false);

            		QueryData<MyGoodProduct> data = new QueryData<MyGoodProduct>(first, pageSize, es, ec);
            		myGoodProductService.find(data);
            		List<MyGoodProduct> list = data.getResult();
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
                public MyGoodProduct getRowData(String rowKey) {
					return serviceLoad(rowKey);
                }

                @Override
                public Object getRowKey(MyGoodProduct element) {
                    return element.getProductid();
                }
			};
    	}
    	return lazyModel;
    }

    public void reloadSelected() {
    	selectedMyGoodProduct = myGoodProductService.load(selectedMyGoodProduct.getProductid());
    }

    public MyGoodProduct serviceLoad(String rowKey) {
	   if (rowKey == null || "null".equals(rowKey)) return new MyGoodProduct();
        return myGoodProductService.load(rowKey);
    }

    public void recoverItemRowToggle(ToggleEvent toggleEvent) {
        selectedMyGoodProduct = (MyGoodProduct) toggleEvent.getData();
    } 

    public void createOrUpdateListener(ActionEvent event) {
		createOrUpdateListener();
	}
	
    public void createOrUpdateListener() {
    	try {
	    	if (selectedMyGoodProduct.getProductid() == null) {
	    		myGoodProductService.create(selectedMyGoodProduct);
	            JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityCreated"), JsfSemanticReferenceUtils.getSemanticReference(selectedMyGoodProduct));
	    	} else {
	    		myGoodProductService.update(selectedMyGoodProduct);
	            JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityUpdated"), JsfSemanticReferenceUtils.getSemanticReference(selectedMyGoodProduct));
	    	}
	    	selectedMyGoodProduct = new MyGoodProduct();
    	} catch (Exception e) {
			System.out.println(">>>>> selectedMyGoodProduct = "+selectedMyGoodProduct);
			e.printStackTrace();
			JsfUtils.addErrorMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("PersistenceErrorOccured"), JsfSemanticReferenceUtils.getSemanticReference(selectedMyGoodProduct));
        }
    }

    public void clearListener(ActionEvent event) {
    	selectedMyGoodProduct = new MyGoodProduct();
    	UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
	    UIComponent component = viewRoot.findComponent("form2");  
	    JsfUtils.clearComponentHierarchy(component);
    }
	
    public void deleteListener(ActionEvent event) {
        try {
            if (selectedMyGoodProduct.getProductid() != null) {
                myGoodProductService.delete(selectedMyGoodProduct);
                JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityDeleted"), JsfSemanticReferenceUtils.getSemanticReference(selectedMyGoodProduct));
            }
			else {
			   System.out.println("no resources found");
			}
	       	selectedMyGoodProduct = new MyGoodProduct();
        } catch (Exception e) {
			e.printStackTrace();
			JsfUtils.addErrorMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("PersistenceErrorOccured"));
        }
    }
	
    public List<Category> getCategoryids() {
        return petshopLookupDefaultingService.getCategorys();
    }

    private boolean renderCategoryid = false;
    
    public void desactivateRenderCategoryid () {
     	renderCategoryid = false;
    }
    
    public void activateRenderCategoryid () {
    	renderCategoryid = true;
    }

	public boolean isRenderCategoryid() {
		return renderCategoryid;
	}



	public String create () {
		myGoodProductService.create(myGoodProduct);
		return "success";
	}
	
	public MyGoodProduct update () {
		return myGoodProductService.update (myGoodProduct);
	}
	
	public MyGoodProduct getMyGoodProduct() {
	    return myGoodProduct;
	}
	
	public void setMyGoodProduct (MyGoodProduct myGoodProduct) {
	    this.myGoodProduct = myGoodProduct;
	}	
	
	public MyGoodProduct getSelectedMyGoodProduct() {
        if (selectedMyGoodProduct==null) selectedMyGoodProduct = new MyGoodProduct();
	    return selectedMyGoodProduct;
	}
	
	public void resetSelectedMyGoodProduct () {
	    selectedMyGoodProduct = new MyGoodProduct();
	}		
	
	public void setSelectedMyGoodProduct (MyGoodProduct myGoodProduct) {
	    this.selectedMyGoodProduct = myGoodProduct;
	}		

	//TODO put in upperclass
    public String displayInput () {
		return "success";
	}	
    public void onRowSelect(SelectEvent event) {
        selectedMyGoodProduct = (MyGoodProduct) event.getObject();
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


    public MyGoodProductService getMyGoodProductService () {
        return myGoodProductService;
    }

    public void setMyGoodProductService (MyGoodProductService myGoodProductService) {
        this.myGoodProductService = myGoodProductService;
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