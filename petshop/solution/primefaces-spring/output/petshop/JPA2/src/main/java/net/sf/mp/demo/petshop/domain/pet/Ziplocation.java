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

/**
 *
 * <p>Title: Ziplocation</p>
 *
 * <p>Description: Domain Object describing a Ziplocation entity</p>
 *
 */
@Entity (name="Ziplocation")
@Table (name="ziplocation")
@NamedQueries({
	 @NamedQuery(name="Ziplocation.findAll", query="SELECT ziplocation FROM Ziplocation ziplocation")
	,@NamedQuery(name="Ziplocation.findByCity", query="SELECT ziplocation FROM Ziplocation ziplocation WHERE ziplocation.city = :city")
	,@NamedQuery(name="Ziplocation.findByCityContaining", query="SELECT ziplocation FROM Ziplocation ziplocation WHERE ziplocation.city like :city")

	,@NamedQuery(name="Ziplocation.findByState", query="SELECT ziplocation FROM Ziplocation ziplocation WHERE ziplocation.state = :state")
	,@NamedQuery(name="Ziplocation.findByStateContaining", query="SELECT ziplocation FROM Ziplocation ziplocation WHERE ziplocation.state like :state")

})

public class Ziplocation extends AbstractDomainObject {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Ziplocation.findAll";
    public static final String FIND_BY_CITY = "Ziplocation.findByCity";
    public static final String FIND_BY_CITY_CONTAINING ="Ziplocation.findByCityContaining";
    public static final String FIND_BY_STATE = "Ziplocation.findByState";
    public static final String FIND_BY_STATE_CONTAINING ="Ziplocation.findByStateContaining";
	
    @Id @Column(name="zipcode" ) 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer zipcode;

//MP-MANAGED-ADDED-AREA-BEGINNING @city-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @city-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-city@
    @Column(name="city"  , length=30 , nullable=false , unique=false)
    private String city; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @state-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @state-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-state@
    @Column(name="state"  , length=2 , nullable=false , unique=false)
    private String state; 
//MP-MANAGED-UPDATABLE-ENDING

    /**
    * Default constructor
    */
    public Ziplocation() {
    }

	/**
	* All field constructor 
	*/
    public Ziplocation(
       Integer zipcode,
       String city,
       String state) {
       //primary keys
       setZipcode (zipcode);
       //attributes
       setCity (city);
       setState (state);
       //parents
    }

	public Ziplocation flat() {
	   return new Ziplocation(
          getZipcode(),
          getCity(),
          getState()
	   );
	}

    /**
    * display semanticReference with attribute inside class
    */
	public String display() {
	  StringBuffer sb = new StringBuffer();
      if (this.getCity()!=null)
         sb.append (this.getCity() +" " ); 
      if (this.getState()!=null)
         sb.append (this.getState() +" " ); 
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
            .append("zipcode", zipcode)
            .append("city", city)
            .append("state", state)
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
		if (!(object instanceof Ziplocation)) return false;
		Ziplocation ziplocation = (Ziplocation) object;
		if (ziplocation.zipcode==null || !ziplocation.zipcode.equals(zipcode)) return false;
		return true;
	}    

	public Ziplocation clone() {
        Ziplocation ziplocation = flat();
        return ziplocation;
	}
	
	public void copy (Ziplocation ziplocation) {
		if (ziplocation!=null) {
			setZipcode (ziplocation.getZipcode());
			setCity (ziplocation.getCity());
			setState (ziplocation.getState());
		}
	}
	
	public static Ziplocation fullMask() {
		return new Ziplocation(
			integerMask__ ,
			stringMask__ ,
			stringMask__ 		);
	}

    public Ziplocation maskString(Map<String, String> filter) {
        for (Entry<String, String> set : filter.entrySet()) {
            mask(set.getKey(), getEntry(set.getKey(), set.getValue()));
        }
        return this;
    }
    
    public Object getEntry(String pattern, String value) {
        if(pattern==null || value==null) return null;
    	if ("zipcode".equals(pattern))
           return Integer.valueOf(value);
        if ("city".equals(pattern))
           return value;
        if ("state".equals(pattern))
           return value;
        return null;
    }	
				
    public Ziplocation mask(Map<String, Object> filter) {
        for (Entry<String, Object> set : filter.entrySet()) {
            mask(set.getKey(), set.getValue());
        }
        return this;
    }
    
    public Ziplocation mask(String pattern, Object value) {
        if(pattern==null || value==null) return this;
		if ("zipcode".equals(pattern)) {
           setZipcode((Integer)value);
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
        return this;
    }

    public Ziplocation mask(String pattern) {
        if(pattern==null) return this;
        if ("city".equals(pattern))
           setCity(stringMask__);
        if ("state".equals(pattern))
           setState(stringMask__);
        return this;
    }

	public void assignNullToBlank () {
        if (StringUtils.isEmpty(getCity()))
           setCity(null);
        if (StringUtils.isEmpty(getState()))
           setState(null);
	}

    public Integer getZipcode() {
        return zipcode;
    }
	
    public void setZipcode (Integer zipcode) {
        this.zipcode =  zipcode;
    }
    
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






//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
