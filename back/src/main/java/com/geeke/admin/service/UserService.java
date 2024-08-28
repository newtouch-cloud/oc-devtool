package com.geeke.admin.service;

import com.geeke.admin.dao.UserDao;
import com.geeke.admin.entity.Role;
import com.geeke.admin.entity.User;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Maps;
import java.util.Map;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 开发者管理Service
 * @author
 * @version
 */

@Service("userService")
@Transactional(readOnly = true)
public class UserService extends CrudService<UserDao, User> {

    @Transactional(readOnly = false)
    @Override
    public User save(User user) {
        // 约束条件处理
        Map<String, String> colMaps = Maps.newHashMap();

        // 邮箱唯一检查

        colMaps.clear();

        colMaps.put("email", "email");

        if (exists(dao, user, colMaps)) {
            throw new CommonJsonException(ResultUtil.warningJson(ErrorEnum.E_50001, "邮箱已经被使用"));
        }

        // 添加的用户设置为普通开发者角色
        if (StringUtils.isBlank(user.getId())) {
            user.setRole(new Role("2199"));
        }

        User userTemp = super.save(user);

        // 加密字段处理
        if (StringUtils.isNoneBlank(userTemp.getId())) {
            // 设置加密字段  密码
            if (user.getLoginPasswordUpdate()) {
                Md5Hash md5 = new Md5Hash(user.getLoginPassword(), user.getId(), 6);
                String md5Password = md5.toHex();
                dao.updateLoginPassword(user.getId(), md5Password);
            }
        }

        return userTemp;
    }

    // 修改加密字段

    /**
     * 修改 密码
     */
    @Transactional(readOnly = false)
    public int changeLoginPassword(String id, String pass) {
        // Md5密码
        Md5Hash md5 = new Md5Hash(pass, id, 6);
        String md5Password = md5.toHex();

        int rows = dao.updateLoginPassword(id, md5Password);
        return rows;
    }
}
