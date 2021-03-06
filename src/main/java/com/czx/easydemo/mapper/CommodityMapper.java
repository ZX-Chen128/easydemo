package com.czx.easydemo.mapper;

import com.czx.easydemo.model.Commodity;
import com.czx.easydemo.model.CommodityExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CommodityMapper {
    @SelectProvider(type=CommoditySqlProvider.class, method="countByExample")
    int countByExample(CommodityExample example);

    @DeleteProvider(type=CommoditySqlProvider.class, method="deleteByExample")
    int deleteByExample(CommodityExample example);

    @Delete({
        "delete from commodity",
        "where commodityid = #{commodityid,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long commodityid);

    @Insert({
        "insert into commodity (commodityid, name, ",
        "stock, price)",
        "values (#{commodityid,jdbcType=BIGINT}, #{name,jdbcType=CHAR}, ",
        "#{stock,jdbcType=INTEGER}, #{price,jdbcType=INTEGER})"
    })
    int insert(Commodity record);

    @InsertProvider(type=CommoditySqlProvider.class, method="insertSelective")
    int insertSelective(Commodity record);

    @SelectProvider(type=CommoditySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="commodityid", property="commodityid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="stock", property="stock", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.INTEGER)
    })
    List<Commodity> selectByExample(CommodityExample example);

    @Select({
        "select",
        "commodityid, name, stock, price",
        "from commodity",
        "where commodityid = #{commodityid,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="commodityid", property="commodityid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="stock", property="stock", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.INTEGER)
    })
    Commodity selectByPrimaryKey(Long commodityid);

    @UpdateProvider(type=CommoditySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Commodity record, @Param("example") CommodityExample example);

    @UpdateProvider(type=CommoditySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Commodity record, @Param("example") CommodityExample example);

    @UpdateProvider(type=CommoditySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Commodity record);

    @Update({
        "update commodity",
        "set name = #{name,jdbcType=CHAR},",
          "stock = #{stock,jdbcType=INTEGER},",
          "price = #{price,jdbcType=INTEGER}",
        "where commodityid = #{commodityid,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Commodity record);
}