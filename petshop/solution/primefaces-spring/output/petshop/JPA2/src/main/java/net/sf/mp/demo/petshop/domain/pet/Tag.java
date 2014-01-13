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
import net.sf.mp.demo.petshop.domain.product.MyGoodItem;

/**
 *
 * <p>Title: Tag</p>
 *
 * <p>Description: Domain Object describing a Tag entity</p>
 *
 */
@Entity (name="Tag")
@Table (name="tag")
@NamedQueries({
	 @NamedQuery(name="Tag.findAll", query="SELECT tag FROM Tag tag")
	,@NamedQuery(name="Tag.findByTag", query="SELECT tag FROM Tag tag WHERE tag.tag = :tag")
	,@NamedQuery(name="Tag.findByTagContaining", query="SELECT tag FROM Tag tag WHERE tag.tag like :tag")

	,@NamedQuery(name="Tag.findByRefcount", query="SELECT tag FROM Tag tag WHERE tag.refcount = :refcount")

})
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)

public class Tag extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Tag.findAll";
    public static final String FIND_BY_TAG = "Tag.findByTag";
    public static final String FIND_BY_TAG_CONTAINING ="Tag.findByTagContaining";
    public static final String FIND_BY_REFCOUNT = "Tag.findByRefcount";
	
    @Id @Column(name="tagid" ) 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tagid;

//MP-MANAGED-ADDED-AREA-BEGINNING @tag-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @tag-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-tag@
    @Column(name="tag"  , length=30 , nullable=false , unique=false)
    private String tag; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @refcount-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @refcount-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-refcount@
    @Column(name="refcount"   , nullable=false , unique=false)
    private Integer refcount; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @m2m-myGoodItemTagItemViaItemid-tag@
    @ManyToMany
    @JoinTable(name="TAG_ITEM", 
        joinColumns=@JoinColumn(name="tagid"), 
        inverseJoinColumns=@JoinColumn(name="itemid") 
    )
    private Set <MyGoodItem> myGoodItemTagItemViaItemid = new HashSet <MyGoodItem> ();

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Tag() {
    }

	/**
	* All field constructor 
	*/
    public Tag(
       Integer tagid,
       String tag,
       Integer refcount) {
       //primary keys
       setTagid (tagid);
       //attributes
       setTag (tag);
       setRefcount (refcount);
       //parents
    }

	public Tag flat() {
	   return new Tag(
          getTagid(),
          getTag(),
          getRefcount()
	   );
	}

    /**
    * display semanticReference with attribute inside class
    */
	public String display() {
	  StringBuffer sb = new StringBuffer();
      if (this.getTag()!=null)
         sb.append (this.getTag() +" " ); 
      if (this.getRefcount()!=null)
         sb.append (this.getRefcount() +" " ); 
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
            .append("tagid", tagid)
            .append("tag", tag)
            .append("refcount", refcount)
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
		if (!(object instanceof Tag)) return false;
		Tag tag = (Tag) object;
		if (tag.tagid==null || !tag.tagid.equals(tagid)) return false;
		return true;
	}    

	public Tag clone() {
        Tag tag = flat();
        return tag;
	}
	
	public void copy (Tag tag) {
		if (tag!=null) {
			setTagid (tag.getTagid());
			setTag (tag.getTag());
			setRefcount (tag.getRefcount());
		}
	}
	
	public static Tag fullMask() {
		return new Tag(
			integerMask__ ,
			stringMask__ ,
			integerMask__ 		);
	}

    public Tag maskString(Map<String, String> filter) {
        for (Entry<String, String> set : filter.entrySet()) {
            mask(set.getKey(), getEntry(set.getKey(), set.getValue()));
        }
        return this;
    }
    
    public Object getEntry(String pattern, String value) {
        if(pattern==null || value==null) return null;
    	if ("tagid".equals(pattern))
           return Integer.valueOf(value);
        if ("tag".equals(pattern))
           return value;
    	if ("refcount".equals(pattern))
           return Integer.valueOf(value);
        return null;
    }	
				
    public Tag mask(Map<String, Object> filter) {
        for (Entry<String, Object> set : filter.entrySet()) {
            mask(set.getKey(), set.getValue());
        }
        return this;
    }
    
    public Tag mask(String pattern, Object value) {
        if(pattern==null || value==null) return this;
		if ("tagid".equals(pattern)) {
           setTagid((Integer)value);
		   return this;
		}
        if ("tag".equals(pattern)) {
           setTag(value.toString());
		   return this;
		}
		if ("refcount".equals(pattern)) {
           setRefcount((Integer)value);
		   return this;
		}
        return this;
    }

    public Tag mask(String pattern) {
        if(pattern==null) return this;
        if ("tag".equals(pattern))
           setTag(stringMask__);
        return this;
    }

	public void assignNullToBlank () {
        if (StringUtils.isEmpty(getTag()))
           setTag(null);
	}

    public Integer getTagid() {
        return tagid;
    }
	
    public void setTagid (Integer tagid) {
        this.tagid =  tagid;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-tag@
    public String getTag() {
        return tag;
    }
	
    public void setTag (String tag) {
        this.tag =  tag;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-refcount@
    public Integer getRefcount() {
        return refcount;
    }
	
    public void setRefcount (Integer refcount) {
        this.refcount =  refcount;
    }
	
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @tagItemTagViaTagid-getter-tag@
//MP-MANAGED-UPDATABLE-ENDING

    public Set<MyGoodItem> getMyGoodItemTagItemViaItemid() {
        if (myGoodItemTagItemViaItemid == null){
            myGoodItemTagItemViaItemid = new HashSet<MyGoodItem>();
        }
        return myGoodItemTagItemViaItemid;
    }

    public void setMyGoodItemTagItemViaItemid (Set<MyGoodItem> myGoodItemTagItemViaItemid) {
        this.myGoodItemTagItemViaItemid = myGoodItemTagItemViaItemid;
    }
    	
    public void addMyGoodItemTagItemViaItemid (MyGoodItem element) {
        getMyGoodItemTagItemViaItemid().add(element);
    }
	


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
