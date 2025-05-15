package org.cardinality.tests.Notes;

import org.cardinality.base.BaseTest;
import org.cardinality.pages.NotesPage;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

public class TC_01_MaximumCharacter {
    public static final String URL = "https://gcm-dev.cardinality.info/pages/dynamic-routing/tab/1310/2251/custom/notes/1346/notes-list";

    @Test
    public void checkTextEnter() {
        page.navigate(URL);
        NotesPage notesPage = new NotesPage(page);

        notesPage.enterMaximumText();
        notesPage.saveNotesBtn();
    }
}