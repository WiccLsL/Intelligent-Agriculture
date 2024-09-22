package com.example.smartagriculture.mapper;

import com.example.smartagriculture.entity.FertilizerWateringPlan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FertilizerWateringPlanMapper {

    /**
     * 获取所有施肥浇水计划
     * @return 施肥浇水计划列表
     */
    List<FertilizerWateringPlan> getAllPlans();

    /**
     * 根据ID获取施肥浇水计划
     * @param cropId 计划ID
     * @return 指定ID的施肥浇水计划
     */
    List<FertilizerWateringPlan> getPlanByCropId(Integer cropId);

    FertilizerWateringPlan getPlanById(Integer id);

    /**
     * 添加新的施肥浇水计划
     * @param plan 施肥浇水计划对象
     */
    void addPlan(FertilizerWateringPlan plan);

    /**
     * 更新施肥浇水计划
     * @param plan 包含更新信息的施肥浇水计划对象
     */
    void updatePlan(FertilizerWateringPlan plan);

    /**
     * 删除施肥浇水计划
     * @param id 计划ID
     */
    void deletePlan(Integer id);
}
