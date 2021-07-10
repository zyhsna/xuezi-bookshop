package edu.zyh.service.Impl;

import edu.zyh.dao.UserMapper;
import edu.zyh.domain.Purchaser;
import edu.zyh.service.UserService;
import edu.zyh.utils.JWTUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<Purchaser> findPurchaserByUsername(String username) {
        List<Purchaser> purchaserByUsername = userMapper.findPurchaserByUsername(username);
        return purchaserByUsername;
    }

    @Override
    public int findMultiPurchaserByTelOrUsername(String telephone, String username) {
        List<Purchaser>multiPurchaser = userMapper.findMultiPurchaserByTelOrUsername(telephone, username);
        if (multiPurchaser.isEmpty()){
            // not found multiPurchase, then can regist
            return 0;
        }
        else
            return 1;
    }

    @Override
    public int register(String username, String password, String address, String telephone, String sex) {
        int register = userMapper.register(username, password, address, telephone, sex);
        return register;
    }

    @Override
    public String loginByUsername(String username, String password) {
        Purchaser purchasers = userMapper.loginByUsername(username, password);
        if (purchasers == null){
            return null;
        }
        else {
            String webToken = JWTUtils.generateJsonWebToken(purchasers);
            return webToken;
        }
    }

    @Override
    public String loginByTel(String telephone, String password) {
        Purchaser purchasers = userMapper.loginByTel(telephone, password);
        if (purchasers == null){
            return null;
        }
        else {
            String webToken = JWTUtils.generateJsonWebToken(purchasers);
            return webToken;
        }
    }

    @Override
    public Purchaser getPurchaserInfo(Integer purchaserId) {
        Purchaser purchaser = userMapper.getPurchaserInfo(purchaserId);
        return purchaser;
    }

    @Override
    public int changePurchaserInfo(Purchaser purchaser) {
        int result = userMapper.changePurchaserInfo(purchaser);
        return result;
    }
}
