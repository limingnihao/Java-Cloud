package org.limingnihao.interfaces;

import org.limingnihao.beans.UserBean;
import org.limingnihao.exceptions.ExistsNullException;
import org.limingnihao.exceptions.ExistsValueException;
import org.limingnihao.vos.UserVO;

import java.util.List;

public interface RPCUserService {

    /**
     * 创建用户
     *
     * @param vo
     * @return
     */
    long create(UserVO vo) throws ExistsValueException;

    long delete(Long userId) throws ExistsNullException;

    /**
     * 修改数据
     *
     * @param userId
     * @param vo
     */
    void update(Long userId, UserVO vo) throws ExistsNullException;


    /**
     * 获取用户
     *
     * @param userId
     * @return
     */
    UserBean getById(Long userId) throws ExistsNullException;

    /**
     * 获取用户列表
     *
     * @return
     */
    List<UserBean> getList(String params, String accessToken, String platformCode);

}
