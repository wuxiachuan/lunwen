package com.springboot.dao;

import com.springboot.domain.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProblemDao {
    void insertProblem(Problem problem);
    void updateProblem(Problem problem);
    void resoveProblem(@Param("id") Integer id,@Param("time") String time);
    void finishProblem(@Param("id") Integer id,@Param("time") String time);
    void deleteProblemById(Integer id);
    void deleteProblemByWheelId(Integer id);
    Problem findProblemById(Integer id);
    List<Problem> findProblemByWheelId(Integer wheelId);
    List<Problem> findProblemByFinder(String name);
    List<Problem> findProblemByworker(String name);
    List<Problem> findProblemByFinderUnFinish(String name);
    List<Problem> findProblemByworkerUnFinish(String name);
    List<Problem> findProblemByCond(
            @Param("axleNumber") String axleNumber,
            @Param("axleType") String axleType,
            @Param("problemFinder") String problemFinder,
            @Param("processBelong") String processBelong,
            @Param("worker") String  worker,
            @Param("findTime") String findTime,
            @Param("problemStatus") String  problemStatus,
            @Param("correctTime") String correctTime,
            @Param("confirm") String confirm
            );
}
