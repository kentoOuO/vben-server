package com.example.vben_server.service;

import com.example.vben_server.validate.department.DeptCreateValidate;
import com.example.vben_server.validate.department.DepartmentSearchValidate;
import com.example.vben_server.validate.department.DeptUpdateValidate;
import com.example.vben_server.validate.department.DeptDeleteValidate;
import com.example.vben_server.vo.system.DepartmentListVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptService {
    List<DepartmentListVO> list(DepartmentSearchValidate departmentSearchValidate);

    void add(DeptCreateValidate deptCreateValidate);

    void update(DeptUpdateValidate deptUpdateValidate);

    void delete(DeptDeleteValidate deptDeleteValidate);
}
