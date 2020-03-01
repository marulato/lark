package org.avalon.lark.systemadmin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.avalon.lark.systemadmin.entity.Config;
import java.util.List;

@Mapper
public interface ConfigDao {

    public Config getConfig(String key);

    public List<Config> getAllConfigs();
}
