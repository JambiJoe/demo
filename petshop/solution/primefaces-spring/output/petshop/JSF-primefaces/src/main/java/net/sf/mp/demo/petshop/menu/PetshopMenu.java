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
	* - name      : JSFBeanManagerMenu
	* - file name : JSFPrimefacesMenuBean.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/
package net.sf.mp.demo.petshop.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.tabview.Tab;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.MenuModel;

import java.util.HashMap;
import java.util.Map;

@Service ("petshopMenu")
@Transactional
public class PetshopMenu {


     public static final String pet_INDEX = "0";
     public static final String address_ACCESS_URL = "/petshop/pet/AddressListLazy.xhtml"; 		
     public static final String category_ACCESS_URL = "/petshop/pet/CategoryListLazy.xhtml"; 		
     public static final String sellercontactinfo_ACCESS_URL = "/petshop/pet/SellercontactinfoListLazy.xhtml"; 		
     public static final String tag_ACCESS_URL = "/petshop/pet/TagListLazy.xhtml"; 		
     public static final String ziplocation_ACCESS_URL = "/petshop/pet/ZiplocationListLazy.xhtml"; 		
     public static final String product_INDEX = "1";
     public static final String item_ACCESS_URL = "/petshop/product/MyGoodItemListLazy.xhtml"; 		
     public static final String product_ACCESS_URL = "/petshop/product/MyGoodProductListLazy.xhtml"; 		

	 public static final String SDD_INDEX_ = "2";
     public static final String GET_ADDRESS_ABSTRACT_ACCESS_URL = "/petshop/sdd/statement/GetAddressAbstractChart.xhtml"; 
     public static final String GET_ADDRESS_SUMMARY_ACCESS_URL = "/petshop/sdd/statement/GetAddressSummaryChart.xhtml"; 
     public static final String GET_ADDRESSES_BY_CRITERIA_ACCESS_URL = "/petshop/sdd/statement/GetAddressesByCriteriaInput.xhtml"; 

    private MenuModel model;  
	private AccordionPanel accordion;
    private Map<String, MenuTuple> map;

    private class MenuTuple {
        private String packageIndex, url, entry, title;

        private MenuTuple(String url, String packageIndex, String entry, String title) {
            this.packageIndex = packageIndex;
            this.url = url;
            this.entry = entry;
            this.title = title;
        }

        private String getPackageIndex() {
            return packageIndex;
        }

        private String getUrl() {
            return url;
        }

        private String getEntry() {
            return entry;
        }

        private String getTitle() {
            return title;
        }
    }
  
	public PetshopMenu() { 
        if (map==null)
            populateMenuMap();
	}

    public void populateMenuMap () {
        map = new HashMap<String, MenuTuple>();
        map.put (address_ACCESS_URL, new MenuTuple(address_ACCESS_URL, pet_INDEX, "address","Address")); 		
        map.put (category_ACCESS_URL, new MenuTuple(category_ACCESS_URL, pet_INDEX, "category","Category")); 		
        map.put (sellercontactinfo_ACCESS_URL, new MenuTuple(sellercontactinfo_ACCESS_URL, pet_INDEX, "sellercontactinfo","Sellercontactinfo")); 		
        map.put (tag_ACCESS_URL, new MenuTuple(tag_ACCESS_URL, pet_INDEX, "tag","Tag")); 		
        map.put (ziplocation_ACCESS_URL, new MenuTuple(ziplocation_ACCESS_URL, pet_INDEX, "ziplocation","Ziplocation")); 		
        map.put (item_ACCESS_URL, new MenuTuple(item_ACCESS_URL, product_INDEX, "item","My good item")); 		
        map.put (product_ACCESS_URL, new MenuTuple(product_ACCESS_URL, product_INDEX, "product","My good product")); 		
        map.put (GET_ADDRESS_ABSTRACT_ACCESS_URL, new MenuTuple(GET_ADDRESS_ABSTRACT_ACCESS_URL, SDD_INDEX_, "GET_ADDRESS_ABSTRACT","Get address abstract"));
        map.put (GET_ADDRESS_SUMMARY_ACCESS_URL, new MenuTuple(GET_ADDRESS_SUMMARY_ACCESS_URL, SDD_INDEX_, "GET_ADDRESS_SUMMARY","Get address summary"));
        map.put (GET_ADDRESSES_BY_CRITERIA_ACCESS_URL, new MenuTuple(GET_ADDRESSES_BY_CRITERIA_ACCESS_URL, SDD_INDEX_, "GET_ADDRESSES_BY_CRITERIA","Get addresses by criteria"));
    }
	
    public void load() {  
        model = new DefaultMenuModel();  
        accordion = new AccordionPanel();
        DefaultSubMenu submenupet = new DefaultSubMenu();  
        submenupet.setLabel("Pet"); 	
		//
		Tab tabpet = new Tab();
		tabpet.setTitle("Pet");
		
        submenupet.addElement(getItem("Address", address_ACCESS_URL)); 
		tabpet.getChildren().add(getPanel(getTuple (address_ACCESS_URL)));
		
        submenupet.addElement(getItem("Category", category_ACCESS_URL)); 
		tabpet.getChildren().add(getPanel(getTuple (category_ACCESS_URL)));
		
        submenupet.addElement(getItem("Sellercontactinfo", sellercontactinfo_ACCESS_URL)); 
		tabpet.getChildren().add(getPanel(getTuple (sellercontactinfo_ACCESS_URL)));
		
        submenupet.addElement(getItem("Tag", tag_ACCESS_URL)); 
		tabpet.getChildren().add(getPanel(getTuple (tag_ACCESS_URL)));
		
        submenupet.addElement(getItem("Ziplocation", ziplocation_ACCESS_URL)); 
		tabpet.getChildren().add(getPanel(getTuple (ziplocation_ACCESS_URL)));
		
        model.addElement(submenupet);
		accordion.getChildren().add(tabpet) ;
        DefaultSubMenu submenuproduct = new DefaultSubMenu();  
        submenuproduct.setLabel("Product"); 	
		//
		Tab tabproduct = new Tab();
		tabproduct.setTitle("Product");
		
        submenuproduct.addElement(getItem("My good item", item_ACCESS_URL)); 
		tabproduct.getChildren().add(getPanel(getTuple (item_ACCESS_URL)));
		
        submenuproduct.addElement(getItem("My good product", product_ACCESS_URL)); 
		tabproduct.getChildren().add(getPanel(getTuple (product_ACCESS_URL)));
		
        model.addElement(submenuproduct);
		accordion.getChildren().add(tabproduct) ;

	    // SDD
        DefaultSubMenu sdd = new DefaultSubMenu();  
        sdd.setLabel("SDD");
        
		Tab sddTab = new Tab();
		sddTab.setTitle("SDD");
		
        DefaultMenuItem itemGetAddressAbstract = new DefaultMenuItem();  
        itemGetAddressAbstract.setValue("Get address abstract");
        itemGetAddressAbstract.setUrl("/petshop/sdd/statement/GetAddressAbstractChart.xhtml");
        sdd.addElement(itemGetAddressAbstract); 
		
		sddTab.getChildren().add(getPanel(getTuple (GET_ADDRESS_ABSTRACT_ACCESS_URL)));
      
        DefaultMenuItem itemGetAddressSummary = new DefaultMenuItem();  
        itemGetAddressSummary.setValue("Get address summary");
        itemGetAddressSummary.setUrl("/petshop/sdd/statement/GetAddressSummaryChart.xhtml");
        sdd.addElement(itemGetAddressSummary); 
		
		sddTab.getChildren().add(getPanel(getTuple (GET_ADDRESS_SUMMARY_ACCESS_URL)));
      
        DefaultMenuItem itemGetAddressesByCriteria = new DefaultMenuItem();  
        itemGetAddressesByCriteria.setValue("Get addresses by criteria");
        itemGetAddressesByCriteria.setUrl("/petshop/sdd/statement/GetAddressesByCriteriaInput.xhtml");
        sdd.addElement(itemGetAddressesByCriteria); 
		
		sddTab.getChildren().add(getPanel(getTuple (GET_ADDRESSES_BY_CRITERIA_ACCESS_URL)));
      
        accordion.getChildren().add(sddTab) ;
	    model.addElement(sdd);
    }  
      
    public MenuModel getModel() {  
	    if (model == null)
			load();
        return model;  
    }     
      
	public void activateMenu() {
		MenuTuple activeTuple = getCurrentTuple();
		if (activeTuple!=null) {
    	    accordion.setActiveIndex(activeTuple.getPackageIndex());
    		activateTab(activeTuple);
		}
	}
	
    public AccordionPanel getAccordion() {  
	    if (accordion == null)
			load();
		activateMenu();
        return accordion;  
    }     
	
    public void setAccordion(AccordionPanel accordion) {
        this.accordion = accordion;
    }

	
	private void activateTab (MenuTuple tuple) {
        UIComponent c = accordion.findComponent("menuTitle"+tuple.getEntry());
        Panel p = (Panel)c;
        p.setStyleClass(" ui-state-active ");
    }
	
	private Panel getPanel (MenuTuple tuple) {
        Panel panel = new Panel();
		panel.setId("menuTitle"+tuple.getEntry());
		//panel.setStyleClass(" ui-state-disactive ");
		HtmlOutputLink link = new HtmlOutputLink();
        link.setValue(getContextPath()+tuple.getUrl()); 
		HtmlOutputText h =new HtmlOutputText() ;
        h.setValue(tuple.getTitle());
        link.getChildren().add(h);
        panel.getChildren().add(link);
		return panel;
	}
	
	private DefaultMenuItem getItem (String title, String url)	{
	    DefaultMenuItem item = new DefaultMenuItem();  
        item.setValue(title);  
        item.setUrl(url); 
		return item;
    } 
				
    public MenuTuple getCurrentTuple() {
        return getTuple(getServletPath());
    }
	
    public MenuTuple getTuple(String url) {
        return map.get(url);
    }
	
    public String getRequestURL()
    {
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if(request instanceof HttpServletRequest)
        {
            return ((HttpServletRequest) request).getRequestURL().toString();
        }else
        {
            return "";
        }
    }
		
    public String getServletPath()
    {
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if(request instanceof HttpServletRequest)
        {
            return ((HttpServletRequest) request).getServletPath();
        }else
        {
            return "";
        }
    }
	
	
    public String getContextPath()
    {
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if(request instanceof HttpServletRequest)
        {
            HttpServletRequest req = (HttpServletRequest) request;
            return req.getContextPath();
        }else
        {
            return "";
        }
    }
	
}  