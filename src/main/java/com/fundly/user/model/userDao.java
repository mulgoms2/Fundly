
package com.fundly.user.model;

import com.persistence.dto.userDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userDao {
    //    @Override
    int count() throws Exception;

    //    @Override
    int insert(userDto dto) throws Exception;
}
