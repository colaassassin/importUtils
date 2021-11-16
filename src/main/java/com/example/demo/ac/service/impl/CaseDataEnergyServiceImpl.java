package com.example.demo.ac.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.ac.entity.CaseDataEnergy;
import com.example.demo.ac.mapper.CaseDataEnergyMapper;
import com.example.demo.ac.service.CaseDataEnergyService;
import org.springframework.stereotype.Service;

@Service
public class CaseDataEnergyServiceImpl extends ServiceImpl<CaseDataEnergyMapper, CaseDataEnergy> implements CaseDataEnergyService {
}
