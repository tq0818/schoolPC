package com.yuxin.wx.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.system.ISysConfigItemRelationService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.system.mapper.SysConfigItemRelationMapper;

/**
 * Created by Administrator on 2017/7/31.
 */
@Service
@Transactional
public class SysConfigItemRelationServiceImpl extends BaseServiceImpl implements ISysConfigItemRelationService {
    @Autowired
    private SysConfigItemRelationMapper sysConfigItemRelationMapper;
    @Override
    public void insert(SysConfigItemRelation entity) {
        sysConfigItemRelationMapper.insert(entity);
    }

    @Override
    public void update(SysConfigItemRelation entity) {
        sysConfigItemRelationMapper.update(entity);
    }

    @Override
    public void deleteSysConfigItemRelationById(Integer id) {

        List<SysConfigItemRelation> list =findSysConfigItemRelationById(id,null);
        deleteRelation(list);
    }

    @Override
    public void deleteRelation( List<SysConfigItemRelation> list){
            for(int n =  0 ; n<list.size();n++){
                    SysConfigItemRelation relation = list.get(n);
                    List<SysConfigItemRelation> newList =findSysConfigItemRelationById(relation.getId(),null);
                    if(newList.size()>0) {
                        deleteRelation(newList);
                    }
                sysConfigItemRelationMapper.deleteById(relation.getId());

            }
    }

    @Override
    public void deleteById(Integer id) {
        sysConfigItemRelationMapper.deleteById(id);
    }

    @Override
    public void publishRelation(Integer companyId) {
        sysConfigItemRelationMapper.deleteFront(companyId);
        sysConfigItemRelationMapper.publish(companyId);
    }

    @Override
    public List<SysConfigItemRelation> findItemFront(SysConfigItemRelation item) {
        if(item.getId()==null){
            return sysConfigItemRelationMapper.findFirstLevelFront(item.getCompanyId());
        }else{
            return sysConfigItemRelationMapper.findChildrenFront(item);
        }
    }

    @Override
    public List<SysConfigItemRelation> findAllItemFront(Integer companyId) {
        return sysConfigItemRelationMapper.findAllItemFront(companyId);
    }

    @Override
    public List<SysConfigItemRelation> findItemFrontByLevel(Integer level) {
        return sysConfigItemRelationMapper.findItemFrontByLevel(level);
    }

    @Override
    public List<SysConfigItemRelation> findChildByCode(SysConfigItemRelation relation) {
        return sysConfigItemRelationMapper.findChildByCode(relation);
    }

    @Override
    public List<SysConfigItemRelation> findSysConfigItemRelationById(Integer id,Integer companyId) {
        List<SysConfigItemRelation> list = new ArrayList<SysConfigItemRelation>();
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("companyId",companyId);
        params.put("id",id);
        if(id==null){
            list =  sysConfigItemRelationMapper.findFirstLevel(params);
        }else{
            list = sysConfigItemRelationMapper.findRelationByParentId(params);
        }
        return list;
    }
    
    @Override
	public List<SysConfigItemRelation> findSysConfigItemRelationByCode(SysConfigItemRelation item) {
    	Map<String,Object> params=new HashMap<String,Object>();
        params.put("companyId",item.getCompanyId());
        params.put("itemCode",item.getItemCode());
		return sysConfigItemRelationMapper.findRelationByCode(params);
	}

	@Override

    public List<SysConfigItemRelation> findRelationByLevel(Integer level) {
        List<SysConfigItemRelation> list = new ArrayList<SysConfigItemRelation>();
        list = sysConfigItemRelationMapper.findRelationByLevel(level);
        return list;
    }

    @Override
    public List<SysConfigItemRelation> findRelationByIds(List<Integer> ids) {
        return sysConfigItemRelationMapper.findRelationByIds(ids);
    }


}
