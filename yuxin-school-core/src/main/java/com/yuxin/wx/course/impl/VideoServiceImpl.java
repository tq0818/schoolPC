package com.yuxin.wx.course.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.IVideoService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.course.mapper.VideoMapper;
import com.yuxin.wx.model.course.Video;
import com.yuxin.wx.vo.course.VideoVo;

/**
 * Service Implementation:Video
 *
 * @author wang.zx
 * @date 2015-5-8
 */
@Service
@Transactional
public class VideoServiceImpl extends BaseServiceImpl implements IVideoService {
	Log log = LogFactory.getLog("log");
	@Autowired
	private VideoMapper videoMapper;

	/**
	 *
	 * @Title: saveVideo
	 * @Description: 新增Video
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-5-8
	 * @user by chopin
	 */
	@Override
	public void insert(Video entity) {
		videoMapper.insert(entity);
	};

	/**
	 *
	 * @Title: batchSaveVideo
	 * @Description: 批量新增Video
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-5-8
	 * @user by chopin
	 */
	@Override
	public void batchInsert(List<Video> entity) {
		videoMapper.batchInsert(entity);
	};

	/**
	 *
	 * @Title: updateVideo
	 * @Description: 编辑Video
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-5-8
	 * @user by chopin
	 */
	@Override
	public void update(Video entity) {
		videoMapper.update(entity);
	};

	/**
	 *
	 * @Title: deleteVideoById
	 * @Description: 根据id删除Video
	 * @param id
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-5-8
	 * @user by chopin
	 */
	@Override
	public void deleteVideoById(Integer id) {
		videoMapper.deleteById(id);
	};

	/**
	 *
	 * @Title: deleteVideoByIds
	 * @Description: 根据id批量删除Video
	 * @param ids
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-5-8
	 * @user by chopin
	 */
	@Override
	public void deleteVideoByIds(Integer[] ids) {
		videoMapper.deleteByIds(ids);
	};

	/**
	 *
	 * @Title: findVideoById
	 * @Description: 根据id查询
	 * @param id
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-5-8
	 * @user by chopin
	 */
	@Override
	public Video findVideoById(Integer id) {
		return videoMapper.findById(id);
	};

	/**
	 *
	 * @Title: findVideoByPage
	 * @Description: 分页查询
	 * @return
	 * @return List<Video> 返回类型
	 * @throws @exception
	 * @date 2015-5-8
	 * @user by chopin
	 */
	@Override
	public List<Video> findVideoByPage(Video search) {
		return videoMapper.page(search);
	}

	@Override
	public List<VideoVo> findVideo(VideoVo search) {
		// TODO Auto-generated method stub
		return videoMapper.findVideo(search);
	}

	@Override
	public Integer uploadCount(VideoVo video) {
		// TODO Auto-generated method stub
		return videoMapper.uploadCount(video);
	}

	@Override
	public Boolean isGroupSchool(Integer videoId, Integer currentSchoolId) {
		// TODO Auto-generated method stub
		Integer schoolId = videoMapper.findSchoolIdByVId(videoId);
		log.info("当前登陆用户schoolId：" + currentSchoolId);
		log.info("视频所属schoolId：" + schoolId);
		if (currentSchoolId != null) {
			if (currentSchoolId.equals(schoolId)) {
				log.info("返回值：true");
				return true;
			}
		}
		return false;
	}

	@Override
	public PageFinder<VideoVo> findInfoByPage(VideoVo search) {
		// TODO Auto-generated method stub
		List<VideoVo> list = videoMapper.findVideo(search);
		Integer totalCount = videoMapper.totalCount(search);
		PageFinder<VideoVo> pageFinder = new PageFinder<VideoVo>(search.getPage(), search.getPageSize(), totalCount, list);
		return pageFinder;

	}

	@Override
	public Integer totalCount(VideoVo search) {
		// TODO Auto-generated method stub
		return videoMapper.totalCount(search);
	}

	@Override
	public void deleteVideoByCCVId(String ccvid) {
		videoMapper.deleteVideoByCCVId(ccvid);
	}

	@Override
	public PageFinder<VideoVo> findSourceByPage(VideoVo search) {
		List<VideoVo> list = videoMapper.findSourceByPage(search);
		// Integer totalCount = videoMapper.totalCount(search);
		Integer totalCount = videoMapper.findSourceByPageCount(search);
		PageFinder<VideoVo> pageFinder = new PageFinder<VideoVo>(search.getPage(), search.getPageSize(), totalCount, list);
		return pageFinder;
	}

	@Override
	public Video findVideoByQiniuKeys(String keys) {
		// TODO Auto-generated method stub
		return videoMapper.findVideoByQiniuKeys(keys);
	}

}
