package edu.zyh.controller;


import com.alibaba.fastjson.JSONObject;
import edu.zyh.domain.JsonData;
import edu.zyh.domain.Purchaser;
import edu.zyh.service.UserService;
import edu.zyh.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Pattern;


@RestController
@CrossOrigin
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

    @RequestMapping("/register")
    public int register(String username, String password, String address, String telephone, String sex) {
        if (findMultiPurchaserByTelOrUsername(telephone, username) == 0) {
            int i = userService.register(username, password, address, telephone, sex);
            return i;
        } else
            return 0;
    }

    @RequestMapping("/login")
    public JsonData login(String usernameTag, String password, HttpServletResponse response) {
        String result = "";
        if (isInteger(usernameTag)) {
            result = userService.loginByTel(usernameTag, password);
        } else {
            result = userService.loginByUsername(usernameTag, password);
        }
        int id = 0;
        if (result != null) {
            Claims claims = JWTUtils.checkJWT(result);
            id = (Integer) claims.get("id");
        } else {
            result = "result";
        }
        response.setHeader("token", result);
        response.setContentType("application/json");
        JSONObject json = JsonData.generateJson(id, result);
        if (result.equals("result"))
            return JsonData.buildError(1, json);
        else
            return JsonData.buildError(0, json);

    }

    @RequestMapping("/getPurchaserInfo")
    public JsonData getPurchaserInfo(@RequestParam("purchaserId") Integer purchaserId) {
        Purchaser purchaser = userService.getPurchaserInfo(purchaserId);
        System.out.println(purchaser);
        return JsonData.buildSuccess(purchaser);

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






















