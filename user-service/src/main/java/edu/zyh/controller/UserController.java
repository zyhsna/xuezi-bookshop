package edu.zyh.controller;


import edu.zyh.domain.Purchaser;
import edu.zyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;


@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    UserService userService;

    @RequestMapping("/findPurchaserByUsername")
    public List<Purchaser> findPurchaserByUsername(String username) {
        List<Purchaser> purchaserByUsername = userService.findPurchaserByUsername(username);
        return purchaserByUsername;
    }

    @RequestMapping("/findMultiPurchaserByTelOrUsername")
    private int findMultiPurchaserByTelOrUsername(String telephone, String username) {
        int multiPurchaserByTelOrUsername = userService.findMultiPurchaserByTelOrUsername(telephone, username);
        if (multiPurchaserByTelOrUsername > 0) {
            return 1;
        } else return 0;
    }

    @PostMapping("/register")
    public int register(String username, String password, String address, String telephone, String sex) {
        if (findMultiPurchaserByTelOrUsername(telephone, username) == 0) {
            int i = userService.register(username, password, address, telephone, sex);
            return i;
        } else
            return 0;
    }

    @RequestMapping("/login")
    public String login(String usernameTag, String password) {
        String result = "";
        Purchaser purchaser = new Purchaser();
        if (isInteger(usernameTag)) {
            result = userService.loginByTel(usernameTag, password);
        } else {
            result = userService.loginByUsername(usernameTag, password);
        }
        return Objects.requireNonNullElse(result, "result");
    }

    @RequestMapping("/getPurchaserInfo")
    public Purchaser getPurchaserInfo(@RequestParam("purchaserId") Integer purchaserId) {
        Purchaser purchaser = userService.getPurchaserInfo(purchaserId);
        return purchaser;
    }

    @RequestMapping("/changePurchaserInfo")
    public int changePurchaserInfo(@RequestBody Purchaser purchaser) {
        int result = userService.changePurchaserInfo(purchaser);
        return result;
    }


    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


}






















