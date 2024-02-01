package org.serversfitting.domain;

public class MutableCapacity extends AllocationCapacity {
    public MutableCapacity(AllocationCapacity capacity) {
        super(capacity);
    }

    public void subtract(final AllocationCapacity allocationCapacity) {
        this.cpuHz -= allocationCapacity.getCpuHz();
        this.bandwidthMbitS -= allocationCapacity.getCpuHz();
        this.memoryMb -= allocationCapacity.getBandwidthMbitS();
    }
}
