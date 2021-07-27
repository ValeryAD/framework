package com.github.valeryad.service;

import com.github.valeryad.model.Machine;

public class MachineCreator {

    private static final String TEST_DATA_NUMBER_INSTANCES = "testdata.machine.numberInstances";
    private static final String TEST_DATA_OS_AND_SOFTWARE = "testdata.machine.osAndSoftware";
    private static final String TEST_DATA_VM_CLASS = "testdata.machine.vmClass";
    private static final String TEST_DATA_SERIES = "testdata.machine.series";
    private static final String TEST_DATA_TYPE = "testdata.machine.type";
    private static final String TEST_DATA_NUMBER_GPUS = "testdata.machine.numberGPUs";
    private static final String TEST_DATA_GPU_TYPE = "testdata.machine.gpuType";
    private static final String TEST_DATA_LOCAL_SSD = "testdata.machine.localSSD";
    private static final String TEST_DATA_REGION = "testdata.machine.region";
    private static final String TEST_DATA_COMMITTED_USAGE = "testdata.machine.committedUsage";

    public static Machine withParametersFromProperty() {
        Machine machine = new Machine();
        machine.setNumberInstances(Integer.parseInt(TestDataReader.getTestData(TEST_DATA_NUMBER_INSTANCES)));
        machine.setOsAndSoftware(TestDataReader.getTestData(TEST_DATA_OS_AND_SOFTWARE));
        machine.setVmClass(TestDataReader.getTestData(TEST_DATA_VM_CLASS));
        machine.setSeries(TestDataReader.getTestData(TEST_DATA_SERIES));
        machine.setInstanceType(TestDataReader.getTestData(TEST_DATA_TYPE));
        machine.setNumberGPUs(Integer.parseInt(TestDataReader.getTestData(TEST_DATA_NUMBER_GPUS)));
        machine.setGPUType(TestDataReader.getTestData(TEST_DATA_GPU_TYPE));
        machine.setLocalSSD(TestDataReader.getTestData(TEST_DATA_LOCAL_SSD));
        machine.setRegion(TestDataReader.getTestData(TEST_DATA_REGION));
        machine.setCommittedUsage(TestDataReader.getTestData(TEST_DATA_COMMITTED_USAGE));

        return machine;
    }
}
