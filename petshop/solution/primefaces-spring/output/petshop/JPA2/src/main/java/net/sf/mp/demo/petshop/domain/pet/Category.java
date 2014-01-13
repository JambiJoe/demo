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
package net.sf.mp.demo.petshop.domain.pet;

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
import net.sf.mp.demo.petshop.domain.product.MyGoodProduct;

/**
 *
 * <p>Title: Category</p>
 *
 * <p>Description: Domain Object describing a Category entity</p>
 *
 */
@Entity (name="Category")
@Table (name="category")
@NamedQueries({
	 @NamedQuery(name="Category.findAll", query="SELECT category FROM Category category")
	,@NamedQuery(name="Category.findByName", query="SELECT category FROM Category category WHERE category.name = :name")
	,@NamedQuery(name="Category.findByNameContaining", query="SELECT category FROM Category category WHERE category.name like :name")

	,@NamedQuery(name="Category.findByDescription", query="SELECT category FROM Category category WHERE category.description = :description")
	,@NamedQuery(name="Category.findByDescriptionContaining", query="SELECT category FROM Category category WHERE category.description like :description")

	,@NamedQuery(name="Category.findByImageurl", query="SELECT category FROM Category category WHERE category.imageurl = :imageurl")
	,@NamedQuery(name="Category.findByImageurlContaining", query="SELECT category FROM Category category WHERE category.imageurl like :imageurl")

})
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)

public class Category extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Category.findAll";
    public static final String FIND_BY_NAME = "Category.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="Category.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "Category.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="Category.findByDescriptionContaining";
    public static final String FIND_BY_IMAGEURL = "Category.findByImageurl";
    public static final String FIND_BY_IMAGEURL_CONTAINING ="Category.findByImageurlContaining";
	
    @Id @Column(name="categoryid" ,length=10) 
    private String categoryid;

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

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @myGoodProducts-field-category@
    @OneToMany (targetEntity=net.sf.mp.demo.petshop.domain.product.MyGoodProduct.class, fetch=FetchType.LAZY, mappedBy="categoryid", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <MyGoodProduct> myGoodProducts = new HashSet<MyGoodProduct>(); 

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Category() {
    }

	/**
	* All field constructor 
	*/
    public Category(
       String categoryid,
       String name,
       String description,
       String imageurl) {
       //primary keys
       setCategoryid (categoryid);
       //attributes
       setName (name);
       setDescription (description);
       setImageurl (imageurl);
       //parents
    }

	public Category flat() {
	   return new Category(
          getCategoryid(),
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
      if (this.getCategoryid()!=null)
         sb.append (this.getCategoryid() +" " ); 
      if (this.getName()!=null)
         sb.append (this.getName() +" " ); 
      if (this.getDescription()!=null)
         sb.append (this.getDescription() +" " ); 
      if (this.getImageurl()!=null)
         sb.append (this.getImageurl() +" " ); 
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
            .append("categoryid", categoryid)
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
		if (!(object instanceof Category)) return false;
		Category category = (Category) object;
		if (category.categoryid==null || !category.categoryid.equals(categoryid)) return false;
		return true;
	}    

	public Category clone() {
        Category category = flat();
        return category;
	}
	
	public void copy (Category category) {
		if (category!=null) {
			setCategoryid (category.getCategoryid());
			setName (category.getName());
			setDescription (category.getDescription());
			setImageurl (category.getImageurl());
		}
	}
	
	public static Category fullMask() {
		return new Category(
			stringMask__ ,
			stringMask__ ,
			stringMask__ ,
			stringMask__ 		);
	}

    public Category maskString(Map<String, String> filter) {
        for (Entry<String, String> set : filter.entrySet()) {
            mask(set.getKey(), getEntry(set.getKey(), set.getValue()));
        }
        return this;
    }
    
    public Object getEntry(String pattern, String value) {
        if(pattern==null || value==null) return null;
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
				
    public Category mask(Map<String, Object> filter) {
        for (Entry<String, Object> set : filter.entrySet()) {
            mask(set.getKey(), set.getValue());
        }
        return this;
    }
    
    public Category mask(String pattern, Object value) {
        if(pattern==null || value==null) return this;
        if ("categoryid".equals(pattern)) {
           setCategoryid(value.toString());
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

    public Category mask(String pattern) {
        if(pattern==null) return this;
        if ("categoryid".equals(pattern))
           setCategoryid(stringMask__);
        if ("name".equals(pattern))
           setName(stringMask__);
        if ("description".equals(pattern))
           setDescription(stringMask__);
        if ("imageurl".equals(pattern))
           setImageurl(stringMask__);
        return this;
    }

	public void assignNullToBlank () {
        if (StringUtils.isEmpty(getCategoryid()))
           setCategoryid(null);
        if (StringUtils.isEmpty(getName()))
           setName(null);
        if (StringUtils.isEmpty(getDescription()))
           setDescription(null);
        if (StringUtils.isEmpty(getImageurl()))
           setImageurl(null);
	}

    public String getCategoryid() {
        return categoryid;
    }
	
    public void setCategoryid (String categoryid) {
        this.categoryid =  categoryid;
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



//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @myGoodProducts-getter-category@
    public Set<MyGoodProduct> getMyGoodProducts() {
        if (myGoodProducts == null){
            myGoodProducts = new HashSet<MyGoodProduct>();
        }
        return myGoodProducts;
    }

    public void setMyGoodProducts (Set<MyGoodProduct> myGoodProducts) {
        this.myGoodProducts = myGoodProducts;
    }	
    
    public void addMyGoodProducts (MyGoodProduct element) {
    	    getMyGoodProducts().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
