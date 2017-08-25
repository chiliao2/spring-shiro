package com.cdz.jn.config;

import com.cdz.jn.entity.Role;
import com.cdz.jn.entity.User;
import com.cdz.jn.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {
    private static final  Log log = LogFactory.getLog(MyShiroRealm.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("授权开始...............");
        String userName = (String) super.getAvailablePrincipal(principalCollection);
        User user = userRepository.findByUsername(userName);
        if (user != null) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            Set<String> roleNames = new HashSet<String>();
            for (Role r : user.getRoles()) {
                roleNames.add(r.getName());
            }
            simpleAuthorizationInfo.setRoles(roleNames);
            log.info("授权结束...............");
            return simpleAuthorizationInfo;
        }
        return null;
    }

    /**
     * 认证，相当于登录
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        User user = userRepository.findByUsername(userName);
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        }
        return null;
    }
}
