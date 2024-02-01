package org.serversfitting.algorithms;

import org.serversfitting.domain.AllocationCapacity;
import org.serversfitting.domain.FittingAlgorithm;
import org.serversfitting.domain.VirtualMachine;

import java.util.List;
import java.util.Optional;

public class FirstFitFitting implements FittingAlgorithm {
    @Override
    public Optional<VirtualMachine> findNext(AllocationCapacity capacity, List<VirtualMachine> virtualMachines) {
        return virtualMachines.stream().filter(virtualMachine -> capacity.checkAllocationFits(virtualMachine.allocationCapacity())).findFirst();
    }
}
