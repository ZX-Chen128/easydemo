package com.czx.easydemo.mapper;

import com.czx.easydemo.model.Log;
import com.czx.easydemo.model.LogExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface LogMapper {
    @SelectProvider(type=LogSqlProvider.class, method="countByExample")
    int countByExample(LogExample example);

    @DeleteProvider(type=LogSqlProvider.class, method="deleteByExample")
    int deleteByExample(LogExample example);

    @Delete({
        "delete from log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into log (time, spare)",
        "values (#{time,jdbcType=CHAR}, #{spare,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Log record);

    @InsertProvider(type=LogSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Log record);

    @SelectProvider(type=LogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="time", property="time", jdbcType=JdbcType.CHAR),
        @Result(column="spare", property="spare", jdbcType=JdbcType.CHAR)
    })
    List<Log> selectByExample(LogExample example);

    @Select({
        "select",
        "id, time, spare",
        "from log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="time", property="time", jdbcType=JdbcType.CHAR),
        @Result(column="spare", property="spare", jdbcType=JdbcType.CHAR)
    })
    Log selectByPrimaryKey(Integer id);

    @UpdateProvider(type=LogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Log record, @Param("example") LogExample example);

    @UpdateProvider(type=LogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Log record, @Param("example") LogExample example);

    @UpdateProvider(type=LogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Log record);

    @Update({
        "update log",
        "set time = #{time,jdbcType=CHAR},",
          "spare = #{spare,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Log record);
}