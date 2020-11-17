package com.itheima.mm.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：yp
 * @description : 封装正常返回结果
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Result implements Serializable {
	private boolean flag;//执行结果，true为执行成功 false为执行失败
	private String message;//返回结果信息
	private Object result;//返回数据
	public Result(boolean flag,String message){
		this.flag = flag;
		this.message = message;
	}
}
