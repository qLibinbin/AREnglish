package com.arenglish.domain;

import java.util.Set;

public class CheckPoint implements Comparable<CheckPoint>{
	/*
	 * are_checkpoint_checkpoint_id(好像无用，随便->自增长)
		are_checkpoint_checkpoint_index(关卡数)
		are_checkpoint_checkpoint_name(关卡名称)
	 */
	private Integer checkpointId;
	private Integer checkpointIndex;
	private String checkpointName;
	private Set<User> users;
	public Integer getCheckpointId() {
		return checkpointId;
	}
	public void setCheckpointId(Integer checkpointId) {
		this.checkpointId = checkpointId;
	}
	public Integer getCheckpointIndex() {
		return checkpointIndex;
	}
	public void setCheckpointIndex(Integer checkpointIndex) {
		this.checkpointIndex = checkpointIndex;
	}
	public String getCheckpointName() {
		return checkpointName;
	}
	public void setCheckpointName(String checkpointName) {
		this.checkpointName = checkpointName;
	}
	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	@Override
	public boolean equals(Object obj) {
		if (this.getCheckpointIndex()==((CheckPoint)obj).getCheckpointIndex()) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "CheckPoint [checkpointId=" + checkpointId + ", checkpointIndex=" + checkpointIndex + ", checkpointName="
				+ checkpointName + "]";
	}
	public int compareTo(CheckPoint obj) {

		return checkpointIndex-obj.getCheckpointId();

	}
	
	
	
	
}
