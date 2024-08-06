package com.example.vben_server.service;

import com.example.vben_server.validate.account.AccountAfterSearchValidate;
import com.example.vben_server.validate.account.AccountCreateValidate;
import com.example.vben_server.validate.account.AccountUpdateValidate;
import com.example.vben_server.validate.commons.PageValidate;
import com.example.vben_server.vo.PageResult;
import com.example.vben_server.vo.system.AccountListVo;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    PageResult<AccountListVo> list(PageValidate pageValidate,AccountAfterSearchValidate searchValidate);

    void add(AccountCreateValidate accountCreateValidate);

    void update(AccountUpdateValidate accountUpdateValidate);
}
