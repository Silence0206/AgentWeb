package com.agent;
import java.io.IOException;
import java.util.Scanner;

import com.bean.ClassCreate;

import jade.core.AID;
import jade.core.Agent;
import jade.core.Location;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

public class FirstAgent extends Agent{
	/**
	 * Agent自动创建类
	 */
	 Scanner s = new Scanner(System.in);
	public ClassCreate createclass(){
		ClassCreate classcreate=new ClassCreate();
		
		System.out.println("请输入您想创建的类名，字段，类型!");
		String[] strs=s.next().split(",");
		classcreate.setClassname(strs[0]);
		 System.out.println("开始拼接："+classcreate.getClassname()+"的sql");
	      // System.out.println(s.next());
		 String sql="CREATE TABLE "+classcreate.getClassname()+"(";
		 for(int i=1;i<strs.length;){
			 sql=sql+strs[i]+" "+strs[i+1]+",";
			 i+=2;
		 }
		 sql=sql.substring(0,sql.length()-1)+")";
		 classcreate.setSql(sql);
		 return classcreate;
//		 System.out.println(classcreate.getClassname()+"||"+classcreate.getSql());
	}
	/**
	 * 学习用的
	 * @param classcreate
	 */
	public void sendmsg1(ClassCreate classcreate){
		doWait(5000);
		ClassCreate ex=new ClassCreate("sa","dd");
		ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
		//msg.addReceiver(new AID("SecondAgent", AID.ISLOCALNAME));
		AID r=new AID();
		r.setLocalName("Second");
		msg.setSender(getAID());
		msg.addReceiver(r);
		msg.setContent("lalla");
		send(msg);
		System.out.println("FirstAgent 需要创建类: "+msg.getContent());
		doWait(5000);
		
	}
	
	public void sendmsg(ClassCreate ex) throws IOException{
		doWait(5000);
		ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
		msg.addReceiver(new AID("Second", AID.ISLOCALNAME));
		msg.setSender(getAID());
		 msg.setContentObject(ex);
         msg.setLanguage("JavaSerialization");
         send(msg);
        // System.out.println(getLocalName()+" sent 1st msg "+msg);
         
	}
	
	public void setup(){
		SimpleBehaviour hello_behaviour = new SimpleBehaviour(this) {
			boolean finished = false;

			public void action() {			
				doWait(5000);
				try {
					ClassCreate createclass =createclass();
					sendmsg(createclass);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//finished = true; //去掉这句话这个action才能循环执行
			}

			public boolean done() {
				return finished;
			}
		};
		this.addBehaviour(hello_behaviour);
	}
	

}
