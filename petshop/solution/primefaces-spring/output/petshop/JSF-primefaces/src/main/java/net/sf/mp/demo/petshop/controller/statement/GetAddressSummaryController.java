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

import net.sf.mp.demo.petshop.sdd.out.statement.GetAddressSummaryOutList;
import net.sf.mp.demo.petshop.sdd.out.statement.GetAddressSummaryOut;
import net.sf.mp.demo.petshop.service.face.statement.GetAddressSummaryService;
/**
 *
 * <p>Title: GetAddressSummaryController</p>
 *
 * <p>Description: remote interface for GetAddressSummaryController service </p>
 *
 */
@ManagedBean (name="getAddressSummaryController")
@RequestScoped
public class GetAddressSummaryController {

    @ManagedProperty("#{getAddressSummaryService}")
    GetAddressSummaryService getAddressSummaryService;

    private GetAddressSummaryOutList getAddressSummaryOutList;
    private GetAddressSummaryOut selectedGetAddressSummaryOut;

    private CartesianChartModel categoryModel;  

    public CartesianChartModel getCategoryModel() {  
        return categoryModel;  
    }  

    public CartesianChartModel createCategoryModel() {  
        execute();
        categoryModel = new CartesianChartModel();  
        ChartSeries nb1 = new ChartSeries();  
        nb1.setLabel("Nb");
        for (GetAddressSummaryOut var : getOutputList()) {
            nb1.set(var.getCity(), Integer.valueOf(var.getNb2()));
        }
        categoryModel.addSeries(nb1);  
        ChartSeries nb22 = new ChartSeries();  
        nb22.setLabel("Nb2");
        for (GetAddressSummaryOut var : getOutputList()) {
            nb22.set(var.getCity(), Integer.valueOf(var.getNb2()));
        }
        categoryModel.addSeries(nb22);  
        return categoryModel;
    }
    

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @SDD_EXECUTE-get address summary@
    public GetAddressSummaryOutList execute () {
        getAddressSummaryOutList = getAddressSummaryService.execute();
        return getAddressSummaryOutList;
    }
//MP-MANAGED-UPDATABLE-ENDING

    public List<GetAddressSummaryOut> getOutputList() {
        return (getAddressSummaryOutList==null)?new ArrayList<GetAddressSummaryOut>():getAddressSummaryOutList.getGetAddressSummaryOuts();
    }

    public GetAddressSummaryService getGetAddressSummaryService () {
        return getAddressSummaryService;
    }

    public void setGetAddressSummaryService (GetAddressSummaryService getAddressSummaryService) {
        this.getAddressSummaryService = getAddressSummaryService;
    }

    public GetAddressSummaryOut getSelectedGetAddressSummaryOut () {
        return selectedGetAddressSummaryOut;
    }

    public void setSelectedGetAddressSummaryOut (GetAddressSummaryOut selectedGetAddressSummaryOut) {
        this.selectedGetAddressSummaryOut = selectedGetAddressSummaryOut;
    }


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}