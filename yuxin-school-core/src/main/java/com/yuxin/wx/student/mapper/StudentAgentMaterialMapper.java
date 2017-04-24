package com.yuxin.wx.student.mapper;

import java.util.List;

import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:StudentAgentMaterial
 * @author wang.zx
 * @date 2014-12-5
 */
public interface StudentAgentMaterialMapper extends BaseMapper<StudentAgentMaterial> {
    List<StudentAgentMaterial> findStudentAgentMaterialByStuId(StudentAgentMaterial material);
    
    void insertMaterial(StudentAgentMaterial material);
    
    Integer queryMaterialExit(StudentAgentMaterial material);
}