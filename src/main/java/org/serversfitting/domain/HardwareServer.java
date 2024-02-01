package org.serversfitting.domain;

import org.serversfitting.exceptions.OutOfCapacityException;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public final class HardwareServer {
    @NotNull
    private final MutableCapacity remainingCapacity;

    @NotNull
    private final List<VirtualMachine> virtualMachines;

    public HardwareServer(@NotNull AllocationCapacity capacity) {
        this.remainingCapacity = new MutableCapacity(capacity);
        this.virtualMachines = new ArrayList<>();
    }

    @NotNull
    public AllocationCapacity getRemainingCapacity() {
        return new AllocationCapacity(remainingCapacity);
    }

    @NotNull
    public List<VirtualMachine> getVirtualMachines() {
        return new ArrayList<>(virtualMachines);
    }

    public void allocate(@NotNull final VirtualMachine virtualMachine) {
        if (!this.remainingCapacity.checkAllocationFits(virtualMachine.allocationCapacity())) {
            throw new OutOfCapacityException(new AllocationCapacity(remainingCapacity), virtualMachine.allocationCapacity());
        }
        this.remainingCapacity.subtract(virtualMachine.allocationCapacity());
        this.virtualMachines.add(virtualMachine);
    }
}
