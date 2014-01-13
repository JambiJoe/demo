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
	* - name      : SDDOutputBean
	* - file name : JavaBean.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/
package net.sf.mp.demo.petshop.sdd.out.statement;

import java.util.List;
import java.util.ArrayList;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
/**
 *
 * <p>Title: GetAddressesByCriteriaOut</p>
 *
 * <p>Description: Java Bean containing a collection of GetAddressesByCriteria </p>
 *
 */
public class GetAddressesByCriteriaOut {

    private Integer addressid;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private java.math.BigDecimal latitude;
    private java.math.BigDecimal longitude;

    /**
    * Default constructor
    */
    public GetAddressesByCriteriaOut() {
    }
	
    public Integer getAddressid() {
        return addressid;
    }
    public void setAddressid (Integer addressid) {
        this.addressid =  addressid;
    }

    public String getStreet1() {
        return street1;
    }
    public void setStreet1 (String street1) {
        this.street1 =  street1;
    }

    public String getStreet2() {
        return street2;
    }
    public void setStreet2 (String street2) {
        this.street2 =  street2;
    }

    public String getCity() {
        return city;
    }
    public void setCity (String city) {
        this.city =  city;
    }

    public String getState() {
        return state;
    }
    public void setState (String state) {
        this.state =  state;
    }

    public String getZip() {
        return zip;
    }
    public void setZip (String zip) {
        this.zip =  zip;
    }

    public java.math.BigDecimal getLatitude() {
        return latitude;
    }
    public void setLatitude (java.math.BigDecimal latitude) {
        this.latitude =  latitude;
    }

    public java.math.BigDecimal getLongitude() {
        return longitude;
    }
    public void setLongitude (java.math.BigDecimal longitude) {
        this.longitude =  longitude;
    }


    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Addressid: "+getAddressid());
        sb.append(", ");
        sb.append("Street1: "+getStreet1());
        sb.append(", ");
        sb.append("Street2: "+getStreet2());
        sb.append(", ");
        sb.append("City: "+getCity());
        sb.append(", ");
        sb.append("State: "+getState());
        sb.append(", ");
        sb.append("Zip: "+getZip());
        sb.append(", ");
        sb.append("Latitude: "+getLatitude());
        sb.append(", ");
        sb.append("Longitude: "+getLongitude());
        return sb.toString();
    }

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
