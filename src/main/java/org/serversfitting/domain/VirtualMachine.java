package org.serversfitting.domain;

import javax.validation.constraints.NotNull;

public record VirtualMachine(long id, AllocationCapacity allocationCapacity) {
    public VirtualMachine(long id, @NotNull AllocationCapacity allocationCapacity) {
        this.id = id;
        this.allocationCapacity = allocationCapacity;
    }
}
