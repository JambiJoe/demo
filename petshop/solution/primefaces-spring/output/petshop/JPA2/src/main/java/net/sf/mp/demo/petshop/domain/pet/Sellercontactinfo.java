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
 * <p>Title: Sellercontactinfo</p>
 *
 * <p>Description: Domain Object describing a Sellercontactinfo entity</p>
 *
 */
@Entity (name="Sellercontactinfo")
@Table (name="sellercontactinfo")
@NamedQueries({
	 @NamedQuery(name="Sellercontactinfo.findAll", query="SELECT sellercontactinfo FROM Sellercontactinfo sellercontactinfo")
	,@NamedQuery(name="Sellercontactinfo.findByLastname", query="SELECT sellercontactinfo FROM Sellercontactinfo sellercontactinfo WHERE sellercontactinfo.lastname = :lastname")
	,@NamedQuery(name="Sellercontactinfo.findByLastnameContaining", query="SELECT sellercontactinfo FROM Sellercontactinfo sellercontactinfo WHERE sellercontactinfo.lastname like :lastname")

	,@NamedQuery(name="Sellercontactinfo.findByFirstname", query="SELECT sellercontactinfo FROM Sellercontactinfo sellercontactinfo WHERE sellercontactinfo.firstname = :firstname")
	,@NamedQuery(name="Sellercontactinfo.findByFirstnameContaining", query="SELECT sellercontactinfo FROM Sellercontactinfo sellercontactinfo WHERE sellercontactinfo.firstname like :firstname")

	,@NamedQuery(name="Sellercontactinfo.findByEmail", query="SELECT sellercontactinfo FROM Sellercontactinfo sellercontactinfo WHERE sellercontactinfo.email = :email")
	,@NamedQuery(name="Sellercontactinfo.findByEmailContaining", query="SELECT sellercontactinfo FROM Sellercontactinfo sellercontactinfo WHERE sellercontactinfo.email like :email")

})

public class Sellercontactinfo extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Sellercontactinfo.findAll";
    public static final String FIND_BY_LASTNAME = "Sellercontactinfo.findByLastname";
    public static final String FIND_BY_LASTNAME_CONTAINING ="Sellercontactinfo.findByLastnameContaining";
    public static final String FIND_BY_FIRSTNAME = "Sellercontactinfo.findByFirstname";
    public static final String FIND_BY_FIRSTNAME_CONTAINING ="Sellercontactinfo.findByFirstnameContaining";
    public static final String FIND_BY_EMAIL = "Sellercontactinfo.findByEmail";
    public static final String FIND_BY_EMAIL_CONTAINING ="Sellercontactinfo.findByEmailContaining";
	
    @Id @Column(name="contactinfoid" ) 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer contactinfoid;

//MP-MANAGED-ADDED-AREA-BEGINNING @lastname-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @lastname-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-lastname@
    @Column(name="lastname"  , length=24 , nullable=false , unique=false)
    private String lastname; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @firstname-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @firstname-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-firstname@
    @Column(name="firstname"  , length=24 , nullable=false , unique=false)
    private String firstname; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @email-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @email-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-email@
    @Column(name="email"  , length=24 , nullable=false , unique=false)
    private String email; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @myGoodItems-field-sellercontactinfo@
    @OneToMany (targetEntity=net.sf.mp.demo.petshop.domain.product.MyGoodItem.class, fetch=FetchType.LAZY, mappedBy="contactinfoContactinfoid", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <MyGoodItem> myGoodItems = new HashSet<MyGoodItem>(); 

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Sellercontactinfo() {
    }

	/**
	* All field constructor 
	*/
    public Sellercontactinfo(
       Integer contactinfoid,
       String lastname,
       String firstname,
       String email) {
       //primary keys
       setContactinfoid (contactinfoid);
       //attributes
       setLastname (lastname);
       setFirstname (firstname);
       setEmail (email);
       //parents
    }

	public Sellercontactinfo flat() {
	   return new Sellercontactinfo(
          getContactinfoid(),
          getLastname(),
          getFirstname(),
          getEmail()
	   );
	}

    /**
    * display semanticReference with attribute inside class
    */
	public String display() {
	  StringBuffer sb = new StringBuffer();
      if (this.getLastname()!=null)
         sb.append (this.getLastname() +" " ); 
      if (this.getFirstname()!=null)
         sb.append (this.getFirstname() +" " ); 
      if (this.getEmail()!=null)
         sb.append (this.getEmail() +" " ); 
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
            .append("contactinfoid", contactinfoid)
            .append("lastname", lastname)
            .append("firstname", firstname)
            .append("email", email)
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
		if (!(object instanceof Sellercontactinfo)) return false;
		Sellercontactinfo sellercontactinfo = (Sellercontactinfo) object;
		if (sellercontactinfo.contactinfoid==null || !sellercontactinfo.contactinfoid.equals(contactinfoid)) return false;
		return true;
	}    

	public Sellercontactinfo clone() {
        Sellercontactinfo sellercontactinfo = flat();
        return sellercontactinfo;
	}
	
	public void copy (Sellercontactinfo sellercontactinfo) {
		if (sellercontactinfo!=null) {
			setContactinfoid (sellercontactinfo.getContactinfoid());
			setLastname (sellercontactinfo.getLastname());
			setFirstname (sellercontactinfo.getFirstname());
			setEmail (sellercontactinfo.getEmail());
		}
	}
	
	public static Sellercontactinfo fullMask() {
		return new Sellercontactinfo(
			integerMask__ ,
			stringMask__ ,
			stringMask__ ,
			stringMask__ 		);
	}

    public Sellercontactinfo maskString(Map<String, String> filter) {
        for (Entry<String, String> set : filter.entrySet()) {
            mask(set.getKey(), getEntry(set.getKey(), set.getValue()));
        }
        return this;
    }
    
    public Object getEntry(String pattern, String value) {
        if(pattern==null || value==null) return null;
    	if ("contactinfoid".equals(pattern))
           return Integer.valueOf(value);
        if ("lastname".equals(pattern))
           return value;
        if ("firstname".equals(pattern))
           return value;
        if ("email".equals(pattern))
           return value;
        return null;
    }	
				
    public Sellercontactinfo mask(Map<String, Object> filter) {
        for (Entry<String, Object> set : filter.entrySet()) {
            mask(set.getKey(), set.getValue());
        }
        return this;
    }
    
    public Sellercontactinfo mask(String pattern, Object value) {
        if(pattern==null || value==null) return this;
		if ("contactinfoid".equals(pattern)) {
           setContactinfoid((Integer)value);
		   return this;
		}
        if ("lastname".equals(pattern)) {
           setLastname(value.toString());
		   return this;
		}
        if ("firstname".equals(pattern)) {
           setFirstname(value.toString());
		   return this;
		}
        if ("email".equals(pattern)) {
           setEmail(value.toString());
		   return this;
		}
        return this;
    }

    public Sellercontactinfo mask(String pattern) {
        if(pattern==null) return this;
        if ("lastname".equals(pattern))
           setLastname(stringMask__);
        if ("firstname".equals(pattern))
           setFirstname(stringMask__);
        if ("email".equals(pattern))
           setEmail(stringMask__);
        return this;
    }

	public void assignNullToBlank () {
        if (StringUtils.isEmpty(getLastname()))
           setLastname(null);
        if (StringUtils.isEmpty(getFirstname()))
           setFirstname(null);
        if (StringUtils.isEmpty(getEmail()))
           setEmail(null);
	}

    public Integer getContactinfoid() {
        return contactinfoid;
    }
	
    public void setContactinfoid (Integer contactinfoid) {
        this.contactinfoid =  contactinfoid;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-lastname@
    public String getLastname() {
        return lastname;
    }
	
    public void setLastname (String lastname) {
        this.lastname =  lastname;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-firstname@
    public String getFirstname() {
        return firstname;
    }
	
    public void setFirstname (String firstname) {
        this.firstname =  firstname;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-email@
    public String getEmail() {
        return email;
    }
	
    public void setEmail (String email) {
        this.email =  email;
    }
	
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @myGoodItems-getter-sellercontactinfo@
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
