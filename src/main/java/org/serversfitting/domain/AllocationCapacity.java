package org.serversfitting.domain;

public class AllocationCapacity {
    protected int cpuHz;
    protected int bandwidthMbitS;
    protected int memoryMb;

    public AllocationCapacity(final AllocationCapacity capacity) {
        this.cpuHz = capacity.getCpuHz();
        this.bandwidthMbitS = capacity.getBandwidthMbitS();
        this.memoryMb = capacity.getMemoryMb();
    }

    public AllocationCapacity(int cpuHz, int bandwidthMbitS, int memoryMb) {
        this.cpuHz = cpuHz;
        this.bandwidthMbitS = bandwidthMbitS;
        this.memoryMb = memoryMb;
    }


    public boolean checkAllocationFits(AllocationCapacity allocationCapacity) {
        return this.memoryMb >= allocationCapacity.getMemoryMb() && this.bandwidthMbitS >= allocationCapacity.getBandwidthMbitS() && this.cpuHz >= allocationCapacity.getCpuHz();
    }

    public int getCpuHz() {
        return cpuHz;
    }

    public int getBandwidthMbitS() {
        return bandwidthMbitS;
    }

    public int getMemoryMb() {
        return memoryMb;
    }
}
