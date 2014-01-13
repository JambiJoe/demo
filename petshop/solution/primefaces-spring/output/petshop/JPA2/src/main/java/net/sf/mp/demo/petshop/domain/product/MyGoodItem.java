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
	* - name      : DomainEntityJPA2Annotation
	* - file name : DomainEntityJPA2Annotation.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:20 CET
*/
package net.sf.mp.demo.petshop.domain.product;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import net.sf.minuteProject.architecture.bsla.domain.AbstractDomainObject;
import java.io.Serializable;
import javax.persistence.*;
import net.sf.mp.demo.petshop.domain.pet.Address;
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;
import net.sf.mp.demo.petshop.domain.pet.Sellercontactinfo;
import net.sf.mp.demo.petshop.domain.pet.Tag;

/**
 *
 * <p>Title: MyGoodItem</p>
 *
 * <p>Description: Domain Object describing a MyGoodItem entity</p>
 *
 */
@Entity (name="MyGoodItem")
@Table (name="item")
@NamedQueries({
	 @NamedQuery(name="MyGoodItem.findAll", query="SELECT mygooditem FROM MyGoodItem mygooditem")
	,@NamedQuery(name="MyGoodItem.findByName", query="SELECT mygooditem FROM MyGoodItem mygooditem WHERE mygooditem.name = :name")
	,@NamedQuery(name="MyGoodItem.findByNameContaining", query="SELECT mygooditem FROM MyGoodItem mygooditem WHERE mygooditem.name like :name")

	,@NamedQuery(name="MyGoodItem.findByDescription", query="SELECT mygooditem FROM MyGoodItem mygooditem WHERE mygooditem.description = :description")
	,@NamedQuery(name="MyGoodItem.findByDescriptionContaining", query="SELECT mygooditem FROM MyGoodItem mygooditem WHERE mygooditem.description like :description")

	,@NamedQuery(name="MyGoodItem.findByImageurl", query="SELECT mygooditem FROM MyGoodItem mygooditem WHERE mygooditem.imageurl = :imageurl")
	,@NamedQuery(name="MyGoodItem.findByImageurlContaining", query="SELECT mygooditem FROM MyGoodItem mygooditem WHERE mygooditem.imageurl like :imageurl")

	,@NamedQuery(name="MyGoodItem.findByImagethumburl", query="SELECT mygooditem FROM MyGoodItem mygooditem WHERE mygooditem.imagethumburl = :imagethumburl")
	,@NamedQuery(name="MyGoodItem.findByImagethumburlContaining", query="SELECT mygooditem FROM MyGoodItem mygooditem WHERE mygooditem.imagethumburl like :imagethumburl")

	,@NamedQuery(name="MyGoodItem.findByPrice", query="SELECT mygooditem FROM MyGoodItem mygooditem WHERE mygooditem.price = :price")

	,@NamedQuery(name="MyGoodItem.findByTotalscore", query="SELECT mygooditem FROM MyGoodItem mygooditem WHERE mygooditem.totalscore = :totalscore")

	,@NamedQuery(name="MyGoodItem.findByNumberofvotes", query="SELECT mygooditem FROM MyGoodItem mygooditem WHERE mygooditem.numberofvotes = :numberofvotes")

	,@NamedQuery(name="MyGoodItem.findByDisabled", query="SELECT mygooditem FROM MyGoodItem mygooditem WHERE mygooditem.disabled = :disabled")

})

public class MyGoodItem extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "MyGoodItem.findAll";
    public static final String FIND_BY_NAME = "MyGoodItem.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="MyGoodItem.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "MyGoodItem.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="MyGoodItem.findByDescriptionContaining";
    public static final String FIND_BY_IMAGEURL = "MyGoodItem.findByImageurl";
    public static final String FIND_BY_IMAGEURL_CONTAINING ="MyGoodItem.findByImageurlContaining";
    public static final String FIND_BY_IMAGETHUMBURL = "MyGoodItem.findByImagethumburl";
    public static final String FIND_BY_IMAGETHUMBURL_CONTAINING ="MyGoodItem.findByImagethumburlContaining";
    public static final String FIND_BY_PRICE = "MyGoodItem.findByPrice";
    public static final String FIND_BY_TOTALSCORE = "MyGoodItem.findByTotalscore";
    public static final String FIND_BY_NUMBEROFVOTES = "MyGoodItem.findByNumberofvotes";
    public static final String FIND_BY_DISABLED = "MyGoodItem.findByDisabled";
	
    @Id @Column(name="itemid" ) 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemid;

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name"  , length=30 , nullable=false , unique=false)
    private String name; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @description-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @description-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-description@
    @Column(name="description"  , length=500 , nullable=false , unique=false)
    private String description; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @imageurl-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @imageurl-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-imageurl@
    @Column(name="imageurl"  , length=55 , nullable=true , unique=false)
    private String imageurl; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @imagethumburl-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @imagethumburl-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-imagethumburl@
    @Column(name="imagethumburl"  , length=55 , nullable=true , unique=false)
    private String imagethumburl; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @price-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @price-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-price@
    @Column(name="price"   , nullable=false , unique=false)
    private java.math.BigDecimal price; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @totalscore-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @totalscore-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-totalscore@
    @Column(name="totalscore"   , nullable=false , unique=false)
    private Integer totalscore; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @numberofvotes-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @numberofvotes-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-numberofvotes@
    @Column(name="numberofvotes"   , nullable=false , unique=false)
    private Integer numberofvotes; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @disabled-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @disabled-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-disabled@
    @Column(name="disabled"   , nullable=false , unique=false)
    private Integer disabled; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="address_addressid", referencedColumnName = "addressid" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Address addressAddressid;  

    @Column(name="address_addressid"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer addressAddressid_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="productid", referencedColumnName = "productid" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private MyGoodProduct thisIsMyProduct;  

    @Column(name="productid" , length=10 , nullable=false , unique=true, insertable=false, updatable=false)
    private String thisIsMyProduct_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="contactinfo_contactinfoid", referencedColumnName = "contactinfoid" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Sellercontactinfo contactinfoContactinfoid;  

    @Column(name="contactinfo_contactinfoid"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer contactinfoContactinfoid_;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @m2m-tagTagItemViaTagid-item@
    @ManyToMany
    @JoinTable(name="TAG_ITEM", 
        joinColumns=@JoinColumn(name="itemid"), 
        inverseJoinColumns=@JoinColumn(name="tagid") 
    )
    private Set <Tag> tagTagItemViaTagid = new HashSet <Tag> ();

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public MyGoodItem() {
    }

	/**
	* All field constructor 
	*/
    public MyGoodItem(
       Integer itemid,
       String thisIsMyProduct,
       String name,
       String description,
       String imageurl,
       String imagethumburl,
       java.math.BigDecimal price,
       Integer addressAddressid,
       Integer contactinfoContactinfoid,
       Integer totalscore,
       Integer numberofvotes,
       Integer disabled) {
       //primary keys
       setItemid (itemid);
       //attributes
       setName (name);
       setDescription (description);
       setImageurl (imageurl);
       setImagethumburl (imagethumburl);
       setPrice (price);
       setTotalscore (totalscore);
       setNumberofvotes (numberofvotes);
       setDisabled (disabled);
       //parents
       this.addressAddressid = new Address();
       this.addressAddressid.setAddressid(addressAddressid); 
	   setAddressAddressid_ (addressAddressid);
       this.thisIsMyProduct = new MyGoodProduct();
       this.thisIsMyProduct.setProductid(thisIsMyProduct); 
	   setThisIsMyProduct_ (thisIsMyProduct);
       this.contactinfoContactinfoid = new Sellercontactinfo();
       this.contactinfoContactinfoid.setContactinfoid(contactinfoContactinfoid); 
	   setContactinfoContactinfoid_ (contactinfoContactinfoid);
    }

	public MyGoodItem flat() {
	   return new MyGoodItem(
          getItemid(),
          getThisIsMyProduct_(),
          getName(),
          getDescription(),
          getImageurl(),
          getImagethumburl(),
          getPrice(),
          getAddressAddressid_(),
          getContactinfoContactinfoid_(),
          getTotalscore(),
          getNumberofvotes(),
          getDisabled()
	   );
	}

    /**
    * display semanticReference with attribute inside class
    */
	public String display() {
	  StringBuffer sb = new StringBuffer();
      if (this.getName()!=null)
         sb.append (this.getName() +" - "); 
      if (this.getDescription()!=null)
         sb.append (this.getDescription() +" - "); 
      if (this.getImageurl()!=null)
         sb.append (this.getImageurl() +" - "); 
      if (this.getImagethumburl()!=null)
         sb.append (this.getImagethumburl() +" - "); 
      if (this.getPrice()!=null)
         sb.append (this.getPrice() +" - "); 
      if (this.getTotalscore()!=null)
         sb.append (this.getTotalscore() +" - "); 
      if (this.getNumberofvotes()!=null)
         sb.append (this.getNumberofvotes() +" - "); 
      if (this.getDisabled()!=null)
         sb.append (this.getDisabled() +" - "); 
      return sb.toString();
	}
	
    public String getDisplay() {
		return display();
	}
    /**
    * toString implementation
    */
	public String toString() {
		return toString(this);
	}

	public ToStringBuilder getToStringBuilder(Object object) {
	 	return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("itemid", itemid)
            .append("thisIsMyProduct_", thisIsMyProduct_)
            .append("name", name)
            .append("description", description)
            .append("imageurl", imageurl)
            .append("imagethumburl", imagethumburl)
            .append("price", price)
            .append("addressAddressid_", addressAddressid_)
            .append("contactinfoContactinfoid_", contactinfoContactinfoid_)
            .append("totalscore", totalscore)
            .append("numberofvotes", numberofvotes)
            .append("disabled", disabled)
	 	;
	} 
		
	public String toString(Object object) {
	 	return getToStringBuilder(object).toString();
	} 
	
	public String toStringWithParents() {
	    ToStringBuilder toStringBuilder = getToStringBuilder(this);
        if (addressAddressid!=null)
            toStringBuilder.append("addressAddressid", addressAddressid.toStringWithParents());
        if (thisIsMyProduct!=null)
            toStringBuilder.append("thisIsMyProduct", thisIsMyProduct.toStringWithParents());
        if (contactinfoContactinfoid!=null)
            toStringBuilder.append("contactinfoContactinfoid", contactinfoContactinfoid.toStringWithParents());
	 	return toStringBuilder.toString();	
	}
	/**
    * hashCode implementation
    */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(flat());
	}
	
	/**
    * equals implementation

	public boolean equals(Object object) {
		return super.toEquals(flat(), object);
	}
    */
	
	public boolean equals(Object object) {
		if (object == null) return false;	
		if (object == this) return true;
		if (!(object instanceof MyGoodItem)) return false;
		MyGoodItem mygooditem = (MyGoodItem) object;
		if (mygooditem.itemid==null || !mygooditem.itemid.equals(itemid)) return false;
		return true;
	}    

	public MyGoodItem clone() {
        MyGoodItem mygooditem = flat();
        if (getAddressAddressid()!=null) 
            mygooditem.setAddressAddressid (getAddressAddressid().clone());   
        if (getThisIsMyProduct()!=null) 
            mygooditem.setThisIsMyProduct (getThisIsMyProduct().clone());   
        if (getContactinfoContactinfoid()!=null) 
            mygooditem.setContactinfoContactinfoid (getContactinfoContactinfoid().clone());   
        return mygooditem;
	}
	
	public void copy (MyGoodItem mygooditem) {
		if (mygooditem!=null) {
			setItemid (mygooditem.getItemid());
			setThisIsMyProduct (mygooditem.getThisIsMyProduct());
			setName (mygooditem.getName());
			setDescription (mygooditem.getDescription());
			setImageurl (mygooditem.getImageurl());
			setImagethumburl (mygooditem.getImagethumburl());
			setPrice (mygooditem.getPrice());
			setAddressAddressid (mygooditem.getAddressAddressid());
			setContactinfoContactinfoid (mygooditem.getContactinfoContactinfoid());
			setTotalscore (mygooditem.getTotalscore());
			setNumberofvotes (mygooditem.getNumberofvotes());
			setDisabled (mygooditem.getDisabled());
		}
	}
	
	public static MyGoodItem fullMask() {
		return new MyGoodItem(
			integerMask__ ,
			stringMask__ ,
			stringMask__ ,
			stringMask__ ,
			stringMask__ ,
			stringMask__ ,
			bigDecimalMask__ ,
			integerMask__ ,
			integerMask__ ,
			integerMask__ ,
			integerMask__ ,
			integerMask__ 		);
	}

    public MyGoodItem maskString(Map<String, String> filter) {
        for (Entry<String, String> set : filter.entrySet()) {
            mask(set.getKey(), getEntry(set.getKey(), set.getValue()));
        }
        return this;
    }
    
    public Object getEntry(String pattern, String value) {
        if(pattern==null || value==null) return null;
    	if ("itemid".equals(pattern))
           return Integer.valueOf(value);
        if ("thisIsMyProduct".equals(pattern))
           return value;
        if ("name".equals(pattern))
           return value;
        if ("description".equals(pattern))
           return value;
        if ("imageurl".equals(pattern))
           return value;
        if ("imagethumburl".equals(pattern))
           return value;
    	if ("price".equals(pattern))
           return java.math.BigDecimal.valueOf(Double.valueOf(value));
    	if ("addressAddressid".equals(pattern))
           return Integer.valueOf(value);
    	if ("contactinfoContactinfoid".equals(pattern))
           return Integer.valueOf(value);
    	if ("totalscore".equals(pattern))
           return Integer.valueOf(value);
    	if ("numberofvotes".equals(pattern))
           return Integer.valueOf(value);
    	if ("disabled".equals(pattern))
           return Integer.valueOf(value);
        return null;
    }	
				
    public MyGoodItem mask(Map<String, Object> filter) {
        for (Entry<String, Object> set : filter.entrySet()) {
            mask(set.getKey(), set.getValue());
        }
        return this;
    }
    
    public MyGoodItem mask(String pattern, Object value) {
        if(pattern==null || value==null) return this;
		if ("itemid".equals(pattern)) {
           setItemid((Integer)value);
		   return this;
		}
		if ("thisIsMyProduct".equals(pattern)) {
           setThisIsMyProduct_((String)value);
		   return this;
		}
        if ("name".equals(pattern)) {
           setName(value.toString());
		   return this;
		}
        if ("description".equals(pattern)) {
           setDescription(value.toString());
		   return this;
		}
        if ("imageurl".equals(pattern)) {
           setImageurl(value.toString());
		   return this;
		}
        if ("imagethumburl".equals(pattern)) {
           setImagethumburl(value.toString());
		   return this;
		}
		if ("price".equals(pattern)) {
           setPrice((java.math.BigDecimal)value);
		   return this;
		}
		if ("addressAddressid".equals(pattern)) {
           setAddressAddressid_((Integer)value);
		   return this;
		}
		if ("contactinfoContactinfoid".equals(pattern)) {
           setContactinfoContactinfoid_((Integer)value);
		   return this;
		}
		if ("totalscore".equals(pattern)) {
           setTotalscore((Integer)value);
		   return this;
		}
		if ("numberofvotes".equals(pattern)) {
           setNumberofvotes((Integer)value);
		   return this;
		}
		if ("disabled".equals(pattern)) {
           setDisabled((Integer)value);
		   return this;
		}
        return this;
    }

    public MyGoodItem mask(String pattern) {
        if(pattern==null) return this;
        if ("name".equals(pattern))
           setName(stringMask__);
        if ("description".equals(pattern))
           setDescription(stringMask__);
        if ("imageurl".equals(pattern))
           setImageurl(stringMask__);
        if ("imagethumburl".equals(pattern))
           setImagethumburl(stringMask__);
        return this;
    }

	public void assignNullToBlank () {
        if (StringUtils.isEmpty(getName()))
           setName(null);
        if (StringUtils.isEmpty(getDescription()))
           setDescription(null);
        if (StringUtils.isEmpty(getImageurl()))
           setImageurl(null);
        if (StringUtils.isEmpty(getImagethumburl()))
           setImagethumburl(null);
	}

    public Integer getItemid() {
        return itemid;
    }
	
    public void setItemid (Integer itemid) {
        this.itemid =  itemid;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-name@
    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-description@
    public String getDescription() {
        return description;
    }
	
    public void setDescription (String description) {
        this.description =  description;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-imageurl@
    public String getImageurl() {
        return imageurl;
    }
	
    public void setImageurl (String imageurl) {
        this.imageurl =  imageurl;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-imagethumburl@
    public String getImagethumburl() {
        return imagethumburl;
    }
	
    public void setImagethumburl (String imagethumburl) {
        this.imagethumburl =  imagethumburl;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-price@
    public java.math.BigDecimal getPrice() {
        return price;
    }
	
    public void setPrice (java.math.BigDecimal price) {
        this.price =  price;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-totalscore@
    public Integer getTotalscore() {
        return totalscore;
    }
	
    public void setTotalscore (Integer totalscore) {
        this.totalscore =  totalscore;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-numberofvotes@
    public Integer getNumberofvotes() {
        return numberofvotes;
    }
	
    public void setNumberofvotes (Integer numberofvotes) {
        this.numberofvotes =  numberofvotes;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-disabled@
    public Integer getDisabled() {
        return disabled;
    }
	
    public void setDisabled (Integer disabled) {
        this.disabled =  disabled;
    }
	
//MP-MANAGED-UPDATABLE-ENDING


    public Address getAddressAddressid () {
    	return addressAddressid;
    }
	
    public void setAddressAddressid (Address addressAddressid) {
    	this.addressAddressid = addressAddressid;
    }

    public Integer getAddressAddressid_() {
        return addressAddressid_;
    }
	
    public void setAddressAddressid_ (Integer addressAddressid) {
        this.addressAddressid_ =  addressAddressid;
    }
	
    public MyGoodProduct getThisIsMyProduct () {
    	return thisIsMyProduct;
    }
	
    public void setThisIsMyProduct (MyGoodProduct thisIsMyProduct) {
    	this.thisIsMyProduct = thisIsMyProduct;
    }

    public String getThisIsMyProduct_() {
        return thisIsMyProduct_;
    }
	
    public void setThisIsMyProduct_ (String thisIsMyProduct) {
        this.thisIsMyProduct_ =  thisIsMyProduct;
    }
	
    public Sellercontactinfo getContactinfoContactinfoid () {
    	return contactinfoContactinfoid;
    }
	
    public void setContactinfoContactinfoid (Sellercontactinfo contactinfoContactinfoid) {
    	this.contactinfoContactinfoid = contactinfoContactinfoid;
    }

    public Integer getContactinfoContactinfoid_() {
        return contactinfoContactinfoid_;
    }
	
    public void setContactinfoContactinfoid_ (Integer contactinfoContactinfoid) {
        this.contactinfoContactinfoid_ =  contactinfoContactinfoid;
    }
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @tagItemItemViaItemid-getter-item@
//MP-MANAGED-UPDATABLE-ENDING

    public Set<Tag> getTagTagItemViaTagid() {
        if (tagTagItemViaTagid == null){
            tagTagItemViaTagid = new HashSet<Tag>();
        }
        return tagTagItemViaTagid;
    }

    public void setTagTagItemViaTagid (Set<Tag> tagTagItemViaTagid) {
        this.tagTagItemViaTagid = tagTagItemViaTagid;
    }
    	
    public void addTagTagItemViaTagid (Tag element) {
        getTagTagItemViaTagid().add(element);
    }
	


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
