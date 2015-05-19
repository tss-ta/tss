package com.netcracker.util;

import java.util.concurrent.atomic.AtomicBoolean;

public class GlobalVariables {
	public static String entityLookup = "java:jboss/EntityManagerFactory";
	public static final AtomicBoolean MAILER_STATE = new AtomicBoolean(false);
}
