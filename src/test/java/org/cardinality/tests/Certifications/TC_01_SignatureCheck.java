package org.cardinality.tests.Certifications;

import org.cardinality.base.BaseTest;
import org.cardinality.pages.CertificationsPage;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

public class TC_01_SignatureCheck {
    private static final String URL = "https://gcm-dev.cardinality.info/pages/dynamic-routing/tab/1366/193/page/10"; // Replace with actual URL

    @Test
    public void SignatureCheck() {
        page.navigate(URL); 
        CertificationsPage Certificate=new CertificationsPage(Page);

        Certificate.enableSubmitCheckBox()
    }
}

