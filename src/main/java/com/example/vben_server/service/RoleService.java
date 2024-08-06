package com.example.vben_server.service;

import com.example.vben_server.validate.commons.PageValidate;
import com.example.vben_server.validate.role.RoleAfterSearchValidate;
import com.example.vben_server.validate.role.RoleCreateValidate;
import com.example.vben_server.vo.PageResult;
import com.example.vben_server.vo.system.RoleListVo;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    PageResult<RoleListVo> list(PageValidate pageValidate, RoleAfterSearchValidate searchValidate);

    void add(RoleCreateValidate roleCreateValidate);
}
