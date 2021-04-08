package com.springboot.service;

import com.springboot.dao.ProblemDao;
import com.springboot.domain.Problem;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImp implements ProblemService{
    @Autowired
    private ProblemDao problemDao;

    @Override
    public List<Problem> findProblemByCond(Problem problem) {
        List<Problem> list = null;
        String axleNumber = problem.getAxleNumber();
        String axleType = problem.getAxleType();
        String problemFinder = problem.getProblemFinder();
        String processBelong = problem.getProcessBelong();
        String worker = problem.getWorker();
        String findTime = problem.getFindTime();
        String problemStatus = problem.getProblemStatus();
        String correctTime = problem.getCorrectTime();
        String confirm = problem.getConfirm();
        if (findTime !=null) findTime = "%"+findTime+"%";
        if (correctTime !=null) correctTime = "%"+correctTime+"%";
        list = problemDao.findProblemByCond(
                axleNumber,
                axleType,
                problemFinder,
                processBelong,
                worker,
                findTime,
                problemStatus,
                correctTime,
                confirm);
        return list;
    }
}
