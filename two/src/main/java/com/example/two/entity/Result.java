package com.example.two.entity;

import java.io.Serializable;

public class Result<T>  implements Serializable {
	
	private static final long serialVersionUID = -4214236501903574966L;
	protected String message;
	protected T value;
	protected boolean success;
	protected int code;
	
	private Result() {
	}
	
	public static <T> Result<T> success() {
		Result<T> vo = new Result<T>();
		vo.setCode(200);
		vo.setMessage("请求成功");
		vo.setSuccess(true);
		return vo;
	}
	
	public static <T> Result<T> success(String message) {
		Result<T> vo = new Result<T>();
		vo.setCode(200);
		vo.setMessage(message);
		vo.setSuccess(true);
		return vo;
	}
	
	public static <T> Result<T> success(T value) {
		Result<T> vo = new Result<T>();
		vo.setCode(200);
		vo.setMessage("请求成功");
		vo.setSuccess(true);
		vo.setValue(value);
		return vo;
	}
	
	public static <T> Result<T> err() {
		Result<T> vo = new Result<T>();
		vo.setCode(500);
		vo.setMessage("请求失败");
		vo.setSuccess(false);
		return vo;
	}
	
	public static <T> Result<T> err(String message) {
		Result<T> vo = new Result<T>();
		vo.setCode(500);
		vo.setMessage(message);
		vo.setSuccess(false);
		return vo;
	}
	
	public static <T> Result<T> err(Integer code,String message) {
		Result<T> vo = new Result<T>();
		vo.setCode(code);
		vo.setMessage(message);
		vo.setSuccess(false);
		return vo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
