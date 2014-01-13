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
	* - name      : SDDSpringServiceImpl
	* - file name : SDDSpringServiceImpl.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/
package net.sf.mp.demo.petshop.service.impl.statement;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.sf.mp.demo.petshop.sdd.out.statement.GetAddressesByCriteriaOutList;
import net.sf.mp.demo.petshop.sdd.out.statement.GetAddressesByCriteriaOut;
import net.sf.mp.demo.petshop.sdd.in.statement.GetAddressesByCriteriaIn;
import net.sf.mp.demo.petshop.dao.sdd.face.statement.GetAddressesByCriteriaDaoFace;
import net.sf.mp.demo.petshop.service.face.statement.GetAddressesByCriteriaService;

/**
 *
 * <p>Title: GetAddressesByCriteriaServiceImpl</p>
 *
 * <p>Description: SDD Spring service implementation </p>
 *
 */
@Service ("getAddressesByCriteriaService")
@Transactional
public class GetAddressesByCriteriaServiceImpl implements GetAddressesByCriteriaService{
    @Autowired
    @Qualifier("getAddressesByCriteriaDaoFace")
    GetAddressesByCriteriaDaoFace getAddressesByCriteriaDaoFace;
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @SDD_EXECUTE_GET-get addresses by criteria@
    public GetAddressesByCriteriaOutList execute (GetAddressesByCriteriaIn getAddressesByCriteriaIn) {
        return getAddressesByCriteriaDaoFace.execute (getAddressesByCriteriaIn);
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}