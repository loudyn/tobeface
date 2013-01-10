package com.tobeface.modules.drools.test;

import org.drools.builder.ResourceType;
import org.drools.runtime.StatelessKnowledgeSession;
import org.junit.Test;

import com.tobeface.modules.drools.Droolses;

public class StatelessfulSessionTest {

	@Test
	public void test() {
		StatelessKnowledgeSession session = Droolses.newStateless("com/tobeface/modules/drools/test/driver.drl", ResourceType.DRL);
		Driver driver = new Driver();
		driver.setName("loudyn");
		driver.setAge(17);
		session.execute(driver);
		System.out.println(driver.isCanDrive());
	}

	public static class Driver {
		private String name;
		private int age;
		private boolean canDrive = true;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public boolean isCanDrive() {
			return canDrive;
		}

		public void setCanDrive(boolean canDrive) {
			this.canDrive = canDrive;
		}
	}
}
