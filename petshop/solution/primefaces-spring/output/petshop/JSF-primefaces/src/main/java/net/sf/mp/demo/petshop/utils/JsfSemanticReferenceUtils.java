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
	* - name      : JSFSemanticReferenceUtils
	* - file name : JSFSemanticReferenceUtils.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/
package net.sf.mp.demo.petshop.utils;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.apache.commons.lang.StringUtils;

import net.sf.mp.demo.petshop.domain.pet.Address;
import net.sf.mp.demo.petshop.domain.pet.Category;
import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;
import net.sf.mp.demo.petshop.domain.pet.Tag;
import net.sf.mp.demo.petshop.domain.pet.Ziplocation;
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;

@ManagedBean (name="jsfSemanticReferenceUtils")
@ApplicationScoped
public class JsfSemanticReferenceUtils {

   public static String getStrippedSemanticReferenceAddress (Address element, int length) {
      return StringUtils.substring(getSemanticReference(element),0,length);
   }
   public static String getSemanticReferenceAddress (Address element) {
      return getSemanticReference(element);
   }
   public static String getSemanticReference (Address element) {
      if (element==null) return "";
	  StringBuffer sb = new StringBuffer();
      if (element.getStreet1()!=null)
         sb.append (element.getStreet1() +" " ); 
      if (element.getStreet2()!=null)
         sb.append (element.getStreet2() +" " ); 
      if (element.getCity()!=null)
         sb.append (element.getCity() +" " ); 
      if (element.getState()!=null)
         sb.append (element.getState() +" " ); 
      if (element.getZip()!=null)
         sb.append (element.getZip() +" " ); 
      if (element.getLatitude()!=null)
         sb.append (element.getLatitude() +" " ); 
      if (element.getLongitude()!=null)
         sb.append (element.getLongitude() +" " ); 
      return sb.toString();
   }

   public static String getStrippedSemanticReferenceCategory (Category element, int length) {
      return StringUtils.substring(getSemanticReference(element),0,length);
   }
   public static String getSemanticReferenceCategory (Category element) {
      return getSemanticReference(element);
   }
   public static String getSemanticReference (Category element) {
      if (element==null) return "";
	  StringBuffer sb = new StringBuffer();
      if (element.getCategoryid()!=null)
         sb.append (element.getCategoryid() +" " ); 
      if (element.getName()!=null)
         sb.append (element.getName() +" " ); 
      if (element.getDescription()!=null)
         sb.append (element.getDescription() +" " ); 
      if (element.getImageurl()!=null)
         sb.append (element.getImageurl() +" " ); 
      return sb.toString();
   }

   public static String getStrippedSemanticReferenceSellercontactinfo (Sellercontactinfo element, int length) {
      return StringUtils.substring(getSemanticReference(element),0,length);
   }
   public static String getSemanticReferenceSellercontactinfo (Sellercontactinfo element) {
      return getSemanticReference(element);
   }
   public static String getSemanticReference (Sellercontactinfo element) {
      if (element==null) return "";
	  StringBuffer sb = new StringBuffer();
      if (element.getLastname()!=null)
         sb.append (element.getLastname() +" " ); 
      if (element.getFirstname()!=null)
         sb.append (element.getFirstname() +" " ); 
      if (element.getEmail()!=null)
         sb.append (element.getEmail() +" " ); 
      return sb.toString();
   }

   public static String getStrippedSemanticReferenceTag (Tag element, int length) {
      return StringUtils.substring(getSemanticReference(element),0,length);
   }
   public static String getSemanticReferenceTag (Tag element) {
      return getSemanticReference(element);
   }
   public static String getSemanticReference (Tag element) {
      if (element==null) return "";
	  StringBuffer sb = new StringBuffer();
      if (element.getTag()!=null)
         sb.append (element.getTag() +" " ); 
      if (element.getRefcount()!=null)
         sb.append (element.getRefcount() +" " ); 
      return sb.toString();
   }

   public static String getStrippedSemanticReferenceZiplocation (Ziplocation element, int length) {
      return StringUtils.substring(getSemanticReference(element),0,length);
   }
   public static String getSemanticReferenceZiplocation (Ziplocation element) {
      return getSemanticReference(element);
   }
   public static String getSemanticReference (Ziplocation element) {
      if (element==null) return "";
	  StringBuffer sb = new StringBuffer();
      if (element.getCity()!=null)
         sb.append (element.getCity() +" " ); 
      if (element.getState()!=null)
         sb.append (element.getState() +" " ); 
      return sb.toString();
   }

   public static String getStrippedSemanticReferenceMyGoodItem (MyGoodItem element, int length) {
      return StringUtils.substring(getSemanticReference(element),0,length);
   }
   public static String getSemanticReferenceMyGoodItem (MyGoodItem element) {
      return getSemanticReference(element);
   }
   public static String getSemanticReference (MyGoodItem element) {
      if (element==null) return "";
	  StringBuffer sb = new StringBuffer();
      if (element.getName()!=null)
         sb.append (element.getName() +" - "); 
      if (element.getDescription()!=null)
         sb.append (element.getDescription() +" - "); 
      if (element.getImageurl()!=null)
         sb.append (element.getImageurl() +" - "); 
      if (element.getImagethumburl()!=null)
         sb.append (element.getImagethumburl() +" - "); 
      if (element.getPrice()!=null)
         sb.append (element.getPrice() +" - "); 
      if (element.getTotalscore()!=null)
         sb.append (element.getTotalscore() +" - "); 
      if (element.getNumberofvotes()!=null)
         sb.append (element.getNumberofvotes() +" - "); 
      if (element.getDisabled()!=null)
         sb.append (element.getDisabled() +" - "); 
      return sb.toString();
   }

   public static String getStrippedSemanticReferenceMyGoodProduct (MyGoodProduct element, int length) {
      return StringUtils.substring(getSemanticReference(element),0,length);
   }
   public static String getSemanticReferenceMyGoodProduct (MyGoodProduct element) {
      return getSemanticReference(element);
   }
   public static String getSemanticReference (MyGoodProduct element) {
      if (element==null) return "";
	  StringBuffer sb = new StringBuffer();
      if (element.getProductid()!=null)
         sb.append (element.getProductid() +" " ); 
      if (element.getName()!=null)
         sb.append (element.getName() +" - "); 
      if (element.getDescription()!=null)
         sb.append (element.getDescription() +" - "); 
      if (element.getImageurl()!=null)
         sb.append (element.getImageurl() +" - "); 
      return sb.toString();
   }


}