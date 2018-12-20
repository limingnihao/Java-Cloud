package org.limingnihao.samples.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.limingnihao.beans.UserBean;
import org.limingnihao.data.RegionDao;
import org.limingnihao.exceptions.ExistsNullException;
import org.limingnihao.exceptions.ExistsValueException;
import org.limingnihao.interfaces.RPCUserService;
import org.limingnihao.vos.UserVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements RPCUserService {

    @Autowired
    private RegionDao regionDao;

    @Override
    public long create(UserVO vo) throws ExistsValueException {
        return 0;
    }

    @Override
    public long delete(Long userId) throws ExistsNullException {
        return 0;
    }

    @Override
    public void update(Long userId, UserVO vo) throws ExistsNullException {

    }

    @Override
    public UserBean getById(Long userId) throws ExistsNullException {
        return null;
    }

    @Override
    public List<UserBean> getList(String params, String accessToken, String platformCode) {
        if(params.equals("q")){
            throw new NullPointerException("我有异常");
        }
        List<UserBean> list = new ArrayList<>();
        list.add(new UserBean(1L, "limingnihao1", 23, true));
        list.add(new UserBean(1123123L, "limingnihao2", 23, true));
        list.add(new UserBean(1231L, "limingnihao3", 23, false));
        list.add(new UserBean(34561L, "limingnihao4", 54, true));
        list.add(new UserBean(345651L, "limingnihao5", 23, true));
        int total = this.regionDao.getTotal();
        return list;
    }
}
