package edu.zyh.dao;


import edu.zyh.domain.Purchaser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("select * from purchaser where username=#{username}")
    List<Purchaser> findPurchaserByUsername(String username);

    @Select("select * from purchaser where telephone = #{telephone} or username = #{username}")
    List<Purchaser> findMultiPurchaserByTelOrUsername(@Param("telephone") String telephone, @Param("username") String username);

    @Insert("insert into purchaser(username, password, address, telephone, sex) value (#{username}, #{password}, #{address}, #{telephone}, #{sex})")
    int register(@Param("username") String username, @Param("password") String password, @Param("address") String address, @Param("telephone") String telephone, @Param("sex") String sex);

    @Select("select * from purchaser where telephone = #{telephone} and password = #{password}")
    Purchaser loginByTel(@Param("telephone") String telephone,@Param("password") String password);

    @Select("select * from purchaser where username = #{username} and password = #{password}")
    Purchaser loginByUsername(@Param("username") String username,@Param("password") String password);

    @Select("select * from purchaser where purchaserId = #{purchaserId}")
    Purchaser getPurchaserInfo(Integer purchaserId);

    @Update("update purchaser set username=#{purchaser.username}, password=#{purchaser.password}, address=#{purchaser.address}, " +
            "telephone=#{purchaser.telephone}, sex=#{purchaser.sex} where user_id = #{purchaser.userId}")
    int changePurchaserInfo(@Param("purchaser") Purchaser purchaser);
}
