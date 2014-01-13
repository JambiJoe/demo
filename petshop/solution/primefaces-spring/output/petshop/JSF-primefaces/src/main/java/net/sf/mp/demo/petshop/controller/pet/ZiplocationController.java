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
import net.sf.mp.demo.petshop.service.face.pet.ZiplocationService;
import net.sf.mp.demo.petshop.utils.JsfUtils;
import net.sf.mp.demo.petshop.utils.JsfSemanticReferenceUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import net.sf.mp.demo.petshop.domain.pet.Ziplocation;
/**
 *
 * <p>Title: ZiplocationController</p>
 *
 * <p>Description: JSF controller ZiplocationController </p>
 *
 */
@ManagedBean (name="ziplocationController")
@ViewScoped
public class ZiplocationController implements java.io.Serializable{

	@ManagedProperty("#{ziplocationService}")
    ZiplocationService ziplocationService;
	
	private LazyDataModel<Ziplocation> lazyModel;

	@ManagedProperty("#{petshopLookupDefaultingService}")
    PetshopLookupDefaultingService petshopLookupDefaultingService;

	@ManagedProperty("#{petshopModelService}")
    PetshopModelService petshopModelService;
	
	
	@PostConstruct
	void initialiseSession() {
	    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
	
	private Ziplocation ziplocation = new Ziplocation();
	private Ziplocation selectedZiplocation = new Ziplocation();
	



    public List<Ziplocation> findAll () {
		return ziplocationService.findAll(ziplocation);
	}

	public Ziplocation findById (java.lang.Integer zipcode) {
		return ziplocationService.findById(zipcode);
	}

	public void delete (Ziplocation ziplocation) {
		ziplocationService.delete (ziplocation);
	}
	
	public String delete () {
		ziplocationService.delete (selectedZiplocation);
        return "success";
	}
	

    public LazyDataModel<Ziplocation> getLazyModel() {
    	if (lazyModel == null) {
    		lazyModel = new LazyDataModel<Ziplocation>() {

			     @Override
			     public List<Ziplocation> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
                	QuerySortOrder order = QuerySortOrder.ASC;
                	if (sortOrder == SortOrder.DESCENDING) {
            			order = QuerySortOrder.DESC;     
            		}
					Ziplocation esMask = new Ziplocation();
					esMask.mask(sortField);
                	EntitySort<Ziplocation> es = new EntitySort<Ziplocation>(esMask, order);

            		Ziplocation ecMask = new Ziplocation();
            		ecMask.maskString(filters);
            		EntityCriteria<Ziplocation> ec = new EntityCriteria<Ziplocation>(ecMask, EntityMatchType.ALL, OperandType.CONTAINS, false);

            		QueryData<Ziplocation> data = new QueryData<Ziplocation>(first, pageSize, es, ec);
            		ziplocationService.find(data);
            		List<Ziplocation> list = data.getResult();
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
                public Ziplocation getRowData(String rowKey) {
					return serviceLoad(rowKey);
                }

                @Override
                public Object getRowKey(Ziplocation element) {
                    if (element.getZipcode()==null)
                        return null;
                    return element.getZipcode().toString();
                }
			};
    	}
    	return lazyModel;
    }

    public void reloadSelected() {
    	selectedZiplocation = ziplocationService.load(selectedZiplocation.getZipcode());
    }

    public Ziplocation serviceLoad(String rowKey) {
	   if (rowKey == null || "null".equals(rowKey)) return new Ziplocation();
        return ziplocationService.load(Integer.valueOf(rowKey));
    }

    public void recoverItemRowToggle(ToggleEvent toggleEvent) {
        selectedZiplocation = (Ziplocation) toggleEvent.getData();
    } 

    public void createOrUpdateListener(ActionEvent event) {
		createOrUpdateListener();
	}
	
    public void createOrUpdateListener() {
    	try {
	    	if (selectedZiplocation.getZipcode() == null) {
	    		ziplocationService.create(selectedZiplocation);
	            JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityCreated"), JsfSemanticReferenceUtils.getSemanticReference(selectedZiplocation));
	    	} else {
	    		ziplocationService.update(selectedZiplocation);
	            JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityUpdated"), JsfSemanticReferenceUtils.getSemanticReference(selectedZiplocation));
	    	}
	    	selectedZiplocation = new Ziplocation();
    	} catch (Exception e) {
			System.out.println(">>>>> selectedZiplocation = "+selectedZiplocation);
			e.printStackTrace();
			JsfUtils.addErrorMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("PersistenceErrorOccured"), JsfSemanticReferenceUtils.getSemanticReference(selectedZiplocation));
        }
    }

    public void clearListener(ActionEvent event) {
    	selectedZiplocation = new Ziplocation();
    	UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
	    UIComponent component = viewRoot.findComponent("form2");  
	    JsfUtils.clearComponentHierarchy(component);
    }
	
    public void deleteListener(ActionEvent event) {
        try {
            if (selectedZiplocation.getZipcode() != null) {
                ziplocationService.delete(selectedZiplocation);
                JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityDeleted"), JsfSemanticReferenceUtils.getSemanticReference(selectedZiplocation));
            }
			else {
			   System.out.println("no resources found");
			}
	       	selectedZiplocation = new Ziplocation();
        } catch (Exception e) {
			e.printStackTrace();
			JsfUtils.addErrorMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("PersistenceErrorOccured"));
        }
    }
	


	public String create () {
		ziplocationService.create(ziplocation);
		return "success";
	}
	
	public Ziplocation update () {
		return ziplocationService.update (ziplocation);
	}
	
	public Ziplocation getZiplocation() {
	    return ziplocation;
	}
	
	public void setZiplocation (Ziplocation ziplocation) {
	    this.ziplocation = ziplocation;
	}	
	
	public Ziplocation getSelectedZiplocation() {
        if (selectedZiplocation==null) selectedZiplocation = new Ziplocation();
	    return selectedZiplocation;
	}
	
	public void resetSelectedZiplocation () {
	    selectedZiplocation = new Ziplocation();
	}		
	
	public void setSelectedZiplocation (Ziplocation ziplocation) {
	    this.selectedZiplocation = ziplocation;
	}		

	//TODO put in upperclass
    public String displayInput () {
		return "success";
	}	
    public void onRowSelect(SelectEvent event) {
        selectedZiplocation = (Ziplocation) event.getObject();
    }

    public void onTabChange(TabChangeEvent event) {
	    String tabId = event.getTab().getId();
    }



    public ZiplocationService getZiplocationService () {
        return ziplocationService;
    }

    public void setZiplocationService (ZiplocationService ziplocationService) {
        this.ziplocationService = ziplocationService;
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