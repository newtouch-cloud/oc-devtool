package com.geeke.project.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.project.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 项目管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface MemberDao extends CrudDao<Member> {}
