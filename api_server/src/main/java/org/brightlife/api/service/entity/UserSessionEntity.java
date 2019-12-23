package org.brightlife.api.service.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_session")
public class UserSessionEntity {

	@Id
	private long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "session_id")
	private String sessionId;
	
	@Column(name = "login_time")
	private LocalDateTime loginTime;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@Column(name = "last_updated_by")
	private String lastUpdatedBy;
	
	@Column(name = "last_updated_date")
	private LocalDateTime lastUpdatedDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String created_by) {
		this.createdBy = created_by;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime localDate) {
		this.createdDate = localDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime localDateTime) {
		this.loginTime = localDateTime;
	}

}
