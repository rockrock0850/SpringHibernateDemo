package com.promeritage.shtest.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.promeritage.shtest.base.BaseController;
import com.promeritage.shtest.entity.Employee;
import com.promeritage.shtest.exception.LogicException;
import com.promeritage.shtest.service.IEmployeeService;
import com.promeritage.shtest.utils.Constant;
import com.promeritage.shtest.utils.Constant.Status;
import com.promeritage.shtest.utils.ShareTool;
import com.promeritage.shtest.vo.ResponseVO;
import com.promeritage.shtest.vo.form.EmployeeVO;


@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/demo")
public class SpringHibernateDemoController extends BaseController{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private IEmployeeService iEmployeeService;
	
	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.
				currentRequestAttributes()).getRequest();
		super.init(request);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Object index(HttpServletRequest request) throws Exception{
		List<Employee> list = iEmployeeService.select();
		
		return new ModelAndView(Constant.SYSTEM_INDEX, "list", list);
	}
	
	@RequestMapping(value = "/employeeNew", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody Object employeeNew(HttpServletRequest request, @RequestBody @Valid EmployeeVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setMessage(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		Employee employee = iEmployeeService.insert(requestVO);
		if(employee == null){
			throw new LogicException(Status.UNKNOWN_ERROR);
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(employee);
		
		return responseVO;
	}

	@RequestMapping(value = "/employeeDetail/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody Object employeeDetail(HttpServletRequest request, @PathVariable long id) throws Exception{
		Employee employee = iEmployeeService.select(id);
		
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setFirstName(employee.getFirstName());
		employeeVO.setLastName(employee.getLastName());
		employeeVO.setCellPhone(employee.getCellPhone());
		employeeVO.setBirthDate(ShareTool.toStirng(employee.getBirthDate(), Constant.DATE_PATTERN1));
		
		return employeeVO;
	}
	
	@RequestMapping(value = "/employeeUpdate", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody Object employeeUpdate(HttpServletRequest request, @RequestBody @Valid EmployeeVO requestVO, 
			BindingResult result) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		if(result.hasErrors()){
			responseVO.setMessage(ShareTool.simpleFieldError(result.getFieldErrors()));
			return responseVO;
		}
		
		Employee employee = iEmployeeService.update(requestVO);
		if(employee == null){
			throw new LogicException(Status.UNKNOWN_ERROR);
		}
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(employee);

		return responseVO;
	}
	
	@RequestMapping(value = "/employeeDelete/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody Object employeeDelete(HttpServletRequest request, @PathVariable long id) throws Exception{
		ResponseVO responseVO = new ResponseVO();
		
		iEmployeeService.delete(id);
		
		responseVO.setStatus(Status.SUCCESS.getCode());
		responseVO.setMessage(Status.SUCCESS.getMessage());
		responseVO.setResult(id);

		return responseVO;
	}
	
	/*
	 * LogicException Handler
	 */
	
	@Override
	@ExceptionHandler(LogicException.class)
	public Object handleLogicError(LogicException e) {
		return super.handleLogicError(e);
	}

	/*
	 * Exception Handler
	 */
	
	@Override
	@ExceptionHandler(Exception.class)
	public Object handleError(HttpServletRequest req, Exception ex) {
		return super.handleError(req, ex);
	}
	
}