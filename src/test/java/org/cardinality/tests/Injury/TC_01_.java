package org.cardinality.tests.Injury;

import org.cardinality.base.BaseTest;
import org.cardinality.pages.InjuryPage;
import org.testng.annotations.Test;

public class TC_01_ extends BaseTest {

     private static final String URL = "https://gcm-dev.cardinality.info/pages/dynamic-search/search/1196"; // Replace with actual URL

  @Test
    public void verifyNatureInjurySection() {
        page.navigate(URL); // Replace with your actual URL
        InjuryPage injuryPage = new InjuryPage(page);
        injuryPage.verifyNatureInjurySection(); // ✅ This is non-static usage
    }

    @Test
    public void verifyCauseInjurySection() {
        page.navigate(URL); // Replace with your actual URL
        InjuryPage injuryPage = new InjuryPage(page);
        injuryPage.verifyCauseInjurySection(); // ✅ This is non-static usage
    }
}

