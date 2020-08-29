package com.cloud.frame.frameauth.security;

import com.cloud.frame.authclient.entity.ComRole;
import com.cloud.frame.authclient.entity.ComUser;
import com.cloud.frame.frameauth.service.IComRoleService;
import com.cloud.frame.frameauth.service.IComUserService;
import com.cloud.frame.framecommon.enums.BoolEnum;
import com.cloud.frame.framesecurity.entity.SecurityAuthority;
import com.cloud.frame.framesecurity.entity.SecurityUser;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * security用户获取
 * @author Liulj
 * @version v 0.1 2019/11/6 10:56
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IComUserService comUserService;
    @Autowired
    IComRoleService comRoleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        ComUser checkUser = ComUser.builder()
                .userName(userName)
                .build();

        ComUser dbUser = comUserService.selectOne(checkUser);
        if(Objects.isNull(dbUser)){
            throw new UsernameNotFoundException("用户不存在");
        }
        dbUser.setPassword("{bcrypt}" + dbUser.getPassword());
        //获取权限列表
        Set<SecurityAuthority> grantedAuthorities = Sets.newHashSet();
        List<Long> roleIds = Lists.newArrayList();
        if(StringUtils.isNotEmpty(dbUser.getRoleIds())){
            roleIds = Arrays.stream(dbUser.getRoleIds().split(","))
                    .map(Long::valueOf).collect(Collectors.toList());
        }
        if(CollectionUtils.isNotEmpty(roleIds)){
            ComRole comRole = new ComRole();
            comRole.andRoleId(Opt.IN,roleIds);
            List<ComRole> comRoles = comRoleService.selectList(comRole);
            for (ComRole role : comRoles) {
                SecurityAuthority securityAuthority = new SecurityAuthority();
                securityAuthority.setAuthority("ROLE_" + role.getRoleCode());
                grantedAuthorities.add(securityAuthority);
            }
        }
        //enabled 可用性 :true:可用 false:不可用
        //accountNonExpired 过期性 :true:没过期 false:过期
        //credentialsNonExpired 有效性 :true:凭证有效 false:凭证无效
        //accountNonLocked 锁定性 :true:未锁定 false:已锁定
        Boolean enabled = BoolEnum.codeBoolMap.get(dbUser.getEnabled());
        Boolean accountNonExpired = BoolEnum.codeBoolMap.get(dbUser.getAccountNonExpired());
        Boolean credentialsNonExpired = BoolEnum.codeBoolMap.get(dbUser.getCredentialsNonExpired());
        Boolean accountNonLocked = BoolEnum.codeBoolMap.get(dbUser.getAccountNonLocked());
        SecurityUser securityUser = new SecurityUser(dbUser.getUserName(),dbUser.getPassword(),
                enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,grantedAuthorities);
        securityUser.setId(dbUser.getUserId());
        return securityUser;
    }
}
