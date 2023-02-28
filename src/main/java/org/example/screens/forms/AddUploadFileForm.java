package org.example.screens.forms;

import org.openqa.selenium.By;

public class AddUploadFileForm extends BaseForm {
    public AddUploadFileForm() {
        super(By.id("com.nextcloud.client:id/add_to_cloud"), "Add or upload file Form");
    }
}
