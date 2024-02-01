package org.serversfitting.exceptions;

import org.serversfitting.domain.AllocationCapacity;

public class OutOfCapacityException extends RuntimeException {
    private final AllocationCapacity remainingCapacity;
    private final AllocationCapacity requiredCapacity;

    public OutOfCapacityException(AllocationCapacity remainingCapacity, AllocationCapacity requiredCapacity) {
        this.remainingCapacity = remainingCapacity;
        this.requiredCapacity = requiredCapacity;
    }

    public AllocationCapacity getRemainingCapacity() {
        return remainingCapacity;
    }

    public AllocationCapacity getRequiredCapacity() {
        return requiredCapacity;
    }
}