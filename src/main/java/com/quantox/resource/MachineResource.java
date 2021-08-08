package com.quantox.resource;

import com.quantox.data.Machine;
import com.quantox.dto.CreateMachineDto;
import com.quantox.service.MachineService;
import com.quantox.util.HashingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/machine")
public class MachineResource {

    private Logger log = LoggerFactory.getLogger(MachineResource.class);

    @Autowired
    private MachineService machineService;

    @PostMapping("/start/{id}")
    public ResponseEntity startMachine(@PathVariable("id") String machineId){
        log.info("START_MACHINE ID : " + machineId );
        try{
            Machine machine = machineService.getMachine(machineId);
            machineService.startMachine(machine);
            return new ResponseEntity("Machine: "+machineId+" started Successfully", HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/stop/{id}")
    public ResponseEntity stopMachine(@PathVariable("id") String machineId){
        log.info("STOP_MACHINE ID : " + machineId );
        try{
            Machine machine = machineService.getMachine(machineId);
            machineService.stopMachine(machine);
            return new ResponseEntity("Machine: "+machineId+" stopped Successfully", HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/restart/{id}")
    public ResponseEntity restartMachine(@PathVariable("id") String machineId){
        log.info("RESTART_MACHINE ID : " + machineId );
        try{
            Machine machine = machineService.getMachine(machineId);
            machineService.restartMachine(machine);
            return new ResponseEntity("Machine: "+machineId+" restarted Successfully", HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/destroy/{id}")
    public ResponseEntity destroyMachine(@PathVariable("id") String machineId){
        log.info("RESTART_MACHINE ID : " + machineId );
        try{
            Machine machine = machineService.getMachine(machineId);
            machineService.destroyMachine(machine);
            return new ResponseEntity("Machine: "+machineId+" destroyed Successfully", HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //was not able to implement filter mechanism due to time constraint
    @GetMapping("/search")
    public ResponseEntity searchMachine(){
        try{
            return new ResponseEntity(machineService.searchMachine(), HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity createMachine(@RequestAttribute("uid") Long userId){
        log.info("CREATE_MACHINE ID"+ "header "+ userId );
        try{
            return new ResponseEntity(machineService.createMachine(userId), HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
