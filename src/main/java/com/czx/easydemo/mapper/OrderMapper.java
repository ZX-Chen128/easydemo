package com.czx.easydemo.mapper;

import com.czx.easydemo.model.Order;
import com.czx.easydemo.model.OrderExample;
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

public interface OrderMapper {
    @SelectProvider(type=OrderSqlProvider.class, method="countByExample")
    int countByExample(OrderExample example);

    @DeleteProvider(type=OrderSqlProvider.class, method="deleteByExample")
    int deleteByExample(OrderExample example);

    @Delete({
        "delete from order",
        "where orderid = #{orderid,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long orderid);

    @Insert({
        "insert into order (orderid, buyer, ",
        "commodity, number)",
        "values (#{orderid,jdbcType=BIGINT}, #{buyer,jdbcType=INTEGER}, ",
        "#{commodity,jdbcType=INTEGER}, #{number,jdbcType=INTEGER})"
    })
    int insert(Order record);

    @InsertProvider(type=OrderSqlProvider.class, method="insertSelective")
    int insertSelective(Order record);

    @SelectProvider(type=OrderSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="orderid", property="orderid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="buyer", property="buyer", jdbcType=JdbcType.INTEGER),
        @Result(column="commodity", property="commodity", jdbcType=JdbcType.INTEGER),
        @Result(column="number", property="number", jdbcType=JdbcType.INTEGER)
    })
    List<Order> selectByExample(OrderExample example);

    @Select({
        "select",
        "orderid, buyer, commodity, number",
        "from order",
        "where orderid = #{orderid,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="orderid", property="orderid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="buyer", property="buyer", jdbcType=JdbcType.INTEGER),
        @Result(column="commodity", property="commodity", jdbcType=JdbcType.INTEGER),
        @Result(column="number", property="number", jdbcType=JdbcType.INTEGER)
    })
    Order selectByPrimaryKey(Long orderid);

    @UpdateProvider(type=OrderSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    @UpdateProvider(type=OrderSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    @UpdateProvider(type=OrderSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Order record);

    @Update({
        "update order",
        "set buyer = #{buyer,jdbcType=INTEGER},",
          "commodity = #{commodity,jdbcType=INTEGER},",
          "number = #{number,jdbcType=INTEGER}",
        "where orderid = #{orderid,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Order record);
}