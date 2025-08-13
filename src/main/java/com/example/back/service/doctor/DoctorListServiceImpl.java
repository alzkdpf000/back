package com.example.back.service.doctor;

import com.example.back.dto.doctor.DoctorListDTO;
import com.example.back.repository.doctor.DoctorListDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorListServiceImpl implements DoctorListService {

    private final DoctorListDAO doctorListDAO;

    public DoctorListServiceImpl(DoctorListDAO doctorListDAO) {
        this.doctorListDAO = doctorListDAO;
    }

    @Override
    public List<DoctorListDTO> getList(DoctorListDTO doctorListDTO) {
        return doctorListDAO.getList(doctorListDTO);
    }
}