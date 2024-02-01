package org.serversfitting.domain;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface FittingAlgorithm {
    @NotNull
    Optional<VirtualMachine> findNext(@NotNull AllocationCapacity capacity, @NotNull List<VirtualMachine> virtualMachines);

    record Input(@NotNull List<VirtualMachine> virtualMachines, @NotNull AllocationCapacity serverCapacity) {
    }
}
