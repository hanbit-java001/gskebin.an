package com.hanbit.tutor.core.session;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SessionAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionAspect.class);

//	@Pointcut("execution(* com.hanbit..*.get*(..))")
//	public void hanbitGetMethod(){}
//
//	@Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseBody")
//	public void responseBody(){}

//	@Around("hanbitGetMethod() && responseBody()") //안에 선언한 내용 포인트컷
//	public Object aopTestByAnnotation(ProceedingJoinPoint pjp) throws Throwable{

//
//	LOGGER.debug("aopTest ::::::::: start");
//
//		//before
//
//		Object returnValue = pjp.proceed();
//		//Map getData()
//
//		if(returnValue instanceof Map){
//			Map originalResult = (Map)returnValue;
//			originalResult.put("aop", "something");
//		}
//
//		//after
//
//		return returnValue;


	@Around("@annotation(com.hanbit.tutor.core.session.LoginRequired)")
	public Object checkLogin(ProceedingJoinPoint pjp) throws Throwable{

		Session session = SessionHelpler.getSession();

		if(session.isLoggedIn()){
			return pjp.proceed();
		}

//		LOGGER.debug((MethodSignature)pjp.getSignature().);
		MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
		Class returnType = methodSignature.getReturnType();

		if(returnType == String.class){

			return "login";
		}else{

			Map result = new HashMap();
			result.put("errorMsg", "로그인이 필요함");

			return result;
		}
	}
}


