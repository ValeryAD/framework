package com.github.valeryad.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatorResultsTest extends SmokeTest {

    private final static String EXPECTED_VM_CLASS_RESULT_PATTERN = "VM class: %s";
    private final static String EXPECTED_INSTANCE_TYPE_RESULT_PATTERN = "Instance type: %s";
    private final static String EXPECTED_REGION_RESULT_PATTERN = "Region: %s";
    private final static String EXPECTED_LOCAL_SSD_RESULT_PATTERN = "Total available local SSD space %s GiB";
    private final static String EXPECTED_COMMITTED_USAGE_RESULT_PATTERN = "Commitment term: %s";


    @Test(description = "\"VM class\" section in estimation results should be equal to vm class that has been pointed out",
            dependsOnMethods = {"canGetResultFromCloudCalculator"})
    public void vmClassShouldBeEqualToGivenOne() {
        Assert.assertEquals(estimateResultsPage.readResultsVMClass(),
                String.format(EXPECTED_VM_CLASS_RESULT_PATTERN, machine.getVmClass().toLowerCase()));
    }

    @Test(description = "\"Instance type\" section in estimation results should be equal to instance type that has been pointed out",
            dependsOnMethods = {"canGetResultFromCloudCalculator"})
    public void instanceTypeShouldBeEqualToGivenOne() {
        Assert.assertEquals(estimateResultsPage.readResultsInstanceType(),
                String.format(EXPECTED_INSTANCE_TYPE_RESULT_PATTERN, machine.getInstanceType()));
    }

    @Test(description = "\"Region\" section in estimation results should be equal to region that has been pointed out",
            dependsOnMethods = {"canGetResultFromCloudCalculator"})
    public void regionShouldBeEqualToGivenOn() {
        Assert.assertEquals(estimateResultsPage.readResultsRegion(),
                String.format(EXPECTED_REGION_RESULT_PATTERN, machine.getRegion()));
    }

    @Test(description = "\"Total available local SSD space\" section in estimation results should be equal to local SSD option that has been pointed out",
            dependsOnMethods = {"canGetResultFromCloudCalculator"})
    public void localSSDShouldBeEqualToGivenOne() {
        Assert.assertEquals(estimateResultsPage.readResultsLocalSSDinfo(),
                String.format(EXPECTED_LOCAL_SSD_RESULT_PATTERN, machine.getLocalSSD()));
    }

    @Test(description = "\"Commitment term:\" section in estimation results should be equal to commitment term option that has been pointed out",
            dependsOnMethods = {"canGetResultFromCloudCalculator"})
    public void commitmentTermSholeBeEqualToGivenOne() {
        Assert.assertEquals(estimateResultsPage.readResultsCommitmentTerm(),
                String.format(EXPECTED_COMMITTED_USAGE_RESULT_PATTERN, machine.getCommittedUsage()));
    }
}

