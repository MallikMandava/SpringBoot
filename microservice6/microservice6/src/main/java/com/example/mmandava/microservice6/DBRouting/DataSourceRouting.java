package com.example.mmandava.microservice6.DBRouting;


import com.example.mmandava.microservice6.ContextConfig.DataSourceContextHolder;
import com.example.mmandava.microservice6.ContextConfig.DataSourceEnum;
import com.example.mmandava.microservice6.config.DataSourceOneConfig;
import com.example.mmandava.microservice6.config.DataSourceTwoConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.activation.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataSourceRouting  extends AbstractRoutingDataSource {

    private DataSourceOneConfig dataSourceOneConfig;
    private DataSourceTwoConfig dataSourceTwoConfig;
    private DataSourceContextHolder dataSourceContextHolder;


    public DataSourceRouting(DataSourceContextHolder dataSourceContextHolder, DataSourceOneConfig dataSourceOneConfig,
                             DataSourceTwoConfig dataSourceTwoConfig) {
        this.dataSourceOneConfig = dataSourceOneConfig;
        this.dataSourceTwoConfig = dataSourceTwoConfig;
        this.dataSourceContextHolder = dataSourceContextHolder;

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceEnum.DATASOURCE_ONE, dataSourceOneDataSource());
        dataSourceMap.put(DataSourceEnum.DATASOURCE_TWO, dataSourceTwoDataSource());
        this.setTargetDataSources(dataSourceMap);
        this.setDefaultTargetDataSource(dataSourceOneDataSource());
    }

    @Override
    protected Object determineCurrentLookupKey() {



        return  dataSourceContextHolder.getBranchContext();

    }

    public DataSource dataSourceOneDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dataSourceOneConfig.getUrl());
        dataSource.setUsername(dataSourceOneConfig.getUsername());
        dataSource.setPassword(dataSourceOneConfig.getPassword());
        return (DataSource) dataSource;
    }


    public DataSource dataSourceTwoDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dataSourceTwoConfig.getUrl());
        dataSource.setUsername(dataSourceTwoConfig.getUsername());
        dataSource.setPassword(dataSourceTwoConfig.getPassword());
        return (DataSource) dataSource;
    }

}
