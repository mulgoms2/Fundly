package com.fundly.order.model;

import com.persistence.dto.OrderGiftDetailsDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

@Mapper
public interface OrderGiftDetailsDao {

    // 상세페이지에서 insert

    // 주문페이지에서 select
    OrderGiftDetailsDto select(String order_no) throws DataAccessException;
}
