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
	* - name      : JSFBeanManagerConverter
	* - file name : JSFBeanManagerConverter.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/
package net.sf.mp.demo.petshop.converter.pet;



import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;


import org.springframework.stereotype.*;

import net.sf.mp.demo.petshop.bean.PetshopLookupDefaultingService;
import net.sf.mp.demo.petshop.service.face.PetshopModelService;
import net.sf.mp.demo.petshop.service.face.pet.AddressService;
import net.sf.mp.demo.petshop.utils.JsfUtils;
import net.sf.mp.demo.petshop.utils.JsfSemanticReferenceUtils;

import net.sf.mp.demo.petshop.domain.pet.Address;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
/**
 *
 * <p>Title: AddressConverter</p>
 *
 * <p>Description: JSF converter AddressConverter </p>
 *
 */
@FacesConverter(value="addressConverter")
@Component ("addressConverter")
public class AddressConverter implements Converter {


    @Override
    public Object getAsObject(FacesContext context, UIComponent ui, String value) {
        Address address = new Address();
	    address.setAddressid(java.lang.Integer.valueOf(value));
    	return address;
    }

	@Override
    public String getAsString(FacesContext context, UIComponent ui, Object object) {
		if (object==null)
            return "";
		return ((Address) object).getAddressid().toString();
    }
	

}