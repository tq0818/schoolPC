package com.yuxin.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.course.Video;
import com.yuxin.wx.vo.course.VideoVo;

/**
 * Service Interface:Users
 *
 * @author chopin
 * @date 2015-3-12
 */
public interface VideoMapper extends BaseMapper<Video> {
	List<VideoVo> findVideo(VideoVo search);

	Integer uploadCount(VideoVo video);

	Integer findSchoolIdByVId(Integer videoId);

	Integer totalCount(VideoVo search);

	void deleteVideoByCCVId(String ccvid);

	String sumVideoSize(VideoVo search);

	List<VideoVo> getGroupSize(Integer companyId);

	List<VideoVo> findSourceByPage(VideoVo search);
	
	Integer findSourceByPageCount(VideoVo search);
	
	List<Video> findEQZero();
	
	Video findVideoByQiniuKeys(@Param("keys") String keys);
	
	List<String> findWebVideoIdBystorageType(Video video);
}