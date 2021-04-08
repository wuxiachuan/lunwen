package com.springboot.service;

import com.springboot.domain.Problem;

import java.util.List;

public interface ProblemService {
    List<Problem> findProblemByCond(Problem problem);
}
