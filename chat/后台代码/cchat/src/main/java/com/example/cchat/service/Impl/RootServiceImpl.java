package com.example.cchat.service.Impl;

import com.example.cchat.dao.RootDao;
import com.example.cchat.entity.Root;
import com.example.cchat.service.RootService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RootServiceImpl implements RootService {
    @Resource
    private RootDao rootDao;

    @Override
    public Root checkRoot(Root root) {
        Root rootFind = rootDao.findByName(root.getUser_name());
        if (rootFind != null){
            if (rootFind.getUser_password().equals(root.getUser_password())) {
                return rootFind;
            }
        }
        return null;
    }
}
