package com.fundly.project.controller;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StoryForm {
    private String pj_id;
    private String pj_intro;
    private String pj_budget;
    private String pj_sched;
    private String pj_sel_intro;
    private String pj_gift_intro;
    private boolean isEmpty;
    private boolean edit;

    public boolean isEmpty(){ // 다섯개가 다 null이면 true반환
        return isEmpty = (pj_intro == null && pj_budget == null
                && pj_sched == null && pj_sel_intro == null && pj_gift_intro == null);
    }
    public boolean getIsEmpty(){
        return this.isEmpty; //왜 이 getter는 롬복으로 만들수가 없고 직접 만들어줘야할까
    }
    public boolean getEdit(){
        return this.edit;
    }
    public void setEdit(){
        this.edit = true;
    }
}
