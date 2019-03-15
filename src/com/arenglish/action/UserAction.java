package com.arenglish.action;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.arenglish.domain.CheckPoint;
import com.arenglish.domain.User;
import com.arenglish.service.UserService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	
	private User user;
	private UserService userService;
	/*
	 * @parameter: user.userAccount , user.userPassword
	 * @Description: get all userInfo
	 */
	public String login() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();  
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter out = response.getWriter();  
        
        User loginUser = userService.login(user);
        if (loginUser==null) {
			loginUser=new User();
		}
        
        Set<CheckPoint> checkpointList = loginUser.getCheckpointList();
        if (checkpointList!=null) {
        	for (CheckPoint checkPoint : checkpointList) {
    			checkPoint.setUsers(null);
    		}
		}
        
        Gson gson=new Gson();
        
        out.write(gson.toJson(loginUser));
        
		return null;
	}
	/*
	 * @parameter: name,account,password,confirmPassword
	 * @Description: get all userInfo
	 */
	public String registerTest() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		
		String userName = request.getParameter("name");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		boolean states=false;
		Gson gson=new Gson();
		if (userName==null || account==null || password ==null || confirmPassword==null) {
			out.write(gson.toJson(states));
			return null;
		}
		if (!password.equals(confirmPassword)) {
			out.write(gson.toJson(states));
			return null;
		}
		user=new User();
		user.setUserName(userName);
		user.setUserAccount(account);
		user.setUserPassword(password);
		user.setUserCredit(0);
		
		if (userService.saveNewUser(user)) {
			states=true;
		}
		out.write(gson.toJson(states));
		return null;
		
	}
	/*
	 * @parameter: user.userId
	 * @Description: get all userInfo
	 */
	public String getUserById() throws Exception {
		
		User userFromDao=userService.getUserById(user.getUserId());
		if (userFromDao==null) {
			userFromDao=new User();
		}
		Gson gson=new Gson();
		
		for (CheckPoint checkPoint : userFromDao.getCheckpointList()) {
			checkPoint.setUsers(null);
		}
		
		ServletActionContext.getResponse().getWriter().write(gson.toJson(userFromDao));
		return null;
	}
	/*
	 * @parameter: user.userId
	 * @Description: get user credit count
	 */
	public String getCredit() throws Exception {
		Integer credit;
		credit=userService.getCreditById(user.getUserId());
		
		Gson gson=new Gson();
		
		ServletActionContext.getResponse().getWriter().write("{\"credit\":"+credit+"}");
		
		return null;
	}
	/*
	 * @parameter: user.userId,creditCount
	 * @Description: change user credit count
	 */
	public String increaseCredit() throws Exception {
		
		boolean flag=false;
		Integer newCredit=-1;
		Integer creditCount =  Integer.valueOf(ServletActionContext.getRequest().getParameter("creditCount"));
		
		flag=userService.modifyCredit(user.getUserId(),true,creditCount);
		if (flag) {
			newCredit = userService.getCreditById(user.getUserId());
		}
		
		
		ServletActionContext.getResponse().getWriter().write("{\"credit\":"+newCredit+",\"states\":\""+flag+"\"}");
		return null;
	}
	/*
	 * @parameter: userId,creditCount
	 * @Description: change user credit count
	 */
	public String decreaseCredit() throws Exception {
		boolean flag=false;
		Integer newCredit=-1;
		Integer creditCount =  Integer.valueOf(ServletActionContext.getRequest().getParameter("creditCount"));
		
		flag=userService.modifyCredit(user.getUserId(),false,creditCount);
		if (flag) {
			newCredit = userService.getCreditById(user.getUserId());
		}
		
		
		ServletActionContext.getResponse().getWriter().write("{\"credit\":"+newCredit+",\"states\":\""+flag+"\"}");
		return null;
	}
	/*
	 * @parameter: user.userId
	 * @Description: get all passing checkpoint
	 */
	public String getAllPassingCheckpoint() throws Exception {
		
		List<CheckPoint> checkPointList;
		//System.out.println(user.getUserId());
		checkPointList=userService.getAllPassingCheckpointById(user.getUserId());
		if (checkPointList==null) {
			checkPointList=new ArrayList<CheckPoint>();
		}
		//清空用户列表，tojson防止无限循环调用
		for (CheckPoint checkPoint : checkPointList) {
			checkPoint.setUsers(null);
		}
		
		Gson gson =new Gson();
		String json = gson.toJson(checkPointList);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().write(json);
		
		return null;
	}
	/*
	 * @parameter: user.userId,checkpointIndex
	 * @Description: add checkpoint
	 */
	public String addPassingCheckpoint() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer checkpointIndex = Integer.valueOf(request.getParameter("checkpointIndex"));
		boolean states=false;
		if(userService.addPassingCheckpoint(user.getUserId(),checkpointIndex)) {
			states=true;
		}
		ServletActionContext.getResponse().getWriter().write("{\"states\":"+states+"}");
		
		return null;
	}
	
	
	//----getter setter
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	
	
}
