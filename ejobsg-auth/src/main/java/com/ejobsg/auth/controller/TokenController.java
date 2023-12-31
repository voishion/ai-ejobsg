package com.ejobsg.auth.controller;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.ejobsg.common.core.constant.FrontConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ejobsg.auth.form.LoginBody;
import com.ejobsg.auth.form.RegisterBody;
import com.ejobsg.auth.service.SysLoginService;
import com.ejobsg.common.core.domain.R;
import com.ejobsg.common.core.utils.JwtUtils;
import com.ejobsg.common.core.utils.StringUtils;
import com.ejobsg.common.security.auth.AuthUtil;
import com.ejobsg.common.security.service.TokenService;
import com.ejobsg.common.security.utils.SecurityUtils;
import com.ejobsg.system.api.model.LoginUser;

/**
 * token 控制
 *
 * @author lilu
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form) {
        // 密码解密
        String password = SecureUtil.rsa(FrontConstants.PRIVATE_KEY, null).decryptStr(form.getPassword(), KeyType.PrivateKey);
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), password);
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request) {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody) {
        // 用户注册
        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        return R.ok();
    }
}
