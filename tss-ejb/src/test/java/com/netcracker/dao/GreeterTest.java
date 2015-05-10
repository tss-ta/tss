package com.netcracker.dao;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.netcracker.util.PasswordsKeeper;

@RunWith(Arquillian.class)
public class GreeterTest {

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addClass(PasswordsKeeper.class);
	}

	@Test
	public void should_create_greeting() {
		Assert.assertEquals("Hello, Earthling!",
				"Hello, Earthling!");
	}
}