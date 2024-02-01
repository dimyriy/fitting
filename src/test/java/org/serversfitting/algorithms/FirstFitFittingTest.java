package org.serversfitting.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.serversfitting.domain.*;
import org.serversfitting.exceptions.OutOfCapacityException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class FirstFitFittingTest {
    private final Random random = new Random();

    @Test
    public void testShouldThrowExceptionWhenSomeVmsDoNotFit() {
        List<VirtualMachine> vms = List.of(new VirtualMachine(0, new AllocationCapacity(11, 2, 3)));
        FittingAlgorithm.Input input = new FittingAlgorithm.Input(vms, new AllocationCapacity(10, 10, 10));
        Fitting fitting = new Fitting(input.serverCapacity(), new FirstFitFitting());
        Assertions.assertThrows(OutOfCapacityException.class, () -> fitting.fit(input.virtualMachines()));

    }

    @Test
    public void testShouldReturnCorrectListForCorrectHardware() {
        List<VirtualMachine> vms = List.of(
                new VirtualMachine(0, new AllocationCapacity(4, 1, 1)),
                new VirtualMachine(1, new AllocationCapacity(7, 1, 1)),
                new VirtualMachine(2, new AllocationCapacity(5, 1, 1)),
                new VirtualMachine(3, new AllocationCapacity(6, 1, 1)),
                new VirtualMachine(4, new AllocationCapacity(1, 1, 1)),
                new VirtualMachine(5, new AllocationCapacity(2, 1, 1))
        );

        FittingAlgorithm.Input input = new FittingAlgorithm.Input(vms, new AllocationCapacity(10, 100, 100));
        Fitting fitting = new Fitting(input.serverCapacity(), new FirstFitFitting());
        fitting.fit(input.virtualMachines());

        Assertions.assertEquals(3, fitting.getServers().size());
        Assertions.assertEquals(List.of(vms.get(0), vms.get(2), vms.get(4)), fitting.getServers().get(0).getVirtualMachines());
        Assertions.assertEquals(List.of(vms.get(1), vms.get(5)), fitting.getServers().get(1).getVirtualMachines());
        Assertions.assertEquals(List.of(vms.get(3)), fitting.getServers().get(2).getVirtualMachines());
    }
}