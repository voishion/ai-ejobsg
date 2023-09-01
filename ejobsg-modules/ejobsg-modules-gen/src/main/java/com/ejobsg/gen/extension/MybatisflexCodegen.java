package com.ejobsg.gen.extension;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.ejobsg.common.core.web.controller.BaseController;
import com.ejobsg.common.core.web.domain.BaseEntity;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.EntityConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.service.IService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;

/**
 * @author voishion
 */
public class MybatisflexCodegen {

    public static void main(String[] args) {
        String applicationName = "ai-ejobsg/ejobsg-modules/ejobsg-modules-recruitment";
        String basePackage = "com.ejobsg.recruitment";

        String[] tablePrefix = new String[]{"tb_", "t_", "sys_", "biz_"};
        String[] tables = new String[]{"face_compare_request_log"};
        //tables = new String[]{"document_catalogue_base"};
        //tables = new String[]{"document_library_base"};

        String shortJdbcUrl = "jdbc:mysql://localhost:3306/ai-ejobsg";
        String username = "root";
        String password = "feaafeaadf6b4e61be1037dd6d522ef1";

        //shortJdbcUrl = "jdbc:mysql://10.152.160.66:59967/wenku";
        //username = "db_admin";
        //password = "mKw=5RunIRyM";

        generate(applicationName, basePackage, tablePrefix, tables, shortJdbcUrl, username, password);
    }

    private static void generate(String applicationName, String basePackage, String[] tablePrefix, String[] tables, String shortJdbcUrl, String username, String password) {
        // 配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(StrUtil.format("{}?serverTimezone=GMT%2B8&tinyInt1isBit=false&useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true", shortJdbcUrl));
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        // 创建配置内容，两种风格都可以。
        String projectDir = StrUtil.format("{}/{}", new File("").getAbsoluteFile().getParentFile().getAbsoluteFile(), applicationName);
        GlobalConfig globalConfig = createGlobalConfigUseStyle(projectDir, basePackage, tablePrefix, tables);

        // 通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        // 生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle(String projectDir, String basePackage, String[] tablePrefix, String[] tables) {
        if (ArrayUtil.isEmpty(tables)) {
            throw new RuntimeException("表名为空");
        }
        // 创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        // 注释配置
        globalConfig.getJavadocConfig()
                .setSince(DateTime.now().toString(DatePattern.NORM_DATE_PATTERN))
                .setAuthor("lilu");

        // 包配置
        globalConfig.getPackageConfig()
                .setSourceDir(projectDir + "/src/main/java")
                .setBasePackage(basePackage)
                .setEntityPackage(globalConfig.getBasePackage() + ".domain.entity")
                .setMapperPackage(globalConfig.getBasePackage() + ".mapper")
                .setServicePackage(globalConfig.getBasePackage() + ".service")
                .setServiceImplPackage(globalConfig.getBasePackage() + ".service.impl")
                .setControllerPackage(globalConfig.getBasePackage() + ".controller")
                //.setTableDefPackage(globalConfig.getEntityPackage() + ".tables")
                .setMapperXmlPath(projectDir + "/src/main/resources/mapper")
        ;

        // 策略配置
        globalConfig.getStrategyConfig()
                .setVersionColumn("version")
                .setLogicDeleteColumn("deleted")
                .setTablePrefix(tablePrefix)
                .setGenerateTable(tables);

        // 模板配置
        globalConfig.getTemplateConfig()
                .setEntity("/kitty/entity.tpl")
                .setMapper("/kitty/mapper.tpl")
                .setService("/kitty/service.tpl")
                .setServiceImpl("/kitty/serviceImpl.tpl")
                .setController("/kitty/controller.tpl")
                .setTableDef("/kitty/tableDef.tpl")
                .setMapperXml("/kitty/mapperXml.tpl")
        ;
        System.out.println(globalConfig.getTemplateConfig().getEntity());

        // Entity 生成配置
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.getEntityConfig()
                .setOverwriteEnable(true)
                .setWithLombok(true)
                .setWithSwagger(true)
                .setSuperClass(BaseEntity.class)
                .setSwaggerVersion(EntityConfig.SwaggerVersion.FOX);

        // Mapper 生成配置
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.getMapperConfig()
                .setOverwriteEnable(true)
                .setSuperClass(BaseMapper.class);

        // MapperXml 生成配置
        globalConfig.setMapperXmlGenerateEnable(true);
        globalConfig.getMapperXmlConfig()
                .setOverwriteEnable(true)
                .setFileSuffix("Mapper");

        // Service 生成配置
        globalConfig.setServiceGenerateEnable(true);
        globalConfig.getServiceConfig()
                .setOverwriteEnable(true)
                .setSuperClass(IService.class);

        // ServiceImpl 生成配置
        globalConfig.setServiceImplGenerateEnable(true);
        globalConfig.getServiceImplConfig()
                .setOverwriteEnable(true)
                .setClassSuffix("ServiceImpl")
                .setSuperClass(ServiceImpl.class);

        // Controller 生成配置
        globalConfig.setControllerGenerateEnable(true);
        globalConfig.getControllerConfig()
                .setOverwriteEnable(true)
            .setSuperClass(BaseController.class)
                .setClassSuffix("Controller");

        return globalConfig;
    }

}
