package com.qa.opencart.listeners;

import java.lang.reflect.Constructor;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import aj.org.objectweb.asm.commons.Method;

public class AnnotationTransformer implements IAnnotationTransformer
{
	public void transform(ITestAnnotation annotation, 
			Class testClass,
			Constructor testConstructor,
			Method testMethod) 
	{
		annotation.setRetryAnalyzer(Retry.class);
		
	}

}
