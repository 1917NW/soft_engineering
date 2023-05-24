package com.example;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql:///exam_system";
        String username = "root";
        String password = "root";
        String author = "lxy";
        String moduleName = "sys";
        String srcPath = "D:\\software_engineering\\source\\back\\src\\main\\java";
        String parentPackageName = "com";
        String xmlPath = "D:\\software_engineering\\source\\back\\src\\main\\resources\\mapper\\";
        String tables = "e_question";
        String prefixFilter = "e_";
        String moduleXmlPath = xmlPath;

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir(srcPath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackageName) // 设置父包名
                            .moduleName("example") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, moduleXmlPath)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix(prefixFilter); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
