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

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

import net.sf.mp.demo.petshop.sdd.out.statement.GetAddressAbstractOutList;
import net.sf.mp.demo.petshop.sdd.out.statement.GetAddressAbstractOut;
import net.sf.mp.demo.petshop.sdd.in.statement.GetAddressAbstractIn;
import net.sf.mp.demo.petshop.service.face.statement.GetAddressAbstractService;
/**
 *
 * <p>Title: GetAddressAbstractController</p>
 *
 * <p>Description: remote interface for GetAddressAbstractController service </p>
 *
 */
@ManagedBean (name="getAddressAbstractController")
@RequestScoped
public class GetAddressAbstractController {

    @ManagedProperty("#{getAddressAbstractService}")
    GetAddressAbstractService getAddressAbstractService;

    private GetAddressAbstractIn getAddressAbstractIn = new GetAddressAbstractIn();
    private GetAddressAbstractOutList getAddressAbstractOutList;
    private GetAddressAbstractOut selectedGetAddressAbstractOut;

    private PieChartModel pieModel;
    public PieChartModel getPieModel() {  
        return pieModel;  
    }  
    
    public PieChartModel createPieModel() {
        execute();
        pieModel = new PieChartModel();  
        for (GetAddressAbstractOut var : getOutputList()) {
            pieModel.set(var.getCity(), Integer.valueOf(var.getNb()));
        }
        return pieModel;
    }


//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @SDD_EXECUTE-get address abstract@
    public GetAddressAbstractOutList execute () {
        getAddressAbstractOutList = getAddressAbstractService.execute(getAddressAbstractIn);
        return getAddressAbstractOutList;
    }
//MP-MANAGED-UPDATABLE-ENDING

    public List<GetAddressAbstractOut> getOutputList() {
        return (getAddressAbstractOutList==null)?new ArrayList<GetAddressAbstractOut>():getAddressAbstractOutList.getGetAddressAbstractOuts();
    }

    public GetAddressAbstractIn getGetAddressAbstractIn () {
        return getAddressAbstractIn;
    }

    public void setGetAddressAbstractIn (GetAddressAbstractIn getAddressAbstractIn) {
        this.getAddressAbstractIn = getAddressAbstractIn;
    }

    public GetAddressAbstractService getGetAddressAbstractService () {
        return getAddressAbstractService;
    }

    public void setGetAddressAbstractService (GetAddressAbstractService getAddressAbstractService) {
        this.getAddressAbstractService = getAddressAbstractService;
    }

    public GetAddressAbstractOut getSelectedGetAddressAbstractOut () {
        return selectedGetAddressAbstractOut;
    }

    public void setSelectedGetAddressAbstractOut (GetAddressAbstractOut selectedGetAddressAbstractOut) {
        this.selectedGetAddressAbstractOut = selectedGetAddressAbstractOut;
    }


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}