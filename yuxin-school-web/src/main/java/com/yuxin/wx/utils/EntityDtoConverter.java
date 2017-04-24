package com.yuxin.wx.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.yuxin.wx.utils.reflact.RefBean;


public class EntityDtoConverter {
	
	private static Object beanObj = null;

	private static Object dtoObj = null;
	

    public static Object dto2Bean(Object dto,Object bean)
                                       throws IllegalAccessException,
                                              IllegalArgumentException,
                                              InvocationTargetException,
                                              SecurityException,
                                              ClassNotFoundException,
                                              InstantiationException, NoSuchMethodException
    {
    	
    	converter(bean, dto, "dto2Bean");
    	
    	return beanObj;
    }
    
    


	public static Object bean2Dto(Object bean,Object dto)
                                       throws IllegalAccessException,
                                              IllegalArgumentException,
                                              InvocationTargetException,
                                              SecurityException,
                                              ClassNotFoundException,
                                              InstantiationException, NoSuchMethodException
    {
    	
    	converter(bean, dto, "bean2Dto");    	
    	
    	return dtoObj;
    }
    
    @SuppressWarnings("unchecked")
	private static void converter(Object bean,Object dto,String convertSign)
                                                                  throws IllegalArgumentException,
                                                                         SecurityException,
                                                                         IllegalAccessException,
                                                                         InvocationTargetException,
                                                                         InstantiationException,
                                                                         ClassNotFoundException,
                                                                         NoSuchMethodException{
    	beanObj = bean;
    	dtoObj = dto;
    	
    	Class<?> beanClazz = bean.getClass(); 
    	Class<?> dtoClazz = dto.getClass();
    	
    	Field[] fields = beanClazz.getDeclaredFields();
    	
    	//获取所有DTO类声明的方法
    	Method[] dtoMethod = dtoClazz.getDeclaredMethods();
    	
    	for (Field field : fields) {
    	
    		invokeTool(beanClazz.getDeclaredMethods(), dtoClazz.getDeclaredMethods(), field.getName(),convertSign);
    	}
    	
    	
    	for (Method me : dtoMethod) {
    		
    		Annotation [] ann = me.getAnnotations();
    		
    		for(Annotation annotation : ann){
    			
    			if (annotation.annotationType().equals(RefBean.class)) {
    				
    				RefBean refBean = (RefBean) annotation;
    				//获取指向的实体全称
    				String objName = refBean.beanName();
    				boolean isPrimaryKey = refBean.isPrimaryKey();
    				//分割实体全称
    				String [] fieldObj = objName.split("\\.");
    				//获取指向实体实例
    				Object object = Class.forName(objName).newInstance();
    						
    				//指向实体的属性
    				String fieldName = refBean.filed();
    				
    				//set方法参数类型
    				String [] params = refBean.paramsType();
    				
    				List<Class> paramsList = new ArrayList<Class>();
    				
    				Class [] paramClasses = new Class[params.length];
    				
    				for(int i = 0 ;i < params.length; i ++){
    					
    					//通过注解中 paramsType 值，获取该类型的类型全称
    					String typeStr =getClassFallName(params[i]);
    					
    					Class clazz = Class.forName(typeStr);
    				    				   
    					paramClasses[i] = clazz;
    					paramsList.add(clazz);
    				}
    				
    				//Class [] paramsTypes =  (Class[]) paramsList.toArray();
    				
    				String upperName =
    					Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1);
    				
    				String setMethod = "set"+upperName;
    				String getMethod = "get"+upperName;
    				
    				//获取指向的实体
    				Class cl = object.getClass();
    				
    				if(convertSign.equals("bean2Dto")){
        				
    					//从实体获取关联实体方法字符串  如 ： getOrgans()
        				String getFieldObj = "get"+fieldObj[fieldObj.length-1]; 
        				//获取get实体方法
        				Method getFieldObjMethod = beanObj.getClass().getMethod(getFieldObj);
        				
        				object = getFieldObjMethod.invoke(beanObj);
    					
        				//获取指向实体对应属性值方法
        				Method beanGetValueMethod = cl.getMethod(getMethod); 
        				Method dtoSetValueMethod = dtoClazz.getMethod(setMethod,paramClasses);
        				//执行dto中属性的set方法，取值为 实体对象中对应关联实体属性取值方法
        				dtoSetValueMethod.invoke(dtoObj, beanGetValueMethod.invoke(object));
    				}
    				else if (convertSign.equals("dto2Bean")&&isPrimaryKey){
    					
    				   //指向实体设置主键方法
    				   Method beanSetValueMethod = cl.getMethod(setMethod,paramClasses);
    				   //DTO对象获取指向实体主键值方法
    				   Method dtoGetValueMethod = dtoClazz.getMethod(getMethod);
    				   //bean对象设置指向对象方法
    				   String setObjField = "set"+fieldObj[fieldObj.length-1];
    				   //获取bean对象设置指向对象方法
    				   Method setFieldObjMethod = beanObj.getClass().getMethod(setObjField,object.getClass());
    				   
    				   //设置指向实体主键
    				   beanSetValueMethod.invoke(object,dtoGetValueMethod.invoke(dtoObj));
    				   
    				   setFieldObjMethod.invoke(beanObj, object);
    				}
    				
    			}
    			
    		}

    	}
    }
    
    
    /**
     *   根据类名获取类实例
     * 
     *@Title:dynamicCreateInstanceByName
     *@Description:根据类名获取类实例对象
     *@param:@param className
     *                   类名全称，如：com.paySystem.ic.bean.base.Organs
     *@param:@return
     *@param:@throws ClassNotFoundException
     *@param:@throws InstantiationException
     *@param:@throws IllegalAccessException
     *@return:Object 
     *                   通过类名获取的实例对象
     *@author:
     *         谢洪飞
     *@Thorws:
     */
    @SuppressWarnings({ "unchecked" })
	private static Object dynamicCreateInstanceByName(String className)
                                                  throws ClassNotFoundException,
                                                         InstantiationException,
                                                         IllegalAccessException
   {
    	
    	Class cl;
    	Object obj;
    	
    	cl = Class.forName(className);
    	
    	obj = (cl.getClassLoader().loadClass(className)).newInstance();
    	
    	return obj;
    }
    
    
	/**
	 *   
	 *   反射工具
	 * 
	 *@Title:invokeTool
	 *@Description: 反射工具：根据Bean对象声明方法 与 DTO对象声明方法相互设值
	 *@param:@param beanMethods
	 *              Bean对象所有声明的方法，如：getName/setName
	 *@param:@param dtoMethods
	 *              DTO对象所有声明方法
     *@param:@param name
	 *              属性名称
	 *              
	 *@author: 谢洪飞
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 *@Thorws:
	 */
    private static void invokeTool(Method[] beanMethods,Method[] dtoMethods,String name,String convertSign)
                                                           throws IllegalAccessException,
                                                                  IllegalArgumentException,
                                                                  InvocationTargetException,
                                                                  SecurityException,
                                                                  NoSuchMethodException{
    	
    	//获取属性首字母大写字符串
    	String upperName =
    		Character.toUpperCase(name.charAt(0))+name.substring(1);
    	
    	//获取setter 与  getter 方法名称
    	String setterName = "set"+upperName;
    	String getterName = "get"+upperName;
    	
    	//获取 getter / setter 方法
    	Method beanGetMethod = getMethodByName(beanMethods,getterName);
    	Method beanSetMethod = getMethodByName(beanMethods, setterName);
    	Method dtoGetMethod = getMethodByName(dtoMethods,getterName); 
    	Method dtoSetMethod = getMethodByName(dtoMethods, setterName);    
    	
    	
    	if(convertSign.equals("dto2Bean")
    			&& dtoGetMethod != null
    			&& dtoSetMethod != null)
    	{
    		beanSetMethod.invoke(beanObj, dtoGetMethod.invoke(dtoObj));
    	    	
    	}
    	else if (convertSign.equals("bean2Dto")
    			       && beanGetMethod != null
    			       && dtoGetMethod != null)
    	{
    		dtoSetMethod.invoke(dtoObj, beanGetMethod.invoke(beanObj));
    		
    	}
    	
    }
    
	/**
	 *   根据方法名称获取方法
	 * 
	 *@Title:getMethodByName
	 *@Description: 根据方法名称获取方法
	 *@param:@param methods
	 *              Bean/Dto所有声明的方法，如：getName/setName
	 *@param:@param methodName
	 *              方法名称
	 *@author: 谢洪飞
	 *@Thorws:
	 */
    private static Method getMethodByName(Method[] methods,String methodName){
    	
    	for(Method m : methods){
    		if(m.getName().equals(methodName)){
    			return m;
    		}
    	}
    	return null;
    }
    
    
    /**
     *   设置实体中关联实体对象
     *   
     *@Title:refBeanInvoke
     *@Description:设置实体中关联实体对象 如： merchants.setOrgans(organs);
     *@param:@param refBean RefBean 注解对象
     *@param:@param dtoMethod
     *                        DTO对象中所有声明的方法数组
     *@param:@param beanMethod
     *                       实体Bean对象中所有声明的方法数组
     *@param:@throws IllegalArgumentException
     *@param:@throws IllegalAccessException
     *@param:@throws InvocationTargetException
     *@param:@throws ClassNotFoundException
     *@param:@throws InstantiationException
     *@Return:void
     *@author: 谢洪飞
     *@Thorws:
     */
    @SuppressWarnings("unused")
	private static void refBeanGetInvoke(RefBean refBean,Method[] dtoMethod,Method[] beanMethod)
                                                                             throws IllegalArgumentException,
                                                                                    IllegalAccessException,
                                                                                    InvocationTargetException,
                                                                                    ClassNotFoundException,
                                                                                    InstantiationException
      {
    	 //获取注解内容beanName与filed
	     String beanName = refBean.beanName();
	     String f = refBean.filed();
	     
	     //获取属性实体对象
	     Object o = dynamicCreateInstanceByName(beanName);
	     
	     //获取属性实体clazz对象
	     Class cl = o.getClass();
	     
	     //获取所有属性实体对象声明的方法
	     Method[] fbeanMethods = o.getClass().getDeclaredMethods();
	     
	     String [] bn = beanName.split("\\.");
	    
	     //获取实体中SetObject方法名称
	     String setObj ="set"+bn[bn.length-1];
	    
	     String uppName = Character.toUpperCase(f.charAt(0))+f.substring(1);
	    
	     //获取实体属性中 setPrimaryKey 方法名称
	     String setPrimaryKey = "set"+uppName;
	    
	     //获取关联实体 set主键方法
	     Method setPriMethod =getMethodByName(fbeanMethods, setPrimaryKey);
	        
	     //获取要设置的值的方法
	     String getPriFromDto = "get"+uppName;

	     //dto中取值方法
		 Method getPriMethod = getMethodByName(dtoMethod,getPriFromDto);
		
		//设置object主键值
		setPriMethod.invoke(o,getPriMethod.invoke(dtoObj));
		
		//setObject方法
		Method setObjMethod = getMethodByName(beanMethod,setObj);
		
	    setObjMethod.invoke(beanObj, o); 
	       /* priMethod.invoke(o, args)*/
	    	 
	    	 
    }
    
    
    /**
     *   获取基本类型类全称
     *  
     *@Title:getClassFallName
     *@Description: 根据 "String","Integer"等类型字符串 获取对应类型全称
     *@param:@param shortName 类型字符串，如 "String"/"Integer" 等
     *@param:@return
     *@return:String
     *@author: 谢洪飞
     *@Thorws:
     */
    private static String getClassFallName(String shortName){
    	String classType = "";
    	
    	if(shortName.equals("String")){
    		classType = "java.lang.String";
    	}
    	else if(shortName.equals("Integer")){
    		classType = "java.lang.Integer";
    	}
    	else if (shortName.equals("BigDecimal")){
    		classType = "java.math.BigDecimal";
    	}
    	else if (shortName.equals("Date")){
    		classType = "java.util.Date";
    	}
    	BigDecimal big = new BigDecimal("0.00");
    	
    	
    	
    	return classType;
    }
}
