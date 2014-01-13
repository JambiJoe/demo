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
	* - name      : JSFSDDController
	* - file name : JSFSDDController.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:21 CET
*/
package net.sf.mp.demo.petshop.controller.statement;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.sql.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


import net.sf.mp.demo.petshop.sdd.out.statement.GetAddressesByCriteriaOutList;
import net.sf.mp.demo.petshop.sdd.out.statement.GetAddressesByCriteriaOut;
import net.sf.mp.demo.petshop.sdd.in.statement.GetAddressesByCriteriaIn;
import net.sf.mp.demo.petshop.service.face.statement.GetAddressesByCriteriaService;
/**
 *
 * <p>Title: GetAddressesByCriteriaController</p>
 *
 * <p>Description: remote interface for GetAddressesByCriteriaController service </p>
 *
 */
@ManagedBean (name="getAddressesByCriteriaController")
@RequestScoped
public class GetAddressesByCriteriaController {

    @ManagedProperty("#{getAddressesByCriteriaService}")
    GetAddressesByCriteriaService getAddressesByCriteriaService;

    private GetAddressesByCriteriaIn getAddressesByCriteriaIn = new GetAddressesByCriteriaIn();
    private GetAddressesByCriteriaOutList getAddressesByCriteriaOutList;
    private GetAddressesByCriteriaOut selectedGetAddressesByCriteriaOut;


//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @SDD_EXECUTE-get addresses by criteria@
    public GetAddressesByCriteriaOutList execute () {
        getAddressesByCriteriaOutList = getAddressesByCriteriaService.execute(getAddressesByCriteriaIn);
        return getAddressesByCriteriaOutList;
    }
//MP-MANAGED-UPDATABLE-ENDING

    public List<GetAddressesByCriteriaOut> getOutputList() {
        return (getAddressesByCriteriaOutList==null)?new ArrayList<GetAddressesByCriteriaOut>():getAddressesByCriteriaOutList.getGetAddressesByCriteriaOuts();
    }

    public GetAddressesByCriteriaIn getGetAddressesByCriteriaIn () {
        return getAddressesByCriteriaIn;
    }

    public void setGetAddressesByCriteriaIn (GetAddressesByCriteriaIn getAddressesByCriteriaIn) {
        this.getAddressesByCriteriaIn = getAddressesByCriteriaIn;
    }

    public GetAddressesByCriteriaService getGetAddressesByCriteriaService () {
        return getAddressesByCriteriaService;
    }

    public void setGetAddressesByCriteriaService (GetAddressesByCriteriaService getAddressesByCriteriaService) {
        this.getAddressesByCriteriaService = getAddressesByCriteriaService;
    }

    public GetAddressesByCriteriaOut getSelectedGetAddressesByCriteriaOut () {
        return selectedGetAddressesByCriteriaOut;
    }

    public void setSelectedGetAddressesByCriteriaOut (GetAddressesByCriteriaOut selectedGetAddressesByCriteriaOut) {
        this.selectedGetAddressesByCriteriaOut = selectedGetAddressesByCriteriaOut;
    }


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}