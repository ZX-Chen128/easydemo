package com.czx.easydemo.mapper;

import com.czx.easydemo.model.Order;
import com.czx.easydemo.model.OrderExample.Criteria;
import com.czx.easydemo.model.OrderExample.Criterion;
import com.czx.easydemo.model.OrderExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class OrderSqlProvider {

    public String countByExample(OrderExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("order");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(OrderExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("order");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(Order record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("order");
        
        if (record.getOrderid() != null) {
            sql.VALUES("orderid", "#{orderid,jdbcType=BIGINT}");
        }
        
        if (record.getBuyer() != null) {
            sql.VALUES("buyer", "#{buyer,jdbcType=CHAR}");
        }
        
        if (record.getBuyerid() != null) {
            sql.VALUES("buyerid", "#{buyerid,jdbcType=BIGINT}");
        }
        
        if (record.getCommodity() != null) {
            sql.VALUES("commodity", "#{commodity,jdbcType=CHAR}");
        }
        
        if (record.getCommodityid() != null) {
            sql.VALUES("commodityid", "#{commodityid,jdbcType=BIGINT}");
        }
        
        if (record.getNumber() != null) {
            sql.VALUES("number", "#{number,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(OrderExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("orderid");
        } else {
            sql.SELECT("orderid");
        }
        sql.SELECT("buyer");
        sql.SELECT("buyerid");
        sql.SELECT("commodity");
        sql.SELECT("commodityid");
        sql.SELECT("number");
        sql.FROM("order");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Order record = (Order) parameter.get("record");
        OrderExample example = (OrderExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("order");
        
        if (record.getOrderid() != null) {
            sql.SET("orderid = #{record.orderid,jdbcType=BIGINT}");
        }
        
        if (record.getBuyer() != null) {
            sql.SET("buyer = #{record.buyer,jdbcType=CHAR}");
        }
        
        if (record.getBuyerid() != null) {
            sql.SET("buyerid = #{record.buyerid,jdbcType=BIGINT}");
        }
        
        if (record.getCommodity() != null) {
            sql.SET("commodity = #{record.commodity,jdbcType=CHAR}");
        }
        
        if (record.getCommodityid() != null) {
            sql.SET("commodityid = #{record.commodityid,jdbcType=BIGINT}");
        }
        
        if (record.getNumber() != null) {
            sql.SET("number = #{record.number,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("order");
        
        sql.SET("orderid = #{record.orderid,jdbcType=BIGINT}");
        sql.SET("buyer = #{record.buyer,jdbcType=CHAR}");
        sql.SET("buyerid = #{record.buyerid,jdbcType=BIGINT}");
        sql.SET("commodity = #{record.commodity,jdbcType=CHAR}");
        sql.SET("commodityid = #{record.commodityid,jdbcType=BIGINT}");
        sql.SET("number = #{record.number,jdbcType=INTEGER}");
        
        OrderExample example = (OrderExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Order record) {
        SQL sql = new SQL();
        sql.UPDATE("order");
        
        if (record.getBuyer() != null) {
            sql.SET("buyer = #{buyer,jdbcType=CHAR}");
        }
        
        if (record.getBuyerid() != null) {
            sql.SET("buyerid = #{buyerid,jdbcType=BIGINT}");
        }
        
        if (record.getCommodity() != null) {
            sql.SET("commodity = #{commodity,jdbcType=CHAR}");
        }
        
        if (record.getCommodityid() != null) {
            sql.SET("commodityid = #{commodityid,jdbcType=BIGINT}");
        }
        
        if (record.getNumber() != null) {
            sql.SET("number = #{number,jdbcType=INTEGER}");
        }
        
        sql.WHERE("orderid = #{orderid,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, OrderExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}