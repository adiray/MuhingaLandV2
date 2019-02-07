package com.example.dell.muhingalandv2;

import com.google.gson.annotations.SerializedName;

public class SongsItem{

	@SerializedName("Artist")
	private String artist;

	@SerializedName("file")
	private String file;

	@SerializedName("created")
	private long created;

	@SerializedName("___class")
	private String ___class;

	@SerializedName("cover_image")
	private String coverImage;

	@SerializedName("title")
	private String title;

	@SerializedName("ownerId")
	private String ownerId;

	@SerializedName("updated")
	private long updated;

	@SerializedName("objectId")
	private String objectId;

	public void setArtist(String artist){
		this.artist = artist;
	}

	public String getArtist(){
		return artist;
	}

	public void setFile(String file){
		this.file = file;
	}

	public String getFile(){
		return file;
	}

	public void setCreated(long created){
		this.created = created;
	}

	public long getCreated(){
		return created;
	}

	public void setClass(String ___class){
		this.___class = ___class;
	}

	public String get___class(){
		return ___class;
	}

	public void setCoverImage(String coverImage){
		this.coverImage = coverImage;
	}

	public String getCoverImage(){
		return coverImage;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setOwnerId(String ownerId){
		this.ownerId = ownerId;
	}

	public Object getOwnerId(){
		return ownerId;
	}

	public void setUpdated(long updated){
		this.updated = updated;
	}

	public long getUpdated(){
		return updated;
	}

	public void setObjectId(String objectId){
		this.objectId = objectId;
	}

	public String getObjectId(){
		return objectId;
	}

	@Override
 	public String toString(){
		return 
			"SongsItem{" + 
			"artist = '" + artist + '\'' + 
			",file = '" + file + '\'' + 
			",created = '" + created + '\'' + 
			",___class = '" +___class + '\'' +
			",cover_image = '" + coverImage + '\'' + 
			",title = '" + title + '\'' + 
			",ownerId = '" + ownerId + '\'' + 
			",updated = '" + updated + '\'' + 
			",objectId = '" + objectId + '\'' + 
			"}";
		}
}