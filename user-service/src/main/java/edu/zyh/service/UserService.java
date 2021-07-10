package edu.zyh.service;

import edu.zyh.domain.Purchaser;

import java.util.List;

public interface UserService {
    List<Purchaser> findPurchaserByUsername(String username);

    int findMultiPurchaserByTelOrUsername(String telephone, String username);

    int register(String username, String password, String address, String telephone, String sex);

    String loginByUsername(String usernameTag, String password);

    String loginByTel(String usernameTag, String password);

    Purchaser getPurchaserInfo(Integer purchaserId);

    int changePurchaserInfo(Purchaser purchaser);
}
