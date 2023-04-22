package com.example.demo.entity;

import java.io.Serializable;

import com.example.demo.entity.pk.DicEntityPk;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "DIC")
@IdClass(DicEntityPk.class)
public class DataDic implements Serializable {

	/**
	 * 類別
	 */
	@Id
	@Column(name = "DIC_TYPE")
	private String type;

	/**
	 * 鍵值
	 */
	@Id
	@Column(name = "DIC_KEY")
	private String key;

	/**
	 * 值
	 */
	@Basic
	@Column(name = "DIC_VALUE")
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
