package com.agent;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.ClassCreate;
import com.db.DBHelper;

/**
 * ѭ��������Ϣ
 */
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;

public class SecondAgent extends Agent {
	static String sql = null;
	static DBHelper db1 = null;
	
	
	public void dosql(ClassCreate ex)
	{
		sql = ex.sql;//SQL���
		db1 = new DBHelper(sql);//����DBHelper����

		try {
			int result=db1.pst.executeUpdate();//ִ����䣬�õ������
			if(result==0){
				System.out.println("��"+ex.classname+"�Ĵ������"+ex.sql+"ִ�����");
			}
			db1.close();//�ر�����
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
					 ACLMessage msg = blockingReceive();//����agent������ֱ������Ϣ
					if ("JavaSerialization".equals(msg.getLanguage())) {
						ClassCreate p;
						p = (ClassCreate) msg.getContentObject();
						System.out.println(getLocalName() + " Agent�ѻ�ȡ��Java���� ");
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
