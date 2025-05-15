package org.cardinality.tests.Timeline;

import org.cardinality.base.BaseTest;
import org.cardinality.pages.ApplicantInfoPage;
import org.cardinality.pages.TimelinePage;
import org.testng.annotations.Test;

public class TC_01_ApplicationIdCreation extends BaseTest{

     private static final String URL = "https://gcm-dev.cardinality.info/pages/dynamic-routing/tab/1310/null/custom/timeline/1344"; 
    
    @Test

    public void checkApplicationStatus(){
        page.navigate(URL);
        TimelinePage timelinePage = new TimelinePage(page);
         ApplicantInfoPage.clickStartApplicationButton();

        timelinePage.applicationStatusVerify();
        timelinePage.applicationID();
    }   
}