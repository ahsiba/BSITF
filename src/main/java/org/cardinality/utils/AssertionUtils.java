package org.cardinality.utils;

import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Collectors;

public class AssertionUtils {

    // For hard assertion (Assert)
    public static void assertAndLogTrue(boolean condition, String fieldName, Logger logger) {
        logger.info("{} is expected to be true. Actual: {}", fieldName, condition);
        Assert.assertTrue(condition, fieldName + " should be true");
    }

    // For soft assertion (SoftAssert)
    public static void assertAndLogTrue(SoftAssert softAssert, boolean condition, String fieldName, Logger logger) {
        logger.info("{} is expected to be true. Actual: {}", fieldName, condition);
        softAssert.assertTrue(condition, fieldName + " should be true");
    }

    // Existing assertAndLogEquals...
    public static void assertAndLogEquals(SoftAssert softAssert, String actual, String expected,
                                          String fieldName, Logger logger) {
        logger.info("{} - Expected: {}, Actual: {}", fieldName, expected, actual);
        softAssert.assertEquals(actual, expected, fieldName + " mismatch");
    }

    public static boolean assertSearchResult(List<String> results, String searchTerm, String expectedValue,
                                             boolean expectResults, Logger logger) {
        boolean conditionMet = evaluateCondition(results, searchTerm, expectedValue, expectResults, logger);
        String message = buildMessage(conditionMet, searchTerm, expectedValue);
        logger.info(message);
        return conditionMet;
    }

    public static boolean evaluateCondition(List<String> results, String searchTerm,
                                            String expectedValue, boolean expectResults, Logger logger) {
        String expectedLower = expectedValue.toLowerCase();
        List<String> lowerResults = results.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        if (lowerResults.isEmpty()) {
            return !expectResults;
        }

        if (!expectResults) {
            return false;
        }

        switch (searchTerm.toLowerCase()) {
            case "contains":
                return lowerResults.stream().anyMatch(val -> val.contains(expectedLower));
            case "does not contain":
                return lowerResults.stream().noneMatch(val -> val.contains(expectedLower));
            case "starts with":
                return lowerResults.stream().allMatch(val -> val.startsWith(expectedLower));
            case "ends with":
                return lowerResults.stream().allMatch(val -> val.endsWith(expectedLower));
            case "equals":
                return lowerResults.stream().allMatch(val -> val.equals(expectedLower));
            case "does not equal":
                return lowerResults.stream().noneMatch(val -> val.equals(expectedLower));
            default:
                logger.error("❌ Unknown search term: {}", searchTerm);
                return false;
        }
    }
    private static String buildMessage(boolean conditionMet, String searchTerm, String expectedValue) {
        return (conditionMet ? "✅ " : "❌ ") +
                "Search results validated for filter [" + searchTerm + "] with value [" + expectedValue + "]";
    }
}