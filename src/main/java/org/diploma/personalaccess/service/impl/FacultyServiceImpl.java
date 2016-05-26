package org.diploma.personalaccess.service.impl;

import org.diploma.personalaccess.entity.Faculty;
import org.diploma.personalaccess.repository.FacultyRepository;
import org.diploma.personalaccess.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;


    @Override
    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

}
