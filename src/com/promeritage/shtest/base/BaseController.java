package com.promeritage.shtest.base;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.promeritage.shtest.exception.LogicException;
import com.promeritage.shtest.utils.Constant.Status;
import com.promeritage.shtest.vo.ResponseVO;

public abstract class BaseController {
	private Logger log = Logger.getLogger(this.getClass());
	
	/*
	 * 
	 */
	
	protected String baseDir;
	protected String baseUri;
	
	/**
	 * 
	 * @param request
	 * @param txManager
	 */
	protected void init(HttpServletRequest request){
		String url = request.getRequestURL().toString();
		this.baseUri = url.replace(request.getServletPath(), "");
		this.baseDir = request.getSession().getServletContext().getRealPath("").
				replace(".metadata\\.plugins\\org.eclipse.wst.server.core\\tmp3\\wtpwebapps\\", "");
	    System.setProperty("baseDir", baseDir);
	}
	
	protected Object handleLogicError(LogicException e) {
		ResponseVO responseVO = new ResponseVO();
		responseVO.setStatus(e.getCode());
		responseVO.setMessage(e.getMessage());

		return responseVO;
	}
	
	/**
	 * 
	 * @param req
	 * @param exception
	 * @return
	 */
	protected Object handleError(HttpServletRequest req, Exception e) {
		ResponseVO responseVO = new ResponseVO();
		responseVO.setStatus(Status.UNKNOWN_ERROR.getCode());
		responseVO.setMessage(Status.UNKNOWN_ERROR.getMessage());
		responseVO.setResult(new StringBuilder("Request: " + req.getRequestURL() + ", Throws ****" + e.getMessage() + "****"));
		
		log.error(e, e);
		return responseVO;
	}
}
