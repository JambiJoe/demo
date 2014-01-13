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
	* - time      : 2014/01/11 ap. J.-C. at 23:51:19 CET
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
 * <p>Title: Address</p>
 *
 * <p>Description: Domain Object describing a Address entity</p>
 *
 */
@Entity (name="Address")
@Table (name="address")
@NamedQueries({
	 @NamedQuery(name="Address.findAll", query="SELECT address FROM Address address")
	,@NamedQuery(name="Address.findByStreet1", query="SELECT address FROM Address address WHERE address.street1 = :street1")
	,@NamedQuery(name="Address.findByStreet1Containing", query="SELECT address FROM Address address WHERE address.street1 like :street1")

	,@NamedQuery(name="Address.findByStreet2", query="SELECT address FROM Address address WHERE address.street2 = :street2")
	,@NamedQuery(name="Address.findByStreet2Containing", query="SELECT address FROM Address address WHERE address.street2 like :street2")

	,@NamedQuery(name="Address.findByCity", query="SELECT address FROM Address address WHERE address.city = :city")
	,@NamedQuery(name="Address.findByCityContaining", query="SELECT address FROM Address address WHERE address.city like :city")

	,@NamedQuery(name="Address.findByState", query="SELECT address FROM Address address WHERE address.state = :state")
	,@NamedQuery(name="Address.findByStateContaining", query="SELECT address FROM Address address WHERE address.state like :state")

	,@NamedQuery(name="Address.findByZip", query="SELECT address FROM Address address WHERE address.zip = :zip")
	,@NamedQuery(name="Address.findByZipContaining", query="SELECT address FROM Address address WHERE address.zip like :zip")

	,@NamedQuery(name="Address.findByLatitude", query="SELECT address FROM Address address WHERE address.latitude = :latitude")

	,@NamedQuery(name="Address.findByLongitude", query="SELECT address FROM Address address WHERE address.longitude = :longitude")

})

public class Address extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Address.findAll";
    public static final String FIND_BY_STREET1 = "Address.findByStreet1";
    public static final String FIND_BY_STREET1_CONTAINING ="Address.findByStreet1Containing";
    public static final String FIND_BY_STREET2 = "Address.findByStreet2";
    public static final String FIND_BY_STREET2_CONTAINING ="Address.findByStreet2Containing";
    public static final String FIND_BY_CITY = "Address.findByCity";
    public static final String FIND_BY_CITY_CONTAINING ="Address.findByCityContaining";
    public static final String FIND_BY_STATE = "Address.findByState";
    public static final String FIND_BY_STATE_CONTAINING ="Address.findByStateContaining";
    public static final String FIND_BY_ZIP = "Address.findByZip";
    public static final String FIND_BY_ZIP_CONTAINING ="Address.findByZipContaining";
    public static final String FIND_BY_LATITUDE = "Address.findByLatitude";
    public static final String FIND_BY_LONGITUDE = "Address.findByLongitude";
	
    @Id @Column(name="addressid" ) 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addressid;

//MP-MANAGED-ADDED-AREA-BEGINNING @street1-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @street1-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-street1@
    @Column(name="street1"  , length=55 , nullable=false , unique=false)
    private String street1; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @street2-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @street2-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-street2@
    @Column(name="street2"  , length=55 , nullable=true , unique=false)
    private String street2; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @city-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @city-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-city@
    @Column(name="city"  , length=55 , nullable=false , unique=false)
    private String city; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @state-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @state-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-state@
    @Column(name="state"  , length=25 , nullable=false , unique=false)
    private String state; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @zip-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @zip-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-zip@
    @Column(name="zip"  , length=5 , nullable=false , unique=false)
    private String zip; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @latitude-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @latitude-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-latitude@
    @Column(name="latitude"   , nullable=false , unique=false)
    private java.math.BigDecimal latitude; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @longitude-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @longitude-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-longitude@
    @Column(name="longitude"   , nullable=false , unique=false)
    private java.math.BigDecimal longitude; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @myGoodItems-field-address@
    @OneToMany (targetEntity=net.sf.mp.demo.petshop.domain.product.MyGoodItem.class, fetch=FetchType.LAZY, mappedBy="addressAddressid", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <MyGoodItem> myGoodItems = new HashSet<MyGoodItem>(); 

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Address() {
    }

	/**
	* All field constructor 
	*/
    public Address(
       Integer addressid,
       String street1,
       String street2,
       String city,
       String state,
       String zip,
       java.math.BigDecimal latitude,
       java.math.BigDecimal longitude) {
       //primary keys
       setAddressid (addressid);
       //attributes
       setStreet1 (street1);
       setStreet2 (street2);
       setCity (city);
       setState (state);
       setZip (zip);
       setLatitude (latitude);
       setLongitude (longitude);
       //parents
    }

	public Address flat() {
	   return new Address(
          getAddressid(),
          getStreet1(),
          getStreet2(),
          getCity(),
          getState(),
          getZip(),
          getLatitude(),
          getLongitude()
	   );
	}

    /**
    * display semanticReference with attribute inside class
    */
	public String display() {
	  StringBuffer sb = new StringBuffer();
      if (this.getStreet1()!=null)
         sb.append (this.getStreet1() +" " ); 
      if (this.getStreet2()!=null)
         sb.append (this.getStreet2() +" " ); 
      if (this.getCity()!=null)
         sb.append (this.getCity() +" " ); 
      if (this.getState()!=null)
         sb.append (this.getState() +" " ); 
      if (this.getZip()!=null)
         sb.append (this.getZip() +" " ); 
      if (this.getLatitude()!=null)
         sb.append (this.getLatitude() +" " ); 
      if (this.getLongitude()!=null)
         sb.append (this.getLongitude() +" " ); 
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
            .append("addressid", addressid)
            .append("street1", street1)
            .append("street2", street2)
            .append("city", city)
            .append("state", state)
            .append("zip", zip)
            .append("latitude", latitude)
            .append("longitude", longitude)
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
		if (!(object instanceof Address)) return false;
		Address address = (Address) object;
		if (address.addressid==null || !address.addressid.equals(addressid)) return false;
		return true;
	}    

	public Address clone() {
        Address address = flat();
        return address;
	}
	
	public void copy (Address address) {
		if (address!=null) {
			setAddressid (address.getAddressid());
			setStreet1 (address.getStreet1());
			setStreet2 (address.getStreet2());
			setCity (address.getCity());
			setState (address.getState());
			setZip (address.getZip());
			setLatitude (address.getLatitude());
			setLongitude (address.getLongitude());
		}
	}
	
	public static Address fullMask() {
		return new Address(
			integerMask__ ,
			stringMask__ ,
			stringMask__ ,
			stringMask__ ,
			stringMask__ ,
			stringMask__ ,
			bigDecimalMask__ ,
			bigDecimalMask__ 		);
	}

    public Address maskString(Map<String, String> filter) {
        for (Entry<String, String> set : filter.entrySet()) {
            mask(set.getKey(), getEntry(set.getKey(), set.getValue()));
        }
        return this;
    }
    
    public Object getEntry(String pattern, String value) {
        if(pattern==null || value==null) return null;
    	if ("addressid".equals(pattern))
           return Integer.valueOf(value);
        if ("street1".equals(pattern))
           return value;
        if ("street2".equals(pattern))
           return value;
        if ("city".equals(pattern))
           return value;
        if ("state".equals(pattern))
           return value;
        if ("zip".equals(pattern))
           return value;
    	if ("latitude".equals(pattern))
           return java.math.BigDecimal.valueOf(Double.valueOf(value));
    	if ("longitude".equals(pattern))
           return java.math.BigDecimal.valueOf(Double.valueOf(value));
        return null;
    }	
				
    public Address mask(Map<String, Object> filter) {
        for (Entry<String, Object> set : filter.entrySet()) {
            mask(set.getKey(), set.getValue());
        }
        return this;
    }
    
    public Address mask(String pattern, Object value) {
        if(pattern==null || value==null) return this;
		if ("addressid".equals(pattern)) {
           setAddressid((Integer)value);
		   return this;
		}
        if ("street1".equals(pattern)) {
           setStreet1(value.toString());
		   return this;
		}
        if ("street2".equals(pattern)) {
           setStreet2(value.toString());
		   return this;
		}
        if ("city".equals(pattern)) {
           setCity(value.toString());
		   return this;
		}
        if ("state".equals(pattern)) {
           setState(value.toString());
		   return this;
		}
        if ("zip".equals(pattern)) {
           setZip(value.toString());
		   return this;
		}
		if ("latitude".equals(pattern)) {
           setLatitude((java.math.BigDecimal)value);
		   return this;
		}
		if ("longitude".equals(pattern)) {
           setLongitude((java.math.BigDecimal)value);
		   return this;
		}
        return this;
    }

    public Address mask(String pattern) {
        if(pattern==null) return this;
        if ("street1".equals(pattern))
           setStreet1(stringMask__);
        if ("street2".equals(pattern))
           setStreet2(stringMask__);
        if ("city".equals(pattern))
           setCity(stringMask__);
        if ("state".equals(pattern))
           setState(stringMask__);
        if ("zip".equals(pattern))
           setZip(stringMask__);
        return this;
    }

	public void assignNullToBlank () {
        if (StringUtils.isEmpty(getStreet1()))
           setStreet1(null);
        if (StringUtils.isEmpty(getStreet2()))
           setStreet2(null);
        if (StringUtils.isEmpty(getCity()))
           setCity(null);
        if (StringUtils.isEmpty(getState()))
           setState(null);
        if (StringUtils.isEmpty(getZip()))
           setZip(null);
	}

    public Integer getAddressid() {
        return addressid;
    }
	
    public void setAddressid (Integer addressid) {
        this.addressid =  addressid;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-street1@
    public String getStreet1() {
        return street1;
    }
	
    public void setStreet1 (String street1) {
        this.street1 =  street1;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-street2@
    public String getStreet2() {
        return street2;
    }
	
    public void setStreet2 (String street2) {
        this.street2 =  street2;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-city@
    public String getCity() {
        return city;
    }
	
    public void setCity (String city) {
        this.city =  city;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-state@
    public String getState() {
        return state;
    }
	
    public void setState (String state) {
        this.state =  state;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-zip@
    public String getZip() {
        return zip;
    }
	
    public void setZip (String zip) {
        this.zip =  zip;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-latitude@
    public java.math.BigDecimal getLatitude() {
        return latitude;
    }
	
    public void setLatitude (java.math.BigDecimal latitude) {
        this.latitude =  latitude;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-longitude@
    public java.math.BigDecimal getLongitude() {
        return longitude;
    }
	
    public void setLongitude (java.math.BigDecimal longitude) {
        this.longitude =  longitude;
    }
	
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @myGoodItems-getter-address@
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
