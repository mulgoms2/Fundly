package com.fundly.order.model;

import com.persistence.dto.OrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

@Mapper
public interface OrderDao {

    // 상세페이지에서 insert
    // 주문페이지에서 select
    OrderDto select(String order_list_id) throws DataAccessException;
}
