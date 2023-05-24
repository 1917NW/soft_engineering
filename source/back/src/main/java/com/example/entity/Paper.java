package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lxy
 * @since 2023-05-14
 */
@TableName("e_paper")
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "paper_id", type = IdType.AUTO)
    private Integer paperId;

    private String paperName;

    private String paperQuestionList;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }
    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }
    public String getPaperQuestionList() {
        return paperQuestionList;
    }

    public void setPaperQuestionList(String paperQuestionList) {
        this.paperQuestionList = paperQuestionList;
    }

    @Override
    public String toString() {
        return "Paper{" +
            "paperId=" + paperId +
            ", paperName=" + paperName +
            ", paperQuestionList=" + paperQuestionList +
        "}";
    }
}
