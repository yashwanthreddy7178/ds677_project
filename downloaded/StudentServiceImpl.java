package com.rui.fwb.service.impl;

import com.rui.fwb.dao.IStudentDao;
import com.rui.fwb.service.IStudentService;
import com.rui.fwb.vo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    @Resource
    private IStudentDao iStudentDAO;


    @Override
    public List<Student> list() {
        return this.iStudentDAO.findAll();
    }

    @Override
    public void Insert(String name, String passwd, Integer cla, String realName) {
         this.iStudentDAO.insert(name,passwd,cla,realName);
    }

    @Override
    public Student findByName(String name) {
        return this.iStudentDAO.findByName(name);
    }


}
