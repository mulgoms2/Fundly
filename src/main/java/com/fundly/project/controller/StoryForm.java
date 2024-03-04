package com.fundly.project.controller;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StoryForm {
    String pj_id;
    String pj_intro;
    String pj_budget;
    String pj_sched;
    String pj_sel_intro;
    String pj_gift_intro;

}
