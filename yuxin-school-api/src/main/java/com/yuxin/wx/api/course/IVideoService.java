package com.yuxin.wx.api.course;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.course.Video;
import com.yuxin.wx.vo.course.VideoVo;

/**
 * Service Interface:Video
 *
 * @author wang.zx
 * @date 2015-5-8
 */
public interface IVideoService {
	/**
	 *
	 * @Title: saveVideo
	 * @Description: 新增Video
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-5-8
	 * @user by wangzx
	 */
	void insert(Video entity);

	/**
	 *
	 * @Title: batchSaveVideo
	 * @Description: 批量新增Video
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-5-8
	 * @user by wangzx
	 */
	void batchInsert(List<Video> list);

	/**
	 *
	 * @Title: updateVideo
	 * @Description: 编辑Video
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-5-8
	 * @user by wangzx
	 */
	void update(Video entity);

	/**
	 *
	 * @Title: deleteVideoById
	 * @Description: 根据id删除Video
	 * @param id
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-5-8
	 * @user by wangzx
	 */
	void deleteVideoById(Integer id);

	/**
	 *
	 * @Title: deleteVideoByIds
	 * @Description: 根据id批量删除Video
	 * @param ids
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-5-8
	 * @user by wangzx
	 */
	void deleteVideoByIds(Integer[] ids);

	/**
	 * @Description:根据视频ID删除对应的数据(真实删除，不在数据库中保留)
	 * @author wzx
	 * @date 2015-6-12 上午10:39:19
	 * @version 1.0
	 * @param ccvid
	 */
	void deleteVideoByCCVId(String ccvid);

	/**
	 *
	 * @Title: findVideoById
	 * @Description: 根据id查询
	 * @param id
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-5-8
	 * @user by wangzx
	 */
	Video findVideoById(Integer id);

	/**
	 *
	 * @Title: findVideoByPage
	 * @Description: 分页查询
	 * @return
	 * @return List<Video> 返回类型
	 * @throws @exception
	 * @date 2015-5-8
	 * @user by wangzx
	 */
	List<Video> findVideoByPage(Video search);

	/**
	 *
	 * Class Name: IVideoService.java
	 *
	 * @Description: 不分页查询
	 * @author ycl
	 * @date 2015-5-8 下午4:44:24
	 * @modifier
	 * @modify-date 2015-5-8 下午4:44:24
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<VideoVo> findVideo(VideoVo search);

	/**
	 *
	 * Class Name: IVideoService.java
	 *
	 * @Description: 分页查询
	 * @author ycl
	 * @date 2015-5-8 下午4:44:24
	 * @modifier
	 * @modify-date 2015-5-8 下午4:44:24
	 * @version 1.0
	 * @param search
	 * @return
	 */
	public PageFinder<VideoVo> findInfoByPage(VideoVo search);

	/**
	 *
	 * Class Name: IVideoService.java
	 *
	 * @Description: 分页查询数量
	 * @author yuchanglong
	 * @date 2015年6月9日 下午12:15:13
	 * @version 1.0
	 * @param search
	 * @return
	 */
	Integer totalCount(VideoVo search);

	/**
	 *
	 * Class Name: IVideoService.java
	 *
	 * @Description: 查询已上传视频的个数
	 * @author ycl
	 * @date 2015-5-11 下午12:01:57
	 * @modifier
	 * @modify-date 2015-5-11 下午12:01:57
	 * @version 1.0
	 * @param video
	 * @return
	 */
	Integer uploadCount(VideoVo video);

	/**
	 *
	 * Class Name: IVideoService.java
	 *
	 * @Description: 判断该视频是否属于该校区
	 * @author yuchanglong
	 * @date 2015年6月8日 上午11:04:10
	 * @version 1.0
	 * @param videoId
	 * @return
	 */
	Boolean isGroupSchool(Integer videoId, Integer currentSchoolId);

	/**
	 * @Description 查询所有的资源，视频资源， 文档等
	 * @param search
	 * @return
	 */
	PageFinder<VideoVo> findSourceByPage(VideoVo search);
	
	Video findVideoByQiniuKeys(String keys);

}