package com.malonwright.DisneyCollection.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name="items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(min=3)
	private String name;
	private String description;
	@NotNull
	private float value;
	
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;

	// Between Items and Pictures
	@OneToMany(mappedBy="item", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Picture> pics;
	
	//Between Items and Comments
	@OneToMany(mappedBy="item", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Comment> comments;
	
	//Between Items and Locations
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="location_id")
	private Location roomItemIsIn;
	
	//Between Items and Users for Like unlike
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="likes",
			joinColumns = @JoinColumn(name="item_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
			)
	private List<User> likers;
	
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	public Item() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Location getRoomItemIsIn() {
		return roomItemIsIn;
	}
	public void setRoomItemIsIn(Location roomItemIsIn) {
		this.roomItemIsIn = roomItemIsIn;
	}
	public List<User> getLikers() {
		return likers;
	}
	public void setLikers(List<User> likers) {
		this.likers = likers;
	}

	public List<Picture> getPics() {
		return pics;
	}
	public void setPics(List<Picture> pics) {
		this.pics = pics;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
}
