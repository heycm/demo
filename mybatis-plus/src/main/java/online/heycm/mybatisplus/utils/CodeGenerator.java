package online.heycm.mybatisplus.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Description MP代码生成器
 * @Author heycm@qq.com
 * @Date 2020-07-27 21:20
 */
public class CodeGenerator {

    public static void main(String[] args) {
        // 读取MP配置文件
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
        gc.setAuthor(rb.getString("author")); // 作者
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
        if ("MYSQL".equals(rb.getString("db.type").toUpperCase())) {
            ds.setDbType(DbType.MYSQL);
            ds.setTypeConvert(new MySqlTypeConvert());
        }
        if ("ORACLE".equals(rb.getString("db.type").toUpperCase())) {
            ds.setDbType(DbType.ORACLE);
            ds.setTypeConvert(new OracleTypeConvert());
        }
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
        // 需要生成的表
        if (StringUtils.isNotBlank(rb.getString("tables"))) {
            sc.setInclude(rb.getString("tables").split(","));
        }
        // 逻辑删除列
        if (StringUtils.isNotBlank(rb.getString("logicDeleteFieldName"))) {
            sc.setLogicDeleteFieldName(rb.getString("logicDeleteFieldName"));
        }
        // 自动填充列配置
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        String[] insert = rb.getString("fieldFill.insert").split(",");
        String[] update = rb.getString("fieldFill.update").split(",");
        String[] insertUpdate = rb.getString("fieldFill.insert_update").split(",");
        if (insert.length != 0) {
            for (String field : insert) {
                tableFillList.add(new TableFill(field, FieldFill.INSERT));
            }
        }
        if (update.length != 0) {
            for (String field : insert) {
                tableFillList.add(new TableFill(field, FieldFill.UPDATE));
            }
        }
        if (insertUpdate.length != 0) {
            for (String field : insert) {
                tableFillList.add(new TableFill(field, FieldFill.INSERT_UPDATE));
            }
        }
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
