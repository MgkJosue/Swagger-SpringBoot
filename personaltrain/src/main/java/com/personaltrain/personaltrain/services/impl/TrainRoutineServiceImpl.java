package com.personaltrain.personaltrain.services.impl;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personaltrain.personaltrain.dto.NewTrainRoutineDTO;
import com.personaltrain.personaltrain.dto.TrainRoutineDTO;
import com.personaltrain.personaltrain.dto.TrainRoutineListDTO;
import com.personaltrain.personaltrain.exceptions.NoContentException;
import com.personaltrain.personaltrain.exceptions.ResourceNotFoundException;
import com.personaltrain.personaltrain.models.TrainRoutine;
import com.personaltrain.personaltrain.repositories.TrainRoutineRepository;
import com.personaltrain.personaltrain.services.TrainRoutineService;

@Service
public class TrainRoutineServiceImpl implements TrainRoutineService {

    final ModelMapper modelMapper;
    final TrainRoutineRepository trainRoutineRepository;

    public TrainRoutineServiceImpl(TrainRoutineRepository repository, ModelMapper mapper){
        this.trainRoutineRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public TrainRoutineDTO create(NewTrainRoutineDTO trainRoutineDTO) {
        TrainRoutine trainRoutine = modelMapper.map(trainRoutineDTO, TrainRoutine.class);
        trainRoutineRepository.save(trainRoutine);        
        return modelMapper.map(trainRoutine, TrainRoutineDTO.class); 
    }

    @Override
    @Transactional(readOnly = true)
    public TrainRoutineDTO retrieve(Long id) {
        TrainRoutine trainRoutine = trainRoutineRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("TrainRoutine not found"));
        return modelMapper.map(trainRoutine, TrainRoutineDTO.class);
    }

    @Override
    @Transactional
    public TrainRoutineDTO update(TrainRoutineDTO trainRoutineDTO, Long id) {
        TrainRoutine trainRoutine = trainRoutineRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TrainRoutine not found"));
        
        trainRoutine.setId(id);
        trainRoutine = modelMapper.map(trainRoutineDTO, TrainRoutine.class);
        trainRoutineRepository.save(trainRoutine);       

        return modelMapper.map(trainRoutine, TrainRoutineDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        TrainRoutine trainRoutine = trainRoutineRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TrainRoutine not found"));        
        trainRoutineRepository.deleteById(trainRoutine.getId());        
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainRoutineListDTO> list() {
        List<TrainRoutine> trainRoutines = trainRoutineRepository.findAll();
        if(trainRoutines.isEmpty()) throw new NoContentException("TrainRoutines is empty");
        return trainRoutines.stream().map(trainRoutine -> modelMapper.map(trainRoutine, TrainRoutineListDTO.class))
            .collect(Collectors.toList());        
    }
    
}
