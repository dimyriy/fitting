package org.serversfitting.domain;

import org.serversfitting.exceptions.OutOfCapacityException;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Fitting {
    private final List<HardwareServer> servers;
    private final AllocationCapacity serverCapacity;
    @NotNull
    private final FittingAlgorithm fittingAlgorithm;

    public Fitting(@NotNull final AllocationCapacity serverCapacity, @NotNull final FittingAlgorithm algorithm) {
        this.servers = new ArrayList<>();
        this.serverCapacity = serverCapacity;
        this.fittingAlgorithm = algorithm;
    }

    public List<HardwareServer> getServers() {
        return servers;
    }

    public void nextServer() {
        servers.add(new HardwareServer(serverCapacity));
    }

    private HardwareServer currentServer() {
        return servers.get(servers.size() - 1);
    }

    public void fit(@NotNull List<VirtualMachine> virtualMachines) {
        final List<VirtualMachine> vms = new ArrayList<>(virtualMachines);
        nextServer();
        checkNotFittingVms(vms);
        while (!vms.isEmpty()) {
            final HardwareServer currentServer = currentServer();
            final Optional<VirtualMachine> virtualMachine = fittingAlgorithm.findNext(currentServer.getRemainingCapacity(), vms);
            if (virtualMachine.isEmpty()) {
                nextServer();
            } else {
                currentServer.allocate(virtualMachine.get());
                vms.remove(virtualMachine.get());
            }
        }
    }

    public void checkNotFittingVms(@NotNull List<VirtualMachine> virtualMachines) {
        Optional<VirtualMachine> invalidVm = virtualMachines.stream().filter(virtualMachine -> !serverCapacity.checkAllocationFits(virtualMachine.allocationCapacity())).findAny();
        if (invalidVm.isPresent()) {
            throw new OutOfCapacityException(serverCapacity, invalidVm.get().allocationCapacity());
        }
    }
}
