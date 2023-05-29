package com.example.mapper;

import com.example.entity.Exam;
import com.example.entity.UserExam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxy
 * @since 2023-05-11
 */
public interface UserExamMapper extends BaseMapper<UserExam> {

    List<Exam> getMyExams(@Param("id") Integer id);
}
