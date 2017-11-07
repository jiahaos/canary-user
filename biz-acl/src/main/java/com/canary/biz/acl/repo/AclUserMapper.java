package com.canary.biz.acl.repo;


import com.canary.biz.acl.enti.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AclUserMapper extends BaseMapper<UserEntity, Long>{
	
	/**
	 * 添加用户和角色对应关系
	 * @param userEntity
	 * @return
	 */
	public int insertUserRole(UserEntity userEntity);
	
	/**
	 * 更新用户和角色对应关系
	 * @param userEntity
	 * @return
	 */
	public int updateUserRole(UserEntity userEntity);
	
	/**
	 * 删除用户和角色对应关系
	 * @return
	 */
	public int deleteBatchUserRole(List<Long> userIds);

	/**
	 * 添加用户
	 * @param userEntity
	 * @return
	 */
	public int insertUserAccount(UserEntity userEntity);
	
	/**
	 * 添加用户个人资料信息
	 * @param userEntity
	 * @return
	 */
	public int insertUserInfo(UserEntity userEntity);
	
	/**
	 * 更新用户个人资料信息
	 * @param userEntity
	 * @return
	 */
	public int updateUserInfo(UserEntity userEntity);

	/**
	 * 获取用户和API的对应关系
	 * @param account
	 * @return
	 */
	public List<String> findApiUrlsByAccountId(@Param(value = "account") String account);

	/**
	 * 获取用户账户
	 * @param accountName
	 * @return
	 */
	public UserEntity findAccountByAccountName(@Param(value = "accountName") String accountName);

	/**
	 * 查询多个ID对应的账号对象
	 * @param list
	 * @return
     */
	List<UserEntity> selectAccountDTOByIds(List<Integer> list);

}
