#set(tableComment = isNotBlank(table.getComment()) ? table.getComment() : 'TABLE_NAME')
package #(packageConfig.mapperPackage);

#if(mapperConfig.isMapperAnnotation())
import org.apache.ibatis.annotations.Mapper;
#end
import #(mapperConfig.buildSuperClassImport());
import #(packageConfig.entityPackage).#(table.buildEntityClassName());

/**
 * #(tableComment) 映射
 *
 * @author #(javadocConfig.getAuthor())
 * @since #(javadocConfig.getSince())
 */
#if(mapperConfig.isMapperAnnotation())
@Mapper
#end
public interface #(table.buildMapperClassName()) extends #(mapperConfig.buildSuperClassName())<#(table.buildEntityClassName())> {

}
