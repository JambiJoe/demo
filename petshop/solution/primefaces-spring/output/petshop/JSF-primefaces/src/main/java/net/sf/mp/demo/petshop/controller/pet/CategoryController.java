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
import net.sf.mp.demo.petshop.service.face.pet.CategoryService;
import net.sf.mp.demo.petshop.utils.JsfUtils;
import net.sf.mp.demo.petshop.utils.JsfSemanticReferenceUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import net.sf.mp.demo.petshop.domain.pet.Category;
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
/**
 *
 * <p>Title: CategoryController</p>
 *
 * <p>Description: JSF controller CategoryController </p>
 *
 */
@ManagedBean (name="categoryController")
@ViewScoped
public class CategoryController implements java.io.Serializable{

	@ManagedProperty("#{categoryService}")
    CategoryService categoryService;
	
	private LazyDataModel<Category> lazyModel;

	@ManagedProperty("#{petshopLookupDefaultingService}")
    PetshopLookupDefaultingService petshopLookupDefaultingService;

	@ManagedProperty("#{petshopModelService}")
    PetshopModelService petshopModelService;
	
	
	@PostConstruct
	void initialiseSession() {
	    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
	
	private Category category = new Category();
	private Category selectedCategory = new Category();
	
	private List<MyGoodProduct> myGoodProducts;
	private Boolean refreshMyGoodProducts=true;
	//used as property listener
	public void setRefreshMyGoodProducts(Boolean b) {
		refreshMyGoodProducts=true;
    }
	
    public List<MyGoodProduct> getMyGoodProducts () {
		if (myGoodProducts==null || refreshMyGoodProducts) {
			populatemyGoodProducts ();
		}
        return myGoodProducts;
    }

	private void populatemyGoodProducts () {
		if (selectedCategory.getCategoryid()!=null) {
		    refreshMyGoodProducts = false;
    		MyGoodProduct mask = new MyGoodProduct();
			mask.setCategoryid_(selectedCategory.getCategoryid());
    		myGoodProducts = petshopModelService.getList(mask);
		}
	}
	


	private void populateFirst () {
		populatemyGoodProducts ();
	}

    public List<Category> findAll () {
		return categoryService.findAll(category);
	}

	public Category findById (java.lang.String categoryid) {
		return categoryService.findById(categoryid);
	}

	public void delete (Category category) {
		categoryService.delete (category);
	}
	
	public String delete () {
		categoryService.delete (selectedCategory);
        return "success";
	}
	

    public LazyDataModel<Category> getLazyModel() {
    	if (lazyModel == null) {
    		lazyModel = new LazyDataModel<Category>() {

			     @Override
			     public List<Category> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
                	QuerySortOrder order = QuerySortOrder.ASC;
                	if (sortOrder == SortOrder.DESCENDING) {
            			order = QuerySortOrder.DESC;     
            		}
					Category esMask = new Category();
					esMask.mask(sortField);
                	EntitySort<Category> es = new EntitySort<Category>(esMask, order);

            		Category ecMask = new Category();
            		ecMask.maskString(filters);
            		EntityCriteria<Category> ec = new EntityCriteria<Category>(ecMask, EntityMatchType.ALL, OperandType.CONTAINS, false);

            		QueryData<Category> data = new QueryData<Category>(first, pageSize, es, ec);
            		categoryService.find(data);
            		List<Category> list = data.getResult();
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
                public Category getRowData(String rowKey) {
					return serviceLoad(rowKey);
                }

                @Override
                public Object getRowKey(Category element) {
                    return element.getCategoryid();
                }
			};
    	}
    	return lazyModel;
    }

    public void reloadSelected() {
    	selectedCategory = categoryService.load(selectedCategory.getCategoryid());
    }

    public Category serviceLoad(String rowKey) {
	   if (rowKey == null || "null".equals(rowKey)) return new Category();
        return categoryService.load(rowKey);
    }

    public void recoverItemRowToggle(ToggleEvent toggleEvent) {
        selectedCategory = (Category) toggleEvent.getData();
    } 

    public void createOrUpdateListener(ActionEvent event) {
		createOrUpdateListener();
	}
	
    public void createOrUpdateListener() {
    	try {
	    	if (selectedCategory.getCategoryid() == null) {
	    		categoryService.create(selectedCategory);
	            JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityCreated"), JsfSemanticReferenceUtils.getSemanticReference(selectedCategory));
	    	} else {
	    		categoryService.update(selectedCategory);
	            JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityUpdated"), JsfSemanticReferenceUtils.getSemanticReference(selectedCategory));
	    	}
	    	selectedCategory = new Category();
    	} catch (Exception e) {
			System.out.println(">>>>> selectedCategory = "+selectedCategory);
			e.printStackTrace();
			JsfUtils.addErrorMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("PersistenceErrorOccured"), JsfSemanticReferenceUtils.getSemanticReference(selectedCategory));
        }
    }

    public void clearListener(ActionEvent event) {
    	selectedCategory = new Category();
    	UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
	    UIComponent component = viewRoot.findComponent("form2");  
	    JsfUtils.clearComponentHierarchy(component);
    }
	
    public void deleteListener(ActionEvent event) {
        try {
            if (selectedCategory.getCategoryid() != null) {
                categoryService.delete(selectedCategory);
                JsfUtils.addSuccessMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("entityDeleted"), JsfSemanticReferenceUtils.getSemanticReference(selectedCategory));
            }
			else {
			   System.out.println("no resources found");
			}
	       	selectedCategory = new Category();
        } catch (Exception e) {
			e.printStackTrace();
			JsfUtils.addErrorMessage(ResourceBundle.getBundle("net.sf.mp.demo.petshop.i18n.petshop").getString("PersistenceErrorOccured"));
        }
    }
	


	public String create () {
		categoryService.create(category);
		return "success";
	}
	
	public Category update () {
		return categoryService.update (category);
	}
	
	public Category getCategory() {
	    return category;
	}
	
	public void setCategory (Category category) {
	    this.category = category;
	}	
	
	public Category getSelectedCategory() {
        if (selectedCategory==null) selectedCategory = new Category();
	    return selectedCategory;
	}
	
	public void resetSelectedCategory () {
	    selectedCategory = new Category();
	}		
	
	public void setSelectedCategory (Category category) {
	    this.selectedCategory = category;
	}		

	//TODO put in upperclass
    public String displayInput () {
		return "success";
	}	
    public void onRowSelect(SelectEvent event) {
        selectedCategory = (Category) event.getObject();
		populateFirst ();
    }

    public void onTabChange(TabChangeEvent event) {
	    String tabId = event.getTab().getId();
	    rendermyGoodProducts = false; // reset
	    if ("child-myGoodProducts".equals(tabId)) {
		    rendermyGoodProducts = true;
			populatemyGoodProducts ();
		}

    }

	//myGoodProducts
	//rendering of tab
    private boolean rendermyGoodProducts = false;

	public boolean isRenderMyGoodProducts() {
		return rendermyGoodProducts;
	}
	// rendering of add button 
    private boolean isRenderAddMyGoodProducts = false;

	public boolean isRenderAddMyGoodProducts() {
		return isRenderAddMyGoodProducts;
	}	
	public void activateRenderAddMyGoodProducts() {
		isRenderAddMyGoodProducts = true;
	}
	public void desactivateRenderAddMyGoodProducts() {
		isRenderAddMyGoodProducts = false;
	}
	// rendering of dialog button 
    private boolean isRenderDialogMyGoodProducts = false;

	public boolean isRenderDialogMyGoodProducts() {
		return isRenderDialogMyGoodProducts;
	}	
	public void activateRenderDialogMyGoodProducts() {
		isRenderDialogMyGoodProducts=true;
	}
	public void desactivateRenderDialogMyGoodProducts() {
		isRenderDialogMyGoodProducts=false;
	}


    public CategoryService getCategoryService () {
        return categoryService;
    }

    public void setCategoryService (CategoryService categoryService) {
        this.categoryService = categoryService;
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