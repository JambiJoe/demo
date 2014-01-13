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
	* - name      : SDDSpringJPADao
	* - file name : SDDSpringJPADao.vm
	* - time      : 2014/01/11 ap. J.-C. at 23:51:20 CET
*/
package net.sf.mp.demo.petshop.dao.sdd.impl.statement;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.sf.mp.demo.petshop.sdd.out.statement.GetAddressSummaryOutList;
import net.sf.mp.demo.petshop.sdd.out.statement.GetAddressSummaryOut;
import net.sf.mp.demo.petshop.dao.sdd.face.statement.GetAddressSummaryDaoFace;
/**
 *
 * <p>Title: GetAddressSummaryRepository</p>
 *
 * <p>Description: SDD DAO Spring JPA implementation </p>
 *
 */
@Repository ("getAddressSummaryDaoFace")
@Transactional(propagation = Propagation.REQUIRED) 
public class GetAddressSummaryRepository implements GetAddressSummaryDaoFace{

	public static final String QUERY_NATIVE = "select city, count(*) as nb, count(*) as nb2 from address group by city order by count(*) desc";

	@PersistenceContext(unitName = "petshop")  
    EntityManager entityManager;  
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @SDD_EXECUTE_GET-get address summary@
    public GetAddressSummaryOutList execute () {
		GetAddressSummaryOutList getAddressSummaryOutList = new GetAddressSummaryOutList();
		List<GetAddressSummaryOut> list = executeJDBC ();
		getAddressSummaryOutList.setGetAddressSummaryOuts (list);
        return getAddressSummaryOutList;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @SDD_EXECUTE_JDBC-get address summary@
	public List<GetAddressSummaryOut> executeJDBC() {
		List<GetAddressSummaryOut> list = new ArrayList<GetAddressSummaryOut>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(QUERY_NATIVE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GetAddressSummaryOut getAddressSummaryOut = new GetAddressSummaryOut();
				getAddressSummaryOut.setCity(rs.getString(1)); 
				getAddressSummaryOut.setNb(rs.getString(2)); 
				getAddressSummaryOut.setNb2(rs.getString(3)); 
				list.add(getAddressSummaryOut);
	        }
		} catch (Exception e) {
		      e.printStackTrace();
	    } finally {
	      try {
	        rs.close();
	        pstmt.close();
	        conn.close();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
		return list;
	}
//MP-MANAGED-UPDATABLE-ENDING

//if JPA2 implementation is hibernate
	@SuppressWarnings("deprecation")   
   	public Connection getConnection() throws HibernateException {  
		Session session = getSession();  
		Connection connection = session.connection();  
		return connection;  
   	} 
	
   	private Session getSession() {  
   		Session session = (Session) entityManager.getDelegate();  
   		return session;  
   	}
	
//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}