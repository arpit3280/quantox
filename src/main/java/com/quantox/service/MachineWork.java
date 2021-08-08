package com.quantox.service;

import com.quantox.constant.MachineStatus;
import com.quantox.data.Machine;
import com.quantox.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MachineWork {

    @Autowired
    private MachineRepository machineRepository;

    @Async
    public void startMachine(Machine machine) throws Exception {
        int startingTime = (int) Math.random() * 5000 + 1000;
        Thread.sleep(startingTime);
        machine.setStatus(MachineStatus.RUNNING);
        machineRepository.save(machine);
    }

    @Async
    public void stopMachine(Machine machine) throws Exception {
        int startingTime = (int) Math.random() * 5000 + 5000;
        Thread.sleep(startingTime);
        machine.setStatus(MachineStatus.STOPPED);
        machineRepository.save(machine);
    }

    @Async
    public void restartMachine(Machine machine) throws Exception {
        stopMachine(machine);
        startMachine(machine);
    }
}


