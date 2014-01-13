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

import net.sf.mp.demo.petshop.sdd.out.statement.GetAddressesByCriteriaOut;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
/**
 *
 * <p>Title: GetAddressesByCriteriaOutList</p>
 *
 * <p>Description: Java Bean GetAddressesByCriteriaOutList </p>
 *
 */
@XmlRootElement (name="GetAddressesByCriteriaOutList")
public class GetAddressesByCriteriaOutList {

    @XmlElement (name="GetAddressesByCriteriaOut") //(name="getaddressesbycriteriaouts")
    private List<GetAddressesByCriteriaOut> getAddressesByCriteriaOuts;

    /**
    * Default constructor
    */
    public GetAddressesByCriteriaOutList() {
    }
	
    public void setGetAddressesByCriteriaOuts (List<GetAddressesByCriteriaOut> getAddressesByCriteriaOuts) {
        this.getAddressesByCriteriaOuts = getAddressesByCriteriaOuts;
    }

    @XmlTransient
    public List<GetAddressesByCriteriaOut> getGetAddressesByCriteriaOuts () {
        if (getAddressesByCriteriaOuts==null)
            getAddressesByCriteriaOuts = new ArrayList<GetAddressesByCriteriaOut>();
        return getAddressesByCriteriaOuts;
    }

    public void addGetAddressesByCriteriaOut (GetAddressesByCriteriaOut getAddressesByCriteriaOut) {
        getGetAddressesByCriteriaOuts ().add(getAddressesByCriteriaOut);
    }


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@
}
