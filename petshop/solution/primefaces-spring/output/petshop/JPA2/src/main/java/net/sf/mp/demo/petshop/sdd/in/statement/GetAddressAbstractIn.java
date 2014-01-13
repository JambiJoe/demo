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
	* - name      : SDDInputBean
	* - file name : JavaBean.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/
package net.sf.mp.demo.petshop.sdd.in.statement;

import java.util.List;
import java.util.ArrayList;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
/**
 *
 * <p>Title: GetAddressAbstractIn</p>
 *
 * <p>Description: Java Bean containing a collection of GetAddressAbstract </p>
 *
 */
public class GetAddressAbstractIn {

    private final Integer __DEFAULT_TOP_CITY = Integer.valueOf(10);
    private Integer topCity;

    /**
    * Default constructor
    */
    public GetAddressAbstractIn() {
    }
	
    public Integer getTopCity() {
        return (topCity!=null)?topCity:__DEFAULT_TOP_CITY;
    }
    public void setTopCity (Integer topCity) {
        this.topCity =  topCity;
    }


    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("TopCity: "+getTopCity());
        return sb.toString();
    }

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
