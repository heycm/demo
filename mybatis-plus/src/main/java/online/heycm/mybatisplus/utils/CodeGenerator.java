package online.heycm.mybatisplus.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-07-27 21:20
 */
public class CodeGenerator {

    public static void main(String[] args) {

        ResourceBundle rb = ResourceBundle.getBundle("mp-generator");


        // 实例一个代码生成器
        AutoGenerator ag = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(rb.getString("outPutDir")); // 输出路径
        gc.setFileOverride(true); // 同名覆盖
        gc.setActiveRecord(true); // 开启 activeRecord 模式
        gc.setEnableCache(false); // XML 二级缓存
        gc.setBaseResultMap(true); // XML ResultMap
        gc.setBaseColumnList(true); // XML columnList
        gc.setAuthor(rb.getString("author"));
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(rb.getString("isUseSwagger2").equals("true")); // 实体属性 Swagger2 注解

        gc.setEntityName("%s");
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName(gc.getServiceName() + "Impl");
        gc.setMapperName("%sMapper");
        // gc.setXmlName("%sMapper");
        ag.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig ds = new DataSourceConfig();
        ds.setDbType(DbType.MYSQL);
        ds.setTypeConvert(new MySqlTypeConvert());
        ds.setDriverName(rb.getString("dataSource.driverName"));
        ds.setUsername(rb.getString("dataSource.username"));
        ds.setPassword(rb.getString("dataSource.password"));
        ds.setUrl(rb.getString("dataSource.url"));
        ag.setDataSource(ds);

        // 生成策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setTablePrefix(rb.getString("tablePrefix").split(",")); // 表前缀
        sc.setNaming(NamingStrategy.underline_to_camel); // 表名生成策略 t_user_xxx UserXxx，驼峰命名
        sc.setFieldPrefix(rb.getString("fieldPrefix").split(","));
        sc.setEntityLombokModel(rb.getString("isLombokModel").equals("true")); // 实体用 lombok
        sc.setRestControllerStyle(true);//restful api

        sc.setInclude(rb.getString("tables").split(",")); // 需要生成的表
        sc.setLogicDeleteFieldName("is_deleted"); // 逻辑删除列
        List<TableFill> tableFillList = new ArrayList<TableFill>();//自动填充列配置
        tableFillList.add(new TableFill("is_deleted", FieldFill.INSERT));
        tableFillList.add(new TableFill("create_user", FieldFill.INSERT));
        tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
        tableFillList.add(new TableFill("modify_user", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("modify_time", FieldFill.INSERT_UPDATE));
        sc.setTableFillList(tableFillList);
        ag.setStrategy(sc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(rb.getString("parentPackage"));
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("model");
        pc.setMapper("mapper");
        ag.setPackageInfo(pc);

        //注入自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        ArrayList<FileOutConfig> focList = new ArrayList<>();
        // 调整 xml 生成目录
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("xmlOutPutDir") + "/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        ag.setCfg(cfg);

        // 模板设置，null就不生成
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/controller.java.vm");
        tc.setService("/templates/service.java.vm");
        tc.setServiceImpl("/templates/serviceImpl.java.vm");
        tc.setEntity("/templates/entity.java.vm");
        tc.setMapper("/templates/mapper.java.vm");
        tc.setXml(null);

        ag.setTemplate(tc);
        ag.execute();

    }

}
