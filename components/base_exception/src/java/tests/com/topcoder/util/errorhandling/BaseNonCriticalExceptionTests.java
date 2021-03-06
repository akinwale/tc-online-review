/*
 * Copyright (C) 2007 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.util.errorhandling;

import junit.framework.TestCase;

/**
 * Unit test for the <code>BaseNonCriticalException</code> class.
 *
 * @author TCSDEVELOPER
 * @version 2.0
 */
public class BaseNonCriticalExceptionTests extends TestCase {
    /**
     * Represents the <code>ExceptionData</code> instance used to test
     * against.
     */
    private ExceptionData data;

    /**
     * Represents the <code>Throwable</code> instance used to test against.
     */
    private Throwable throwable;

    /**
     * Set up the test environment.
     */
    protected void setUp() {
        data = new ExceptionData();
        throwable = new NullPointerException();
    }

    /**
     * Accuracy test for the constructor <code>BaseNonCriticalException()</code>.
     * Instance should be created successfully.
     */
    public void testCtor1() {
        BaseNonCriticalException exception = new BaseNonCriticalException();
        assertNotNull("Instance should be created", exception);
        assertTrue("BaseNonCriticaleException should subclass BaseException", exception instanceof BaseException);
    }

    /**
     * Accuracy test for the constructor
     * <code>BaseNonCriticalException(ExceptionData)</code>. Instance should be
     * created successfully.
     */
    public void testCtor2() {
        BaseNonCriticalException exception = new BaseNonCriticalException(data);
        assertNotNull("Instance should be created", exception);
        assertTrue("BaseNonCriticalException should subclass BaseException", exception instanceof BaseException);
        ExceptionData nullData = null;
        exception = new BaseNonCriticalException(nullData);
    }

    /**
     * Accuracy test for the constructor
     * <code>BaseNonCriticalException(String)</code>. Instance should be created
     * successfully.
     */
    public void testCtor3() {
        BaseNonCriticalException exception = new BaseNonCriticalException("test");
        assertNotNull("Instance should be created", exception);
        assertTrue("BaseNonCriticaleException should subclass BaseException", exception instanceof BaseException);
        assertEquals("Return value should be 'test'", "test", exception.getMessage());
    }

    /**
     * Accuracy test for the constructor
     * <code>BaseNonCriticalException(String,ExceptionData)</code>. Instance
     * should be created successfully.
     */
    public void testCtor4() {
        BaseNonCriticalException exception = new BaseNonCriticalException("test", data);
        assertNotNull("Instance should be created", exception);
        assertTrue("BaseNonCriticalException should subclass BaseException", exception instanceof BaseException);
        assertEquals("Return value should be 'test'", "test", exception.getMessage());
        ExceptionData nullData = null;
        exception = new BaseNonCriticalException("test", nullData);
    }

    /**
     * Accuracy test for the constructor
     * <code>BaseNonCriticalException(String,Throwable)</code>. Instance should
     * be created successfully.
     */
    public void testCtor5() {
        BaseNonCriticalException exception = new BaseNonCriticalException("test", throwable);
        assertNotNull("Instance should be created", exception);
        assertTrue("BasNonCriticalException should subclass BaseException", exception instanceof BaseException);
        assertEquals("Return value should be 'test'", "test", exception.getMessage());
        assertEquals("Cause should be set correctly", throwable, exception.getCause());
    }

    /**
     * Accuracy test for the constructor
     * <code>BaseNonCriticalException(String,Throwable,ExceptionData)</code>.
     * Instance should be created successfully.
     */
    public void testCtor6() {
        BaseNonCriticalException exception = new BaseNonCriticalException("test", throwable, data);
        assertNotNull("Instance should be created", exception);
        assertTrue("BaseNonCriticalException should subclass BaseException", exception instanceof BaseException);
        assertEquals("Return value should be 'test'", "test", exception.getMessage());
        assertEquals("Cause should be set correctly", throwable, exception.getCause());
        ExceptionData nullData = null;
        exception = new BaseNonCriticalException("test", throwable, nullData);
    }

    /**
     * Accuracy test for the constructor
     * <code>BaseNonCriticalException(Throwable)</code>. Instance should be
     * created successfully.
     */
    public void testCtor7() {
        BaseNonCriticalException exception = new BaseNonCriticalException(throwable);
        assertNotNull("Instance should be created", exception);
        assertTrue("BasNonCriticalException should subclass BaseException", exception instanceof BaseException);
        assertEquals("Cause should be set correctly", throwable, exception.getCause());
    }

    /**
     * Accuracy test for the constructor
     * <code>BaseNonCriticalException(Throwable,ExceptionData)</code>. Instance
     * should be created successfully.
     */
    public void testCtor8() {
        BaseNonCriticalException exception = new BaseNonCriticalException(throwable, data);
        assertNotNull("Instance should be created", exception);
        assertTrue("BaseNonCriticalException should subclass BaseException", exception instanceof BaseException);
        assertEquals("Cause should be set correctly", throwable, exception.getCause());
        ExceptionData nullData = null;
        exception = new BaseNonCriticalException(throwable, nullData);
    }

}
