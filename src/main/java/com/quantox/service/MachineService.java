package com.quantox.service;

import com.quantox.constant.MachineStatus;
import com.quantox.data.Machine;
import com.quantox.data.User;
import com.quantox.dto.CreateMachineDto;
import com.quantox.dto.MachineDto;
import com.quantox.repository.MachineRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MachineService {

    private Logger log = LoggerFactory.getLogger(MachineService.class);

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private MachineWork machineWork;

    private ModelMapper modelMapper = new ModelMapper();

    public Machine getMachine(String machineId) throws Exception {
        Machine machine = machineRepository.getByUid(machineId);
        if(machine == null)
            throw new Exception("Machine with id: " + machineId+ " doesn't exist");
        return machine;
    }

    public void startMachine(Machine machine) throws Exception {
        if (machine.getStatus() != MachineStatus.STOPPED)
            throw new Exception("Machine:" + machine.getUid() + " cannot be started.");
        machineWork.startMachine(machine);
    }


    public void stopMachine(Machine machine) throws Exception {
        if(machine.getStatus() != MachineStatus.RUNNING)
            throw new Exception("Machine:"+ machine.getUid()+" cannot be stopped.");
        machineWork.stopMachine(machine);
    }

    public void restartMachine(Machine machine) throws Exception {
        stopMachine(machine);
        startMachine(machine);
    }

    @Transactional
    public String createMachine( Long user) {
        Machine machine = new Machine(0, UUID.randomUUID().toString(), MachineStatus.STOPPED,user, LocalDateTime.now(),true);
        machineRepository.save(machine);
        return machine.getUid();
    }

    public void destroyMachine(Machine machine) throws Exception {
        if(machine.getStatus() != MachineStatus.STOPPED)
            throw new Exception("Machine:"+ machine.getUid()+" cannot be started.");
        machine.setActive(false);
        machineRepository.save(machine);
    }

    public List<MachineDto> searchMachine() {
        List<Machine> machineList = machineRepository.searchActiveMachine();
        List<MachineDto> machineDto = new ArrayList<>();
        for(int index = 0 ; index< machineList.size();index++){
            machineDto.add(modelMapper.map(machineList.get(index), MachineDto.class));
        }
        return machineDto;
    }
}
