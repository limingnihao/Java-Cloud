package org.limingnihao.data;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionDao {

    @Select("select count(*) from app_region_info ")
    int getTotal();

}
