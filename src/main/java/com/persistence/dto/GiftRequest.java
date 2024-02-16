package com.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigInteger;


@AllArgsConstructor
@Data
public class GiftRequest { //view에 의존적인 dto
//    private Integer gift_id;
    private String pj_id;
    @Valid
    private String item_id;
    private Integer item_qty;
    @Valid
    private String gift_name;
    private String gift_qty_lim_yn;
    @Valid
    private Integer gift_total_qty;
    @Valid
    private Integer gift_max_qty_per_person;
    @Valid
    private String gift_ship_due_date;
    private String gift_ship_need_yn;
    @Min(value=1000, message="1000원 이상 입력해주세요")
    @Max(value=10000000, message="10,000,000원 이하로 입력해주세요")
    private BigInteger gift_money;
    private Integer gift_sold_qty = 0;
    private Integer gift_curr_qty = gift_total_qty - gift_sold_qty;
    //이 필드는 그냥 간단한 계산이면 되고, 뷰에 보여주기 위한 용도가 큰데 굳이 DB에 저장해야 할까?






}
