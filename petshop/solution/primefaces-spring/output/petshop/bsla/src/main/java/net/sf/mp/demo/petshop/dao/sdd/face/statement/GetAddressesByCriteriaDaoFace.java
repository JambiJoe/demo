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
	* - name      : SDDDaoInterface
	* - file name : SDDDaoInterface.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:20 CET
*/
package net.sf.mp.demo.petshop.dao.sdd.face.statement;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

import net.sf.mp.demo.petshop.sdd.out.statement.GetAddressesByCriteriaOutList;
import net.sf.mp.demo.petshop.sdd.in.statement.GetAddressesByCriteriaIn;

/**
 *
 * <p>Title: GetAddressesByCriteriaDaoFace</p>
 *
 * <p>Description: remote interface for GetAddressesByCriteriaDaoFace service </p>
 *
 */
public interface GetAddressesByCriteriaDaoFace {

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @SDD_EXECUTE_GET-get addresses by criteria@
    public GetAddressesByCriteriaOutList execute (GetAddressesByCriteriaIn getAddressesByCriteriaIn) ;
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}