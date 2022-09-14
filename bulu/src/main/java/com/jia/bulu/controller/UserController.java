package com.jia.bulu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jia.bulu.common.R;
import com.jia.bulu.entity.User;
import com.jia.bulu.service.UserService;
import com.jia.bulu.utils.SMSUtils;
import com.jia.bulu.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送手机验证码
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user,
                             HttpSession session){

        //获取手机号
        String phone = user.getPhone();

        if(StringUtils.isNotEmpty(phone)){
            //生成随机的四位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();

            //通用阿里云的短信服务API完成发送短信
            //SMSUtils.sendMessage("签名","模板",phone,code);

            //使用redis模拟验证码发送
            //将code保存到redis数据库中
            redisTemplate.opsForValue().set(phone+":code",code,60*5, TimeUnit.SECONDS);


            redisTemplate.opsForValue().get(phone+":code");


            //需要将生成的验证码保存到session
            //session.setAttribute(phone,code);

            return R.success("你的短信验证码为  " + code + "  有效期为5分钟");

        }


        return R.error("短信发送失败");

    }

    /**
     * 用户登录
     * @param map
     * @param session
     *@return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map,
                         HttpSession session){

        log.info("map: ",map);

        //从map获取手机号
        String phone = map.get("phone").toString();


        //获取验证码
        String codeFromUser = map.get("code").toString();

        //从redis中获取保存的验证码
        String codeFromRedis = (String) redisTemplate.opsForValue().get(phone + ":code");

        //进行验证码的比对
        if (codeFromRedis != null && codeFromRedis.equals(codeFromUser)){

            //比对成功，说明登录成功
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);

            User user = userService.getOne(queryWrapper);
            if (user == null){
                //判断当前手机号对应的用户是否是新用户，如果是则自动完成注册
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());

            return R.success(user);

        }



        return R.error("登陆失败");
    }

}
