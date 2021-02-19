package com.rnb.newbase.persistence.generator.customize.plugin.generator;

import com.rnb.newbase.persistence.generator.MybatisConfig;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

import java.io.*;


public class CustomJavaMapperMethodGenerator extends AbstractJavaMapperMethodGenerator {
    //修改mapper类
    @Override
    public void addInterfaceElements(Interface interfaze) {
        FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType("BaseMapper<"
                + introspectedTable.getBaseRecordType() + ">");
        /**
         * 添加extends
         */
        interfaze.addSuperInterface(fullyQualifiedJavaType);
        /**
         * 添加import
         */
        interfaze.addImportedType(new FullyQualifiedJavaType(
                "com.rnb.newbase.persistence.mapper.BaseMapper"));
        interfaze.addImportedType(new FullyQualifiedJavaType(
                introspectedTable.getBaseRecordType()));
        interfaze.addImportedType(new FullyQualifiedJavaType(
                "org.apache.ibatis.annotations.Mapper"));
        interfaze.addImportedType(new FullyQualifiedJavaType(
                "org.springframework.stereotype.Repository"));
        interfaze.addAnnotation("@Mapper");
        interfaze.addAnnotation("@Repository");
        /**
         * 方法不需要
         */
        interfaze.getMethods().clear();


        //生成dao
        try{
            generatedDao(introspectedTable,interfaze);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void generatedDao(IntrospectedTable introspectedTable,Interface interfaze) throws Exception{
//        File directory = shellCallback.getDirectory(gjf
//                interfaze.getTargetProject(), gjf.getTargetPackage());
        File targetFile = new File(getDirectory(generatedJavaFileName(introspectedTable)));
        writeFile(targetFile, getContent(interfaze,introspectedTable),new String("UTF-8"));
    }

    private String getDirectory(String generatedJavaFileName) {
        StringBuilder sb = new StringBuilder();
        sb.append(generatedJavaFileName.replace(".","\\"));
        sb.append(".java");
        return sb.toString();
    }

    private String generatorFileLocation(String name){
        return null;
    }

    private String generatedJavaFileName(IntrospectedTable introspectedTable) {
        String sb = introspectedTable.getMyBatis3JavaMapperType()
                .replace("Mapper","Dao")
                .replace("mapper","dao");
        return sb;
    }

    private void writeFile(File file, String content, String fileEncoding) throws IOException {
        File f = new File(file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf("\\")));
        f.mkdirs();
        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw;
        if (fileEncoding == null) {
            osw = new OutputStreamWriter(fos);
        } else {
            osw = new OutputStreamWriter(fos, fileEncoding);
        }
        try (BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write(content);
        }
    }

    public String getContent(Interface interfaze, IntrospectedTable introspectedTable){
        String filename = introspectedTable.getMyBatis3JavaMapperType()
                .replace("mapper","dao");
        StringBuilder sb = new StringBuilder();
        sb.append("package "+filename.substring(0,filename.lastIndexOf("."))+";\n" +
                "\n" +
                "import com.rnb.newbase.persistence.dao.BaseDao;\n" +
                "import com.rnb.newbase.persistence.mapper.BaseMapper;\n" +
                "import "+introspectedTable.getMyBatis3JavaMapperType()+";\n" +
                "import "+introspectedTable.getBaseRecordType()+";\n" +
                "import org.springframework.stereotype.Repository;\n" +
                "\n" +
                "import javax.annotation.Resource;\n" +
                "\n" +
                "@Repository\n" +
                "public class "+introspectedTable.getMyBatis3JavaMapperType()
                .replace("Mapper","Dao").substring(introspectedTable.getMyBatis3JavaMapperType().lastIndexOf(".")+1)+" extends BaseDao<"+introspectedTable.getBaseRecordType().substring(introspectedTable.getBaseRecordType().lastIndexOf(".")+1)+"> {\n" +
                "    @Resource\n" +
                "    private "+introspectedTable.getMyBatis3JavaMapperType().substring(introspectedTable.getMyBatis3JavaMapperType().lastIndexOf(".")+1)+" mapper;\n" +
                "    @Override\n" +
                "    public BaseMapper<"+introspectedTable.getBaseRecordType().substring(introspectedTable.getBaseRecordType().lastIndexOf(".")+1)+"> getBaseMapper() {\n" +
                "        return mapper;\n" +
                "    }\n" +
                "}");
        return sb.toString();
    }


}
