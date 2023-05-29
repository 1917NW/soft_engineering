package com.example.service.impl;

import com.example.entity.Exam;
import com.example.entity.UserExam;
import com.example.mapper.UserExamMapper;
import com.example.service.IUserExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxy
 * @since 2023-05-11
 */
@Service
public class UserExamServiceImpl extends ServiceImpl<UserExamMapper, UserExam> implements IUserExamService {

    public List<Exam> getMyExam(Integer userId){
        List<Exam> myExams = this.baseMapper.getMyExams(userId);
        return myExams;
    }

}
