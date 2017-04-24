package com.yuxin.wx.shiro.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

import com.yuxin.wx.common.ViewResult;

public class GobalSessionFilter extends PathMatchingFilter {
	Log log=LogFactory.getLog("log");
    private String loginUrl = "";
    private String firstloginUrl = "";
    private String registUrl="";
    private String successUrl = "";
    private String baseUrl="";
    private String guideUrl="";
    private String registCompanyUrl="";
    private String captchaUrl="";
    private String userStatusUrl="";
    private String certificationUrl="";
    private String chooseServiceUrl="";
    private String chooseServiceUrl2="";
    private String chooseServiceUrl3="";
    private List<String> filterPages=new ArrayList<String>();
    private List<String> resources= new ArrayList<String>();

    /**
     * 预处理/前置处理
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(isResource(req)){
            WebUtils.toHttp(req);
            return true;
        }
        if (SecurityUtils.getSubject().getPrincipal()!=null) {//session未过期并且已经登录

            if(isLoginRequest(req) || isBaseRequest(req)){
                redirectToSuccessUrl(req, resp);
                return false;
            }
            Integer company_first_use=(Integer)com.yuxin.wx.utils.WebUtils.getSessionAttribute("company_first_use");//第一次使用标记
            if((company_first_use==null || company_first_use!=1) && !isChooseServiceRequest(req)){
            	redirectToChooseServiceUrl(req, resp);
            }
            	//购买超期的，功能还能使用，只是不能报名、直播、录播
             WebUtils.toHttp(req);
             return true;
        }else{
        	if(com.yuxin.wx.utils.WebUtils.isAjax(request)){
        		com.yuxin.wx.utils.WebUtils.sendJson(resp, new ViewResult(true,"您尚未登录或操作超时,请重新登录!"));
        	}else{
        		saveRequestAndRedirectToLogin(req, resp);
        	}
            
            return false;
        }
    }
    
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return super.onPreHandle(request, response, mappedValue);
    }
    
    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
        // TODO Auto-generated method stub
        super.afterCompletion(request, response, exception);
    }

    private boolean redirectToSuccessUrl(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebUtils.redirectToSavedRequest(req, resp, successUrl);
        return false;
    }
    private boolean redirectToCertification(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	WebUtils.redirectToSavedRequest(req, resp, guideUrl);
    	return false;
    }
    private boolean redirectToChooseServiceUrl(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebUtils.redirectToSavedRequest(req, resp, chooseServiceUrl);
        return false;
    }
    private void saveRequestAndRedirectToLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebUtils.issueRedirect(req, resp, loginUrl);
    }
    private void redirectGotoBuy(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	WebUtils.issueRedirect(req, resp, guideUrl);
    }

    private boolean isLoginRequest(HttpServletRequest req) {
        return pathsMatch(loginUrl, WebUtils.getPathWithinApplication(req)) || pathsMatch(firstloginUrl, WebUtils.getPathWithinApplication(req));
    }

    private boolean isRegistCompanyRequest(HttpServletRequest req){
        return pathsMatch(registCompanyUrl,WebUtils.getPathWithinApplication(req));
    }

    private boolean isRegistRequest(HttpServletRequest req){
    	return pathsMatch(registUrl,WebUtils.getPathWithinApplication(req));
    }

    private boolean isUserStatusRequest(HttpServletRequest req){
    	return pathsMatch(userStatusUrl,WebUtils.getPathWithinApplication(req));
    }
    private boolean isCaptchaUrlRequest(HttpServletRequest req){
    	return pathsMatch(captchaUrl,WebUtils.getPathWithinApplication(req));
    }
    private boolean isBaseRequest(HttpServletRequest req){
    	return pathsMatch(baseUrl,WebUtils.getPathWithinApplication(req));
    }
    private boolean isGuideRequest(HttpServletRequest req){
    	return pathsMatch(guideUrl,WebUtils.getPathWithinApplication(req));
    }
    private boolean isChooseServiceRequest(HttpServletRequest req){
    	return pathsMatch(chooseServiceUrl,WebUtils.getPathWithinApplication(req)) 
    			|| pathsMatch(chooseServiceUrl2,WebUtils.getPathWithinApplication(req))
    			|| pathsMatch(chooseServiceUrl3,WebUtils.getPathWithinApplication(req));
    }
  
   /* private boolean isManageUrlRequest(HttpServletRequest req){
    	return pathsMatch(manageUrl,WebUtils.getPathWithinApplication(req));
    }*/
    
    private boolean isFilterPage(HttpServletRequest req){
    	String path=WebUtils.getPathWithinApplication(req);

    	 Boolean isr=true;
    	 for(String r: filterPages){
    		 if(pathsMatch(r,WebUtils.getPathWithinApplication(req))){
    			 isr=false;
    		 }
         }
    	 return isr;
    }
    private boolean isResource(HttpServletRequest req){
        String path=WebUtils.getPathWithinApplication(req);
        Boolean isr=false;
        for(String r: resources){
            if(path.contains(".")){
                String resourceName=path.substring(path.lastIndexOf(".")+1,path.length());
                if(resourceName!=null && resourceName.equals(r)){
                    isr=true;
                }
            }
        }
        return isr;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getGuideUrl() {
		return guideUrl;
	}

	public void setGuideUrl(String guideUrl) {
		this.guideUrl = guideUrl;
	}

	public String getRegistCompanyUrl() {
		return registCompanyUrl;
	}

	public void setRegistCompanyUrl(String registCompanyUrl) {
		this.registCompanyUrl = registCompanyUrl;
	}

	public String getCaptchaUrl() {
		return captchaUrl;
	}

	public void setCaptchaUrl(String captchaUrl) {
		this.captchaUrl = captchaUrl;
	}

	public String getUserStatusUrl() {
		return userStatusUrl;
	}

	public void setUserStatusUrl(String userStatusUrl) {
		this.userStatusUrl = userStatusUrl;
	}

	public String getChooseServiceUrl() {
		return chooseServiceUrl;
	}

	public void setChooseServiceUrl(String chooseServiceUrl) {
		this.chooseServiceUrl = chooseServiceUrl;
	}

	public String getRegistUrl() {
		return registUrl;
	}

	public void setRegistUrl(String registUrl) {
		this.registUrl = registUrl;
	}


	public List<String> getFilterPages() {
		return filterPages;
	}

	public void setFilterPages(List<String> filterPages) {
		this.filterPages = filterPages;
	}

	public String getCertificationUrl() {
		return certificationUrl;
	}

	public void setCertificationUrl(String certificationUrl) {
		this.certificationUrl = certificationUrl;
	}

	public String getChooseServiceUrl2() {
		return chooseServiceUrl2;
	}

	public void setChooseServiceUrl2(String chooseServiceUrl2) {
		this.chooseServiceUrl2 = chooseServiceUrl2;
	}

	public String getChooseServiceUrl3() {
		return chooseServiceUrl3;
	}

	public void setChooseServiceUrl3(String chooseServiceUrl3) {
		this.chooseServiceUrl3 = chooseServiceUrl3;
	}

	public String getFirstloginUrl() {
		return firstloginUrl;
	}

	public void setFirstloginUrl(String firstloginUrl) {
		this.firstloginUrl = firstloginUrl;
	}
	
    
}
