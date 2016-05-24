package com.agent;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.ClassCreate;
import com.db.DBHelper;

/**
 * 循环接受消息
 */
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;

public class SecondAgent extends Agent {
	static String sql = null;
	static DBHelper db1 = null;
	
	
	public void dosql(ClassCreate ex)
	{
		sql = ex.sql;//SQL语句
		db1 = new DBHelper(sql);//创建DBHelper对象

		try {
			int result=db1.pst.executeUpdate();//执行语句，得到结果集
			if(result==0){
				System.out.println("表"+ex.classname+"的创建语句"+ex.sql+"执行完毕");
			}
			db1.close();//关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void setup() {

		this.addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				/*
				 * ACLMessage msg=receive();
				 * 
				 * if(msg!=null){
				 * System.out.println("receive:"+msg.getContent()+msg.getSender(
				 * )); }
				 */

				try {
					 ACLMessage msg = blockingReceive();//整个agent被悬挂直到有消息
					if ("JavaSerialization".equals(msg.getLanguage())) {
						ClassCreate p;
						p = (ClassCreate) msg.getContentObject();
						System.out.println(getLocalName() + " Agent已获取到Java对象： ");
						System.out.println(p.classname+"|"+p.sql);
						dosql(p);
					}
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block

					e.printStackTrace();
				}

			}

		});
	}
}
