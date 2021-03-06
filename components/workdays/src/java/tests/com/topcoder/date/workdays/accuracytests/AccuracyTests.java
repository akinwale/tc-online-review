/*
 * Copyright (c) 2010, TopCoder, Inc. All rights reserved
 */
package com.topcoder.date.workdays.accuracytests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * <p>
 * This test case aggregates all Accuracy test cases.
 * </p>
 *
 * @author TopCoder
 * @version 1.0
 */
public class AccuracyTests extends TestCase {

    public static Test suite() {
        final TestSuite suite = new TestSuite();
        suite.addTest(DefaultWorkdaysTestV11.suite());
        suite.addTest(DefaultWorkdaysFactoryTestV11.suite());
        suite.addTest(ConfigurationFileExceptionTest.suite());
        suite.addTest(DefaultWorkdaysTest.suite());
        suite.addTest(DefaultWorkdaysTestTwo.suite());
        suite.addTest(DefaultWorkdaysFactoryTest.suite());
        suite.addTest(WorkdaysUnitOfTimeTest.suite());
        return suite;
    }

}
