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
	* - name      : SDDOutputBeanCollection
	* - file name : JavaBeanCollection.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/
package net.sf.mp.demo.petshop.sdd.out.statement;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.*;

import net.sf.mp.demo.petshop.sdd.out.statement.GetAddressAbstractOut;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
/**
 *
 * <p>Title: GetAddressAbstractOutList</p>
 *
 * <p>Description: Java Bean GetAddressAbstractOutList </p>
 *
 */
@XmlRootElement (name="GetAddressAbstractOutList")
public class GetAddressAbstractOutList {

    @XmlElement (name="GetAddressAbstractOut") //(name="getaddressabstractouts")
    private List<GetAddressAbstractOut> getAddressAbstractOuts;

    /**
    * Default constructor
    */
    public GetAddressAbstractOutList() {
    }
	
    public void setGetAddressAbstractOuts (List<GetAddressAbstractOut> getAddressAbstractOuts) {
        this.getAddressAbstractOuts = getAddressAbstractOuts;
    }

    @XmlTransient
    public List<GetAddressAbstractOut> getGetAddressAbstractOuts () {
        if (getAddressAbstractOuts==null)
            getAddressAbstractOuts = new ArrayList<GetAddressAbstractOut>();
        return getAddressAbstractOuts;
    }

    public void addGetAddressAbstractOut (GetAddressAbstractOut getAddressAbstractOut) {
        getGetAddressAbstractOuts ().add(getAddressAbstractOut);
    }


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
