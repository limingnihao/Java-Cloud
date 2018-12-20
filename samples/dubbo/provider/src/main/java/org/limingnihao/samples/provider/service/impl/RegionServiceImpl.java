package org.limingnihao.samples.provider.service.impl;

import com.zhaopin.common3.http.HttpUtils;
import com.zhaopin.common3.http.holder.StringResponseHolder;
import org.limingnihao.data.RegionDao;
import org.limingnihao.interfaces.RPCRegionService;
import org.springframework.beans.factory.annotation.Autowired;

@com.alibaba.dubbo.config.annotation.Service
public class RegionServiceImpl implements RPCRegionService {

    @Autowired
    private RegionDao regionDao;


    @Override
    public long info() {
        return System.currentTimeMillis();
    }

    @Override
    public int getTotal(String params, String token) {
        int total = this.regionDao.getTotal();
        System.out.println("total=" + total);
        return total;
    }

    @Override
    public int getHttp() {
        StringResponseHolder s = HttpUtils.createGet("http://www.jd.com")
                .queryAsString();
        return 0;
    }

}
