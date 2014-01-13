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
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;
import net.sf.mp.demo.petshop.domain.pet.Category;

/**
 *
 * <p>Title: MyGoodProduct</p>
 *
 * <p>Description: Domain Object describing a MyGoodProduct entity</p>
 *
 */
@Entity (name="MyGoodProduct")
@Table (name="product")
@NamedQueries({
	 @NamedQuery(name="MyGoodProduct.findAll", query="SELECT mygoodproduct FROM MyGoodProduct mygoodproduct")
	,@NamedQuery(name="MyGoodProduct.findByName", query="SELECT mygoodproduct FROM MyGoodProduct mygoodproduct WHERE mygoodproduct.name = :name")
	,@NamedQuery(name="MyGoodProduct.findByNameContaining", query="SELECT mygoodproduct FROM MyGoodProduct mygoodproduct WHERE mygoodproduct.name like :name")

	,@NamedQuery(name="MyGoodProduct.findByDescription", query="SELECT mygoodproduct FROM MyGoodProduct mygoodproduct WHERE mygoodproduct.description = :description")
	,@NamedQuery(name="MyGoodProduct.findByDescriptionContaining", query="SELECT mygoodproduct FROM MyGoodProduct mygoodproduct WHERE mygoodproduct.description like :description")

	,@NamedQuery(name="MyGoodProduct.findByImageurl", query="SELECT mygoodproduct FROM MyGoodProduct mygoodproduct WHERE mygoodproduct.imageurl = :imageurl")
	,@NamedQuery(name="MyGoodProduct.findByImageurlContaining", query="SELECT mygoodproduct FROM MyGoodProduct mygoodproduct WHERE mygoodproduct.imageurl like :imageurl")

})

public class MyGoodProduct extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "MyGoodProduct.findAll";
    public static final String FIND_BY_NAME = "MyGoodProduct.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="MyGoodProduct.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "MyGoodProduct.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="MyGoodProduct.findByDescriptionContaining";
    public static final String FIND_BY_IMAGEURL = "MyGoodProduct.findByImageurl";
    public static final String FIND_BY_IMAGEURL_CONTAINING ="MyGoodProduct.findByImageurlContaining";
	
    @Id @Column(name="productid" ,length=10) 
    private String productid;

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name"  , length=25 , nullable=false , unique=false)
    private String name; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @description-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @description-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-description@
    @Column(name="description"  , length=255 , nullable=false , unique=false)
    private String description; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @imageurl-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @imageurl-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-imageurl@
    @Column(name="imageurl"  , length=55 , nullable=true , unique=false)
    private String imageurl; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="categoryid", referencedColumnName = "categoryid" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Category categoryid;  

    @Column(name="categoryid" , length=10 , nullable=false , unique=true, insertable=false, updatable=false)
    private String categoryid_;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @myGoodItems-field-product@
    @OneToMany (targetEntity=net.sf.mp.demo.petshop.domain.product.MyGoodItem.class, fetch=FetchType.LAZY, mappedBy="thisIsMyProduct", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <MyGoodItem> myGoodItems = new HashSet<MyGoodItem>(); 

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public MyGoodProduct() {
    }

	/**
	* All field constructor 
	*/
    public MyGoodProduct(
       String productid,
       String categoryid,
       String name,
       String description,
       String imageurl) {
       //primary keys
       setProductid (productid);
       //attributes
       setName (name);
       setDescription (description);
       setImageurl (imageurl);
       //parents
       this.categoryid = new Category();
       this.categoryid.setCategoryid(categoryid); 
	   setCategoryid_ (categoryid);
    }

	public MyGoodProduct flat() {
	   return new MyGoodProduct(
          getProductid(),
          getCategoryid_(),
          getName(),
          getDescription(),
          getImageurl()
	   );
	}

    /**
    * display semanticReference with attribute inside class
    */
	public String display() {
	  StringBuffer sb = new StringBuffer();
      if (this.getProductid()!=null)
         sb.append (this.getProductid() +" " ); 
      if (this.getName()!=null)
         sb.append (this.getName() +" - "); 
      if (this.getDescription()!=null)
         sb.append (this.getDescription() +" - "); 
      if (this.getImageurl()!=null)
         sb.append (this.getImageurl() +" - "); 
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
            .append("productid", productid)
            .append("categoryid_", categoryid_)
            .append("name", name)
            .append("description", description)
            .append("imageurl", imageurl)
	 	;
	} 
		
	public String toString(Object object) {
	 	return getToStringBuilder(object).toString();
	} 
	
	public String toStringWithParents() {
	    ToStringBuilder toStringBuilder = getToStringBuilder(this);
        if (categoryid!=null)
            toStringBuilder.append("categoryid", categoryid.toStringWithParents());
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
		if (!(object instanceof MyGoodProduct)) return false;
		MyGoodProduct mygoodproduct = (MyGoodProduct) object;
		if (mygoodproduct.productid==null || !mygoodproduct.productid.equals(productid)) return false;
		return true;
	}    

	public MyGoodProduct clone() {
        MyGoodProduct mygoodproduct = flat();
        if (getCategoryid()!=null) 
            mygoodproduct.setCategoryid (getCategoryid().clone());   
        return mygoodproduct;
	}
	
	public void copy (MyGoodProduct mygoodproduct) {
		if (mygoodproduct!=null) {
			setProductid (mygoodproduct.getProductid());
			setCategoryid (mygoodproduct.getCategoryid());
			setName (mygoodproduct.getName());
			setDescription (mygoodproduct.getDescription());
			setImageurl (mygoodproduct.getImageurl());
		}
	}
	
	public static MyGoodProduct fullMask() {
		return new MyGoodProduct(
			stringMask__ ,
			stringMask__ ,
			stringMask__ ,
			stringMask__ ,
			stringMask__ 		);
	}

    public MyGoodProduct maskString(Map<String, String> filter) {
        for (Entry<String, String> set : filter.entrySet()) {
            mask(set.getKey(), getEntry(set.getKey(), set.getValue()));
        }
        return this;
    }
    
    public Object getEntry(String pattern, String value) {
        if(pattern==null || value==null) return null;
        if ("productid".equals(pattern))
           return value;
        if ("categoryid".equals(pattern))
           return value;
        if ("name".equals(pattern))
           return value;
        if ("description".equals(pattern))
           return value;
        if ("imageurl".equals(pattern))
           return value;
        return null;
    }	
				
    public MyGoodProduct mask(Map<String, Object> filter) {
        for (Entry<String, Object> set : filter.entrySet()) {
            mask(set.getKey(), set.getValue());
        }
        return this;
    }
    
    public MyGoodProduct mask(String pattern, Object value) {
        if(pattern==null || value==null) return this;
        if ("productid".equals(pattern)) {
           setProductid(value.toString());
		   return this;
		}
		if ("categoryid".equals(pattern)) {
           setCategoryid_((String)value);
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
        return this;
    }

    public MyGoodProduct mask(String pattern) {
        if(pattern==null) return this;
        if ("productid".equals(pattern))
           setProductid(stringMask__);
        if ("name".equals(pattern))
           setName(stringMask__);
        if ("description".equals(pattern))
           setDescription(stringMask__);
        if ("imageurl".equals(pattern))
           setImageurl(stringMask__);
        return this;
    }

	public void assignNullToBlank () {
        if (StringUtils.isEmpty(getProductid()))
           setProductid(null);
        if (StringUtils.isEmpty(getName()))
           setName(null);
        if (StringUtils.isEmpty(getDescription()))
           setDescription(null);
        if (StringUtils.isEmpty(getImageurl()))
           setImageurl(null);
	}

    public String getProductid() {
        return productid;
    }
	
    public void setProductid (String productid) {
        this.productid =  productid;
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


    public Category getCategoryid () {
    	return categoryid;
    }
	
    public void setCategoryid (Category categoryid) {
    	this.categoryid = categoryid;
    }

    public String getCategoryid_() {
        return categoryid_;
    }
	
    public void setCategoryid_ (String categoryid) {
        this.categoryid_ =  categoryid;
    }
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @myGoodItems-getter-product@
    public Set<MyGoodItem> getMyGoodItems() {
        if (myGoodItems == null){
            myGoodItems = new HashSet<MyGoodItem>();
        }
        return myGoodItems;
    }

    public void setMyGoodItems (Set<MyGoodItem> myGoodItems) {
        this.myGoodItems = myGoodItems;
    }	
    
    public void addMyGoodItems (MyGoodItem element) {
    	    getMyGoodItems().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
