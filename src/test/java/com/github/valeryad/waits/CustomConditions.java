package com.github.valeryad.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomConditions {
    public static ExpectedCondition<Boolean> elementContainsEmailAddress(By by) {
        return new ElementContainsEmailAddressCondition(by);
    }

    public static ExpectedCondition<Boolean> pickedRightOption(String locatorPattern, String key) {
        return new PickedRightOptionCondition(locatorPattern, key);
    }
}
