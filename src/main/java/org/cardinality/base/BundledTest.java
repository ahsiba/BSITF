package org.cardinality.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;

import java.io.IOException;

public class BundledTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BundledTest.class);

    @AfterClass
    public void classTearDown() throws IOException {
        logger.info("Completing execution of all tests in the class");
        page.context().browser().close();
    }
}
