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
 * <p>Title: GetAddressSummaryOut</p>
 *
 * <p>Description: Java Bean containing a collection of GetAddressSummary </p>
 *
 */
public class GetAddressSummaryOut {

    private String city;
    private String nb;
    private String nb2;

    /**
    * Default constructor
    */
    public GetAddressSummaryOut() {
    }
	
    public String getCity() {
        return city;
    }
    public void setCity (String city) {
        this.city =  city;
    }

    public String getNb() {
        return nb;
    }
    public void setNb (String nb) {
        this.nb =  nb;
    }

    public String getNb2() {
        return nb2;
    }
    public void setNb2 (String nb2) {
        this.nb2 =  nb2;
    }


    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("City: "+getCity());
        sb.append(", ");
        sb.append("Nb: "+getNb());
        sb.append(", ");
        sb.append("Nb2: "+getNb2());
        return sb.toString();
    }

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
